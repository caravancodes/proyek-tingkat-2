package org.d3ifcool.counterpulsa;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by AKU CINTA DIA on 2/25/2018.
 */

public class AdapterList<T> extends BaseAdapter {
    // DECLARATION VARIBLE
    private ArrayList<T> dataList;
    private LayoutInflater layoutInflater;
    private int target;

    // MAIN CONSTRUCTOR
    public AdapterList(Activity activity, ArrayList<T> data, int target) {
        this.dataList = data;
        this.target = target;
        this.layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // GET SIZE OF STORAGE
    @Override
    public int getCount() {
        return this.dataList.size();
    }

    // GET OBJECT ITEM
    @Override
    public Object getItem(int i) {
        return i;
    }

    // GET POSITION ITEM
    @Override
    public long getItemId(int i) {
        return i;
    }

    // GET VIEW OF ITEM
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = this.layoutInflater.inflate(R.layout.tagihan_item, null);
        TextView title_tagihan, paket_tagihan;
        title_tagihan = (TextView) view.findViewById(R.id.title_tagihan);
        paket_tagihan = (TextView) view.findViewById(R.id.paket_tagihan);
        int status = R.string.label_empty;
        switch (target){
            case 1:
                SaldoPembelian dataSaldo = (SaldoPembelian) this.dataList.get(i);
                title_tagihan.setText(R.string.label_saldo_tagihan + dataSaldo.getTanggal());
                paket_tagihan.setText(R.string.label_saldo_paket + dataSaldo.getSaldoListClasses().getNama_paket() + "(Harga : " + dataSaldo.getSaldoListClasses().getHarga_paket() + ")");
                break;
            case 2:
                ServiceClass dataService = (ServiceClass) this.dataList.get(i);
                if(dataService.getStatus_service() == 1){
                    title_tagihan.setText(R.string.label_service_status_1);
                }
                else{
                    title_tagihan.setText(R.string.label_service_status_1);
                }
                paket_tagihan.setText(dataService.getNama_service());
                break;
            case 3:
                RekapClass dataRekapHarian = (RekapClass) this.dataList.get(i);
                 switch (dataRekapHarian.getStatus_transaksi()){
                     case 1:
                         title_tagihan.setText(dataRekapHarian.getTanggal() + " | Pending");
                         break;
                     case 2:
                         title_tagihan.setText(dataRekapHarian.getTanggal() + " | Success");
                         break;
                     case 3:
                         title_tagihan.setText(dataRekapHarian.getTanggal() + " | Error");
                         break;
                 }
                 paket_tagihan.setText(dataRekapHarian.getNo_hp());
                 break;
            case 4:
                RekapClass dataRekapBulanan = (RekapClass) this.dataList.get(i);
                title_tagihan.setText(R.string.label_rekap_tanggal);
                paket_tagihan.setText(dataRekapBulanan.getTanggal().split("/")[0] + "/" + dataRekapBulanan.getTanggal().split("/")[1]);
                break;
            case 5:
                view = this.layoutInflater.inflate(R.layout.beranda_item, null);
                title_tagihan = (TextView) view.findViewById(R.id.beranda_title);
                paket_tagihan = (TextView) view.findViewById(R.id.beranda_description);
                BerandaClass dataBeranda = (BerandaClass) this.dataList.get(i);
                title_tagihan.setText(dataBeranda.getBeranda_title());
                paket_tagihan.setText(dataBeranda.getBeranda_description());
                break;
            case 6:
                view = this.layoutInflater.inflate(R.layout.tagihan_item, null);
                title_tagihan = (TextView) view.findViewById(R.id.title_tagihan);
                paket_tagihan = (TextView) view.findViewById(R.id.paket_tagihan);
                RekapClass dataRekapDate = (RekapClass) this.dataList.get(i);
                switch (dataRekapDate.getStatus_transaksi()){
                    case 1:
                        title_tagihan.setText(dataRekapDate.getTanggal() + " | Pending");
                        break;
                    case 2:
                        title_tagihan.setText(dataRekapDate.getTanggal() + " | Success");
                        break;
                    case 3:
                        title_tagihan.setText(dataRekapDate.getTanggal() + " | Error");
                        break;
                }
                paket_tagihan.setText(dataRekapDate.getNo_hp());
                break;
        }
        return view;
    }
}
