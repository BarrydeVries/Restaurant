package com.example.barry.restaurant;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

    public class MenuActivity extends AppCompatActivity implements MenuRequest.Callback {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_menu);
            Intent intent = getIntent();

            String categorie = (String) intent.getSerializableExtra("categorie");

            // create a request to get the menu's
            MenuRequest request = new MenuRequest(MenuActivity.this);
            request.getMenu(this, categorie);
        }



        @Override
    public void gotMenu(ArrayList<MenuItem> menu) {
        MenuAdapter adapter = new MenuAdapter(this, R.layout.menu_item, menu);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        final ArrayList<MenuItem> finalMenu = menu;


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // do something smart here.
                MenuItem menuItem = finalMenu.get(position);
                Intent intent = new Intent(MenuActivity.this, MenuItemActivity.class);
                intent.putExtra("description", menuItem.getDescription());
                intent.putExtra("image", menuItem.getImageUrl());
                intent.putExtra("name", menuItem.getName());
                intent.putExtra("prize", menuItem.getPrice());
                startActivity(intent);
            }
        });
    }

    @Override
    public void gotMenuError(String message) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast.makeText(context, message, duration).show();

    }
}
