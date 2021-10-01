package com.example.jasontood.halloffameindo;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ListView lv ;
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.menu);
        ArrayList<MenuItem> list = new ArrayList<MenuItem>();
        list.add(new MenuItem("Militer",R.mipmap.ic_militer));
        list.add(new MenuItem("Arsitek",R.mipmap.ic_arsitek));
        list.add(new MenuItem("Pendidikan",R.mipmap.ic_pendidikan));
        list.add(new MenuItem("Seniman",R.mipmap.ic_seniman));
        list.add(new MenuItem("Olahraga",R.mipmap.ic_olahraga));
        list.add(new MenuItem("Agama",R.mipmap.ic_agama));
        Adapter lin =  new Adapter(MainActivity.this,list);
        lv.setAdapter(lin);

lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

           Intent intent = new Intent(MainActivity.this, Tokoh.class);

           String code="Agama";

           if(position==0){
               code="Militer";
           }else if(position==1){
               code="Arsitek";
           }else if(position==2){
               code="Pendidikan";
           }else if(position==3){
               code="Seniman";
           }else if(position==4){
               code="Olahraga";
           }
           intent.putExtra("id",code);
           startActivity(intent);


    }
});

        mDrawerlayout=findViewById(R.id.drawer);
        toggle = new ActionBarDrawerToggle(MainActivity.this,mDrawerlayout,R.string.open,R.string.close);
        mDrawerlayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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

        Intent intent = new Intent(MainActivity.this, Tokoh.class);
        if (id == R.id.nav_home) {
        Intent inten = new Intent(MainActivity.this,MainActivity.class);
            startActivity(inten);
        }else  if (id == R.id.nav_feedback) {
            Intent inte1 = new Intent(MainActivity.this,FeedbackActivity.class);
            startActivity(inte1);
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

