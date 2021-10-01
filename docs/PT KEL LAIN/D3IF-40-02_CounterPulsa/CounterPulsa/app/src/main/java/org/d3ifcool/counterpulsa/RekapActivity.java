package org.d3ifcool.counterpulsa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class RekapActivity extends AppCompatActivity {

    // DECLARATION VARIABLE
    private LinearLayout rekap_harian, rekap_bulanan;
    private ImageView logout;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rekap);

        // INITIALIZATION VARIABLE
        this.rekap_harian = (LinearLayout) findViewById(R.id.rekap_harian);
        this.rekap_bulanan = (LinearLayout) findViewById(R.id.rekap_bulanan);
        this.database = new Database(this);
        this.logout = (ImageView) findViewById(R.id.logout_id);

        // EVENT ONCLICK LISTENER
        this.rekap_harian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RekapHarian.class);
                startActivity(intent);
            }
        });
        this.rekap_bulanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RekapBulanan.class);
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
}
