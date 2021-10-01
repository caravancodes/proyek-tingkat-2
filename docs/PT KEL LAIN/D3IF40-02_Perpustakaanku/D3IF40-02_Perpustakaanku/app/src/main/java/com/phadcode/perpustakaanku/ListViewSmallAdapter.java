package com.phadcode.perpustakaanku;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewSmallAdapter extends ArrayAdapter<ListViewSmall>{

    public ListViewSmallAdapter(Activity context, ArrayList<ListViewSmall> words){
        super(context, 0, words);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;

        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.listview_small, parent, false);
        }

        ListViewSmall listviewsmall = getItem(position);

        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.tvName);
        miwokTextView.setText(listviewsmall.getName());
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.tvDesc);
        defaultTextView.setText(listviewsmall.getDesc());


        ImageView miwok_image_view = (ImageView) listItemView.findViewById(R.id.ivImage);
        if (listviewsmall.isImageProvided()) {
            miwok_image_view.setImageResource(listviewsmall.getImageResourceID());
        }
        else {
            miwok_image_view.setVisibility(View.GONE);
        }

        return listItemView;
    }
}
