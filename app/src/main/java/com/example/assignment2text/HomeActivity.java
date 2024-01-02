package com.example.assignment2text;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class HomeActivity extends AppCompatActivity {
    private Button Homebtnpredict;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_home);
        setupfunction();
    }

    private void setupfunction() {
        Homebtnpredict = findViewById (R.id.Homebtnpredict);
    }

    public void OnclickPredictAge(View view) {
        Intent intent = new Intent (this ,PredictAgeActivity.class );
        startActivity (intent);
    }

    public void OnclickPredictNationality(View view) {
        Intent intent = new Intent (this ,BitcoinPriceextend.class );
        startActivity (intent);
    }

    public void OnclickPredictgender(View view) {
        Intent intent = new Intent (this ,PredictgenderActivity.class );
        startActivity (intent);
    }

    public void Logoutfunction(View view) {
        Intent intent = new Intent (this ,MainActivity.class );
        startActivity (intent);
        finish ();

    }
}