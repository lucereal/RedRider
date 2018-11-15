package com.example.lucer_000.redrider.Post;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.example.lucer_000.redrider.Data.Driver;
import com.example.lucer_000.redrider.Data.Profile;
import com.example.lucer_000.redrider.Data.Rider;
import com.example.lucer_000.redrider.Data.PostRepository;
import com.example.lucer_000.redrider.util.HttpUtils;
import com.example.lucer_000.redrider.util.Volleycallback;

import org.json.JSONException;
import org.json.JSONObject;

public class PostPresenter implements PostContract.Presenter {



    @NonNull
    private final PostContract.View mPostView;
    Context context;
    PostRepository postRepository;
    Profile userProfile;

    public PostPresenter(PostRepository postRepository,@NonNull PostContract.View postView, Context context){
        // mMatchView = checkNotNull(matchView, "matchView cannot be null!");
        mPostView = postView;
        this.context = context;
        this.postRepository = postRepository;
        mPostView.setPresenter(this);
        userProfile = postRepository.getProfile();
    }

    @Override
    public void start() {

    }

    @Override
    public void submitNewPost(String dest,String date, String time){
        //public Rider(String date, String destination, int riderId, String time)

        int profileId = userProfile.getIdProfile();

        Rider riderPost = new Rider(dest,date, profileId, time);

        System.out.println("inside rider submit rider post");


        JSONObject jsonBody;
        try{
            jsonBody = new JSONObject();
            jsonBody.put("destination",dest);
            jsonBody.put("date", date);
            jsonBody.put("time", time);
            jsonBody.put("riderid", profileId);

            HttpUtils.getInstance(context).makePost(jsonBody,"riderpost", new Volleycallback() {
                @Override
                public void onSuccess(JSONObject response) {

                    try{
                        System.out.println("request success: " + response.get("success"));
                        if(response.get("success").toString().equals("true")){

                            System.out.println("postId: " + response.get("postId"));
                            String postId = response.get("postId").toString();

                            postRepository.savePost(postId,riderPost);


                            mPostView.submitPostSuccess();
                        }else{
                            System.out.println("rider post not success");
                        }



                    }catch(JSONException e){
                        e.printStackTrace();
                    }

                    //return null;
                }
            });

        }catch(JSONException e){
            e.printStackTrace();
        }

        mPostView.submitPostSuccess();
        //come back to this later and create a Post class with a user post list
    }
    @Override
    public void submitNewPost(String dest, String date, String seats, String time, String vehicle){



        int profileId = userProfile.getIdProfile();

        //String dest, String date, int driverId, int seats, String time, String vehicle
        Driver driverPost = new Driver(dest,date,profileId,Integer.parseInt(seats),time,vehicle);


        JSONObject jsonBody;
        try{
            jsonBody = new JSONObject();
            jsonBody.put("destination",dest);
            jsonBody.put("date", date);
            jsonBody.put("seats", seats);
            jsonBody.put("time", time);
            jsonBody.put("vehicle", vehicle);
            jsonBody.put("driverid", profileId);

            HttpUtils.getInstance(context).makePost(jsonBody,"driverpost", new Volleycallback() {
                @Override
                public void onSuccess(JSONObject response) {

                    try{
                        System.out.println("request success: " + response.get("success"));
                        if(response.get("success").toString().equals("true")){

                            System.out.println("postId: " + response.get("postId"));
                            String postId = response.get("postId").toString();


                            postRepository.savePost(postId,driverPost);
                            mPostView.submitPostSuccess();

                        }else{
                            System.out.println("driver post not success");
                        }



                    }catch(JSONException e){
                        e.printStackTrace();
                    }

                    //return null;
                }
            });

        }catch(JSONException e){
            e.printStackTrace();
        }






        //come back to this later and create a Post class with a user post list
    }

    @Override
    public void setSubmitBtn(){
        mPostView.setSubmitBtn();
    }
}

