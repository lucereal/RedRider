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
    public void submitNewPost(String dest,String date, String time){
        //public Rider(String date, String destination, int riderId, String time)
        Rider riderPost = new Rider(dest,date, 1234, time);
        System.out.println("\n\npost: " + riderPost.getDestination());
        System.out.println("\n\npost: " + riderPost.getDate());
        System.out.println("inside rider submit post");
        postRepository.savePost(riderPost);
        System.out.println("after repo save");

        mPostView.submitPostSuccess();
        //come back to this later and create a Post class with a user post list
    }
    @Override
    public void submitNewPost(String dest, String date, String seats, String time, String vehicle){

        System.out.println("dest: " + dest);
        System.out.println("dest: " + date);
        System.out.println("dest: " + time);
        System.out.println("dest: " + seats);
        //String dest, String date, int driverId, int seats, String time, String vehicle
        Driver driverPost = new Driver(dest,date,1234,1,time,vehicle);
        System.out.println("driverPost: " + driverPost.getVehicle());
        System.out.println("\n\npost: " + driverPost.getDestination());
        System.out.println("\n\npost: " + driverPost.getDate());
        System.out.println("\n\npost: " + driverPost.getSeats());
        postRepository.savePost(driverPost);

        System.out.println("inside driver submit post");
        mPostView.submitPostSuccess();
        //come back to this later and create a Post class with a user post list
    }

    @Override
    public void setSubmitBtn(){
        mPostView.setSubmitBtn();
    }
}

