package com.shobhit.workshop.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shobhit.workshop.Models.Workshop_Model;
import com.shobhit.workshop.R;
import com.shobhit.workshop.WorkshopDescription;

import java.util.List;

public class WorkshopCardRecyclerViewAdapter extends RecyclerView.Adapter<WorkshopCardViewHolder> {

    private List<Workshop_Model> workshopList;
    private Context context;

   public WorkshopCardRecyclerViewAdapter(Context context, List<Workshop_Model> workshopList) {
        this.workshopList = workshopList;
        this.context=context;
    }

    @NonNull
    @Override
    public WorkshopCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.workshop_card, parent, false);
        return new WorkshopCardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkshopCardViewHolder holder, final int position) {

        String description=workshopList.get(position).getDescription();
        description=description.substring(0,description.indexOf('.'));
        holder.workshopDesc.setText(description);
        holder.workshopTitle.setText(workshopList.get(position).getTitle());
//        holder.workshopImage.setImageDrawable();

        holder.workshop_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, WorkshopDescription.class);
                intent.putExtra("workshop_id",workshopList.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return workshopList.size();
    }
}