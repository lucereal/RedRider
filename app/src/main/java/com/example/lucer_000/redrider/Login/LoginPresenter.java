package com.example.lucer_000.redrider.Login;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class LoginPresenter implements LoginContract.Presenter{

    private final LoginContract.View mLoginView;


    public LoginPresenter(@NonNull LoginContract.View loginView) {

       // mLoginView = checkNotNull(taskDetailView, "taskDetailView cannot be null!");
        mLoginView = loginView;
        mLoginView.setPresenter(this);
    }

    @Override
    public void start() {
        //openTask();
    }
}
