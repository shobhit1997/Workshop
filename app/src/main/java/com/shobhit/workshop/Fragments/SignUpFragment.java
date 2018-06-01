package com.shobhit.workshop.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shobhit.workshop.DBHelper.DatabaseHelper;
import com.shobhit.workshop.Dashboard;
import com.shobhit.workshop.Models.Workshop_Model;
import com.shobhit.workshop.R;

import java.util.ArrayList;
import java.util.List;

public class SignUpFragment extends Fragment {

    public SignUpFragment() {
        // Required empty public constructor
    }
   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view=getView();
        final TextInputLayout passwordTextInput = view.findViewById(R.id.password_text_input);
        final TextInputEditText passwordEditText = view.findViewById(R.id.password_edit_text);
        final TextInputLayout usernameTextInput = view.findViewById(R.id.username_input_text);
        final TextInputEditText usernameEditText = view.findViewById(R.id.username_edit_text);
        final TextInputLayout phoneTextInput = view.findViewById(R.id.phone_input_text);
        final TextInputEditText phoneEditText = view.findViewById(R.id.phone_edit_text);
        MaterialButton nextButton = view.findViewById(R.id.signup_button);
        MaterialButton cancelButton = view.findViewById(R.id.cancel_button);
        // Set an error if the password is less than 8 characters.
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences=getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                String username=sharedPreferences.getString("username",null);
                String password=sharedPreferences.getString("password",null);
                if(username!=null&&password!=null) {
                    if (username.equals(usernameEditText.getText().toString())) {
                        usernameTextInput.setError("User already exists");
                    }
                    else {

                         SharedPreferences.Editor editor=sharedPreferences.edit();
                         editor.putString("username",usernameEditText.getText().toString());
                         editor.putString("password",passwordEditText.getText().toString());
                         editor.putString("phone",phoneEditText.getText().toString());
                         editor.putBoolean("loggedIn",true);
                         editor.commit();
                         updateDB();
                         Intent intent = new Intent(getActivity(), Dashboard.class);
                         startActivity(intent);
                    }
                }
                else
                {
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("username",usernameEditText.getText().toString());
                    editor.putString("password",passwordEditText.getText().toString());
                    editor.putString("phone",phoneEditText.getText().toString());
                    editor.putBoolean("loggedIn",true);
                    editor.commit();
                    updateDB();
                    Intent intent = new Intent(getActivity(), Dashboard.class);
                    startActivity(intent);
                }

            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame,new Splash());
                fragmentTransaction.commit();
            }
        });


    }
    private void updateDB()
    {
        DatabaseHelper db=new DatabaseHelper(getContext());
        List<Workshop_Model> workshops=db.getAppliedWorkshops();
        for(int x=0;x<workshops.size();x++)
        {
            workshops.get(x).setApplied("not applied");
            db.updateWorkshop(workshops.get(x));
        }


    }

}
