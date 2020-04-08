package com.example.anant.iitbhuvaranasi;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.ArrayList;
import java.util.Objects;

import static com.example.anant.iitbhuvaranasi.AboutIndividual.TYPE_LINK;

public class AboutFragment extends Fragment {

    public AboutFragment() {
        // Required empty public constructor
    }

    private Toolbar toolbar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false);



    }

    @Override
    public void onStart() {
        super.onStart();

        toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("About the Developers");

        final Context context = getContext();

        AboutAdapter aboutAdapter = new AboutAdapter(new ArrayList<AboutCategory>() {{
            add(new AboutCategory("Core Developers", new ArrayList<AboutIndividual>() {{
                add(new AboutIndividual("abhinavsingh", "Abhinav Singh", "varun.jpg","Computer Science"));
                add(new AboutIndividual("bhoomikbhamawat.eee18@itbhu.ac.in", "Bhoomik Bhamawat", "sajal.jpg","Electrical"));
            }}, context));
            add(new AboutCategory("Developers", new ArrayList<AboutIndividual>() {{
                add(new AboutIndividual(null, "Yogesh", "mrunmayi.jpg","Electrical"));
                add(new AboutIndividual(null, "Sachit", "owais.jpg","Electrical"));
                add(new AboutIndividual(null, "Monu Kumar", "hrushikesh.jpg","Electrical"));
                add(new AboutIndividual(null, "Tanmay Mishra", "yashkhem.jpg","Electrical"));
                add(new AboutIndividual(null, "Martin", "bavish.jpg","Electrical"));
                add(new AboutIndividual("suryansh.min19@itbhu.ac.in", "Suryansh", "mayu.jpg","Mining"));
                //add(new AboutIndividual("maitreya", "Maitreya Verma", "maitreya.jpg"));
                 }}, context));
           /* add(new AboutCategory("Design", new ArrayList<AboutIndividual>() {{
                add(new AboutIndividual("150040007", "Soham Khadtare", "soham.jpg"));
            }}, context));*/

            add(new AboutCategory("Ideation", new ArrayList<AboutIndividual>() {{
                add(new AboutIndividual(null, "Anant Gowadiya", "nihal.jpg","SMST"));
                add(new AboutIndividual(null, "Abhinav Singh", "ydidwania.jpg","Electrical"));
                add(new AboutIndividual(null, "Bhoomik Bhamawat", "ydidwania.jpg","Electrical"));
              //  add(new AboutIndividual(null, "Arihant Shuklecha", "cheeku.jpg"));
                //add(new AboutIndividual(null, "Arihant Shuklecha", "sarthak.jpg"));
            }}, context));
            add(new AboutCategory("Managing Team", new ArrayList<AboutIndividual>() {{
            //    add(new AboutIndividual(null, "Anant Gowadiya", "nihal.jpg"));
                add(new AboutIndividual(null, "Bharat Jain", "ydidwania.jpg","Electrical"));
                add(new AboutIndividual(null, "Arihant Shuklecha", "cheeku.jpg","Electrical"));
                add(new AboutIndividual(null, "Sachit", "cheeku.jpg","Electrical"));
             //   add(new AboutIndividual(null, "Bhoomik", "cheeku.jpg"));
                //add(new AboutIndividual(null, "Arihant Shuklecha", "sarthak.jpg"));
            }}, context));
            /*add(new AboutCategory("Alumni", new ArrayList<AboutIndividual>() {{
                add(new AboutIndividual("abhijit.tomar", "Anant Gowadiya", "tomar.jpg"));
                add(new AboutIndividual(null, "Abhinav Singh", "bijoy.jpg"));
                 }}, context));*/

        }});

        RecyclerView aboutRecyclerView = getActivity().findViewById(R.id.about_recycler_view);
        aboutRecyclerView.setAdapter(aboutAdapter);

        FlexboxLayoutManager manager = new FlexboxLayoutManager(context, FlexDirection.ROW);
        manager.setJustifyContent(JustifyContent.CENTER);
        aboutRecyclerView.setLayoutManager(manager);
    }

    @Override
    public void onResume() {
        toolbar.setTitle("About the Developers");
        super.onResume();
    }

    @Override
    public void onStop() {
        toolbar.setTitle("IIT(BHU) Varanasi");
        super.onStop();
    }

    /*   @Override
    public void onStop() {
        super.onStop();

        toolbar.setTitle(R.string.app_name);



    }*/
}
