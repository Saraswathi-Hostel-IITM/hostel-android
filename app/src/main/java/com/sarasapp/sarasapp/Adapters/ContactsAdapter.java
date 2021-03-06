package com.sarasapp.sarasapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sarasapp.sarasapp.Objects.Contact;
import com.sarasapp.sarasapp.R;

import java.util.ArrayList;

/**
 * Created by kevinselvaprasanna on 4/16/16.
 */
public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    Context mContext;
    ArrayList<Contact> mItems;



    public ContactsAdapter(Context context, ArrayList<Contact> items) {
        mContext = context;
        mItems = items;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView name,email,phone, post;
        ImageView contactPic;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.name);
            phone = (TextView)itemView.findViewById(R.id.phone);
            email = (TextView)itemView.findViewById(R.id.email);
            post = (TextView)itemView.findViewById(R.id.posttext);
            contactPic = (ImageView)itemView.findViewById(R.id.contactpic);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public ContactsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout;
        layout = R.layout.item_contacts;
        View view = LayoutInflater.from(parent.getContext())
                .inflate(layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(mItems.get(position).getName());
        holder.email.setText(mItems.get(position).getEmail());
        holder.phone.setText(mItems.get(position).getPhone());
        holder.post.setText(mItems.get(position).getPost());
        holder.contactPic.setImageResource(mItems.get(position).getImageID());
    }



    @Override
    public int getItemCount() {
        return mItems.size();
    }


}
