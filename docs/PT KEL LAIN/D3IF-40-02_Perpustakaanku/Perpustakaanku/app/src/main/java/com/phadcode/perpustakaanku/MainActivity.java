package com.phadcode.perpustakaanku;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.phadcode.perpustakaanku.data.PerpusContract;
import com.phadcode.perpustakaanku.helper.DataFetcherFromJSON;
import com.phadcode.perpustakaanku.helper.PerpusDatabaseHelper;
import com.phadcode.perpustakaanku.helper.Session;

public class MainActivity extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private Toast toast;
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    private FragmentTransaction fragmentTransaction;
    private NavigationView navigationView;

    private PerpusDatabaseHelper perpusDatabaseHelper;
    private Session session;

    private ProgressBar pbLoading;

    private String email = "";
    private String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setActionBarTitle("Beranda");

        perpusDatabaseHelper = new PerpusDatabaseHelper(this);

        toast = Toast.makeText(MainActivity.this, null, Toast.LENGTH_SHORT);

        //Get Helper
        session = new Session(this);
        //End Get Helper

        //Clear Unused Session
        session.setSessionString("id_perpus", null);
        session.setSessionString("profil", null);
        session.setSessionString("id_buku", null);
        session.setSessionString("owned_perpus", null);
        //End Clear Unused Session

        //Nav
        navigationView = findViewById(R.id.navigationView);
        if (isLogin()) {
            Cursor cursor;

            //Change to Loged In NavBar
            navigationView.getMenu().setGroupVisible(R.id.mnLogin, true);
            navigationView.getMenu().setGroupVisible(R.id.mnNotLogin, false);
            navigationView.inflateHeaderView(R.layout.navbar_header);
            //End Change to Loged In NavBar

            //Check Perpus Owned
            cursor = perpusDatabaseHelper.selectUserOwnedPerpus(email);
            try {
                if (cursor.getCount() == 0) {
                    //Set View Only Tambah Perpus
                    navigationView.getMenu().findItem(R.id.tambahPerpustakaan).setVisible(true);
                    navigationView.getMenu().findItem(R.id.kelolaPerpustakaan).setVisible(false);
                }
                else{
                    //Set View Only Kelola Perpus
                    navigationView.getMenu().findItem(R.id.tambahPerpustakaan).setVisible(false);
                    navigationView.getMenu().findItem(R.id.kelolaPerpustakaan).setVisible(true);
                    if (cursor.moveToFirst()){
                        session.setSessionString("owned_perpus", cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.PERPUS_ID)));
                    }
                }
            }finally {
                cursor.close();
            }
            //End Check Perpus Owned

            //Get User Data from DB
            cursor = perpusDatabaseHelper.selectSingleUser(email);
            try {
                while (cursor.moveToNext()){
                    name = cursor.getString(cursor.getColumnIndex(PerpusContract.PerpusEntry.COLUMN_PENGGUNA_NAME));
                }
            }finally {
                cursor.close();
            }
            //End Get User Data from DB

            //Set User Data to NavBar
            View headerLayout = navigationView.getHeaderView(0);
            TextView tvHeaderName = headerLayout.findViewById(R.id.tvHeaderName);
            tvHeaderName.setText(name);
            TextView tvHeaderEmail = headerLayout.findViewById(R.id.tvHeaderEmail);
            tvHeaderEmail.setText(email);
            //End Set User Data to NavBar

        }
        else{
            navigationView.getMenu().setGroupVisible(R.id.mnNotLogin, true);
        }

        mDrawerlayout = findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //Loged Off
                if (item.getItemId() == R.id.login) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    mDrawerlayout.closeDrawers();
                }
                else if (item.getItemId() == R.id.register) {
                    startActivity(new Intent(MainActivity.this, RegisterActivity.class));
                    mDrawerlayout.closeDrawers();
                }
                else if (item.getItemId() == R.id.infoAplikasi1){
                    toast.setText("Info aplikasi belum tersedia");
                    toast.show();
                }
                //End Loged Off
                //Loged In
                else if (item.getItemId() == R.id.profil){
                    session.setSessionString("profil", "user");
                    startActivity(new Intent(MainActivity.this, ProfilActivity.class));
                }
                else if (item.getItemId() == R.id.pemesanan){
                    startActivity(new Intent(MainActivity.this, PemesananSayaActivity.class));
                }
                else if (item.getItemId() == R.id.pemberitahuan){
                    toast.setText("Pemberitahuan belum tersedia");
                    toast.show();
                }
                else if (item.getItemId() == R.id.tambahPerpustakaan){
                    startActivity(new Intent(MainActivity.this, BuatPerpusActivity.class));
                }
                else if (item.getItemId() == R.id.kelolaPerpustakaan){
                    startActivity(new Intent(MainActivity.this, KelolaPerpusActivity.class));
                }
                else if (item.getItemId() == R.id.infoAplikasi){
                    startActivity(new Intent(MainActivity.this, InfoAplikasiActivity.class));
                }
                else if (item.getItemId() == R.id.keluar) {
                    session.setSessionBoolean(Session.LOGED_IN, false);
                    session.setSessionString("email", null);

                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();

                    mDrawerlayout.closeDrawers();
                }
                //End Loged In
                return false;
            }
        });
    }
    @Override
    protected void onResume() {
        checkSensorState();
        if (isConnected() && !isLocalDatabaseLoaded()){
            startBackgroundProccess();
            prepareFirebase();
        }
        else if (!isConnected() && isLocalDatabaseLoaded()){
            startBackgroundProccess();
            loadData();
        }
        else if (isConnected() && isLocalDatabaseLoaded()){
            startBackgroundProccess();
            loadData();
        }
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

            SearchView search = (SearchView) menu.findItem(R.id.search).getActionView();
            search.setSearchableInfo(manager.getSearchableInfo(getComponentName()));
            search.setQueryHint("Cari buku atau perpustakaan");
            search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    toast.setText("Cari belum tersedia");
                    toast.show();
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
        }
        return true;
    }

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
        if (session.getSessionBoolean(Session.LOGED_IN)){
            email = session.getSessionString("email");
            return true;
        }
        else {
            return false;
        }
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    private boolean isConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            return true;
        }
        else {
            return false;
        }
    }

    private void alertNotConnected() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Internet Anda mati, mohon nyalakan terlebih dahulu")
                .setCancelable(false)
                .setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                    }
                });
        builder.create().show();
    }

    private void alertNotConnectedButDataExist() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Saat ini aplikasi menggunakan data offline, nyalakan internet untuk data baru.")
                .setCancelable(false)
                .setPositiveButton("Nyalakan", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        builder.create().show();
    }

    private void alertNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("GPS Anda mati, apakah Anda ingin menyalakannya?")
                .setCancelable(false)
                .setPositiveButton("Nyalakan", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        builder.create().show();
    }

    private void startBackgroundProccess(){
        if (!isMyServiceRunning(DataFetcherFromJSON.class)){
            Log.d("Background proccess", "Running");
            Intent i= new Intent(this, DataFetcherFromJSON.class);
            startService(i);
        }
    }

    private void prepareFirebase(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("root").child("perpustakaan");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                loadData();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void loadData(){
        pbLoading = findViewById(R.id.pbLoading);
        pbLoading.setVisibility(View.GONE);

        Fragment fragment = getSupportFragmentManager().findFragmentByTag("home_fragment");
        if (fragment != null){
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.detach(fragment);
            fragmentTransaction.commit();
        }

        getSupportFragmentManager().popBackStack();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.mainContainer, new HomeFragment(), "home_fragment");
        fragmentTransaction.commit();
    }

    private void checkSensorState(){
        final LocationManager manager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );
        if (!manager.isProviderEnabled( LocationManager.GPS_PROVIDER )) {
            alertNoGps();
        }
        if (!isConnected() && !isLocalDatabaseLoaded()){
            alertNotConnected();
        }
        else if (!isConnected() && isLocalDatabaseLoaded()){
            alertNotConnectedButDataExist();
        }
    }

    private boolean isLocalDatabaseLoaded(){
        Cursor voidCursor = perpusDatabaseHelper.selectPerpus();
        if (voidCursor.getCount() == 0){
            return false;
        }
        else{
            return true;
        }
    }
}

