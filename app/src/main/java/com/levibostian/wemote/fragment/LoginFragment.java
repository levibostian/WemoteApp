package com.levibostian.wemote.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import com.levibostian.wemote.R;
import com.levibostian.wemote.activity.MainActivity;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

public class LoginFragment extends Fragment {

    private TwitterLoginButton mLoginButton;
    private LoginFragmentListener mListener;

    public void setListener(LoginFragmentListener listener) {
        mListener = listener;
    }

    public interface LoginFragmentListener {
        void loginComplete();
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        mLoginButton = (TwitterLoginButton) view.findViewById(R.id.twitter_login_button);
        mLoginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                setShowSelectionFragment();
            }

            @Override
            public void failure(TwitterException exception) {
                // Do something on failure
            }
        });

        return view;
    }

    private void setShowSelectionFragment() {
        mListener.loginComplete();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mLoginButton.onActivityResult(requestCode, resultCode, data);
    }

}
