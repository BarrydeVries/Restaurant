package com.example.barry.restaurant;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MenuAdapter extends ArrayAdapter<MenuItem> {
    private ArrayList menu;


    public MenuAdapter(@NonNull Context context, int resource, @NonNull ArrayList<MenuItem> objects) {
        super(context, resource, objects);
        menu = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.menu_item, parent, false);
        }
        // get access to relevant xml objects
        ImageView photo = convertView.findViewById(R.id.image);
        TextView name = convertView.findViewById(R.id.title);
        TextView prize = convertView.findViewById(R.id.prize);

        MenuItem menuItem = (MenuItem) menu.get(position);

        // fill in the objects content
        name.setText(menuItem.getName());
        prize.setText(String.valueOf(menuItem.getPrice()));



        return convertView;
    }
}
