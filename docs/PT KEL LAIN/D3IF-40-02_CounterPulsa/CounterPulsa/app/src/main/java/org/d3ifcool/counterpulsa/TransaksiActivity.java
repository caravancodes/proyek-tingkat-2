package org.d3ifcool.counterpulsa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class TransaksiActivity extends AppCompatActivity {

    // DECLARATION VARIABEL
    private Spinner transaksi_service;
    private Button transaksi_beli;
    private ImageView logout;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);

        // INITIALIZATION VARIABLE
        this.transaksi_beli = (Button) findViewById(R.id.transaksi_beli);
        this.transaksi_service = (Spinner) findViewById(R.id.transaksi_service);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.service_list_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.transaksi_service.setAdapter(adapter);
        this.database = new Database(this);
        this.logout = (ImageView) findViewById(R.id.logout_id);

        // EVENT ONCLICK LISTENER
        this.transaksi_beli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), R.string.label_msg_transaksi_1, Toast.LENGTH_SHORT).show();
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
