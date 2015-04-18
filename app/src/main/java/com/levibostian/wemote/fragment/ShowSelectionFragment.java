package com.levibostian.wemote.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.levibostian.wemote.R;

public class ShowSelectionFragment extends Fragment {

    public static ShowSelectionFragment newInstance() {
        return new ShowSelectionFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_selection, container, false);

        return view;
    }
}
