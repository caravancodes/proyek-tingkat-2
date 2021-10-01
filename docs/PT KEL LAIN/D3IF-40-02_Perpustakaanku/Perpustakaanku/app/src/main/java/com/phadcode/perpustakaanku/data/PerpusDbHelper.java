package com.phadcode.perpustakaanku.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PerpusDbHelper extends SQLiteOpenHelper {
    public static final String LOG_TAG = PerpusDbHelper.class.getSimpleName();
    private static final String DATABASE_NAME = "perpustakaanku.db";
    private static final int DATABASE_VERSION = 1;

    public PerpusDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_TABLE;

        SQL_CREATE_TABLE = "CREATE TABLE " + PerpusContract.PerpusEntry.TABLE_USER+ " ("
                + PerpusContract.PerpusEntry.COLUMN_PENGGUNA_EMAIL + " TEXT PRIMARY KEY, "
                + PerpusContract.PerpusEntry.COLUMN_PENGGUNA_FIREBASE_KEY + " TEXT NOT NULL, "
                + PerpusContract.PerpusEntry.COLUMN_PENGGUNA_HANDPHONE+ " TEXT NOT NULL, "
                + PerpusContract.PerpusEntry.COLUMN_PENGGUNA_PROFIL_PICTURE + " TEXT, "
                + PerpusContract.PerpusEntry.COLUMN_PENGGUNA_BACKGROUND_PICTURE + " TEXT , "
                + PerpusContract.PerpusEntry.COLUMN_PENGGUNA_NAME + " TEXT NOT NULL, "
                + PerpusContract.PerpusEntry.COLUMN_PENGGUNA_PASSWORD + " TEXT NOT NULL, "
                + PerpusContract.PerpusEntry.COLUMN_PENGGUNA_DATE_OF_BIRTH + " TEXT NOT NULL, "
                + PerpusContract.PerpusEntry.COLUMN_PENGGUNA_GENDER + " TEXT NOT NULL, "
                + PerpusContract.PerpusEntry.COLUMN_PENGGUNA_ADDRESS + " TEXT NOT NULL);";
        db.execSQL(SQL_CREATE_TABLE);

        SQL_CREATE_TABLE = "CREATE TABLE " + PerpusContract.PerpusEntry.TABLE_PERPUS+ " ("
                + PerpusContract.PerpusEntry.PERPUS_ID + " TEXT PRIMARY KEY, "
                + PerpusContract.PerpusEntry.COLUMN_PERPUS_FIREBASE_KEY + " TEXT NOT NULL, "
                + PerpusContract.PerpusEntry.COLUMN_PERPUS_EMAIL + " TEXT NOT NULL, "
                + PerpusContract.PerpusEntry.COLUMN_PERPUS_HANDPHONE+ " TEXT NOT NULL, "
                + PerpusContract.PerpusEntry.COLUMN_PERPUS_PROFIL_PICTURE + " TEXT, "
                + PerpusContract.PerpusEntry.COLUMN_PERPUS_BACKGROUND_PICTURE + " TEXT, "
                + PerpusContract.PerpusEntry.COLUMN_PERPUS_NAME + " TEXT NOT NULL, "
                + PerpusContract.PerpusEntry.COLUMN_PERPUS_GPS + " TEXT NOT NULL, "
                + PerpusContract.PerpusEntry.COLUMN_PERPUS_ADDRESS + " TEXT NOT NULL, "
                + PerpusContract.PerpusEntry.COLUMN_PERPUS_OWNER + " TEXT NOT NULL, "
                + PerpusContract.PerpusEntry.COLUMN_PERPUS_ADMIN + " TEXT, "
                + PerpusContract.PerpusEntry.COLUMN_PERPUS_MEMBERS + " TEXT, "
                + PerpusContract.PerpusEntry.COLUMN_PERPUS_PENDING_MEMBERS + " TEXT, "
                + PerpusContract.PerpusEntry.COLUMN_PERPUS_BOOK_CATEGORIES + " TEXT, "
                + PerpusContract.PerpusEntry.COLUMN_PERPUS_STATUS + " TEXT NOT NULL);";
        db.execSQL(SQL_CREATE_TABLE);

        SQL_CREATE_TABLE = "CREATE TABLE " + PerpusContract.PerpusEntry.TABLE_BUKU+ " ("
                + PerpusContract.PerpusEntry.COLUMN_BUKU_ID + " TEXT PRIMARY KEY, "
                + PerpusContract.PerpusEntry.COLUMN_BUKU_FIREBASE_KEY + " TEXT NOT NULL, "
                + PerpusContract.PerpusEntry.COLUMN_BUKU_GAMBAR + " TEXT, "
                + PerpusContract.PerpusEntry.COLUMN_BUKU_JUDUL + " TEXT NOT NULL, "
                + PerpusContract.PerpusEntry.COLUMN_BUKU_DESKRIPSI + " TEXT NOT NULL, "
                + PerpusContract.PerpusEntry.COLUMN_BUKU_PENULIS + " TEXT NOT NULL, "
                + PerpusContract.PerpusEntry.COLUMN_BUKU_PENERBIT + " TEXT NOT NULL, "
                + PerpusContract.PerpusEntry.COLUMN_BUKU_JUMLAH + " TEXT NOT NULL, "
                + PerpusContract.PerpusEntry.COLUMN_BUKU_TERPINJAM + " TEXT NOT NULL, "
                + PerpusContract.PerpusEntry.COLUMN_BUKU_PERPUS + " TEXT NOT NULL REFERENCES "+ PerpusContract.PerpusEntry.TABLE_PERPUS +"("+ PerpusContract.PerpusEntry.PERPUS_ID +") ON UPDATE CASCADE);";
        db.execSQL(SQL_CREATE_TABLE);

        SQL_CREATE_TABLE = "CREATE TABLE " + PerpusContract.PerpusEntry.TABLE_BOOKING+ " ("
                + PerpusContract.PerpusEntry.COLUMN_BOOKING_ID + " TEXT PRIMARY KEY, "
                + PerpusContract.PerpusEntry.COLUMN_BOOKING_FIREBASE_KEY + " TEXT NOT NULL, "
                + PerpusContract.PerpusEntry.COLUMN_BOOKING_TANGGAL_BOOKING + " TEXT NOT NULL, "
                + PerpusContract.PerpusEntry.COLUMN_BOOKING_STATUS + " TEXT NOT NULL, "
                + PerpusContract.PerpusEntry.COLUMN_BOOKING_EMAIL_PEMINJAM + " TEXT NOT NULL REFERENCES "+ PerpusContract.PerpusEntry.TABLE_USER +"("+ PerpusContract.PerpusEntry.COLUMN_PENGGUNA_EMAIL +") ON UPDATE CASCADE, "
                + PerpusContract.PerpusEntry.COLUMN_BOOKING_ID_BUKU + " TEXT NOT NULL REFERENCES "+ PerpusContract.PerpusEntry.TABLE_BUKU +"("+ PerpusContract.PerpusEntry.COLUMN_BUKU_ID +") ON UPDATE CASCADE);";
        db.execSQL(SQL_CREATE_TABLE);

        SQL_CREATE_TABLE = "CREATE TABLE " + PerpusContract.PerpusEntry.TABLE_KEANGGOTAAN+ " ("
                + PerpusContract.PerpusEntry.COLUMN_KEANGGOTAAN_ID + " TEXT PRIMARY KEY, "
                + PerpusContract.PerpusEntry.COLUMN_KEANGGOTAAN_FIREBASE_KEY + " TEXT NOT NULL, "
                + PerpusContract.PerpusEntry.COLUMN_KEANGGOTAAN_STATUS + " TEXT NOT NULL, "
                + PerpusContract.PerpusEntry.COLUMN_KEANGGOTAAN_EMAIL_PENGGUNA + " TEXT NOT NULL REFERENCES "+ PerpusContract.PerpusEntry.TABLE_USER +"("+ PerpusContract.PerpusEntry.COLUMN_PENGGUNA_EMAIL +") ON UPDATE CASCADE, "
                + PerpusContract.PerpusEntry.COLUMN_KEANGGOTAAN_PERPUS_ID + " TEXT NOT NULL REFERENCES "+ PerpusContract.PerpusEntry.TABLE_PERPUS +"("+ PerpusContract.PerpusEntry.PERPUS_ID +") ON UPDATE CASCADE);";
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
