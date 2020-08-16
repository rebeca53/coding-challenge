package com.example.emailprocessor;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.widget.Toast;

import java.util.LinkedList;

public class MyService extends Service {
    static final int MSG_REMOVE_DUPLICATES_FROM_EMAIL_THREAD = 1;
    static Messenger m_messenger;

    static public LinkedList<String> RemoveDuplicates(LinkedList<String> list){
        LinkedList<String> auxList = list;

        for (int i=0; i<list.size();i++){
            for (int j=i+1; j<list.size();j++){
                if (auxList.get(i) == auxList.get(j)){
                    auxList.remove(j);
                    i = 0;
                }
            }
        }

        return auxList;
    }

    static class IncomingHandler extends Handler{
        private Context applicationContext;

        IncomingHandler(Context context){
            applicationContext = context.getApplicationContext();
        }

        @Override
        public void handleMessage(Message message) {
            switch (message.what){
                case MSG_REMOVE_DUPLICATES_FROM_EMAIL_THREAD:
                    Messenger client = message.replyTo;

                    Bundle bundle = message.getData();
                    LinkedList<String> list= (LinkedList<String>) bundle.getSerializable("email_thread");
                    if (list == null) {
                        break;
                    }

                    list = MyService.RemoveDuplicates(list);
                    //todo remove duplicates

                    try{
                        Message response = Message.obtain(null, MSG_REMOVE_DUPLICATES_FROM_EMAIL_THREAD, 0, 0);

                        Bundle responseBundle = new Bundle();
                        responseBundle.putSerializable("clean_email_thread", list);
                        response.setData(responseBundle);
                        client.send(response);
                    } catch (RemoteException e){
                        e.printStackTrace();
                    }
                    break;
                default:
                    super.handleMessage(message);
            }
        }
    }


    public MyService() {
    }

    //client binds, also creates service. That is alive while any client is binded.
    //called by bindService()
    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(getApplicationContext(), "binding", Toast.LENGTH_SHORT).show();
        m_messenger = new Messenger(new IncomingHandler(this));
        return m_messenger.getBinder();
    }
}
