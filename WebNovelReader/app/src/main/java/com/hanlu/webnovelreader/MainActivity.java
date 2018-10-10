package com.hanlu.webnovelreader;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;


public class MainActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {

    private MyRecyclerViewAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WuXiaHelpers wuXiaHelpers = new WuXiaHelpers();
        wuXiaHelpers.getLatestChapters(this);
        // data to populate the RecyclerView with
        String[] data = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};

        // set up the RecyclerView
        recyclerView = findViewById(R.id.recycler_view);
        int numberOfColumns = 3;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        adapter = new MyRecyclerViewAdapter(this, data);
        initializeBook(this.getAssets());
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    public void initializeBook(AssetManager assetManager) {
        InputStream inputStream;
        try {
            inputStream = assetManager.open("novels.txt");
            Scanner kb = new Scanner(inputStream);
            kb.nextLine();
            while (kb.hasNext()) {
                String pictureUrl = kb.nextLine();
                String title = kb.nextLine();
                String unread = kb.nextLine();
                Book book = new Book(pictureUrl, title, unread);
                MyRecyclerViewAdapter.addBook(book);
                kb.nextLine();
            }
            inputStream.close();
            kb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(View view, int position) {
        Book book = adapter.getItem(position);

        Toast.makeText(this, book.getTitle() + "is selected!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, NovelActivity.class);
        startActivity(intent);

        Log.i("TAG", "You clicked number " + adapter.getItem(position).getTitle() + ", which is at cell position " + position);
    }
}