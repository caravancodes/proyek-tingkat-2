package com.phadcode.perpustakaanku;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Login extends AppCompatActivity {
    Button btnLogin, btnFacebook, btnGoogle;
    TextView tvLupaPassword, tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, MainActivity.class);
                intent.putExtra("username", "hhizari");
                intent.putExtra("email", "hhizari@gmail.com");
                startActivity(intent);
            }
        });

        btnFacebook = (Button) findViewById(R.id.btnFacebook);
        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, MainActivity.class);
                intent.putExtra("username", "hhizari");
                intent.putExtra("email", "hhizari@gmail.com");
                startActivity(intent);
            }
        });

        btnGoogle = (Button) findViewById(R.id.btnGoogle);
        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, MainActivity.class);
                intent.putExtra("username", "hhizari");
                intent.putExtra("email", "hhizari@gmail.com");
                startActivity(intent);
            }
        });

        tvRegister= (TextView) findViewById(R.id.tvRegister);
        tvRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

    }
}
