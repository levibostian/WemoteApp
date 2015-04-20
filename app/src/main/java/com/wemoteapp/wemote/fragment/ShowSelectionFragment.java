package com.wemoteapp.wemote.fragment;

import android.app.Fragment;
import android.os.Bundle;
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
        ArrayList<ShowVo> shows = new ArrayList<ShowVo>();

        shows.add(new ShowVo("American Idol", R.color.american_idol_theme_color, "Idol"));
        shows.add(new ShowVo("Better Call Saul", R.color.better_call_saul_theme_color, "BetterCallSaul"));
        shows.add(new ShowVo("Cutthroat Kitchen", R.color.cutthroat_kitchen_theme_color, "CutThroatKitchen"));
        shows.add(new ShowVo("Game of Thrones", R.color.game_of_thrones_theme_color, "GoT"));
        shows.add(new ShowVo("Greys Anatomy", R.color.greys_anatomy_theme_color, "GreysAnatomy"));
        shows.add(new ShowVo("House of Cards", R.color.house_of_cards_theme_color, "HouseOfCards"));
        shows.add(new ShowVo("Modern Family", R.color.modern_family_theme_color, "ModernFamily"));
        shows.add(new ShowVo("NBA Playoffs", R.color.nba_playoffs_theme_color, "NBAPlayoffs"));
        shows.add(new ShowVo("NHL Playoffs", R.color.nhl_playoffs_theme_color, "NHLPlayoffs"));
        shows.add(new ShowVo("Once Upon A Time", R.color.once_upon_a_time_theme_color, "OnceUponATime"));
        shows.add(new ShowVo("Orange is the New Black", R.color.orange_is_the_new_black_theme_color, "OITNB"));
        shows.add(new ShowVo("Real Housewives of Atlanta", R.color.real_housewives_of_atlanta_theme_color, "RHOA"));
        shows.add(new ShowVo("Scandal", R.color.scandal_theme_color, "Scandal"));
        shows.add(new ShowVo("Sons of Anarchy", R.color.sons_of_anarchy_theme_color, "SOA"));
        shows.add(new ShowVo("The Bachelor", R.color.the_bachelor_theme_color, "TheBachelor"));
        shows.add(new ShowVo("The Blacklist", R.color.the_blacklist_theme_color, "TheBlacklist"));
        shows.add(new ShowVo("The View", R.color.the_view_theme_color, "TheView"));
        shows.add(new ShowVo("The Voice", R.color.the_voice_theme_color, "TheVoice"));
        shows.add(new ShowVo("The Walking Dead", R.color.walking_dead_theme_color, "TheWalkingDead"));
        shows.add(new ShowVo("Today Show", R.color.today_show_theme_color, "TodayShow"));

        return shows;
    }
}
