package com.phadcode.perpustakaanku;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    private FragmentTransaction fragmentTransaction;
    private NavigationView navigationView;
    private ImageButton btnMenu, btnList;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
    public boolean isLogin(){
        if (getIntent().hasExtra("username")){
            return true;
        }
        else {
            return false;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Navx
        navigationView = findViewById(R.id.navigationView);
        if (isLogin()) {
            navigationView.getMenu().setGroupVisible(R.id.mnLogin, true);
            navigationView.getMenu().setGroupVisible(R.id.mnNotLogin, false);
            navigationView.inflateHeaderView(R.layout.navbar_header);
            View headerLayout = navigationView.getHeaderView(0);
            TextView tvHeaderName = headerLayout.findViewById(R.id.tvHeaderName);
            tvHeaderName.setText(getIntent().getStringExtra("username"));
            TextView tvHeaderEmail = headerLayout.findViewById(R.id.tvHeaderEmail);
            tvHeaderEmail.setText(getIntent().getStringExtra("email"));
        }
        else{
            navigationView.getMenu().setGroupVisible(R.id.mnNotLogin, true);
        }

        mDrawerlayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.mainContainer, new HomeFragment());
        fragmentTransaction.commit();
        this.setActionBarTitle("Home");
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home) {
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.mainContainer, new HomeFragment());
                    fragmentTransaction.commit();
                    getSupportActionBar().setTitle("Home");
                    item.setChecked(true);
                    mDrawerlayout.closeDrawers();
                } else if (item.getItemId() == R.id.login) {
                    startActivity(new Intent(MainActivity.this, Login.class));
                } else if (item.getItemId() == R.id.register) {
                    startActivity(new Intent(MainActivity.this, Register.class));
                }
                return false;
            }
        });
    }
}

