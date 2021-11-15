package com.example.sports;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Item> {
    private Context context;
    private int resource;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<Item> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;//this is the item row resource
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
            Button itemButton = view.findViewById(R.id.buttonMore);
            itemButton.setOnClickListener(new View.OnClickListener() { //to the add button
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "this item was added to shopping cart", Toast.LENGTH_SHORT).show();
                }
            });

            imageView.setImageResource(item.getResid());
            textName.setText(item.getDescription());
            textLocation.setText(item.getDescription());
            textDays.setText(item.getDescription());
            textHours.setText(item.getDescription());

        }
        return view;
    }
}
