package net.danteh.dantehviewer;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.livequery.LiveQueryException;
import com.parse.livequery.ParseLiveQueryClient;
import com.parse.livequery.ParseLiveQueryClientCallbacks;

public class DantehApplication extends Application {
   // public SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences();
   private static final String TAG = "DANTEHAPP";
    public static ParseLiveQueryClient parseLiveQueryClient;
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("DantehView")
                .clientKey("DantehView27561")
                .server("http://188.40.170.72:1337/parse/")
                .build()
        );

//        Parse.checkInit();
//        Parse.setLogLevel(Parse.LOG_LEVEL_VERBOSE);
        ParseInstallation installation =ParseInstallation.getCurrentInstallation();
        installation.put("GCMSenderId","336238375701");
        installation.put("FCMSenderId","336238375701");
        installation.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("com.parse.push", "successfully installed.");
                } else {
                    Log.e("com.parse.push", "failed to install", e);
                }
            }
        });



//        ParsePush.subscribeInBackground("", new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                if (e == null) {
//                    Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
//                } else {
//                    Log.e("com.parse.push", "failed to subscribe for push", e);
//                }
//            }
//        });
    }
}
