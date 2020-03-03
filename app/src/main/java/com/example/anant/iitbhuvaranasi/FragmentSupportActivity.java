package com.example.anant.iitbhuvaranasi;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class FragmentSupportActivity extends FragmentActivity {

    public static FragmentManager fragmentManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_support);

        fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        IITBHUMapFragment fragment = new IITBHUMapFragment();
        fragmentTransaction.add(R.id.fragment_container_new,fragment,null);
        fragmentTransaction.commit();

    }


}
