package com.example.anant.iitbhuvaranasi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private GoogleSignInClient mGoogleSignInClient;
    private GoogleSignInOptions gso;
    NavigationView navigationView;
    int x =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);

        // View headerView = navigationView.getHeaderView(0);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        String personName="",personEmail="",personGivenName="",personFamilyName="";
        if (acct != null) {
            personGivenName = acct.getGivenName();
            personEmail = acct.getEmail();
            //Important : Can be used later if needed
            //personName = acct.getDisplayName();
            //Stores branch of the student and year of study
            //personFamilyName = acct.getFamilyName();
        }

        TextView emailOfStudent = headerView.findViewById(R.id.email_of_student);
        TextView nameOfStudent = headerView.findViewById(R.id.name_of_student);

        emailOfStudent.setText(personEmail);
        nameOfStudent.setText(personGivenName);
                /*Log.d("EmailCheck","email="+personEmail+"\name="+personName+"\npersonGivenName="+personGivenName
                +"\npersonFamilyName="+personFamilyName);*/

        navigationView.setCheckedItem(R.id.nav_notifications);
        navigationView.setNavigationItemSelectedListener(this);

        if(savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new FeedFragment()).commit();
        }




        //Added by Suryansh.

        BottomNavigationView bottomNav = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNav.setOnNavigationItemSelectedListener(listener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new FeedFragment()).commit();
//        bottomNav.setSelectedItemId(R.id.feed);


        //Added by Suryansh.


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        switch (menuItem.getItemId()) {

            case R.id.nav_notifications:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FeedFragment()).commit();
                bottomNavigationView.setSelectedItemId(R.id.feed);

                bottomNavigationView.setVisibility(View.VISIBLE);
                x=0;

                break;

            case R.id.nav_maps:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new IITBHUMapFragment()).commit();
                bottomNavigationView.setVisibility(View.GONE);
                x++;
                break;
            case R.id.nav_complain:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ComplainFragment()).commit();
                bottomNavigationView.setVisibility(View.GONE);
                x++;

                break;
            case R.id.nav_imp_contacts:
                Intent intent = new Intent(HomeActivity.this, ContactsActivity.class);
                startActivity(intent);
                finish();

                x++;

                break;

            case R.id.nav_about:
                Toast.makeText(this, "Info", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_logout:
                mGoogleSignInClient.signOut()
                        .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                // ...
                            }
                        });
                SharedPreferences spreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor spreferencesEditor = spreferences.edit();
                spreferencesEditor.clear();
                spreferencesEditor.commit();

                Intent intent2 = new Intent(this, SignInActivity.class);
                startActivity(intent2);
                finish();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        MenuItem item_notification = (MenuItem) findViewById(R.id.nav_notifications);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        }
        else if (x>0){
            navigationView.setCheckedItem(R.id.nav_notifications);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new FeedFragment()).commit();
            bottomNavigationView.setSelectedItemId(R.id.feed);

            bottomNavigationView.setVisibility(View.VISIBLE);
            x=0;
        }
        else if(bottomNavigationView.getSelectedItemId() != R.id.feed) {
            bottomNavigationView.setSelectedItemId(R.id.feed);

        }
        else{
            super.onBackPressed();
        }


    }


    private BottomNavigationView.OnNavigationItemSelectedListener listener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);



            switch (item.getItemId()) {
                case R.id.id_card:
                    selectedFragment = new IDCardFragment();


                    break;

                case R.id.feed:
                    selectedFragment = new FeedFragment();

                    break;


            }


            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment, "frag").commit();


            return true;

        }
    };

//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        recreate();
////        Fragment fragment = getSupportFragmentManager().findFragmentByTag("frag");
////
////        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
////        ft.replace(R.id.container, new FeedFragment());;
////        ft.commitAllowingStateLoss();
//
//    }
}

