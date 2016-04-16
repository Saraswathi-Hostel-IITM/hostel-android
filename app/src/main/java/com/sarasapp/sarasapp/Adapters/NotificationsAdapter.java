package com.sarasapp.sarasapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sarasapp.sarasapp.Objects.Notification;
import com.sarasapp.sarasapp.R;

import java.util.ArrayList;

/**
 * Created by kevinselvaprasanna on 4/16/16.
 */
public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.ViewHolder> {

    Context mContext;
    ArrayList<Notification> mItems;



    public NotificationsAdapter(Context context, ArrayList<Notification> items) {
        mContext = context;
        mItems = items;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView name,email,phone;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.name);
            phone = (TextView)itemView.findViewById(R.id.phone);
            email = (TextView)itemView.findViewById(R.id.email);

        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public NotificationsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout;
        layout = R.layout.item_notifications;
        View view = LayoutInflater.from(parent.getContext())
                .inflate(layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }



    @Override
    public int getItemCount() {
        return mItems.size();
    }


}

