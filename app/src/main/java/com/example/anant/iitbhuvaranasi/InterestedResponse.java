package com.example.anant.iitbhuvaranasi;

import android.app.VoiceInteractor;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class InterestedResponse
{
    private static RequestQueue mRequestQueue;
    
    public static JSONObject method(final Context context, ServerCallback serverCallback,int notifid) {

        mRequestQueue = Volley.newRequestQueue(context);

        JSONObject[] apiResponse = new JSONObject[1];


        String url = "http://iitbhuapp.tk/interested";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email","tanmaymishra.mec18@itbhu.ac.in");
            jsonObject.put("notifid",notifid);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    int status = response.getInt("status");
                    if (status == 0){
                        apiResponse[0] = null;
                    }
                    else{
                        apiResponse[0] = response;
                        Log.i("ricks", apiResponse[0].toString());
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                serverCallback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.i("Error","Error Occured");
            }
        });
        
        mRequestQueue.add(request);
        
    return apiResponse[0];
    }

}
/*
public class InterestedResponse
{
    private static RequestQueue mRequestQueue;
    private static String[] apiResponse={"0"};
    private static ServerCallback mServerCallback;

    public static String method(final Context context, ServerCallback serverCallback, int notifid)
    {
        mServerCallback=serverCallback;
        mRequestQueue = Volley.newRequestQueue(context);

        String url = "http://iitbhuapp.tk/interested";
        JSONObject jsonObject =new JSONObject();

        try
        {
            jsonObject.put("email", "tanmaymishra.mec18@itbhu.ac.in");
            jsonObject.put("notifid", notifid);
        }
        catch (JSONException e){e.printStackTrace();}

        ResponseListener responseListener = new ResponseListener();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,url,jsonObject,responseListener,responseListener);
        mRequestQueue.add(request);
        Log.i("RESPONSE","InterestedClass "+apiResponse[0]);
        return apiResponse[0];
    }


    static class ResponseListener implements Response.Listener<JSONObject>,Response.ErrorListener
    {
        @Override
        public void onResponse(JSONObject response) {
            try {
                int status = response.getInt("status");
                if (status == 0){
                    apiResponse[0] = "";
                }
                else{
                    apiResponse[0] = response.toString();
                    Log.i("ricks", apiResponse[0]);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            apiResponse[0] = response.toString();
            mServerCallback.onSuccess();
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            error.printStackTrace();
        }
    }
}*/