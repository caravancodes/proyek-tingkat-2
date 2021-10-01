package com.phadcode.perpustakaanku;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.phadcode.perpustakaanku.data.PerpusContract;
import com.phadcode.perpustakaanku.helper.PerpusDatabaseHelper;
import com.phadcode.perpustakaanku.helper.Session;
import com.phadcode.perpustakaanku.listview.ListViewSingleRowWithImage;
import com.phadcode.perpustakaanku.listview.ListViewSingleRowWithImageAdapter;
import com.phadcode.perpustakaanku.listview.ListViewSmall;

import java.util.ArrayList;

public class KelolaBukuActivity extends AppCompatActivity {
    private PerpusDatabaseHelper perpusDatabaseHelper;
    private Cursor cursor;
    private Session session;

    private ListView lvBook;
    private TextView tvEmptyView;
    private FloatingActionButton fabTambahBuku;

    private ArrayList<ListViewSingleRowWithImage> listViewSingleRowWithImages;
    private ListViewSingleRowWithImageAdapter adapter;
    private String selectedId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelola_buku);getSupportActionBar().setTitle("Kelola Perpustakaan");

        getSupportActionBar().setTitle("Kelola Buku");

        session = new Session(this);

        perpusDatabaseHelper = new PerpusDatabaseHelper(this);
        cursor = perpusDatabaseHelper.selectOwnedBuku(session.getSessionString("owned_perpus"));
        System.out.println("ID Perpus : " + session.getSessionString("owned_perpus"));
        System.out.println("Cursos Count : " + cursor.getCount());

        listViewSingleRowWithImages = new ArrayList<>();
        try {
            while (cursor.moveToNext()){
                String id = cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_BUKU_ID));
                String name = cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_BUKU_JUDUL));
                listViewSingleRowWithImages.add(new ListViewSingleRowWithImage(id, name, R.drawable.bookempty));
            }
        }finally {
            cursor.close();
        }

        adapter = new ListViewSingleRowWithImageAdapter(this, listViewSingleRowWithImages);

        lvBook = (ListView) findViewById(R.id.lvBook);
        lvBook.setAdapter(adapter);
        registerForContextMenu(lvBook);

        tvEmptyView = (TextView) findViewById(R.id.tvEmptyView);
        lvBook.setEmptyView(tvEmptyView);

        fabTambahBuku = (FloatingActionButton) findViewById(R.id.fabTambahBuku);
        fabTambahBuku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(KelolaBukuActivity.this, TambahBukuActivity.class));
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.listview_menu, menu);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        this.selectedId = listViewSingleRowWithImages.get(info.position).getID();
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.edit) {
            Toast.makeText(KelolaBukuActivity.this, "Edit buku belum tersedia", Toast.LENGTH_LONG).show();
            return true;
        } else if (item.getItemId() == R.id.hapus) {
            Toast.makeText(KelolaBukuActivity.this, "Hapus buku belum tersedia", Toast.LENGTH_LONG).show();
            return true;
        } else {
            return super.onContextItemSelected(item);
        }
    }
}
