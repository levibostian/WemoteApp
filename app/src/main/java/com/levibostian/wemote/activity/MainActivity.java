package com.levibostian.wemote.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.levibostian.wemote.R;
import com.levibostian.wemote.application.WemoteApplication;
import com.levibostian.wemote.fragment.HashtagFeedFragment;
import com.levibostian.wemote.fragment.LoginFragment;
import com.levibostian.wemote.fragment.ShowSelectionFragment;
import com.mixpanel.android.mpmetrics.MixpanelAPI;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;
import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends ActionBarActivity implements LoginFragment.LoginFragmentListener,
                                                               ShowSelectionFragment.ShowSelectionFragmentListener {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "0W8H1dPaxTBHmMSaWCMhJraXo";
    private static final String TWITTER_SECRET = "Q8QekXOw0V1U0d5oWb5EUwhklmgnsS1df2vC6PtVjz4HiPBaj5";

    private static final String LOGIN_SCREEN = "LoginScreen";
    private static final String SHOW_SELECTION_SCREEN = "ShowSelectionScreen";
    private static final String HASHTAG_FEED_SCREEN = "HashtagFeedScreen";

    private LoginFragment mLoginFragment;

    @Inject MixpanelAPI mMixpanelAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_main);

        WemoteApplication.inject(this);

        determineIfUserLoggedIn();
    }

    @Override
    protected void onDestroy() {
        mMixpanelAPI.flush();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();

        checkTwitterAppInstalled();

        trackTimeStamps();
    }

    private int hourOfTheDay() {
        final Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    private long hoursSinceEpoch() {
        final Date now = new Date();
        final long nowMillis = now.getTime();
        return nowMillis / 1000 * 60 * 60;
    }

    private void trackTimeStamps() {
        final long nowInHours = hoursSinceEpoch();
        final int hourOfTheDay = hourOfTheDay();

        try {
            final JSONObject properties = new JSONObject();
            properties.put("first viewed on", nowInHours);
            mMixpanelAPI.registerSuperPropertiesOnce(properties);
        } catch (final JSONException e) {
            throw new RuntimeException("Could not encode hour first viewed as JSON");
        }

        try {
            final JSONObject properties = new JSONObject();
            properties.put("hour of the day", hourOfTheDay);
            mMixpanelAPI.track("App Resumed", properties);
        } catch(final JSONException e) {
            throw new RuntimeException("Could not encode hour of the day in JSON");
        }
    }

    private void checkTwitterAppInstalled() {
        PackageManager pkManager = getPackageManager();
        try {
            PackageInfo pkgInfo = pkManager.getPackageInfo("com.twitter.android", 0);
            String getPkgInfo = pkgInfo.toString();

            if (getPkgInfo.equals("com.twitter.android"))   {
                // App installed. All good.
                return;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();

            trackTwitterNotInstalled();

            Toast.makeText(this, "Must install Twitter app to use " + getString(R.string.app_name), Toast.LENGTH_LONG).show();
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.twitter.android")));
            } catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=com.twitter.android")));
            }
        }
    }

    private void trackTwitterNotInstalled() {
        JSONObject object = new JSONObject();

        try {
            object.put("TwitterInstalled", false);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mMixpanelAPI.track("Twitter Stats", object);
    }

    private void determineIfUserLoggedIn() {
        if (isUserLoggedIn()) {
            loginComplete();
        } else {
            setupLoginFragment();
        }
    }

    private boolean isUserLoggedIn() {
        return Twitter.getSessionManager().getActiveSession() != null;
    }

    private void setupLoginFragment() {
        mLoginFragment = LoginFragment.newInstance();
        mLoginFragment.setListener(this);

        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, mLoginFragment)
                .commit();

        trackUserScreenActivity(LOGIN_SCREEN);
    }

    private void trackUserScreenActivity(String screenToIncrement) {
        mMixpanelAPI.getPeople().increment(screenToIncrement, 1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            logoutUser();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void logoutUser() {
        setupLoginFragment();
    }

    @Override
    public void loginComplete() {
        ShowSelectionFragment fragment = ShowSelectionFragment.newInstance();
        fragment.setListener(this);

        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();

        trackUserScreenActivity(SHOW_SELECTION_SCREEN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mLoginFragment.onActivityResult(requestCode, resultCode, data);
        setupTrackingProfileName("" + Twitter.getSessionManager().getActiveSession().getUserId());
    }

    private void setupTrackingProfileName(String userName) {
        mMixpanelAPI.identify(userName);
        mMixpanelAPI.getPeople().identify(userName);
    }

    @Override
    public void showSelected(String nameShow, String hashtag) {
        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, HashtagFeedFragment.newInstance(nameShow, hashtag))
                .commit();

        trackUserScreenActivity(HASHTAG_FEED_SCREEN);
    }
}
