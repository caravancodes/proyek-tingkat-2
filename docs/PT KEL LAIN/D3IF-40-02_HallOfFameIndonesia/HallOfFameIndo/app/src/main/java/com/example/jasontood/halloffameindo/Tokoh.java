package com.example.jasontood.halloffameindo;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Tokoh extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener {
    private ListView listView;
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tokoh);
        final ArrayList<TokohItem> list = new ArrayList<TokohItem>();
        list.add(new TokohItem("Soekarno ","Tokoh Proklmasi"," 6 Juni 1901","Jawa Timur",R.mipmap.ic_soekarno));
        list.add(new TokohItem("Jendral Sudirman","Jendral Besar","24 Januari 1916","Purbalingga",R.mipmap.ic_sudirman));
        list.add(new TokohItem("Abdul Haris Nasution","Pahlawan Nasional"," 3 Desember 1918","Sumatra Utara",R.mipmap.ic_nasution));
        list.add(new TokohItem("Ahmad Husein","Pemimpin militer PRRI.","1 April 1925 ","Sumatra Barat",R.mipmap.ic_husein_ahmad));
        list.add(new TokohItem("Edi Sudradjat"," Kepala Staf TNI-AD",""," Jambi",R.mipmap.ic_edi_sudraja));
        list.add(new TokohItem("Ahmad Yani ","Jenderal TNI Anumerta "," 19 Juni 1922 ","Jawa Tengah",R.mipmap.ic_ahmadyani));
        list.add(new TokohItem("T.B. Simatupang","Kepala Staf Angkatan Perang Republik Indonesia"," 1 Januari 1990","Jakarta",R.mipmap.ic_tbsimatupang));
        TextView ktg = findViewById(R.id.kategori);
        ImageView icon = findViewById(R.id.icon);
        listView=findViewById(R.id.Tokoh);
        TokohAdapter heroAdapter = new TokohAdapter(Tokoh.this,list);
        listView.setAdapter(heroAdapter);


        Bundle b = getIntent().getExtras();//extras intent
        final String idk = b.getString("id");//id kategori

        if(idk.equalsIgnoreCase("Militer")){
            ktg.setText("Militer");
            icon.setImageResource(R.mipmap.ic_militer);
        } else if(idk.equalsIgnoreCase("Arsitek")){
            ktg.setText("Arsitek");
            icon.setImageResource(R.mipmap.ic_arsitek);
        } else if(idk.equalsIgnoreCase("Pendidikan")){
            ktg.setText("Pendidikan");
            icon.setImageResource(R.mipmap.ic_pendidikan);
        } else if(idk.equalsIgnoreCase("Seniman")){
            ktg.setText("Seniman");
            icon.setImageResource(R.mipmap.ic_seniman);
        }else if(idk.equalsIgnoreCase("Olahraga")){
            ktg.setText("Olahraga");
            icon.setImageResource(R.mipmap.ic_olahraga);
        }else{
            ktg.setText("Agama");
            icon.setImageResource(R.mipmap.ic_agama);
        }



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in = new Intent(Tokoh.this,Artikel.class);
               TokohItem hero= (TokohItem) list.get(position);

                in.putExtra("nama",hero.getNama());
                in.putExtra("gambar",hero.getIcon());
                in.putExtra("tgllahir",hero.getTgl_lahir());
                in.putExtra("tempatlahir",hero.getTempat_lahir());
                in.putExtra("deskripsi",hero.getDeskripsi());
                in.putExtra("kategori",idk);
                startActivity(in);
            }
        });
       // navigation drawer  section
        mDrawerlayout=findViewById(R.id.drawer);
        toggle = new ActionBarDrawerToggle(Tokoh.this,mDrawerlayout,R.string.open,R.string.close);
        mDrawerlayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //endnavigtionDrawer
    }


    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }
    public boolean onNavigationItemSelected(android.view.MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Intent intent = new Intent(Tokoh.this, Tokoh.class);
        if (id == R.id.nav_home) {
            Intent inten = new Intent(Tokoh.this,MainActivity.class);
            startActivity(inten);
        }else  if (id == R.id.nav_feedback) {
            Intent inten = new Intent(Tokoh.this,FeedbackActivity.class);
            startActivity(inten);
        } else if (id == R.id.nav_agama) {
            intent.putExtra("id","agama");
            startActivity(intent);
        } else if (id == R.id.nav_arsitek) {
            intent.putExtra("id","arsitek");
            startActivity(intent);
        } else if (id == R.id.nav_olahraga) {
            intent.putExtra("id","olahraga");
            startActivity(intent);
        } else if (id == R.id.nav_militer) {
            intent.putExtra("id","militer");
            startActivity(intent);
        }else if (id == R.id.nav_seniman) {
            intent.putExtra("id","seniman");
            startActivity(intent);
        }else if (id == R.id.nav_pendidikan) {
            intent.putExtra("id","ic_pendidikan");
            startActivity(intent);
        }

        return true;
    }

}
