package com.example.anant.iitbhuvaranasi;

import android.net.Uri;
import android.os.Bundle;

import androidx.browser.customtabs.CustomTabsIntent;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class ImportantLinksFragment extends Fragment {

//    String formattedDate;
    LinearLayout wifi,academic_layout;
    TextView wifi_guide,academic;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_important_links, container, false);

//        Date c = Calendar.getInstance().getTime();
//        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
//        formattedDate = df.format(c);
        wifi = view.findViewById(R.id.wifi_clickable);
        academic_layout = view.findViewById(R.id.academic_layout);
        wifi.setVisibility(View.GONE);
        academic = view.findViewById(R.id.academic_clickable);
        wifi_guide = view.findViewById(R.id.wifi_lan);
        expandCollapseText(academic,academic_layout);
        expandCollapseText(wifi_guide,wifi);


        view.findViewById(R.id.iitbhu_link).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://iitbhu.ac.in/";
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(Objects.requireNonNull(getContext()), Uri.parse(url));

            }
        });

        view.findViewById(R.id.academic_links).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://iitbhu.ac.in/deans/doaa/";
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(Objects.requireNonNull(getContext()), Uri.parse(url));

            }
        });

        view.findViewById(R.id.grade_report).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://academics.iitbhu.ac.in/gmail_auth/index.php";
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
               customTabsIntent.launchUrl(Objects.requireNonNull(getContext()), Uri.parse(url));


            }
        });

        view.findViewById(R.id.academic_calender_odd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://iitbhu.ac.in/contents/institute/academics/academic_info/doc/calendar_19-20_odd.pdf";
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(Objects.requireNonNull(getContext()), Uri.parse(url));

            }
        });
       view.findViewById(R.id.academic_calender_even).setOnClickListener(new View.OnClickListener() {
           @Override

            public void onClick(View v) {
                String url = "https://iitbhu.ac.in/contents/institute/academics/academic_info/doc/calendar_19-20_even.pdf";
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(Objects.requireNonNull(getContext()), Uri.parse(url));

            }
        });
        view.findViewById(R.id.academic_holidays_2019).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://iitbhu.ac.in/contents/institute/others/misc/holidays_2019.pdf";
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(Objects.requireNonNull(getContext()), Uri.parse(url));

            }
        });view.findViewById(R.id.academic_holidays_2020).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://iitbhu.ac.in/contents/institute/others/misc/holidays_2020.pdf";
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(Objects.requireNonNull(getContext()), Uri.parse(url));

            }
        });


        view.findViewById(R.id.wifi_lan_guide_link).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.iitbhu.ac.in/cf/cis/network/helpdesk/";
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(Objects.requireNonNull(getContext()), Uri.parse(url));

            }
        });

        view.findViewById(R.id.wifi_android).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://iitbhu.ac.in/contents/institute/central_facilities/cis/doc/wifi_setting_mobile.pdf";
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(Objects.requireNonNull(getContext()), Uri.parse(url));

            }
        });
        view.findViewById(R.id.wifi_win7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://iitbhu.ac.in/contents/institute/central_facilities/cis/doc/wifi_connection_settings.pdf";
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(Objects.requireNonNull(getContext()), Uri.parse(url));

            }
        });
        view.findViewById(R.id.wifi_win10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://iitbhu.ac.in/contents/institute/central_facilities/cis/doc/wifi_ssid_settings_windows10.pdf";
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(Objects.requireNonNull(getContext()), Uri.parse(url));

            }
        });
        view.findViewById(R.id.lan_win).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://iitbhu.ac.in/contents/institute/central_facilities/cis/doc/wired_connection_windows_settings.pdf";
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(Objects.requireNonNull(getContext()), Uri.parse(url));

            }
        });
        view.findViewById(R.id.lan_ubuntu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://iitbhu.ac.in/contents/institute/central_facilities/cis/doc/wired_connection_ubuntu_settings.pdf";
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(Objects.requireNonNull(getContext()), Uri.parse(url));

            }
        });
        view.findViewById(R.id.lan_mac).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://iitbhu.ac.in/contents/institute/central_facilities/cis/doc/wired_connection_mac_settings.pdf";
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(Objects.requireNonNull(getContext()), Uri.parse(url));

            }
        });

        view.findViewById(R.id.rfid_link).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://www.icardiitbhu.com/";
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(Objects.requireNonNull(getContext()), Uri.parse(url));

            }
        });




        return view;
    }
    void expandCollapseText(final TextView title,final LinearLayout text){
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text.isShown()){
                    text.setVisibility(View.GONE);
                }
                else{
                    text.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
