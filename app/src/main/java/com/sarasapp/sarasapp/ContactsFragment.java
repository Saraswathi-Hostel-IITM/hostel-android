package com.sarasapp.sarasapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sarasapp.sarasapp.Adapters.ContactsAdapter;
import com.sarasapp.sarasapp.Objects.Contact;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContactsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ContactsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ContactsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactsFragment newInstance(String param1, String param2) {
        ContactsFragment fragment = new ContactsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        getActivity().setTitle("Contacts");
        LinearLayoutManager layoutManager;
        RecyclerView rvNot;
        ArrayList<Contact> cots = new ArrayList<Contact>();
        Contact ct = new Contact("General Secretary", "Sandeep R", "1234567890", "saras.gensec@gmail.com", R.drawable.sandeep_1);
        cots.add(ct);
        Contact ct2 = new Contact("Hostel Legislator", "Mahanth", "1234567890", "saras.legislator@gmail.com", R.drawable.mahanth_1);
        cots.add(ct2);
        Contact ct3 = new Contact("Technical Affairs Secretary", "Krishna Changlani", "1234567890", "saras.techsec@gmail.com", R.drawable.krishna_1);
        cots.add(ct3);
        Contact ct4 = new Contact("Literary Affairs Secretary", "Kalyan Perisetty", "1234567890", "saras.litsoc@gmail.com", R.drawable.kalyan_1);
        cots.add(ct4);
        Contact ct5 = new Contact("Social Affairs Secretary", "Srikanth Musti", "1234567890", "saras.litsoc@gmail.com", R.drawable.srikanth_1);
        cots.add(ct5);
        Contact ct6 = new Contact("Sports Secretary", "Akhil Reddy", "1234567890", "saras.gensec@gmail.com", R.drawable.akhil_1);
        cots.add(ct6);
        Contact ct7 = new Contact("Health and Hygiene Secretary", "Vulchi Kalyan", "1234567890", "saras.hhs@gmail.com", R.drawable.matt_circle);
        cots.add(ct7);


        rvNot = (RecyclerView)view.findViewById(R.id.rvContacts);
        layoutManager = new LinearLayoutManager(getActivity());
        rvNot.setLayoutManager(layoutManager);
        ContactsAdapter conAdapter = new ContactsAdapter(getActivity(),cots);
        rvNot.setAdapter(conAdapter);
        return  view;
    }

    // TODO: Rename method, update argument and hook method into UI event




    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
