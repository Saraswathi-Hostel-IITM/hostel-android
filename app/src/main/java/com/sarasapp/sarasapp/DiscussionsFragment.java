package com.sarasapp.sarasapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sarasapp.sarasapp.Constants.URLConstants;
import com.sarasapp.sarasapp.Network.GetRequest;
import com.sarasapp.sarasapp.Network.PostRequest;
import com.sarasapp.sarasapp.Objects.PostParam;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adarsh on 14/4/16.
 */
public class DiscussionsFragment extends Fragment {
    public static final String ARG_PLANET_NUMBER = "planet_number";
    public ViewPager viewPager1;
    private TabLayout tabLayout1;

    private DiscLoadTask mAuthTask = null;
    private String access_token;

    public View rootView;

    public DiscussionsJoinedFragment djfragment;
    public DiscussionsAllFragment dafragment;

    public DiscussionsFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_discussions, container, false);
        getActivity().setTitle("Discussions");

        djfragment = new DiscussionsJoinedFragment();
        dafragment = new DiscussionsAllFragment();

        viewPager1 = (ViewPager) rootView.findViewById(R.id.viewpager1);
        viewPager1.setOffscreenPageLimit(2);
        setupViewPager(viewPager1);

        tabLayout1 = (TabLayout) rootView.findViewById(R.id.tabs1);
        tabLayout1.setupWithViewPager(viewPager1);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("USER",0);
        if(sharedPreferences.contains("access_token")){
            access_token = sharedPreferences.getString("access_token", null);
        }

        else {
            access_token = "3af33f01ad77f7541e5e0bd6ca012201";
        }

        mAuthTask = new DiscLoadTask(access_token);
        mAuthTask.execute((Void) null);

        return rootView;
    }

    public class DiscLoadTask extends AsyncTask<Void, Void, Boolean> {

        private final String mToken;
        JSONObject ResponseJSON;

        DiscLoadTask(String token) {
            mToken = token;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.


          /*  for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split("check@check.com:hello");
                if (pieces[0].equals(mEmail)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);
                }
            }*/

            ResponseJSON = GetRequest.execute(URLConstants.URLDiscussionList + "?access_token=" + mToken, null);
            Log.d("RESPONSE", ResponseJSON.toString());
            try {
                if(ResponseJSON.getJSONObject("data").getBoolean("result")) {
                    // Toast.makeText(getActivity(), "Hippee", Toast.LENGTH_LONG).show();
                    JSONObject obj = ResponseJSON.getJSONObject("data").getJSONObject("data");
                    final JSONArray joined = obj.getJSONArray("joined");
                    final JSONArray unjoined = obj.getJSONArray("unjoined");

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            djfragment.setdata(joined);
                            dafragment.setdata(joined);
                            dafragment.setdata(unjoined);
                        }
                    });
                }
                else {

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return false;
            // TODO: register the new account here.
        }

//        @Override
//        protected void onPostExecute(final Boolean success) {
////            mAuthTask = null;
////            showProgress(false);
//        }

//        @Override
//        protected void onCancelled() {
////            mAuthTask = null;
////            showProgress(false);
//        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(djfragment, "Joined");
        adapter.addFragment(dafragment, "All");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}