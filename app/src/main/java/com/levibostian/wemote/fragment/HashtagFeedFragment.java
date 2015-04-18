package com.levibostian.wemote.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.levibostian.wemote.R;
import com.twitter.sdk.android.tweetui.SearchTimeline;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;

public class HashtagFeedFragment extends Fragment {

    private static final String NAME_SHOW_TAG = "hashtagFeedFragment.showTag";
    private static final String HASHTAG_TAG = "hashtagFeedFragment.hashtagTag";

    private String mNameShow;
    private String mHashtag;

    private ListView mHashtagListView;

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

        setupViews();

        return view;
    }

    private void setupViews() {
        final SearchTimeline searchTimeline = new SearchTimeline.Builder()
                .query(mHashtag)
                .build();

        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter(getActivity(), searchTimeline);
        mHashtagListView.setAdapter(adapter);
    }
    
}