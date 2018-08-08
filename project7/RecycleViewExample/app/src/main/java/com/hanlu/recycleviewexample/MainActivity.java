package com.hanlu.recycleviewexample;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;;

import java.util.ArrayList;

import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity implements MovieFragment.CommunicateWithActivity {

    private RecyclerView recyclerView;
    private MovieAdaptor moviesAdapter;
    private FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Movie List");
        setSupportActionBar(toolbar); // have to manually set unlike others
        setupRecyclerView();
        populateList();
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
                if (prev != null)
                    ft.remove(prev);
                ft.addToBackStack(null);
                DialogFragment dialogFragment = new MovieFragment();
                dialogFragment.setCancelable(false);
                dialogFragment.show(ft,"dialog");
            }
    });

}

    public void populateList() {
        for (int i = 0; i < 3; i++) {
            moviesAdapter.addMovie("Superman", "Action", "2013");
            moviesAdapter.addMovie("Iron Man", "Adventure & Action", "2011");
        }
        moviesAdapter.notifyDataSetChanged();
    }

    public void setupRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view);
        moviesAdapter = new MovieAdaptor();
        recyclerView.setAdapter(moviesAdapter);
        // SETUP DISPLAY FOR RECYCLER VIEW
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.addOnScrollListener((new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && fab.getVisibility() == View.VISIBLE) {
                    fab.hide();
                } else if (dy < 0 && fab.getVisibility() != View.VISIBLE) {
                    fab.show();
                }
            }

        }));

    }

    @Override
    public void createMovie(String title, String year, String genre) {
        moviesAdapter.addMovie(title,genre,year);
    }
}
