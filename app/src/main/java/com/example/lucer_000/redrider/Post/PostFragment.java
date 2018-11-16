package com.example.lucer_000.redrider.Post;

import android.app.Activity;
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
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
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

import com.example.lucer_000.redrider.MatchPage.MatchActivity;
import com.example.lucer_000.redrider.R;

import java.util.Objects;

public class PostFragment extends Fragment implements PostContract.View{

    PostContract.Presenter mPresenter;
    View root;
    private TextView destination;
    private TextView date;
    private TextView vehicle;
    private TextView seats;
    private TextView time;

    private Toolbar toolbar;

    private boolean driver;
    Button riderBtn;
    Button driverBtn;
    private ImageView backBtn;
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

        toolbar = root.findViewById(R.id.appbar);
        ((AppCompatActivity)Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);
        getActivity().setTitle("Red Rider");

        backBtn = root.findViewById(R.id.backBtn);
        backBtn.setVisibility(View.VISIBLE);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(root.getContext(), MatchActivity.class);
                startActivity(intent);
            }
        });


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

        return root;
    }


    @Override
    public void submitPostSuccess(){
//        riderBtn.setVisibility(View.GONE);
//        driverBtn.setVisibility(View.GONE);
//        Intent intent = new Intent(getContext(), MatchActivity.class);
//        startActivity(intent);

        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();


    }


    @Override
    public void setSubmitBtn(){
        //JSONObject jsonObj = new JSONObject();

        Button submitBtn = root.findViewById(R.id.submit);
        submitBtn.setVisibility(View.VISIBLE);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(driver){
                    destination = root.findViewById(R.id.destdriver);
                    date = root.findViewById(R.id.datedriver);
                    time = root.findViewById(R.id.timedriver);
                    seats = root.findViewById(R.id.seatsdriver);
                    vehicle = root.findViewById(R.id.vehicledriver);
                    System.out.println("destination: "+ destination.getText().toString());
                    System.out.println("seats: "+ seats.getText());
                    System.out.println("date: "+ date.getText());
                    //String dest,String date, String riderId, String time
                    mPresenter.submitNewPost(destination.getText().toString(),date.getText().toString(),seats.getText().toString(),time.getText().toString(),vehicle.getText().toString());
                }else{
                    destination = root.findViewById(R.id.dest);
                    date = root.findViewById(R.id.date);
                    time = root.findViewById(R.id.time);
                    System.out.println("destination: "+ destination.getText().toString());
                    System.out.println("date: "+ date.getText());
                    //String dest, String date, String driverId, String seats, String time, String vehicle
                    mPresenter.submitNewPost(destination.getText().toString(),date.getText().toString(), time.getText().toString());
                }



            }
        });

    }
}
