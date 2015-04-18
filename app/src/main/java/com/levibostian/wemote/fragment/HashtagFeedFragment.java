package com.levibostian.wemote.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.levibostian.wemote.R;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;
import com.twitter.sdk.android.tweetui.SearchTimeline;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;

public class HashtagFeedFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String NAME_SHOW_TAG = "hashtagFeedFragment.showTag";
    private static final String HASHTAG_TAG = "hashtagFeedFragment.hashtagTag";

    private String mNameShow;
    private String mHashtag;

    private ListView mHashtagListView;
    private Button mSendTweetButton;
    private SwipeRefreshLayout mSwipeToRefreshLayout;
    private TextView mEmptyText;

    public static HashtagFeedFragment newInstance(String nameShow, String hashtag) {
        HashtagFeedFragment fragment = new HashtagFeedFragment();

        Bundle bundle = new Bundle();
        bundle.putString(NAME_SHOW_TAG, nameShow);
        bundle.putString(HASHTAG_TAG, hashtag);

        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        mNameShow = bundle.getString(NAME_SHOW_TAG);
        mHashtag = bundle.getString(HASHTAG_TAG);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hashtag_feed, container, false);

        mHashtagListView = (ListView) view.findViewById(R.id.hashtag_listview);
        mSendTweetButton = (Button) view.findViewById(R.id.send_tweet_button);
        mSwipeToRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_to_refresh);
        mEmptyText = (TextView) view.findViewById(android.R.id.empty);

        setupViews();

        return view;
    }

    private void populateHashtagList() {
        final SearchTimeline searchTimeline = new SearchTimeline.Builder()
                                                      .query(mHashtag)
                                                      .languageCode("en")
                                                      .build();
        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter(getActivity(), searchTimeline);
        mHashtagListView.setAdapter(adapter);
    }

    private void setupViews() {
        populateHashtagList();

        mHashtagListView.setEmptyView(mEmptyText);

        mSendTweetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                composeNewTweet();
            }
        });

        mSwipeToRefreshLayout.setOnRefreshListener(this);
    }

    private void composeNewTweet() {
        TweetComposer.Builder builder = new TweetComposer.Builder(getActivity())
                .text("#" + mHashtag + " ");
        builder.show();
    }

    private void setRefresh(boolean indicateRefresh) {
        mSwipeToRefreshLayout.setRefreshing(indicateRefresh);
    }
    
    @Override
    public void onRefresh() {
        populateHashtagList();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setRefresh(false);
            }
        }, 1500);
    }

}
