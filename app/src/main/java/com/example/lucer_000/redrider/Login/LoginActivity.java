package com.example.lucer_000.redrider.Login;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.lucer_000.redrider.Data.Injection;
import com.example.lucer_000.redrider.R;
import com.example.lucer_000.redrider.util.ActivityUtils;

public class LoginActivity extends AppCompatActivity {

    LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_act);

        LoginFragment loginFragment =
                (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if(loginFragment == null){
            loginFragment = LoginFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), loginFragment, R.id.contentFrame
            );
        }

        mLoginPresenter = new LoginPresenter(Injection.provideTasksRepository(getApplicationContext()),loginFragment, getApplicationContext());

    }

}
