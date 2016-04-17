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

import com.sarasapp.sarasapp.Adapters.ComplaintsAdapter;
import com.sarasapp.sarasapp.Objects.Complaint;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ComplaintsClosedFragment extends Fragment{
    RecyclerView recyclerView;
    private ComplaintsAdapter mAdapter;

    public ComplaintsClosedFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_complaints_closed, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<Complaint> complaintdata = new ArrayList<Complaint>();
        Complaint c = new Complaint("MM14B001", "Room cleaning", "Room cleaning", 2);
        complaintdata.add(c);
        Complaint c3 = new Complaint("MM14B001", "Mosquito Nets not provided", "Mosquito Nets not provided", 0);
        complaintdata.add(c3);

        mAdapter = new ComplaintsAdapter(getActivity(), complaintdata);
        recyclerView.setAdapter(mAdapter);

        return rootView;
    }

    public void setdata(JSONArray array) {
        List<Complaint> complaintdata = new ArrayList<Complaint>();
        for(int i=0;i<array.length();i++) {
            try {
                JSONObject ob = array.getJSONObject(i);
                Complaint dt = new Complaint("MM14B001", ob.getString("caption"), ob.getString("_id"), 2);
                complaintdata.add(dt);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        mAdapter = new ComplaintsAdapter(getActivity(), complaintdata);
        recyclerView.setAdapter(mAdapter);
    }

}