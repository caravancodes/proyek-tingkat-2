package com.example.jasontood.halloffameindo;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Artikel extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       //navigtion drawer
        setContentView(R.layout.activity_artikel);
        mDrawerlayout=findViewById(R.id.drawer);
        toggle = new ActionBarDrawerToggle(Artikel.this,mDrawerlayout,R.string.open,R.string.close);
        mDrawerlayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //end navigtion drawer

//content section
        TextView nama = findViewById(R.id.textNamatokoh);
        ImageView profile = findViewById(R.id.fotoprofile);
        TextView  tll = findViewById(R.id.ttl);
        TextView  deskripsi = findViewById(R.id.deskripsi);
        TextView  kategori = findViewById(R.id.kategori);
        Bundle b = getIntent().getExtras();
        String StringNama=  b.getString("nama");
        int gambar = b.getInt("gambar");

        String StringDeskripsi = b.getString("deskripsi");
        String tempattgllahir = b.getString("tempatlahir")+","+b.getString("tgllahir");
        nama.setText(StringNama);
        profile.setImageResource(gambar);
        tll.setText(tempattgllahir);
        deskripsi.setText(StringDeskripsi);
        kategori.setText(b.getString("kategori"));

//end content section
    }




    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }
    //menu navigasi
    public boolean onNavigationItemSelected(android.view.MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Intent intent = new Intent(Artikel.this, Tokoh.class);
        if (id == R.id.nav_home) {
            Intent inten = new Intent(Artikel.this,MainActivity.class);
            startActivity(inten);
        }else  if (id == R.id.nav_feedback) {
            Intent inten = new Intent(Artikel.this,FeedbackActivity.class);
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
    //end menu navigasi
}
