package com.phadcode.perpustakaanku.object;

import java.io.Serializable;

/**
 * Created by Hudio Hizari on 4/25/2018.
 */

public class BookingObject implements Serializable {
    private String idBooking, tglBooking, statusBooking, emailPeminjam, idBuku;

    public static final String BOOKING_STATUS_AKTIF = "Aktif";
    public static final String BOOKING_STATUS_TIDAK_AKTIF = "Tidak Aktif";

    public BookingObject() {
    }

    public BookingObject(String idBooking, String tglBooking, String statusBooking, String emailPeminjam, String idBuku) {
        this.idBooking = idBooking;
        this.tglBooking = tglBooking;
        this.statusBooking = statusBooking;
        this.emailPeminjam = emailPeminjam;
        this.idBuku = idBuku;
    }

    public String getIdBooking() {
        return idBooking;
    }
    public String getTglBooking() {
        return tglBooking;
    }
    public String getstatusBooking() {
        return statusBooking;
    }
    public String getEmailPeminjam() {
        return emailPeminjam;
    }
    public String getIdBuku() {
        return idBuku;
    }
}
