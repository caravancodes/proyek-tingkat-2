package org.d3ifcool.counterpulsa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class RekapHarian extends AppCompatActivity {

    // DECLARATION VARIABLE
    private ListView harian_list;
    private ControllerRekap controllerRekap;
    private ImageView logout;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rekap_harian);

        // INITIALIZATION VARIABLE
        this.harian_list = (ListView) findViewById(R.id.harian_list);
        this.controllerRekap = new ControllerRekap();
        AdapterList<RekapClass> adapterList = new AdapterList<>(this, controllerRekap.getRekapHarian(), 3);
        this.harian_list.setAdapter(adapterList);
        this.database = new Database(this);
        this.logout = (ImageView) findViewById(R.id.logout_id);

        // EVENT ONCLICK LISTENER
        this.harian_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), RekapViewItem.class);
                intent.putExtra("rekapViewItem", controllerRekap.getRekapHarian().get(i));
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
