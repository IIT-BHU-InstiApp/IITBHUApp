package com.example.anant.iitbhuvaranasi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class ContactsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_important_contacts);

        Toolbar toolbar = findViewById(R.id.toolbar_important_contacts);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Window window = this.getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimaryDark));


        BottomNavigationView bottomNav = (BottomNavigationView) findViewById(R.id.bottom_navigation_imp_contacts);

        bottomNav.setOnNavigationItemSelectedListener(listener);
        if(getIntent().getStringExtra("Intent").equals("academics") ) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_imp_contacts,
                    new ContactsAcademics()).commit();
            bottomNav.setSelectedItemId(R.id.imp_academics);
        }
        else if(getIntent().getStringExtra("Intent").equals("por") ) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_imp_contacts,
                    new ContactsPOR()).commit();
            bottomNav.setSelectedItemId(R.id.por_imp);
        }
        else {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_imp_contacts,
                    new ContactsSecurity()).commit();
            bottomNav.setSelectedItemId(R.id.security_imp);
        }

    }

    private BottomNavigationView.OnNavigationItemSelectedListener listener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);



            switch (item.getItemId()) {
                case R.id.imp_academics:
                    selectedFragment = new ContactsAcademics();


                    break;

                case R.id.por_imp:
                    selectedFragment = new ContactsPOR();

                    break;
                case R.id.security_imp:
                    selectedFragment = new ContactsSecurity();

                    break;


            }


            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_imp_contacts, selectedFragment, "frag").commit();


            return true;

        }
    };

    @Override
    public void onBackPressed() {
        Intent intent2 = new Intent(ContactsActivity.this, HomeActivity.class);
        startActivity(intent2);
        finish();

    }
    @Override
    public boolean onSupportNavigateUp(){
        Intent intent2 = new Intent(ContactsActivity.this, HomeActivity.class);
        startActivity(intent2);
        finish();
        return true;
    }
}
