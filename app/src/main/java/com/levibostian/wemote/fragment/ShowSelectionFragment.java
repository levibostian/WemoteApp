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

public class ShowSelectionFragment extends Fragment {

    private GridView mShowSelectionGridView;

    public static ShowSelectionFragment newInstance() {
        return new ShowSelectionFragment();
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
        mShowSelectionGridView.setAdapter(new ShowSelectionImageAdapter(getActivity()));

        mShowSelectionGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // get the position and pull out the data for that position.
            }
        });
    }
}
