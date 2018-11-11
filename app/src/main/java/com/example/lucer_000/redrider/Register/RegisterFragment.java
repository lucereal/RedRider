package com.example.lucer_000.redrider.Register;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lucer_000.redrider.Login.LoginActivity;
import com.example.lucer_000.redrider.Profile.ProfileActivity;
import com.example.lucer_000.redrider.Profile.ProfileContract;
import com.example.lucer_000.redrider.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterFragment extends Fragment implements RegisterContract.View{

    RegisterContract.Presenter mPresenter;
    View root;
    private EditText email;
    private EditText pass;
    private EditText rePass;
    private TextView signInLink;
    private Button registerBtn;

    public void setPresenter(@NonNull RegisterContract.Presenter presenter){
        mPresenter = presenter;
    }
    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }
    public RegisterFragment(){

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.register_frag, container, false);

        email = root.findViewById(R.id.newEmail);
        pass = root.findViewById(R.id.newPass);
        rePass = root.findViewById(R.id.reNewPass);
        registerBtn = root.findViewById(R.id.registerBtm);
        signInLink = root.findViewById(R.id.signInLink);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validateEmail(email.getText().toString())){
                    email.setError("Must be a TTU email!");
                    email.requestFocus();
                } else if (!validatePassword(pass.getText().toString(), rePass.getText().toString())){
                    pass.setError("Invalid Password");
                    pass.requestFocus();
                    rePass.setError("Invalid Password");
                    rePass.requestFocus();
                }
                else {
                    profilePage(email.getText().toString(), pass.getText().toString());
                }
            }
        });

        signInLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInPage();
            }
        });



        return root;
    }

    public void signInPage(){
        Intent intent = new Intent(root.getContext(), LoginActivity.class);
        startActivity(intent);
    }

    public void profilePage(String email, String pass){
        Intent intent = new Intent(root.getContext(), ProfileActivity.class);
        intent.putExtra("Email", email);
        intent.putExtra("Password", pass);
        startActivity(intent);
    }

    public boolean validatePassword(String pass, String rePass){
        if(pass.equals(rePass)){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean validateEmail(String email){
        String coDomain = "ttu.edu";
        String emailPattern =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + Pattern.quote(coDomain) + "$";

        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();

    }

    public void signUpSuccess(){
        Intent intent = new Intent(root.getContext(), ProfileActivity.class);
        startActivity(intent);
    }
}
