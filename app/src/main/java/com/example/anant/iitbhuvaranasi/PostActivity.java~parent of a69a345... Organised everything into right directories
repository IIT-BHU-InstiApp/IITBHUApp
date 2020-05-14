package com.example.anant.iitbhuvaranasi;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.ContentView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import com.mikhaellopez.circularimageview.CircularImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import static com.example.anant.iitbhuvaranasi.HomeActivity.emailOfStudent;


public class PostActivity extends AppCompatActivity {

    private final int PICK_IMAGE_REQUEST = 1;
    private final int MAP_REQUEST_CODE = 2;
    final String FEED_POST_URL = "http://iitbhuapp.tk/notification";
    TextView dateTime;
    LinearLayout setDateTime;
    int mYear, mMonth, mDay, mHour, mMinute;
    String mDateTime;
    EditText eventDescription, eventTitle, venue;
    ImageView eventImage;
    CircularImageView removeImage;
    CoordinatorLayout coordinatorLayout;
    String mposter,mMapLocation;
    String mMapLocationTitle;
    Uri imageUri;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("mYear",mYear);
        outState.putInt("mMonth",mMonth);
        outState.putInt("mDay",mDay);
        outState.putInt("mHour",mHour);
        outState.putInt("mMinutes",mMinute);
        outState.putString("dateNtime",dateTime.getText().toString());
        outState.putString("eventTitle",eventTitle.getText().toString());
        outState.putString("venue",venue.getText().toString());
        outState.putString("eventDescription",eventDescription.getText().toString());
        outState.putString("imageUri", String.valueOf(imageUri));
        outState.putString("mposter",mposter);
        outState.putString("mMapLocationTitle",mMapLocationTitle);
        outState.putString("mMapLocation",mMapLocation);
        outState.putString("venue",venue.getText().toString());

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if(savedInstanceState != null){
            mYear = savedInstanceState.getInt("mYear");
            mMonth = savedInstanceState.getInt("mMonth");
            mDay = savedInstanceState.getInt("mDay");
            mHour = savedInstanceState.getInt("mHour");
            mMinute = savedInstanceState.getInt("mMinute");
            dateTime.setText(savedInstanceState.getString("dateNtime"));
            venue.setText(savedInstanceState.getString("venue"));
            eventTitle.setText(savedInstanceState.getString("eventTitle"));
            eventDescription.setText(savedInstanceState.getString("eventDescription"));
            mposter = savedInstanceState.getString("poster");
            imageUri = Uri.parse(savedInstanceState.getString("imageUri"));
            if(imageUri != null){
                removeImage.setVisibility(View.VISIBLE);
                ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                eventImage.setLayoutParams(layoutParams);
                eventImage.setImageURI(imageUri);
                eventImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
                eventImage.setBackgroundResource(0);
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        coordinatorLayout = findViewById(R.id.coordinator_layout);

        dateTime = findViewById(R.id.event_date_time);
        setDateTime = findViewById(R.id.set_date_time);
//        setVenue = findViewById(R.id.set_venue);
        venue = findViewById(R.id.event_venue);
        eventTitle = findViewById(R.id.event_title);
        eventDescription = findViewById(R.id.event_description);
        eventImage = findViewById(R.id.event_image);
        removeImage = findViewById(R.id.remove_image);
        Button location = findViewById(R.id.location);
        Button shareEvent = findViewById(R.id.share_event_button);
        Button reminder = findViewById(R.id.clock);
        Button interested = findViewById(R.id.interested);

        Toolbar toolbar = findViewById(R.id.toolbar_post);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        venue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(venue.getText())){
                    Toast.makeText(PostActivity.this, "This venue is for preview. \n For map select location using \"ON MAP\" button", Toast.LENGTH_SHORT).show();
                }
            }
        });

        interested.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(coordinatorLayout, "This button is disabled in post preview", Snackbar.LENGTH_SHORT).show();
            }
        });
        shareEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(coordinatorLayout, "This button is disabled in post preview", Snackbar.LENGTH_SHORT).show();
            }
        });

        reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(coordinatorLayout, "This button is disabled in post preview", Snackbar.LENGTH_SHORT).show();
            }
        });

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Feedfragment_notifcation_Activity.location2345 = mMapLocation;
                Intent locationIntent = new Intent(PostActivity.this,IITBHUMapActivity.class);
                locationIntent.putExtra("PostActivity",true);
                startActivityForResult(locationIntent,MAP_REQUEST_CODE);
            }
        });

        Calendar calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        mHour = 18;
        mMinute = 00;

        setDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(PostActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mYear = year;
                        mMonth = month;
                        mDay = dayOfMonth;
                        mDateTime = mYear + "-" + mMonth + "-" + mDay + " " + mHour + ":" + mMinute;
                        Date date = null;
                        try {
                            date = new SimpleDateFormat("yyyy-mm-dd hh:mm").parse(mDateTime);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        dateTime.setText(new SimpleDateFormat("E, dd MMM  hh:mm a").format(date));
                        TimePickerDialog timePickerDialog = new TimePickerDialog(PostActivity.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                mHour = hourOfDay;
                                mMinute = minute;
                                mDateTime = mYear + "-" + mMonth + "-" + mDay + " " + mHour + ":" + mMinute;
                                Date dateNTime = null;
                                try {
                                    dateNTime = new SimpleDateFormat("yyyy-mm-dd hh:mm").parse(mDateTime);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                dateTime.setText(new SimpleDateFormat("E, dd MMM  hh:mm a").format(dateNTime));

                            }
                        }, mHour, mMinute, false);
                        timePickerDialog.show();
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        eventImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });

        removeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeImage.setVisibility(View.GONE);
                int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics());
                ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
                eventImage.setLayoutParams(layoutParams);
                mposter = "";
                eventImage.setImageDrawable(getDrawable(R.drawable.ic_insert_photo_color_primary_24dp));
                eventImage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                eventImage.setBackgroundColor(Color.parseColor("#E0E0E0"));
            }
        });


    }

    public void post(View view) {
        if(TextUtils.isEmpty(eventTitle.getText())){
            Snackbar.make(coordinatorLayout, "Please fill event title", Snackbar.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(dateTime.getText())){
            Snackbar.make(coordinatorLayout, "Please specify event date & time", Snackbar.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(venue.getText())){
            Snackbar.make(coordinatorLayout, "Please specify venue", Snackbar.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(eventDescription.getText())){
            Snackbar.make(coordinatorLayout, "Please fill event description", Snackbar.LENGTH_SHORT).show();
        }else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            StringBuilder builderMessage = new StringBuilder();
            if(eventImage.getScaleType() == ImageView.ScaleType.CENTER_INSIDE){
                builderMessage.append("This event post doesn't contain poster.\n");
            }
            if(mMapLocation == null){
                builderMessage.append("You haven't set map location.\n\n");
            }else{
                builderMessage.append("Your map Location is set to: " + mMapLocation + " i.e " +mMapLocationTitle+ "\n\n");
            }
            builderMessage.append("Are you sure you want to post this event?");
            builder.setMessage(builderMessage.toString());
            builder.setCancelable(false);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    final ProgressDialog pdialog = new ProgressDialog(PostActivity.this);
                    pdialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    pdialog.setMessage("Posting your event...");
                    pdialog.show();

                    SharedPreferences sharedPreferences = PostActivity.this.getSharedPreferences(Constants.PREF_NAME, MODE_PRIVATE);
                    String email = sharedPreferences.getString(Constants.Email, Constants.Email_Key);
                    String password = sharedPreferences.getString(Constants.password_shared, Constants.password);
                    // Todo fetch club/Council
                    String clubCouncil = "SNTC" ;
                    String year = String.valueOf(mYear);
                    String month = String.valueOf(mMonth);
                    String day = String.valueOf(mDay);
                    String hour = String.valueOf(mHour);
                    String minutes = String.valueOf(mMinute);
                    String location = venue.getText().toString();
                    String mapLocation = mMapLocation;
                    String header = eventTitle.getText().toString();
                    String description = eventDescription.getText().toString();
                    String image = mposter;


                    JSONObject postparams = new JSONObject();
                    try {
                        postparams.put("email", email);
                        postparams.put("password", password);
                        postparams.put("club", clubCouncil);
                        postparams.put("year", year);
                        postparams.put("month", month);
                        postparams.put("day", day);
                        postparams.put("hour", hour);
                        postparams.put("minutes", minutes);
                        postparams.put("location",location);
                        postparams.put("map_location",mapLocation);
                        postparams.put("header", header);
                        postparams.put("description", description);
                        postparams.put("image", image);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, FEED_POST_URL, postparams, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            pdialog.dismiss();
                            try {
                                int responseCode = response.getInt("status");
                                switch(responseCode){
                                    case 0:
                                        Log.e("TAGG", "res 0");
                                        Snackbar.make(coordinatorLayout, "Something went Wrong.\nTry again Later", Snackbar.LENGTH_LONG).show();
                                        break;
                                    case 1:
                                        Snackbar.make(coordinatorLayout, "Event successfully posted", Snackbar.LENGTH_LONG).show();
                                        break;
                                    case 3:
                                        Snackbar.make(coordinatorLayout, "Invalid credentials.\nUnable to post event", Snackbar.LENGTH_LONG).show();
                                        break;
                                    default:
                                        Log.e("TAGG", "default");

                                        Snackbar.make(coordinatorLayout, "Something went Wrong.\nTry again Later", Snackbar.LENGTH_LONG).show();
                                        break;
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            pdialog.dismiss();
                            Log.e("TAGG", "error" + error);

                            Snackbar.make(coordinatorLayout, "Something went Wrong.\nTry again Later", Snackbar.LENGTH_LONG).show();
                        }
                    });
                    RequestQueue requestQueue = Volley.newRequestQueue(PostActivity.this);
                    requestQueue.add(postRequest);
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
            if (data != null) {
                imageUri = data.getData();


                Bitmap rbitmap;
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                    rbitmap = getResizedBitmap(bitmap, 500);//Setting the Bitmap to ImageView
                    mposter = getStringImage(rbitmap);
                    //base64toString.add(userImage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                removeImage.setVisibility(View.VISIBLE);
                ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                eventImage.setLayoutParams(layoutParams);
                eventImage.setImageURI(imageUri);
                eventImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
                eventImage.setBackgroundResource(0);
            } else {
                Toast.makeText(this, "You haven't picked image", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == MAP_REQUEST_CODE && resultCode == RESULT_OK) {
            if(data != null) {
                mMapLocationTitle = data.getStringExtra("MarkerTitle");
                mMapLocation = data.getStringExtra("MarkerKey");
                if (TextUtils.isEmpty(venue.getText())) {
                    venue.setText(mMapLocationTitle);
                }
            }
        }
    }

    private Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);

    }

    private String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();

        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }

    @Override
    public boolean onSupportNavigateUp(){
        super.onBackPressed();

        return true;
    }
}
