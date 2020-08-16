package com.example.emailprocessor;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import java.util.Arrays;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    Messenger m_service = null;
    boolean bound;

    class IncomingHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MyService.MSG_REMOVE_DUPLICATES_FROM_EMAIL_THREAD:
                    LinkedList<String> list = (LinkedList<String>) msg.getData().getSerializable("clean_email_thread");
                    Toast.makeText(getApplicationContext(), "email thread received back: "+list.toString(), Toast.LENGTH_SHORT).show();
                    Log.d("Activity", "email thread received back: "+list.toString());
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    final Messenger m_messenger = new Messenger(new IncomingHandler());

    private ServiceConnection m_connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            m_service = new Messenger(iBinder);
            bound = true;
            Log.d("Activity", "binded");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            m_service = null;
            bound = false;
            Log.d("Activity", "unbinded");
        }
    };

    public void RequestEmailProcessor(View v){
        if (!bound) return;

        Message msg = Message.obtain(null, MyService.MSG_REMOVE_DUPLICATES_FROM_EMAIL_THREAD, 0, 0);
        LinkedList<String> list = new LinkedList<>(Arrays.asList("oi", "bye", "bye", "bye", "oi", "bye"));
        Bundle bundle = new Bundle();
        bundle.putSerializable("email_thread", list);
        msg.setData(bundle);
        msg.replyTo = m_messenger;
        try {
            m_service.send(msg);
        } catch (RemoteException e){
            e.printStackTrace();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Send thread email to remove duplicates: "+Arrays.asList("oi", "bye", "bye", "bye", "oi", "bye").toString(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                RequestEmailProcessor(view);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        bindService(new Intent(this, MyService.class), m_connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (bound){
            unbindService(m_connection);
            bound = false;
        }
    }
}