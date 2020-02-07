package com.example.anant.iitbhuvaranasi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private GoogleSignInClient mGoogleSignInClient;
    private GoogleSignInOptions gso;
    private NavigationView navigationView;

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

        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new FeedFragment()).commit();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_notifications:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FeedFragment()).commit();
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_search:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SearchFragment()).commit();
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_complain:
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                        new ComplainFragment()).commit();
                Menu navigationMenu = navigationView.getMenu();

                if(navigationMenu.findItem(R.id.nav_hostel_complain).isVisible()){
                    navigationMenu.findItem(R.id.nav_hostel_complain).setVisible(false);
                    navigationMenu.findItem(R.id.nav_general_complain).setVisible(false);
                    navigationMenu.findItem(R.id.nav_mess_complain).setVisible(false);
                    navigationMenu.findItem(R.id.nav_complain).setIcon(R.drawable.ic_keyboard_arrow_down_black_24dp);
                }else {
                    navigationMenu.findItem(R.id.nav_hostel_complain).setVisible(true);
                    navigationMenu.findItem(R.id.nav_general_complain).setVisible(true);
                    navigationMenu.findItem(R.id.nav_mess_complain).setVisible(true);
                    navigationMenu.findItem(R.id.nav_complain).setIcon(R.drawable.ic_keyboard_arrow_up_black_24dp);
                }
                break;
            case R.id.nav_hostel_complain:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HostelComplainFragment()).commit();
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_myprofile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MyProfileFragment()).commit();
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_eidcard:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new IDCardFragment()).commit();
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_about:
                Toast.makeText(this, "Info", Toast.LENGTH_SHORT).show();
                drawer.closeDrawer(GravityCompat.START);
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

                Intent intent = new Intent(this, SignInActivity.class);
                startActivity(intent);
                finish();
                drawer.closeDrawer(GravityCompat.START);
                break;
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }
}
