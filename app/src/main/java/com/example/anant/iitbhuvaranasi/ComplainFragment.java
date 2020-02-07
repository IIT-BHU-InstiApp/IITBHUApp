package com.example.anant.iitbhuvaranasi;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;
import static com.android.volley.VolleyLog.TAG;

public class ComplainFragment extends Fragment {


    private Toolbar toolbar;
    private ImageButton sendButton;
    private LinearLayout uploadedImageContainer;
    private String hostel;
    private String Complainttype;
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int CAPTURE_IMAGE_REQUEST =2;
    private EditText subjectEditBox;
    private EditText issueBox;
    private String url = "http://iitbhuapp.tk/postcomplain";
    private CheckBox anonymousCheckbox;
    private int keepAnonymous;
    private RequestQueue mrequestqueue;
    private RequestQueue imageRequestqueue;
    private TextView removeImage;
    private ArrayList<String> base64toString;
    private Intent cameraIntent;
    private Dialog attachImageOption;

    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.complain_fragment, container, false);


        mrequestqueue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
        imageRequestqueue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
        issueBox = view.findViewById(R.id.issue_box);
        toolbar = (Toolbar) Objects.requireNonNull(getActivity()).findViewById(R.id.toolbar);
        uploadedImageContainer = view.findViewById(R.id.uploaded_image_container);
        anonymousCheckbox = view.findViewById(R.id.anonymous_checkbox);
        removeImage = view.findViewById(R.id.remove_image);
        Spinner complainSpinner = view.findViewById(R.id.complain_spinner);
        final Spinner hostelSpinner = view.findViewById(R.id.hostel_spinner);
        ImageButton anonymousHelp = (ImageButton) view.findViewById(R.id.anonymous_help);
        subjectEditBox = view.findViewById(R.id.subject_edittext);
        ImageButton addImage = view.findViewById(R.id.add_image);

        base64toString = new ArrayList<>();


        //Creating send button
        int endMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics());
        sendButton = new ImageButton(getContext());
        Toolbar.LayoutParams sendLayoutParam = new Toolbar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        sendLayoutParam.gravity = Gravity.END;
        sendLayoutParam.setMarginEnd(endMargin);
        sendButton.setLayoutParams(sendLayoutParam);
        sendButton.setBackground(getContext().getResources().getDrawable(R.drawable.ic_send_black_24dp));

        // List for complainttype & hostel
        List<String> complaints = new ArrayList<>();
        complaints.add(0, "Complain Regarding");
        complaints.add("Academics Facilities");
        complaints.add("Hostel Facilities");
        complaints.add("Mess Facilities");
        complaints.add("Others");

        List<String> hostels = new ArrayList<>();
        hostels.add(0, "Your Hostel");
        hostels.add("Aryabhatta - I (A & B Block)");
        hostels.add("Aryabhatta - II (C & D Block)");
        hostels.add("A.S.N. Bose");
        hostels.add("Dhanrajgiri");
        hostels.add("Dr. C.V. Raman");
        hostels.add("Dr. S. Ramanujan");
        hostels.add("Gandhi Smriti Chhatravas (EXTENSION)");
        hostels.add("Gandhi Smriti Chhatravas (OLD)");
        hostels.add("IIT (BHU) Girl's Hostel – I");
        hostels.add("IIT (BHU) Girl's Hostel – II");
        hostels.add("IIT Boy's (Saluja)");
        hostels.add("Limbdi");
        hostels.add("Morvi");
        hostels.add("Rajputana");
        hostels.add("S.C. Dey");
        hostels.add("Vishwakarma");
        hostels.add("Vishweshvaraiya");
        hostels.add("Vivekananda");

        // Setting up adapters to spinners
        ArrayAdapter<String> complaintsAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, complaints) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                switch (position) {
                    case 0:

                        tv.setTypeface(null, Typeface.BOLD);
                        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, 52);
                        tv.setTextColor(getResources().getColor(R.color.colorPrimary));
                        break;
                    default:
                        tv.setTypeface(null, Typeface.NORMAL);
                        tv.setTextColor(Color.BLACK);
                        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, 48);
                        break;
                }
                return view;
            }
        };
        complaintsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        complainSpinner.setAdapter(complaintsAdapter);

        ArrayAdapter<String> hostelAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, hostels) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                switch (position) {
                    case 0:
                        tv.setTypeface(null, Typeface.BOLD);
                        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, 52);
                        tv.setTextColor(getResources().getColor(R.color.colorPrimary));
                        break;
                    default:
                        tv.setTypeface(null, Typeface.NORMAL);
                        tv.setTextColor(Color.BLACK);
                        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, 48);
                        break;
                }
                return view;
            }
        };
        hostelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hostelSpinner.setAdapter(hostelAdapter);

        complainSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Complainttype = parent.getItemAtPosition(position).toString();

