package com.phadcode.perpustakaanku.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public final class PerpusContract {
    private PerpusContract (){
    }
    public static final String CONTENT_AUTHORITY = "com.phadcode.perpustakaanku";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_PERPUS = "perpus";

    public static final class PerpusEntry implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PERPUS);
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PERPUS;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PERPUS;

        public static final String TABLE_USER = "table_user";
        public static final String COLUMN_PENGGUNA_EMAIL = "email";
        public static final String COLUMN_PENGGUNA_FIREBASE_KEY = "key";
        public static final String COLUMN_PENGGUNA_HANDPHONE = "handphone";
        public static final String COLUMN_PENGGUNA_PROFIL_PICTURE = "profil_picture";
        public static final String COLUMN_PENGGUNA_BACKGROUND_PICTURE = "background_picture";
        public static final String COLUMN_PENGGUNA_NAME = "name";
        public static final String COLUMN_PENGGUNA_PASSWORD = "password";
        public static final String COLUMN_PENGGUNA_DATE_OF_BIRTH = "date_of_birth";
        public static final String COLUMN_PENGGUNA_GENDER = "gender";
        public static final String COLUMN_PENGGUNA_ADDRESS = "address";

        public static final String TABLE_PERPUS = "table_perpus";
        public static final String PERPUS_ID = "id";
        public static final String COLUMN_PERPUS_FIREBASE_KEY = "key";
        public static final String COLUMN_PERPUS_EMAIL = "email";
        public static final String COLUMN_PERPUS_HANDPHONE = "handphone";
        public static final String COLUMN_PERPUS_PROFIL_PICTURE = "profil_picture";
        public static final String COLUMN_PERPUS_BACKGROUND_PICTURE = "background_picture";
        public static final String COLUMN_PERPUS_NAME = "name";
        public static final String COLUMN_PERPUS_GPS = "gps";
        public static final String COLUMN_PERPUS_ADDRESS = "address";
        public static final String COLUMN_PERPUS_OWNER = "owner";
        public static final String COLUMN_PERPUS_ADMIN = "admin";
        public static final String COLUMN_PERPUS_MEMBERS = "members";
        public static final String COLUMN_PERPUS_PENDING_MEMBERS = "pending_members";
        public static final String COLUMN_PERPUS_BOOK_CATEGORIES = "book_categories";
        public static final String COLUMN_PERPUS_STATUS = "status";

        public static final String TABLE_BUKU = "table_buku";
        public static final String COLUMN_BUKU_ID = "id";
        public static final String COLUMN_BUKU_FIREBASE_KEY = "key";
        public static final String COLUMN_BUKU_GAMBAR = "gambar";
        public static final String COLUMN_BUKU_JUDUL = "judul";
        public static final String COLUMN_BUKU_DESKRIPSI = "deksripsi";
        public static final String COLUMN_BUKU_PENULIS = "penulis";
        public static final String COLUMN_BUKU_PENERBIT = "penerbit";
        public static final String COLUMN_BUKU_JUMLAH = "jumlah";
        public static final String COLUMN_BUKU_TERPINJAM = "terpinjam";
        public static final String COLUMN_BUKU_PERPUS = "perpus";

        public static final String TABLE_BOOKING = "table_booking";
        public static final String COLUMN_BOOKING_ID = "id";
        public static final String COLUMN_BOOKING_FIREBASE_KEY = "key";
        public static final String COLUMN_BOOKING_TANGGAL_BOOKING = "tanggal_booking";
        public static final String COLUMN_BOOKING_STATUS = "status_booking";
        public static final String COLUMN_BOOKING_EMAIL_PEMINJAM = "email_peminjam";
        public static final String COLUMN_BOOKING_ID_BUKU = "id_buku";

        public static final String TABLE_KEANGGOTAAN = "table_keanggotaan";
        public static final String COLUMN_KEANGGOTAAN_ID = "id";
        public static final String COLUMN_KEANGGOTAAN_FIREBASE_KEY = "key";
        public static final String COLUMN_KEANGGOTAAN_STATUS = "status";
        public static final String COLUMN_KEANGGOTAAN_EMAIL_PENGGUNA = "email_pengguna";
        public static final String COLUMN_KEANGGOTAAN_PERPUS_ID = "perpus_id";

//        public static final int GENDER_UNKNOWN = 0;
//        public static final int GENDER_MALE = 1;
//        public static final int GENDER_FEMALE = 2;
//
//        /**
//         * Returns whether or not the given gender is {@link #GENDER_UNKNOWN}, {@link #GENDER_MALE},
//         * or {@link #GENDER_FEMALE}.
//         */
//        public static boolean isValidGender(int gender) {
//            if (gender == GENDER_UNKNOWN || gender == GENDER_MALE || gender == GENDER_FEMALE) {
//                return true;
//            }
//            return false;
//        }
    }
}
