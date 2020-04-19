package com.example.anant.iitbhuvaranasi;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class PORadapter extends FragmentPagerAdapter {

        public PORadapter(FragmentManager fm){
            super(fm);

        }

        @Override
        public Fragment getItem(int position) {
            if(position == 0){
                return new PORparliament();
            }
            else if(position == 1){
                return new PORcouncils();
            }
            else {
                return new PORfestivals();
            }

        }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "Parliament";
                case 1:
                    return "Councils";
                case 2:
                    return "Festivals";


                default:
                    return "error";
            }

    }

    @Override
        public int getCount() {
            return 3;
        }
    }

