package com.example.anant.iitbhuvaranasi;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import com.mikhaellopez.circularimageview.CircularImageView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.anant.iitbhuvaranasi.Constants.POR_RESPONSIBILITIES_PREF;
import static com.example.anant.iitbhuvaranasi.Constants.PREF_NAME;
import static com.example.anant.iitbhuvaranasi.IITBHUMapActivity.Place;
import static com.example.anant.iitbhuvaranasi.IITBHUMapActivity.getMapLocations;


public class PostActivity extends AppCompatActivity {

    private final int PICK_IMAGE_REQUEST = 1;
    private final int MAP_REQUEST_CODE = 2;
    final String FEED_POST_URL = "http://iitbhuapp.tk/notification";
    TextView dateTime;
    LinearLayout setDateTime;
    int mYear, mMonth, mDay, mHour, mMinute;
    String mDateTime, mMapLocation, mMapLocationTitle;
    EditText eventDescription, eventTitle;
    Spinner clubName;
    AutoCompleteTextView venue;
    ImageView eventImage;
    CircularImageView removeImage;
    CoordinatorLayout coordinatorLayout;
    Uri imageUri = null;
    byte[] imageData = null;
    ArrayAdapter<String> clubAdapter;


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("mYear", mYear);
        outState.putInt("mMonth", mMonth);
        outState.putInt("mDay", mDay);
        outState.putInt("mHour", mHour);
        outState.putInt("mMinutes", mMinute);
        outState.putString("dateNtime", dateTime.getText().toString());
        outState.putString("eventTitle", eventTitle.getText().toString());
        outState.putString("venue", venue.getText().toString());
        outState.putString("eventDescription", eventDescription.getText().toString());
        outState.putString("imageUri", String.valueOf(imageUri));
        outState.putByteArray("imageData", imageData);
        outState.putString("mMapLocationTitle", mMapLocationTitle);
        outState.putString("mMapLocation", mMapLocation);
        outState.putString("venue", venue.getText().toString());

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState != null) {
            mYear = savedInstanceState.getInt("mYear");
            mMonth = savedInstanceState.getInt("mMonth");
            mDay = savedInstanceState.getInt("mDay");
            mHour = savedInstanceState.getInt("mHour");
            mMinute = savedInstanceState.getInt("mMinute");
            dateTime.setText(savedInstanceState.getString("dateNtime"));
            venue.setText(savedInstanceState.getString("venue"));
            eventTitle.setText(savedInstanceState.getString("eventTitle"));
            eventDescription.setText(savedInstanceState.getString("eventDescription"));
            imageData = savedInstanceState.getByteArray("imageData");
            imageUri = Uri.parse(savedInstanceState.getString("imageUri"));
            if (imageUri != null) {
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
        clubName = findViewById(R.id.council_name);
        Button location = findViewById(R.id.location);
        Button shareEvent = findViewById(R.id.share_event_button);
        Button reminder = findViewById(R.id.clock);
        Button interested = findViewById(R.id.interested);

        Toolbar toolbar = findViewById(R.id.toolbar_post);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SharedPreferences sharedPreferences = this.getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        String responsibilities = sharedPreferences.getString(POR_RESPONSIBILITIES_PREF, "Club");

        if (responsibilities != null) {
            clubAdapter = new ArrayAdapter<String>(this, R.layout.post_activity_club_spinner_layout, new ArrayList<String>(Arrays.asList(responsibilities.split("\\s*,\\s*")))) {
                @Override
                public View getDropDownView(int position, View convertView, @NotNull ViewGroup parent) {

                    View view = getLayoutInflater().inflate(R.layout.support_simple_spinner_dropdown_item, parent, false);
                    ((TextView) view).setText(getItem(position));
                    return view;
                }
            };
            clubName.setAdapter(clubAdapter);
        }

        VenueAdapter venueAdapter = new VenueAdapter(this, getMapLocations());
        venue.setAdapter(venueAdapter);
        venue.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mMapLocation = venueAdapter.mLocations.get(position).getKey();
                mMapLocationTitle = venueAdapter.mLocations.get(position).getName();
                venue.setText(mMapLocationTitle);
            }
        });

