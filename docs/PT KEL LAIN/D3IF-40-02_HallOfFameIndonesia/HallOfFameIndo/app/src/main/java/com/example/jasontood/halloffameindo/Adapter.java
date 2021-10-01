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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jason Tood on 2/23/2018.
 */

public class Adapter extends android.widget.ArrayAdapter<MenuItem> {

    public Adapter(@NonNull Context context, @NonNull ArrayList<MenuItem> objects) {
        super(context,0, objects);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       View ViewAdapter=convertView;
       if(ViewAdapter==null){
           ViewAdapter = LayoutInflater.from(getContext()).inflate(R.layout.menuitem,parent,false);
       }
       MenuItem current = getItem(position);
        TextView text = ViewAdapter.findViewById(R.id.txt);
        ImageView icon = ViewAdapter.findViewById(R.id.gbr);

        icon.setImageResource(current.getIcon());

        text.setText(current.getText());
        return ViewAdapter ;
    }
}
