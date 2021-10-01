package org.d3ifcool.counterpulsa;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // DECLARATION VARIABLE
    private LinearLayout saldo_menu;
    private LinearLayout transaksi_menu;
    private LinearLayout service_menu;
    private LinearLayout rekap_menu;
    private LinearLayout beranda_menu;
    private LinearLayout profil_menu;
    private ImageView logout;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // INITIALIZATION VARIABLE
        this.database = new Database(this);
        this.saldo_menu = (LinearLayout) findViewById(R.id.saldo_id);
        this.transaksi_menu = (LinearLayout) findViewById(R.id.transaksi_id);
        this.service_menu = (LinearLayout) findViewById(R.id.service_id);
        this.rekap_menu = (LinearLayout) findViewById(R.id.rekap_id);
        this.beranda_menu = (LinearLayout) findViewById(R.id.beranda_id);
        this.profil_menu = (LinearLayout) findViewById(R.id.akun_id);
        this.logout = (ImageView) findViewById(R.id.logout_id);

        // CHECK FIRST IF AKUN IS ALREADY ON SYSTEM
        Cursor checkSession = this.database.getAllData("Select * from `user_table`");
        if(checkSession.getCount() != 0){
            // EVENT ONCLICK LISTENER
            this.saldo_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), SaldoActivity.class);
                    startActivity(intent);
                }
            });
            this.transaksi_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), TransaksiActivity.class);
                    startActivity(intent);
                }
            });
            this.service_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), ServiceActivity.class);
                    startActivity(intent);
                }
            });
            this.rekap_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), RekapActivity.class);
                    startActivity(intent);
                }
            });
            this.beranda_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), BerandaActivity.class);
                    startActivity(intent);
                }
            });
            this.profil_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                    startActivity(intent);
                }
            });
            this.logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(database.deleteData("user_table", "NO_KTP = ?", new String[] {"1"}) == true){
                        Toast.makeText(getApplicationContext(), R.string.label_msg_logout_1, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                    }
                }
            });
        }
        else{
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }
    }
}
