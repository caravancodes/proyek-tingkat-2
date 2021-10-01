package com.phadcode.perpustakaanku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.phadcode.perpustakaanku.listview.ListViewSingleRowWithImage;
import com.phadcode.perpustakaanku.listview.ListViewSingleRowWithImageAdapter;

import java.util.ArrayList;

public class KelolaPerpusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelola_perpus);

        getSupportActionBar().setTitle("Kelola Perpustakaan");

        final Toast toast = Toast.makeText(KelolaPerpusActivity.this, null, Toast.LENGTH_LONG);

        final ArrayList<ListViewSingleRowWithImage> listViewSingleRowWithImages = new ArrayList<>();
        listViewSingleRowWithImages.add(new ListViewSingleRowWithImage("","Kelola Buku", R.drawable.kelola_buku));
        listViewSingleRowWithImages.add(new ListViewSingleRowWithImage("","Kelola Member", R.drawable.kelola_member));
        listViewSingleRowWithImages.add(new ListViewSingleRowWithImage("","Kelola Data Perpustakaan", R.drawable.kelola_perpus));
        listViewSingleRowWithImages.add(new ListViewSingleRowWithImage("","Hapus Perpustakaan", R.drawable.delete));

        ListViewSingleRowWithImageAdapter adapter = new ListViewSingleRowWithImageAdapter(this, listViewSingleRowWithImages);

        ListView lvKelola = (ListView) findViewById(R.id.lvKelola);
        lvKelola.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selected = ((TextView) view.findViewById(R.id.tvText)).getText().toString();
                if (selected == "Kelola Buku"){
                    startActivity(new Intent(KelolaPerpusActivity.this, KelolaBukuActivity.class));
                }
                else if (selected == "Kelola Member"){
                    startActivity(new Intent(KelolaPerpusActivity.this, KelolaMemberActivity.class));
                }
                else if (selected == "Kelola Data Perpustakaan"){
                    toast.setText("Kelola data Perpustakaan belum tersedia");
                    toast.show();
                }
                else if (selected == "Hapus Perpustakaan"){
                    toast.setText("Hapus perpustakaan belum tersedia");
                    toast.show();
                }
            }
        });

        lvKelola.setAdapter(adapter);
    }
}
