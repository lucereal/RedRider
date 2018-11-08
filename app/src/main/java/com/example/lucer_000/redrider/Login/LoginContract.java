package com.example.lucer_000.redrider.Login;

import com.example.lucer_000.redrider.BaseView;
import com.example.lucer_000.redrider.BasePresenter;


public interface LoginContract {

    interface View extends BaseView<Presenter>{

        void openRegister();
    }
    interface Presenter extends BasePresenter{

    }
}
