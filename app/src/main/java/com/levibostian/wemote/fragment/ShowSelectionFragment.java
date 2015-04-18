package com.levibostian.wemote.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import com.levibostian.wemote.R;
import com.levibostian.wemote.adapter.ShowSelectionImageAdapter;
import com.levibostian.wemote.vo.ShowVo;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ShowSelectionFragment extends Fragment {

    private GridView mShowSelectionGridView;

    private ArrayList<ShowVo> mShows;

    private ShowSelectionFragmentListener mListener;

    public interface ShowSelectionFragmentListener {
        void showSelected(String nameShow, String hashtag);
    }

    public void setListener(ShowSelectionFragmentListener listener) {
        mListener = listener;
    }

    public static ShowSelectionFragment newInstance() {
        return new ShowSelectionFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mShows = getShows();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_selection, container, false);

        mShowSelectionGridView = (GridView) view.findViewById(R.id.show_gridview);

        setupViews();

        return view;
    }

    private void setupViews() {
        mShowSelectionGridView.setAdapter(new ShowSelectionImageAdapter(getActivity(), mShows));

        mShowSelectionGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.showSelected(mShows.get(position).name, mShows.get(position).hashtag);
            }
        });
    }

    public ArrayList<ShowVo> getShows() {
        ArrayList<ShowVo> shows = new ArrayList<>();

        shows.add(new ShowVo("The Office", R.drawable.the_office, "TheOffice"));
        shows.add(new ShowVo("The Office", R.drawable.the_office, "TheOffice"));
        shows.add(new ShowVo("The Office", R.drawable.the_office, "TheOffice"));
        shows.add(new ShowVo("The Office", R.drawable.the_office, "TheOffice"));
        shows.add(new ShowVo("The Office", R.drawable.the_office, "TheOffice"));
        shows.add(new ShowVo("The Office", R.drawable.the_office, "TheOffice"));

        return shows;
    }
}
