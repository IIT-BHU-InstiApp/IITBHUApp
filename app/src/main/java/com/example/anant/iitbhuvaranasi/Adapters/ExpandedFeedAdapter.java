//package com.example.anant.iitbhuvaranasi.Adapters;
//
//import androidx.annotation.NonNull;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//import androidx.fragment.app.FragmentPagerAdapter;
//
//import com.example.anant.iitbhuvaranasi.Fragments.PastEventsFragment;
//import com.example.anant.iitbhuvaranasi.Fragments.UpcomingEventsFragment;
//
//public class ExpandedFeedAdapter extends FragmentPagerAdapter {
//    public ExpandedFeedAdapter(@NonNull FragmentManager fm) {
//        super(fm);
//    }
//
//    @NonNull
//    @Override
//    public Fragment getItem(int position) {
//        Fragment fragment = new UpcomingEventsFragment();
//        switch (position){
//            case 1:
//                fragment = new UpcomingEventsFragment();
//            case 2:
//                fragment = new PastEventsFragment();
//        }
//        return fragment;
//    }
//
//    @Override
//    public int getCount() {
//        return 2;
//    }
//}
