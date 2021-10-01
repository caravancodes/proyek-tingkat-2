package com.phadcode.perpustakaanku;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class TambahBukuActivity extends AppCompatActivity {
//    private DatabaseReference databaseReference;
    private Button btnLanjut;
    private EditText etJudul, etDeskripsi;
//    private ImageView ivTambahFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_buku);

        getSupportActionBar().setTitle("Tambah Buku");

//        ivTambahFoto = (ImageView) findViewById(R.id.ivTambahFoto);
//        ivTambahFoto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                handleChooseImage(view);
//            }
//        });

        etJudul = (EditText) findViewById(R.id.etJudul);
        etDeskripsi = (EditText) findViewById(R.id.etDeskripsi);

        btnLanjut = (Button) findViewById(R.id.btnLanjut);
        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TambahBukuActivity.this, TambahBukuLanjutanActivity.class);
                intent.putExtra("judul", etJudul.getText().toString().trim());
                intent.putExtra("deskripsi", etDeskripsi.getText().toString().trim());
                startActivity(intent);
            }
        });

//        databaseReference = FirebaseDatabase.getInstance().getReference();
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Map<String, Object> updatedFlower = (Map<String, Object>) dataSnapshot.getValue();
//                Log.i("MainActivity", "updatedFlower = " + updatedFlower.toString());
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Log.i("MainActivity", "onCancelled");
//            }
//        });
    }

//    public void handleChooseImage(View view) {
//        Intent pickerPhotoIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(pickerPhotoIntent, 1);
//    }
//
//    public void handleInsertData(View view) {
//        //Insert data to Firebase Storage
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
//        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
//        switch (requestCode) {
//            case 0:
//                if(requestCode == RESULT_OK) {
//                    Log.i("MainActivity", "case 0");
//                }
//                break;
//            case 1:
//                if(resultCode == RESULT_OK) {
//                    Uri selectedImage = imageReturnedIntent.getData();
//                    Log.i("MainActivity", "selected Image = "+selectedImage);
////                    imageViewFlower.setImageURI(selectedImage);
//                }
//                break;
//        }
//    }
}
