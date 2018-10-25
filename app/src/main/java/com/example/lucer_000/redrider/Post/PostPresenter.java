package com.example.lucer_000.redrider.Post;


import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.example.lucer_000.redrider.Data.Driver;
import com.example.lucer_000.redrider.Data.Rider;
import com.example.lucer_000.redrider.Data.PostRepository;

public class PostPresenter implements PostContract.Presenter {



    @NonNull
    private final PostContract.View mPostView;

    PostRepository postRepository;


    public PostPresenter(PostRepository postRepository,@NonNull PostContract.View postView){
        // mMatchView = checkNotNull(matchView, "matchView cannot be null!");
        mPostView = postView;
        this.postRepository = postRepository;
        mPostView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void submitNewPost(String dest, String date){
        Rider riderPost = new Rider(dest,date);
        postRepository.savePost(riderPost);

        mPostView.submitPostSuccess();
        //come back to this later and create a Post class with a user post list
    }
    @Override
    public void submitNewPost(String dest, String date, String comp, String numSeats){

        Driver driverPost = new Driver(dest,date,comp,Integer.valueOf(numSeats));
        postRepository.savePost(driverPost);

        mPostView.submitPostSuccess();
        //come back to this later and create a Post class with a user post list
    }

    @Override
    public void setSubmitBtn(){
        mPostView.setSubmitBtn();
    }
}

