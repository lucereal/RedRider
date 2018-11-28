package com.example.lucer_000.redrider.MatchPage;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.content.Context;
import com.example.lucer_000.redrider.Data.Driver;
import com.example.lucer_000.redrider.Data.Injection;
import com.example.lucer_000.redrider.Data.PostRepository;
import com.example.lucer_000.redrider.Data.Post;
import com.example.lucer_000.redrider.Data.Profile;
import java.util.ArrayList;
import java.util.List;

import com.example.lucer_000.redrider.util.ActivityUtils;
import com.example.lucer_000.redrider.util.GetPostCallback;
import com.example.lucer_000.redrider.util.HttpUtils;
import com.example.lucer_000.redrider.util.Volleycallback;


import org.json.JSONException;
import org.json.JSONObject;

public class MatchPresenter implements MatchContract.Presenter {

    //initialize database class

    Context context;
    private final MatchContract.View mMatchView;

    private PostRepository postRepository;

    public MatchPresenter(PostRepository postRepository,@NonNull MatchContract.View matchView, Context context){
       // mMatchView = checkNotNull(matchView, "matchView cannot be null!");
        this.context = context;
        mMatchView = matchView;
        this.postRepository = postRepository;
        mMatchView.setPresenter(this);
    }
    @Override
    public void start() {
        loadPosts();
    }


//    public void result(int requestCode, int resultCode) {
////        // If a task was successfully added, show snackbar
////        if (AddEditTaskActivity.REQUEST_ADD_TASK == requestCode && Activity.RESULT_OK == resultCode) {
////            mTasksView.showSuccessfullySavedMessage();
////        }
//    }



    public String getValue(){
        return "Shoe";
    }

    @Override
    public void addPost(){
        mMatchView.showAddPost();
    }

    public void loadPosts(){

        postRepository.getPosts(context, new GetPostCallback() {
            @Override
            public void onSuccess(ArrayList list) {
                List<Post> postsToShow = new ArrayList<Post>();
                postsToShow = list;
                processPosts(postsToShow);
            }
        });




    }

    public void processPosts(List<Post> postList){
        if(postList.isEmpty()){
            processNoPosts();
        }else{

            System.out.println("\n\npostlist size: " + postList.size());
            mMatchView.showPost(postList);
        }
    }

    public void processNoPosts(){

    }

    public void test(){
        Profile test = postRepository.getProfile();
        System.out.println("test");
        System.out.println(test.getName());
    }



    public void acceptMatch(boolean accepted, int tripID, int userID){
        String acceptedStr;
        if(accepted){
            acceptedStr = "accepted";

        }else{
            acceptedStr = "declined";
        }

        JSONObject jsonBody;
        try{
            jsonBody = new JSONObject();
            jsonBody.put("response", acceptedStr);
            jsonBody.put("userID", userID);
            jsonBody.put("tripID", tripID);

            HttpUtils.getInstance(context).makePost(jsonBody,"matchresponse", new Volleycallback() {
                @Override
                public void onSuccess(JSONObject response) {
                    System.out.println("made it");
                    try{
                        System.out.println("success: " + response.get("success"));

//                        if(accepted){
//                            mMatchView.showMatchAccepted(true);
//                        }else{
//                            mMatchView.showMatchAccepted(false);
//                        }




                    }catch(JSONException e){
                        e.printStackTrace();
                    }

                    //return null;
                }
            });

        }catch(JSONException e){
            e.printStackTrace();
        }
    }
    public void makeRequest(){


        JSONObject jsonBody;
        try{
            jsonBody = new JSONObject();
            jsonBody.put("email","cade.wall@ttu.edu");
            jsonBody.put("password", "password");

            HttpUtils.getInstance(context).makePost(jsonBody,"login", new Volleycallback() {
                @Override
                public void onSuccess(JSONObject response) {
                    System.out.println("made it");
                    try{
                        System.out.println("success: " + response.get("success"));
                        JSONObject user = response.getJSONObject("user");
                        Profile returnguy = new Profile();
                        returnguy.setEmail(user.get("Email").toString());
                        System.out.println(user.get("Password"));



                    }catch(JSONException e){
                        e.printStackTrace();
                    }

                    //return null;
                }
            });

        }catch(JSONException e){
            e.printStackTrace();
        }




    }//end makeRequest



}
