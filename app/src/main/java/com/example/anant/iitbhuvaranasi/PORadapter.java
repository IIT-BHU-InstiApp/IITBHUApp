package com.example.anant.iitbhuvaranasi;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

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
            else if(position == 2){
                return new PORfestivals();
            }
            else{return new PORhec();}
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
                case 3:
                    return "HEC";

                default:
                    return "error";
            }

    }

    @Override
        public int getCount() {
            return 4;
        }
    }

