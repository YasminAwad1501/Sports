package com.example.sports;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CustomAdapter extends ArrayAdapter<Item> {
    private Context context;
    private int resource;
    private FirebaseAuth maFirebaseAuth = FirebaseAuth.getInstance();
    private FirebaseDatabase database=FirebaseDatabase.getInstance("https://sports-931b0-default-rtdb.europe-west1.firebasedatabase.app/");
    private DatabaseReference myRef;

    private List<Item> objects;
    private ArrayList<Item> arrayList;

    public int getCount(){
        return objects.size();
    }

    public Item getItem(int position){
        return objects.get(position);
    }

    public long getItemId(int position){
        return position;
    }

    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<Item> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;//this is the item row resource

        this.objects = objects;
        this.arrayList = new ArrayList<>();
        this.arrayList.addAll(objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //position -
        View view = convertView;
        if (view == null)
            view = LayoutInflater.from(context).inflate(resource, parent, false);
        Item item = getItem(position); //method from the android studio, not related to item object
        if (item != null){
            ImageView imageView = view.findViewById(R.id.imageItem);
            TextView textName = view.findViewById(R.id.textName);
            TextView textLocation = view.findViewById(R.id.textLocation);
            TextView textDays = view.findViewById(R.id.textDays);
            TextView textHours = view.findViewById(R.id.textHours);
            ImageButton starButton = view.findViewById(R.id.buttonFavorite);
            ImageButton itemMassage = view.findViewById(R.id.buttonMassage);
            starButton.setOnClickListener(new View.OnClickListener() { //to the add button
                @Override
                public void onClick(View view) {
                    String UID =maFirebaseAuth.getUid();
                    myRef =database.getReference("favourites/"+UID);
                    myRef.push().setValue(item);
                    starButton.setImageResource(R.drawable.fill_star);
                    Toast.makeText(context, "This item was added to favourites", Toast.LENGTH_SHORT).show();
                }
            });

            textName.setText(item.getName());
            textLocation.setText(item.getLocation());
            textDays.setText(item.getDays());
            textHours.setText(item.getHours());
            imageView.setImageResource(item.getImage());
            starButton.setImageResource(R.drawable.star_icon);
            itemMassage.setImageResource(R.drawable.massage_icon);
        }
        return view;
    }

    public void filter(String s){
        s=s.toLowerCase(Locale.getDefault());
        objects.clear();
        if (s.length()==0)
            objects.addAll(arrayList);
        else {
            for (Item item: arrayList){
                if (item.getName().toLowerCase(Locale.getDefault()).contains(s))
                    objects.add(item);
            }
        }


        notifyDataSetChanged();
    }



}
