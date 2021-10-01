package com.phadcode.perpustakaanku;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.phadcode.perpustakaanku.data.PerpusContract;
import com.phadcode.perpustakaanku.helper.PerpusDatabaseHelper;
import com.phadcode.perpustakaanku.helper.Session;
import com.phadcode.perpustakaanku.object.PerpusObject;

import java.util.Calendar;
import java.util.Date;

public class BuatPerpusLanjutanActivity extends AppCompatActivity{
    private FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    PerpusObject perpusObject;
    Toast toast;
    Session session;
    PlacePicker.IntentBuilder picker;
    int PLACE_PICKER_REQUEST = 1;
    TextView tvEmail, tvNoTelp, tvGPS;
    EditText etEmail, etNoTelp;
    Button btnBuat;
    String PLACE_COORDINATE = "";
    Boolean MAP_NOT_CLICKED = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_perpus_lanjutan);

        toast = Toast.makeText(BuatPerpusLanjutanActivity.this, null, Toast.LENGTH_SHORT);

        session = new Session(this);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("root");

        setTitle("Buat Perpustakaan");

        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvEmail.setVisibility(View.GONE);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus){
                    etEmail.setHint("");
                    tvEmail.setVisibility(View.VISIBLE);
                }
                else{
                    etEmail.setHint("Email Perpustakaan");
                }
            }
        });

        tvNoTelp = (TextView) findViewById(R.id.tvNoTelp);
        tvNoTelp.setVisibility(View.GONE);

        etNoTelp = (EditText) findViewById(R.id.etNoTelp);
        etNoTelp.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus){
                    etNoTelp.setHint("");
                    tvNoTelp.setVisibility(View.VISIBLE);
                }
                else{
                    etNoTelp.setHint("No. Telp Perpustakaan");
                }
            }
        });

        tvGPS = (TextView) findViewById(R.id.tvGPS);
        tvGPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                picker = new PlacePicker.IntentBuilder();
                try {
                    if (MAP_NOT_CLICKED){
                        MAP_NOT_CLICKED = false;
                        intent = picker.build(BuatPerpusLanjutanActivity.this);
                        startActivityForResult(intent, PLACE_PICKER_REQUEST);
                    }
                    else{
                        toast.setText("Memuat peta");
                        toast.show();
                    }
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });

        btnBuat = (Button) findViewById(R.id.btnBuat);
        btnBuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (buatPerpus()){
                    toast.setText("Berhasil buat perpustakaan");
                    toast.show();
                    Intent intent = new Intent(BuatPerpusLanjutanActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
                else{
                    toast.setText("Gagal buat perpustakaan");
                }
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        if (requestCode == PLACE_PICKER_REQUEST){
            MAP_NOT_CLICKED = true;
            if (resultCode == RESULT_OK){
                Place place = PlacePicker.getPlace(this, intent);
                tvGPS.setText(place.getAddress().toString());
                PLACE_COORDINATE = place.getLatLng().latitude + ", " + place.getLatLng().longitude;
            }
        }
    }

    public Boolean buatPerpus(){
        Bundle bundle = getIntent().getExtras();
        Date c = Calendar.getInstance().getTime();

        perpusObject = new PerpusObject(
                "|[PERPUSTAKAAN]|" + c.toString() + "||" + etEmail.getText().toString().trim() + "||",
                etEmail.getText().toString().trim(),
                etNoTelp.getText().toString().trim(),
                "",
                "",
                bundle.getString("nama"),
                PLACE_COORDINATE,
                bundle.getString("alamat"),
                session.getSessionString("email"),
                "",
                "",
                "",
                "",
                "inactive"
        );

        databaseReference.child("perpustakaan").push().setValue(perpusObject);

        return true;
    }

}
