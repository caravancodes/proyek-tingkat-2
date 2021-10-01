package com.phadcode.perpustakaanku;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.phadcode.perpustakaanku.data.PerpusContract;
import com.phadcode.perpustakaanku.helper.PerpusDatabaseHelper;
import com.phadcode.perpustakaanku.helper.Session;
import com.phadcode.perpustakaanku.object.BookingObject;
import com.phadcode.perpustakaanku.object.KeanggotaanObject;
import com.uncopt.android.widget.text.justify.JustifiedTextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PopBookActivity extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private PerpusDatabaseHelper perpusDatabaseHelper;
    private Cursor cursor;
    private Session session;
    private BookingObject bookingObject;
    private KeanggotaanObject keanggotaanObject;

    private TextView tvBukuName, tvPenulis, tvPenerbit, tvStatus, tvLamaPinjam, tvTerpinjam, tvDenda;
    private JustifiedTextView tvDeskripsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_book);

        session = new Session(this);

        setTitle("Ikhtisar Buku");

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("root");
        keanggotaanObject = new KeanggotaanObject();

        tvBukuName = findViewById(R.id.tvBukuName);
        tvDeskripsi = findViewById(R.id.tvDeskripsi);
        tvPenulis = findViewById(R.id.tvPenulis);
        tvPenerbit = findViewById(R.id.tvPenerbit);
        tvStatus = findViewById(R.id.tvStatus);
        tvLamaPinjam = findViewById(R.id.tvLamaPinjam);
        tvTerpinjam = findViewById(R.id.tvTerpinajam);
        tvDenda = findViewById(R.id.tvDenda);

        perpusDatabaseHelper = new PerpusDatabaseHelper(this);
        cursor = perpusDatabaseHelper.selectSingleBuku(session.getSessionString("id_buku"));

        try {
            while (cursor.moveToNext()){
                String name = cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_BUKU_JUDUL));
                String deskripsi = cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_BUKU_DESKRIPSI));
                String penulis = "Penulis : " + cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_BUKU_PENULIS));
                String penerbit = "Penerbit : " + cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_BUKU_PENERBIT));
                String lamaPinjam = "Lama Pinjam : 3 Hari";
                String terpinjam = "Terpinjam : " + cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_BUKU_TERPINJAM)) + " kali";
                String denda = "Denda : Rp 1.500 / Hari";

                String status = "Status : ";
                if (Integer.parseInt(cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_BUKU_JUMLAH))) > 0){
                    status = status + "Tersedia";
                }
                else {
                    status = status + "Tidak Tersedia";
                }

                tvBukuName.setText(name);
                tvDeskripsi.setText(deskripsi);
                tvPenulis.setText(penulis);
                tvPenerbit.setText(penerbit);
                tvStatus.setText(status);
                tvLamaPinjam.setText(lamaPinjam);
                tvTerpinjam.setText(terpinjam);
                tvDenda.setText(denda);
            }
        }finally {
            cursor.close();
        }

        Button btnBooking = findViewById(R.id.btnBooking);
        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (session.getSessionBoolean(Session.LOGED_IN)){
                    if (isAnggota()){
                        if (getStatus().equalsIgnoreCase(keanggotaanObject.STATUS_KEANGGOTAAN_DITERIMA)){
                            if (tambahBooking()){
                                updateBukuTerpinjam();
                                Toast.makeText(PopBookActivity.this, "Pemesanan berhasil", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            else{
                                Toast.makeText(PopBookActivity.this, "Ada kesalahan", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                        else if (getStatus().equalsIgnoreCase(keanggotaanObject.STATUS_KEANGGOTAAN_BELUM_DITERIMA)){
                            final AlertDialog.Builder builder = new AlertDialog.Builder(PopBookActivity.this);
                            builder.setMessage("Anda belum diterima sebagai anggota, harap tunggu beberapa saat.")
                                    .setCancelable(true)
                                    .setNegativeButton("Oke", new DialogInterface.OnClickListener() {
                                        public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                                            dialog.cancel();
                                        }
                                    });
                            builder.create().show();
                        }
                        else if (getStatus().equalsIgnoreCase(keanggotaanObject.STATUS_KEANGGOTAAN_TIDAK_DITERIMA)){
                            final AlertDialog.Builder builder = new AlertDialog.Builder(PopBookActivity.this);
                            builder.setMessage("Anda tidak diterima sebagai anggota, harap isi data Anda dengan data yang valid agar pengurus perpustakaan dapat menerima anda di sebagai anggota.")
                                    .setCancelable(true)
                                    .setNegativeButton("Oke", new DialogInterface.OnClickListener() {
                                        public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                                            dialog.cancel();
                                        }
                                    });
                            builder.create().show();
                        }
                    }
                    else{
                        final AlertDialog.Builder builder = new AlertDialog.Builder(PopBookActivity.this);
                        builder.setMessage("Anda harus menjadi anggota dari perpustakaan ini agar dapat melakukan pemesanan buku.")
                                .setCancelable(true)
                                .setNegativeButton("Oke", new DialogInterface.OnClickListener() {
                                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                                        dialog.cancel();
                                    }
                                });
                        builder.create().show();
                    }
                }
                else{
                    Intent intent = new Intent(PopBookActivity.this, LoginActivity.class);
                    intent.putExtra("pop", true);
                    startActivity(intent);
                }
            }
        });
    }

    private String getTanggal(int a){
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");
        if (a == 0){
            return c.toString();
        }
        else{
            return df.format(c);
        }
    }

    private Boolean tambahBooking(){
        bookingObject = new BookingObject(
                "|[BOOKING]|" + getTanggal(0) + "||" + session.getSessionString("email") + "||",
                getTanggal(1),
                bookingObject.BOOKING_STATUS_AKTIF,
                session.getSessionString("email"),
                session.getSessionString("id_buku")
        );

        databaseReference.child("booking").push().setValue(bookingObject);
        return true;
    }

    private void updateBukuTerpinjam(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("root").child("buku");
        perpusDatabaseHelper = new PerpusDatabaseHelper(this);

        cursor = perpusDatabaseHelper.selectSingleBuku(session.getSessionString("id_buku"));
        cursor.moveToFirst();
        String key = cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_BUKU_FIREBASE_KEY));
        int terpinjam = Integer.parseInt(cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_BUKU_TERPINJAM))) + 1;
        databaseReference.child(key).child("terpinjam").setValue(terpinjam + "");
    }

    private boolean isAnggota(){
        cursor = perpusDatabaseHelper.selectSinglePerpusKeanggotaan(session.getSessionString("email"), session.getSessionString("id_perpus"));
        if (cursor.getCount() == 0){
            return false;
        }
        else{
            return true;
        }
    }

    private String getStatus(){
        cursor = perpusDatabaseHelper.selectSinglePerpusKeanggotaan(session.getSessionString("email"), session.getSessionString("id_perpus"));
        cursor.moveToFirst();
        return cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_KEANGGOTAAN_STATUS));
    }
}
