package com.example.lucer_000.redrider.Post;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.lucer_000.redrider.MatchPage.MatchActivity;
import com.example.lucer_000.redrider.MatchPage.MatchFragment;
import com.example.lucer_000.redrider.R;
import com.example.lucer_000.redrider.util.ActivityUtils;
import com.example.lucer_000.redrider.Data.Injection;


public class PostActivity extends AppCompatActivity {

    public static final int REQUEST_ADD_POST = 1;
    PostPresenter mPostPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_act);


        PostFragment postFragment =
                (PostFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if(postFragment == null){
            //craete fragment
            postFragment = PostFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), postFragment, R.id.contentFrame);

        }
        mPostPresenter = new PostPresenter(Injection.provideTasksRepository(getApplicationContext()),
                postFragment, getApplicationContext());

        Button backbutton = findViewById(R.id.backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MatchActivity.class);
                startActivity(intent);
            }
        });


    }


}
