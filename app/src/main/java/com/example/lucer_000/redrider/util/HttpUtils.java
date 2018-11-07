package com.example.lucer_000.redrider.util;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.lucer_000.redrider.util.Volleycallback;
import org.json.JSONException;
import org.json.JSONObject;


public class HttpUtils {

    private Context context;
    private JSONObject jsonBody;
    String URL ="http://10.201.14.137:3000/login";

    // Instantiate the RequestQueue.
    RequestQueue queue;

    public HttpUtils(Context context){

        this.context = context;
        queue = Volley.newRequestQueue(context);
    }


    public void makePost(JSONObject jsonBody, final Volleycallback callback){


        JsonObjectRequest jsonObject = new JsonObjectRequest(Request.Method.POST, URL, jsonBody, new Response.Listener<JSONObject>() {

            @Override
        public void onResponse(JSONObject response) {
                callback.onSuccess(response);

        }


        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: Handle error

                error.printStackTrace();

            }
        });

        queue.add(jsonObject);

    }
















}
