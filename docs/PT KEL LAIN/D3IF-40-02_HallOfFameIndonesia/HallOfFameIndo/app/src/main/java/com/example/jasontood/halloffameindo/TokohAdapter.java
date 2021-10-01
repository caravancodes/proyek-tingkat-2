package com.example.jasontood.halloffameindo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Jason Tood on 2/24/2018.
 */

public class TokohAdapter extends ArrayAdapter<TokohItem> {

    public TokohAdapter(@NonNull Context context, @NonNull List<TokohItem> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View viewAdapter=convertView;
        if(viewAdapter==null){
            viewAdapter= LayoutInflater.from(getContext()).inflate(R.layout.tokohitem,parent,false);
        }

        TokohItem hr =  getItem(position);
        TextView nama = viewAdapter.findViewById(R.id.name);
        TextView des = viewAdapter.findViewById(R.id.deskripsi);
        ImageView gbr = viewAdapter.findViewById(R.id.ktgicon);

        nama.setText(hr.getNama());
        des.setText(hr.getDeskripsi());
        gbr.setImageResource(hr.getIcon());
        return viewAdapter;
    }
}
