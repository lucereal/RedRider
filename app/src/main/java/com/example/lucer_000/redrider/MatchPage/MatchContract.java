package com.example.lucer_000.redrider.MatchPage;
import com.example.lucer_000.redrider.BasePresenter;
import com.example.lucer_000.redrider.BaseView;

public interface MatchContract {

    interface View extends BaseView<Presenter>{

        void showAddPost();
    }

    interface Presenter extends BasePresenter{

        String getValue();

        void addPost();
    }
}
