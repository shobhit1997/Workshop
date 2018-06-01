package com.shobhit.workshop;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.shobhit.workshop.Adapters.WorkshopCardRecyclerViewAdapter;
import com.shobhit.workshop.DBHelper.DatabaseHelper;
import com.shobhit.workshop.Models.Workshop_Model;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends AppCompatActivity {

    WorkshopCardRecyclerViewAdapter adapter;
    List<Workshop_Model> workshops;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Dashboard.this, AvailableWorkshops.class);
                startActivity(intent);
            }
        });


        RecyclerView recycler_view=findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_view.setLayoutManager(layoutManager);

        workshops=new ArrayList<>();
        adapter = new WorkshopCardRecyclerViewAdapter(this,workshops);
        recycler_view.setAdapter(adapter);

        db=new DatabaseHelper(this);
    }
    private void getAllWorkshops()
    {
        workshops.clear();
        workshops.addAll(db.getAppliedWorkshops());
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllWorkshops();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sign_out_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        SharedPreferences sharedPreferences=getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean("loggedIn",false);
        editor.commit();
        Intent intent = new Intent(Dashboard.this, MainActivity.class);
        startActivity(intent);
        finishAffinity();
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}
