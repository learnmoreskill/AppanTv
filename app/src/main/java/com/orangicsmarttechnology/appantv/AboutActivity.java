package com.orangicsmarttechnology.appantv;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.danielstone.materialaboutlibrary.ConvenienceBuilder;
import com.danielstone.materialaboutlibrary.MaterialAboutActivity;
import com.danielstone.materialaboutlibrary.items.MaterialAboutActionItem;
import com.danielstone.materialaboutlibrary.items.MaterialAboutTitleItem;
import com.danielstone.materialaboutlibrary.model.MaterialAboutCard;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;
import com.orangicsmarttechnology.appantv.helper.App;

public class AboutActivity extends MaterialAboutActivity {

    @NonNull
    @Override
    protected MaterialAboutList getMaterialAboutList(@NonNull Context context) {


        MaterialAboutCard.Builder appCardBuilder = new MaterialAboutCard.Builder();

        //Add items to card
        appCardBuilder.addItem(new MaterialAboutTitleItem.Builder()
                .text(((App) this.getApplication()).getAppName())
                .icon(((App) this.getApplication()).getIcon())
                .build());
        appCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text("Version")
                .subText(BuildConfig.VERSION_NAME)
                .icon(R.drawable.ic_android)
                .build());

        appCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text("Changelog")
                .icon(R.drawable.ic_changelog_18dp)
                .setOnClickAction(ConvenienceBuilder.createWebsiteOnClickAction(context, Uri.parse(((App) this.getApplication()).getChangelogUrl())))
                .build());

        appCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text("Privacy Policy")
                .icon(R.drawable.ic_https)
                .setOnClickAction(ConvenienceBuilder.createWebsiteOnClickAction(context, Uri.parse(((App) this.getApplication()).getPrivacyPolicy())))
                .build());

        MaterialAboutCard.Builder socialCardBuilder = new MaterialAboutCard.Builder();
        socialCardBuilder.title("Social");


        if(!TextUtils.isEmpty(((App) this.getApplication()).getWebsiteURL())){
            socialCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                    .text("Website")
                    .icon(R.drawable.ic_language_black_18dp)
                    .setOnClickAction(ConvenienceBuilder.createWebsiteOnClickAction(context, Uri.parse(((App) this.getApplication()).getWebsiteURL())))
                    .build());
        }

        if(!TextUtils.isEmpty(((App) this.getApplication()).getFacebook())){
            socialCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                    .text("Facebook")
                    .setOnClickAction(ConvenienceBuilder.createWebsiteOnClickAction(context, Uri.parse(((App) this.getApplication()).getFacebook())))
                    .icon(R.drawable.facebook)
                    .build());
        }

        if(!TextUtils.isEmpty(((App) this.getApplication()).getYoutube())){
            socialCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                    .text("YouTube")
                    .icon(R.drawable.youtube)
                    .setOnClickAction(ConvenienceBuilder.createWebsiteOnClickAction(context, Uri.parse(((App) this.getApplication()).getYoutube())))
                    .build());
        }

        if(!TextUtils.isEmpty(((App) this.getApplication()).getInstagram())){
            socialCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                    .text("Instagram")
                    .icon(R.drawable.instagram)
                    .setOnClickAction(ConvenienceBuilder.createWebsiteOnClickAction(context, Uri.parse(((App) this.getApplication()).getInstagram())))
                    .build());
        }


        if(!TextUtils.isEmpty(((App) this.getApplication()).getTwitter())){
            socialCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                    .text("Twitter")
                    .icon(R.drawable.twitter)
                    .setOnClickAction(ConvenienceBuilder.createWebsiteOnClickAction(context, Uri.parse(((App) this.getApplication()).getTwitter())))
                    .build());
        }

        if(!TextUtils.isEmpty(((App) this.getApplication()).getLinkedin())){
            socialCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                    .text("LinkedIn")
                    .icon(R.drawable.linkedin)
                    .setOnClickAction(ConvenienceBuilder.createWebsiteOnClickAction(context, Uri.parse(((App) this.getApplication()).getLinkedin())))
                    .build());
        }




        MaterialAboutCard.Builder supportCardBuilder = new MaterialAboutCard.Builder();
        supportCardBuilder.title("Support");

        supportCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text("Orangic Smart Technology")
                .subText("orangicsmarttechnology.com.np")
                .icon(R.drawable.ic_language_black_18dp)
                .setOnClickAction(ConvenienceBuilder.createWebsiteOnClickAction(context, Uri.parse("https://orangicsmarttechnology.com.np/")))
                .build());
        supportCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text("Krishna Mandal")
                .subText("Developer")
                .icon(R.drawable.ic_sentiment_satisfied_black_18dp)
                .setOnClickAction(ConvenienceBuilder.createWebsiteOnClickAction(context, Uri.parse("https://krishnam.com.np/")))
                .build());


        return new MaterialAboutList(appCardBuilder.build(), socialCardBuilder.build(), supportCardBuilder.build());
    }


    @Nullable
    @Override
    protected CharSequence getActivityTitle() {
        return getString(R.string.about_title);
    }


}
