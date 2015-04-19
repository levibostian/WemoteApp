package com.wemoteapp.wemote.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.wemoteapp.wemote.R;
import com.wemoteapp.wemote.vo.ShowVo;

import java.util.ArrayList;

public class ShowSelectionImageAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<ShowVo> mShows;
    private LayoutInflater mInflater;

    public ShowSelectionImageAdapter(Context context, ArrayList<ShowVo> shows) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);

        mShows = shows;
    }

    @Override
    public int getCount() {
        return mShows.size();
    }

    @Override
    public Object getItem(int position) {
        return mShows.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mShows.get(position).imageRes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ImageView showImage;
        TextView showName;

        if (view == null) {
            view = mInflater.inflate(R.layout.show_grid_item, parent, false);
            view.setTag(R.id.show_imageview, view.findViewById(R.id.show_imageview));
            view.setTag(R.id.show_name, view.findViewById(R.id.show_name));
        }

        showImage = (ImageView) view.getTag(R.id.show_imageview);
        showName = (TextView) view.getTag(R.id.show_name);

        ShowVo show = mShows.get(position);

        showImage.setImageResource(show.imageRes);
        showName.setText(show.name);

        return view;
    }
}
