package com.shobhit.workshop.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shobhit.workshop.R;

public class WorkshopCardViewHolder extends RecyclerView.ViewHolder {

    public ImageView workshopImage;
    public TextView workshopTitle;
    public TextView workshopDesc;
    public LinearLayout workshop_card;

    public WorkshopCardViewHolder(@NonNull View itemView) {
        super(itemView);
        workshopImage = itemView.findViewById(R.id.product_image);
        workshopTitle = itemView.findViewById(R.id.workshop_title);
        workshopDesc = itemView.findViewById(R.id.workshop_desc);
        workshop_card=itemView.findViewById(R.id.workshop_card);
    }
}
