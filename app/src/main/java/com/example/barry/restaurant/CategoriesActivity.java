package com.example.barry.restaurant;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity implements CategoriesRequest.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        // create a request to get the categories
        CategoriesRequest request = new CategoriesRequest(CategoriesActivity.this);
        request.getCategories(this);
    }

    @Override
    public void gotCategories(ArrayList<String> categories) {
        ListView lv = findViewById(R.id.menuItems);

        // set up adapter to fill the category list
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                categories);
        lv.setAdapter(arrayAdapter);


        // set up on click for the categories
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CategoriesActivity.this, MenuActivity.class);
                String category = (String) parent.getItemAtPosition(position);
                intent.putExtra("category", category);
                startActivity(intent);
            }
        });
    }

    @Override
    public void gotCategoriesError(String message) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast.makeText(context, message, duration).show();
    }
}
