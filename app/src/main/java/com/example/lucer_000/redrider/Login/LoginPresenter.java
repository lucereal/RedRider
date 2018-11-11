package com.example.lucer_000.redrider.Login;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.lucer_000.redrider.Data.PostRepository;
import com.example.lucer_000.redrider.Data.Profile;
import com.example.lucer_000.redrider.Register.RegisterActivity;
import com.example.lucer_000.redrider.util.HttpUtils;
import com.example.lucer_000.redrider.util.Volleycallback;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginPresenter implements LoginContract.Presenter{

    private final LoginContract.View mLoginView;
    Context context;
    PostRepository mPostRepository;
    public LoginPresenter(PostRepository postRepository, @NonNull LoginContract.View loginView, Context context) {

       // mLoginView = checkNotNull(taskDetailView, "taskDetailView cannot be null!");
        mLoginView = loginView;
        mLoginView.setPresenter(this);
        this.context = context;
        this.mPostRepository = postRepository;
    }

    @Override
    public void start() {
        //openTask();
    }

    public void signIn(String email, String password){
        JSONObject jsonBody;
        try{
            jsonBody = new JSONObject();
            jsonBody.put("email",email);
            jsonBody.put("password", password);

            HttpUtils.getInstance(context).makePost(jsonBody,"login", new Volleycallback() {
                @Override
                public void onSuccess(JSONObject response) {
                    System.out.println("made it");
                        try{
                            Profile guy = new Profile();

                        //guy.getSex();
                        System.out.println("success: " + response.get("success"));
                        JSONObject user = response.getJSONObject("user");
                            guy.setName(user.get("Name").toString());
                            guy.setAge((Integer) user.get("Age"));
                            guy.setMajor(user.get("Major").toString());
                        System.out.println(user.get("Password"));
                        mPostRepository.setProfile(guy);
                        mLoginView.signInSuccess();



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
}
