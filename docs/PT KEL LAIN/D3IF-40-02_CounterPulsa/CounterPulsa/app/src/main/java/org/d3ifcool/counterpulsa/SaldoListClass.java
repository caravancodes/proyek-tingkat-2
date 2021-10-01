package org.d3ifcool.counterpulsa;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by AKU CINTA DIA on 2/25/2018.
 */

public class SaldoListClass implements Serializable {

    // DECLARATION VARIABLE
    private int id_list_saldo, harga_paket, status_paket;
    private String nama_paket;

    // CONSTRUCTOR
    public SaldoListClass(int id_list_saldo, int harga_paket, int status_paket, String nama_paket) {
        this.id_list_saldo = id_list_saldo;
        this.harga_paket = harga_paket;
        this.status_paket = status_paket;
        this.nama_paket = nama_paket;
    }

    // GETTER AND SETTER METHOD
    public int getId_list_saldo() {
        return id_list_saldo;
    }

    public void setId_list_saldo(int id_list_saldo) {
        this.id_list_saldo = id_list_saldo;
    }

    public int getHarga_paket() {
        return harga_paket;
    }

    public void setHarga_paket(int harga_paket) {
        this.harga_paket = harga_paket;
    }

    public int getStatus_paket() {
        return status_paket;
    }

    public void setStatus_paket(int status_paket) {
        this.status_paket = status_paket;
    }

    public String getNama_paket() {
        return nama_paket;
    }

    public void setNama_paket(String nama_paket) {
        this.nama_paket = nama_paket;
    }
}
