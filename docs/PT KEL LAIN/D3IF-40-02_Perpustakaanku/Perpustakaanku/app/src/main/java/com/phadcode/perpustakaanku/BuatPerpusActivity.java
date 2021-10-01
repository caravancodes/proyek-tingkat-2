package com.phadcode.perpustakaanku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BuatPerpusActivity extends AppCompatActivity {
    TextView tvNama, tvAlamat;
    EditText etNama, etAlamat;
    Button btnLanjut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_perpus);

        setTitle("Buat Perpustakaan");

        tvNama = (TextView) findViewById(R.id.tvNama);
        tvNama.setVisibility(View.GONE);

        etNama = (EditText) findViewById(R.id.etNama);
        etNama.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus){
                    etNama.setHint("");
                    tvNama.setVisibility(View.VISIBLE);
                }
                else{
                    etNama.setHint("Nama Perpustakaan");
                }
            }
        });

        tvAlamat = (TextView) findViewById(R.id.tvAlamat);
        tvAlamat.setVisibility(View.GONE);

        etAlamat = (EditText) findViewById(R.id.etAlamat);
        etAlamat.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus){
                    etAlamat.setHint("");
                    tvAlamat.setVisibility(View.VISIBLE);
                }
                else{
                    etAlamat.setHint("Alamat Perpustakaan");
                }
            }
        });

        btnLanjut = (Button) findViewById(R.id.btnLanjut);
        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuatPerpusActivity.this, BuatPerpusLanjutanActivity.class);
                intent.putExtra("nama", etNama.getText().toString().trim());
                intent.putExtra("alamat", etAlamat.getText().toString().trim());
                startActivity(intent);
            }
        });
    }
}
