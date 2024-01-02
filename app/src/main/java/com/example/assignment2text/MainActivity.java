package com.example.assignment2text;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assignment2text.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText Mainedtname,Mainedtpass;
    private Button MainbtnLogin ,MainbtnCancel ,MainbtnSignup;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private User[] users;
    private CheckBox chk;
    public static final String NAME = "NAME";
    public static final String PASS = "PASS";
    public static final String FLAG = "FLAG";
    private boolean flag = false;
    private String str;
    private List<String> arraylist;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        setupSharedPrefs();
        setupfunction();
        check();

    }

    private void setupSharedPrefs() {
        prefs= PreferenceManager.getDefaultSharedPreferences (this);
        editor = prefs.edit();
    }

    private void setupfunction() {
        Mainedtname=findViewById (R.id.Mainedtname);
        Mainedtpass=findViewById (R.id.Mainedtpass);
        MainbtnLogin=findViewById (R.id.btnLogin);
        MainbtnSignup=findViewById (R.id.MainbtnSignup);
        MainbtnCancel=findViewById (R.id.MainbtnCancel);
        chk=findViewById (R.id.chk);

    }

    public void MainbtnLogin(View view) {
        String name = Mainedtname.getText ().toString ();
        String pass = Mainedtpass.getText ().toString ();

        arraylist = new ArrayList<> ();
        Gson gson = new Gson ();
        str = prefs.getString ("data", "");
        users = gson.fromJson (str, User[].class);


        if ((str.equals("")) || (str.equals("[]"))) {
            Toast.makeText (getApplicationContext(), "There is no User yet!", Toast.LENGTH_SHORT).show ();
        }else {

            if(name.isEmpty ()) {
                Mainedtname.setHint ("Please Enter The UserName");
                Mainedtname.setHintTextColor(Color.parseColor ("#FF8166"));
            }else if(pass.isEmpty ()) {
                Mainedtpass.setHint ("Please Enter The Password");
                Mainedtpass.setHintTextColor (Color.parseColor ("#FF8166"));
            }else {
                for (int i = 0; i < users.length; i++) {
                    if (name.equals (users[i].getName ()) && pass.equals (users[i].getPassword ())) {
                        Intent intent = new Intent (MainActivity.this, HomeActivity.class);
                        startActivity (intent);
                    }
                }
            }
        }
    }

    public void MainOnClickSignup(View view) {
        Intent intent=new Intent (MainActivity.this ,SignupActivity.class);
        startActivity (intent);
    }


    public void MainOnClickCancel(View view) {
        Mainedtname.setText ("");
        Mainedtpass.setText ("");
    }

    private void check(){
        flag = prefs.getBoolean (FLAG , false);
        if(flag){
            String name = prefs.getString (NAME,"");
            String password = prefs.getString (PASS ,"");
            Mainedtname.setText (name);
            Mainedtpass.setText (password);
            chk.setChecked (true);
        }

    }
    public void btnLoginOnClick(View view) {
        String name = Mainedtname.getText().toString();
        String password = Mainedtpass.getText().toString();

        if(chk.isChecked() ) {
        if (!flag) {
            editor.putString (NAME, name);
            editor.putString (PASS, password);
            editor.putBoolean (FLAG, true);
            editor.commit ();
        }
    }
    }
}