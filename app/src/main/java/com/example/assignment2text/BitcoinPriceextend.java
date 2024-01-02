package com.example.assignment2text;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class BitcoinPriceextend  extends AppCompatActivity {
    private final String url = "https://api.coindesk.com/v1/bpi/currentprice.json";
    private RequestQueue queue;
    private TextView txtResultBitcin;
    private String text , result;
    private JSONObject B2 ,B1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bitcoin_priceextends_activity);
        setupfunction();
    }
    private void setupfunction() {
        txtResultBitcin = findViewById(R.id.txtResultBitcin);
        queue = Volley.newRequestQueue(this);
    }
    public void btnRefresh_Click(View view) {
         text="";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                             B1 = response.getJSONObject("bpi");
                             B2 = B1.getJSONObject("USD");
                             result = B2.getString("rate");
                             text="Bitcoin Price Now -->  $" +result;
                            txtResultBitcin.setText(text);
                        } catch (JSONException e) {
                            Toast.makeText(BitcoinPriceextend.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(BitcoinPriceextend.this, "Error get price", Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(jsonObjectRequest);
    }

    public void backfunctionB(View view) {
        Intent intent=new Intent (BitcoinPriceextend.this , HomeActivity.class);
        startActivity (intent);
        txtResultBitcin.setText ("");
        finish ();

        }

}
