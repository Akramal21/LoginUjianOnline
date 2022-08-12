package com.example.myloginapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    TextView txtNisn;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtNisn = (TextView) findViewById(R.id.txtUsername);

        Bundle extras = getIntent().getExtras();
        String nisn;

        if(extras != null){
            nisn = extras.getString("vnisn");
            txtNisn.setText("Welcome " + nisn);
        }
    }
}