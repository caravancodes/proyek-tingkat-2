package com.phadcode.perpustakaanku;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.phadcode.perpustakaanku.data.PerpusContract;
import com.phadcode.perpustakaanku.helper.PerpusDatabaseHelper;
import com.phadcode.perpustakaanku.helper.Session;
import com.phadcode.perpustakaanku.object.KeanggotaanObject;

import java.util.Calendar;
import java.util.Date;

public class ProfilActivity extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private Session session;
    private PerpusDatabaseHelper perpusDatabaseHelper;
    private Cursor cursor;
    private KeanggotaanObject keanggotaanObject;
    private Toast toast;

    private ImageView ivPhotoProfil, ivBackgroundProfil;
    private View detailUser, detailPerpus, viewChoice;
    private TextView tvNama, tvDetail;
    private TextView tvEmailUser, tvNoHpUser, tvTglLahirUser, tvKelaminUser;
    private TextView tvEmailPerpus, tvNoHpPerpus, tvHariBukaPerpus, tvWaktuBukaPerpus;
    private Button btnProfil, btnAtas, btnBawah;

    private String profil = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("root").child("pengguna");

        toast = toast.makeText(ProfilActivity.this, "", Toast.LENGTH_LONG);

        //View Perpus and User Set Visibility
        detailUser = findViewById(R.id.detailUser);
        detailUser.setVisibility(View.GONE);
        detailPerpus = findViewById(R.id.detailPerpus);
        detailPerpus.setVisibility(View.GONE);
        //End View Perpus and User Set Visibility

        //Get Helper
        session = new Session(this);
        perpusDatabaseHelper = new PerpusDatabaseHelper(this);
        //End Get Helper

        ivPhotoProfil = findViewById(R.id.ivPhotoProfil);
        ivBackgroundProfil = findViewById(R.id.ivBackgroundProfil);

        //Get Input Perpus or User
        profil = session.getSessionString("profil");
        //End Get Input Perpus or User

        if (profil.equals("user")){
            detailUser.setVisibility(View.VISIBLE);
            setTitle("Profil");

            ivPhotoProfil.setImageResource(R.drawable.userppempty);
            ivBackgroundProfil.setImageResource(R.drawable.userbgempty);

            //User Data Proccessing
            cursor = perpusDatabaseHelper.selectSingleUser(session.getSessionString("email"));

            try{
                while (cursor.moveToNext()){
                    tvNama = findViewById(R.id.tvNama);
                    tvNama.setText(cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_PENGGUNA_NAME)));

                    tvDetail = findViewById(R.id.tvDetail);
                    tvDetail.setVisibility(View.GONE);

                    tvEmailUser = findViewById(R.id.tvEmailUser);
                    tvEmailUser.setText(cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_PENGGUNA_EMAIL)));

                    tvNoHpUser = findViewById(R.id.tvNoHpUser);
                    tvNoHpUser.setText(cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_PENGGUNA_HANDPHONE)));

                    tvTglLahirUser = findViewById(R.id.tvTglLahirUser);
                    tvTglLahirUser.setText(cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_PENGGUNA_DATE_OF_BIRTH)));

                    tvKelaminUser = findViewById(R.id.tvKelaminUser);
                    tvKelaminUser.setText(cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_PENGGUNA_GENDER)));
                }
            }finally {
                cursor.close();
            }
            btnProfil = findViewById(R.id.btnProfil);
            btnProfil.setText("Edit Profil");
            btnProfil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(ProfilActivity.this, "Edit profil belum tersedia", Toast.LENGTH_SHORT).show();
                }
            });
            //End User Data Proccessing
        }
        else if(profil.equals("perpus")){
            detailPerpus.setVisibility(View.VISIBLE);
            setTitle("Perpustakaan");

            int[] emptyImagePerpusPp = {R.drawable.perpustakaan, R.drawable.kelola_perpus};
            java.util.Random rd = new java.util.Random();
            ivPhotoProfil.setImageResource(emptyImagePerpusPp[rd.nextInt(emptyImagePerpusPp.length)+0]);
            ivBackgroundProfil.setImageResource(R.drawable.perpusbgempty);

            //Perpus Data Proccessing
            cursor = perpusDatabaseHelper.selectSinglePerpus(session.getSessionString("id_perpus"));

            try{
                while (cursor.moveToNext()){
                    tvNama = findViewById(R.id.tvNama);
                    tvNama.setText(cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_PERPUS_NAME)));

                    Cursor getCount = perpusDatabaseHelper.selectPerpusKeanggotaan(session.getSessionString("id_perpus"));
                    tvDetail = findViewById(R.id.tvDetail);
                    tvDetail.setText(getCount.getCount() + " anggota");

                    tvEmailPerpus = findViewById(R.id.tvEmailPerpus);
                    tvEmailPerpus.setText(cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_PERPUS_EMAIL)));

                    tvNoHpPerpus = findViewById(R.id.tvNoHpPerpus);
                    tvNoHpPerpus.setText(cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_PERPUS_HANDPHONE)));

                    tvHariBukaPerpus = findViewById(R.id.tvHariBukaPerpus);
                    tvHariBukaPerpus.setText(tvHariBukaPerpus.getText());

                    tvWaktuBukaPerpus = findViewById(R.id.tvWaktuBukaPerpus);
                    tvWaktuBukaPerpus.setText(tvWaktuBukaPerpus.getText());
                }
            }finally {
                cursor.close();
            }

            btnProfil = findViewById(R.id.btnProfil);
            btnProfil.setText("Lihat Buku");
            btnProfil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(ProfilActivity.this, BookListActivity.class));
                }
            });
            //End Perpus Data Proccessing
        }
        else if(profil.equals("kelola_user_tidak_aktif")) {
            detailUser.setVisibility(View.VISIBLE);
            setTitle("Profil");

            ivPhotoProfil.setImageResource(R.drawable.userppempty);
            ivBackgroundProfil.setImageResource(R.drawable.userbgempty);

            //User Data Proccessing
            final String[] id = getIntent().getExtras().getString("kumpulan_id").split("-");
            cursor = perpusDatabaseHelper.selectSingleUser(id[1]);

            try{
                while (cursor.moveToNext()){
                    tvNama = findViewById(R.id.tvNama);
                    tvNama.setText(cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_PENGGUNA_NAME)));

                    tvDetail = findViewById(R.id.tvDetail);
                    tvDetail.setVisibility(View.GONE);

                    tvEmailUser = findViewById(R.id.tvEmailUser);
                    tvEmailUser.setText(cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_PENGGUNA_EMAIL)));

                    tvNoHpUser = findViewById(R.id.tvNoHpUser);
                    tvNoHpUser.setText(cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_PENGGUNA_HANDPHONE)));

                    tvTglLahirUser = findViewById(R.id.tvTglLahirUser);
                    tvTglLahirUser.setText(cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_PENGGUNA_DATE_OF_BIRTH)));

                    tvKelaminUser = findViewById(R.id.tvKelaminUser);
                    tvKelaminUser.setText(cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_PENGGUNA_GENDER)));
                }
            }finally {
                cursor.close();
            }
            btnProfil =  findViewById(R.id.btnProfil);
            btnProfil.setVisibility(View.GONE);

            viewChoice = findViewById(R.id.viewChoice);
            viewChoice.setVisibility(View.VISIBLE);

            btnAtas = findViewById(R.id.btnAtas);
            btnAtas.setText("Terima Anggota");
            btnAtas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    firebaseDatabase = FirebaseDatabase.getInstance();
                    databaseReference = firebaseDatabase.getReference("root").child("keanggotaan");
                    perpusDatabaseHelper = new PerpusDatabaseHelper(ProfilActivity.this);

                    cursor = perpusDatabaseHelper.selectSinglePerpusKeanggotaan(id[1], session.getSessionString("owned_perpus"));
                    cursor.moveToFirst();
                    String key = cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_KEANGGOTAAN_FIREBASE_KEY));
                    databaseReference.child(key).child("statusKeanggotaan").setValue(keanggotaanObject.STATUS_KEANGGOTAAN_DITERIMA);

                    toast.setText("Berhasil menerima anggota.");
                    toast.show();
                    finish();
                }
            });

            btnBawah = findViewById(R.id.btnBawah);
            btnBawah.setText("Tolak Anggota");
            btnBawah.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    toast.setText("Tolak anggota belum tersedia");
                    toast.show();
                }
            });
            //End User Data Proccessing

        }
        else if(profil.equals("kelola_user_aktif")) {
            detailUser.setVisibility(View.VISIBLE);
            setTitle("Profil");

            ivPhotoProfil.setImageResource(R.drawable.userppempty);
            ivBackgroundProfil.setImageResource(R.drawable.userbgempty);

            //User Data Proccessing
            final String[] id = getIntent().getExtras().getString("kumpulan_id").split("-");
            cursor = perpusDatabaseHelper.selectSingleUser(id[1]);

            try{
                while (cursor.moveToNext()){
                    tvNama = findViewById(R.id.tvNama);
                    tvNama.setText(cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_PENGGUNA_NAME)));

                    tvDetail = findViewById(R.id.tvDetail);
                    tvDetail.setVisibility(View.GONE);

                    tvEmailUser = findViewById(R.id.tvEmailUser);
                    tvEmailUser.setText(cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_PENGGUNA_EMAIL)));

                    tvNoHpUser = findViewById(R.id.tvNoHpUser);
                    tvNoHpUser.setText(cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_PENGGUNA_HANDPHONE)));

                    tvTglLahirUser = findViewById(R.id.tvTglLahirUser);
                    tvTglLahirUser.setText(cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_PENGGUNA_DATE_OF_BIRTH)));

                    tvKelaminUser = findViewById(R.id.tvKelaminUser);
                    tvKelaminUser.setText(cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_PENGGUNA_GENDER)));
                }
            }finally {
                cursor.close();
            }
            btnProfil =  findViewById(R.id.btnProfil);
            btnProfil.setVisibility(View.GONE);

            viewChoice = findViewById(R.id.viewChoice);
            viewChoice.setVisibility(View.VISIBLE);

            btnAtas = findViewById(R.id.btnAtas);
            btnAtas.setText("Hapus anggota");
            btnAtas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    databaseReference = firebaseDatabase.getReference("root").child("keanggotaan");

                    cursor = perpusDatabaseHelper.selectSingleKeanggotaan(id[0]);
                    cursor.moveToFirst();
                    String key = cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_KEANGGOTAAN_FIREBASE_KEY));
                    databaseReference.child(key).setValue(null);
                    toast.setText("Hapus anggota berhasil");
                    toast.show();
                    finish();
                }
            });

            btnBawah = findViewById(R.id.btnBawah);
            btnBawah.setText("Jadikan Pengurus");
            btnBawah.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    toast.setText("Jadikan pengurus belum tersedia");
                    toast.show();
                }
            });
            //End User Data Proccessing
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (profil.equals("perpus") && !isAnggota() && !isOwner()){
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_perpus, menu);
            return true;
        }
        else { return false;}
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (profil.equals("kelola_user_tidak_aktif") || profil.equals("kelola_user_aktif")){
            if (item.getItemId() == android.R.id.home){
                finish();
                return true;
            }
        }
        if (item.getItemId() == R.id.daftarPerpus){
            if(session.getSessionBoolean(Session.LOGED_IN)){
                Date c = Calendar.getInstance().getTime();

                keanggotaanObject = new KeanggotaanObject(
                        "|[KEANGGOTAAN]|" + c.toString() + "||" + session.getSessionString("email") + "||",
                        keanggotaanObject.STATUS_KEANGGOTAAN_BELUM_DITERIMA,
                        session.getSessionString("email"),
                        session.getSessionString("id_perpus")
                );

                databaseReference = firebaseDatabase.getReference("root");
                databaseReference.child("keanggotaan").push().setValue(keanggotaanObject);

                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Berhasil mendaftar menjadi angggota. Tunggu persetujuan pengurus perpustakaan agar dapat melakukan pemesanan buku.")
                        .setCancelable(false)
                        .setNegativeButton("Oke", new DialogInterface.OnClickListener() {
                            public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                                dialog.cancel();
                                finish();
                                startActivity(new Intent(ProfilActivity.this, ProfilActivity.class));
                            }
                        });
                builder.create().show();
            }
            else{
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Masuk ke akun terlebih dahulu agar dapat mendaftar sebagai angggota.")
                        .setCancelable(true)
                        .setNegativeButton("Oke", new DialogInterface.OnClickListener() {
                            public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                                dialog.cancel();
                            }
                        });
                builder.create().show();
            }
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }
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

    private boolean isOwner(){
        cursor = perpusDatabaseHelper.selectPerpusOwner(session.getSessionString("email"), session.getSessionString("id_perpus"));
        if (cursor.getCount() == 0){
            return false;
        }
        else{
            return true;
        }
    }
}
