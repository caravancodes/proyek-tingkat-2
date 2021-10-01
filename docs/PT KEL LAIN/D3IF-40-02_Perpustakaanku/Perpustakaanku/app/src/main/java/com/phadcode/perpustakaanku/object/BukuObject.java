package com.phadcode.perpustakaanku.object;

import java.io.Serializable;

/**
 * Created by Hudio Hizari on 4/22/2018.
 */

public class BukuObject implements Serializable{
    private String id, gambarBuku, judulBuku, deskripsiBuku, penulisBuku, penerbitBuku, jumlahBuku, terpinjam, perpus;

    public BukuObject(){}

    public BukuObject(String id, String gambarBuku, String judulBuku, String deskripsiBuku, String penulisBuku, String penerbitBuku, String jumlahBuku, String terpinjam, String perpus) {
        this.id = id;
        this.gambarBuku = gambarBuku;
        this.judulBuku = judulBuku;
        this.deskripsiBuku = deskripsiBuku;
        this.penulisBuku = penulisBuku;
        this.penerbitBuku = penerbitBuku;
        this.jumlahBuku = jumlahBuku;
        this.terpinjam = terpinjam;
        this.perpus = perpus;
    }

    public String getId() {
        return id;
    }
    public String getGambarBuku() {
        return gambarBuku;
    }
    public String getJudulBuku() {
        return judulBuku;
    }
    public String getDeskripsiBuku() {
        return deskripsiBuku;
    }
    public String getPenulisBuku() {
        return penulisBuku;
    }
    public String getPenerbitBuku() {
        return penerbitBuku;
    }
    public String getJumlahBuku() {
        return jumlahBuku;
    }
    public String getTerpinjam() {
        return terpinjam;
    }
    public String getPerpus() {
        return perpus;
    }
}
