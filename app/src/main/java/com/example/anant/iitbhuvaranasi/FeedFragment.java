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

                /*for (int i = jsonArray.length() - 1; i >= 0; i--) {
                    JSONObject hit = jsonArray.getJSONObject(i);

                    // store in share opref hit.toString()
                    // update last post number
                    // read shared pref and add vertical ddata section

                    String club_name=hit.getString("club");
                    String club_image="http://iitbhuapp.tk"+hit.getString("clubimage");
                    String council_name=hit.getString("council");
                    String council_image="http://iitbhuapp.tk"+hit.getString("councilimage");
                    String title_event=hit.getString("title");
                    String description_event=hit.getString("description");
                    String image_event="http://iitbhuapp.tk"+hit.getString("image");
                    String date_event=hit.getString("datetime");
                    String location=hit.getString("location");

                    Integer viewcount1=hit.getInt("viewedcount");
                    String viewcount=viewcount1.toString();
                    Integer interested1=hit.getInt("interestedcount");
                    String interestedcount=interested1.toString();
                    String interested=hit.getInt("interested") + "";
                    Integer notification_id=hit.getInt("notifid");
                    Integer notif_id=hit.getInt("notifid");
                    String notifid=notif_id.toString();
                    Interestedbutton_class.notification_id=notification_id;
                    getVerticalData1.add(new SingleVerticalData(club_name,club_image,council_name,council_image,title_event,description_event
                            ,image_event,date_event,location,viewcount,interestedcount,interested,notifid));
                    Log.d("verticalse00",getVerticalData1.toArray().toString());

                    // Log.d("imageurl00", image);
                    Log.d("verticaldataori00",getVerticalData1.toString());

                }*/
             //   Log.d("getverticaldata100",getVerticalData1.toString());


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
    //String url= "https://firebasestorage....."
    //Glide.with(getApplicationContext()).load(url).into(imageView);

   /* public static ArrayList<SingleHorizontaldata> getHorizontalData() {
        ArrayList<SingleHorizontaldata> singleHorizontals = new ArrayList<>();
        singleHorizontals.add(new SingleHorizontaldata(R.drawable.culturalcouncil, "Cultural Council"));
        singleHorizontals.add(new SingleHorizontaldata(R.drawable.fmc, "Film and Media Council"));
        singleHorizontals.add(new SingleHorizontaldata(R.drawable.ssc, "Social Service Council"));
        singleHorizontals.add(new SingleHorizontaldata(R.drawable.gamesnsportscouncil, "Games and Sports Council"));
        singleHorizontals.add(new SingleHorizontaldata(R.drawable.ecell, "E Cell"));
        singleHorizontals.add(new SingleHorizontaldata(R.drawable.sntc, "Science and Technology Council"));
        singleHorizontals.add(new SingleHorizontaldata(R.drawable.saic, "Student Alumni Interaction Cell"));
        //singleHorizontals.add(new SingleHorizontaldata(R.drawable.E Cell, "Student's Parliament"));
        singleHorizontals.add(new SingleHorizontaldata(R.drawable.tpc, "Training and Placement Cell"));
        return singleHorizontals;
    }*/

      /* public static ArrayList<SingleVerticalData> getVerticalData1() {
       RequestQueue mRequestQueue;
        final ArrayList<SingleVerticalData> singleVerticals = new ArrayList<>();


      // mRequestQueue = Volley.newRequestQueue();
        JSONObject obj = new JSONObject();
       try {
           obj.put("roll", 18085061);
       } catch (JSONException e) {
           e.printStackTrace();
       }
       String url = "http://iitbhuapp.tk/feed";
       JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, obj, new Response.Listener<JSONObject>() {
           @Override
           public void onResponse(JSONObject response) {
               Log.d("Response", response.toString());

               try {
                   int status = response.getInt("status");
                   Log.d("status", Integer.toString(status));
                   if (status == 1) {

                       Log.d("status1", "1");

                       JSONArray jsonArray = response.getJSONArray("notif");
                       JSONObject hit = jsonArray.getJSONObject(1);
                       String club=hit.getString("club");
                       String title=hit.getString("title");
                       String date=hit.getString("datetime");
                       String image_club="http://iitbhuapp.tk"+hit.getString("clubimage");
                       String image="http://iitbhuapp.tk"+hit.getString("image");
                       Integer viewcount=hit.getInt("viewedcount");
                       Integer interested=hit.getInt("interestedcount");
                       singleVerticals.add(new SingleVerticalData(club,title,date,image_club, image,viewcount,interested));
                        Log.d("singleverticalarray",singleVerticals.toString());
                   } else {
                       Log.d("status0", "0");
                   }


               } catch (JSONException e) {
                   e.printStackTrace();
               }

           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
               VolleyLog.d("TAG", "Error: " + error.getMessage());

           }
       });
      // mRequestQueue.add(request);


        return singleVerticals;
    }*/
 /*public static ArrayList<SingleVerticalData> getVerticalData() {
      final ArrayList<SingleVerticalData> singleVerticals = new ArrayList<>();
       // String url = "http://iitbhuapp.tk/feed";

                singleVerticals.add(new SingleVerticalData("SNTC", "Introductory Workshop DFZ - Dance Club", "Saturday, 25 August 2018", "https://cdn.pixabay.com/photo/2017/11/06/18/39/apple-2924531_960_720.jpg", "https://cdn.pixabay.com/photo/2017/11/06/18/39/apple-2924531_960_720.jpg", "1", "2", "2"));
                i++;

         Log.d("singleverticaladd","hardcode");
        String url_clubs="http://iitbhuapp.tk/clubsandcouncils";
        JSONObject obj = new JSONObject();
        try {
            obj.put("roll", 18085016);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url_clubs,obj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
               // Log.d("Response", response.toString());

                try {
                    int status = response.getInt("status");
                   // Log.d("status", Integer.toString(status));
                    if (status == 1) {

                        //Log.d("status1", "1");
                        Log.d("singleVerticalbeforech",singleVerticals.toString());


                        JSONArray jsonArray = response.getJSONArray("councils");
                        if(singleVerticals.toArray().length<=(jsonArray.length()+1)){



                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject hit = jsonArray.getJSONObject(i);

                            // String name = hit.getString("name");
                            String image_council = "http://iitbhuapp.tk" + hit.getString("image");
                            //  Log.d("clubname", name);
                            //Log.d("imageurl1313",image_council);

                            singleVerticals.add(new SingleVerticalData("SNTC", "Introductory Workshop DFZ - Dance Club", "Saturday, 25 August 2018", image_council, "https://cdn.pixabay.com/photo/2017/11/06/18/39/apple-2924531_960_720.jpg", "1", "2", "2"));
                            String a = Integer.toString(singleVerticals.toArray().length);
                            Log.d("singleVerticalafterch",singleVerticals.toString());

                        }

                        }
                    } else {
                        //Log.d("status0", "0");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());

            }
        });
        mRequestQueue.add(request);
        Log.d("singleVerticalfinal",singleVerticals.toString());
        Log.d("beforereturnvertical", "singlehorizontal");
        return singleVerticals;

    }*/
     /* JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, obj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("Response", response.toString());

                try {
                    int status = response.getInt("status");
                    Log.d("status", Integer.toString(status));
                    if (status == 1) {

                        Log.d("status1", "1");

                        JSONArray jsonArray = response.getJSONArray("notif");

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject hit = jsonArray.getJSONObject(i);


                            String club=hit.getString("club");
                            String title=hit.getString("title");
                            String date=hit.getString("datetime");
                            Integer notification_id=hit.getInt("notifid");
                            Interestedbutton_class.notification_id=notification_id;
                            String image_club="http://iitbhuapp.tk"+hit.getString("clubimage");
                            String image="http://iitbhuapp.tk"+hit.getString("image");
                            Integer viewcount1=hit.getInt("viewedcount");
                            Integer interested1=hit.getInt("interestedcount");
                            Integer notif_id=hit.getInt("notifid");
                            String notifid=notif_id.toString();
                            String viewcount=viewcount1.toString();
                            String interested=interested1.toString();
                            singleVerticals.add(new SingleVerticalData(club,title,date,image_club, image,viewcount,interested,notifid));
                            Log.d("verticalse",singleVerticals.toArray().toString());

                            Log.d("imageurl", image);
                            Log.d("verticaldataori",singleVerticals.toString());

                        }
                        Log.d("getverticaldata1",singleVerticals.toString());

                    } else {
                        Log.d("status0", "0");
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());

            }
        });
        mRequestQueue.add(request);*/
    //Log.d("singleverticalfinal",singleVerticals.toString());

        /* public static ArrayList<SingleVerticalData> getVerticalData1(String club, String title, String date, String image_club, String image, int viewcount, int interested) {
        ArrayList<SingleVerticalData> singleVerticals = new ArrayList<>();
        singleVerticals.add(new SingleVerticalData(club,title,date,image_club, image,viewcount,interested));

        return singleVerticals;
    }
*/


    /* public static ArrayList<SingleHorizontaldata> getHorizontalData() {
        final ArrayList<SingleHorizontaldata> singleHorizontals = new ArrayList<>();
        singleHorizontals.add(new SingleHorizontaldata("https://old.iitbhu.ac.in/imag/it2.png"));
        String url_clubs = "http://iitbhuapp.tk/clubsandcouncils";
        Log.d("singlehorizontaladd","hardcode");
        JSONObject object = new JSONObject();
        try {
            object.put("roll", 18085016);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        JsonObjectRequest request1 = new JsonObjectRequest(Request.Method.POST, url_clubs, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // Log.d("Response", response.toString());

                try {
                    int status = response.getInt("status");
                    //Log.d("status", Integer.toString(status));
                    if (status == 1) {

                        // Log.d("status1", "1");

                        JSONArray jsonArray = response.getJSONArray("councils");

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject hit = jsonArray.getJSONObject(i);

                            // String name = hit.getString("name");
                            String image_council = "http://iitbhuapp.tk" + hit.getString("image");
                            //  Log.d("clubname", name);
                            //Log.d("imageurl",image_council);

                            singleHorizontals.add(new SingleHorizontaldata(image_council));
                            Log.d("horizontaldata2345", singleHorizontals.toString());
                        }
                    } else {
                        // Log.d("status0", "0");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());

            }
        });
        mRequestQueue.add(request1);
        Log.d("singlehorizontalfinal", singleHorizontals.toString());
        // singleHorizontals.add(new SingleHorizontaldata("https://cdn.pixabay.com/photo/2017/11/06/18/39/apple-2924531_960_720.jpg"));
        // singleHorizontals.add(new SingleHorizontaldata("https://cdn.pixabay.com/photo/2017/11/06/18/39/apple-2924531_960_720.jpg"));
        Log.d("beforereturnhorizontal", "singlehorizontal");
        return singleHorizontals;
    }*/

    /*public static ArrayList<SingleHorizontaldata> getHorizontalData() {
        final ArrayList<SingleHorizontaldata> singleHorizontals = new ArrayList<>();
        singleHorizontals.add(new SingleHorizontaldata("https://cdn.pixabay.com/photo/2017/11/06/18/39/apple-2924531_960_720.jpg"));
        singleHorizontals.add(new SingleHorizontaldata("https://old.iitbhu.ac.in/imag/it2.png"));
        singleHorizontals.add(new SingleHorizontaldata("https://cdn.pixabay.com/photo/2017/11/06/18/39/apple-2924531_960_720.jpg"));



        Log.d("singlehorizontaladd","hardcode");
        Log.d("singlehorizontalfinal", singleHorizontals.toString());
         // singleHorizontals.add(new SingleHorizontaldata("https://cdn.pixabay.com/photo/2017/11/06/18/39/apple-2924531_960_720.jpg"));
        Log.d("beforereturnhorizontal", "singlehorizontal");
        Log.d("horizontalarray23",getHorizontalData.toString());

        return singleHorizontals;
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




