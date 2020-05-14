package com.example.anant.iitbhuvaranasi.Fragments;

import android.os.Bundle;

import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.anant.iitbhuvaranasi.R;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class LostAndFoundFragment extends Fragment {

    private Toolbar toolbar;

    public static ImageButton sendButton,addImage;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lost_found_fragment, container, false);
        toolbar = (Toolbar) Objects.requireNonNull(getActivity()).findViewById(R.id.toolbar);


        //Creating send button & addImage Button
        int endMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics());
        TypedValue typedValue = new TypedValue();
        getActivity().getTheme().resolveAttribute(android.R.attr.selectableItemBackgroundBorderless, typedValue, true);
        Toolbar.LayoutParams LayoutParam = new Toolbar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LayoutParam.gravity = Gravity.END;
        LayoutParam.setMarginEnd(endMargin);

        sendButton = new ImageButton(getContext());

        sendButton.setLayoutParams(LayoutParam);
        sendButton.setImageDrawable(Objects.requireNonNull(getContext()).getResources().getDrawable(R.drawable.ic_send_black_24dp));
        sendButton.setBackgroundResource(typedValue.resourceId);

        addImage = new ImageButton(getContext());
        addImage.setLayoutParams(LayoutParam);
        addImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_attachment_black_24dp));
        addImage.setBackgroundResource(typedValue.resourceId);


        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.lost_found_viewpager);
        final LostFoundPageAdapter pageAdapter = new LostFoundPageAdapter(getChildFragmentManager());
        viewPager.setAdapter(pageAdapter);



        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                pageAdapter.something(position);
                /*if(getArguments().getString("Intent").equals("Lost")){
                    pageAdapter.something(0);
                }
                else if (getArguments().getString("Intent").equals("Found"))
                {
                    pageAdapter.something(1);
                }
                else {
                    pageAdapter.something(position);
                }*/
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);


        return view;
    }


    @Override
    public void onResume() {
        super.onResume();


        toolbar.setTitle("Lost/Found");
        toolbar.addView(sendButton);
        toolbar.addView(addImage);




    }

    @Override
    public void onStop() {
        super.onStop();

        toolbar.setTitle(R.string.app_name);
        toolbar.removeView(sendButton);
        toolbar.removeView(addImage);


    }
}


class LostFoundPageAdapter extends FragmentPagerAdapter {
    LostFragment lostFragment=new LostFragment();
    FoundFragment foundFragment=new FoundFragment();

    public void something(int a){
        lostFragment.onClick(a);
        foundFragment.onClick(a);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {

        return super.getItemPosition(object);
    }

    public LostFoundPageAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: {
                return lostFragment;
            }
            case 1: {
                return foundFragment;
            }
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Lost";
            case 1:
                return "Found";
            default:
                return null;
        }
    }


}
