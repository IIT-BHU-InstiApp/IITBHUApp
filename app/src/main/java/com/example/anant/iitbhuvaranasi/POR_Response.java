package com.example.anant.iitbhuvaranasi;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import static android.content.Context.MODE_PRIVATE;
import static com.example.anant.iitbhuvaranasi.Constants.IS_POR_PREF;
import static com.example.anant.iitbhuvaranasi.Constants.POR_RESPONSIBILITIES_PREF;
import static com.example.anant.iitbhuvaranasi.Constants.PREF_NAME;

public class POR_Response {

    private final static String POR_URL = "http://iitbhuapp.tk/pors";


    public static void getPORData(Context context, ServerCallback serverCallback) {

        JSONObject postParams = new JSONObject();
        JsonObjectRequest porRequest = new JsonObjectRequest(Request.Method.POST, POR_URL, postParams,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int responseCode = response.getInt("status");
                            if (responseCode == 1) {
                                SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE);
                                String email = sharedPreferences.getString(Constants.Email, Constants.Email_Key);
//                                email = "monukumar.min18@itbhu.ac.in";
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                JSONArray data = response.getJSONArray("data");

                                for (int i = 0; i < data.length(); i++) {

                                    JSONObject PORData = data.getJSONObject(i);

                                    if (email != null && PORData.getString("email").trim().equals(email.trim())) {
                                        String responsibility = PORData.getString("responsibility");
                                        if(responsibility != null) {
                                            editor.putString(POR_RESPONSIBILITIES_PREF, responsibility);
                                            editor.putBoolean(IS_POR_PREF, true);
                                            editor.apply();
                                        }
                                         break;
                                    }
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                        serverCallback.onSuccess();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue jsonRequestQueue = Volley.newRequestQueue(context);
        jsonRequestQueue.add(porRequest);
    }

}
