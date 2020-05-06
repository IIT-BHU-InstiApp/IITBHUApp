package com.example.anant.iitbhuvaranasi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class Timetable extends Fragment {

    public Timetable() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Api_Response.MethodForTimetable(getContext(), new ServerCallBackForTimetable() {
            @Override
            public void onSuccess(JSONObject response) {
                Log.d("Timetable", "onSuccess: " + response.toString());
            }
        });
        Log.d("tt", "error");
        return inflater.inflate(R.layout.fragment_time_table, container, false);
    }
}
