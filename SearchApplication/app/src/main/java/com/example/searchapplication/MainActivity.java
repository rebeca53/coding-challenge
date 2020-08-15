package com.example.searchapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private final LinkedList<String> m_wordList = new LinkedList<>();
    private WordListAdapter m_adapter;
    private RecyclerView m_recyclerView;
    private SearchView m_searchView;

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
        Log.d("MainActivity", "Create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_recyclerView = findViewById(R.id.recyclerview);
        m_adapter = new WordListAdapter(this, m_wordList);
        m_recyclerView.setAdapter(m_adapter);
        m_recyclerView.setLayoutManager(new LinearLayoutManager(this));

        populateWordList();
        Log.d("MainActivity", "CreateFinished");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d("MainActivity", "Create menu");
        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        m_searchView = (SearchView) menu.findItem(R.id.app_bar_search)
                .getActionView();
        m_searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        m_searchView.setMaxWidth(Integer.MAX_VALUE);

        m_searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                m_adapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                m_adapter.getFilter().filter(s);
                return false;
            }
        });
        Log.d("MainActivity", "Create menu finished");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.app_bar_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // close search view on back button pressed
        if (!m_searchView.isIconified()) {
            m_searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }

}