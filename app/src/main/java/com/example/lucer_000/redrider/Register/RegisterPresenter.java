package com.example.lucer_000.redrider.Register;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.example.lucer_000.redrider.Data.PostRepository;
import com.example.lucer_000.redrider.Data.Profile;
import com.example.lucer_000.redrider.Profile.ProfileActivity;
import com.example.lucer_000.redrider.util.HttpUtils;
import com.example.lucer_000.redrider.util.Volleycallback;

import org.json.JSONException;
import org.json.JSONObject;

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
    public void signUp(String email, String pass){
        JSONObject jsonBody;
        try{
            jsonBody = new JSONObject();
            jsonBody.put("email",email);
            jsonBody.put("password", pass);

            HttpUtils.getInstance(context).makePost(jsonBody, "signup", new Volleycallback() {
                @Override
                public void onSuccess(JSONObject response) {
                    System.out.println("made it");
                    try{
                        Profile guy = new Profile();
                        System.out.println(response.get("success"));
                        if(response.get("success").toString().equals("true")) {
                            guy.setIdProfile(response.getInt("profileId"));

                            System.out.println("name in http call response: " + guy.getName());
                            mPostRepository.setProfile(guy);
                            mRegisterView.signUpSuccess();

                        } else{
                            System.out.println("Profile not created");
                        }

                    }catch(JSONException e){
                        e.printStackTrace();
                    }
                }
            });

        }catch(JSONException e){
            e.printStackTrace();
        }
    }



}
