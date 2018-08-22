package com.hanlu.doordash_v2;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    private PagerAdapter pagerAdapter;
    private ViewPager container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Home Page");
        setSupportActionBar(toolbar);

        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        container = findViewById(R.id.container);
        container.setAdapter(pagerAdapter);
        container.setCurrentItem(1);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.action_food:
                                switchPages(0);
                                break;
                            case R.id.action_drinks:
                                switchPages(1);
                                break;
                            case R.id.action_orders:
                                switchPages(2);
                                break;
                        }
                        return true;
                    }
                });
        switchPages(0);
    }

    public void switchPages(int i) {
        container.setCurrentItem(i);
    }

}
