package com.example.sports;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogInActivity extends AppCompatActivity  {


    private static final String TAG = "FIREBASE";
    //declaring all
    private EditText editTextUsername, editTextPassword;
    private Button buttonLogIn;
    private FirebaseAuth mAuth;
    private Intent musicIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        SharedPreferences sp = getSharedPreferences("settings",MODE_PRIVATE);
        String email = sp.getString("email","");
        String password = sp.getString("password","");

        //returns reference to the instance of the project Firebase
        mAuth = FirebaseAuth.getInstance();

        //this will start the service which in turn will the music
        musicIntent = new Intent(this, MusicService.class);
        startService(musicIntent);

        buttonLogIn = findViewById(R.id.buttonLogin);
        editTextUsername = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);


    }





    public void login(View view) {
        if (!editTextUsername.getText().toString().equals(""))
        {

            //saving email and password of user in local  file for future use
            //create sp file
            SharedPreferences sp = getSharedPreferences("settings",MODE_PRIVATE);
            //open editor for editing
            SharedPreferences.Editor editor = sp.edit();
            //write the wanted settings
            editor.putString("email", editTextUsername.getText().toString());
            editor.putString("password", editTextPassword.getText().toString());
            //save and close file
            editor.commit();


            login(editTextUsername.getText().toString(), editTextPassword.getText().toString());
        }

    }


    public void Signup(View view) {

        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);

    }

    public void login(String email,String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent i = new Intent(LogInActivity.this, SportsActivity.class);
                            startActivity(i);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LogInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }



}