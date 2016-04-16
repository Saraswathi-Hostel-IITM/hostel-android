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

        import java.util.ArrayList;
        import java.util.List;


public class ComplaintsOpenFragment extends Fragment{
    RecyclerView recyclerView;
    private ComplaintsAdapter mAdapter;

    public ComplaintsOpenFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_complaints_open, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<Complaint> complaintdata = new ArrayList<Complaint>();
        Complaint c = new Complaint("MM14B001", "Water Dispenser Not Working", "Water Dispenser Not Working", 1);
        complaintdata.add(c);
        Complaint c2 = new Complaint("EE14B010", "LAN Port not working", "LAN Port Not Working", 1);
        complaintdata.add(c2);
        Complaint c3 = new Complaint("EE14B060", "Mosquito Nets not provided", "Mosquito Nets not provided", 1);
        complaintdata.add(c3);
        Complaint c4 = new Complaint("EE14B060", "Mosquito Nets not provided", "Mosquito Nets not provided", 1);
        complaintdata.add(c4);

        mAdapter = new ComplaintsAdapter(getActivity(), complaintdata);
        recyclerView.setAdapter(mAdapter);


        return rootView;
    }

}