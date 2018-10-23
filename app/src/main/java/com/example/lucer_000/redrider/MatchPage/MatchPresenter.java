package com.example.lucer_000.redrider.MatchPage;

import android.app.Activity;
import android.support.annotation.NonNull;
import com.example.lucer_000.redrider.Data.Injection;
import com.example.lucer_000.redrider.Data.PostRepository;
import com.example.lucer_000.redrider.Data.Post;

import java.util.ArrayList;
import java.util.List;


public class MatchPresenter implements MatchContract.Presenter {

    //initialize database class

    private final MatchContract.View mMatchView;

    private PostRepository postRepository;

    public MatchPresenter(PostRepository postRepository,@NonNull MatchContract.View matchView){
       // mMatchView = checkNotNull(matchView, "matchView cannot be null!");
        mMatchView = matchView;
        this.postRepository = postRepository;
        mMatchView.setPresenter(this);
    }
    @Override
    public void start() {
        loadPosts();
    }


//    public void result(int requestCode, int resultCode) {
////        // If a task was successfully added, show snackbar
////        if (AddEditTaskActivity.REQUEST_ADD_TASK == requestCode && Activity.RESULT_OK == resultCode) {
////            mTasksView.showSuccessfullySavedMessage();
////        }
//    }



    public String getValue(){
        return "Shoe";
    }

    @Override
    public void addPost(){
        mMatchView.showAddPost();
    }

    public void loadPosts(){
        List<Post> postsToShow = new ArrayList<Post>();

        postsToShow = postRepository.getPosts();



        processPosts(postsToShow);


    }

    public void processPosts(List<Post> postList){
        if(postList.isEmpty()){
            processNoPosts();
        }else{
            Post[] postArray = postList.toArray(new Post[0]);

            List<String> destArray = new ArrayList<String>();

            for(int i = 0; i<postList.size(); i++){
                destArray.add(postList.get(i).getDestination());
            }

            String[] arr = {"hi", "there", "fame"};
            mMatchView.showPost(destArray.toArray(new String[destArray.size()]));
            //mMatchView.showPost(arr);
        }
    }

    public void processNoPosts(){

    }



}
