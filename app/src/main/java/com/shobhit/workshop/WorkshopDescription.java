package com.shobhit.workshop;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.button.MaterialButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shobhit.workshop.DBHelper.DatabaseHelper;
import com.shobhit.workshop.Models.Workshop_Model;

public class WorkshopDescription extends AppCompatActivity {

    DatabaseHelper db;
    Workshop_Model workshop_model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workshop_description);
        final Intent intent=getIntent();
        int id=intent.getIntExtra("workshop_id",0);

        db=new DatabaseHelper(this);

        workshop_model=db.getWorkshop(id);

        TextView title=findViewById(R.id.workshop_title);
        TextView desc=findViewById(R.id.workshop_desc);
        TextView date=findViewById(R.id.workshop_date);
        ImageView banner=findViewById(R.id.workshop_image);
        final MaterialButton apply=findViewById(R.id.apply_button);

        SharedPreferences sharedPreferences=getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        final boolean loggedIn=sharedPreferences.getBoolean("loggedIn",false);
        if(workshop_model.getApplied().equals("applied")&&loggedIn)
        {
            apply.setText("Applied");
            apply.setEnabled(false);
        }
        title.setText(workshop_model.getTitle());
        desc.setText(workshop_model.getDescription());
        date.setText(workshop_model.getTimestamp());
//        banner.setImageDrawable();

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(loggedIn)
                {
                    workshop_model.setApplied("applied");
                    db.updateWorkshop(workshop_model);
                    apply.setText("Applied");
                    apply.setEnabled(false);
                }
                else
                {
                    Toast.makeText(WorkshopDescription.this,"Login to Apply",Toast.LENGTH_SHORT).show();
                    Intent intent1=new Intent(WorkshopDescription.this,MainActivity.class);
                    startActivity(intent1);
                }
            }
        });

    }

}
