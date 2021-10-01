package com.phadcode.perpustakaanku.helper;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.IBinder;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.phadcode.perpustakaanku.MainActivity;
import com.phadcode.perpustakaanku.data.PerpusContract;
import com.phadcode.perpustakaanku.listview.ListViewSmallAdapter;
import com.phadcode.perpustakaanku.object.BookingObject;
import com.phadcode.perpustakaanku.object.BukuObject;
import com.phadcode.perpustakaanku.object.KeanggotaanObject;
import com.phadcode.perpustakaanku.object.PenggunaObject;
import com.phadcode.perpustakaanku.object.PerpusObject;

import java.util.ArrayList;

/*
Catatan :
- change password masih belum terganti di SQLite
 */

public class DataFetcherFromJSON extends Service{
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ContentValues contentValues;
    private Cursor cursor;
    private PerpusDatabaseHelper perpusDatabaseHelper;

    private ArrayList<String> anggotaOnFirebase = new ArrayList<>();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        getFireBaseUser();
        getFireBasePerpus();
        getFirebaseBuku();
        getFirebaseBooking();
        getFirebaseKeanggotaan();
        return Service.START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private boolean isEmailExist(String email){
        perpusDatabaseHelper = new PerpusDatabaseHelper(this);
        cursor = perpusDatabaseHelper.selectSingleUser(email);
        try {
            if (cursor.getCount() == 0) {
                return false;
            }
            else{
                return true;
            }
        }finally {
            cursor.close();
        }
    }

