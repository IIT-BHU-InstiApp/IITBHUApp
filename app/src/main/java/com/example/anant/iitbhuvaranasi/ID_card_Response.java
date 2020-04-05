package com.example.anant.iitbhuvaranasi;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static android.content.Context.MODE_PRIVATE;

public class ID_card_Response {

    private static RequestQueue mRequestQueue;
    public static String name;

    public static String method(final Context context)
    {
        mRequestQueue = Volley.newRequestQueue(context);

        String url = "http://iitbhuapp.tk/checkreg";
        JSONObject obj = new JSONObject();
        try {
            obj.put("email", Constants.Email_Key);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final String[] apiresponse = {"0"};

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, obj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {



                try {
                    int status = response.getInt("status");
                    if (status == 1) {
                        name = response.getString("name");
                        Log.d("mnbvcc",name);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                apiresponse[0] = response.toString();
                SharedPreferences pref = context.getSharedPreferences(Constants.ID_Name, MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                String rew = response.toString();
                Log.d("reponsefeed765",rew);
                editor.putString(Constants.Response_ID_Old, rew);
                editor.putString(Constants.Name_Student,name);
                editor.commit();
                Log.d("Response345678", apiresponse[0]);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());

            }
        });
        mRequestQueue.add(request);
        return apiresponse[0];

    }

}
