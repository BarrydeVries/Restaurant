package com.example.barry.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MenuItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);

        // retrieve variables from intent
        Intent intent = getIntent();
        double prize = (double) intent.getSerializableExtra("prize");
        String name = (String) intent.getSerializableExtra("name");
        String description = (String) intent.getSerializableExtra("description");
        String image = (String) intent.getSerializableExtra("image");

        // get id's of all views
        ImageView imageView = findViewById(R.id.image);
        TextView titleView = findViewById(R.id.title);
        TextView descriptionView = findViewById(R.id.description);
        TextView prizeView = findViewById(R.id.prize);

        // fill views with content
        titleView.setText(name);
        descriptionView.setText(description);
        prizeView.setText(String.valueOf(prize));
        Picasso.get().load(image).into(imageView);
    }
}
