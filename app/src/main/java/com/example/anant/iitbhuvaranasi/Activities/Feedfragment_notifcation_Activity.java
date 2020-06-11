package com.example.anant.iitbhuvaranasi.Activities;

import android.animation.Animator;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.CalendarContract;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;


import com.android.volley.RequestQueue;

import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
//
import com.example.anant.iitbhuvaranasi.Constants;
import com.example.anant.iitbhuvaranasi.Models.SingleVerticalData;
import com.example.anant.iitbhuvaranasi.NewModels.BuiltWorkshopSummaryPost;
import com.example.anant.iitbhuvaranasi.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
//import static com.example.anant.iitbhuvaranasi.HomeActivity.name_student;

public class  Feedfragment_notifcation_Activity extends AppCompatActivity implements View.OnClickListener {

    ImageView image_event,image_eventfullscreen;
    String image,time,map_location;
    public static String location2345 = null;
    boolean check;
    String event_title,event_description,event_date,event_venue,event_time;
    TextView title_event, description_event, date_event, venue_event, interested_count,councilName;
    Button  interested_button;
    Button share_button,clock_button;
    Button  location_button;
    BuiltWorkshopSummaryPost obj;
    private Animator currentAnimator;
    private int shortAnimationDuration;
    SharedPreferences sharedpreferences;
    View thumb1View;
    int image2;
    private static RequestQueue mRequestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedfragment_notifcation_);
        Toolbar toolbar = findViewById(R.id.toolbar_notification);
        Window window = this.getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimaryDark));


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        time=getIntent().getStringExtra("time");
        mRequestQueue = Volley.newRequestQueue(this);
        final String url = "http://iitbhuapp.tk/interested";




        String json=getIntent().getStringExtra("all");
        Gson gson = new Gson();
        obj = gson.fromJson(json, BuiltWorkshopSummaryPost.class);


        thumb1View = findViewById(R.id.event_picture_2);

        title_event = findViewById(R.id.event_page_title);
        date_event = findViewById(R.id.event_page_date);
        image_event = findViewById(R.id.event_picture_2);
        councilName = findViewById(R.id.council_name);
        share_button =  findViewById(R.id.share_event_button);
        share_button.setOnClickListener(this);
        venue_event = (TextView) findViewById(R.id.event_venue);
        location_button =  findViewById(R.id.location);
        description_event=findViewById(R.id.event_page_description);
        location_button.setOnClickListener(this);

        clock_button =  findViewById(R.id.clock);
        clock_button.setOnClickListener(this);

//        String notifid = obj.getNotifid();
//        Integer notif_id = Integer.valueOf(notifid);
//
        councilName.setText(obj.getClub().getCouncil().getName());
        title_event.setText(obj.getTitle());
//        map_location = obj.getMap_location();
//
//
//
        description_event.setText("Place for Description");

        Glide.with(this)
                .load(obj.getClub().getLarge_image_url())
                .error(R.drawable.background)
                .thumbnail(.1f)
                .fitCenter() // scale to fit entire image within ImageView
                .into(image_event);
        date_event.setText(time);
        String location = "Location";
        venue_event.setText(location);

//        final JSONObject obj2 = new JSONObject();
//        try {
//            obj2.put("roll", 18085016);
//            obj2.put("notifid",notif_id);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }






        final Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(image_event, "fullscreen");


        image_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View, String>(image_event, "fullscreen");
                Intent intent = new Intent(Feedfragment_notifcation_Activity.this, Full_screen_imageActivity.class);
                ActivityOptions options1 = ActivityOptions.makeSceneTransitionAnimation((Activity) Feedfragment_notifcation_Activity.this, pairs);
                intent.putExtra("image", "https://firebasestorage.googleapis.com/v0/b/iit-bhu-workshops-app.appspot.com/o/sntc%2Frobo.png?alt=media&token=ab0603c8-b62e-433f-8577-4a3b8da02e0a");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent,options1.toBundle());

            }
        });




 }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clock:
//
                addEventToCalender(obj.getTitle().toString(),"obj.getDescription_event()",
                        "obj.getLocation()",time.toString());
                break;



            case R.id.share_event_button:
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "#IIT BHU App\n"+ obj.getTitle().toString() + "\n"
                        + "obj.getDescription()".toString()
                        + "\n\n" + "Date & Time : " + time + "\nVenue : " +
                        "obj.getLocation()".toString() ;
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, obj.getTitle().toString());
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
                break;



            case R.id.location:
                location2345 = map_location;
                startActivity(new Intent(this,IITBHUMapActivity.class));

                break;


        }
    }

    public void addEventToCalender(String title,String description,String location,String time) {
        SharedPreferences sharedPref = getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);
        String savedOption = sharedPref.getString(Constants.CALENDAR_DIALOG, Constants.CALENDAR_DIALOG_ALWAYS_ASK);
        if (savedOption.equals(Constants.CALENDAR_DIALOG_YES)) {
            createAddToCalendarIntent(title,description,location,time);
        } else if (savedOption.equals(Constants.CALENDAR_DIALOG_ALWAYS_ASK)) {
            showAddEventToCalendarDialog(title,description,location,time);
        }
    }

    public void showAddEventToCalendarDialog(final String title, final String description, final String location,final String time) {
        android.app.AlertDialog.Builder dialogBuilder = new android.app.AlertDialog.Builder(this);
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View layout = layoutInflater.inflate(R.layout.calendar_dialog_checkbox, null);
        dialogBuilder.setView(layout);
        final CheckBox dontShowAgain = layout.findViewById(R.id.skip);

        dialogBuilder.setTitle("Add to Calendar")
                .setMessage("You will be notified about this event by IIT(BHU) App. Do you also want to add this event to your calendar?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Feedfragment_notifcation_Activity.this.createAddToCalendarIntent(title, description, location, time);
                        Feedfragment_notifcation_Activity.this.saveCalendarDialogPreference(dontShowAgain.isChecked(), true);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Feedfragment_notifcation_Activity.this.saveCalendarDialogPreference(dontShowAgain.isChecked(), false);
                    }
                })
                .create()
                .show();
    }

    public void createAddToCalendarIntent(String title,String description,String location,String time) {

        DateFormat formatter = new SimpleDateFormat("E, dd MMM  hh:mm a");
        long lnsTime = 0, lneTime = 0;
        Date dateObject = null;
        try {
            dateObject = formatter.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        lnsTime = dateObject.getTime();
        Calendar cal = Calendar.getInstance();

        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra("beginTime", lnsTime);
//
        intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY,0);
        intent.putExtra("endTime", lnsTime+60*60*1000*2);
        intent.putExtra(CalendarContract.Events.TITLE, title);
        intent.putExtra(CalendarContract.Events.DESCRIPTION, description);
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION,location);
        intent.putExtra("eventTimezone", "UTC/GMT +5:30");

        startActivity(intent);
    }

    private void saveCalendarDialogPreference(boolean dontAskAgain, boolean yes) {
        SharedPreferences sharedPref = getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        if (!dontAskAgain) {
            editor.putString(Constants.CALENDAR_DIALOG, Constants.CALENDAR_DIALOG_ALWAYS_ASK);
            editor.commit();
        } else {
            if (yes) {
                editor.putString(Constants.CALENDAR_DIALOG, Constants.CALENDAR_DIALOG_YES);
                editor.commit();
            } else {
                editor.putString(Constants.CALENDAR_DIALOG, Constants.CALENDAR_DIALOG_NO);
                editor.commit();
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        super.onBackPressed();

        return true;
    }
}