    private void getFireBaseUser(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("root").child("pengguna");

        perpusDatabaseHelper = new PerpusDatabaseHelper(this);

        contentValues = new ContentValues();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    PenggunaObject penggunaObject = postSnapshot.getValue(PenggunaObject.class);

                    if (isEmailExist(penggunaObject.getEmail())){
                    }
                    else {
                        contentValues.put(PerpusContract.PerpusEntry.COLUMN_PENGGUNA_EMAIL, penggunaObject.getEmail());
                        contentValues.put(PerpusContract.PerpusEntry.COLUMN_PENGGUNA_FIREBASE_KEY, postSnapshot.getKey());
                        contentValues.put(PerpusContract.PerpusEntry.COLUMN_PENGGUNA_HANDPHONE, penggunaObject.getPhoneNumber());
                        contentValues.put(PerpusContract.PerpusEntry.COLUMN_PENGGUNA_PROFIL_PICTURE, penggunaObject.getProfilPicture());
                        contentValues.put(PerpusContract.PerpusEntry.COLUMN_PENGGUNA_BACKGROUND_PICTURE, penggunaObject.getBackgroundPicture());
                        contentValues.put(PerpusContract.PerpusEntry.COLUMN_PENGGUNA_NAME, penggunaObject.getName());
                        contentValues.put(PerpusContract.PerpusEntry.COLUMN_PENGGUNA_PASSWORD, penggunaObject.getPassword());
                        contentValues.put(PerpusContract.PerpusEntry.COLUMN_PENGGUNA_DATE_OF_BIRTH, penggunaObject.getDot());
                        contentValues.put(PerpusContract.PerpusEntry.COLUMN_PENGGUNA_GENDER, penggunaObject.getGender());
                        contentValues.put(PerpusContract.PerpusEntry.COLUMN_PENGGUNA_ADDRESS, penggunaObject.getAddress());

                        perpusDatabaseHelper.insertUser(contentValues);
                        contentValues.clear();
                        Log.v("Get User to SQLite", "ID = " + penggunaObject.getEmail());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("Error : ", "Failed to read user.", databaseError.toException());
            }
        });
    }

    private boolean isPerpusIDExist(String ID){
        perpusDatabaseHelper = new PerpusDatabaseHelper(this);
        cursor = perpusDatabaseHelper.selectSinglePerpus(ID);
        try {
            if (cursor.getCount() == 0) {
                return false;
            }
            else{
                return true;
            }
        }finally {
            cursor.close();
        }
    }

    private void getFireBasePerpus(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("root").child("perpustakaan");

        perpusDatabaseHelper = new PerpusDatabaseHelper(this);

        contentValues = new ContentValues();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    PerpusObject perpusObject = postSnapshot.getValue(PerpusObject.class);

                    if (isPerpusIDExist(perpusObject.getId())){
                    }
                    else {
                        contentValues.put(PerpusContract.PerpusEntry.PERPUS_ID, perpusObject.getId());
                        contentValues.put(PerpusContract.PerpusEntry.COLUMN_PERPUS_FIREBASE_KEY, postSnapshot.getKey());
                        contentValues.put(PerpusContract.PerpusEntry.COLUMN_PERPUS_EMAIL, perpusObject.getEmail());
                        contentValues.put(PerpusContract.PerpusEntry.COLUMN_PERPUS_HANDPHONE, perpusObject.getPhoneNumber());
                        contentValues.put(PerpusContract.PerpusEntry.COLUMN_PERPUS_PROFIL_PICTURE, perpusObject.getProfilPicture());
                        contentValues.put(PerpusContract.PerpusEntry.COLUMN_PERPUS_BACKGROUND_PICTURE, perpusObject.getBackgroundPicture());
                        contentValues.put(PerpusContract.PerpusEntry.COLUMN_PERPUS_NAME, perpusObject.getName());
                        contentValues.put(PerpusContract.PerpusEntry.COLUMN_PERPUS_GPS, perpusObject.getGps());
                        contentValues.put(PerpusContract.PerpusEntry.COLUMN_PERPUS_ADDRESS, perpusObject.getAddress());
                        contentValues.put(PerpusContract.PerpusEntry.COLUMN_PERPUS_OWNER, perpusObject.getOwner());
                        contentValues.put(PerpusContract.PerpusEntry.COLUMN_PERPUS_ADMIN, perpusObject.getAdmin());
                        contentValues.put(PerpusContract.PerpusEntry.COLUMN_PERPUS_MEMBERS, perpusObject.getMembers());
                        contentValues.put(PerpusContract.PerpusEntry.COLUMN_PERPUS_PENDING_MEMBERS, perpusObject.getPendingMembers());
                        contentValues.put(PerpusContract.PerpusEntry.COLUMN_PERPUS_BOOK_CATEGORIES, perpusObject.getBookCategory());
                        contentValues.put(PerpusContract.PerpusEntry.COLUMN_PERPUS_STATUS, perpusObject.getStatus());

                        perpusDatabaseHelper.insertPerpus(contentValues);
                        contentValues.clear();
                        Log.v("Get Perpus to SQLite", "ID = "+perpusObject.getId());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("Error : ", "Failed to read perpus.", databaseError.toException());
            }
        });
    }

    private boolean isBukuIdExist(String ID){
        perpusDatabaseHelper = new PerpusDatabaseHelper(this);
        cursor = perpusDatabaseHelper.selectSingleBuku(ID);
        try {
            if (cursor.getCount() == 0) {
                return false;
            }
            else{
                return true;
            }
        }finally {
            cursor.close();
        }
    }

    private void cekBukuRowData(String bukuID, BukuObject bukuObject, String key){
        Boolean isSame = true;
        perpusDatabaseHelper = new PerpusDatabaseHelper(this);
        cursor = perpusDatabaseHelper.selectSingleBuku(bukuID);
        cursor.moveToFirst();

        String id = cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_BUKU_ID));
        String gambar = cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_BUKU_GAMBAR));
        String deksripsi = cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_BUKU_DESKRIPSI));
        String penulis = cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_BUKU_PENULIS));
        String penerbit = cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_BUKU_PENERBIT));
        String jumlah = cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_BUKU_JUMLAH));
        String terpinjam = cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_BUKU_TERPINJAM));

        if (!gambar.equals(bukuObject.getGambarBuku())){ isSame = false; }
        else if (!deksripsi.equals(bukuObject.getDeskripsiBuku())){ isSame = false; }
        else if (!penulis.equals(bukuObject.getPenulisBuku())){ isSame = false; }
        else if (!penerbit.equals(bukuObject.getPenerbitBuku())){ isSame = false; }
        else if (!jumlah.equals(bukuObject.getJumlahBuku())){ isSame = false; }
        else if (!terpinjam.equals(bukuObject.getTerpinjam())){ isSame = false; }

        if (!isSame){
            contentValues = new ContentValues();
            contentValues.put(PerpusContract.PerpusEntry.COLUMN_BUKU_ID, bukuObject.getId());
            contentValues.put(PerpusContract.PerpusEntry.COLUMN_BUKU_FIREBASE_KEY , key);
            contentValues.put(PerpusContract.PerpusEntry.COLUMN_BUKU_GAMBAR, bukuObject.getGambarBuku());
            contentValues.put(PerpusContract.PerpusEntry.COLUMN_BUKU_JUDUL, bukuObject.getJudulBuku());
            contentValues.put(PerpusContract.PerpusEntry.COLUMN_BUKU_DESKRIPSI, bukuObject.getDeskripsiBuku());
            contentValues.put(PerpusContract.PerpusEntry.COLUMN_BUKU_PENULIS, bukuObject.getPenulisBuku());
            contentValues.put(PerpusContract.PerpusEntry.COLUMN_BUKU_PENERBIT, bukuObject.getPenerbitBuku());
            contentValues.put(PerpusContract.PerpusEntry.COLUMN_BUKU_JUMLAH, bukuObject.getJumlahBuku());
            contentValues.put(PerpusContract.PerpusEntry.COLUMN_BUKU_TERPINJAM, bukuObject.getTerpinjam());
            contentValues.put(PerpusContract.PerpusEntry.COLUMN_BUKU_PERPUS, bukuObject.getPerpus());
            perpusDatabaseHelper.updateBuku(contentValues, id);
            contentValues.clear();

            Log.v("Updating row data ID", id);
        }
    }

    private void getFirebaseBuku(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("root").child("buku");

        perpusDatabaseHelper = new PerpusDatabaseHelper(this);
        contentValues = new ContentValues();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    BukuObject bukuObject = postSnapshot.getValue(BukuObject.class);
                    String key = postSnapshot.getKey();

                    if (isBukuIdExist(bukuObject.getId())){
                        cekBukuRowData(bukuObject.getId(), bukuObject, key);
                    }
                    else{
                        contentValues.put(PerpusContract.PerpusEntry.COLUMN_BUKU_ID, bukuObject.getId());
                        contentValues.put(PerpusContract.PerpusEntry.COLUMN_BUKU_FIREBASE_KEY , key);
                        contentValues.put(PerpusContract.PerpusEntry.COLUMN_BUKU_GAMBAR, bukuObject.getGambarBuku());
                        contentValues.put(PerpusContract.PerpusEntry.COLUMN_BUKU_JUDUL, bukuObject.getJudulBuku());
                        contentValues.put(PerpusContract.PerpusEntry.COLUMN_BUKU_DESKRIPSI, bukuObject.getDeskripsiBuku());
                        contentValues.put(PerpusContract.PerpusEntry.COLUMN_BUKU_PENULIS, bukuObject.getPenulisBuku());
                        contentValues.put(PerpusContract.PerpusEntry.COLUMN_BUKU_PENERBIT, bukuObject.getPenerbitBuku());
                        contentValues.put(PerpusContract.PerpusEntry.COLUMN_BUKU_JUMLAH, bukuObject.getJumlahBuku());
                        contentValues.put(PerpusContract.PerpusEntry.COLUMN_BUKU_TERPINJAM, bukuObject.getTerpinjam());
                        contentValues.put(PerpusContract.PerpusEntry.COLUMN_BUKU_PERPUS, bukuObject.getPerpus());

                        perpusDatabaseHelper.insertBuku(contentValues);
                        contentValues.clear();
                        Log.v("Get Buku to SQLite", "ID = "+bukuObject.getId());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("Error : ", "Failed to read buku.", databaseError.toException());
            }
        });
    }

    private boolean isBookingIdExist(String ID){
        perpusDatabaseHelper = new PerpusDatabaseHelper(this);
        cursor = perpusDatabaseHelper.selectSingleBooking(ID);
        try {
            if (cursor.getCount() == 0) {
                return false;
            }
            else{
                return true;
            }
        }finally {
            cursor.close();
        }
    }

    private void getFirebaseBooking(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("root").child("booking");

        perpusDatabaseHelper = new PerpusDatabaseHelper(this);

        contentValues = new ContentValues();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    BookingObject bookingObject = postSnapshot.getValue(BookingObject.class);

                    if (isBookingIdExist(bookingObject.getIdBooking())){
                    }
                    else{
                        contentValues.put(PerpusContract.PerpusEntry.COLUMN_BOOKING_ID, bookingObject.getIdBooking());
                        contentValues.put(PerpusContract.PerpusEntry.COLUMN_BOOKING_FIREBASE_KEY, postSnapshot.getKey());
                        contentValues.put(PerpusContract.PerpusEntry.COLUMN_BOOKING_TANGGAL_BOOKING, bookingObject.getTglBooking());
                        contentValues.put(PerpusContract.PerpusEntry.COLUMN_BOOKING_STATUS, bookingObject.getstatusBooking());
                        contentValues.put(PerpusContract.PerpusEntry.COLUMN_BOOKING_EMAIL_PEMINJAM, bookingObject.getEmailPeminjam());
                        contentValues.put(PerpusContract.PerpusEntry.COLUMN_BOOKING_ID_BUKU, bookingObject.getIdBuku());

                        perpusDatabaseHelper.insertBooking(contentValues);
                        contentValues.clear();
                        Log.v("Get Booking to SQLite", "ID = "+bookingObject.getIdBooking());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("Error : ", "Failed to read booking.", databaseError.toException());
            }
        });
    }

    private boolean isKeanggotaanIdExist(String ID){
        perpusDatabaseHelper = new PerpusDatabaseHelper(this);
        cursor = perpusDatabaseHelper.selectSingleKeanggotaan(ID);
        try {
            if (cursor.getCount() == 0) {
                return false;
            }
            else{
                return true;
            }
        }finally {
            cursor.close();
        }
    }

    private void cekKeanggotaanRowData(String keanggotaanID, KeanggotaanObject keanggotaanObject, String key){
        Boolean isSame = true;
        perpusDatabaseHelper = new PerpusDatabaseHelper(this);
        cursor = perpusDatabaseHelper.selectSingleKeanggotaan(keanggotaanID);
        cursor.moveToFirst();

        String id = cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_KEANGGOTAAN_ID));
        String status = cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_KEANGGOTAAN_STATUS));
        String emailPengguna = cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_KEANGGOTAAN_EMAIL_PENGGUNA));
        String idPerpustakaan = cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_KEANGGOTAAN_PERPUS_ID));

        if (!status.equals(keanggotaanObject.getStatusKeanggotaan())){ isSame = false; }
        if (!emailPengguna.equals(keanggotaanObject.getEmailPengguna())){ isSame = false; }
        if (!idPerpustakaan.equals(keanggotaanObject.getIdPerpustakaan())){ isSame = false; }

        if (!isSame){
            contentValues = new ContentValues();
            contentValues.put(PerpusContract.PerpusEntry.COLUMN_KEANGGOTAAN_ID, keanggotaanObject.getIdKeanggotaan());
            contentValues.put(PerpusContract.PerpusEntry.COLUMN_KEANGGOTAAN_FIREBASE_KEY , key);
            contentValues.put(PerpusContract.PerpusEntry.COLUMN_KEANGGOTAAN_STATUS , keanggotaanObject.getStatusKeanggotaan());
            contentValues.put(PerpusContract.PerpusEntry.COLUMN_KEANGGOTAAN_EMAIL_PENGGUNA, keanggotaanObject.getEmailPengguna());
            contentValues.put(PerpusContract.PerpusEntry.COLUMN_KEANGGOTAAN_PERPUS_ID, keanggotaanObject.getIdPerpustakaan());

            perpusDatabaseHelper.updateKeanggotaan(contentValues, id);
            contentValues.clear();

            Log.v("Updating row data ID", id);
        }
    }

    private boolean isKeanggotaanRowEachDatabaseSame(){
        Cursor voidCursor = perpusDatabaseHelper.selectKeanggotaan();
        try {
            System.out.println(voidCursor.getCount() + " = " + anggotaOnFirebase.size());
            if (voidCursor.getCount() == anggotaOnFirebase.size()){
                return true;
            }
            else{
                return false;
            }
        }finally {
            voidCursor.close();
        }
    }

    private void getFirebaseKeanggotaan(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("root").child("keanggotaan");

        perpusDatabaseHelper = new PerpusDatabaseHelper(this);

        contentValues = new ContentValues();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    KeanggotaanObject keanggotaanObject = postSnapshot.getValue(KeanggotaanObject.class);
                    anggotaOnFirebase.add(keanggotaanObject.getIdKeanggotaan());

                    if (isKeanggotaanIdExist(keanggotaanObject.getIdKeanggotaan())){
                        cekKeanggotaanRowData(keanggotaanObject.getIdKeanggotaan(), keanggotaanObject, postSnapshot.getKey());
                    }
                    else{
                        contentValues.put(PerpusContract.PerpusEntry.COLUMN_KEANGGOTAAN_ID, keanggotaanObject.getIdKeanggotaan());
                        contentValues.put(PerpusContract.PerpusEntry.COLUMN_KEANGGOTAAN_FIREBASE_KEY, postSnapshot.getKey());
                        contentValues.put(PerpusContract.PerpusEntry.COLUMN_KEANGGOTAAN_STATUS, keanggotaanObject.getStatusKeanggotaan());
                        contentValues.put(PerpusContract.PerpusEntry.COLUMN_KEANGGOTAAN_EMAIL_PENGGUNA, keanggotaanObject.getEmailPengguna());
                        contentValues.put(PerpusContract.PerpusEntry.COLUMN_KEANGGOTAAN_PERPUS_ID, keanggotaanObject.getIdPerpustakaan());

                        perpusDatabaseHelper.insertKeanggotaan(contentValues);
                        contentValues.clear();
                        Log.v("Get Anggota to SQLite", "ID = "+keanggotaanObject.getIdKeanggotaan());
                    }
                }
                if (isKeanggotaanRowEachDatabaseSame()){

                }
                else{

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("Error : ", "Failed to read booking.", databaseError.toException());
            }
        });
    }
}
