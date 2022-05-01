package com.example.sports;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProfileDetailsActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_details);

        buttonImage = findViewById(R.id.buttonImage);
        buttonImage.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

    }
}