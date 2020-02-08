package com.example.anant.iitbhuvaranasi;



import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SimpleViewAdapter extends PagerAdapter {
    private Context mContext;
//    private int[] mImages;
ArrayList<SingleHorizontaldata> mData = new ArrayList<>();
LayoutInflater layoutInflater;

    public SimpleViewAdapter(Context context, ArrayList<SingleHorizontaldata> data) {
        this.mContext = context;
        this.mData = data;
    }

    @Override
    public Object instantiateItem(ViewGroup container,final int position) {



        View view = layoutInflater.from(mContext).inflate(R.layout.viewpager_card,container,false);
        ImageView imageView = (ImageView) view.findViewById(R.id.image2);

//        imageView.setImageResource(mImages[position]);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(mContext, "This is a ViewPager Item",Toast.LENGTH_SHORT).show();
//
//
//            }
//        });
//        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        container.addView(imageView);
        Picasso.get()
                .load(mData.get(position).getImage())
                .placeholder(R.drawable.ic_eye_view)
                .error(R.drawable.amc_workshop)
                .fit()
                .centerCrop()
                .into(imageView);
        container.addView(view);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, Clubs_group.class);
                intent.putExtra("position",position);
                mContext.startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((View)object);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}

