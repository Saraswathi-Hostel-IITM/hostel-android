package com.sarasapp.sarasapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.sarasapp.sarasapp.Objects.UserProfile;

public class SplashActivity extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(UserProfile.getEmail(SplashActivity.this)!="")
        {
            Intent i =new Intent(SplashActivity.this,HomeActivity.class);
            startActivity(i);
            finish();
            /*if (checkPlayServices()) {
                // Start IntentService to register this application with GCM.
                Intent intent = new Intent(LoginActivity.this, IE_RegistrationIntentService.class);
                startService(intent);
            }
            finish();*/
        }
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                SharedPreferences sharedPreferences = getSharedPreferences("USER",0);
                if(sharedPreferences.contains("userid")){
                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                }

                else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                }

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}