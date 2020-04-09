package com.example.anant.iitbhuvaranasi;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

//import android.util.Log;
//import android.util.Log;
//import android.util.Log;


public class FeedFragment extends Fragment {
    private ArrayList<Object> objects = new ArrayList<>();
   // public static Integer i=0;
   SharedPreferences sharedpreferences;
    private RecyclerView mRecyclerView;
    Boolean isInternetPresent = false;
    ConnectionDetector cd;
   //public static ArrayList<SingleVerticalData> getVerticalData1 = new ArrayList<>();
    public static ArrayList<SingleVerticalData> getVerticalData4 = new ArrayList<>();
    public static ArrayList<SingleVerticalData> getVerticalData5;
    public static ArrayList<SingleHorizontaldata>getHorizontalData1=new ArrayList<>();
    private ArrayList<String> ImageUrl = new ArrayList<>();
    private ArrayList<String> Title = new ArrayList<>();



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.feed_fragment, container, false);

        RecyclerView.RecycledViewPool sharedPool = new RecyclerView.RecycledViewPool();


        cd = new ConnectionDetector(getContext());
        isInternetPresent = cd.isConnectingToInternet();
        if(!isInternetPresent){
            showAlertDialog(getContext(), "No Internet Connection",
                    "You don't have internet connection.", false);
        }
        String apidata = Api_Response.method(this.getActivity());
        getVerticalData4 = VerticalDataFeed.getVerticalData3(this.getActivity());
        Log.d("howareyou1",getVerticalData4.toString());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date CurrentTime = null;
        try {
            CurrentTime = dateFormat.parse(dateFormat.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        getVerticalData5 = new ArrayList<>();
        getHorizontalData1 = new ArrayList<>();



         for (int a = 0 ; a< getVerticalData4.size(); a++){
             String originalString = getVerticalData4.get(a).getDate_event();
             String original = originalString.replace("T"," ");
             String original1 = original.replace("Z","");

             Date date2 = null;
             try {
                 date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(original1);
             } catch (ParseException e) {
                 e.printStackTrace();
             }
             final String newString = new SimpleDateFormat("E, dd MMM  hh:mm a").format(date2);
             if (CurrentTime.before(date2))
             {
                 getVerticalData5.add(getVerticalData4.get(a));
             }
         }
Log.d("abeyyyyyysaaale",getVerticalData5.toString());

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
       // mRecyclerView.setRecycledViewPool(sharedPool);

      //
        //  Log.d("fe",getVerticalData1.toString());
        Log.d("fe",getHorizontalData1.toString());

        mRecyclerView.setHasFixedSize(true);

        SharedPreferences pref3 = this.getActivity().getSharedPreferences(Constants.PREF_NAME, MODE_PRIVATE);
        String resonse_feed = pref3.getString(Constants.Response_Feed_Old,"3");


        /*SharedPreferences pref =  this.getActivity().getSharedPreferences("MyPref", 0);
        String response1 = pref.getString("savehojayaar", "2");
        Log.d("response3456789",response1.toString());*/

        String url = "http://iitbhuapp.tk/feedandclubs";
        /*SharedPreferences prefs = this.getActivity().getPreferences(MODE_PRIVATE);
        String response = prefs.getString(Constants.Response_Feed_Old, "2");
        Log.d("response3456789",response);*/


        /**
         * Fetching Data from sharedpref
         */
        try {
            JSONObject response = new JSONObject(resonse_feed);
            int status = response.getInt("status");
            Log.d("status001", Integer.toString(status));

            if (status == 1) {
                Log.d("status100", "1");
                JSONArray jsonArray = response.getJSONArray("notif");
                JSONArray array = response.getJSONArray("councils");


                for (int j = 0; j < array.length(); j++){
                    JSONObject hit1 = array.getJSONObject(j);
                    String image_council = "http://iitbhuapp.tk" + hit1.getString("image");
                    //  Log.d("clubname", name);
                    //Log.d("imageurl",image_council);

                    getHorizontalData1.add(new SingleHorizontaldata(image_council));
                }
                Log.d("horizontaldata234500", getHorizontalData1.toString());
            }
            else {
                Log.d("status000", "0");
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("beforemainadapter","009");
        MainAdapterfeedfragment adapter = new MainAdapterfeedfragment(getActivity(), getObject());
        Log.d("aftermainadapter","009");
        //Log.d("getobjectstart",getObject().toString());
        mRecyclerView.setHasFixedSize(true);
        adapter.notifyDataSetChanged();
       // adapter.setHasStableIds(true);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));




        //RECYCLERVIEW HORIZONTAL PINTAB

        Api_Response.method(getContext());//////////////////////////////////

        SharedPreferences pref2 = getActivity().getSharedPreferences(Constants.PREF_NAME, MODE_PRIVATE);
        String response45678 = pref2.getString(Constants.Response_Feed_Old, "2");
        Log.d("response34567890123", response45678);

        SharedPreferences sharedPrefs = getActivity().getSharedPreferences("com.example.anant.iitbhuvaranasi", MODE_PRIVATE);

        try {
            JSONObject jsonObject = new JSONObject(response45678);
            int status = jsonObject.getInt("status");
            JSONArray allcouncils = jsonObject.getJSONArray("councils");
//            JSONObject council = allcouncils.getJSONObject(position);
//            JSONArray clubs = council.getJSONArray("clubs");
            View pinView = LayoutInflater.from(getContext()).inflate(R.layout.activity_pin,null);
            LinearLayout subList = pinView.findViewById(R.id.sub_list);
            int posiClub = 0;
            for (int i = 0; i < allcouncils.length(); i++) {
                JSONObject council = allcouncils.getJSONObject(i);
                JSONArray clubs = council.getJSONArray("clubs");
                for (int j = 0; j < clubs.length(); j++) {
                    JSONObject club = clubs.getJSONObject(j);
                    String clubImage = club.getString("image");
                    String clubTitle = club.getString("name");
                    Switch subItem = (Switch) subList.findViewById(posiClub);
                    if(sharedPrefs.getBoolean("000"+Integer.toString(posiClub), true)) {
                        ImageUrl.add(clubImage);
                        Title.add(clubTitle);
                    }
                    posiClub++;
                }

            }
            ArrayList<String> test = ImageUrl;


            Log.d("status0010", Integer.toString(status));

        } catch (JSONException e) {
            e.printStackTrace();
        }





//        int[] imgId = {R.drawable.slide1, R.drawable.slide2, R.drawable.slide3, R.drawable.slide4, R.drawable.slide5, R.drawable.slide1, R.drawable.slide2, R.drawable.slide3, R.drawable.slide4, R.drawable.slide5};
        RecyclerView horizontalRcv = (RecyclerView) view.findViewById(R.id.horizontal_rcv2);
        HorizontalRecyclerAdap horizontalRecyclerAdap = new HorizontalRecyclerAdap(getContext(), ImageUrl, Title);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        horizontalRcv.setLayoutManager(layoutManager2);
        horizontalRcv.setAdapter(horizontalRecyclerAdap);

        Button addButton = (Button) view.findViewById(R.id.add_button);
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

//    @Override
//    public void onResume() {
//        super.onResume();
//
//            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
//
//    }



    private ArrayList<Object> getObject() {
        objects.add(getHorizontalData1);
        objects.add(getVerticalData5);

       // Log.d("horizontalarray234",getHorizontalData1.toString());
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




