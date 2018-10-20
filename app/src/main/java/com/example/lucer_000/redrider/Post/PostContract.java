package com.example.lucer_000.redrider.Post;

import com.example.lucer_000.redrider.BasePresenter;
import com.example.lucer_000.redrider.BaseView;

public interface PostContract {

    interface View extends BaseView<Presenter>{

        void submitPostSuccess(String type);
        void setSubmitBtn();
    }
    interface Presenter extends BasePresenter{

        void setSubmitBtn();
        void submitNewPost(String dest,String date);
        void submitNewPost(String dest,String date,String comp,String numSeats);
    }
}
