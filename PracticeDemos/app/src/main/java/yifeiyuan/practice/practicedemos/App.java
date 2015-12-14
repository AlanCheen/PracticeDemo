package yifeiyuan.practice.practicedemos;

import android.app.Application;

import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;

import io.fabric.sdk.android.Fabric;

/**
 * Created by alanchen on 15/9/6.
 */
public class App extends Application {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "RJjbqTVS5kIseg9q4jsRecf7u";
    private static final String TWITTER_SECRET = "dbBgVZYA4oBkmSr5jKaInMzijO2mt3uB82SHviTJ1tgK2Z5P8E";

    @Override
    public void onCreate() {
        super.onCreate();
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
//        Fabric.with(this, new TwitterCore(authConfig), new Digits());
        Fabric.with(this, new TwitterCore(authConfig),new TweetComposer());
    }
}
