package com.wemoteapp.wemote.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import com.wemoteapp.wemote.R;
import com.wemoteapp.wemote.adapter.ShowSelectionImageAdapter;
import com.wemoteapp.wemote.vo.ShowVo;

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

        shows.add(new ShowVo("The Walking Dead", R.drawable.walking_dead, "TheWalkingDead"));
        shows.add(new ShowVo("NBA Playoffs", R.drawable.nba_playoffs, "NBAPlayoffs"));
        shows.add(new ShowVo("Real Housewives of Atlanta", R.drawable.real_housewives_of_atlanta, "RHOA"));
        shows.add(new ShowVo("Cutthroat Kitchen", R.drawable.cutthroat_kitchen, "CutThroatKitchen"));
        shows.add(new ShowVo("NHL Playoffs", R.drawable.nhl_playoffs, "NHLPlayoffs"));
        shows.add(new ShowVo("House of Cards", R.drawable.house_of_cards, "HouseOfCards"));
        shows.add(new ShowVo("Game of Thrones", R.drawable.game_of_thrones, "GoT"));
        shows.add(new ShowVo("Orange is the New Black", R.drawable.orange_is_the_new_black, "OITNB"));
        shows.add(new ShowVo("Better Call Saul", R.drawable.better_call_saul, "BetterCallSaul"));
        shows.add(new ShowVo("Sons of Anarchy", R.drawable.sons_of_anarchy, "SOA"));
        shows.add(new ShowVo("Scandal", R.drawable.scandal, "Scandal"));

        return shows;
    }
}