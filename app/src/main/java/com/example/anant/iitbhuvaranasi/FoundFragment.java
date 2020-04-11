package com.example.anant.iitbhuvaranasi;

import android.Manifest;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;
import static com.example.anant.iitbhuvaranasi.Constants.ADD_FOUND_FORM_URL;
import static com.example.anant.iitbhuvaranasi.Constants.KEY_ACTION;
import static com.example.anant.iitbhuvaranasi.Constants.KEY_FOUND_Email;
import static com.example.anant.iitbhuvaranasi.Constants.KEY_FOUND_FOUNDITEM;
import static com.example.anant.iitbhuvaranasi.Constants.KEY_FOUND_LOCATION;
import static com.example.anant.iitbhuvaranasi.Constants.KEY_FOUND_NAME;
import static com.example.anant.iitbhuvaranasi.Constants.KEY_FOUND_OWNER_BRANCH;
import static com.example.anant.iitbhuvaranasi.Constants.KEY_FOUND_OWNER_NAME;
import static com.example.anant.iitbhuvaranasi.Constants.KEY_FOUND_TO_CONTACT;
import static com.example.anant.iitbhuvaranasi.Constants.KEY_LOST_IMAGE;
import static com.example.anant.iitbhuvaranasi.HomeActivity.emailOfStudent;
import static com.example.anant.iitbhuvaranasi.HomeActivity.name_student;
import static com.example.anant.iitbhuvaranasi.LostAndFoundFragment.addImage;
import static com.example.anant.iitbhuvaranasi.LostAndFoundFragment.sendButton;


public class FoundFragment extends Fragment {


