package org.d3ifcool.counterpulsa;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by AKU CINTA DIA on 2/25/2018.
 */

public class ControllerSaldoPembelian {

    // DECLARATION VARIABLE
    private ArrayList<SaldoPembelian> saldoPembelians;

    // CONSTRUCTOR
    public ControllerSaldoPembelian() {
        SaldoListClass[] saldoListClasses = new SaldoListClass[]{
                new SaldoListClass(1, 50000, 1, "1"),
                new SaldoListClass(2, 100000, 1, "2")
        };

        Date tanggal, waktu;
        tanggal = new Date();
        waktu = new Date();

        String currentTanggal, currentWaktu;
        currentTanggal = tanggal.getYear() + "/" + tanggal.getMonth() + "/" + tanggal.getDay();
        currentWaktu = waktu.getHours() + ":" + waktu.getMinutes() + ":" + waktu.getSeconds();
        this.saldoPembelians = new ArrayList<>();
        this.beliSaldo(new SaldoPembelian(1, currentTanggal, currentWaktu, saldoListClasses[0]));
        this.beliSaldo(new SaldoPembelian(2, currentTanggal, currentWaktu, saldoListClasses[1]));
    }

    // INSERT NEW PEMBELIAN SALDO
    public boolean beliSaldo(SaldoPembelian data){
        this.saldoPembelians.add(data);
        return true;
    }

    // GETTER METHOD
    public ArrayList<SaldoPembelian> getSaldoPembelians() {
        return saldoPembelians;
    }
}
