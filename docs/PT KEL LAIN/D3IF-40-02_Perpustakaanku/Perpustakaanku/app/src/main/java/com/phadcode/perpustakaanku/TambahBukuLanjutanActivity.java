package com.phadcode.perpustakaanku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.phadcode.perpustakaanku.helper.Session;
import com.phadcode.perpustakaanku.object.BukuObject;
import com.phadcode.perpustakaanku.object.PenggunaObject;

import java.util.Calendar;
import java.util.Date;

public class TambahBukuLanjutanActivity extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private EditText etPenulis, etPenerbit, etJumlahBuku;
    private Spinner spJenisPinjam;
    private Button btnSimpan;

    private Session session;
    private BukuObject bukuObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_buku_lanjutan);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("root");

        session = new Session(this);

        etPenulis = (EditText) findViewById(R.id.etPenulis);
        etPenerbit = (EditText) findViewById(R.id.etPenerbit);
        etJumlahBuku = (EditText) findViewById(R.id.etJumlahBuku);

        //Spinner DropDown
        spJenisPinjam = (Spinner) findViewById(R.id.spJenisPinjam);
        String[] items = new String[]{"Tidak Diketahui"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item_register, items);
        spJenisPinjam.setAdapter(adapter);
        //End Spinner DropDown

        btnSimpan = (Button) findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tambahBuku()){
                    Toast.makeText(TambahBukuLanjutanActivity.this, "Berhasil tambah buku", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(TambahBukuLanjutanActivity.this, KelolaBukuActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(TambahBukuLanjutanActivity.this, "Kesalahan tambah buku", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean tambahBuku(){
        Bundle bundle = getIntent().getExtras();
        Date c = Calendar.getInstance().getTime();

        bukuObject = new BukuObject(
                "|[BUKU]|" + c.toString() + "||" + session.getSessionString("owned_perpus") + "||",
                "",
                bundle.getString("judul"),
                bundle.getString("deskripsi"),
                etPenulis.getText().toString().trim(),
                etPenerbit.getText().toString().trim(),
                etJumlahBuku.getText().toString().trim(),
                "0",
                session.getSessionString("owned_perpus")
        );

        databaseReference.child("buku").push().setValue(bukuObject);

        return true;
    }
}
