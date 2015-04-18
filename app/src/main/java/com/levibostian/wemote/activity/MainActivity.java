package com.levibostian.wemote.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.levibostian.wemote.R;
import com.levibostian.wemote.fragment.LoginFragment;
import com.levibostian.wemote.fragment.ShowSelectionFragment;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;


public class MainActivity extends ActionBarActivity implements LoginFragment.LoginFragmentListener {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "0W8H1dPaxTBHmMSaWCMhJraXo";
    private static final String TWITTER_SECRET = "Q8QekXOw0V1U0d5oWb5EUwhklmgnsS1df2vC6PtVjz4HiPBaj5";

    private LoginFragment mLoginFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_main);

        setupLoginFragment();
    }

    private void setupLoginFragment() {
        mLoginFragment = LoginFragment.newInstance();
        mLoginFragment.setListener(this);

        getFragmentManager().beginTransaction()
                .add(R.id.fragment_container, mLoginFragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void loginComplete() {
        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, ShowSelectionFragment.newInstance())
                .commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mLoginFragment.onActivityResult(requestCode, resultCode, data);
    }
}
