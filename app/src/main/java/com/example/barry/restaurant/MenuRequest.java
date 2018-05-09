package com.example.barry.restaurant;

import android.content.Context;
import android.util.Log;

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
        void gotMenu(ArrayList<String> menu);
        void gotMenuError(String message);
    }

    private final String URL_MENU = "https://resto.mprog.nl/menu";
    private Context globalContext;
    private Callback gloabalActivity;

    @Override
    public void onErrorResponse(VolleyError error) {
        gloabalActivity.gotMenuError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {

        try {
            // write appropiate body
            MenuItem menu;

            

            // give the result back to the callback activity
            gloabalActivity.gotMenu(menu);
        } catch (JSONException e) {
            Log.e("MYAPP", "unexpected JSON exception", e);
            // Do something to recover ... or kill the app.
        }

    }

    public MenuRequest(Context context) {
        globalContext = context;
    }

    public void getMenu(Callback activity){
        gloabalActivity = activity;
        // get the menu from the api as a json
        RequestQueue queue = Volley.newRequestQueue(globalContext);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(URL_MENU,
                null, this, this);
        queue.add(jsonObjectRequest);
    }
}
