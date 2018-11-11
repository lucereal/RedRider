package com.example.lucer_000.redrider.Profile;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.lucer_000.redrider.Data.PostRepository;
import com.example.lucer_000.redrider.Data.Profile;
import com.example.lucer_000.redrider.util.HttpUtils;
import com.example.lucer_000.redrider.util.Volleycallback;

import org.json.JSONException;
import org.json.JSONObject;

public class ProfilePresenter implements ProfileContract.Presenter{

    Context context;
    private final ProfileContract.View mProfileView;
    private PostRepository postRepository;

    public ProfilePresenter(PostRepository postRepository, @NonNull ProfileContract.View profileView, Context context) {

        mProfileView = profileView;
        mProfileView.setPresenter(this);
        this.context = context;
        this.postRepository = postRepository;
    }

    @Override
    public void start() {
        //openTask();
    }
    public Profile getUserProfile(){
        Profile test = postRepository.getProfile();
        return test;
    }

    public void signUp(String email, String pass, String major, String sex, Integer age, String name){
        JSONObject jsonBody;
        try{
            jsonBody = new JSONObject();
            jsonBody.put("email",email);
            jsonBody.put("password", pass);
            jsonBody.put("major", major);
            jsonBody.put("sex", sex);
            jsonBody.put("age", age);
            jsonBody.put("name", name);

            HttpUtils.getInstance(context).makePost(jsonBody, new Volleycallback() {
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
                        guy.setSex(user.get("Sex").toString());
                        postRepository.setProfile(guy);
                        mProfileView.signUpSuccess();



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
