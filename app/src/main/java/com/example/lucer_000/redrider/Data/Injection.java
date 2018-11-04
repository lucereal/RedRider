package com.example.lucer_000.redrider.Data;


import android.content.Context;



public class Injection {

    public static PostRepository provideTasksRepository(Context context) {

        return PostRepository.getInstance();
    }
}
