package org.d3ifcool.ppob_counterpulsa;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class PortalView extends AppCompatActivity {

    public static final String TAG_PORTAL_LOGIN = "portalLogin";
    public static final String TAG_PORTAL_REGISTRATION = "portalRegistration";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portal_view);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Fragment fragment = new PortalLogin();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.portalFrame, fragment, TAG_PORTAL_LOGIN);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        if (!isNetworkAvailable()){
            Toast.makeText(this, "Tidak terhubung ke internet.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (checkLastBackStack())
            moveTaskToBack(true);
    }

    private boolean checkLastBackStack(){
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(TAG_PORTAL_REGISTRATION);
        return fragment == null;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
