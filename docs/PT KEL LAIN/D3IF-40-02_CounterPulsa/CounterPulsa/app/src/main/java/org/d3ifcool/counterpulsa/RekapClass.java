package org.d3ifcool.counterpulsa;

import java.io.Serializable;

/**
 * Created by AKU CINTA DIA on 2/25/2018.
 */

public class RekapClass implements Serializable{

    // DECLARATION VARIABLE
    private int id_transaksi, status_transaksi;
    private String no_hp, tanggal, waktu;
    private ServiceClass serviceClass;

    // CONSTRUCTOR
    public RekapClass(int id_transaksi, int status_transaksi, String no_hp, String tanggal, String waktu, ServiceClass serviceClass) {
        this.id_transaksi = id_transaksi;
        this.status_transaksi = status_transaksi;
        this.no_hp = no_hp;
        this.tanggal = tanggal;
        this.waktu = waktu;
        this.serviceClass = serviceClass;
    }

    // GETTER AND SETTER METHOD
    public String getTanggal() {
        return tanggal;
    }

    public String getWaktu() {
        return waktu;
    }

    public int getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(int id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public int getStatus_transaksi() {
        return status_transaksi;
    }

    public void setStatus_transaksi(int status_transaksi) {
        this.status_transaksi = status_transaksi;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public ServiceClass getServiceClass() {
        return serviceClass;
    }

    public void setServiceClass(ServiceClass serviceClass) {
        this.serviceClass = serviceClass;
    }
}
