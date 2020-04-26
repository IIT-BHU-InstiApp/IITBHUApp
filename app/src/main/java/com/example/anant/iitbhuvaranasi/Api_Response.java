package com.example.anant.iitbhuvaranasi;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static android.content.Context.MODE_PRIVATE;

public class Api_Response {
    private static RequestQueue mRequestQueue;

    public static String method(final Context context,ServerCallback serverCallback)
    {
        mRequestQueue = Volley.newRequestQueue(context);

        String url = "http://iitbhuapp.tk/feedandclubs";
        JSONObject obj = new JSONObject();
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.PREF_NAME, MODE_PRIVATE);
        String email3 = sharedPreferences.getString(Constants.Email, Constants.Email_Key);
        String password3 = sharedPreferences.getString(Constants.password_shared, Constants.password);
        try {
            obj.put("email",email3);
            obj.put("password",password3);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final String[] apiresponse = {"0"};

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, obj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                apiresponse[0] = response.toString();
               /* SharedPreferences pref = context.getSharedPreferences("MyPref2", 0);
                SharedPreferences.Editor editor = pref.edit();
                String rew = response.toString();
                editor.putString("bhaiplease", rew);
                editor.commit();*/
                SharedPreferences pref = context.getSharedPreferences(Constants.PREF_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                String rew = response.toString();
                editor.putString(Constants.Response_Feed_Old, rew);
                editor.commit();

                
                serverCallback.onSuccess();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });
        mRequestQueue.add(request);
        return apiresponse[0];
    }
}