package com.example.sports;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class SportsActivity extends AppCompatActivity  implements DialogInterface.OnClickListener {


    private static final int NOTIFICATION_REMINDER_NIGHT = 1;

    private RecyclerView mRecycleView;
    private List<String> titles;
    private List<Integer> mImages;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports);
        Intent notifyIntent = new Intent(this, NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast
                (this, NOTIFICATION_REMINDER_NIGHT, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),
                1000 * 60 * 2, pendingIntent);


        mRecycleView = findViewById(R.id.recycleview);

        titles = new ArrayList<>();
        mImages = new ArrayList<>();

        adapter = new MyAdapter(this, titles, mImages);

        mImages.add(R.drawable.running);
        mImages.add(R.drawable.boxing);
        mImages.add(R.drawable.basketball);
        mImages.add(R.drawable.skiing);
        mImages.add(R.drawable.soccer);
        mImages.add(R.drawable.surfing);
        mImages.add(R.drawable.swimming);
        mImages.add(R.drawable.tennis);

        titles.add("Running");
        titles.add("Boxing");
        titles.add("BasketBall");
        titles.add("Skiing");
        titles.add("Soccer");
        titles.add("Surfing");
        titles.add("Swimming");
        titles.add("Tennis");

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        mRecycleView.setLayoutManager(gridLayoutManager);
        mRecycleView.setHasFixedSize(true);

        mRecycleView.setAdapter(adapter);


    }


    public void Profile(View view) {

        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);

    }

    @Override
    //inflate the design of the required menu on top of the activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem.OnActionExpandListener onActionExpandListener = new MenuItem.OnActionExpandListener() {
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

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.favorite:
                Toast.makeText(this, "Favorite", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(this, FavoriteActivity.class);
                startActivity(intent1);
                break;

            case R.id.latestSearch:
                Toast.makeText(this, "LatestSearch", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(this, FavoriteActivity.class);
                startActivity(intent2);
                break;
            case R.id.itemProfile:

        }
        return super.onOptionsItemSelected(item);


    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("ARE YOU SURE?");
        builder.setCancelable(false);
        builder.setPositiveButton("yes", this);
        builder.setNegativeButton("No", this);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        if (i == dialogInterface.BUTTON_POSITIVE) {
            super.onBackPressed();
            dialogInterface.cancel();
        }
        if (i == dialogInterface.BUTTON_NEGATIVE) {
            dialogInterface.cancel();
        }
    }


    public void List(View view) {
    }

}