package com.example.barry.restaurant;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MenuRequest implements Response.Listener<JSONObject>, Response.ErrorListener{
    public interface Callback {
        void gotMenu(ArrayList<MenuItem> menu);
        void gotMenuError(String message);
    }

    private final String URL_MENU = "https://resto.mprog.nl/menu";
    private Context globalContext;
    private Callback globalActivity;
    private String globalCategorie;

    @Override
    public void onErrorResponse(VolleyError error) {
        globalActivity.gotMenuError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {

        try {
            JSONArray menuArray = response.getJSONArray("items");
            ArrayList menu = new ArrayList<MenuItem>();
            for (int i = 0; i < menuArray.length();i++) {
                if (menuArray.getJSONObject(i).getString("category").equals(globalCategorie)) {
                    // create a new menu item, fill it and add it to list
                    MenuItem menuItem = new MenuItem();
                    menuItem.setName(menuArray.getJSONObject(i).getString("name"));
                    menuItem.setCategory(menuArray.getJSONObject(i).getString("category"));
                    menuItem.setDescription(menuArray.getJSONObject(i).getString("description"));
                    menuItem.setImageUrl(menuArray.getJSONObject(i).getString("image_url"));
                    menuItem.setPrice(menuArray.getJSONObject(i).getDouble("price"));
                    menu.add(menuItem);
                }
            }

            // give the result back to the callback activity
            globalActivity.gotMenu(menu);
        } catch (JSONException e) {
            Log.e("MYAPP", "unexpected JSON exception", e);
            String message = "Something went wrong";
            int duration = Toast.LENGTH_SHORT;
            Toast.makeText(globalContext, message, duration).show();
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }

    public MenuRequest(Context context) {
        globalContext = context;
    }

    public void getMenu(Callback activity, String categorie){
        globalActivity = activity;
        globalCategorie = categorie;
        // get the menu from the api as a json
        RequestQueue queue = Volley.newRequestQueue(globalContext);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(URL_MENU,
                null, this, this);
        queue.add(jsonObjectRequest);
    }
}
