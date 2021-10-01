package com.phadcode.perpustakaanku;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Pop extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_book);

        TextView tvBukuName = findViewById(R.id.tvBukuName);
        tvBukuName.setText(getIntent().getStringExtra("judulBuku").toString());
        ImageView gbr_buku = findViewById(R.id.gbrbuku);
        DisplayMetrics dm = new DisplayMetrics();

        Bundle b = getIntent().getExtras();
        gbr_buku.setImageResource(b.getInt("gambarku"));


        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.8),(int) (height*0.5));
        //getWindow().setLayout((6 * width)/7, ViewGroup.LayoutParams.WRAP_CONTENT);

        Button btnBooking = findViewById(R.id.btnBooking);
        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity ma = new MainActivity();
                if (ma.isLogin()){

                }
                else{
                    startActivity(new Intent(Pop.this, Login.class));
                }
            }
        });
    }
}
