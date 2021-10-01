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

public class BeliSaldoActivity extends AppCompatActivity {

    // DECLARATION VARIABLE
    private Spinner saldo_paket;
    private Button beli_saldo;
    private ImageView logout;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beli_saldo);

        // INITIALIZATION VARIABLE
        this.saldo_paket = (Spinner) findViewById(R.id.saldo_paket);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.saldo_paket_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.saldo_paket.setAdapter(adapter);
        this.beli_saldo = (Button) findViewById(R.id.beli_saldo);
        this.database = new Database(this);
        this.logout = (ImageView) findViewById(R.id.logout_id);

        // EVENT ONCLICK LISTENER
        this.beli_saldo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), R.string.label_msg_tagihan_saldo, Toast.LENGTH_SHORT).show();
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
