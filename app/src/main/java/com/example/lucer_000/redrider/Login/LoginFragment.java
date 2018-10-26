package com.example.lucer_000.redrider.Login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lucer_000.redrider.Profile.ProfileActivity;
import com.example.lucer_000.redrider.R;
import com.example.lucer_000.redrider.Register.RegisterActivity;

import org.w3c.dom.Text;

public class LoginFragment extends Fragment implements LoginContract.View{

    LoginContract.Presenter mPresenter;
    View root;
    private TextView signUp;
    private Button logIn;
    private EditText email;
    private EditText password;

    @Override
    public void setPresenter(@NonNull LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public LoginFragment(){}
    //empty constructor

    public static LoginFragment newInstance(){
        return new LoginFragment();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.login_frag, container, false);
        signUp = (TextView) root.findViewById(R.id.signup);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegister();
            }
        });

        logIn = (Button) root.findViewById(R.id.logIn);
        email = (EditText) root.findViewById(R.id.emailId);
        password = (EditText) root.findViewById(R.id.password);

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn(email.getText().toString(), password.getText().toString());
            }
        });

        return root;
    }



    public void openRegister(){
        Intent intent = new Intent(root.getContext(), RegisterActivity.class);
        startActivity(intent);
    }

    public void signIn(String email, String password){
        if(email.equals("mayur.bhakta@ttu.edu") && password.equals("testing")){
            Intent intent = new Intent(root.getContext(), ProfileActivity.class);
            startActivity(intent);
        }
        else{
            System.out.println("Failed");
        }
    }
}
