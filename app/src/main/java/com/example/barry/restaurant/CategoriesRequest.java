package com.example.barry.restaurant;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;




public class CategoriesRequest implements Response.Listener<JSONObject> , Response.ErrorListener {

    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    // global variables
    private final String URL_CATAGORIES = "https://resto.mprog.nl/categories";
    private Context globalContext;
    private Callback globalActivity;



    @Override
    public void onErrorResponse(VolleyError error) {
        globalActivity.gotCategoriesError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {

        try {
            // retrieve data from JSON
            JSONArray categoriesArray = response.getJSONArray("categories");
            ArrayList categories = new ArrayList<String>();
            for (int i = 0; i < categoriesArray.length(); i++) {
                categories.add(categoriesArray.getString(i)); // moet misschien getJSONstring zijn
            }

            // give the result back to the callback activity
            globalActivity.gotCategories(categories);
        } catch (JSONException e) {
            // catch and log JSON exceptions
            Log.e("MYAPP", "unexpected JSON exception", e);

            String message = "Something went wrong";
            int duration = Toast.LENGTH_SHORT;
            Toast.makeText(globalContext, message, duration).show();
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }

    public CategoriesRequest(Context context) {
        globalContext = context;
    }

    public void getCategories(Callback activity){
        globalActivity = activity;
        // get the categories from the api as a json
        RequestQueue queue = Volley.newRequestQueue(globalContext);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(URL_CATAGORIES,
                null, this, this);
        queue.add(jsonObjectRequest);
    }
}
