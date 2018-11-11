package com.example.lucer_000.redrider.Login;

import com.example.lucer_000.redrider.BaseView;
import com.example.lucer_000.redrider.BasePresenter;
import com.example.lucer_000.redrider.Data.Profile;


public interface LoginContract {

    interface View extends BaseView<Presenter>{

        void openRegister();
        void signInSuccess();
    }
    interface Presenter extends BasePresenter{
        void signIn(String email, String password);

    }
}