//           if (parent.getItemAtPosition(position).equals("Hostel Facilities")) {
//              email = "hostel@gmail.com";
//
//           } else if (parent.getItemAtPosition(position).equals("Mess Facilities")) {
//              email = "Mess@gmail.com";
//
//           } else if (parent.getItemAtPosition(position).equals("Academics Facilities")) {
//             email = "Academics@gmail.com";
//
//           } else if (parent.getItemAtPosition(position).equals("Others")) {
//             email = "others@gmail.com";
//
//           }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        hostelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    hostel = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }


        );

//        issueBox.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                issueBox.setBackground(null);
//                return false;
//            }
//        });

        // Anonymous Checkbox
        anonymousCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (anonymousCheckbox.isChecked())
                    keepAnonymous = 1;
                else
                    keepAnonymous = 0;
            }
        });

        anonymousHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder warning = new AlertDialog.Builder(Objects.requireNonNull(getContext()));
                warning.setMessage("Your identity will be kept anonymous to complain authority.\n\nBut in case of any dispute/misuse of this facility your identity can be reveled to authorities.");
                warning.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        warning.show().dismiss();
                    }
                });

                warning.show();
            }
        });

        anonymousHelp.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getContext(), "Help", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Adding & Removing Image
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                attachImageOption = new Dialog(getContext());
                LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.uploadimage_dialog_layout,null,false);


                linearLayout.findViewById(R.id.attachimage).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        attachImage();
                    }
                });



                linearLayout.findViewById(R.id.captureimage).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        captureImage();
                    }
                });

                attachImageOption.addContentView(linearLayout,params);

                attachImageOption.show();






            }
        });

        removeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 1; i < uploadedImageContainer.getChildCount(); ) {
                    uploadedImageContainer.removeViewAt(i);
                }
                base64toString.clear();
                removeImage.setVisibility(View.GONE);
            }
        });

        // Send Button
        sendButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getContext(), "Send Complain", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Complainttype.equals("Complain Regarding")) {
                    Toast.makeText(getContext(), "Please specify complain type", Toast.LENGTH_SHORT).show();
                } else if (hostel.equals("Your Hostel")) {
                    Toast.makeText(getContext(), "Please select your hostel", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(subjectEditBox.getText())) {
//                    issueBox.setBackground(getResources().getDrawable(R.drawable.button_style));
                    issueBox.setPressed(true);
                    Toast.makeText(getContext(), "Please fill subject of complain", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(issueBox.getText())) {
//                    issueBox.setBackground(getResources().getDrawable(R.drawable.button_style));
                    issueBox.setPressed(true);
                    Toast.makeText(getContext(), "Please describe your issue", Toast.LENGTH_SHORT).show();
                } else {
                    AlertDialog.Builder a_builder = new AlertDialog.Builder(getContext());
                    a_builder.setMessage("I am aware that if I will misuse this facility by any way I would be deregistered from this app");
                    a_builder.setCancelable(false);
                    a_builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            final String message = issueBox.getText().toString();
                            final String subject = subjectEditBox.getText().toString();
                            final ProgressDialog pdialog = new ProgressDialog(getContext());
                            pdialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                            pdialog.setMessage("Registering your complain....");
                            pdialog.show();


                            JSONObject complain = new JSONObject();
                            try {
                                complain.put("roll", 18085016);
                                complain.put("complain", message);
                                complain.put("anonymous", keepAnonymous);
                                complain.put("header", subject);
                                complain.put("type", Complainttype);
                                complain.put("hostel", hostel);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, url, complain, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {

                                    pdialog.dismiss();
                                    Toast.makeText(getContext(), "Complain registered", Toast.LENGTH_SHORT).show();
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    pdialog.dismiss();
                                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                                    error.printStackTrace();
                                    VolleyLog.d("TAG", "Error: " + error.getMessage());
                                }
                            });
                            mrequestqueue.add(jsonObjReq);
//                            //AppController.getInstance ().addToRequestQueue ( jsonObjReq, tag_json_obj );
//                      /*  String recipientlist = email;
//                       message = auto.getText().toString();
//                        String[] recipients = recipientlist.split(",");
//                        String subject = autoCompleteTextView.getText().toString();
//                        final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
//                        intent.setType("text/plain");
//                        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
//                        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
//                        intent.putExtra(Intent.EXTRA_TEXT, message);
//                        intent.setType("image/png");
//                        intent.putExtra(Intent.EXTRA_STREAM,mImageUri);
//                       final PackageManager pm = getActivity().getPackageManager();
//                        final List<ResolveInfo> matches = pm.queryIntentActivities(intent, 0);
//                        ResolveInfo best = null;
//                        for (final ResolveInfo info : matches)
//                            if (info.activityInfo.packageName.endsWith(".gm") ||
//                                    info.activityInfo.name.toLowerCase().contains("gmail")) best = info;
//                        if (best != null)
//                            intent.setClassName(best.activityInfo.packageName, best.activityInfo.name);
//                        startActivity(intent);
//*/
//
//                            // Intent intent = new Intent(Intent.ACTION_SEND);
//                            // intent.putExtra(Intent.EXTRA_EMAIL, recipients);
//                            //intent.putExtra(Intent.EXTRA_SUBJECT, subject);
//                            //intent.putExtra(Intent.EXTRA_TEXT, message);
//
//                            // intent.setType("image/png");
//                            //intent.putExtra(Intent.EXTRA_STREAM,mImageUri);
//                            // startActivity(Intent.createChooser(intent, "Share image using"));
//                            //intent.setType("message/rfc822");
//                            //startActivity(Intent.createChooser(intent, "choose an email client"));
//
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

                }
            }


        });

        return view;
    }

    // Working with Attach & remove Image
    private void attachImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    private void captureImage(){
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, PICK_IMAGE_REQUEST);
        }
        cameraIntent = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAPTURE_IMAGE_REQUEST);

    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());
        int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());



        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
            assert data != null;
            ClipData mClipData = data.getClipData();


            if (mClipData != null && mClipData.getItemCount() > 1) {

                for (int i = 0; i < mClipData.getItemCount(); i++) {
                    ImageView image = new ImageView(getContext());
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width,
                            LinearLayout.LayoutParams.MATCH_PARENT);
                    params.setMargins(margin, margin, margin, margin);
                    image.setLayoutParams(params);
                    image.setScaleType(ImageView.ScaleType.CENTER_CROP);

                    Uri imageUri = mClipData.getItemAt(i).getUri();

                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(Objects.requireNonNull(getActivity()).getContentResolver(),imageUri);
                        base64toString.add(imagetoString(bitmap));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    image.setImageURI(imageUri);
                    uploadedImageContainer.addView(image);
                    removeImage.setVisibility(View.VISIBLE);
                }
            } else {
                Uri imageUri = data.getData();

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(Objects.requireNonNull(getActivity()).getContentResolver(),imageUri);
                        base64toString.add(imagetoString(bitmap));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ImageView image = new ImageView(getContext());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                params.setMargins(margin, margin, margin, margin);
                image.setLayoutParams(params);
                image.setScaleType(ImageView.ScaleType.CENTER_CROP);

                image.setImageURI(imageUri);
                uploadedImageContainer.addView(image);
                removeImage.setVisibility(View.VISIBLE);
            }

        }

        if (requestCode == CAPTURE_IMAGE_REQUEST && resultCode == RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");

            base64toString.add(imagetoString(bitmap));

            ImageView image = new ImageView(getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            params.setMargins(margin, margin, margin, margin);
            image.setLayoutParams(params);
            image.setScaleType(ImageView.ScaleType.CENTER_CROP);

            image.setImageBitmap(bitmap);
            uploadedImageContainer.addView(image);
            removeImage.setVisibility(View.VISIBLE);


        }

        attachImageOption.hide();

    }





    private void uploadImage(){

        StringRequest stringRequest = new StringRequest(Request.Method.POST,"Some Url",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {

            protected Map<String,String> getparams() throws AuthFailureError{
                Map<String,String> params = new HashMap<>();
                params.put("tags","uploaded Image");
//                params.put("image",);

                return params;
            }
        };
        imageRequestqueue.add(stringRequest);
    }

    private String imagetoString(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte,Base64.DEFAULT);
    }

    @Override
    public void onResume() {
        super.onResume();


        toolbar.setTitle("Complain");
        //        toolbar.setNavigationIcon(null);
//        ((HomeActivity) getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);
//        ((HomeActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        ((HomeActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.addView(sendButton);


    }

    @Override
    public void onStop() {
        super.onStop();

        toolbar.removeView(sendButton);
        toolbar.setTitle(R.string.app_name);

    }

}
