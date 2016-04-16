package com.sarasapp.sarasapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sarasapp.sarasapp.Adapters.GalleryAdapter;
import com.sarasapp.sarasapp.Constants.URLConstants;
import com.sarasapp.sarasapp.Network.PostRequest;
import com.sarasapp.sarasapp.Objects.Photo;
import com.sarasapp.sarasapp.Objects.PostParam;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by adarsh on 14/4/16.
 */
public class GalleryFragment extends Fragment {
    JSONObject ResponseJSON;
    ArrayList<PostParam> iPostParams;
    RecyclerView rvNot;
    private View mProgressView;

    public GalleryFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);
        getActivity().setTitle("New Fragment");

        LinearLayoutManager layoutManager;

        mProgressView = rootView.findViewById(R.id.photo_progress);
        rvNot = (RecyclerView)rootView.findViewById(R.id.rvGallery);
        layoutManager = new LinearLayoutManager(rootView.getContext());

        GetTask gt = new GetTask();
        gt.execute();

        //set the recycler view to use the linear layout manager
        rvNot.setLayoutManager(layoutManager);

        //initialize events feed adapter
        GalleryAdapter photoAdapter = new GalleryAdapter(getActivity() , Photo.getAllPhotos(getActivity()));
        rvNot.setAdapter(photoAdapter);

        return rootView;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

           /* mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });*/

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            rvNot.setVisibility(show ? View.GONE : View.VISIBLE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                    rvNot.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            rvNot.setVisibility(show ? View.GONE : View.VISIBLE);
            //mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    public class GetTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgress(true);
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            ResponseJSON = PostRequest.execute(URLConstants.URLPhoto, iPostParams, null);
            try {
                JSONObject data = ResponseJSON.getJSONObject("data");
                Photo photo;
                JSONObject jphoto;
                ArrayList<Photo> photos = new ArrayList<Photo>();
                for (int i=0; i<ResponseJSON.getJSONObject("data").getInt("length"); i++){
                    jphoto = data.getJSONObject(String.valueOf(i));
                    photo = new Photo(jphoto);
                    photo.savePhoto(getActivity());
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.d("RESPONSE", ResponseJSON.toString());
            return null;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            showProgress(false);

        }
    }
}