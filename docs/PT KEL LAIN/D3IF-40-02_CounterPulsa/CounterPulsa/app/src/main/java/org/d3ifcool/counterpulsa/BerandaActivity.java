package org.d3ifcool.counterpulsa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class BerandaActivity extends AppCompatActivity {

    // DECLARATION VARIABLE
    private ListView beranda_list;
    private ControllerBeranda controllerBeranda;
    private ImageView logout;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);

        // INITIALIZATION VARIABLE
        this.beranda_list = (ListView) findViewById(R.id.beranda_list);
        this.controllerBeranda = new ControllerBeranda();
        AdapterList<BerandaClass> adapterList = new AdapterList<>(this, this.controllerBeranda.getBerandaClasses(), 5);
        this.beranda_list.setAdapter(adapterList);
        this.database = new Database(this);
        this.logout = (ImageView) findViewById(R.id.logout_id);

        // EVENT ONCLICK LISTENER
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
