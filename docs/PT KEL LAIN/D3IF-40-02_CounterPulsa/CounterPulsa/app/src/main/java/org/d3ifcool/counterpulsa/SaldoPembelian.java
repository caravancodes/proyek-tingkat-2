package org.d3ifcool.counterpulsa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by AKU CINTA DIA on 2/25/2018.
 */

public class SaldoPembelian implements Serializable{

    // DECLARATION VARIABLE
    private int id_pembelian_saldo;
    private String tanggal, waktu;
    private SaldoListClass saldoListClasses;

    // CONSTRUCTOR
    public SaldoPembelian(int id_pembelian_saldo, String tanggal, String waktu, SaldoListClass saldoListClasses) {
        this.id_pembelian_saldo = id_pembelian_saldo;
        this.tanggal = tanggal;
        this.waktu = waktu;
        this.saldoListClasses = saldoListClasses;
    }

    // GETTER AND SETTER METHOD
    public int getId_pembelian_saldo() {
        return id_pembelian_saldo;
    }

    public void setId_pembelian_saldo(int id_pembelian_saldo) {
        this.id_pembelian_saldo = id_pembelian_saldo;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public SaldoListClass getSaldoListClasses() {
        return saldoListClasses;
    }

    public void setSaldoListClasses(SaldoListClass saldoListClasses) {
        this.saldoListClasses = saldoListClasses;
    }
}
