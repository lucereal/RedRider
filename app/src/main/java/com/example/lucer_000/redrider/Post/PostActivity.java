package com.example.lucer_000.redrider.Post;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.lucer_000.redrider.MatchPage.MatchFragment;
import com.example.lucer_000.redrider.R;
import com.example.lucer_000.redrider.util.ActivityUtils;

public class PostActivity extends AppCompatActivity {

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
        mPostPresenter = new PostPresenter(postFragment);


    }


}
