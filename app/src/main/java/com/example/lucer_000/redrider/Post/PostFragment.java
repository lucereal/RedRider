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
import org.json.JSONObject;


import com.example.lucer_000.redrider.R;

public class PostFragment extends Fragment implements PostContract.View{

    PostContract.Presenter mPresenter;
    View root;
    private TextView destination;
    private TextView date;
    private TextView compensation;
    private TextView numSeats;
    private boolean driver;
    Button riderBtn;
    Button driverBtn;
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
         root = inflater.inflate(R.layout.post_frag, container, false);





        riderBtn = root.findViewById(R.id.rider);
        driverBtn = root.findViewById(R.id.driver);


        riderBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                root.findViewById(R.id.includedLayout2).setVisibility(View.GONE);
                root.findViewById(R.id.includedLayout).setVisibility(View.VISIBLE);
                //root.findViewById(R.id.submit).setVisibility(View.VISIBLE);
                driver = false;
                mPresenter.setSubmitBtn();

            }
        });

        driverBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                root.findViewById(R.id.includedLayout).setVisibility(View.GONE);
                root.findViewById(R.id.includedLayout2).setVisibility(View.VISIBLE);
                driver = true;
                mPresenter.setSubmitBtn();
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
//                    View input = inflater.inflate(R.layout.input_driver,container,true);
//                    //mPresenter.setDriverPostView();
//                }
//            }
//        });








        return root;
    }


    @Override
    public void submitPostSuccess(String type){
//        riderBtn.setVisibility(View.GONE);
//        driverBtn.setVisibility(View.GONE);
        root.findViewById(R.id.includedLayout2).setVisibility(View.GONE);
        root.findViewById(R.id.includedLayout).setVisibility(View.GONE);
        root.findViewById(R.id.submit).setVisibility(View.GONE);
        if(type.equals("driver")){
            root.findViewById(R.id.driversuccess).setVisibility(View.VISIBLE);
        }else{
            root.findViewById(R.id.ridersuccess).setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void setSubmitBtn(){
        //JSONObject jsonObj = new JSONObject();

        Button submitBtn = root.findViewById(R.id.submit);
        submitBtn.setVisibility(View.VISIBLE);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                destination = root.findViewById(R.id.dest);
                date = root.findViewById(R.id.date);
                if(driver){
                    compensation = root.findViewById(R.id.compensation);
                    numSeats = root.findViewById(R.id.numSeats);
                    mPresenter.submitNewPost(destination.getText().toString(),date.getText().toString(),compensation.getText().toString(),numSeats.getText().toString());
                }else{
                    mPresenter.submitNewPost(destination.getText().toString(),date.getText().toString());
                }



            }
        });

    }
}
