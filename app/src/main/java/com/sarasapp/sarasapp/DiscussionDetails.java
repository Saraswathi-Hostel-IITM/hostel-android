package com.sarasapp.sarasapp;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.sarasapp.sarasapp.Adapters.DiscussionDetailsAdapter;
import com.sarasapp.sarasapp.Adapters.DiscussionsAdapter;
import com.sarasapp.sarasapp.Constants.URLConstants;
import com.sarasapp.sarasapp.Network.GetRequest;
import com.sarasapp.sarasapp.Objects.DiscussionDetail;
import com.sarasapp.sarasapp.Objects.DiscussionTopic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DiscussionDetails extends AppCompatActivity {
    RecyclerView detailsRV;
    public String name;
    public String id;
    public String date, caption;
    public String access_token;
    private DiscLoadTask mAuthTask = null;
    private DiscussionDetailsAdapter mAdapter;
    public List<DiscussionDetail> complaintdata;
    private TextView nametext, datetext, captiontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                name = null;
                id = null;
                date = null;
                caption = null;
            } else {
                name = extras.getString("name");
                id = extras.getString("id");
                date = extras.getString("date");
                caption = extras.getString("caption");
            }
        } else {
            // newString= (String) savedInstanceState.getSerializable("STRING_I_NEED");
        }

        nametext = (TextView) findViewById(R.id.nametext);
        nametext.setText(name);
        captiontext = (TextView) findViewById(R.id.captiontext);
        captiontext.setText(caption);
        datetext = (TextView) findViewById(R.id.datetext);
        datetext.setText(date);

        detailsRV = (RecyclerView) findViewById(R.id.detailsRV);
        detailsRV.setLayoutManager(new LinearLayoutManager(this));

        SharedPreferences sharedPreferences = getSharedPreferences("USER",0);
        if(sharedPreferences.contains("access_token")){
            access_token = sharedPreferences.getString("access_token", null);
        }

        else {
            access_token = "3af33f01ad77f7541e5e0bd6ca012201";
        }

        mAuthTask = new DiscLoadTask(access_token, id);
        mAuthTask.execute((Void) null);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gallery, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id== android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class DiscLoadTask extends AsyncTask<Void, Void, Boolean> {

        private final String mToken;
        private final String mId;
        JSONObject ResponseJSON;

        DiscLoadTask(String token, String iid) {
            mToken = token;
            mId = iid;
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

            ResponseJSON = GetRequest.execute(URLConstants.URLDiscussionGet + "?access_token=" + mToken + "&id=" + mId, null);
            Log.d("RESPONSE", ResponseJSON.toString());
            try {
                if(ResponseJSON.getJSONObject("data").getBoolean("result")) {
                    // Toast.makeText(getActivity(), "Hippee", Toast.LENGTH_LONG).show();
                    JSONObject obj = ResponseJSON.getJSONObject("data").getJSONObject("data");
                    final JSONArray joined = obj.getJSONArray("messages");

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setdata(joined);
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

    }

    public void setdata(JSONArray array) {
        complaintdata = new ArrayList<DiscussionDetail>();
        for(int i=0;i<array.length();i++) {
            try {
                JSONObject ob = array.getJSONObject(i);
                DiscussionDetail dt = new DiscussionDetail("MM14B001", ob.getString("caption"), ob.getString("_id"), ob.getString("dateUpdated").split("T")[0]);
                complaintdata.add(dt);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        mAdapter = new DiscussionDetailsAdapter(DiscussionDetails.this, complaintdata);
        detailsRV.setAdapter(mAdapter);
    }

}
