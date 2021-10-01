package org.d3ifcool.counterpulsa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class RekapDateItem extends AppCompatActivity {

    // DECLARATION VARIABLE
    private ListView rekap_date_item_list;
    private ControllerRekap controllerRekap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rekap_date_item);

        // INITIALIZATION VARIABLE
        Intent intent = getIntent();
        final RekapClass data = (RekapClass) intent.getSerializableExtra("rekapDateItem");
        this.rekap_date_item_list = (ListView) findViewById(R.id.rekap_date_item_list);
        this.controllerRekap = new ControllerRekap();
        AdapterList<RekapClass> adapterList = new AdapterList<>(this, this.controllerRekap.getRekapByDate(data.getTanggal()), 6);
        this.rekap_date_item_list.setAdapter(adapterList);

        // EVENT ONCLICK LISTENER
        this.rekap_date_item_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent1 = new Intent(getApplicationContext(), RekapViewItem.class);
                intent1.putExtra("rekapViewItem", controllerRekap.getRekapByDate(data.getTanggal()).get(i));
                startActivity(intent1);
            }
        });
    }
}
