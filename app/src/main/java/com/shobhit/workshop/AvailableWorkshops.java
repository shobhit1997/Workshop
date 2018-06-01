package com.shobhit.workshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.shobhit.workshop.Adapters.WorkshopCardRecyclerViewAdapter;
import com.shobhit.workshop.DBHelper.DatabaseHelper;
import com.shobhit.workshop.Models.Workshop_Model;

import java.util.ArrayList;
import java.util.List;

public class AvailableWorkshops extends AppCompatActivity {


    WorkshopCardRecyclerViewAdapter adapter;
    List<Workshop_Model> workshops;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_workshops);

        RecyclerView recycler_view=findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_view.setLayoutManager(layoutManager);

        workshops=new ArrayList<>();
        adapter = new WorkshopCardRecyclerViewAdapter(this,workshops);
        recycler_view.setAdapter(adapter);

        db=new DatabaseHelper(this);
        getAllWorkshops();
    }
    private void getAllWorkshops()
    {
        workshops.addAll(db.getAllWorkshops());
        adapter.notifyDataSetChanged();
    }

}
