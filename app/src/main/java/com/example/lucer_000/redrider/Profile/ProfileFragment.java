package com.example.lucer_000.redrider.Profile;


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
        interest = root.findViewById(R.id.interest);

        edit = root.findViewById(R.id.editBtn);
        save = root.findViewById(R.id.saveBtn);

        name.setText(hi);
        major.setText("Computer Science");
        age.setText("23");
        interest.setText("Soccer, Soccer, and Soccer");


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

        return root;
    }


}


