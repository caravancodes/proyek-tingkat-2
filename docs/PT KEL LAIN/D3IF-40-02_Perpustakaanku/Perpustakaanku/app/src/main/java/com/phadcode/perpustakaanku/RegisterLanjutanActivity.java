package com.phadcode.perpustakaanku;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.phadcode.perpustakaanku.object.PenggunaObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RegisterLanjutanActivity extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private PenggunaObject penggunaObject;
    private EditText etTglLahir, etAlamat, etNoHP;
    private Spinner spKelamin;
    private Button btnDaftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_lanjutan);

        getSupportActionBar().setTitle("Daftar Baru");
//        getSupportActionBar().hide();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("root");

        etTglLahir = (EditText) findViewById(R.id.etTglLahir);
        etAlamat = (EditText) findViewById(R.id.etAlamat);
        etNoHP = (EditText) findViewById(R.id.etNoHP);

        //Spinner DropDown
        spKelamin = (Spinner) findViewById(R.id.spKelamin);
        String[] items = new String[]{"Tidak Diketahui", "Laki - laki", "Perempuan"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item_register, items);
        spKelamin.setAdapter(adapter);
        //End Spinner DropDown

        //DatePicker
        etTglLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(RegisterLanjutanActivity.this, date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        //End DatePicker

        btnDaftar = (Button) findViewById(R.id.btnDaftar);
        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tambahUser()){
                    Toast.makeText(RegisterLanjutanActivity.this, "Berhasil daftar", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterLanjutanActivity.this, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(RegisterLanjutanActivity.this, "Gagal Daftar", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    //DatePicker
    Calendar calendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);
            updateNilaiTglLahir();
        }
    };
    private void updateNilaiTglLahir() {
        String myFormat = "dd/MM/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        etTglLahir.setText(sdf.format(calendar.getTime()));
    }
    //End DatePicker

    public boolean tambahUser(){
        Intent cekIntent = getIntent();
        Bundle bundle = cekIntent.getExtras();

        penggunaObject = new PenggunaObject(
                bundle.getString("email"),
                etNoHP.getText().toString().trim(),
                "",
                "",
                bundle.getString("nama"),
                bundle.getString("password"),
                etTglLahir.getText().toString().trim(),
                spKelamin.getSelectedItem().toString().trim(),
                etAlamat.getText().toString().trim());

        databaseReference.child("pengguna").push().setValue(penggunaObject);

        return true;
    }
}