    private LinearLayout uploadedImageContainer;
    private String branch, semester, email, name1;
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int CAPTURE_IMAGE_REQUEST = 2;
    private static final int CAMERA_PERMISSION_REQUEST = 3;
    private EditText ownerName;
    private EditText foundItem;
    private EditText contact;
    private EditText location;
    private TextView removeImage;
    private ArrayList<String> UserImage;
    private Animation bottomUp,bottomDown;
    private LinearLayout hiddenPanel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lost_found_viewpager, container, false);

        ownerName = view.findViewById(R.id.owner_name);
        location = view.findViewById(R.id.location);
        EditText details = view.findViewById(R.id.details);
        contact = view.findViewById(R.id.contact);
        location = view.findViewById(R.id.location);
        uploadedImageContainer = view.findViewById(R.id.uploaded_image_container);
        removeImage = view.findViewById(R.id.remove_image);
        Spinner branchSpinner = view.findViewById(R.id.branch);
        Spinner semesterSpinner = view.findViewById(R.id.semester);
        foundItem = view.findViewById(R.id.lost_found_Item);
        TextView name = view.findViewById(R.id.name);
        TextView emailaddress = view.findViewById(R.id.emailaddress);
        TextView linkInfo = view.findViewById(R.id.link_info);
        TextView ownerInformation = view.findViewById(R.id.owner_information);
        TextInputLayout lostItemTILayout = view.findViewById(R.id.lost_found_item_layout);
        TextInputLayout locationlayout = view.findViewById(R.id.location_layout);
        TextInputLayout contactLayout = view.findViewById(R.id.contact_layout);

        bottomUp = AnimationUtils.loadAnimation(getContext(),
                R.anim.bottom_up);
        bottomDown = AnimationUtils.loadAnimation(getContext(),R.anim.bottom_down);
        hiddenPanel = (LinearLayout) view.findViewById(R.id.upload_image);
        View outsideCard = hiddenPanel.findViewById(R.id.outside_card);


        outsideCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hiddenPanel.startAnimation(bottomDown);
                hiddenPanel.setVisibility(View.GONE);

            }
        });


        SpannableString spannableString = new SpannableString("All found forms are registered in the given sheet link.");
        ClickableSpan link = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                String url = "https://docs.google.com/spreadsheets/d/1hp0PnIh2fKjbXRfUVkycWzCpgaLlG54TTqCvGnsiVdc/edit?ts=5e8de55a#gid=0";
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(requireContext(), Uri.parse(url));

            }
        };

        spannableString.setSpan(link, 44, 54, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(requireContext().getResources().getColor(R.color.holo_blue_light)), 44, 54, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


        linkInfo.setText(spannableString);
        linkInfo.setMovementMethod(LinkMovementMethod.getInstance());


        email = emailOfStudent;
        name1 = name_student;
        name.setText(name1);
        emailaddress.setText(email);


        UserImage = new ArrayList<>();

        ownerInformation.setText("Owner's info. (If available in the item found)");
        contactLayout.setHint("Your Contact Number");
        lostItemTILayout.setHint("Found Item");
        locationlayout.setHint("Found at (Location)");

        details.setHint(null);
        details.setEnabled(false);


        List<String> Semester = new ArrayList<>();
        Semester.add(0, "Semester");
        Semester.add("Sem: I");
        Semester.add("Sem: II");
        Semester.add("Sem: III");
        Semester.add("Sem: IV");
        Semester.add("Sem: V");
        Semester.add("Sem: VI");
        Semester.add("Sem: VII");
        Semester.add("Sem: VIII");
        Semester.add("Sem: IX (IDD)");
        Semester.add("Sem: X (IDD)");
        Semester.add("M.Tech");
        Semester.add("PhD");


        List<String> Branch = new ArrayList<>();
        Branch.add(0, "Owner's Branch");

        Branch.add("Architecture, Planning and Design");
        Branch.add("Biochemical Engineering");
        Branch.add("Biomedical Engineering");
        Branch.add("Ceramic Engineering and Technology");
        Branch.add("Chemical Engineering ");
        Branch.add("Chemistry");
        Branch.add("Civil Engineering");
        Branch.add("Computer Science and Engineering");
        Branch.add("Electrical Engineering");
        Branch.add("Electronics Engineering");
        Branch.add("Humanistic Studies");
        Branch.add("Materials Science and Technology");
        Branch.add("Mathematical Sciences");
        Branch.add("Mechanical Engineering");
        Branch.add("Metallurgical Engineering");
        Branch.add("Mining Engineering");
        Branch.add("Pharmaceutical Engineering and Technology");
        Branch.add("Physics");

        // Setting up adapters to spinners
        ArrayAdapter<String> semesterAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, Semester) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {

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
        semesterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        semesterSpinner.setAdapter(semesterAdapter);

        ArrayAdapter<String> branchAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, Branch) {
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
        branchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        branchSpinner.setAdapter(branchAdapter);

        semesterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                semester = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        branchSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                                        branch = parent.getItemAtPosition(position).toString();
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



        //Removing Image
        removeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 1; i < uploadedImageContainer.getChildCount(); ) {
                    uploadedImageContainer.removeViewAt(i);
                }
                UserImage.clear();
                uploadedImageContainer.setVisibility(View.GONE);
                removeImage.setVisibility(View.GONE);
            }
        });


        return view;
    }


    public void onClick(int a) {
        if (a == 1) {

            // Adding Image
            addImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (uploadedImageContainer.getChildCount() < 4) {

                        if(hiddenPanel.getVisibility() == View.VISIBLE){
                            hiddenPanel.startAnimation(bottomDown);
                            hiddenPanel.setVisibility(View.GONE);
                        }else {

                            hiddenPanel.startAnimation(bottomUp);
                            hiddenPanel.setVisibility(View.VISIBLE);

                        }
                        hiddenPanel.findViewById(R.id.attachimage).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                attachImage();
                            }
                        });
                        hiddenPanel.findViewById(R.id.captureimage).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                captureImage();
                            }
                        });
                    } else {
                        try {
                            Snackbar.make(requireView(), "You have reached your maximum upload limit of 4", Snackbar.LENGTH_SHORT).show();
                        } catch (NullPointerException e) {
                            Log.e("FoundFragment", "Snackbar: You have reached your maximum upload limit of 4", e);
                            Toast.makeText(getContext(), "You have reached your maximum upload limit of 4", Toast.LENGTH_SHORT).show();
                        }
                    }


                }
            });


            sendButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (TextUtils.isEmpty(foundItem.getText())) {
                        foundItem.setPressed(true);
                        try {
                            Snackbar.make(requireView(), "Please specify foundItem", Snackbar.LENGTH_SHORT).show();
                        } catch (NullPointerException e) {
                            Log.e("FoundFragment", "Snackbar: Please specify foundItem", e);
                            Toast.makeText(getContext(), "Please specify foundItem", Toast.LENGTH_SHORT).show();
                        }
                    } else if (TextUtils.isEmpty(location.getText())) {
                        location.setPressed(true);
                        try {
                            Snackbar.make(requireView(), "Please specify where you found item", Snackbar.LENGTH_SHORT).show();
                        } catch (NullPointerException e) {
                            Log.e("FoundFragment", "Snackbar: Please specify where you found item", e);
                            Toast.makeText(getContext(), "Please specify where you found item", Toast.LENGTH_SHORT).show();
                        }
                    } else if (TextUtils.isEmpty(contact.getText())) {
                        contact.setPressed(true);
                        try {
                            Snackbar.make(requireView(), "Please fill your contact number", Snackbar.LENGTH_SHORT).show();
                        } catch (NullPointerException e) {
                            Log.e("FoundFragment", "Snackbar: Please fill your contact number", e);
                            Toast.makeText(getContext(), "Please fill your contact number", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AlertDialog.Builder a_builder = new AlertDialog.Builder(requireContext());
                        a_builder.setMessage("I am aware that if I will misuse this facility by any way I would be deregistered from this app");
                        a_builder.setCancelable(false);
                        a_builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final String OwnerName = ownerName.getText().toString();
                                final String Location = location.getText().toString();
                                final String Contact = contact.getText().toString();
                                final String FoundItem = foundItem.getText().toString();
                                final ProgressDialog pdialog = new ProgressDialog(getContext());
                                pdialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                pdialog.setMessage("Submitting your form....");
                                pdialog.show();
                                final String Semester = semester.trim();
                                final String Branch = branch.trim();

                                StringRequest stringRequest = new StringRequest(Request.Method.POST, ADD_FOUND_FORM_URL,
                                        new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {
                                                pdialog.dismiss();
                                                //Toast.makeText(getContext(),response,Toast.LENGTH_LONG).show();
                                                if ((response.toString()).equals("Success")) {
                                                    try {
                                                        Snackbar.make(requireView(), "Form successfully Registered", Snackbar.LENGTH_SHORT).show();
                                                    } catch (NullPointerException e) {
                                                        Log.e("FoundFragment", "Snackbar: Form successfully Registered", e);
                                                        Toast.makeText(getContext(), "Form successfully Registered", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            }
                                        },
                                        new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                pdialog.dismiss();
                                                try {
                                                    Snackbar.make(requireView(), "Something went Wrong\nTry again Later", Snackbar.LENGTH_SHORT).show();
                                                } catch (NullPointerException e) {
                                                    Log.e("FoundFragment", "Snackbar: Something went Wrong\nTry again Later", e);
                                                    Toast.makeText(getContext(), "Something went Wrong\nTry again Later", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        }) {
                                    @Override
                                    protected Map<String, String> getParams() {
                                        Map<String, String> params = new HashMap<String, String>();

                                        params.put(KEY_ACTION, "insert");
                                        params.put(KEY_FOUND_NAME, name1);
                                        params.put(KEY_FOUND_Email, email);
                                        params.put(KEY_FOUND_OWNER_NAME, OwnerName);
                                        params.put(KEY_FOUND_OWNER_BRANCH, Branch + Semester);
                                        params.put(KEY_FOUND_FOUNDITEM, FoundItem);
                                        params.put(KEY_FOUND_LOCATION, Location);
                                        params.put(KEY_FOUND_TO_CONTACT, Contact);
                                        if (UserImage != null) {
                                            for (int i = 0; i < UserImage.size(); i++) {
                                                params.put(KEY_LOST_IMAGE + i, UserImage.get(i));
                                            }
                                        }
                                        return params;
                                    }

                                };

                                int socketTimeout = 30000; // 30 seconds. You can change it
                                RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

                                stringRequest.setRetryPolicy(policy);


                                RequestQueue requestQueue = Volley.newRequestQueue(requireContext());

                                requestQueue.add(stringRequest);


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

        }
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

    private void captureImage() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{
                    Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST);

        } else {
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAPTURE_IMAGE_REQUEST);
        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,200,getResources().getDisplayMetrics());
        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());
        int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());


        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
            assert data != null;
            ClipData mClipData = data.getClipData();


            if (mClipData != null && mClipData.getItemCount() > 1 && mClipData.getItemCount() < 5 - uploadedImageContainer.getChildCount()) {

                for (int i = 0; i < mClipData.getItemCount(); i++) {
                    ImageView image = new ImageView(getContext());
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
                    params.setMargins(margin, margin, margin, margin);
                    image.setLayoutParams(params);
                    image.setScaleType(ImageView.ScaleType.CENTER_CROP);

                    Uri imageUri = mClipData.getItemAt(i).getUri();

                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), imageUri);
                        Bitmap rbitmap = getResizedBitmap(bitmap, 500);//Setting the Bitmap to ImageView
                        String userImage = getStringImage(rbitmap);
                        UserImage.add(userImage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    image.setImageURI(imageUri);
                    uploadedImageContainer.addView(image);
                    uploadedImageContainer.setVisibility(View.VISIBLE);
                    removeImage.setVisibility(View.VISIBLE);
                }
            } else if (data.getData() != null) {
                Uri imageUri = data.getData();

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), imageUri);
                    Bitmap rbitmap = getResizedBitmap(bitmap, 500);//Setting the Bitmap to ImageView
                    String userImage = getStringImage(rbitmap);
                    //base64toString.add(userImage);
                    UserImage.add(userImage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ImageView image = new ImageView(getContext());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
                params.setMargins(margin, margin, margin, margin);
                image.setLayoutParams(params);
                image.setScaleType(ImageView.ScaleType.CENTER_CROP);

                image.setImageURI(imageUri);
                uploadedImageContainer.addView(image);
                uploadedImageContainer.setVisibility(View.VISIBLE);
                removeImage.setVisibility(View.VISIBLE);
            } else {
                try {
                    Snackbar.make(requireView(), "Can't upload more than 4 images", Snackbar.LENGTH_SHORT).show();
                } catch (NullPointerException e) {
                    Log.e("ComplainFragment", "Can't upload more than 4 images", e);
                    Toast.makeText(getContext(), "Can't upload more than 4 images", Toast.LENGTH_SHORT).show();

                }
            }

        } else {
            Toast.makeText(getContext(), "You haven't picked image", Toast.LENGTH_SHORT).show();
        }

        if (requestCode == CAPTURE_IMAGE_REQUEST && resultCode == RESULT_OK) {
            assert data != null;
            Bitmap bitmap = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");
            assert bitmap != null;
            Bitmap rbitmap = getResizedBitmap(bitmap, 500);//Setting the Bitmap to ImageView
            String userImage = getStringImage(rbitmap);
            // base64toString.add(userImage);
            UserImage.add(userImage);

            ImageView image = new ImageView(getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
            params.setMargins(margin, margin, margin, margin);
            image.setLayoutParams(params);
            image.setScaleType(ImageView.ScaleType.CENTER_CROP);

            image.setImageBitmap(bitmap);

            uploadedImageContainer.addView(image);
            uploadedImageContainer.setVisibility(View.VISIBLE);
            removeImage.setVisibility(View.VISIBLE);


        }
        hiddenPanel.startAnimation(bottomDown);
        hiddenPanel.setVisibility(View.GONE);

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

}
