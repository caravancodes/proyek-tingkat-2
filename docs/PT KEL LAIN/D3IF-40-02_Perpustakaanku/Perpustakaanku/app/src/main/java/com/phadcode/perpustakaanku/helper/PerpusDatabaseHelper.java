package com.phadcode.perpustakaanku.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.phadcode.perpustakaanku.LoginActivity;
import com.phadcode.perpustakaanku.R;
import com.phadcode.perpustakaanku.data.PerpusDbHelper;
import com.phadcode.perpustakaanku.data.PerpusContract;
import com.phadcode.perpustakaanku.listview.ListViewSingleRowWithImageAdapter;
import com.phadcode.perpustakaanku.listview.ListViewSmall;
import com.phadcode.perpustakaanku.object.PenggunaObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hudio Hizari on 4/10/2018.
 */

public class PerpusDatabaseHelper {
    private PerpusDbHelper dbHelper;

    public PerpusDatabaseHelper(Context context) {
        dbHelper = new PerpusDbHelper(context);
    }

    //User
    public void insertUser(ContentValues values){
        SQLiteDatabase dbWrite = dbHelper.getWritableDatabase();
        dbWrite.insert(PerpusContract.PerpusEntry.TABLE_USER, null, values);
    }
    public Cursor selectUser(){
        SQLiteDatabase dbRead = dbHelper.getReadableDatabase();

        String[] projection = {
                PerpusContract.PerpusEntry.COLUMN_PENGGUNA_EMAIL,
                PerpusContract.PerpusEntry.COLUMN_PENGGUNA_HANDPHONE,
                PerpusContract.PerpusEntry.COLUMN_PENGGUNA_PROFIL_PICTURE,
                PerpusContract.PerpusEntry.COLUMN_PENGGUNA_BACKGROUND_PICTURE,
                PerpusContract.PerpusEntry.COLUMN_PENGGUNA_NAME,
                PerpusContract.PerpusEntry.COLUMN_PENGGUNA_PASSWORD,
                PerpusContract.PerpusEntry.COLUMN_PENGGUNA_DATE_OF_BIRTH,
                PerpusContract.PerpusEntry.COLUMN_PENGGUNA_GENDER,
                PerpusContract.PerpusEntry.COLUMN_PENGGUNA_ADDRESS
        };

        return dbRead.query(
                PerpusContract.PerpusEntry.TABLE_USER,
                projection,
                null,
                null,
                null,
                null,
                null
        );
    }
    public Cursor selectSingleUser(String email) {
        SQLiteDatabase dbRead = dbHelper.getReadableDatabase();
        return dbRead.rawQuery("SELECT * FROM " + PerpusContract.PerpusEntry.TABLE_USER + " WHERE " + PerpusContract.PerpusEntry.COLUMN_PENGGUNA_EMAIL + " = '"+email+"'", null);
    }
    //End User

