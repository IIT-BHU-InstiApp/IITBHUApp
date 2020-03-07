package com.example.anant.iitbhuvaranasi;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class IDCardFragment extends Fragment {


    private static final String LOG_TAG = IDCardFragment.class.getSimpleName();
    public String url = "http://iitbhuapp.tk/checkreg";
    /*Use following when backend has data of all students
     *public String email = HomeActivity.emailOfStudent;
     */
    //For Development Only
    public String email = "bhoomikbhamawat.eee18@itbhu.ac.in";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.activity_idcards,container,false);

        final TextView name = (TextView) view.findViewById(R.id.names);
        final TextView roll = (TextView) view.findViewById(R.id.rollnoedit);
        final TextView father = (TextView) view.findViewById(R.id.fathernameedit);
        final TextView dob = (TextView) view.findViewById(R.id.dobedit);
        final TextView course = (TextView) view.findViewById(R.id.courseedit);
        final TextView department = (TextView) view.findViewById(R.id.departmentedit);
        final TextView yoa = (TextView) view.findViewById(R.id.yoaedit);
        final TextView bloodgrp = (TextView) view.findViewById(R.id.bloodgrpedit);
        final TextView contact = (TextView) view.findViewById(R.id.contactnoedit);
        final TextView emailTextView = (TextView) view.findViewById(R.id.emailidedit);
        final TextView address = (TextView) view.findViewById(R.id.addressedit);

        //RequestQueue queue = Volley.newRequestQueue(this);

        // Post params to be sent to the server
        Map<String, String> params = new HashMap();
        params.put("email", email);

        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest request_json = new JsonObjectRequest(Request.Method.POST , url , parameters,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //Process os success response
                            name.setText(response.optString("name"));
                            roll.setText(response.optString("roll"));
                            father.setText(response.optString("fatherName"));
                            dob.setText(response.optString("dob"));
                            course.setText(response.optString("course"));
                            department.setText(response.optString("department"));
                            yoa.setText(response.optString("yearOfAdmission"));
                            bloodgrp.setText(response.optString("bloodGroup"));
                            contact.setText(response.optString("phone"));
                            emailTextView.setText(email);
                            address.setText(response.optString("address"));

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        });
        // add the request object to the queue to be executed
        //MySingleton.getInstance(this).addToRequestQueue(request_json);
        Volley.newRequestQueue(this.getContext()).add(request_json);


        /*
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest strRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                        Log.v("response","RESPONSE = "+response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                return params;
            }
        };

        queue.add(strRequest);*/
    return view;
    }
}
