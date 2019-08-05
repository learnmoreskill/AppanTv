package com.orangicsmarttechnology.appantv.fragments;


import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Debug;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.orangicsmarttechnology.appantv.AboutActivity;
import com.orangicsmarttechnology.appantv.Constants;
import com.orangicsmarttechnology.appantv.MainPage;
import com.orangicsmarttechnology.appantv.R;
import com.orangicsmarttechnology.appantv.YoutubeActivity;
import com.orangicsmarttechnology.appantv.helper.App;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home_Fragment extends Fragment implements View.OnClickListener {

    Intent intent = null;


    public Home_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        LinearLayout one = (LinearLayout) view.findViewById(R.id.idOne);
        one.setOnClickListener(this);

        LinearLayout two = (LinearLayout) view.findViewById(R.id.idTwo);
        two.setOnClickListener(this);

        LinearLayout three = (LinearLayout) view.findViewById(R.id.idThree);
        three.setOnClickListener(this);

        LinearLayout four = (LinearLayout) view.findViewById(R.id.idFour);
        four.setOnClickListener(this);

        LinearLayout five = (LinearLayout) view.findViewById(R.id.idFive);
        five.setOnClickListener(this);


        return view;
    }



    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.idOne:
                intent = new Intent(getActivity(), YoutubeActivity.class);
                startActivity(intent);
                break;

            case R.id.idTwo:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse( Constants.WEBSITE_URL ));
                startActivity(browserIntent);

                break;

            case R.id.idThree:
                ((MainPage)Objects.requireNonNull(getActivity())).replaceContactFragment();
                break;

            case R.id.idFour:
                ((MainPage)Objects.requireNonNull(getActivity())).loadnewInterstitinalAds();
                ((MainPage)Objects.requireNonNull(getActivity())).replaceNewsFragment();
                //fragment = new NewsPageFragment();
                break;

            case R.id.idFive:
                intent = new Intent(getActivity(), AboutActivity.class);
                startActivity(intent);
                break;
        }
    }





    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Dashboard");
    }

}
