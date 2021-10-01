package org.d3ifcool.counterpulsa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class RekapViewItem extends AppCompatActivity {

    // DECLARATION VARIABLE
    private TextView
            rekap_item_no_hp,
            rekap_item_tanggal,
            rekap_item_waktu,
            rekap_item_nama_service,
            rekap_item_status_transaksi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rekap_view_item);

        // INITIALIZATION VARIABLE
        Intent intent = getIntent();
        RekapClass data = (RekapClass) intent.getSerializableExtra("rekapViewItem");
        this.rekap_item_no_hp = (TextView) findViewById(R.id.rekap_item_no_hp);
        this.rekap_item_tanggal = (TextView) findViewById(R.id.rekap_item_tanggal);
        this.rekap_item_waktu = (TextView) findViewById(R.id.rekap_item_waktu);
        this.rekap_item_nama_service = (TextView) findViewById(R.id.rekap_item_nama_service);
        this.rekap_item_status_transaksi = (TextView) findViewById(R.id.rekap_item_status_transaksi);
        this.rekap_item_no_hp.setText(data.getNo_hp());
        this.rekap_item_tanggal.setText(data.getTanggal());
        this.rekap_item_waktu.setText(data.getWaktu());
        this.rekap_item_nama_service.setText(data.getServiceClass().getNama_service());
        String status = "";
        if(data.getStatus_transaksi() == 1)
            status = "Pending";
        else if(data.getStatus_transaksi() == 2)
            status = "Success";
        else
            status = "Error";
        this.rekap_item_status_transaksi.setText(status);
    }
}
