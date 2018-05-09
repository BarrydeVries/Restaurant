package com.example.barry.restaurant;

import android.content.Context;
import android.util.Log;

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

    Callback gloabalActivity;

    final String URL_CATAGORIES = "https://resto.mprog.nl/categories";
    Context context;



    @Override
    public void onErrorResponse(VolleyError error) {
        gloabalActivity.gotCategoriesError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {

        try {
            JSONArray categoriesArray = response.getJSONArray("categories");
            ArrayList categories = new ArrayList<String>();
            for (int i = 0; i < categoriesArray.length(); i++){
                categories.add(categoriesArray.getJSONObject(i));
            }

            // give the result back to the callback activity
            gloabalActivity.gotCategories(categories);
        } catch (JSONException e) {
            Log.e("MYAPP", "unexpected JSON exception", e);
            // Do something to recover ... or kill the app.
        }

    }

    public CategoriesRequest(Context context) {


    }

    public void getCategories(Callback activity){
        gloabalActivity = activity;
        // get the categories from the api as a json
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(URL_CATAGORIES,
                null, this, this);
        queue.add(jsonObjectRequest);

    }
}
