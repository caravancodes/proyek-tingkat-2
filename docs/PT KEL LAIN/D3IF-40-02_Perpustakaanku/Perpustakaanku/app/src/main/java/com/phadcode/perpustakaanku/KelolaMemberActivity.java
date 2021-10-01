package com.phadcode.perpustakaanku;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.phadcode.perpustakaanku.data.PerpusContract;
import com.phadcode.perpustakaanku.helper.PerpusDatabaseHelper;
import com.phadcode.perpustakaanku.helper.Session;
import com.phadcode.perpustakaanku.helper.StringHelper;
import com.phadcode.perpustakaanku.listview.ListViewSmall;
import com.phadcode.perpustakaanku.listview.ListViewSmallAdapter;

import java.util.ArrayList;

public class KelolaMemberActivity extends AppCompatActivity {
    private Session session;
    private PerpusDatabaseHelper perpusDatabaseHelper;
    private Cursor cursor;
    private ListViewSmallAdapter adapter;

    private ListView lvMember;
    private TextView tvEmptyView;

    private ArrayList<ListViewSmall> listViewSmalls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelola_member);

        getSupportActionBar().setTitle("Kelola Member");

        session = new Session(this);

        session.setSessionString("profil", null);

        listViewSmalls = getArraylist();
        lvMember = findViewById(R.id.lvMember);
        adapter = new ListViewSmallAdapter(this, listViewSmalls);
        lvMember.setAdapter(adapter);

        lvMember.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                TextView tvId = view.findViewById(R.id.tvID);
                TextView tvDetail = view.findViewById(R.id.tvDesc);
                if (tvDetail.getText().toString().equalsIgnoreCase("belum diterima")){
                    session.setSessionString("profil", "kelola_user_tidak_aktif");
                }
                else{
                    session.setSessionString("profil", "kelola_user_aktif");
                }
                Intent intent = new Intent(KelolaMemberActivity.this, ProfilActivity.class);
                intent.putExtra("kumpulan_id", tvId.getText().toString());
                startActivity(intent);
            }
        });

        tvEmptyView = findViewById(R.id.tvEmptyView);
        tvEmptyView.setText("Tidak ada member.");
        lvMember.setEmptyView(tvEmptyView);
    }

    private ArrayList<ListViewSmall> getArraylist(){
        perpusDatabaseHelper = new PerpusDatabaseHelper(this);
        cursor = perpusDatabaseHelper.selectPerpusKeanggotaan(session.getSessionString("owned_perpus"));
        ArrayList<ListViewSmall> listViewSmalls = new ArrayList<>();
        try {
            while (cursor.moveToNext()){
                String id = "";
                String status = cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_KEANGGOTAAN_STATUS));
                String email = cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_KEANGGOTAAN_EMAIL_PENGGUNA));
                String nameUser = "";
                Cursor cursorSecond = perpusDatabaseHelper.selectSingleUser(email);
                try {
                    cursorSecond.moveToFirst();
                    id = cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_KEANGGOTAAN_ID)) + "-" + cursorSecond.getString(cursorSecond.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_PENGGUNA_EMAIL));
                    nameUser = cursorSecond.getString(cursorSecond.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_PENGGUNA_NAME));
                }
                finally {
                    cursorSecond.close();
                }
                listViewSmalls.add(new ListViewSmall(id, nameUser, status, R.drawable.userppempty));
            }
        }finally {
            cursor.close();
        }
        return listViewSmalls;
    }

    @Override
    protected void onResume() {
        super.onResume();
        listViewSmalls.clear();
        listViewSmalls = getArraylist();
        lvMember = findViewById(R.id.lvMember);
        adapter = new ListViewSmallAdapter(this, listViewSmalls);
        lvMember.setAdapter(adapter);
    }
}
