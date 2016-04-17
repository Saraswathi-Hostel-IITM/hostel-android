package com.sarasapp.sarasapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sarasapp.sarasapp.Adapters.TempGalleryAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adarsh on 14/4/16.
 */
public class TempGalleryFragment extends Fragment {
    public static final String ARG_PLANET_NUMBER = "planet_number";
    public RecyclerView rvGallery;


    public TempGalleryFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_temp_gallery, container, false);
        getActivity().setTitle("Gallery");

        rvGallery = (RecyclerView) rootView.findViewById(R.id.rvGallery);

        List<Integer> imagelist = new ArrayList<Integer>(){};
        imagelist.add(R.drawable.saras_photo_1);
        imagelist.add(R.drawable.saras_photo_2);
        imagelist.add(R.drawable.saras_photo_3);
        imagelist.add(R.drawable.saras_photo_4);
        imagelist.add(R.drawable.saras_photo_5);
        imagelist.add(R.drawable.saras_photo_6);
        imagelist.add(R.drawable.saras_photo_7);
        imagelist.add(R.drawable.saras_photo_8);
        imagelist.add(R.drawable.saras_photo_9);
        imagelist.add(R.drawable.saras_photo_10);
        imagelist.add(R.drawable.saras_photo_11);
        imagelist.add(R.drawable.saras_photo_12);
        imagelist.add(R.drawable.saras_photo_13);

        TempGalleryAdapter adapter = new TempGalleryAdapter(rootView.getContext(), imagelist);
        rvGallery.setAdapter(adapter);
        rvGallery.setLayoutManager(new LinearLayoutManager(getContext()));
        rvGallery.setLayoutManager(new GridLayoutManager(getContext(),2));


        return rootView;
    }
}