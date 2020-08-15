package com.example.searchapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {
    private final LinkedList<String> m_wordList;

    private LayoutInflater m_inflater;

    public WordListAdapter(Context context, LinkedList<String> m_wordList) {
        m_inflater = LayoutInflater.from(context);
        this.m_wordList = m_wordList;
    }

    class WordViewHolder extends RecyclerView.ViewHolder {
        public final TextView wordItemView;
        final WordListAdapter m_wordListAdapter;

        public WordViewHolder(View itemView, WordListAdapter adapter) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
            this.m_wordListAdapter = adapter;
        }

    }

    @NonNull
    @Override
    public WordListAdapter.WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View m_itemView = m_inflater.inflate(R.layout.list_item, parent, false);
        return new WordViewHolder(m_itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull WordListAdapter.WordViewHolder holder, int position) {
        String m_current = m_wordList.get(position);
        holder.wordItemView.setText(m_current);
    }

    @Override
    public int getItemCount() {
        return m_wordList.size();
    }
}
