package com.example.anant.iitbhuvaranasi.Activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.anant.iitbhuvaranasi.BackendCalls.OtherAPIs;
import com.example.anant.iitbhuvaranasi.BackendResponse.Api_Response;
import com.example.anant.iitbhuvaranasi.ConnectionDetector;
import com.example.anant.iitbhuvaranasi.Constants;
import com.example.anant.iitbhuvaranasi.BackendResponse.Login_response;
import com.example.anant.iitbhuvaranasi.NewModels.LoginPost;
import com.example.anant.iitbhuvaranasi.NewModels.Token;
import com.example.anant.iitbhuvaranasi.POR_Response;
import com.example.anant.iitbhuvaranasi.R;
import com.example.anant.iitbhuvaranasi.Interfaces.ServerCallback;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.GoogleAuthProvider;

import org.json.JSONObject;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {
    public static int guestLoginChecker;

    private static final int RC_SIGN_IN = 1;
    private Button signInButton;
    private static final int REQ_CODE = 9001;
    private GoogleSignInClient mGoogleSignInClient;
    private GoogleSignInOptions gso;
    private String email, imageUri;
    private Uri personphoto;
    Boolean isInternetPresent = false;
    ConnectionDetector cd;
    LinearLayout signInLayout;
    ProgressBar progressBar;

    //    ------------------------------------------------------------
    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;
    String firebaseIdToken;


//    ------------------------------------------------------------


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        ActionBar actionBar = getSupportActionBar();
        //actionBar.hide();


        gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        cd = new ConnectionDetector(this);
        isInternetPresent = cd.isConnectingToInternet();
        if (!isInternetPresent) {
            showAlertDialog(this, "No Internet Connection",
                    "You don't have internet connection.", false);
        }

        signInButton = findViewById(R.id.siginbutton);
        signInButton.setOnClickListener(this);

        signInLayout = findViewById(R.id.signIn_layout);
        progressBar = findViewById(R.id.signIn_progress);
        TextView guestLogin = findViewById(R.id.guestLogin);
        guestLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInLayout.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);

                guestLoginChecker = 1;
                String email4 = "guestuser@iitbhu.ac.in";
                SharedPreferences sharedPref = getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();

                editor.putString(Constants.Email, email4);
                editor.commit();
                Login_response.method(SignInActivity.this, email4, new ServerCallback() {
                    @Override
                    public void onSuccess() {
                        Api_Response.method(SignInActivity.this, new ServerCallback() {
                            @Override
                            public void onSuccess() {
                                updateUI("true");
                            }

                            @Override
                            public void onError() {

                                startActivity(new Intent(SignInActivity.this, HomeActivity.class));
                                finish();
                            }

                            @Override
                            public void onSuccess(JSONObject response) {

                            }


                        });

                    }

                    @Override
                    public void onError() {
                        startActivity(new Intent(SignInActivity.this, HomeActivity.class));
                        finish();
                    }

                    @Override
                    public void onSuccess(JSONObject response) {

                    }


                });

            }
        });
    }

    @Override
    public void onClick(View v) {

        //Api_Response.method(this);
        switch (v.getId()) {
            case R.id.siginbutton:
                signIn();
                break;


        }
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }


    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // -----------------------------------------------Firebase Auth Starts------------------------------------------------------

            //TODO: SHOW PROGRESS BAR HERE
