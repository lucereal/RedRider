package com.example.lucer_000.redrider.util;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.lucer_000.redrider.Data.Profile;
import com.example.lucer_000.redrider.util.Volleycallback;
import org.json.JSONException;
import org.json.JSONObject;


public class HttpUtils {

//    private static HttpUtils INSTANCE = null;

    public static HttpUtils myInstance;
    private static Context context;
    private String URL ="http://10.161.121.245:3000/login";

    public  RequestQueue queue;


    public HttpUtils(Context context){

        this.context = context;
        queue = getRequestQueue();
    }


    public static synchronized HttpUtils getInstance(Context context) {
        if (myInstance == null) {
            myInstance = new HttpUtils(context);
        }
        return myInstance;
    }

    public RequestQueue getRequestQueue() {
        if (queue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            queue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return queue;
    }

//    public void addToRequestQueue(JSONObject req) {
//        getRequestQueue().add(req);
//    }


    public void makePost(JSONObject jsonBody, final Volleycallback callback){

        System.out.println("1");
        JsonObjectRequest jsonObject = new JsonObjectRequest(Request.Method.POST, URL, jsonBody, new Response.Listener<JSONObject>() {

            @Override
        public void onResponse(JSONObject response) {
                System.out.println("2");
                callback.onSuccess(response);

        }


        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: Handle error

                error.printStackTrace();

            }
        });

        System.out.println("3");
        queue.add(jsonObject);

    }

    public void login(String email, String password){
        JSONObject jsonBody;
        try{
            jsonBody = new JSONObject();
            jsonBody.put("email","cade.wall@ttu.edu");
            jsonBody.put("password", "password");

            HttpUtils.getInstance(context).makePost(jsonBody, new Volleycallback() {
                @Override
                public void onSuccess(JSONObject response) {
                    System.out.println("made it");
                    try{
                        System.out.println("success: " + response.get("success"));
                        JSONObject user = response.getJSONObject("user");
                        Profile returnguy = new Profile();
                        returnguy.setEmail(user.get("Email").toString());
                        //returnguy.setAge(user.get("Age").toString());
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

    }







}
