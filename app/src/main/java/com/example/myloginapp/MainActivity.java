package com.example.myloginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.EditText;
import com.google.android.material.button.MaterialButton;

import com.example.myloginapp.model.ResObj;
import com.example.myloginapp.remote.ApiUtils;
import com.example.myloginapp.remote.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText edtNisn;
    EditText edtToken;
    UserService userService;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNisn = (EditText) findViewById(R.id.edtNisn);
        edtToken = (EditText) findViewById(R.id.edtToken);
        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);
        userService = ApiUtils.getUserService();
        
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nisn = edtNisn.getText().toString();
                String token = edtToken.getText().toString();
                //validate form
                if(validateLogin(nisn, token)){
                    //do login
                    doLogin(nisn, token);
                }
            }
        });
    }

    private boolean validateLogin(String nisn, String token){
        if(nisn == null || nisn.trim().length() == 0){
            Toast.makeText(this, "NISN is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(token == null || token.trim().length() == 0){
            Toast.makeText(this, "Token is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    
    private void doLogin(final String nisn,final String token){
        Call call = userService.login(nisn,token);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if(response.isSuccessful()){
                    ResObj resObj = (ResObj) response.body();
                    if(resObj.getMessage().equals("true")){
                        //login start main activity
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        intent.putExtra("nisn", nisn);
                        startActivity(intent);

                    } else {
                        Toast.makeText(MainActivity.this, "The nisn or password is incorrect", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Error! Please try again!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}