package com.example.lucer_000.redrider.Login;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.lucer_000.redrider.Data.PostRepository;
import com.example.lucer_000.redrider.Register.RegisterActivity;

public class LoginPresenter implements LoginContract.Presenter{

    private final LoginContract.View mLoginView;

    public LoginPresenter(PostRepository postRepository, @NonNull LoginContract.View loginView) {

       // mLoginView = checkNotNull(taskDetailView, "taskDetailView cannot be null!");
        mLoginView = loginView;
        mLoginView.setPresenter(this);
    }

    @Override
    public void start() {
        //openTask();
    }

    /*public void openRegister(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }*/
}
