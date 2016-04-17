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
import android.widget.TextView;


import com.sarasapp.sarasapp.DiscussionDetails;
import com.sarasapp.sarasapp.Objects.Complaint;
import com.sarasapp.sarasapp.Objects.DiscussionDetail;
import com.sarasapp.sarasapp.Objects.DiscussionTopic;
import com.sarasapp.sarasapp.R;

import java.util.List;

public class DiscussionDetailsAdapter extends RecyclerView.Adapter<DiscussionDetailsAdapter.MyViewHolder> {

    private List<DiscussionDetail> itemList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, caption, idtext, datetext;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.nametext);
            caption = (TextView) view.findViewById(R.id.captiontext);
            // idtext = (TextView) view.findViewById(R.id.idtext);
            datetext = (TextView) view.findViewById(R.id.datetext);
        }
    }


    public DiscussionDetailsAdapter(Context context, List<DiscussionDetail> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.object_discussion_details, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final DiscussionDetail complaint = itemList.get(position);
        holder.name.setText(complaint.getName());
        holder.caption.setText(complaint.getCaption());
        // holder.idtext.setText("#242");
        holder.datetext.setText("Last Updated " + complaint.getDate());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}