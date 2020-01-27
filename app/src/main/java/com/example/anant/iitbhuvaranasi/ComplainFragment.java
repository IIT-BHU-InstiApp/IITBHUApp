package com.example.anant.iitbhuvaranasi;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class ComplainFragment extends Fragment {


    private Spinner spinner;
    private Spinner spinner1;
    int e =0;
    private String message;

    private Uri mImageUri;
    private ImageView image;
    private Button Upload;
    private String hostel;
    private String Complainttype;

    private static final int PICK_IMAGE_REQUEST=1;
    private AutoCompleteTextView autoCompleteTextView;
    private AutoCompleteTextView auto;
    String tag_json_obj = "json_obj_req";
    String email = "sahiljain.eee18@itbhu.ac.in";
    String url = "http://iitbhuapp.tk/postcomplain";
    CheckBox checkBox;
    int i;
    private static RequestQueue mrequestqueue;
    private static TextView text1;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.complain_fragment, container, false);
        mrequestqueue = Volley.newRequestQueue(getContext());
        checkBox=view.findViewById(R.id.checkBox);
        text1=view.findViewById(R.id.text1);
        image=view.findViewById(R.id.image_view);
      TextView textView1 =view.findViewById(R.id.textView);

        // InputFilter[] filterArray = new InputFilter[1];
        //filterArray[0] = new InputFilter.LengthFilter(30);
        //autoCompleteTextView.setFilters(filterArray);
        //autoCompleteTextView=findViewById(R.id.autoCompleteTextView);

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(checkBox.isChecked()){
                    checkBox.setChecked(false);
                }
                else
                    checkBox.setChecked(true);


            }
        });

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked())
                    i = 1;
                else
                    i = 0;
            }
        });

        spinner = view.findViewById(R.id.spin1);
        List<String> complaints = new ArrayList<>();
        complaints.add(0, "Select Type ");
        complaints.add("Hostel Facilities");
        complaints.add("Mess Facilities");
        complaints.add("Academics Facilities");
        complaints.add("Others");

        spinner1 = view.findViewById(R.id.spin2);
        List<String> hostels = new ArrayList<>();
        hostels.add(0, "Hostels ");
        hostels.add("Aryabhatta 1");
        hostels.add("Aryabhatta 2");
        hostels.add("SC dey");
        hostels.add("DG");
        hostels.add("visvesaraiys");
        hostels.add("Ramanujan");
        hostels.add("GSMC Ext.");
        hostels.add("GSMC");
        hostels.add("Morvi");
        hostels.add("Limbdi");
        hostels.add("Cv Raman");
        hostels.add("IIT girls");
        hostels.add("vishwakarma");
        hostels.add("vivekanand");
        hostels.add("Rajputana");


        ArrayAdapter<String> data_adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, complaints);
        data_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(data_adapter);
        ArrayAdapter<String> data_adapter2 = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, hostels);
        data_adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(data_adapter2);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                              @Override
                                              public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                  if (parent.getItemAtPosition(position).equals("Select Type")) {


                                                  } else {
                                                      String item = parent.getItemAtPosition(position).toString();
                                                      Complainttype = item;
                                                      Toast.makeText(parent.getContext(), item, Toast.LENGTH_SHORT).show();
                                                  }

                                                  if (parent.getItemAtPosition(position).equals("Hostel Facilities")) {
                                                      email = "hostel@gmail.com";

                                                  } else if (parent.getItemAtPosition(position).equals("Mess Facilities")) {
                                                      email = "Mess@gmail.com";

                                                  } else if (parent.getItemAtPosition(position).equals("Academics Facilities")) {
                                                      email = "Academics@gmail.com";

                                                  } else if (parent.getItemAtPosition(position).equals("Others")) {
                                                      email = "others@gmail.com";

                                                  }
                                              }

                                              @Override
                                              public void onNothingSelected(AdapterView<?> parent) {


                                              }
                                          }


        );
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                               @Override
                                               public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                   if (parent.getItemAtPosition(position).equals("Select Hostel ")) {

                                                   } else {
                                                       String item1 = parent.getItemAtPosition(position).toString();
                                                       hostel=item1;
                                                       Toast.makeText(parent.getContext(), item1, Toast.LENGTH_SHORT).show();
                                                   }


                                               }

                                               @Override
                                               public void onNothingSelected(AdapterView<?> parent) {

                                               }
                                           }


        );
        auto = view.findViewById(R.id.auto);
        autoCompleteTextView = view.findViewById(R.id.autoCompleteTextView);
        Button Send = view.findViewById(R.id.button);
        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image.setImageBitmap(null);
                mImageUri=null;
                Upload.setText("Upload");

            }
        });
        Upload = view.findViewById(R.id.button3);
        Upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
                Upload.setText("Change");
            }
        });
       message = auto.getText().toString();
        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((Complainttype=="Select Type ")){
                    Toast.makeText(getContext(),"Please fill type of complain",Toast.LENGTH_SHORT).show();}
                else if(hostel=="Hostels ")
                    Toast.makeText(getContext(),"Please select hostel",Toast.LENGTH_SHORT).show();
                else if(auto==null)
                    Toast.makeText(getContext(),"Please fill message",Toast.LENGTH_SHORT).show();
                else{


                AlertDialog.Builder a_builder = new AlertDialog.Builder(getContext());
                a_builder.setMessage("I am aware that if I will misuse this facility by any way I would be deregistered from this app");
                a_builder.setCancelable(false);
                a_builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String message = auto.getText().toString();
                        final String subject = autoCompleteTextView.getText().toString();
                        final ProgressDialog pdialog = new ProgressDialog(getContext());
                        pdialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        pdialog.setMessage("Sending Mail....");
                        pdialog.show();



                        JSONObject postparams = new JSONObject();

                        try {
                            postparams.put("header", subject);
                            postparams.put("type",Complainttype);
                            postparams.put("hostel",hostel);
                            postparams.put("roll",18085061);
                            postparams.put("complain",message);
                            postparams.put("anonymous",i);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, url, postparams, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d("aaaaaaa", "ccccccc ");
                                pdialog.dismiss();
                                Toast.makeText(getContext(),"Message sended",Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
                            }
                        });
                        mrequestqueue.add(jsonObjReq);
                        //AppController.getInstance ().addToRequestQueue ( jsonObjReq, tag_json_obj );
                      /*  String recipientlist = email;
                       message = auto.getText().toString();
                        String[] recipients = recipientlist.split(",");
                        String subject = autoCompleteTextView.getText().toString();
                        final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
                        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                        intent.putExtra(Intent.EXTRA_TEXT, message);
                        intent.setType("image/png");
                        intent.putExtra(Intent.EXTRA_STREAM,mImageUri);
                       final PackageManager pm = getActivity().getPackageManager();
                        final List<ResolveInfo> matches = pm.queryIntentActivities(intent, 0);
                        ResolveInfo best = null;
                        for (final ResolveInfo info : matches)
                            if (info.activityInfo.packageName.endsWith(".gm") ||
                                    info.activityInfo.name.toLowerCase().contains("gmail")) best = info;
                        if (best != null)
                            intent.setClassName(best.activityInfo.packageName, best.activityInfo.name);
                        startActivity(intent);
*/

                        // Intent intent = new Intent(Intent.ACTION_SEND);
                        // intent.putExtra(Intent.EXTRA_EMAIL, recipients);
                        //intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                        //intent.putExtra(Intent.EXTRA_TEXT, message);

                        // intent.setType("image/png");
                        //intent.putExtra(Intent.EXTRA_STREAM,mImageUri);
                        // startActivity(Intent.createChooser(intent, "Share image using"));
                        //intent.setType("message/rfc822");
                        //startActivity(Intent.createChooser(intent, "choose an email client"));

                    }
                });
                a_builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();


                    }
                });
                AlertDialog alert = a_builder.create();
                alert.setTitle("Alert!");
                alert.show();
            }}
        });
return view;
    }
    private void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"),PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {

            mImageUri = data.getData();
            Picasso.get().load(mImageUri).into(image);

        }
    }
}
