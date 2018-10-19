package com.example.lucer_000.redrider.Post;


import android.support.annotation.NonNull;



public class PostPresenter implements PostContract.Presenter {



    @NonNull
    private final PostContract.View mPostView;

    public PostPresenter(@NonNull PostContract.View postView){
        // mMatchView = checkNotNull(matchView, "matchView cannot be null!");
        mPostView = postView;
        mPostView.setPresenter(this);
    }

    @Override
    public void start() {

    }

//    @Override
//    public void setDriverPostView(){
//        mPostView.setDriverView();
//    }
//
//    @Override
//    public void setRiderPostView(){
//        mPostView.setRiderView();
//    }

}

