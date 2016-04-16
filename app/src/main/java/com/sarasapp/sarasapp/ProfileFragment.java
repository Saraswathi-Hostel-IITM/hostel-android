package com.sarasapp.sarasapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sarasapp.sarasapp.Constants.URLConstants;
import com.sarasapp.sarasapp.Network.HttpRequest;
import com.sarasapp.sarasapp.Network.PostRequest;
import com.sarasapp.sarasapp.Objects.PostParam;
import com.sarasapp.sarasapp.Objects.UserProfile;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    EditText name,phone,password;
    TextView tvphone,tvemail;
    String sname,sphone,spass;
    Button edit,update;
    TextInputLayout tiname,tiphone,tipass;
    View page,progress;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_profile, container, false);
        name = (EditText)rootview.findViewById(R.id.name);
        phone = (EditText)rootview.findViewById(R.id.phone);
        password = (EditText)rootview.findViewById(R.id.password);
        update = (Button) rootview.findViewById(R.id.update);
        page = rootview.findViewById(R.id.profilepage);
        progress = rootview.findViewById(R.id.update_progress);
        tvphone =(TextView) rootview.findViewById(R.id.tvphone);
        tvemail =(TextView)rootview.findViewById(R.id.tvemail);
        edit = (Button)rootview.findViewById(R.id.edit_profile);
        tiname = (TextInputLayout)rootview.findViewById(R.id.tiname);
        tiphone = (TextInputLayout)rootview.findViewById(R.id.tiphone);
        tipass = (TextInputLayout)rootview.findViewById(R.id.tipass);
        if(UserProfile.getEmail(getActivity())==""){
            tvphone.setVisibility(View.GONE);
            tvemail.setVisibility(View.GONE);
            edit.setVisibility(View.GONE);
            tiname.setVisibility(View.VISIBLE);
            tiphone.setVisibility(View.VISIBLE);
            tipass.setVisibility(View.VISIBLE);
            update.setVisibility(View.VISIBLE);
        }else {
            tvemail.setText("Email:" + UserProfile.getEmail(getActivity()));
            tvphone.setText("Mobile no:"+ UserProfile.getPhone(getActivity()));
        }

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvphone.setVisibility(View.GONE);
                tvemail.setVisibility(View.GONE);
                edit.setVisibility(View.GONE);
                tiname.setVisibility(View.VISIBLE);
                tiphone.setVisibility(View.VISIBLE);
                tipass.setVisibility(View.VISIBLE);
                update.setVisibility(View.VISIBLE);
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sname = name.getText().toString();
                spass = password.getText().toString();
                sphone = phone.getText().toString();
                UpdateTask ut = new UpdateTask();
                ut.execute();
            }
        });
        return rootview;
    }

    // TODO: Rename method, update argument and hook method into UI event



    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            page.setVisibility(show ? View.GONE : View.VISIBLE);
            page.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    page.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            progress.setVisibility(show ? View.VISIBLE : View.GONE);
            progress.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    progress.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            progress.setVisibility(show ? View.VISIBLE : View.GONE);
            page.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }


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

    class UpdateTask extends AsyncTask<String, Void, Void> {
        JSONObject ResponseJSON;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgress(true);
        }

        @Override
        protected Void doInBackground(String... params) {

            ArrayList<PostParam> instiPostParams = new ArrayList<PostParam>();
            JSONObject jsonObject = new JSONObject();
            JSONObject dataObject = new JSONObject();
            JSONObject detailsObject = new JSONObject();
            try {
                detailsObject.put("email",sname);
                detailsObject.put("phoneno",sphone);
                dataObject.put("password",spass);
                dataObject.putOpt("details",detailsObject);
                jsonObject.put("access_token",UserProfile.getToken(getActivity()));
                jsonObject.putOpt("data",dataObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }


            ResponseJSON = HttpRequest.execute("POST",URLConstants.URLProfile,null, jsonObject);
            Log.d("RESPONSE",ResponseJSON.toString());

            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            showProgress(false);
            try {
                if(ResponseJSON.getJSONObject("data").getBoolean("result")) {
                    super.onPostExecute(aVoid);
                    Fragment fragment = new ContactsFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

                }else {
                    Toast.makeText(getActivity(), "Server is not working", Toast.LENGTH_LONG).show();
                    showProgress(false);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
