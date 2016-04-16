package com.sarasapp.sarasapp;

/**
 * Created by adarsh on 14/4/16.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sarasapp.sarasapp.Adapters.DiscussionsAdapter;
import com.sarasapp.sarasapp.Objects.DiscussionTopic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class DiscussionsAllFragment extends Fragment{
    RecyclerView recyclerView;
    private DiscussionsAdapter mAdapter;
    public List<DiscussionTopic> complaintdata;

    public DiscussionsAllFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_discussions_all, container, false);

        complaintdata = new ArrayList<DiscussionTopic>();

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return rootView;
    }

    public void setdata(JSONArray array) {
        for(int i=0;i<array.length();i++) {
            try {
                JSONObject ob = array.getJSONObject(i);
                DiscussionTopic dt = new DiscussionTopic("MM14B001", ob.getString("caption"), ob.getString("_id"), ob.getString("dateUpdated").split("T")[0]);
                complaintdata.add(dt);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        Collections.sort(complaintdata, new Comparator<DiscussionTopic>() {
            @Override
            public int compare(DiscussionTopic fruit2, DiscussionTopic fruit1)
            {

                return fruit1.getDate().compareTo(fruit2.getDate());
            }
        });

        mAdapter = new DiscussionsAdapter(getActivity(), complaintdata);
        recyclerView.setAdapter(mAdapter);
    }

}