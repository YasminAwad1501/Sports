package com.example.sports;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListViewSports extends AppCompatActivity {



    //the object of the view - design
    private ListView myListView;
    //the object for the adapter connecting the data to the view
    private CustomAdapter myAdapter;
    //object containing the items to be displayed - Data
    private ArrayList<Item> list;

    private ArrayList<Item> backup;


    //get instance of authentication project in FB console
    //gets the root of the real time database in the FB console

    private FirebaseAuth maFirebaseAuth = FirebaseAuth.getInstance();
    private FirebaseDatabase database=FirebaseDatabase.getInstance("https://sports-931b0-default-rtdb.europe-west1.firebasedatabase.app/");
    private DatabaseReference myRef;
    private TextView categoryTV;
    private String searchView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_sports);

        //build a ref for user relate data in real time database using user ID

        //adds an item to the firebase under the referenced specified
        categoryTV = findViewById(R.id.category);



        list = new ArrayList<>();
        String UID = maFirebaseAuth.getUid();
        String category = getIntent().getStringExtra("category");
        categoryTV.setText(category);
        myRef = database.getReference(category);

/*
        list = new ArrayList<>();
        list.add(new Item("China's soccer", "China", "Weekdays", "08:00 - 18:00", R.drawable.soccerchina, false));
        list.add(new Item("Soccer courts", "Italy", "Monday - Saturday", "05:30 - 23:00", R.drawable.socceritaly, false));
        list.add(new Item("Soccer's place", "London", "Weekdays", "06:00 - 00:00", R.drawable.soccerlondon, false));
        list.add(new Item("Courts", "Turkey", "Sunday - Friday", "04:30 - 19:00", R.drawable.soccerturkey, false));


        myRef.push().setValue(new Item("China's soccer", "China", "Weekdays", "08:00 - 18:00", R.drawable.soccerchina, false));
        myRef.push().setValue(new Item("Soccer courts", "Italy", "Monday - Saturday", "05:30 - 23:00", R.drawable.socceritaly, false));
        myRef.push().setValue(new Item("Soccer's place", "London", "Weekdays", "06:00 - 00:00", R.drawable.soccerlondon, false));
        myRef.push().setValue(new Item("Courts", "Turkey", "Sunday - Friday", "04:30 - 19:00", R.drawable.soccerturkey, false));
*/

        //reference to the list view so it can programmed
        myListView = findViewById(R.id.myListView);




        //connect adapter with data
        myAdapter = new CustomAdapter(this, R.layout.sport_row,list);

        //connect adapter with view
        myListView.setAdapter(myAdapter);

        //connects click listener to items in the
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "Item:" + list.get(i), Toast.LENGTH_LONG).show();
            }
        });
        myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                list.remove(i);
                myAdapter.notifyDataSetChanged();
                return false;
            }
        });




        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Item item=dataSnapshot.getValue(Item.class);
                    list.add(item);
                    myAdapter.notifyDataSetChanged();
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    //inflate the design of the required menu on top of the activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_listview, menu);
       /* MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("search");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                return false;
            }
        });
    */


        return super.onCreateOptionsMenu(menu);

      /*
        MenuItem.OnActionExpandListener onActionExpandListener = new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem menuItem) {
                return false;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                return false;
            }
        */




    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.favorite:
                Toast.makeText(this, "Favorite", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(this, FavoriteActivity.class);
                startActivity(intent1);
                break;
            case R.id.search:



        }
        return super.onOptionsItemSelected(item);

    }



        public void search(String toSearch){
        ArrayList<Item> backup = new ArrayList<>();
        for(Item item: backup){
            if(item.getName().equals(toSearch))
            {
                backup.add(item);
                myAdapter.notifyDataSetChanged();
            }
        }
    }
}