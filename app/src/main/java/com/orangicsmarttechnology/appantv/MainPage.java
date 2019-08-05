package com.orangicsmarttechnology.appantv;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.orangicsmarttechnology.appantv.fragments.ContactFragment;
import com.orangicsmarttechnology.appantv.fragments.Home_Fragment;
import com.orangicsmarttechnology.appantv.fragments.NewsPageFragment;
import com.orangicsmarttechnology.appantv.helper.App;
import com.orangicsmarttechnology.appantv.helper.ForceUpdateChecker;

import java.util.Objects;

import static android.widget.Toast.LENGTH_SHORT;

public class MainPage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , ForceUpdateChecker.OnUpdateNeededListener {

    private InterstitialAd mInterstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //INITIALIZE APP ID
        MobileAds.initialize(this, ((App) this.getApplication()).getAdAppId());

        // Find Banner ad
        AdView adView = (AdView) findViewById(R.id.myBannerAdView);
        AdRequest adRequest = new AdRequest.Builder().build();
        // Display Banner ad
        adView.loadAd(adRequest);




        // Interstitial ADS
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        //Load interstitial ads
        mInterstitialAd.loadAd(new AdRequest.Builder().build());


        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                replaceHomeFragment();
            }
        });






    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        TextView navHeaderName = (TextView) navigationView.getHeaderView(0).findViewById(R.id.navName);
        navHeaderName.setText(((App) this.getApplication()).getAppName());

        TextView navHeaderWebsite = (TextView) navigationView.getHeaderView(0).findViewById(R.id.navWebsite);
        navHeaderWebsite.setText(((App) this.getApplication()).getWebsite());

        ImageView navLogo = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.navLogo);
        navLogo.setImageResource(((App) this.getApplication()).getIcon());


       /* LinearLayout one = (LinearLayout) findViewById(R.id.idOne);
       // one.setOnClickListener(this);

        LinearLayout two = (LinearLayout) findViewById(R.id.idTwo);
       // two.setOnClickListener(this);

        LinearLayout three = (LinearLayout) findViewById(R.id.idThree);
       // three.setOnClickListener(this);

        LinearLayout four = (LinearLayout) findViewById(R.id.idFour);
        //four.setOnClickListener(this);

        LinearLayout five = (LinearLayout) findViewById(R.id.idFive);
*/




        //add this line to display menu1 when the activity is loaded
        displaySelectedScreen(R.id.nav_home);

        //Check update from firebase remote config on each start of app
        ForceUpdateChecker.with(this).onUpdateNeeded(this).check();

    }

    public void loadnewInterstitinalAds()
    {
        if (!mInterstitialAd.isLoading() && !mInterstitialAd.isLoaded()) {
            mInterstitialAd.loadAd(new AdRequest.Builder().build());
        }
    }

    public void displayInterstitial()
    {
        // If Interstitial Ads are loaded then show else show nothing.
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }else{
            replaceHomeFragment();
        }
    }

    public void replaceHomeFragment()
    {
        fragment = new Home_Fragment();
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            //ft.addToBackStack(null);
            ft.commit();

        }
    }

    public void replaceNewsFragment()
    {
        fragment = new NewsPageFragment();
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            //ft.addToBackStack(null);
            ft.commit();

        }
    }
    public void replaceContactFragment()
    {
        fragment = new ContactFragment();
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            //ft.addToBackStack(null);
            ft.commit();

        }
    }

    @Override
    public void onUpdateNeeded() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("New version available")
                .setMessage("Please, update app to new version to continue use.")
                .setPositiveButton("Update",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                redirectStore();
                            }
                        }).setNegativeButton("No, thanks",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //finish();
                                dialog.dismiss();
                            }
                        }).create();
        dialog.show();
    }

    @Override
    public void onUpdateNeededManually(final Boolean yesno) {

        if (yesno){
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle("New version available")
                    .setMessage("Please, update app to new version to get new features.")
                    .setPositiveButton("Update",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    redirectStore();
                                }
                            }).setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).create();
            dialog.show();
        }else{
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle("Update Version")
                    .setMessage("This is the newest version")
                    .setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            }).create();
            dialog.show();
        }

    }

    private void redirectStore() {
        final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }




    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        // First check whether drawer is open or not
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START)) {

            drawer.closeDrawer(GravityCompat.START);

        }else if(fragment instanceof Home_Fragment){

            if (doubleBackToExitPressedOnce) {

                super.onBackPressed(); //Close App
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);

        }else if(fragment instanceof NewsPageFragment){

            displayInterstitial();


        }else {

           replaceHomeFragment();

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_exit) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        //calling the method displayselectedscreen and passing the id of selected menu
        displaySelectedScreen(item.getItemId());

        //make this method blank
        return true;

    }


    //creating fragment object
    Fragment fragment = null;
    Intent intent = null;

    private void displaySelectedScreen(int itemId) {



        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.nav_home:
                fragment = new Home_Fragment();
                break;

            case R.id.nav_news:
                loadnewInterstitinalAds();
                fragment = new NewsPageFragment();
                break;

            case R.id.nav_videos:
                intent = new Intent(getApplicationContext(), YoutubeActivity.class);
                startActivity(intent);
                break;

            case R.id.nav_contactus:
                fragment = new ContactFragment();
                break;

            case R.id.nav_share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, R.string.app_link);
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.app_name)));
                break;

            case R.id.nav_update:
                ForceUpdateChecker.with(this).onUpdateNeeded(this).checkManually();
                break;

            case R.id.nav_about:
                intent = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(intent);
                break;

        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

}

