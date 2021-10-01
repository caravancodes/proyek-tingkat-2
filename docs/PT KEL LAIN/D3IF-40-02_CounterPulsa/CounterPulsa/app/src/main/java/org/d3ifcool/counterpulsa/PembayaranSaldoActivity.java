package org.d3ifcool.counterpulsa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class PembayaranSaldoActivity extends AppCompatActivity {

    // DECLARATION VARIABLE
    private ControllerSaldoPembelian tagihan_data;
    private ListView tagihan_list;
    private ImageView logout;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran_saldo);

        // INITIALIZATION VARIABLE
        this.tagihan_list = (ListView) findViewById(R.id.tagihan_list);
        this.tagihan_data = new ControllerSaldoPembelian();
        AdapterList<SaldoPembelian> adapter = new AdapterList<>(this, this.tagihan_data.getSaldoPembelians(), 1);
        this.tagihan_list.setAdapter(adapter);
        this.database = new Database(this);
        this.logout = (ImageView) findViewById(R.id.logout_id);

        // EVENT ONCLICK LISTENER
        this.tagihan_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), UploadBuktiPembayaran.class);
                intent.putExtra("dataSelected", tagihan_data.getSaldoPembelians().get(i));
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
