package org.d3ifcool.counterpulsa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class OptionService extends AppCompatActivity {

    // DECLARATION VARIABLE
    private TextView service_nama, service_harga;
    private RadioButton service_ya, service_tidak;
    private Button update_service;
    private ImageView logout;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_service);

        // INITIALIZATION VARIABLE
        Intent intent = getIntent();
        ServiceClass data = (ServiceClass) intent.getSerializableExtra("serviceSelected");
        this.service_nama = (TextView)findViewById(R.id.service_nama);
        this.service_harga = (TextView)findViewById(R.id.service_harga);
        this.service_ya = (RadioButton) findViewById(R.id.service_ya);
        this.service_tidak = (RadioButton) findViewById(R.id.service_tidak);
        this.service_nama.setText("Pulsa" + data.getHarga_service());
        this.service_harga.setText(data.getNama_service());
        if(data.getStatus_service() == 1){
            this.service_ya.setChecked(true);
            this.service_tidak.setChecked(false);
        }
        else{
            this.service_ya.setChecked(false);
            this.service_tidak.setChecked(true);
        }
        this.database = new Database(this);
        this.logout = (ImageView) findViewById(R.id.logout_id);

        // EVENT ONCLICK LISTENER
        this.service_ya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setService();
            }
        });
        this.service_tidak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setService();
            }
        });
        this.update_service = (Button) findViewById(R.id.update_service);
        this.update_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), R.string.label_msg_service_1, Toast.LENGTH_SHORT).show();
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
    // SET SERVICE
    private void setService(){
        if(this.service_ya.isChecked()){
            this.service_ya.setChecked(true);
            this.service_tidak.setChecked(false);
        }
        else{
            this.service_tidak.setChecked(true);
            this.service_ya.setChecked(false);
        }
    }
}
