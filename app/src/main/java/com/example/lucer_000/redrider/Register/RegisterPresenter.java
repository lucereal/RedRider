package com.example.lucer_000.redrider.Register;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.lucer_000.redrider.Data.PostRepository;

public class RegisterPresenter implements RegisterContract.Presenter{
    Context context;
    private final RegisterContract.View mRegisterView;
    PostRepository mPostRepository;

    public RegisterPresenter(PostRepository postRepository, @NonNull RegisterContract.View registerView, Context context) {

        mRegisterView = registerView;
        mRegisterView.setPresenter(this);
        this.context = context;
        this.mPostRepository = postRepository;
    }

    @Override
    public void start() {

    }


}
