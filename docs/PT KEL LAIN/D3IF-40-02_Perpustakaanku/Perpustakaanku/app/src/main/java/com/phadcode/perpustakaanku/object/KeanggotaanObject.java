package com.phadcode.perpustakaanku.object;

/**
 * Created by Hudio Hizari on 4/26/2018.
 */

public class KeanggotaanObject {
    private String idKeanggotaan, statusKeanggotaan, emailPengguna, idPerpustakaan;

    public final static String STATUS_KEANGGOTAAN_DITERIMA = "Diterima";
    public final static String STATUS_KEANGGOTAAN_BELUM_DITERIMA = "Belum Diterima";
    public final static String STATUS_KEANGGOTAAN_TIDAK_DITERIMA = "Tidak Diterima";

    public KeanggotaanObject() {
    }

    public KeanggotaanObject(String idKeanggotaan, String statusKeanggotaan, String emailPengguna, String idPerpustakaan) {
        this.idKeanggotaan = idKeanggotaan;
        this.statusKeanggotaan = statusKeanggotaan;
        this.emailPengguna = emailPengguna;
        this.idPerpustakaan = idPerpustakaan;
    }

    public String getIdKeanggotaan() {
        return idKeanggotaan;
    }
    public String getStatusKeanggotaan() {
        return statusKeanggotaan;
    }
    public String getEmailPengguna() {
        return emailPengguna;
    }
    public String getIdPerpustakaan() {
        return idPerpustakaan;
    }
}
