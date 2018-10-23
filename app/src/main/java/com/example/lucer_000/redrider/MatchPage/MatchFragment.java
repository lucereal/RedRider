package com.example.lucer_000.redrider.MatchPage;

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

import com.example.lucer_000.redrider.Data.DriverPost;
import com.example.lucer_000.redrider.Data.Post;
import com.example.lucer_000.redrider.Post.PostActivity;
import com.example.lucer_000.redrider.R;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class MatchFragment extends Fragment implements  MatchContract.View {

    private MatchContract.Presenter mPresenter;



    View root;
    ListView listView;
    ArrayAdapter<String> adapter;
    TextView postView;
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
         root = inflater.inflate(R.layout.match_frag, container, false);




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



        //postView = root.findViewById(R.id.postdest);


        return root;
    }

    @Override
    public void showAddPost(){
        Intent intent = new Intent(getContext(), PostActivity.class);
        startActivityForResult(intent, PostActivity.REQUEST_ADD_POST);
    }



    @Override
    public void showPost(String[] postArray){
        //root.findViewById(R.id.showpoststemp).setVisibility(View.VISIBLE);

        adapter = new ArrayAdapter<String>(getContext(),R.layout.post,postArray);
        listView.setAdapter(adapter);
    }

    private static class PostAdapter extends ArrayAdapter<Post>{

        private List<Post> mPosts;

        public PostAdapter(Context context, ArrayList<Post> posts) {
            super(context, 0, posts);
        }




        @Override
        public Post getItem(int i) {
            return mPosts.get(i);
        }

        private void setList(List<Post> tasks) {
            mPosts = tasks;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            // Get the data item for this position
            Post post = getItem(position);

            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.post, parent, false);
            }
            // Lookup view for data population
            TextView dest = (TextView) convertView.findViewById(R.id.postDest);
            TextView date= (TextView) convertView.findViewById(R.id.postDate);

            if(post instanceof DriverPost){

            }
            TextView comp = convertView.findViewById(R.id.postComp);
            TextView numSeats = convertView.findViewById(R.id.postSeats);
            // Populate the data into the template view using the data object
            dest.setText(post.getDestination());
            date.setText(post.getDate());


            // Return the completed view to render on screen
            return convertView;
        }



    }


}
