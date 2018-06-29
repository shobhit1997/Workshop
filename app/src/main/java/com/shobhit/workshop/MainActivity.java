package com.shobhit.workshop;

import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.design.button.MaterialButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.shobhit.workshop.DBHelper.DatabaseHelper;
import com.shobhit.workshop.Fragments.Splash;
import com.shobhit.workshop.Models.Workshop_Model;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        if(!sharedPreferences.getBoolean("firstTime",false))
        {
            insertWorkshops();
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putBoolean("firstTime",true);
            editor.commit();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame,new Splash());
        fragmentTransaction.commit();
    }
    private void insertWorkshops()
    {
        DatabaseHelper db=new DatabaseHelper(this);
        Workshop_Model model=new Workshop_Model();
        model.setTitle("C++ Workshop");
        model.setDescription("Workshop on basic C++.It will cover all concepts of C++.It a hands-on workshop");
        model.setApplied("not applied");
        model.setImageUrl("https://www.google.com");
        model.setTimestamp("07-06-2018|13:00");
        db.insertWorkshop(model);

        model.setTitle("Python Workshop");
        model.setDescription("Workshop on basic Python.It will cover all concepts of Python.It a hands-on workshop");
        model.setApplied("not applied");
        model.setImageUrl("https://www.google.com");
        model.setTimestamp("10-06-2018|13:00");
        db.insertWorkshop(model);

        model.setTitle("Java Workshop");
        model.setDescription("Workshop on basic Java.It will cover all concepts of Java.It a hands-on workshop");
        model.setApplied("not applied");
        model.setImageUrl("https://www.google.com");
        model.setTimestamp("09-06-2018|13:00");
        db.insertWorkshop(model);

        model.setTitle("Android Workshop");
        model.setDescription("Workshop on basic Android.It will cover all concepts of Android.It a hands-on workshop");
        model.setApplied("not applied");
        model.setImageUrl("https://www.google.com");
        model.setTimestamp("12-06-2018|13:00");
        db.insertWorkshop(model);

        model.setTitle("HTML/CSS Workshop");
        model.setDescription("Workshop on basic HTML/CSS.It will cover all concepts of HTML/CSS.It a hands-on workshop");
        model.setApplied("not applied");
        model.setImageUrl("https://www.google.com");
        model.setTimestamp("15-06-2018|13:00");
        db.insertWorkshop(model);

        model.setTitle("JS Workshop");
        model.setDescription("Workshop on basic JS.It will cover all concepts of JS.It a hands-on workshop");
        model.setApplied("not applied");
        model.setImageUrl("https://www.google.com");
        model.setTimestamp("16-06-2018|13:00");
        db.insertWorkshop(model);
    }
}
