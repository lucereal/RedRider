package com.example.lucer_000.redrider.Profile;


import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.method.KeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.lucer_000.redrider.Data.Profile;
import com.example.lucer_000.redrider.R;

import org.w3c.dom.Text;

import java.io.Console;
import java.io.Serializable;

public class ProfileFragment extends Fragment implements ProfileContract.View {

    ProfileContract.Presenter mPresenter;
    View root;
    private Spinner sex;
    private TextView personName, name, major, age, interest;
    private EditText nameField, majorField, ageField, interestField;
    private Button edit, save;


    @Override
    public void setPresenter(@NonNull ProfileContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public ProfileFragment() {
    }
    //empty constructor

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.profile_frag, container, false);

        String email = getArguments().getString("Email");
        String pass = getArguments().getString("Password");

        //linear = inflater.inflate(R.layout.profile_frag, container, false);
        String hi = "Mayur Bhakta";
        personName = (TextView) root.findViewById(R.id.personName);
        personName.setText(hi);

        nameField = root.findViewById(R.id.nameField);
        majorField = root.findViewById(R.id.majorField);
        ageField = root.findViewById(R.id.ageField);
        interestField = root.findViewById(R.id.interestField);

        name = root.findViewById(R.id.name);
        major = root.findViewById(R.id.major);
        age = root.findViewById(R.id.age);
        sex = root.findViewById(R.id.sex);
        interest = root.findViewById(R.id.interest);

        edit = root.findViewById(R.id.editBtn);
        save = root.findViewById(R.id.saveBtn);

        Profile test = mPresenter.getUserProfile();

        name.setText(test.getName());
        major.setText(test.getMajor());
        age.setText(String.valueOf(test.getAge()));
//        setSpinText(sex, test.getSex());

        nameField.setText(test.getName());
        majorField.setText(test.getMajor());
        ageField.setText(String.valueOf(test.getAge()));
       // setSpinText(sex, test.getSex());


        edit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                System.out.println("pressed");
                name.setVisibility(View.GONE);
                major.setVisibility(View.GONE);
                age.setVisibility(View.GONE);
                interest.setVisibility(View.GONE);
                nameField.setVisibility(View.VISIBLE);
                majorField.setVisibility(View.VISIBLE);
                ageField.setVisibility(View.VISIBLE);
                interestField.setVisibility(View.VISIBLE);
                edit.setVisibility(View.GONE);
                save.setVisibility(View.VISIBLE);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.signUp(email, pass, major.getText().toString(), "male", Integer.parseInt(age.getText().toString()), name.getText().toString() );
            }
        });

        return root;
    }
    public void setSpinText(Spinner spin, String text)
    {
        for(int i= 0; i < spin.getAdapter().getCount(); i++)
        {
            if(spin.getAdapter().getItem(i).toString().contains(text))
            {
                spin.setSelection(i-1);
            }
        }

    }

    public void signUpSuccess(){
        Intent intent = new Intent(root.getContext(), ProfileActivity.class);
        startActivity(intent);
    }

}