    //Perpus
    public void insertPerpus(ContentValues values){
        SQLiteDatabase dbWrite = dbHelper.getWritableDatabase();
        dbWrite.insert(PerpusContract.PerpusEntry.TABLE_PERPUS, null, values);
    }
    public Cursor selectPerpus(){
        SQLiteDatabase dbRead = dbHelper.getReadableDatabase();

        String[] projection = {
                PerpusContract.PerpusEntry.PERPUS_ID,
                PerpusContract.PerpusEntry.COLUMN_PERPUS_EMAIL,
                PerpusContract.PerpusEntry.COLUMN_PERPUS_HANDPHONE,
                PerpusContract.PerpusEntry.COLUMN_PERPUS_PROFIL_PICTURE,
                PerpusContract.PerpusEntry.COLUMN_PERPUS_BACKGROUND_PICTURE,
                PerpusContract.PerpusEntry.COLUMN_PERPUS_NAME,
                PerpusContract.PerpusEntry.COLUMN_PERPUS_GPS,
                PerpusContract.PerpusEntry.COLUMN_PERPUS_ADDRESS,
                PerpusContract.PerpusEntry.COLUMN_PERPUS_OWNER,
                PerpusContract.PerpusEntry.COLUMN_PERPUS_ADMIN,
                PerpusContract.PerpusEntry.COLUMN_PERPUS_MEMBERS,
                PerpusContract.PerpusEntry.COLUMN_PERPUS_PENDING_MEMBERS,
                PerpusContract.PerpusEntry.COLUMN_PERPUS_BOOK_CATEGORIES,
                PerpusContract.PerpusEntry.COLUMN_PERPUS_STATUS
        };

        return dbRead.query(
                PerpusContract.PerpusEntry.TABLE_PERPUS,
                projection,
                null,
                null,
                null,
                null,
                null
        );
    }
    public Cursor selectSinglePerpus(String ID) {
        SQLiteDatabase dbRead = dbHelper.getReadableDatabase();
        return dbRead.rawQuery("SELECT * FROM " + PerpusContract.PerpusEntry.TABLE_PERPUS + " WHERE " + PerpusContract.PerpusEntry.PERPUS_ID + " = '"+ID+"'", null);
    }
    public Cursor selectUserOwnedPerpus(String email) {
        SQLiteDatabase dbRead = dbHelper.getReadableDatabase();
        return dbRead.rawQuery("SELECT " + PerpusContract.PerpusEntry.PERPUS_ID + ", " + PerpusContract.PerpusEntry.COLUMN_PERPUS_OWNER + " FROM " + PerpusContract.PerpusEntry.TABLE_PERPUS + " WHERE " + PerpusContract.PerpusEntry.COLUMN_PERPUS_OWNER + " = '"+email+"'", null);
    }
    public Cursor selectPerpusOwner(String emailPengguna, String idPerpus) {
        SQLiteDatabase dbRead = dbHelper.getReadableDatabase();
        return dbRead.rawQuery("SELECT * FROM " + PerpusContract.PerpusEntry.TABLE_PERPUS + " WHERE " + PerpusContract.PerpusEntry.COLUMN_PERPUS_OWNER + " = '"+emailPengguna+"' and " + PerpusContract.PerpusEntry.PERPUS_ID + " = '"+idPerpus+"'", null);
    }
    //End Perpus

    //Buku
    public void insertBuku(ContentValues values){
        SQLiteDatabase dbWrite = dbHelper.getWritableDatabase();
        dbWrite.insert(PerpusContract.PerpusEntry.TABLE_BUKU, null, values);
    }
    public Cursor selectSingleBuku(String bukuID) {
        SQLiteDatabase dbRead = dbHelper.getReadableDatabase();
        return dbRead.rawQuery("SELECT * FROM " + PerpusContract.PerpusEntry.TABLE_BUKU + " WHERE " + PerpusContract.PerpusEntry.COLUMN_BUKU_ID + " = '"+bukuID+"'", null);
    }
    public Cursor selectOwnedBuku(String perpusID) {
        SQLiteDatabase dbRead = dbHelper.getReadableDatabase();
        return dbRead.rawQuery("SELECT * FROM " + PerpusContract.PerpusEntry.TABLE_BUKU + " WHERE " + PerpusContract.PerpusEntry.COLUMN_BUKU_PERPUS + " = '"+perpusID+"'", null);
    }
    public void updateBuku(ContentValues values, String bukuID){
        SQLiteDatabase dbWrite = dbHelper.getWritableDatabase();
        dbWrite.update(PerpusContract.PerpusEntry.TABLE_BUKU, values, PerpusContract.PerpusEntry.COLUMN_BUKU_ID + " = '" + bukuID + "'", null);
    }
    //End Buku

