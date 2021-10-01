package com.phadcode.perpustakaanku.listview;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.phadcode.perpustakaanku.R;
import com.phadcode.perpustakaanku.object.BukuObject;

import java.util.ArrayList;
import java.util.List;

public class ListViewSmallAdapter extends ArrayAdapter<ListViewSmall>{

    public ListViewSmallAdapter(Activity context, ArrayList<ListViewSmall> words){
        super(context, 0, words);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;

        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.listview_small, parent, false);
        }

        ListViewSmall listviewsmall = getItem(position);

        TextView tvID = (TextView) listItemView.findViewById(R.id.tvID);
        tvID.setText(listviewsmall.getID());
        tvID.setVisibility(View.GONE);

        TextView textView = (TextView) listItemView.findViewById(R.id.tvName);
        textView.setText(listviewsmall.getName());

        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.tvDesc);
        defaultTextView.setText(listviewsmall.getDesc());


        ImageView imageView = (ImageView) listItemView.findViewById(R.id.ivImage);
        if (listviewsmall.isImageProvided()) {
            imageView.setImageResource(listviewsmall.getImageResourceID());
        }
        else {
            imageView.setVisibility(View.GONE);
        }

        return listItemView;
    }
}
