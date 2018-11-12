package com.example.lucer_000.redrider.Register;

import com.example.lucer_000.redrider.BasePresenter;
import com.example.lucer_000.redrider.BaseView;

public interface RegisterContract {
    interface View extends BaseView<RegisterContract.Presenter> {
        void signUpSuccess();
    }
    interface Presenter extends BasePresenter {
        void signUp(String email, String pass);
    }
}
