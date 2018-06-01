package com.shobhit.workshop.Fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.shobhit.workshop.AvailableWorkshops;
import com.shobhit.workshop.Dashboard;
import com.shobhit.workshop.MainActivity;
import com.shobhit.workshop.R;
import com.shobhit.workshop.WorkshopDescription;

/**
 * A simple {@link Fragment} subclass.
 */
public class Splash extends Fragment implements View.OnClickListener{


    public Splash() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final TextView appLogo=getView().findViewById(R.id.app_logo);
        final ProgressBar loader=getView().findViewById(R.id.loader);
        final LinearLayout layout=getView().findViewById(R.id.login_layout);
        MaterialButton login_button=getView().findViewById(R.id.login_button);
        MaterialButton signup_button=getView().findViewById(R.id.signup_button);
        MaterialButton skip_button=getView().findViewById(R.id.skip_button);
        loader.setMax(50);
        loader.setProgress(0);
        new CountDownTimer(5000, 100) {
            @Override
            public void onTick(long l) {
                loader.setProgress(50-(int)l/100);
            }

            @Override
            public void onFinish() {

                SharedPreferences sharedPreferences=getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                boolean loggedIn=sharedPreferences.getBoolean("loggedIn",false);
                if(loggedIn)
                {
                    Intent intent1=new Intent(getActivity(),Dashboard.class);
                    startActivity(intent1);
                }
                else {
                    appLogo.animate().yBy(-150.0f).setDuration(50);
                    loader.setVisibility(View.GONE);
                    layout.setVisibility(View.VISIBLE);
                }
            }
        }.start();

        login_button.setOnClickListener(this);
        signup_button.setOnClickListener(this);
        skip_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        if(id==R.id.login_button)
        {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame,new LoginFragment());
            fragmentTransaction.commit();
        }
        else if(id==R.id.signup_button)
        {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame,new SignUpFragment());
            fragmentTransaction.commit();
        }
        else if(id==R.id.skip_button)
        {
            Intent intent = new Intent(getActivity(), AvailableWorkshops.class);
            startActivity(intent);
        }
    }
}
