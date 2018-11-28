package com.example.lucer_000.redrider.MatchPage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import android.widget.Button;
import android.widget.TextView;

import com.example.lucer_000.redrider.Data.Driver;
import com.example.lucer_000.redrider.Data.MatchPost;
import com.example.lucer_000.redrider.Data.Post;
import com.example.lucer_000.redrider.Data.Profile;
import com.example.lucer_000.redrider.Data.Rider;
import com.example.lucer_000.redrider.Post.PostActivity;
import com.example.lucer_000.redrider.R;

import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class MatchFragment extends Fragment implements  MatchContract.View {

    private MatchContract.Presenter mPresenter;



    View root;
    ListView listView;
    private ArrayAdapter adapter;
    TextView postView;
    Button button;
    TextView emailView;
    private Toolbar toolbar;


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

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         root = inflater.inflate(R.layout.match_frag, container, false);

         mPresenter.test();


        toolbar = root.findViewById(R.id.appbar);
        ((AppCompatActivity)Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);
        getActivity().setTitle("Red Rider");

        // Set up floating action button
        FloatingActionButton fab =
                getActivity().findViewById(R.id.floatingActionButton);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.addPost();
            }
        });

        // Set up tasks view
        listView = root.findViewById(R.id.post_list);

//        button = root.findViewById(R.id.requestbutton);
//
//
//
//        //emailView.setText("hi");
//        button.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                mPresenter.makeRequest();
//            }
//        });


        //postView = root.findViewById(R.id.postdest);


        return root;
    }


    @Override
    public void showAddPost(){
        Intent intent = new Intent(getContext(), PostActivity.class);
        startActivityForResult(intent, PostActivity.REQUEST_ADD_POST);
    }



    @Override
    public void showPost(List<Post> postArray){

        adapter = new PostAdapter(getContext(),postArray,mPresenter);
        listView.setAdapter(adapter);

    }

    private static class PostAdapter extends ArrayAdapter<Post>{

        private List<Post> mPosts = new ArrayList<>();
        private Context context;
        private List<Post> postList = new ArrayList<>();
        private MatchContract.Presenter mPresenter;
        public PostAdapter(Context context, List<Post> posts, MatchContract.Presenter mPresenter) {
            super(context, 0, posts);
            this.context = context;
            mPosts = posts;
            this.mPresenter = mPresenter;

        }

        @Override
        public Post getItem(int i) {
            return mPosts.get(i);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            System.out.print("hiiiiii");
            View listItem = convertView;

            // Get the data item for this position
            Post currentPost = getItem(position);

            if(currentPost instanceof Driver){
                if (listItem == null) {
                    listItem = LayoutInflater.from(context).inflate(R.layout.driverpost, parent, false);
                }

                System.out.println("IS DRIVER");

                TextView dest = (TextView) listItem.findViewById(R.id.postDestDriver);
                TextView date= (TextView) listItem.findViewById(R.id.postDateDriver);
                TextView vehicle = listItem.findViewById(R.id.postVehicleDriver);
                TextView seats = listItem.findViewById(R.id.postSeatsDriver);
                TextView time = listItem.findViewById(R.id.postTimeDriver);
                TextView postDTitle = listItem.findViewById(R.id.driverPst);
                postDTitle.setVisibility(View.VISIBLE);

                System.out.println("\n\ncurrentPost.getDestination(): " + currentPost.getDestination());

                dest.setText("Destination: " + currentPost.getDestination());

                date.setText("Date: " + currentPost.getDate());

                time.setText("Time: " + ((Driver) currentPost).getTime());

                vehicle.setText("Vehicle: " + ((Driver) currentPost).getVehicle());
                int seatInt = ((Driver) currentPost).getSeats();

                seats.setText("Seats available: " + Integer.toString(seatInt));


            }else if(currentPost instanceof Rider){
                // Check if an existing view is being reused, otherwise inflate the view
                if (listItem == null) {
                    listItem = LayoutInflater.from(context).inflate(R.layout.post, parent, false);
                }

                System.out.println("IS RIDER");

                TextView dest = (TextView) listItem.findViewById(R.id.postDest);
                TextView date= (TextView) listItem.findViewById(R.id.postDate);
                TextView time= (TextView) listItem.findViewById(R.id.postTime);
                TextView postRTitle = listItem.findViewById(R.id.riderPst);
                postRTitle.setVisibility(View.VISIBLE);

                dest.setText("Destination: " + currentPost.getDestination());
                date.setText("Date: " + currentPost.getDate());
                time.setText("Time: " + ((Rider) currentPost).getTime());
            }else if(currentPost instanceof MatchPost){
                if (listItem == null) {
                    listItem = LayoutInflater.from(context).inflate(R.layout.matchpost, parent, false);
                }

                System.out.println("IS RIDER");

                TextView dest = (TextView) listItem.findViewById(R.id.postDestMatch);
                TextView date= (TextView) listItem.findViewById(R.id.postDateMatch);
                TextView name= (TextView) listItem.findViewById(R.id.postNameMatch);
                TextView major= (TextView) listItem.findViewById(R.id.postMajorMatch);
                TextView postMTitle = listItem.findViewById(R.id.matchPst);
                postMTitle.setVisibility(View.VISIBLE);
                dest.setText("Destination: " + currentPost.getDestination());
                date.setText("Date: " + currentPost.getDate());

                name.setText("Name: " + ((MatchPost) currentPost).getProfile().getName());
                major.setText("Major: " + ((MatchPost) currentPost).getProfile().getMajor());


                ImageView acceptBtn = listItem.findViewById(R.id.acceptBtn);
                ImageView declineBtn = listItem.findViewById(R.id.declineBtn);

                int tripID = ((MatchPost) currentPost).getTripID();
                int userID = ((MatchPost) currentPost).getProfile().getIdProfile();

                TextView acceptText = listItem.findViewById(R.id.acceptText);

                acceptBtn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){

                        acceptBtn.setVisibility(View.GONE);
                        declineBtn.setVisibility(View.GONE);
                        acceptText.setVisibility(View.VISIBLE);
                        Toast.makeText(getContext(), "Match Accepted", Toast.LENGTH_SHORT).show();

                        mPresenter.acceptMatch(true,tripID,userID);


                    }
                });

                declineBtn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){

                        acceptBtn.setVisibility(View.GONE);
                        declineBtn.setVisibility(View.GONE);
                        acceptText.setVisibility(View.VISIBLE);
                        acceptText.setText("Match Declined!");

                        mPresenter.acceptMatch(false,tripID,userID);
                    }
                });





            }

            // Return the completed view to render on screen
            return listItem;
        }//end getView



    }//end adapter class


}
