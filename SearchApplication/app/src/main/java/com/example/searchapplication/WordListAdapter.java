package com.example.searchapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> implements Filterable {
    private final LinkedList<String> m_wordList;
    private LinkedList<String> m_filteredList;

    private LayoutInflater m_inflater;

    public WordListAdapter(Context context, LinkedList<String> m_wordList) {
        m_inflater = LayoutInflater.from(context);
        this.m_wordList = m_wordList;
        this.m_filteredList = m_wordList;
    }

    class WordViewHolder extends RecyclerView.ViewHolder{
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
        String m_current = m_filteredList.get(position);
        holder.wordItemView.setText(m_current);
    }

    @Override
    public int getItemCount() {
        if (m_filteredList == null)
        {
            return 0;
        }
        return m_filteredList.size();
    }

    boolean isPartialPermutation(String wordA, String wordB){
        int lettersChanged = 0;
        int counterA = 0;
        int counterB = 0;

        for (char a : wordA.toCharArray())
        {
            counterB = 0;
            for (char b : wordB.toCharArray())
            {
                if (counterA == counterB){
                    if (counterA == 0 && a != b){
                        return false;
                    }
                    if (a != b)
                    {
                        lettersChanged++;
                    }
                }

                if (b == a){
                    wordB = wordB.substring(0, counterB)+'$' + wordB.substring(counterB+1);
                }

                counterB++;
            }
            counterA++;
        }

        if (lettersChanged/counterA < (2/3))
        {
            return false;
        }

        for (char b : wordB.toCharArray())
        {
            if (b != '$'){
                return false;
            }
        }

        return true;
    }

    boolean isMissingChar(String wordA, String wordB)
    {
        if (wordA.length() < wordB.length()){
            return false;
        }
        for (int i=0; i<wordA.length()-1;i++){
            if (wordA.charAt(i) != wordB.charAt(i))
            {
                return wordA.charAt(i+1) == wordB.charAt(i);
            }
        }

        return true;
    }

    boolean isCharInserted(String wordA, String wordB){
        if (wordB.length() < wordA.length()){
            return false;
        }

        for (int i=0; i<wordB.length()-1; i++){
            if (wordA.charAt(i) != wordB.charAt(i)){
                return wordA.charAt(i) == wordB.charAt(i+1);
            }
        }

        return false;
    }

    boolean isCharReplaced(String wordA, String wordB){
        if (wordA.length() != wordB.length()){
            return false;
        }

        int replacedQuantity = 0;
        for (int i =0; i<wordA.length() -1; i++){
            if (wordA.charAt(i) != wordB.charAt(i)){
                replacedQuantity++;
            }

            if (replacedQuantity > 1){
                return false;
            }
        }

        return true;
    }

    boolean isTypo(String wordA, String wordB){
        return isMissingChar(wordA,wordB) || isCharInserted(wordA,wordB) || isCharReplaced(wordA,wordB);
    }

    boolean isTypoORisPermutation(String wordA, String wordB){
        if (isTypo(wordA, wordB) && isPartialPermutation(wordA, wordB))
            return false;

        return isTypo(wordA, wordB) || isPartialPermutation(wordA, wordB);
    }
    @Override
    public Filter getFilter() {
        Log.d("WordListAdapter", "getFilter");
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    Log.d("WordListAdapter", "empty string");
                    m_filteredList = m_wordList;
                } else {
                    LinkedList<String> filteredList = new LinkedList<String>();
                    for (String row : m_wordList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }
                    Log.d("WordListAdapter", "not empty string");
                    m_filteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = m_filteredList;
                Log.d("WordListAdapter", "filter done");
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                Log.d("WordListAdapter", "publish");
                m_filteredList = (LinkedList<String>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

}
