package com.example.lucer_000.redrider.Profile;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.lucer_000.redrider.Data.Injection;
import com.example.lucer_000.redrider.Data.Profile;
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


        ProfileFragment profileFragment =  (ProfileFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if(profileFragment == null){
            profileFragment = ProfileFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), profileFragment, R.id.contentFrame
            );
        }

        mProfilePresenter = new ProfilePresenter(Injection.provideTasksRepository(getApplicationContext()),profileFragment);

    }

}
