package com.example.anant.iitbhuvaranasi;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;


public class FeedFragment extends Fragment {
    private ArrayList<Object> objects = new ArrayList<>();
    // public static Integer i=0;
//   SharedPreferences sharedpreferences;
    private RecyclerView mRecyclerView;
    Boolean isInternetPresent = false;
    ConnectionDetector cd;
    //public static ArrayList<SingleVerticalData> getVerticalData1 = new ArrayList<>();
    public static ArrayList<SingleVerticalData> getVerticalData4 = new ArrayList<>();
    public static ArrayList<SingleVerticalData> getVerticalData5;
    public static ArrayList<SingleHorizontaldata> getHorizontalData1 = new ArrayList<>();
    private ArrayList<String> ImageUrl = new ArrayList<>();
    private ArrayList<String> Title = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.feed_fragment, container, false);

//        RecyclerView.RecycledViewPool sharedPool = new RecyclerView.RecycledViewPool();


        cd = new ConnectionDetector(getContext());
        isInternetPresent = cd.isConnectingToInternet();
        if (!isInternetPresent) {
            showAlertDialog(getContext(), "No Internet Connection",
                    "You don't have internet connection.", false);
        }
//        String apidata = Api_Response.method(this.getActivity());
        getVerticalData4 = VerticalDataFeed.getVerticalData3(this.getActivity());
//

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date CurrentTime = null;
        try {
            CurrentTime = dateFormat.parse(dateFormat.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        getVerticalData5 = VerticalDataFeed.getUpcomingEvents(getContext());
        getHorizontalData1 = new ArrayList<>();


//        for (int a = 0; a < getVerticalData4.size(); a++) {
//            String originalString = getVerticalData4.get(a).getDate_event();
//            String original = originalString.replace("T", " ");
//            String original1 = original.replace("Z", "");
//
//            Date date2 = null;
//            try {
//                date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(original1);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//            if (CurrentTime.before(date2)) {
//                getVerticalData5.add(getVerticalData4.get(a));
//            }
//        }
        mRecyclerView = view.findViewById(R.id.recycler_view);

        mRecyclerView.setHasFixedSize(true);

        SharedPreferences pref3 = this.getActivity().getSharedPreferences(Constants.PREF_NAME, MODE_PRIVATE);
        String resonse_feed = pref3.getString(Constants.Response_Feed_Old, "3");



        /**
         * Fetching Data from sharedpref
         */
        try {
            JSONObject response = new JSONObject(resonse_feed);
            int status = response.getInt("status");
//

            if (status == 1) {
//
//                JSONArray jsonArray = response.getJSONArray("notif");
                JSONArray array = response.getJSONArray("councils");


                for (int j = 0; j < array.length(); j++) {
                    JSONObject hit1 = array.getJSONObject(j);
                    String image_council = "http://iitbhuapp.tk" + hit1.getString("image");
                    //
                    //

                    getHorizontalData1.add(new SingleHorizontaldata(image_council));
                }


            } else {
           }
        } catch (JSONException e) {
            e.printStackTrace();
        }

//
        MainAdapterfeedfragment adapter = new MainAdapterfeedfragment(getActivity(), getObject());
//
        //
        mRecyclerView.setHasFixedSize(true);
        adapter.notifyDataSetChanged();
        // adapter.setHasStableIds(true);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });


        //RECYCLERVIEW HORIZONTAL PINTAB

        Api_Response.method(getContext(), new ServerCallback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {

            }
        });//////////////////////////////////

        SharedPreferences pref2 = getActivity().getSharedPreferences(Constants.PREF_NAME, MODE_PRIVATE);
        String response45678 = pref2.getString(Constants.Response_Feed_Old, "2");
//

        SharedPreferences sharedPrefs = getActivity().getSharedPreferences("com.example.anant.iitbhuvaranasi", MODE_PRIVATE);

        try {
            JSONObject jsonObject = new JSONObject(response45678);
//            int status = jsonObject.getInt("status");
            JSONArray allcouncils = jsonObject.getJSONArray("councils");
//            JSONObject council = allcouncils.getJSONObject(position);
//            JSONArray clubs = council.getJSONArray("clubs");
//            View pinView = LayoutInflater.from(getContext()).inflate(R.layout.activity_pin,null);
//            LinearLayout subList = pinView.findViewById(R.id.sub_list);
            int posiClub = 0;
            for (int i = 0; i < allcouncils.length(); i++) {
                JSONObject council = allcouncils.getJSONObject(i);
                JSONArray clubs = council.getJSONArray("clubs");
                for (int j = 0; j < clubs.length(); j++) {
                    JSONObject club = clubs.getJSONObject(j);
                    String clubImage = club.getString("image");
                    String clubTitle = club.getString("name");
//                    Switch subItem = (Switch) subList.findViewById(posiClub);
                    if (sharedPrefs.getBoolean("000" + posiClub, false)) {
                        ImageUrl.add(clubImage);
                        Title.add(clubTitle);
                    }
                    posiClub++;
                }

            }
//            ArrayList<String> test = ImageUrl;


//

        } catch (JSONException e) {
            e.printStackTrace();
        }


//        int[] imgId = {R.drawable.slide1, R.drawable.slide2, R.drawable.slide3, R.drawable.slide4, R.drawable.slide5, R.drawable.slide1, R.drawable.slide2, R.drawable.slide3, R.drawable.slide4, R.drawable.slide5};
        RecyclerView horizontalRcv = view.findViewById(R.id.horizontal_rcv2);
        HorizontalRecyclerAdap horizontalRecyclerAdap = new HorizontalRecyclerAdap(getContext(), ImageUrl, Title);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        horizontalRcv.setLayoutManager(layoutManager2);
        horizontalRcv.setAdapter(horizontalRecyclerAdap);

        ImageView addButton = view.findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openPinActivity = new Intent(getContext(), PinActivity.class);
                startActivity(openPinActivity);
                getActivity().finish();

            }
        });


        return view;
    }


    private ArrayList<Object> getObject() {
        objects.add(getHorizontalData1);
        objects.add(getVerticalData5);

        //
        return objects;
    }

    /*public void MakeSnSnackbar(String text) {
        hideKeyboard();
        Snackbar snack = Snackbar.make(findViewById(R.id.container), text, Snackbar.LENGTH_LONG);
        ViewGroup group = (ViewGroup) snack.getView();
        for (int i = 0; i < group.getChildCount(); i++) {
            View v = group.getChildAt(i);
            if (v instanceof TextView) {
                TextView t = (TextView) v;
                t.setTextColor(Color.RED);
            }
        }
        snack.show();
    }

    private void hideKeyboard() {
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }*/
    //String url= "https://firebasestorage....."
    //Glide.with(getApplicationContext()).load(url).into(imageView);


    public void showAlertDialog(Context context, String title, String message, Boolean status) {
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




