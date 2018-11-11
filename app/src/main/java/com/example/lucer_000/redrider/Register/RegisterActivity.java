package com.example.lucer_000.redrider.Register;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.lucer_000.redrider.Data.Injection;
import com.example.lucer_000.redrider.R;
import com.example.lucer_000.redrider.util.ActivityUtils;

public class RegisterActivity extends AppCompatActivity {

    RegisterPresenter mRegisterPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_act);

        RegisterFragment registerFragment = (RegisterFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if(registerFragment == null){
            registerFragment = RegisterFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), registerFragment, R.id.contentFrame
            );
        }

        mRegisterPresenter = new RegisterPresenter(Injection.provideTasksRepository(getApplicationContext()),registerFragment, getApplicationContext());
    }

}
