package com.example.searchapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private final LinkedList<String> m_wordList = new LinkedList<>();
    private WordListAdapter m_adapter;
    private RecyclerView m_recyclerView;

    private void populateWordList()
    {
        m_wordList.addLast("you");
        m_wordList.addLast("probably");
        m_wordList.addLast("despite");
        m_wordList.addLast("moon");
        m_wordList.addLast("misspellings");
        m_wordList.addLast("pale");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateWordList();

        m_recyclerView = findViewById(R.id.recyclerview);
        m_adapter = new WordListAdapter(this, m_wordList);
        m_recyclerView.setAdapter(m_adapter);
        m_recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}