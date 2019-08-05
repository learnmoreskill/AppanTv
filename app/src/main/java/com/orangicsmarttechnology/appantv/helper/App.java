package com.orangicsmarttechnology.appantv.helper;

import android.app.Application;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.orangicsmarttechnology.appantv.R;

import java.util.HashMap;
import java.util.Map;

/* Check app update */

public class App extends Application {

    private static final String TAG = App.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

        final FirebaseRemoteConfig firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();

        // set in-app defaults
        Map<String, Object> remoteConfigDefaults = new HashMap();
        remoteConfigDefaults.put(ForceUpdateChecker.KEY_UPDATE_REQUIRED, false);
        remoteConfigDefaults.put(ForceUpdateChecker.KEY_CURRENT_VERSION, "1.0.0");

        firebaseRemoteConfig.setDefaults(remoteConfigDefaults);
        firebaseRemoteConfig.fetch(30) // fetch every 30 second
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "remote config is fetched.");
                            firebaseRemoteConfig.activateFetched();
                        }
                    }
                });
    }



    private String appName = "appantv";

    public String getAdAppId() {
        if (appName.equals("appantv")){
            return "ca-app-pub-4142525948953301~8653637994";
            //appantvApp id applied
        }else if(appName.equals("janakpurtv")){
            return "ca-app-pub-3940256099942544~3347511713";
        }else{
            return "ca-app-pub-3940256099942544~3347511713";
        }
    }


    public String getAppName() {
        if (appName.equals("appantv")){
            return "Appan Tv";
        }else if(appName.equals("janakpurtv")){
            return "Janakpur Tv";
        }else{
            return "App Name";
        }
    }
    public String getWebsite() {
        if (appName.equals("appantv")){
            return "www.appantv.com.np";
        }else if(appName.equals("janakpurtv")){
            return "Janakpur Tv";
        }else{
            return "";
        }
    }
    public String getWebsiteURL() {
        if (appName.equals("appantv")){
            return "http://www.appantv.com.np/";
        }else if(appName.equals("janakpurtv")){
            return "http://www.janakpur.com.np/";
        }else{
            return "";
        }
    }
    public Integer getIcon() {
        if (appName.equals("appantv")){
            return R.drawable.appantv_icon;
        }else if(appName.equals("janakpurtv")){
            return R.drawable.appantv;
        }else{
            return R.drawable.appantv;
        }
    }
    public String getChangelogUrl() {
        if (appName.equals("appantv")){
            return "https://learnmoreskill.github.io/changelog/appantv_changelog.html";
        }else if(appName.equals("janakpurtv")){
            return "https://learnmoreskill.github.io/changelog/janakpur_changelog.html";
        }else{
            return "";
        }
    }
    public String getPrivacyPolicy() {
        if (appName.equals("appantv")){
            return "https://learnmoreskill.github.io/privacypolicy/appantv_privacy_policy.html";
        }else if(appName.equals("janakpurtv")){
            return "https://learnmoreskill.github.io/privacypolicy/appantv_privacy_policy.html";
        }else{
            return "";
        }
    }
    public String getFacebook() {
        if (appName.equals("appantv")){
            return "https://www.facebook.com/appantelevision/";
        }else if(appName.equals("janakpurtv")){
            return "https://www.facebook.com/";
        }else{
            return "";
        }
    }
    public String getYoutube() {
        if (appName.equals("appantv")){
            return "https://www.youtube.com/channel/UCXcQ1FAOAPIq3Vguv5C5QGQ";
        }else if(appName.equals("janakpurtv")){
            return "https://www.youtube.com/channel/";
        }else{
            return "";
        }
    }
    public String getInstagram() {
        if (appName.equals("appantv")){
            return "";
        }else if(appName.equals("janakpurtv")){
            return "";
        }else{
            return "";
        }
    }
    public String getTwitter() {
        if (appName.equals("appantv")){
            return "https://twitter.com/AppanTelevision";
        }else if(appName.equals("janakpurtv")){
            return "https://twitter.com/";
        }else{
            return "";
        }
    }
    public String getLinkedin() {
        if (appName.equals("appantv")){
            return "https://np.linkedin.com/in/appan-television-b75a8a131";
        }else if(appName.equals("janakpurtv")){
            return "https://np.linkedin.com/in/";
        }else{
            return "";
        }
    }

}
