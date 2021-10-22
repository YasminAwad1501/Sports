package com.example.sports;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LogInActivity extends AppCompatActivity  {


    private EditText editTextUsername, editTextPassword;
    private Button buttonLogIn, buttonSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

    }

    public void login(View view) {
        Intent intent = new Intent(this, SportsActivity.class);
        startActivity(intent);
    }


    public void Signup(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }


}