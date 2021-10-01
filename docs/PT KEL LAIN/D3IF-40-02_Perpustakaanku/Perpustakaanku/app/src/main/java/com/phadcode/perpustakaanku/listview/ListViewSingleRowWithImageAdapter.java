package com.phadcode.perpustakaanku.listview;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.phadcode.perpustakaanku.R;

import java.util.ArrayList;

/**
 * Created by Hudio Hizari on 4/21/2018.
 */

public class ListViewSingleRowWithImageAdapter extends ArrayAdapter<ListViewSingleRowWithImage> {

    public ListViewSingleRowWithImageAdapter(Activity context, ArrayList<ListViewSingleRowWithImage> words){
        super(context, 0, words);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;

        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.listview_singlerowimage, parent, false);
        }

        ListViewSingleRowWithImage ListViewSingleRowWithImage = getItem(position);

        TextView textView = (TextView) listItemView.findViewById(R.id.tvText);
        textView.setText(ListViewSingleRowWithImage.getText());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.ivImage);
        if (ListViewSingleRowWithImage.isImageProvided()) {
            imageView.setImageResource(ListViewSingleRowWithImage.getImageResourceID());
        }
        else {
            imageView.setVisibility(View.GONE);
        }

        return listItemView;
    }
}