//        venue.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                for (Location item : venueList(getMapLocations())) {
//                    if (s.toString().trim().toLowerCase().equals(item.getName().trim().toLowerCase())){
//                        mMapLocation = null;
//                        mMapLocationTitle = null;
//                    }
//                }
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                for (Location item : venueList(getMapLocations())) {
//                    if (s.toString().trim().toLowerCase().equals(item.getName().trim().toLowerCase())){
//                        mMapLocation = item.getKey();
//                        mMapLocationTitle = item.getName();
//                    }
//                }
//            }
//        });

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
                Intent locationIntent = new Intent(PostActivity.this, IITBHUMapActivity.class);
                locationIntent.putExtra("PostActivity", true);
                startActivityForResult(locationIntent, MAP_REQUEST_CODE);
            }
        });

        location.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String message;
                if (mMapLocation == null) {
                    message = "You haven't set map location.";
                } else {
                    message = "Your map Location is set to: " + mMapLocation + " i.e " + mMapLocationTitle;
                }
                Snackbar snackbar = Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG);
                if (mMapLocation != null) {
                    snackbar.setAction("Remove", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mMapLocation = null;
                            mMapLocationTitle = null;
                        }
                    });
                    snackbar.setActionTextColor(getResources().getColor(R.color.colorPrimary));
                    snackbar.setDuration(Snackbar.LENGTH_INDEFINITE);
                }
                snackbar.show();
                return true;
            }
        });

        getCurrentTime();


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
                removeImage();
            }
        });

    }

    public void getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        mHour = 18;
        mMinute = 00;
    }

    public void removeImage() {
        removeImage.setVisibility(View.GONE);
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics());
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
        eventImage.setLayoutParams(layoutParams);
        imageUri = null;
        imageData = null;
        eventImage.setImageDrawable(getDrawable(R.drawable.ic_insert_photo_color_primary_24dp));
        eventImage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        eventImage.setBackgroundColor(Color.parseColor("#E0E0E0"));
    }

    public void post(View view) {
        if (TextUtils.isEmpty(eventTitle.getText())) {
            Snackbar.make(coordinatorLayout, "Please fill event title", Snackbar.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(dateTime.getText())) {
            Snackbar.make(coordinatorLayout, "Please specify event date & time", Snackbar.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(venue.getText())) {
            Snackbar.make(coordinatorLayout, "Please specify venue", Snackbar.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(eventDescription.getText())) {
            Snackbar.make(coordinatorLayout, "Please fill event description", Snackbar.LENGTH_SHORT).show();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            StringBuilder builderMessage = new StringBuilder();
            if (eventImage.getScaleType() == ImageView.ScaleType.CENTER_INSIDE) {
                builderMessage.append("This event post doesn't contain poster.\n\n");
            }
            if (mMapLocation == null) {
                builderMessage.append("You haven't set map location.\n\n");
            } else {
                builderMessage.append("Your map Location is set to: ").append(mMapLocation).append(" i.e ").append(mMapLocationTitle).append("\n\n");
            }
            builderMessage.append("Are you sure you want to post this event?");
            builder.setMessage(builderMessage.toString());
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    postEvent();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

    private void postEvent() {
        final ProgressDialog pdialog = new ProgressDialog(PostActivity.this);
        pdialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pdialog.setMessage("Posting your event...");
        pdialog.setCanceledOnTouchOutside(false);
        pdialog.show();

        SharedPreferences sharedPreferences = PostActivity.this.getSharedPreferences(Constants.PREF_NAME, MODE_PRIVATE);
        String email = sharedPreferences.getString(Constants.Email, Constants.Email_Key);
        String password = sharedPreferences.getString(Constants.password_shared, Constants.password);
        String club = clubName.getSelectedItem().toString();
        String year = String.valueOf(mYear);
        String month = String.valueOf(mMonth);
        String day = String.valueOf(mDay);
        String hour = String.valueOf(mHour);
        String minutes = String.valueOf(mMinute);
        String location = venue.getText().toString();
        String mapLocation = mMapLocation;
        String header = eventTitle.getText().toString();
        String description = eventDescription.getText().toString();

        VolleyMultipartRequest multipartRequest = new VolleyMultipartRequest(Request.Method.POST, FEED_POST_URL,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        pdialog.dismiss();
                        try {
                            int responseCode = response.getInt("status");
                            switch (responseCode) {
                                case 0:
                                    Snackbar.make(coordinatorLayout, "Something went Wrong! Try again Later", Snackbar.LENGTH_LONG).show();
                                    break;
                                case 1:
                                    Snackbar.make(coordinatorLayout, "Event successfully posted", Snackbar.LENGTH_LONG).show();
                                    removeImage();
                                    eventTitle.getText().clear();
                                    dateTime.setText(null);
                                    mDateTime = null;
                                    getCurrentTime();
                                    venue.getText().clear();
                                    mMapLocation = null;
                                    mMapLocationTitle = null;
                                    eventDescription.getText().clear();
                                    break;
                                case 3:
                                    Snackbar.make(coordinatorLayout, "Invalid credentials! Unable to post event", Snackbar.LENGTH_LONG).show();
                                    break;
                                default:
                                    Snackbar.make(coordinatorLayout, "Something went Wrong! Try again Later", Snackbar.LENGTH_LONG).show();
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
                Snackbar.make(coordinatorLayout, "Something went Wrong! Try again Later", Snackbar.LENGTH_LONG).show();
            }
        }) {
            @Override
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);
                params.put("club", club);
                params.put("location", location);
                params.put("map_location", mapLocation);
                params.put("header", header);
                params.put("description", description);
                params.put("year", year);
                params.put("month", month);
                params.put("day", day);
                params.put("hour", hour);
                params.put("minutes", minutes);
                return params;
            }

            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> files = new HashMap<>();
                if (imageData != null) {
                    String imageName = header + ".jpeg";
                    files.put("notification_image", new DataPart(imageName, imageData, "image/jpeg"));
                }
                return files;
            }
        };
        RequestQueue multipartRequestQueue = Volley.newRequestQueue(PostActivity.this);
        multipartRequestQueue.add(multipartRequest);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
            if (data != null) {
                imageUri = data.getData();

                Bitmap imageBitmap;
                try {
                    imageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                    imageBitmap = getResizedBitmap(imageBitmap, 500);//Setting the Bitmap to ImageView
                    imageData = getFileData(imageBitmap);
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
            if (data != null) {
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

    public byte[] getFileData(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public boolean onSupportNavigateUp() {
        super.onBackPressed();

        return true;
    }

    private ArrayList<Location> venueList(Map<String, Place> mapLocations) {
        ArrayList<Location> venueName = new ArrayList<>();
        for (Map.Entry<String, Place> entry : mapLocations.entrySet()) {
            venueName.add(new Location(entry.getKey(), entry.getValue().getName()));
        }
        return venueName;
    }


    class VenueAdapter extends ArrayAdapter<Location> {

        Context mContext;
        List<Location> originalLocations = new ArrayList<>();
        List<Location> mLocations = new ArrayList<>();

        public VenueAdapter(@NonNull Context context, Map<String, Place> mapLocations) {
            super(context, 0, venueList(mapLocations));

            mContext = context;
            originalLocations = venueList(mapLocations);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.support_simple_spinner_dropdown_item, parent, false);
            ((TextView) view).setText(mLocations.get(position).getName());
            return view;
        }

        @Override
        public int getCount() {
            return mLocations.size();
        }

        @NonNull
        @Override
        public Filter getFilter() {
            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    final FilterResults results = new FilterResults();

                    final String constraintString = constraint.toString().trim().toLowerCase();

                    final List<Location> values = new ArrayList<>(originalLocations);
                    final List<Location> newValues = new ArrayList<>();

                    for (Location item : values) {
                        final String entryString = item.getName().trim().toLowerCase();
                        if (entryString.contains(constraintString)) {
                            newValues.add(new Location(item.getKey(), item.getName()));
                        }
                    }
                    results.values = newValues;
                    results.count = newValues.size();

                    return results;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    if (results.count != 0) {
                        mLocations = (List<Location>) results.values;
                        notifyDataSetChanged();
                    } else {
                        notifyDataSetInvalidated();
                    }
                }
            };
        }
    }

    static class Location {

        String mKey;
        String mName;

        Location(String key, String name) {
            mKey = key;
            mName = name;
        }

        private String getKey() {
            return mKey;
        }

        private String getName() {
            return mName;
        }
    }

    static class VolleyMultipartRequest extends Request<JSONObject> {

        private final String twoHyphens = "--";
        private final String lineEnd = "\r\n";
        private final String boundary = "--xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx--";


        private Response.Listener<JSONObject> mListener;
        private Response.ErrorListener mErrorListener;

        VolleyMultipartRequest(int method, String url,
                               Response.Listener<JSONObject> listener,
                               Response.ErrorListener errorListener) {
            super(method, url, errorListener);
            this.mListener = listener;
            this.mErrorListener = errorListener;
        }


        @Override
        public String getBodyContentType() {
            return "multipart/form-data; boundary=" + boundary;
        }

        @Override
        public byte[] getBody() throws AuthFailureError {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(bos);

            try {
                Map<String, String> params = getParams();
                if (params != null && params.size() > 0) {
                    textParse(dos, params, getParamsEncoding());
                }

                Map<String, DataPart> data = getByteData();
                if (data != null && data.size() > 0) {
                    dataParse(dos, data);
                }

                // close multipart form data after text and file data
                dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                return bos.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected Map<String, DataPart> getByteData() throws AuthFailureError {
            return null;
        }

        @Override
        protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {

            // For Network Response
            /**
             *   try {
             *      return Response.success(
             *             response,
             *            HttpHeaderParser.parseCacheHeaders(response));
             *} catch (Exception e) {
             *   return Response.error(new ParseError(e));
             *}
             */

            try {
                String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                return Response.success(new JSONObject(jsonString), HttpHeaderParser.parseCacheHeaders(response));
            } catch (UnsupportedEncodingException | JSONException e) {
                return Response.error(new ParseError(e));
            }
        }

        @Override
        protected void deliverResponse(JSONObject response) {
            mListener.onResponse(response);
        }

        @Override
        public void deliverError(VolleyError error) {
            mErrorListener.onErrorResponse(error);
        }

        private void textParse(DataOutputStream dataOutputStream, Map<String, String> params, String encoding) throws IOException {
            try {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    buildTextPart(dataOutputStream, entry.getKey(), entry.getValue());
                }
            } catch (UnsupportedEncodingException uee) {
                throw new RuntimeException("Encoding not supported: " + encoding, uee);
            }
        }

        private void buildTextPart(DataOutputStream dataOutputStream, String parameterName, String parameterValue) throws IOException {

            String string = twoHyphens + boundary + lineEnd +
                    "Content-Disposition: form-data;" +
                    " name=\"" + parameterName + "\"" + lineEnd + lineEnd +
                    parameterValue + lineEnd;
            dataOutputStream.writeBytes(string);
        }


        private void dataParse(DataOutputStream dataOutputStream, Map<String, DataPart> data) throws IOException {
            for (Map.Entry<String, DataPart> entry : data.entrySet()) {
                buildDataPart(dataOutputStream, entry.getKey(), entry.getValue());
            }
        }

        private void buildDataPart(DataOutputStream dataOutputStream, String inputName, DataPart dataFile) throws IOException {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(twoHyphens + boundary + lineEnd)
                    .append("Content-Disposition: form-data; name=\"")
                    .append(inputName).append("\";")
                    .append(" filename=\"")
                    .append(dataFile.getFileName())
                    .append("\"")
                    .append(lineEnd);

            if (dataFile.getType() != null && !dataFile.getType().trim().isEmpty()) {
                stringBuilder.append("Content-Type: ")
                        .append(dataFile.getType())
                        .append(lineEnd);
            }
            stringBuilder.append(lineEnd);
            dataOutputStream.writeBytes(stringBuilder.toString());

            ByteArrayInputStream fileInputStream = new ByteArrayInputStream(dataFile.getContent());
            int bytesAvailable = fileInputStream.available();

            int maxBufferSize = 1024 * 1024;
            int bufferSize = Math.min(bytesAvailable, maxBufferSize);
            byte[] buffer = new byte[bufferSize];

            int bytesRead = fileInputStream.read(buffer, 0, bufferSize);

            while (bytesRead > 0) {
                dataOutputStream.write(buffer, 0, bufferSize);
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            }

            dataOutputStream.writeBytes(lineEnd);
        }

        static class DataPart {
            private String fileName;
            private byte[] content;
            private String type;


            DataPart(String name, byte[] data, String dataType) {
                fileName = name;
                content = data;
                type = dataType;
            }

            String getFileName() {
                return fileName;
            }

            byte[] getContent() {
                return content;
            }

            String getType() {
                return type;
            }
        }
    }
}
