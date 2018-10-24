package com.example.lucer_000.redrider.Login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.lucer_000.redrider.R;
import com.example.lucer_000.redrider.Register.RegisterActivity;

public class LoginFragment extends Fragment implements LoginContract.View{

    LoginContract.Presenter mPresenter;
    View root;
    private TextView signUp;

    @Override
    public void setPresenter(@NonNull LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public LoginFragment(){}
    //empty constructor

    public static LoginFragment newInstance(){
        return new LoginFragment();
    }

    public void setSignUp(){

        signUp = (TextView) root.findViewById(R.id.signup);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegister();
            }
        });
    }

    public void openRegister(){
        Intent intent = new Intent(root.getContext(), RegisterActivity.class);
        startActivity(intent);
    }
}
