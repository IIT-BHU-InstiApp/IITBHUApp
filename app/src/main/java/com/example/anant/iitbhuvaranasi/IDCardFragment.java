package com.example.anant.iitbhuvaranasi;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class IDCardFragment extends Fragment {


    private static final String LOG_TAG = IDCardFragment.class.getSimpleName();
    public String url = "http://iitbhuapp.tk/checkreg";
    /*Use following when backend has data of all students
     *public String email = HomeActivity.emailOfStudent;
     */
    //For Development Only
    public String email;
    ProgressDialog pdialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_idcards, container, false);

        pdialog = new ProgressDialog(getContext());
        pdialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pdialog.setMessage("Showing Your ID Card");
        pdialog.show();

        SharedPreferences pref3 = this.getActivity().getSharedPreferences(Constants.ID_Name, MODE_PRIVATE);
        String resonse_feed = pref3.getString(Constants.Response_ID_Old, "3");
        Log.d("response_id",resonse_feed);
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(Constants.PREF_NAME, MODE_PRIVATE);
        email = sharedPreferences.getString(Constants.Email, Constants.Email_Key);
        Log.d("email1234",email);
        Log.d("email123",Constants.Email_Key);
        Log.d("response_id",resonse_feed);
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

        try {
            JSONObject response = new JSONObject(resonse_feed);
            int status = response.getInt("status");
            Log.d("status001", Integer.toString(status));

            if (status == 1) {
                Log.d("status100", "1");
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
                pdialog.dismiss();

            }
            else{

                androidx.appcompat.app.AlertDialog.Builder a_builder = new AlertDialog.Builder(getContext());
                a_builder.setMessage("You are not Registered");
                a_builder.setCancelable(false);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;
    }
}
