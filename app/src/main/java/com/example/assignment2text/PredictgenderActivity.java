package com.example.assignment2text;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class PredictgenderActivity extends AppCompatActivity {
    private final String url = "https://api.genderize.io";
    private RequestQueue queue;
    private EditText edtName;
    private TextView txtPredictedGender;
    private String edt ,ur ,MF,result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView (R.layout.activity_predictgender);

        setupfuntion();
    }

    public void setupfuntion() {
        edtName = findViewById(R.id.edtName);
        txtPredictedGender = findViewById(R.id.txtPredictedGender);
        queue = Volley.newRequestQueue(this);
    }
    public void predictgender(View view) {
        edt = edtName.getText().toString();
        if (edt.isEmpty()) {
            txtPredictedGender.setText("Please Enter a Name To Predicted Gender ");
        } else {
             ur = url + "?name=" + edt;
            StringRequest stringRequest = new StringRequest(Request.Method.GET, ur,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                MF = jsonObject.optString("gender", "Unknown");
                                result = "Predicted Gender: " + MF +" :)";
                                txtPredictedGender.setText(result);

                                InputMethodManager input = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                input.hideSoftInputFromWindow(view.getWindowToken(), 0);

                            } catch (JSONException exception) {
                                exception.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(PredictgenderActivity.this, "Error predicting gender", Toast.LENGTH_SHORT).show();
                }
            });

            queue.add(stringRequest);
        }
    }

    public void backfunctionG(View view) {
        Intent intent=new Intent (PredictgenderActivity.this , HomeActivity.class);
        startActivity (intent);
        finish ();
    }
}
