package com.example.lucer_000.redrider.Profile;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.lucer_000.redrider.Data.PostRepository;
import com.example.lucer_000.redrider.Data.Profile;
import com.example.lucer_000.redrider.util.HttpUtils;
import com.example.lucer_000.redrider.util.Volleycallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLOutput;

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
        System.out.println("name in presenter: " + test.getName());
        System.out.println(test.getMajor());
        return test;
    }

    public void updateProfile(String name, String major, String sex, Integer age){
        JSONObject jsonBody;
        Profile test = postRepository.getProfile();
        try{
            jsonBody = new JSONObject();
            jsonBody.put("profileId", test.getIdProfile());
            jsonBody.put("major", major);
            jsonBody.put("sex", sex);
            jsonBody.put("age", age);
            jsonBody.put("name", name);

            HttpUtils.getInstance(context).makePost(jsonBody, "updateprofile", new Volleycallback() {
                @Override
                public void onSuccess(JSONObject response) {
                    System.out.println("made it");
                    try{

                         if(response.get("success").toString().equals("true")) {

                             test.setName(name);
                             test.setSex(sex);
                             test.setMajor(major);
                             test.setAge(age);


                             mProfileView.updateSuccess();
                         }else{
                             System.out.println("Profile not updated");
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
    }

}
