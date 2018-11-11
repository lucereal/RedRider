package com.example.lucer_000.redrider.Profile;

import com.example.lucer_000.redrider.BasePresenter;
import com.example.lucer_000.redrider.BaseView;
import com.example.lucer_000.redrider.Data.Profile;

public interface ProfileContract {

    interface View extends BaseView<Presenter> {
        void signUpSuccess();
    }
    interface Presenter extends BasePresenter {
        Profile getUserProfile();
        void signUp(String email, String pass, String major, String sex, Integer age, String name);
    }
}
