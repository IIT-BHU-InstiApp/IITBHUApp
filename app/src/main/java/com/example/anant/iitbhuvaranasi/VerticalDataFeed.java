package com.example.anant.iitbhuvaranasi;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class VerticalDataFeed {

    public static ArrayList<SingleVerticalData> getVerticalData3;

    public static ArrayList<SingleVerticalData> getVerticalData3(final Context context) {

        SharedPreferences pref3 = context.getSharedPreferences(Constants.PREF_NAME, MODE_PRIVATE);
        String resonse_feed = pref3.getString(Constants.Response_Feed_Old, "3");

        getVerticalData3 = new ArrayList<>();

        JSONObject response;

        {
            try {
                response = new JSONObject(resonse_feed);
                int status = response.getInt("status");


                if (status == 1) {

                    JSONArray jsonArray = response.getJSONArray("notif");
                    //JSONArray array = response.getJSONArray("councils");

            /*    for (int j = 0; j < array.length(); j++){
                    JSONObject hit1 = array.getJSONObject(j);
                    String image_council = "http://iitbhuapp.tk" + hit1.getString("image");
                    //
                    //

                    getHorizontalData1.add(new SingleHorizontaldata(image_council));
                }*/


                    for (int i = jsonArray.length() - 1; i >= 0; i--) {
                        JSONObject hit = jsonArray.getJSONObject(i);

                        // store in share opref hit.toString()
                        // update last post number
                        // read shared pref and add vertical ddata section

                        String club_name = hit.getString("club");
                        String club_image = "http://iitbhuapp.tk" + hit.getString("clubimage");
                        String council_name = hit.getString("council");
                        String council_image = "http://iitbhuapp.tk" + hit.getString("councilimage");
                        String title_event = hit.getString("title");
                        String description_event = hit.getString("description");
                        String image_event;
                        if (hit.has("image")) {
                            image_event = "http://iitbhuapp.tk" + hit.getString("image");

                            // It exists, do your stuff
                        } else {
                            image_event = "http://iitbhuapp.tk/media/IMG_20190924_210044.jpg";

                            // It doesn't exist, do nothing
                        }
                        String date_event = hit.getString("datetime");
                        String location = hit.getString("location");
                        String map_location = hit.getString("map_location");

                        Integer viewcount1 = hit.getInt("viewedcount");
                        String viewcount = viewcount1.toString();
                        Integer interested1 = hit.getInt("interestedcount");
                        String interestedcount = interested1.toString();
                        String interested = hit.getInt("interested") + "";
                        Integer notification_id = hit.getInt("notifid");
                        Integer notif_id = hit.getInt("notifid");
                        String notifid = notif_id.toString();
                        Interestedbutton_class.notification_id = notification_id;
                        getVerticalData3.add(new SingleVerticalData(club_name, club_image, council_name, council_image, title_event, description_event
                                , image_event, date_event, location, viewcount, interestedcount, interested, notifid,map_location));


                        //


                    }



                } else {

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

  return getVerticalData3;
    }



}
