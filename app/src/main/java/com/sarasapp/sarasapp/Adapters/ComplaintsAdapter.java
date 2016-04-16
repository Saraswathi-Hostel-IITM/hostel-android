package com.sarasapp.sarasapp.Adapters;

/**
 * Created by adarsh on 14/4/16.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sarasapp.sarasapp.Objects.Complaint;
import com.sarasapp.sarasapp.R;

import java.util.List;

public class ComplaintsAdapter extends RecyclerView.Adapter<ComplaintsAdapter.MyViewHolder> {

    private List<Complaint> itemList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, caption, idtext;
        public ImageView statusimage;

        public MyViewHolder(View view) {
            super(view);
            caption = (TextView) view.findViewById(R.id.captiontext);
            idtext = (TextView) view.findViewById(R.id.idtext);
            statusimage = (ImageView) view.findViewById(R.id.statusimage);
        }
    }


    public ComplaintsAdapter(Context context, List<Complaint> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.object_complaint, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Complaint complaint = itemList.get(position);
        holder.caption.setText(complaint.getCaption());
        holder.idtext.setText("#242");
        if(complaint.getStatus() == 1)
            holder.statusimage.setImageResource(R.drawable.pending);
        else if(complaint.getStatus() == 0)
            holder.statusimage.setImageResource(R.drawable.rejected);
        else if(complaint.getStatus() == 2)
            holder.statusimage.setImageResource(R.drawable.completed);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}