    //Booking
    public void insertBooking(ContentValues values){
        SQLiteDatabase dbWrite = dbHelper.getWritableDatabase();
        dbWrite.insert(PerpusContract.PerpusEntry.TABLE_BOOKING, null, values);
    }
    public Cursor selectSingleBooking(String bookingID) {
        SQLiteDatabase dbRead = dbHelper.getReadableDatabase();
        return dbRead.rawQuery("SELECT * FROM " + PerpusContract.PerpusEntry.TABLE_BOOKING + " WHERE " + PerpusContract.PerpusEntry.COLUMN_BOOKING_ID + " = '"+bookingID+"'", null);
    }
    public Cursor selectOwnedBooking(String emailPengguna) {
        SQLiteDatabase dbRead = dbHelper.getReadableDatabase();
        return dbRead.rawQuery("SELECT * FROM " + PerpusContract.PerpusEntry.TABLE_BOOKING + " WHERE " + PerpusContract.PerpusEntry.COLUMN_BOOKING_EMAIL_PEMINJAM + " = '"+emailPengguna+"'", null);
    }
    //End Booking

    //Keanggotaan
    public void insertKeanggotaan(ContentValues values){
        SQLiteDatabase dbWrite = dbHelper.getWritableDatabase();
        dbWrite.insert(PerpusContract.PerpusEntry.TABLE_KEANGGOTAAN, null, values);
    }
    public Cursor selectKeanggotaan() {
        SQLiteDatabase dbRead = dbHelper.getReadableDatabase();
        return dbRead.rawQuery("SELECT * FROM " + PerpusContract.PerpusEntry.TABLE_KEANGGOTAAN, null);
    }
    public Cursor selectSingleKeanggotaan(String idKeanggotaan) {
        SQLiteDatabase dbRead = dbHelper.getReadableDatabase();
        return dbRead.rawQuery("SELECT * FROM " + PerpusContract.PerpusEntry.TABLE_KEANGGOTAAN + " WHERE " + PerpusContract.PerpusEntry.COLUMN_KEANGGOTAAN_ID + " = '"+idKeanggotaan+"'", null);
    }
    public Cursor selectSinglePerpusKeanggotaan(String emailPengguna, String idPerpus) {
        SQLiteDatabase dbRead = dbHelper.getReadableDatabase();
        return dbRead.rawQuery("SELECT * FROM " + PerpusContract.PerpusEntry.TABLE_KEANGGOTAAN + " WHERE " + PerpusContract.PerpusEntry.COLUMN_KEANGGOTAAN_EMAIL_PENGGUNA + " = '"+emailPengguna+"' and " + PerpusContract.PerpusEntry.COLUMN_KEANGGOTAAN_PERPUS_ID + " = '"+idPerpus+"'", null);
    }
    public Cursor selectPerpusKeanggotaan(String idPerpus) {
        SQLiteDatabase dbRead = dbHelper.getReadableDatabase();
        return dbRead.rawQuery("SELECT * FROM " + PerpusContract.PerpusEntry.TABLE_KEANGGOTAAN + " WHERE " + PerpusContract.PerpusEntry.COLUMN_KEANGGOTAAN_PERPUS_ID + " = '"+idPerpus+"'", null);
    }
    public void updateKeanggotaan(ContentValues values, String keanggotaanID){
        SQLiteDatabase dbWrite = dbHelper.getWritableDatabase();
        dbWrite.update(PerpusContract.PerpusEntry.TABLE_KEANGGOTAAN, values, PerpusContract.PerpusEntry.COLUMN_KEANGGOTAAN_ID + " = '" + keanggotaanID + "'", null);
    }
    public void deleteKeanggotaan(String keanggotaanID){
        SQLiteDatabase dbWrite = dbHelper.getWritableDatabase();
        dbWrite.delete(PerpusContract.PerpusEntry.TABLE_KEANGGOTAAN, PerpusContract.PerpusEntry.COLUMN_KEANGGOTAAN_ID + " = '" + keanggotaanID + "'", null);
    }
    //End Keanggotaan
}
