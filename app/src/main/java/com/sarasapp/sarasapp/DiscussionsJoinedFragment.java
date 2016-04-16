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
import com.sarasapp.sarasapp.Adapters.DiscussionsAdapter;
import com.sarasapp.sarasapp.Objects.Complaint;
import com.sarasapp.sarasapp.Objects.DiscussionTopic;

import java.util.ArrayList;
import java.util.List;


public class DiscussionsJoinedFragment extends Fragment{
    RecyclerView recyclerView;
    private DiscussionsAdapter mAdapter;

    public DiscussionsJoinedFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_discussions_joined, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<DiscussionTopic> complaintdata = new ArrayList<DiscussionTopic>();
        DiscussionTopic c = new DiscussionTopic("MM14B001", "Lets play COD", "Let's play COD");
        complaintdata.add(c);
        DiscussionTopic c2 = new DiscussionTopic("EE14B013", "Saras Wing Videos & RG", "Saras Wing Videos & RG");
        complaintdata.add(c2);
        DiscussionTopic c3 = new DiscussionTopic("EE14B060", "Your opinion on the new entrance?", "Your opinion on the new entrance");
        complaintdata.add(c3);
        DiscussionTopic c4 = new DiscussionTopic("EE14B060", "Monkey Menace", "Monkey Menace");
        complaintdata.add(c4);
        DiscussionTopic c5 = new DiscussionTopic("MM14B001", "DC++ New hubs", "DC++ new hubs");
        complaintdata.add(c4);
        DiscussionTopic c6 = new DiscussionTopic("EE14B013", "Saras Alumni meet", "Saras Alumni meet");
        complaintdata.add(c4);

        mAdapter = new DiscussionsAdapter(getActivity(), complaintdata);
        recyclerView.setAdapter(mAdapter);

        return rootView;
    }

}