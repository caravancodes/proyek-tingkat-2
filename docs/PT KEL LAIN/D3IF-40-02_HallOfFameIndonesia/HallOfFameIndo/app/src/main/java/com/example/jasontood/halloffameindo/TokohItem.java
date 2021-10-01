package com.example.jasontood.halloffameindo;

/**
 * Created by Jason Tood on 2/24/2018.
 */

public class TokohItem {
    String nama,deskripsi,tgl_lahir,tempat_lahir;
    int icon;

    public TokohItem(String nama, String deskripsi, String tgl_lahir, String tempat_lahir, int icon) {
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.tgl_lahir = tgl_lahir;
        this.tempat_lahir = tempat_lahir;
        this.icon = icon;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getTgl_lahir() {
        return tgl_lahir;
    }

    public void setTgl_lahir(String tgl_lahir) {
        this.tgl_lahir = tgl_lahir;
    }

    public String getTempat_lahir() {
        return tempat_lahir;
    }

    public void setTempat_lahir(String tempat_lahir) {
        this.tempat_lahir = tempat_lahir;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
