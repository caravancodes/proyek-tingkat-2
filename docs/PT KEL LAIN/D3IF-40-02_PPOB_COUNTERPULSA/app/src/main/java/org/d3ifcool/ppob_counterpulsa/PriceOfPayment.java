package org.d3ifcool.ppob_counterpulsa;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.d3ifcool.ppob_counterpulsa.model.PurchaseBalanceModel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class PriceOfPayment extends AppCompatActivity {

    private String file_data, file_name, id_purchase, action;
    private Bitmap bitmap;
    private File file;
    private Uri file_uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_of_payment);

        PurchaseBalanceModel purchaseBalanceModel = (PurchaseBalanceModel)getIntent().getExtras().getSerializable("dataTransaction");
        id_purchase = String.valueOf(purchaseBalanceModel.getId_purchase_balance());
        action = "uploadTransferAPI";

        Button uploadImage = findViewById(R.id.uploadImage);
        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                getFileUri();
                intent.putExtra(MediaStore.EXTRA_OUTPUT, file_uri);
                startActivityForResult(intent, 10);
            }
        });
    }

    public void getFileUri() {
        file_name = "testing.jpg";
        file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + File.separator + file_name);
        file_uri = Uri.fromFile(file);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 10 && resultCode == RESULT_OK){
            new Encode_image().execute();
        }
    }

    @SuppressLint("StaticFieldLeak")
    class Encode_image extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            bitmap = BitmapFactory.decodeFile(file_uri.getPath());
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);

            byte[] array = stream.toByteArray();
            file_data = Base64.encodeToString(array, 0);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            makeRequest();
        }
    }

    void makeRequest(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST, "http://counterpulsa.xyz/try/base/php/saldo.php", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("action", action);
                map.put("id", id_purchase);
                map.put("buktiPembayaran", file_name);
                map.put("buktiPembayaranFile", file_data);
                return map;
            }
        };
        requestQueue.add(request);
    }
}
