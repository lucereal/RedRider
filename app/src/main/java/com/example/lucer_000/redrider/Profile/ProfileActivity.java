package com.example.lucer_000.redrider.Profile;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.lucer_000.redrider.Data.Injection;
import com.example.lucer_000.redrider.Data.Profile;
import com.example.lucer_000.redrider.Login.LoginActivity;

import android.view.View;
import android.widget.Button;

import com.example.lucer_000.redrider.MatchPage.MatchActivity;
import com.example.lucer_000.redrider.R;
import com.example.lucer_000.redrider.util.ActivityUtils;
import com.example.lucer_000.redrider.Profile.ProfileFragment;

import java.io.Serializable;

public class ProfileActivity extends AppCompatActivity {


    private Profile user;
    ProfilePresenter mProfilePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_act);

        String email = getIntent().getStringExtra("Email");
        String pass = getIntent().getStringExtra("Password");



        ProfileFragment profileFragment =  (ProfileFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if(profileFragment == null){
            profileFragment = ProfileFragment.newInstance();
            Bundle bundle = new Bundle();
            bundle.putString("Email", email);
            bundle.putString("Password", pass);
            profileFragment.setArguments(bundle);
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), profileFragment, R.id.contentFrame
            );
        }

        mProfilePresenter = new ProfilePresenter(Injection.provideTasksRepository(getApplicationContext()),profileFragment, getApplicationContext());

        Button matchpage =  findViewById(R.id.matchpagebutton);
        matchpage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), MatchActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.drawer_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.postItem:
                Toast.makeText(this, "Post Selected", Toast.LENGTH_SHORT).show();
                matchPage();
                return true;
            case R.id.profileItem:
                Toast.makeText(this, "Profile Selected", Toast.LENGTH_SHORT).show();
                profilePage();
                return true;
            case R.id.signoutItem:
                Toast.makeText(this, "Sign out Selected", Toast.LENGTH_SHORT).show();
                loginPage();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void matchPage(){
        Intent intent = new Intent(this, MatchActivity.class);
        startActivity(intent);
    }

    public void profilePage(){
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    public void loginPage(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
