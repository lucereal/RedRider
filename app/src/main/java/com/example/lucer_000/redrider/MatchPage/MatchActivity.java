package com.example.lucer_000.redrider.MatchPage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.design.widget.NavigationView;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.lucer_000.redrider.Data.Injection;
import com.example.lucer_000.redrider.util.ActivityUtils;
import com.example.lucer_000.redrider.R;

public class MatchActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private MatchPresenter mMatchPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.match_act);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set up the navigation drawer.
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerLayout.setStatusBarBackground(R.color.colorPrimaryDark);
//        NavigationView navigationView = findViewById(R.id.nav_view);
//        if (navigationView != null) {
//            setupDrawerContent(navigationView);
//        }



        MatchFragment matchFragment =
                (MatchFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if(matchFragment == null){
            //craete fragment
            matchFragment = MatchFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), matchFragment, R.id.contentFrame);

        }

        // Create the presenter
//        mMatchPresenter = new MatchPresenter(
//                Injection.provideTasksRepository(getApplicationContext()), matchFragment);
//
            mMatchPresenter = new MatchPresenter(Injection.provideTasksRepository(getApplicationContext()),matchFragment);
        //injection???


    }

    private void setupDrawerContent(NavigationView navigationView) {
//        navigationView.setNavigationItemSelectedListener(
//                new NavigationView.OnNavigationItemSelectedListener() {
//                    @Override
//                    public boolean onNavigationItemSelected(MenuItem menuItem) {
//                        switch (menuItem.getItemId()) {
//                            case R.id.list_navigation_menu_item:
//                                // Do nothing, we're already on that screen
//                                break;
//                            case R.id.statistics_navigation_menu_item:
//                                Intent intent =
//                                        new Intent(TasksActivity.this, StatisticsActivity.class);
//                                startActivity(intent);
//                                break;
//                            default:
//                                break;
//                        }
//                        // Close the navigation drawer when an item is selected.
//                        menuItem.setChecked(true);
//                        mDrawerLayout.closeDrawers();
//                        return true;
//                    }
//                });
    }

}
