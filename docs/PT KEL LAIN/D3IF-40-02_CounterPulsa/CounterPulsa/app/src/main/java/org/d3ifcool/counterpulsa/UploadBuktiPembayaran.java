package org.d3ifcool.counterpulsa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class UploadBuktiPembayaran extends AppCompatActivity {

    // DECLARATION VARIABLE
    private ImageView image_bukti_pembayaran;
    private Button choose_bukti_pembayaran, upload_bukti_pembayaran;
    private ImageView logout;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_bukti_pembayaran);

        // INITIALIZATION VARIABLE
        Intent intent = getIntent();
        SaldoPembelian getData = (SaldoPembelian) intent.getSerializableExtra("dataSelected");
        this.image_bukti_pembayaran = (ImageView) findViewById(R.id.image_bukti_pembayaran);
        this.choose_bukti_pembayaran = (Button) findViewById(R.id.choose_bukti_pembayaran);
        this.upload_bukti_pembayaran = (Button) findViewById(R.id.upload_bukti_pembayaran);
        this.database = new Database(this);
        this.logout = (ImageView) findViewById(R.id.logout_id);

        // EVENT ONCLICK LISTENER
        this.choose_bukti_pembayaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_bukti_pembayaran.setVisibility(View.VISIBLE);
            }
        });
        this.upload_bukti_pembayaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), R.string.label_msg_saldo_bukti_1, Toast.LENGTH_SHORT).show();
                image_bukti_pembayaran.setVisibility(View.GONE);
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
