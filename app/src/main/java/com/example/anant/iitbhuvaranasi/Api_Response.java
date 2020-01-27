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

public class Api_Response {
    private static RequestQueue mRequestQueue;

    public static String method(final Context context)
    {
        mRequestQueue = Volley.newRequestQueue(context);

        String url = "http://iitbhuapp.tk/feedandclubs";
        JSONObject obj = new JSONObject();
        try {
            obj.put("roll", 18085016);
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
                Log.d("reponsefeed234",rew);
                editor.putString(Constants.Response_Feed_Old, rew);
                editor.commit();


                //SharedPreferences prefs = getPreferences(MODE_PRIVATE);
               // String response3 = prefs.getString(Constants.Response_Feed_Old, "2");
               // Log.d("response345",response3);
                Log.d("Response345678", apiresponse[0]);

                try {
                    int status = response.getInt("status");
                    Log.d("status00", Integer.toString(status));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

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
