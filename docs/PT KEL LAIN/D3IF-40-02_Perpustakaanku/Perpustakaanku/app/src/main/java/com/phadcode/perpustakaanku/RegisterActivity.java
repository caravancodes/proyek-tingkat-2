package com.phadcode.perpustakaanku;

import com.phadcode.perpustakaanku.data.*;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RegisterActivity extends AppCompatActivity {
    EditText etEmail, etNama, etPassword, etPasswordUlang;
    Button btnLanjut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setTitle("Daftar Baru");
//        getSupportActionBar().hide();

        etEmail = (EditText) findViewById(R.id.etEmail);
        etNama = (EditText) findViewById(R.id.etNama);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etPasswordUlang = (EditText) findViewById(R.id.etPasswordUlang);

        btnLanjut = (Button) findViewById(R.id.btnLanjut);
        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, RegisterLanjutanActivity.class);
                intent.putExtra("email", etEmail.getText().toString().trim());
                intent.putExtra("nama", etNama.getText().toString().trim());
                intent.putExtra("password", etPassword.getText().toString().trim());
                startActivity(intent);
            }
        });
    }
}
