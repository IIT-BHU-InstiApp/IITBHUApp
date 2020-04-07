package com.example.anant.iitbhuvaranasi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.browser.customtabs.CustomTabsIntent;
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
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private GoogleSignInClient mGoogleSignInClient;
    private GoogleSignInOptions gso;
    public String email;
    NavigationView navigationView;
    int x = 0;
    int track = 0;


    public static String emailOfStudent = "";
    public static String name_student;

    @Override
    protected void onResume() {
        navigationView.getMenu().getItem(track).setChecked(true);
        super.onResume();
//        SharedPreferences sharedPrefs = getSharedPreferences("com.example.anant.iitbhuvaranasi", MODE_PRIVATE);
//
//        int tracked = sharedPrefs.getInt("track",0);
//        navigationView.getMenu().getItem(tracked).setChecked(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SharedPreferences sharedPreferences =getSharedPreferences(Constants.PREF_NAME, MODE_PRIVATE);
        email = sharedPreferences.getString(Constants.Email, Constants.Email_Key);

        Log.d("email1234",email);
        Constants.Email_Key = email;

        ID_card_Response.method(this);
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

        View headerView = navigationView.getHeaderView(0);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        String personName = "", personEmail = "", personGivenName = "", personFamilyName = "";
        if (acct != null) {
            personGivenName = acct.getGivenName();
            personEmail = acct.getEmail();
            emailOfStudent = personEmail;


            //Important : Can be used later if needed
            //personName = acct.getDisplayName();
            //Stores branch of the student and year of study
            //personFamilyName = acct.getFamilyName();
        }

         TextView emailOfStudent = headerView.findViewById(R.id.email_of_student);
         TextView nameOfStudent = headerView.findViewById(R.id.name_of_student);
        SharedPreferences pref3 = getSharedPreferences(Constants.ID_Name, MODE_PRIVATE);
        name_student = pref3.getString(Constants.Name_Student,personGivenName );
        Log.d("mnbv",name_student);
         emailOfStudent.setText(personEmail);
         nameOfStudent.setText(name_student);
                Log.d("EmailCheck","email="+personEmail+"\name="+personName+"\npersonGivenName="+personGivenName
                +"\npersonFamilyName="+personFamilyName);

        navigationView.setCheckedItem(R.id.nav_notifications);
        navigationView.setNavigationItemSelectedListener(this);


        if (savedInstanceState == null) {
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

        SharedPreferences sharedPrefs = getSharedPreferences("com.example.anant.iitbhuvaranasi", MODE_PRIVATE);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        switch (menuItem.getItemId()) {

            case R.id.nav_notifications:
                track = 0;

//                SharedPreferences.Editor editor = getSharedPreferences("com.example.anant.iitbhuvaranasi", MODE_PRIVATE).edit();
//                editor.putInt("track",0);
//                editor.commit();

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FeedFragment()).commit();
                bottomNavigationView.setSelectedItemId(R.id.feed);

                bottomNavigationView.setVisibility(View.VISIBLE);



                break;

            case R.id.nav_maps:
                track = 1;
//                SharedPreferences.Editor editor1 = getSharedPreferences("com.example.anant.iitbhuvaranasi", MODE_PRIVATE).edit();
//                editor1.putInt("track",1);
//                editor1.commit();

                /*getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new IITBHUMapFragment()).commit();
                bottomNavigationView.setVisibility(View.GONE);*/
                Intent intent1 = new Intent(HomeActivity.this, IITbhu_Map.class);
                startActivity(intent1);
                finish();
                x++;
                break;
            case R.id.nav_complain:

//                SharedPreferences.Editor editor2 = getSharedPreferences("com.example.anant.iitbhuvaranasi", MODE_PRIVATE).edit();
//                editor2.putInt("track",2);
//                editor2.commit();
                if(SignInActivity.guestLoginChecker != 1){
                    track = 2;

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ComplainFragment()).commit();
                bottomNavigationView.setVisibility(View.GONE);
                    x++;}
                else{
                    navigationView.getMenu().getItem(track).setChecked(true);
                    Toast toast = Toast.makeText(getApplicationContext(),"You need to LogIn for this feature",Toast.LENGTH_LONG);
                    toast.show();

                }



                break;
            case R.id.lost_found:

                if(SignInActivity.guestLoginChecker != 1){
                    track = 3;

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new LostAndFoundFragment()).commit();
                    bottomNavigationView.setVisibility(View.GONE);
                    x++;}
                else{
                    navigationView.getMenu().getItem(track).setChecked(true);
                    Toast toast = Toast.makeText(getApplicationContext(),"You need to LogIn for this feature",Toast.LENGTH_LONG);
                    toast.show();

                }
                break;


            case R.id.important_links:
                track = 5;
