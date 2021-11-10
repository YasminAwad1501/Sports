package com.example.sports;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SportsActivity extends AppCompatActivity  {

    private Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports);

        buttonBack=findViewById(R.id.buttonBack);


    }

    public void back(View view){
        Intent intent = new Intent (this, LogInActivity.class);
        startActivity(intent);
    }



    public void Profile(View view) {

        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);

    }

    @Override
    //inflate the design of the required menu on top of the activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem.OnActionExpandListener onActionExpandListener=new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem menuItem) {
                return false;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                return false;
            }

        };
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item){

        switch (item.getItemId()){
            case R.id.settingsMenu:
                Toast.makeText(this, "Favorite", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent (this, FavoriteActivity.class);
                startActivity(intent);
                break;

            case R.id.exitMenu:
                // closeApplication();
                break;
        }
        return super.onOptionsItemSelected(item);


    }



    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("ARE YOU SURE?");
        builder.setCancelable(false);
        builder.setPositiveButton("yes", (DialogInterface.OnClickListener) this);
        builder.setNegativeButton("No", (DialogInterface.OnClickListener) this);
        AlertDialog dialog=builder.create();
        dialog.show();
    }


    public void onClick(DialogInterface dialogInterface, int i) {
        if (i== dialogInterface.BUTTON_POSITIVE)
        {
            super.onBackPressed();
            dialogInterface.cancel();
        }
        if (i== dialogInterface.BUTTON_NEGATIVE)
        {
            dialogInterface.cancel();
        }
    }




}