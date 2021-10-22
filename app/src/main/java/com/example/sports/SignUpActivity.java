package com.example.sports;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {

    private EditText editTextName, editTextUsername, editTextPassword, editTextEmail, editTextBirthday, editTextPhoneNumber;
    private Button buttonSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void Signup(View view) {
        Intent intent = new Intent(this, SportsActivity.class);
        startActivity(intent);
    }
}