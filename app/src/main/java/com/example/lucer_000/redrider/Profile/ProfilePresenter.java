package com.example.lucer_000.redrider.Profile;

import android.support.annotation.NonNull;

import com.example.lucer_000.redrider.Data.PostRepository;

public class ProfilePresenter implements ProfileContract.Presenter{


    private final ProfileContract.View mProfileView;

    public ProfilePresenter(PostRepository postRepository, @NonNull ProfileContract.View profileView) {

        mProfileView = profileView;
        mProfileView.setPresenter(this);
    }

    @Override
    public void start() {
        //openTask();
    }


}
