package com.phadcode.perpustakaanku;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.phadcode.perpustakaanku.data.PerpusContract;
import com.phadcode.perpustakaanku.listview.ListViewSmall;
import com.phadcode.perpustakaanku.listview.ListViewSmallAdapter;
import com.phadcode.perpustakaanku.helper.PerpusDatabaseHelper;
import com.phadcode.perpustakaanku.helper.Session;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private PerpusDatabaseHelper perpusDatabaseHelper;
    private Cursor cursor;

    private ArrayList<ListViewSmall> listviewsmall;
    private ListView lvFeed;
    private TextView tvEmptyView;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        perpusDatabaseHelper = new PerpusDatabaseHelper(getActivity());
        cursor = perpusDatabaseHelper.selectPerpus();

        listviewsmall = new ArrayList<>();
        try {
            while (cursor.moveToNext()){
                String id = cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.PERPUS_ID));
                String name = cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_PERPUS_NAME));
                int[] emptyImagePerpusPp = {R.drawable.perpustakaan, R.drawable.kelola_perpus};
                java.util.Random rd = new java.util.Random();
                listviewsmall.add(new ListViewSmall(id, name, "5 KM", emptyImagePerpusPp[rd.nextInt(emptyImagePerpusPp.length)+0]));
            }
        }finally {
            cursor.close();
        }

        final ListViewSmallAdapter adapter = new ListViewSmallAdapter(this.getActivity(), listviewsmall);
        lvFeed = getView().findViewById(R.id.lvFeed);

        lvFeed.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Session session = new Session(getActivity());

                TextView tvID = (TextView) view.findViewById(R.id.tvID);
                session.setSessionString("id_perpus", tvID.getText().toString().trim());
                session.setSessionString("profil", "perpus");
                startActivity(new Intent(getActivity(), ProfilActivity.class));
            }
        });
        lvFeed.setDivider(null);
        lvFeed.setAdapter(adapter);

        tvEmptyView = (TextView) getView().findViewById(R.id.tvEmptyView);
        lvFeed.setEmptyView(tvEmptyView);
    }
}