//                    showProgressBar();
            Log.w("google id token", account.getIdToken());
            AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);

            mAuth = FirebaseAuth.getInstance();
            mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        //  Sign in success, update UI with the signed-in user's information

                        Log.d("GoogleActivity", "signInWithCredential:success");
                        firebaseUser = mAuth.getCurrentUser();

                        firebaseIdToken = firebaseUser.getIdToken(false).getResult().getToken();


                        //______________________________________ requesting token from backend _______________________

                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(Constants.herokuBaseUrl)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        OtherAPIs loginAPI = retrofit.create(OtherAPIs.class);
                        Call<Token> call = loginAPI.logInPost(new LoginPost(firebaseIdToken));
                        signInLayout.setVisibility(View.GONE);
                        progressBar.setVisibility(View.VISIBLE);
                        call.enqueue(new Callback<Token>() {
                            @Override
                            public void onResponse(Call<Token> call, Response<Token> response) {

                                if (response.isSuccessful() && response.code() == 200) {

                                    System.out.println("------------------------token fetched from backend-------------------------");

                                    Token token = response.body();
                                    Constants.djangoToken = "token " + token.getToken();
                                    SharedPreferences sharedPref = getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPref.edit();

                                    editor.putString(Constants.djangoTokenKey, Constants.djangoToken);
                                    editor.commit();

                                    editor.putString(Constants.Email, email);
                                    editor.commit();

                                    email = account.getEmail();
                                    personphoto = account.getPhotoUrl();
                                    if (personphoto == null) {
                                        imageUri = "no value";
                                    } else {
                                        imageUri = personphoto.toString();
                                    }

                                    Login_response.method(SignInActivity.this, email, new ServerCallback() {
                                        @Override
                                        public void onSuccess() {
                                            POR_Response.getPORData(SignInActivity.this, new ServerCallback() {
                                                @Override
                                                public void onSuccess() {
                                                    Api_Response.method(SignInActivity.this, new ServerCallback() {
                                                        @Override
                                                        public void onSuccess() {
                                                            updateUI("true");
                                                        }

                                                        @Override
                                                        public void onError() {

                                                        }

                                                        @Override
                                                        public void onSuccess(JSONObject jsonResponse) {

                                                        }
                                                    });
                                                }

                                                @Override
                                                public void onError() {

                                                }

                                                @Override
                                                public void onSuccess(JSONObject jsonResponse) {

                                                }
                                            });
                                        }

                                        @Override
                                        public void onError() {

                                        }

                                        @Override
                                        public void onSuccess(JSONObject jsonResponse) {

                                        }
                                    });

//                                    updateUI("true");
                                }else{

                                    signout();
                                    isInternetPresent = false;
                                    isInternetPresent = cd.isConnectingToInternet();

                                    if (!isInternetPresent) {
                                        showAlertDialog(SignInActivity.this, "No Internet Connection",
                                                "You don't have internet connection.", false);
                                    } else {
                                        updateUI("invalid");
                                    }

                                }
                            }

                            @Override
                            public void onFailure(Call<Token> call, Throwable t) {
                                System.out.println("------------------------ response failed-------------------------");
                                System.out.println("------------------------ response failed-------------------------");
                                System.out.println("------------------------ response failed-------------------------");


                                signout();

                                isInternetPresent = false;
                                isInternetPresent = cd.isConnectingToInternet();

                                if (!isInternetPresent) {
                                    showAlertDialog(SignInActivity.this, "No Internet Connection",
                                            "You don't have internet connection.", false);
                                } else {
                                    updateUI("invalid");
                                }

                            }
                        });

//_________________________________________________________________________________________________


                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("GoogleActivity", "signInWithCredential:failure", task.getException());

                        updateUI("false");
                    }

                    //TODO:  PROGRESS BAR
//                     hideProgressBar();

                }
            });

            // ------------------------------------------------------Firebase Auth Ends-----------------------------------------------


            // Signed in successfully, show authenticated UI.
