package com.sarasapp.sarasapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sarasapp.sarasapp.GalleryActivity;
import com.sarasapp.sarasapp.Objects.Photo;
import com.sarasapp.sarasapp.R;

import java.util.ArrayList;

/**
 * Created by kevinselvaprasanna on 4/1/16.
 */
public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    Context mContext;
    ArrayList<Photo> mItems;
    public GalleryAdapter(Context mContext, ArrayList<Photo> allPhotos) {
        this.mContext = mContext;
        this.mItems = allPhotos;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv1,iv2,iv3;
        public ViewHolder(View itemView) {
            super(itemView);
            iv1 = (ImageView)itemView.findViewById(R.id.iv1);
            iv2 = (ImageView)itemView.findViewById(R.id.iv2);
            iv3 = (ImageView)itemView.findViewById(R.id.iv3);
        }
    }

    @Override
    public GalleryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout;
        layout = R.layout.item_gallery;
        View view = LayoutInflater.from(parent.getContext())
                .inflate(layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(GalleryAdapter.ViewHolder holder, int position) {

        Glide
                .with(mContext)
                .load(mItems.get(position*3).imgurl)
                .centerCrop()
                .into(holder.iv1);
        Glide
                .with(mContext)
                .load(mItems.get(position*3+1).imgurl)
                .centerCrop()
                .into(holder.iv2);
        Glide
                .with(mContext)
                .load(mItems.get(position*3+2).imgurl)
                .centerCrop()
                .into(holder.iv3);
    }

    @Override
    public int getItemCount() {
        return mItems.size()/3;
    }


}
