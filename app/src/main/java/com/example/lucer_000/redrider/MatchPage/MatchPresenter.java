package com.example.lucer_000.redrider.MatchPage;

import android.app.Activity;
import android.support.annotation.NonNull;




public class MatchPresenter implements MatchContract.Presenter {

    //initialize database class

    private final MatchContract.View mMatchView;


    public MatchPresenter(@NonNull MatchContract.View matchView){
       // mMatchView = checkNotNull(matchView, "matchView cannot be null!");
        mMatchView = matchView;
        mMatchView.setPresenter(this);
    }
    @Override
    public void start() {
        //loadTasks(false);
    }


    public void result(int requestCode, int resultCode) {
//        // If a task was successfully added, show snackbar
//        if (AddEditTaskActivity.REQUEST_ADD_TASK == requestCode && Activity.RESULT_OK == resultCode) {
//            mTasksView.showSuccessfullySavedMessage();
//        }
    }
}
