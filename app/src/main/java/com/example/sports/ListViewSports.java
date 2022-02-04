package com.example.sports;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListViewSports extends AppCompatActivity {

    //the object of the view - design
    private ListView myListView;
    //the object for the adapter connecting the data to the view
    private CustomAdapter myAdapter;
    //object containing the items to be displayed - Data
    private ArrayList<Item> list;

    //get instance of authentication project in FB console
    //gets the root of the real time database in the FB console

    private FirebaseAuth maFirebaseAuth = FirebaseAuth.getInstance();
    private FirebaseDatabase database=FirebaseDatabase.getInstance("https://sports-931b0-default-rtdb.europe-west1.firebasedatabase.app/");
    private DatabaseReference myRef;
    private TextView categoryTV;
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
        list.add(new Item("Dubai's tennis", "Dubai", "Weekdays", "8:00 - 18:00", R.drawable.tennisdubai));
        list.add(new Item("Tennis courts", "Holland", "Monday - Saturday", "05:30 - 23:00", R.drawable.tennisholand));
        list.add(new Item("Tennis's place", "India", "Weekdays", "6:00 - 00:00", R.drawable.tennisindia));
        list.add(new Item("Courts", "Israel", "Sunday - Friday", "4:30 - 19:00", R.drawable.tennisisrael));


        myRef.push().setValue(new Item("Dubai's tennis", "Dubai", "Weekdays", "8:00 - 18:00", R.drawable.tennisdubai));
        myRef.push().setValue(new Item("Tennis courts", "Holland", "Monday - Saturday", "05:30 - 23:00", R.drawable.tennisholand));
        myRef.push().setValue(new Item("Tennis's place", "India", "Weekdays", "6:00 - 00:00", R.drawable.tennisindia));
        myRef.push().setValue(new Item("Courts", "Israel", "Sunday - Friday", "4:30 - 19:00", R.drawable.tennisisrael));
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



}