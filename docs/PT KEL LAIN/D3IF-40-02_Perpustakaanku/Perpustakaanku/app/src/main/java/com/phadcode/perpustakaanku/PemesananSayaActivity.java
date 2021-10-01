package com.phadcode.perpustakaanku;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.phadcode.perpustakaanku.data.PerpusContract;
import com.phadcode.perpustakaanku.helper.PerpusDatabaseHelper;
import com.phadcode.perpustakaanku.helper.Session;
import com.phadcode.perpustakaanku.helper.StringHelper;
import com.phadcode.perpustakaanku.listview.ListViewSmall;
import com.phadcode.perpustakaanku.listview.ListViewSmallAdapter;

import java.util.ArrayList;

public class PemesananSayaActivity extends AppCompatActivity {
    private Session session;
    private PerpusDatabaseHelper perpusDatabaseHelper;
    private Cursor cursor;

    private ListView lvBooking;
    private TextView tvEmptyView;

    private ArrayList<ListViewSmall> listViewSmalls;
    private ListViewSmallAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanan_saya);

        getSupportActionBar().setTitle("Pemesanan Saya");

        session = new Session(this);
        perpusDatabaseHelper = new PerpusDatabaseHelper(this);
        cursor = perpusDatabaseHelper.selectOwnedBooking(session.getSessionString("email"));

        listViewSmalls = new ArrayList<>();
        try {
            while (cursor.moveToNext()){
                String id = cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_BOOKING_ID));
                String idBook = cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_BOOKING_ID_BUKU));
                String status = cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_BOOKING_STATUS));
                String nameBook = "";
                Cursor cursor = perpusDatabaseHelper.selectSingleBuku(idBook);
                try {
                    cursor.moveToFirst();
                    nameBook = cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_BUKU_JUDUL));
                }
                finally {
                    cursor.close();
                }
                listViewSmalls.add(new ListViewSmall(id, nameBook, status, R.drawable.bookempty));
            }
        }finally {
            cursor.close();
        }
        adapter = new ListViewSmallAdapter(this, listViewSmalls);

        lvBooking = (ListView) findViewById(R.id.lvBooking);
        lvBooking.setAdapter(adapter);

        tvEmptyView = (TextView) findViewById(R.id.tvEmptyView);
        tvEmptyView.setText("Tidak ada pemesanan.");
        lvBooking.setEmptyView(tvEmptyView);
    }
}
