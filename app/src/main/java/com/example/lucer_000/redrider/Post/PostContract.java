package com.example.lucer_000.redrider.Post;

import com.example.lucer_000.redrider.BasePresenter;
import com.example.lucer_000.redrider.BaseView;

public interface PostContract {

    interface View extends BaseView<Presenter>{

        void submitPostSuccess();
        void setSubmitBtn();
    }
    interface Presenter extends BasePresenter{

        void setSubmitBtn();
        //destination.getText().toString(),date.getText().toString(), time.getText().toString());
        void submitNewPost(String dest,String date, String time);


        //destination.getText().toString(),date.getText().toString(),seats.getText().toString(),time.getText().toString(),vehicle.getText().toString()
        void submitNewPost(String dest, String date, String seats, String time, String vehicle);
    }
}
