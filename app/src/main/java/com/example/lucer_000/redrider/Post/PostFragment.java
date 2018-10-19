package com.example.lucer_000.redrider.Post;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.Button;

import com.example.lucer_000.redrider.R;

public class PostFragment extends Fragment implements PostContract.View{

    PostContract.Presenter mPresenter;


    public PostFragment(){}
    //empty constructor

    public static PostFragment newInstance(){
        return new PostFragment();
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
    public void setPresenter(PostContract.Presenter presenter) {
        mPresenter = presenter;
    }




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.post_frag, container, false);





        Button riderBtn = root.findViewById(R.id.rider);
        Button driverBtn = root.findViewById(R.id.driver);
//        layoutParams = new LinearLayout.LayoutParams
//                (LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        riderBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                root.findViewById(R.id.includedLayout2).setVisibility(View.GONE);
                root.findViewById(R.id.includedLayout).setVisibility(View.VISIBLE);
            }
        });

        driverBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                root.findViewById(R.id.includedLayout).setVisibility(View.GONE);
                root.findViewById(R.id.includedLayout2).setVisibility(View.VISIBLE);
            }
        });

//        ToggleButton riderBtn = root.findViewById(R.id.rider);
//        ToggleButton driverBtn = root.findViewById(R.id.driver);

//        riderBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(isChecked){
//
//                    View inputs = inflater.inflate(R.layout.input_rider,container,true);
//                    //mPresenter.setRiderPostView();z
//                }
//            }
//        });

//        driverBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(isChecked){
//                    View input = inflater.inflate(R.layout.post_input,container,true);
//                    //mPresenter.setDriverPostView();
//                }
//            }
//        });








        return root;
    }

    @Override
    public void setRiderView(){

    }

    @Override
    public void setDriverView(){

    }

}
