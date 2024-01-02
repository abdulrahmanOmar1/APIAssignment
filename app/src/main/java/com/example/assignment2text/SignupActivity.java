package com.example.assignment2text;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.assignment2text.R;
import com.google.gson.Gson;

public class SignupActivity extends AppCompatActivity {
    private EditText edtname , edtpass ,edtemail , edtPhone;
    private Button btnSignup ,btnLogin;
    private RadioGroup gender;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private Gson gson , gson2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_signup);
        setupSharedPrefs();
        setupfunction();
        onPauseTest();

    }

    private void onPauseTest() {
        String string=prefs.getString ("newuserSignUP","");
        gson2=new Gson ();
        User u =gson2. fromJson (string, User. class) ;
        if (!string. equals ("")) {
            String[] arr = string.split (",");
            if (!arr[0].toString ().equals (""))
                edtname.setText (u.getName ().toString ());
            if (!arr[1].toString ().equals (""))
                edtpass.setText (u.getPassword ().toString ());
            if (!arr[2].toString ().equals (""))
                edtPhone.setText (u.getPhone ().toString ());
            if (!arr[3].toString ().equals (""))
                edtemail.setText (u.getEmail ().toString ());
        }
            editor. putString ("newuserSignUP","");
            editor. commit();
    }

    private void setupSharedPrefs() {
        prefs= PreferenceManager.getDefaultSharedPreferences (this);
        editor = prefs.edit();
    }

    private void setupfunction() {
        edtname=findViewById (R.id.nameID);
        edtpass=findViewById (R.id.passID);
        edtemail=findViewById (R.id.emailID);
        edtPhone=findViewById (R.id.PhoneID);
        btnLogin=findViewById (R.id.btnLogin);
        btnSignup=findViewById (R.id.btnSignup);

    }

    public void btnSignup(View view) {
        String name = edtname.getText().toString();
        String pass = edtpass.getText().toString();
        String email = edtemail.getText().toString();
        String phone = edtPhone.getText().toString();

         gson = new Gson ();
        String str = prefs.getString ("data", "");
        User[] users = gson.fromJson (str, User[].class);

        if(name.isEmpty ()) {
            edtname.setHint ("Please Enter The UserName");
            edtname.setHintTextColor(Color.parseColor ("#FF8166"));
        }else if(pass.isEmpty ()){
            edtpass.setHint ("Please Enter The Password");
            edtpass.setHintTextColor(Color.parseColor ("#FF8166"));
        }else if(phone.isEmpty () ) {
            edtPhone.setHint ("Please Enter The your phone");
            edtPhone.setHintTextColor(Color.parseColor ("#FF8166"));
        }else if(email.isEmpty () ) {
            edtemail.setHint ("Please Enter The your Email");
            edtemail.setHintTextColor(Color.parseColor ("#FF8166"));
        }else if(!email.contains ("@")) {
            edtemail.setText ("");
            edtemail.setHint ("The email you entered is invalid");
            edtemail.setHintTextColor(Color.parseColor ("#FF8166"));
        }else {

            if (users == null) {
                users = new User[1];
                users[0] = new User (name, phone , email ,pass);

            } else {

                for (int i = 0; i <users.length; i++) {
                    User user = users[i];
                    if (user.getName().equals(name)) {
                        Toast.makeText(getApplicationContext(), "Name already registered", Toast.LENGTH_SHORT).show();
                        empty();
                        return;
                    }
                }

                User[] newUsers = new User[users.length + 1];
                for (int i = 0; i < users.length; i++) {
                    newUsers[i] = users[i];
                }
                newUsers[users.length] = new User (name, phone , email ,pass);
                users = newUsers;
            }
            String jsonTasks = gson.toJson (users);
            editor.putString ("data", jsonTasks);
            editor.commit ();

            Intent intent = new Intent (this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void btnlogin(View view) {
        Intent intent = new Intent (this, MainActivity.class);
        startActivity(intent);
    }

    private void empty() {
        edtname.setText ("");
        edtpass.setText ("");
        edtemail.setText ("");
        edtPhone.setText ("");

    }

    @Override
    protected void onPause() {
        String name = edtname.getText().toString();
        String pass = edtpass.getText().toString();
        String email = edtemail.getText().toString();
        String phone = edtPhone.getText().toString();

        User U1=new User(name,phone,email,pass);

        gson2 = new Gson ();
        String str2=gson2.toJson (U1);
        editor.putString ("newuserSignUP" , str2);
        editor.commit ();
        super.onPause ();
    }
}