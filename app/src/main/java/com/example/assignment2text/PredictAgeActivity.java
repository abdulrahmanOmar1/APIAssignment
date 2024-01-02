package com.example.assignment2text;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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

public class PredictAgeActivity extends AppCompatActivity {
    private final String url = "https://api.agify.io";
    private EditText edtPredictAge;
    private RequestQueue queue;
    private TextView txtPredictAge;
    private String txt , ur ,result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predict_age);
        setupfuntion();
    }

    public void setupfuntion() {
        edtPredictAge = findViewById(R.id.edtPredictAge);
        txtPredictAge = findViewById(R.id.txtPredictAge);
        queue = Volley.newRequestQueue(this);
    }
    public void btnOnclick(View view) {
         txt = edtPredictAge.getText().toString();
        if (txt.isEmpty()) {
            txtPredictAge.setText("enter name to predict his age :)");
        } else {
             ur = url + "?name=" + txt;
            StringRequest stringRequest = new StringRequest(Request.Method.GET, ur,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                             result = "";
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int age = jsonObject.getInt("age");
                                result = "Predicted Age is " + age + " :)";
                                txtPredictAge.setText(result);

                                InputMethodManager input = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                input.hideSoftInputFromWindow(view.getWindowToken(), 0);
                            } catch (JSONException exception) {
                                exception.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(PredictAgeActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            });
            queue.add(stringRequest);
        }
    }
    public void backfunction(View view) {
        Intent intent=new Intent (PredictAgeActivity.this , HomeActivity.class);
        startActivity (intent);
        finish ();
    }
}
