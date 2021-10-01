package org.d3ifcool.ppob_counterpulsa;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.d3ifcool.ppob_counterpulsa.controller.StorageController;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Andy on 4/23/2018.
 */

public class FragmentProfile extends Fragment {
    private boolean status;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final EditText profile_identity_number = view.findViewById(R.id.profile_identity_number),
                profile_full_name = view.findViewById(R.id.profile_full_name),
                profile_email = view.findViewById(R.id.profile_email),
                profile_phone_number = view.findViewById(R.id.profile_phone_number),
                profile_address = view.findViewById(R.id.profile_address),
                profile_password = view.findViewById(R.id.profile_password);
        final StorageController storageController = new StorageController(getActivity());
        Cursor getUser = storageController.getUserSession();
        if (getUser != null){
            if (getUser.moveToFirst()){
                profile_identity_number.setText(getUser.getString(getUser.getColumnIndex("identity_number")));
                profile_full_name.setText(getUser.getString(getUser.getColumnIndex("full_name")));
                profile_email.setText(getUser.getString(getUser.getColumnIndex("email")));
                profile_phone_number.setText(getUser.getString(getUser.getColumnIndex("phone_number")));
                profile_address.setText(getUser.getString(getUser.getColumnIndex("address")));
                profile_password.setText(getUser.getString(getUser.getColumnIndex("password")));

                this.status = true;

                profile_password.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        final int DRAWABLE_LEFT = 0;
                        final int DRAWABLE_TOP = 1;
                        final int DRAWABLE_RIGHT = 2;
                        final int DRAWABLE_BOTTOM = 3;
                        if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                            if (motionEvent.getX() >= (profile_password.getRight() - profile_password.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())){
                                if (status == true) {
                                    profile_password.setTransformationMethod(null);
                                    status = false;
                                }
                                else{
                                    profile_password.setTransformationMethod(new PasswordTransformationMethod());
                                    status = true;
                                }
                                return true;
                            }
                        }
                        return false;
                    }
                });
            }
        }

        Button profile_button = view.findViewById(R.id.profile_button);
        profile_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor getUser = storageController.getUserSession();
                if (getUser.moveToFirst()){StringBuilder packageRegistrationToPOST;
                    packageRegistrationToPOST = new StringBuilder();
                    packageRegistrationToPOST.append("action").append("=").append("updateUserAPI").append("&");
                    packageRegistrationToPOST.append("identityNumber").append("=").append(profile_identity_number.getText().toString()).append("&");
                    packageRegistrationToPOST.append("fullName").append("=").append(profile_full_name.getText().toString()).append("&");
                    packageRegistrationToPOST.append("email").append("=").append(profile_email.getText().toString()).append("&");
                    packageRegistrationToPOST.append("phoneNumber").append("=").append(profile_phone_number.getText().toString()).append("&");
                    packageRegistrationToPOST.append("address").append("=").append(profile_address.getText().toString()).append("&");
                    packageRegistrationToPOST.append("password").append("=").append(profile_password.getText().toString()).append("&");
                    packageRegistrationToPOST.append("idUser").append("=").append(String.valueOf(getUser.getInt(getUser.getColumnIndex("id_user"))));

                    if (isNetworkAvailable()) {
                        new updateProfile(packageRegistrationToPOST, storageController, String.valueOf(getUser.getInt(getUser.getColumnIndex("id_user")))).execute();
                    }
                    else{
                        Toast.makeText(getActivity(), "Tidak terhubung ke internet.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profile_fragment, container, false);
    }

    class updateProfile extends AsyncTask<Void, Void, Boolean>{

        private StringBuilder packageProfileOrderToPOST;
        private StorageController storageController;
        private ProgressDialog progressDialog;
        private String idUser;

        updateProfile(StringBuilder packageProfileOrderToPOST, StorageController storageController, String idUser) {
            this.packageProfileOrderToPOST = packageProfileOrderToPOST;
            this.storageController = storageController;
            this.idUser = idUser;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("Mohon tunggu sebentar");
            progressDialog.setCancelable(false);
            progressDialog.setMax(100);
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            try {
                URL targetUrl = new URL("http://counterpulsa.xyz/try/base/php/user.php");
                HttpURLConnection connection = (HttpURLConnection) targetUrl.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
                dataOutputStream.writeBytes(this.packageProfileOrderToPOST.toString());
                dataOutputStream.flush();
                dataOutputStream.close();

                InputStream inputStream = new BufferedInputStream(connection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null)
                    stringBuilder.append(line).append('\n');
                if (!TextUtils.isEmpty(stringBuilder.toString())) {
                    try {
                            JSONObject jsonObject = new JSONObject(stringBuilder.toString());

                        System.out.println(stringBuilder.toString());

                            ArrayList<String> columnName = new ArrayList<>();
                            columnName.add("id_user"); columnName.add("identity_number");
                            columnName.add("full_name"); columnName.add("email");
                            columnName.add("phone_number"); columnName.add("address");
                            columnName.add("password"); columnName.add("id_balance");
                            columnName.add("current_balance");columnName.add("out_balance");

                            ArrayList<String> rowValues = new ArrayList<>();
                            rowValues.add(Integer.toString(jsonObject.getInt("id_user")));
                            rowValues.add(jsonObject.getString("no_ktp"));
                            rowValues.add(jsonObject.getString("nama_lengkap"));
                            rowValues.add(jsonObject.getString("email"));
                            rowValues.add(jsonObject.getString("no_hp"));
                            rowValues.add(jsonObject.getString("alamat"));
                            rowValues.add(jsonObject.getString("password"));
                            rowValues.add(Integer.toString(jsonObject.getInt("id_saldo")));
                            rowValues.add(Integer.toString(jsonObject.getInt("current_saldo")));
                            rowValues.add(Integer.toString(jsonObject.getInt("saldo_out")));

                            this.storageController.removeUserSession();
                            return this.storageController.setUserSession(columnName, rowValues);
                    }
                    catch(JSONException e){
                        e.printStackTrace();
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (!aBoolean){
                Toast.makeText(getActivity(), "Gagal menghubungi server", Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(getActivity(), "Berhasil menyimpan", Toast.LENGTH_LONG).show();
            }
            progressDialog.dismiss();
        }
    }
}