//                SharedPreferences.Editor editor5 = getSharedPreferences("com.example.anant.iitbhuvaranasi", MODE_PRIVATE).edit();
//                editor5.putInt("track",5);
//                editor5.commit();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ImportantLinksFragment()).commit();
                bottomNavigationView.setVisibility(View.GONE);


                x++;
                break;
            case R.id.nav_security:
                Intent intent = new Intent(HomeActivity.this, ContactsActivity.class);
                intent.putExtra("Intent", "security");
                startActivity(intent);
                finish();

                x++;

                break;
            case R.id.nav_academics:
                Intent intent4 = new Intent(HomeActivity.this, ContactsActivity.class);
                intent4.putExtra("Intent", "academics");
                startActivity(intent4);
                finish();
                x++;

                break;
            case R.id.nav_por:
                Intent intent3 = new Intent(HomeActivity.this, ContactsActivity.class);
                intent3.putExtra("Intent", "por");
                startActivity(intent3);
                finish();
                x++;

                break;
            case R.id.nav_study:


                String url = "https://drive.google.com/drive/u/1/folders/1UxuN1fej_4L-l9S_efyWq39h1YAzH8TW";


                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.intent.setPackage("com.android.chrome");
                customTabsIntent.launchUrl(this, Uri.parse(url));



                break;
            case R.id.nav_about:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AboutFragment()).commit();
                bottomNavigationView.setVisibility(View.GONE);
                x++;
                break;
                case R.id.feedback:
                    String url1 = "https://docs.google.com/forms/d/e/1FAIpQLScGQbDEt_6gN5u3P6UsEEAEEmHE-8vvbjNUl6XhZPgBgKE0KA/viewform?usp=sf_link";
                    CustomTabsIntent.Builder builder1 = new CustomTabsIntent.Builder();
                    CustomTabsIntent customTabsIntent1 = builder1.build();
                    //customTabsIntent1.intent.setPackage("com.android.chrome");
                    customTabsIntent1.launchUrl(this, Uri.parse(url1));
                    //customTabsIntent1.launchUrl(Objects.requireNonNull(this, Uri.parse(url1));



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

        } else if (x > 0) {
            navigationView.setCheckedItem(R.id.nav_notifications);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new FeedFragment()).commit();
            bottomNavigationView.setSelectedItemId(R.id.feed);

            bottomNavigationView.setVisibility(View.VISIBLE);
            x = 0;
        } else if (bottomNavigationView.getSelectedItemId() != R.id.feed) {
            bottomNavigationView.setSelectedItemId(R.id.feed);

        } else {
//            SharedPreferences.Editor editor1 = getSharedPreferences("com.example.anant.iitbhuvaranasi", MODE_PRIVATE).edit();
//            editor1.putInt("track",0);
//            editor1.commit();
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

    void onNavigationEvent(int navigationEvent, Bundle extras){
//        if(home == 1) {
//            navigationView.getMenu().getItem(0).setChecked(true);
//        }
//        else if(map == 1) {
//            navigationView.getMenu().getItem(1).setChecked(true);
//        }
//        else if(complain == 1) {
//            navigationView.getMenu().getItem(2).setChecked(true);
//        }
//        else if(lost == 1) {
//            navigationView.getMenu().getItem(3).setChecked(true);
//        }
//        else {
//            navigationView.getMenu().getItem(5).setChecked(true);
//        }
    }
}

