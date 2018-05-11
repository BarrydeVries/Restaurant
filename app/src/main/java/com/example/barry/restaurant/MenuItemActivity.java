package com.example.barry.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);

        Intent intent = getIntent();

        double prize = (double) intent.getSerializableExtra("prize");
        String name = (String) intent.getSerializableExtra("name");
        String description = (String) intent.getSerializableExtra("description");
        String image = (String) intent.getSerializableExtra("image");

        ImageView imageView = findViewById(R.id.image);
        TextView titleView = findViewById(R.id.title);
        TextView descriptionView = findViewById(R.id.description);
        TextView prizeView = findViewById(R.id.prize);

        titleView.setText(name);
        descriptionView.setText(description);
        prizeView.setText(String.valueOf(prize));
    }
}
