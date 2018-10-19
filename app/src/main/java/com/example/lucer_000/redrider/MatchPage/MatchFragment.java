package com.example.lucer_000.redrider.MatchPage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import android.widget.Button;
import android.widget.TextView;

import com.example.lucer_000.redrider.Post.PostActivity;
import com.example.lucer_000.redrider.R;

import android.support.annotation.Nullable;


public class MatchFragment extends Fragment implements  MatchContract.View {

    private MatchContract.Presenter mPresenter;

    //initialize main view here

    //intialize all views here
    //ex private TextView mTaskView
    Button button;



    public MatchFragment(){}
        //empty constructor

    public static MatchFragment newInstance(){
        return new MatchFragment();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);



    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(MatchContract.Presenter presenter) {
        mPresenter = presenter;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //mPresenter.result(requestCode, resultCode);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.match_frag, container, false);




        // Set up floating action button
        FloatingActionButton fab =
                getActivity().findViewById(R.id.floatingActionButton);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.addPost();
            }
        });




        return root;
    }

    @Override
    public void showAddPost(){
        Intent intent = new Intent(getContext(), PostActivity.class);
        startActivity(intent);
    }

}
