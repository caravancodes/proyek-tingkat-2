package com.phadcode.perpustakaanku.listview;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.database.Cursor;

import com.phadcode.perpustakaanku.R;
import com.phadcode.perpustakaanku.data.PerpusContract;
import com.phadcode.perpustakaanku.helper.PerpusDatabaseHelper;
import com.phadcode.perpustakaanku.helper.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hudio Hizari on 4/25/2018.
 */

public class ListViewSmallLoader extends AsyncTaskLoader<List<ListViewSmall>> {
    private String kelas;
    private int i = 1;

    public ListViewSmallLoader(Context context) {
        super(context);
        this.kelas = kelas;
    }

    @Override
    protected void onStartLoading(){
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public List<ListViewSmall> loadInBackground() {
//        System.out.println("Load in background terpanggil ke : " + i + " kali.");
        i++;
        PerpusDatabaseHelper perpusDatabaseHelper = new PerpusDatabaseHelper(getContext());
        List<ListViewSmall> listviewsmall = new ArrayList<>();
        Session session = new Session(getContext());
        Cursor cursor = perpusDatabaseHelper.selectOwnedBuku(session.getSessionString("id_perpus"));

        try {
            while (cursor.moveToNext()){
                String id = cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_BUKU_ID));
                String name = cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_BUKU_JUDUL));
                listviewsmall.add(new ListViewSmall(id, name, "Tersedia", R.drawable.bookempty));
            }
        }finally {
            cursor.close();
        }
        return listviewsmall;
    }

}
