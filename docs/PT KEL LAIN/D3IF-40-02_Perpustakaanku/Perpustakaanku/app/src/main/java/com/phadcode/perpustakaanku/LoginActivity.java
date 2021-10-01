package com.phadcode.perpustakaanku;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.phadcode.perpustakaanku.data.PerpusContract;
import com.phadcode.perpustakaanku.helper.PerpusDatabaseHelper;
import com.phadcode.perpustakaanku.helper.Session;

public class LoginActivity extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private Toast toast;
    private Session session;
    private PerpusDatabaseHelper perpusDatabaseHelper;
    private Cursor cursor;
    private EditText etEmail, etPassword;
    private Button btnLogin, btnFacebook, btnGoogle;
    private TextView tvLupaPassword, tvRegister;
    private String email = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        toast = Toast.makeText(LoginActivity.this, null, Toast.LENGTH_SHORT);

        session = new Session(this);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("root").child("pengguna");

        perpusDatabaseHelper = new PerpusDatabaseHelper(this);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cekLogin();
            }
        });

        btnFacebook = (Button) findViewById(R.id.btnFacebook);
        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toast.setText("Login Facebook belum tersedia");
                toast.show();
            }
        });

        btnGoogle = (Button) findViewById(R.id.btnGoogle);
        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toast.setText("Login Google belum tersedia");
                toast.show();
            }
        });

        tvRegister = (TextView) findViewById(R.id.tvRegister);
        tvRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        tvLupaPassword = (TextView) findViewById(R.id.tvLupaPassword);
        tvLupaPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "Lupa password belum tersedia", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void cekLogin(){
        boolean tidakKetemu = true;
        cursor = perpusDatabaseHelper.selectUser();

        try {
            while (cursor.moveToNext()) {
                int indexEmail = cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_PENGGUNA_EMAIL);
                int indexPassword = cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_PENGGUNA_PASSWORD);
                if (etEmail.getText().toString().trim().equals(cursor.getString(indexEmail))) {
                    tidakKetemu = false;
                    if (etPassword.getText().toString().trim().equals(cursor.getString(indexPassword))) {
                        email = cursor.getString(indexEmail);
                        Toast.makeText(LoginActivity.this, "Selamat datang", Toast.LENGTH_SHORT).show();
                        logedIn();
                        break;
                    } else {
                        Toast.makeText(LoginActivity.this, "Kata sandi salah", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }finally {
            cursor.close();
        }
        if (tidakKetemu) {
            Toast.makeText(LoginActivity.this, "Email tidak ditemukan", Toast.LENGTH_SHORT).show();
        }
    }

    public void logedIn(){
        session.setSessionBoolean(Session.LOGED_IN, true);
        session.setSessionString("email", email);

        Intent cekIntent = getIntent();
        Bundle bundle = cekIntent.getExtras();
        if (bundle == null){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        finish();
    }
}
