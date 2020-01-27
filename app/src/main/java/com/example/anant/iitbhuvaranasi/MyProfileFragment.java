package com.example.anant.iitbhuvaranasi;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import app.AppController;
import de.hdodenhof.circleimageview.CircleImageView;

public class MyProfileFragment extends Fragment {

    private EditText Name,Rollno,Department,Email,Contact;
    private CircleImageView imageView;
    private CheckBox checkBox;
    private Button Edit,Save;
    private Uri personphoto;
    SharedPreferences myprefs;
    String tag_json_obj = "json_obj_req";
    private int check,rollno;
    private boolean result=false;
    private TextView Nm,Rn,Ct;
    private String url = "http://iitbhuapp.tk/login";
    private ProgressDialog pDialog;
    private String email,dept,year,name,roll,contact,img_url;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.activity_edit_profile, container, false);





        imageView=(CircleImageView) view.findViewById(R.id.profilepic);
        Nm=(TextView) view.findViewById(R.id.namet);
        Rn = (TextView) view.findViewById(R.id.rollnot);
        Ct = (TextView) view.findViewById(R.id.contactnot);
        Name = (EditText) view.findViewById(R.id.name);
        Department = (EditText) view.findViewById(R.id.dept);
        Rollno =(EditText) view.findViewById(R.id.rollno);
        Contact=(EditText) view.findViewById(R.id.contactno);
        Email=(EditText) view.findViewById(R.id.email);
        Edit=(Button) view.findViewById(R.id.edit);
        Save=(Button) view.findViewById(R.id.save);
        checkBox = (CheckBox) view.findViewById(R.id.check);

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                result = checkBox.isChecked();
            }
        });

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Name.getText().toString().isEmpty()){
                    name="name";
                }else{
                    name = Name.getText().toString().trim();
                }

                if(Rollno.getText().toString().isEmpty()){
                    roll="roll";
                    rollno=0;
                }else{
                    roll = Rollno.getText().toString().trim();
                    rollno=Integer.parseInt(roll);
                }

                if(Contact.getText().toString().isEmpty()){
                    contact="contact";
                }else{
                    contact=Contact.getText().toString().trim();
                }

                if(result==true)
                {
                    pDialog = new ProgressDialog(getContext());
                    Log.d(EditProfileActivity.class.getSimpleName(), "progressdialog");
                    pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    pDialog.setMessage("Saving Info...");
                    pDialog.show();

                    JSONObject postparams = new JSONObject();
                    try {
                        postparams.put("email",email);
                        postparams.put("name",name);
                        postparams.put("roll",rollno);
                        postparams.put("phone",contact);
                        postparams.put("department",dept);
                        postparams.put("fcmtoken","instituteapptest123");
                        postparams.put("year",year);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JsonObjectRequest jsonObjReq= new JsonObjectRequest(Request.Method.POST, url
                            ,postparams, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            SharedPreferences myprefs = PreferenceManager
                                    .getDefaultSharedPreferences(getContext());
                            SharedPreferences.Editor editor = myprefs.edit();
                            editor.putString("roll", roll);
                            editor.putString("email", email);
                            editor.putString("department", dept);
                            editor.putString("name", name);
                            editor.putString("year", year);
                            editor.putString("phone", contact);
                            editor.putString("fcmtoken", "instituteapptest123");
                            editor.commit();

                            pDialog.dismiss();
                            Toast.makeText(getContext(), "Saved Successfully" , Toast.LENGTH_SHORT).show();

                            onStart();

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            pDialog.dismiss();
                            Toast.makeText(getContext(),"something  went wrong",Toast.LENGTH_SHORT).show();
                            error.printStackTrace();
                            VolleyLog.d("TAG", "Error: " + error.getMessage());
                            pDialog.hide();
                        }
                    });
                    AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);


                }
                else if(result==false && check==1)
                {
                    Toast.makeText(getContext(),"Agree with the policy of App",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getContext(),"You haven`t edited anything",Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        checkBox.setVisibility(View.GONE);
        check=0;
        imageView.setImageResource(R.drawable.student);
       /* Intent intent=getIntent();
        email = intent.getStringExtra("email");
        img_url=intent.getStringExtra("imageUri");
        if(img_url.equals("no value"))
        {
            imageView.setImageResource(R.drawable.student);
        }
        else
        {
            Glide.with(this).load(img_url).into(imageView);
        }
        Log.d("aaaaaaaaaaaaaaaaaa","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaammmmmmmmmmmmmmmmmmmmmmmm"+img_url);

        String[] arrOfStr = email.split("@", -2);
        String Str=arrOfStr[0];
        int l=Str.length();
        String y=Str.substring(l-2,l);
        year="20"+y;
        dept=Str.substring(l-5,l-2);
        Department.setText(dept);
        Email.setText(email);



        Name.setEnabled(false);
        Department.setEnabled(false);
        Rollno.setEnabled(false);
        Contact.setEnabled(false);
        Email.setEnabled(false);*/

        SharedPreferences sharedPreferences= PreferenceManager
                .getDefaultSharedPreferences(getContext());

        String name = sharedPreferences.getString("name", "name");
        String roll = sharedPreferences.getString("roll", "roll");
        String contact = sharedPreferences.getString("phone", "contact");



        if(name.equals("name"))
        {
            Nm.setVisibility(View.GONE);
            Name.setVisibility(View.GONE);
        }
        else
        {
            Name.setText(name);
        }

        if(roll.equals("roll"))
        {
            Rn.setVisibility(View.GONE);
            Rollno.setVisibility(View.GONE);
        }
        else
        {
            Rollno.setText(roll);
        }

        if(contact.equals("contact"))
        {
            Ct.setVisibility(View.GONE);
            Contact.setVisibility(View.GONE);
        }
        else
        {
            Contact.setText(contact);
        }


    }
}
