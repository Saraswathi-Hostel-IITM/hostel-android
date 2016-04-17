package com.sarasapp.sarasapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.sarasapp.sarasapp.Constants.URLConstants;
import com.sarasapp.sarasapp.Network.PostRequest;
import com.sarasapp.sarasapp.Objects.PostParam;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import com.sarasapp.sarasapp.Services.IE_RegistrationIntentService;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public String access_token;
    private UserLogoutTask mAuthTask = null;
    private NewDiscTask mAuthTaskDesc = null;
    public SharedPreferences sharedPreferences;
    public FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startService(new Intent(HomeActivity.this, IE_RegistrationIntentService.class));
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Fragment fragment = new HomeFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        sharedPreferences = getSharedPreferences("USER",0);
        if(sharedPreferences.contains("access_token")){
            access_token = sharedPreferences.getString("access_token", null);
        }

        else {
            access_token = "";
        }

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.GONE);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Fragment fragment = new HomeFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        }
        else if (id == R.id.nav_profile) {
            Fragment fragment = new ProfileFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        }

        else if (id == R.id.nav_gallery) {
            Fragment fragment = new ProfileFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();


        } else if (id == R.id.nav_slideshow) {
            Fragment fragment = new DiscussionsFragment();

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

            fab.setVisibility(View.VISIBLE);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    popup_request();
                }
            });

        } else if (id == R.id.nav_manage) { //complaint
            Fragment fragment = new ComplaintsFragment();

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        } else if (id == R.id.nav_contacts) {
            Fragment fragment = new ContactsFragment();

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        }
        else if (id == R.id.nav_notifications) {
            Fragment fragment = new NotificationsFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        }

        else if (id == R.id.nav_logout) {
            mAuthTask = new UserLogoutTask(access_token);
            mAuthTask.execute((Void) null);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public class UserLogoutTask extends AsyncTask<Void, Void, Boolean> {

        private final String mToken;
        JSONObject ResponseJSON;

        UserLogoutTask(String token) {
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

            ArrayList<PostParam> iPostParams = new ArrayList<PostParam>();
            PostParam postemail = new PostParam("access_token", mToken);
            iPostParams.add(postemail);
            ResponseJSON = PostRequest.execute(URLConstants.URLLogout, iPostParams, null);
            Log.d("RESPONSE", ResponseJSON.toString());
            try {
                if(ResponseJSON.getJSONObject("data").getBoolean("result")) {
                    return true;
                }
                else {
                    return false;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return false;
            // TODO: register the new account here.
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;

            try {
                if (ResponseJSON.getJSONObject("data").getBoolean("result")) {
                   /* Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);*/

                    // Toast.makeText(getApplicationContext(), ResponseJSON.getJSONObject("data").getJSONObject("user").getString("_id"), Toast.LENGTH_LONG).show();

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("userid", null);
                    editor.putString("access_token", null);
                    editor.commit();

                    Intent intent = new Intent(HomeActivity.this,LoginActivity.class);
                    intent.putExtra("LOGIN", true);
                    startActivity(intent);

//                    Toast.makeText(getApplicationContext(), ResponseJSON.getJSONObject("data").getJSONObject("data").getJSONObject("user").getString("_id"), Toast.LENGTH_LONG).show();
                    finish();
                } else if(ResponseJSON.getJSONObject("data").getJSONObject("err").getInt("code") == 345 ){

                }else {

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
        }
    }

    private void popup_request() {
        LayoutInflater layoutInflater = LayoutInflater.from(HomeActivity.this);

        View promptView = layoutInflater.inflate(R.layout.popup_layout, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(HomeActivity.this);

        // set prompts.xml to be the layout file of the alertdialog builder
        alertDialogBuilder.setView(promptView);

        final EditText input = (EditText) promptView.findViewById(R.id.userInput);

        // setup a dialog window
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // get user input and set it to result
//                        editTextMainScreen.setText(input.getText());
                        mAuthTaskDesc = new NewDiscTask(access_token, input.getText().toString());
                        mAuthTaskDesc.execute((Void) null);

                            }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alertD = alertDialogBuilder.create();

        alertD.show();
    }

    public class NewDiscTask extends AsyncTask<Void, Void, Boolean> {

        private final String mToken;
        private final String mInput;
        JSONObject ResponseJSON;

        NewDiscTask(String token, String input) {
            mToken = token;
            mInput = input;
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

            ArrayList<PostParam> iPostParams = new ArrayList<PostParam>();
            PostParam postemail = new PostParam("access_token", mToken);
            PostParam postinput = new PostParam("caption", mInput);
            iPostParams.add(postemail);
            iPostParams.add(postinput);
            ResponseJSON = PostRequest.execute(URLConstants.URLNewDesc, iPostParams, null);
            Log.d("RESPONSE", ResponseJSON.toString());
            try {
                if(ResponseJSON.getJSONObject("data").getBoolean("result")) {
                    return true;
                }
                else {
                    return false;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return false;
            // TODO: register the new account here.
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;

            try {
                if (ResponseJSON.getJSONObject("data").getBoolean("result")) {
                   /* Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);*/

                    // Toast.makeText(getApplicationContext(), ResponseJSON.getJSONObject("data").getJSONObject("user").getString("_id"), Toast.LENGTH_LONG).show();

//                    Toast.makeText(getApplicationContext(), ResponseJSON.getJSONObject("data").getJSONObject("data").getJSONObject("user").getString("_id"), Toast.LENGTH_LONG).show();
                } else if(ResponseJSON.getJSONObject("data").getJSONObject("err").getInt("code") == 345 ){

                }else {

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
        }
    }
}
