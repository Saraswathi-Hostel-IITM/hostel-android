package com.sarasapp.sarasapp.Adapters;

/**
 * Created by adarsh on 14/4/16.
 */
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.sarasapp.sarasapp.DiscussionDetails;
import com.sarasapp.sarasapp.Objects.Complaint;
import com.sarasapp.sarasapp.Objects.DiscussionTopic;
import com.sarasapp.sarasapp.R;

import java.util.List;

public class TempGalleryAdapter extends RecyclerView.Adapter<TempGalleryAdapter.MyViewHolder> {

    private List<Integer> itemList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView1;

        public MyViewHolder(View view) {
            super(view);
            imageView1 = (ImageView) view.findViewById(R.id.imageView1);
        }
    }


    public TempGalleryAdapter(Context context, List<Integer> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.object_temp_gallery, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Integer complaint = itemList.get(position);
        holder.imageView1.setImageResource(complaint);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}