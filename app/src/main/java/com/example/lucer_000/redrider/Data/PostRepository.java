package com.example.lucer_000.redrider.Data;



import android.content.Context;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import com.example.lucer_000.redrider.util.GetPostCallback;
import com.example.lucer_000.redrider.util.HttpUtils;
import com.example.lucer_000.redrider.util.Volleycallback;

import org.json.*;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;

public class PostRepository {

    private static PostRepository INSTANCE = null;


    private Profile mProfile;
    private Map<String, Post> mCachedPosts;


    public static PostRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PostRepository();

        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    public void savePost(String postId,Post post) {
        System.out.println("\n\npost: " + post.getDestination());

        // Do in memory cache update to keep the app UI up to date
        if (mCachedPosts == null) {
            mCachedPosts = new LinkedHashMap<>();
        }

        if(post instanceof Driver){
            String key = "driver+"+postId;
            mCachedPosts.put(key,post);
        }else if(post instanceof Rider){
            String key = "rider+"+postId;
            mCachedPosts.put(key, post);
        }else{
            String key = "match+"+postId;
            mCachedPosts.put(key,post);
        }



    }

    public void getPosts(Context context, final GetPostCallback callback){

        int profileID = mProfile.getIdProfile();
        JSONObject jsonBody;
        try{
            jsonBody = new JSONObject();
            jsonBody.put(  "userId",profileID);

            HttpUtils.getInstance(context).makePost(jsonBody,"getposts", new Volleycallback() {
                @Override
                public void onSuccess(JSONObject response) {

                    try{
                        System.out.println("request success: " + response.get("success"));
                        if(response.get("success").toString().equals("true")){

                            JSONArray riderArray = response.getJSONArray("riderposts");
                            JSONArray driverArray = response.getJSONArray("driverposts");



                            System.out.println("riderArray: " + riderArray.length());
                            System.out.println("driverArray: " + driverArray.length());


                            for(int i = 0; i<riderArray.length(); i++){
                                Rider temprider = new Rider();
                                JSONObject jsonObj = new JSONObject();
                                jsonObj = riderArray.getJSONObject(i);

                                temprider.setRiderId(jsonObj.getInt("RiderID"));
                                temprider.setDate(jsonObj.get("Date").toString());
                                temprider.setDestination(jsonObj.get("DestinationID").toString());
                                temprider.setTime(jsonObj.get("Time").toString());
                                String postId = jsonObj.get("postID").toString();
                                savePost(postId,temprider);
                            }

                            for(int i = 0; i<driverArray.length(); i++){
                                Driver tempdriver = new Driver();
                                JSONObject jsonObj = new JSONObject();
                                jsonObj = driverArray.getJSONObject(i);

                                tempdriver.setDriverId(jsonObj.getInt("DriverID"));
                                tempdriver.setSeats(jsonObj.getInt("Seats"));
                                tempdriver.setTime(jsonObj.get("Time").toString());
                                tempdriver.setVehicle(jsonObj.get("Vehicle").toString());
                                tempdriver.setDate(jsonObj.get("Date").toString());
                                tempdriver.setDestination(jsonObj.get("DestinationID").toString());
                                String postId = jsonObj.get("TripId").toString();
                                savePost(postId,tempdriver);
                            }


                            JSONObject jsonBodyB;
                            try{
                                jsonBodyB = new JSONObject();
                                jsonBodyB.put(  "userId", profileID);

                                HttpUtils.getInstance(context).makePost(jsonBody,"getmatches", new Volleycallback() {
                                    @Override
                                    public void onSuccess(JSONObject response) {

                                        try{
                                            System.out.println("request success: " + response.get("success"));
                                            if(response.get("success").toString().equals("true")){


                                                JSONArray matchArray = response.getJSONArray("matchArray");


                                                for(int i = 0; i<matchArray.length(); i++){
                                                    MatchPost tempMatchPost = new MatchPost();
                                                    JSONObject jsonObjParent = new JSONObject();
                                                    jsonObjParent = matchArray.getJSONObject(i);
                                                    JSONObject match = new JSONObject();
                                                    JSONObject rider = new JSONObject();
                                                    JSONObject driver = new JSONObject();

                                                    match = jsonObjParent.getJSONObject("matchpost");
                                                    rider = jsonObjParent.getJSONObject("riderprofile");
                                                    driver = jsonObjParent.getJSONObject("driverprofile");
                                                    Profile profile = new Profile();
                                                    if(rider.getInt("idProfile") == (profileID)){

                                                        profile.setName(driver.get("Name").toString());
                                                        profile.setMajor(driver.get("Major").toString());
                                                        profile.setIdProfile(driver.getInt("idProfile"));
                                                        profile.setAge(driver.getInt("Age"));
                                                    }else{
                                                        profile.setName(rider.get("Name").toString());
                                                        profile.setMajor(rider.get("Major").toString());
                                                        profile.setIdProfile(rider.getInt("idProfile"));
                                                        profile.setAge(rider.getInt("Age"));
                                                    }

                                                    tempMatchPost.setProfile(profile);
                                                    tempMatchPost.setDate(match.get("Date").toString());
                                                    tempMatchPost.setDestination(match.get("DestinationID").toString());
                                                    String postId = match.get("TripID").toString();
                                                    tempMatchPost.setTripID(Integer.parseInt(postId));

                                                    savePost(postId,tempMatchPost);
                                                }



                                                ArrayList<Post> postList = new ArrayList<>();

                                                if (mCachedPosts != null ) {

                                                    postList = new ArrayList(mCachedPosts.values());
                                                }else{

                                                    postList = new ArrayList();
                                                }


                                                callback.onSuccess(postList);

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




                        }else{
                            System.out.println("rider post not success");
                            callback.onSuccess(new ArrayList());
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


    public Profile getProfile(){
        if(mProfile != null){
            return mProfile;
        }
        return new Profile();
    }

    public void setProfile(Profile profile){

        System.out.println("profile in repo: " + profile.getMajor());
        if(mProfile == null){
            mProfile = new Profile();
        }
            mProfile = profile;


    }

}