//            isInternetPresent = false;
//            isInternetPresent = cd.isConnectingToInternet();
//            if ((isEmailValid2(email) == true || isEmailValid1(email) == true) && (isInternetPresent)) {
//                signInLayout.setVisibility(View.GONE);
//                progressBar.setVisibility(View.VISIBLE);
//
//                SharedPreferences sharedPref = getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPref.edit();
//
//                editor.putString(Constants.Email, email);
//                editor.commit();
//
//                Login_response.method(this, email, new ServerCallback() {
//                    @Override
//                    public void onSuccess() {
//                        POR_Response.getPORData(SignInActivity.this, new ServerCallback() {
//                            @Override
//                            public void onSuccess() {
//                                Api_Response.method(SignInActivity.this, new ServerCallback() {
//                                    @Override
//                                    public void onSuccess() {
//                                        updateUI("true");
//                                    }
//
//                                    @Override
//                                    public void onError() {
//
//                                    }
//
//                                    @Override
//                                    public void onSuccess(JSONObject jsonResponse) {
//
//                                    }
//                                });
//                            }
//
//                            @Override
//                            public void onError() {
//
//                            }
//
//                            @Override
//                            public void onSuccess(JSONObject jsonResponse) {
//
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void onError() {
//
//                    }
//
//                    @Override
//                    public void onSuccess(JSONObject jsonResponse) {
//
//                    }
//                });
//
//
//                //Login_response.method(this);
//                //  Api_Response.method(this);
//            } else {
//                if (!isInternetPresent) {
//                    showAlertDialog(this, "No Internet Connection",
//                            "You don't have internet connection.", false);
//                } else {
//                    updateUI("invalid");
//                }
//            }
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("TAG", "signInResult:failed code=" + e.getStatusCode());
            updateUI("false");
        }
    }

    private void updateUI(String result) {

        if (result == "true") {
            Intent intent = new Intent(SignInActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        } else if (result == "invalid") {
            Toast.makeText(SignInActivity.this, "Select Valid Institute Email-id", Toast.LENGTH_SHORT).show();
            signout();
        } else if (result == "false") {
            isInternetPresent = false;
            isInternetPresent = cd.isConnectingToInternet();
            if (!isInternetPresent) {
                showAlertDialog(this, "No Internet Connection",
                        "You don't have internet connection.", false);
            } else {

                Toast.makeText(SignInActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                signout();
            }
        }
        progressBar.setVisibility(View.INVISIBLE);
        signInLayout.setVisibility(View.VISIBLE);

    }

    public static boolean isEmailValid1(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@itbhu.ac.in";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    public static boolean isEmailValid2(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@iitbhu.ac.in";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
//        UpdateUI(account);
        FirebaseUser currentUser = mAuth.getCurrentUser();
        UpdateUI(account, currentUser);
        //
    }

    private void UpdateUI(
            GoogleSignInAccount account,
            FirebaseUser user) {
        if (
                account != null &&
                        user != null) {
            Api_Response.method(SignInActivity.this, new ServerCallback() {
                @Override
                public void onSuccess() {
                }

                @Override
                public void onError() {

                }

                @Override
                public void onSuccess(JSONObject jsonResponse) {

                }
            });
            Intent intent = new Intent(SignInActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }

    }

    private void signout() {
        System.out.println("-------------------------signing out--------------------");
        System.out.println("-------------------------signing out--------------------");
// Firebase sign out
        if (mAuth != null) {
            mAuth.signOut();
        }
        // Google sign out
        if (mGoogleSignInClient != null) {
            mGoogleSignInClient.signOut()
                    .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            // ...
                        }
                    });
        }
        SharedPreferences spreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor spreferencesEditor = spreferences.edit();
        spreferencesEditor.clear();
        spreferencesEditor.commit();
    }

    public void showAlertDialog(Context context, String title, String message, Boolean status) {

        progressBar.setVisibility(View.INVISIBLE);
        signInLayout.setVisibility(View.VISIBLE);

        AlertDialog alertDialog = new AlertDialog.Builder(context).create();

        // Setting Dialog Title
        alertDialog.setTitle(title);

        // Setting Dialog Message
        alertDialog.setMessage(message);

        // Setting alert dialog icon
        alertDialog.setIcon((status) ? R.drawable.ic_signal_wifi_off_black_24dp : R.drawable.ic_signal_wifi_off_black_24dp);

        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }
}
