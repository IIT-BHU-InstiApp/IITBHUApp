package com.example.anant.iitbhuvaranasi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class InterestedNamesAdapter extends ArrayAdapter<String>
{
    private final Context mContext;
    private final ArrayList<String> mNames;

    public InterestedNamesAdapter(Context context, ArrayList<String> namesOfInterested) {
        super(context,0,namesOfInterested);
        mContext=context;
        mNames=namesOfInterested;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.interested_dialog_item, parent, false);
        }

        String initials="",name =mNames.get(position);

        TextView nameTextView =  listItemView.findViewById(R.id.tv_user_name);
        nameTextView.setText(name);

        if (name.trim().contains(" "))
            initials=name.charAt(0)+""+name.charAt(name.indexOf(' ')+1);
        else
            initials=name.charAt(0)+"";

        Button avatar = listItemView.findViewById(R.id.avatar);
        avatar.setText(initials);
        Pair<Integer,Integer> color= InterestedNamesAdapter.RandomColors.getColor();
        //Second color is for background
        //First color is for text
        avatar.setBackgroundTintList(ContextCompat.getColorStateList(this.getContext(),color.second));
        avatar.setTextColor(color.first);
        return listItemView;
    }

    public static class RandomColors{
        private static Stack<Pair<Integer,Integer>> colors = new Stack<>();
        private static Stack<Pair<Integer,Integer>> recycle = new Stack<>();
        static {
            //TODO :: To choose better color combinations
            recycle.add(Pair.create(R.color.Black,R.color.colorblue220));
            recycle.add(Pair.create(R.color.sailor_blue,R.color.mint));
            recycle.add(Pair.create(R.color.bubblegum_pink,R.color.pale_green));
        }

        public static Pair<Integer, Integer> getColor() {
            if (colors.size()==0) {
                while(!recycle.isEmpty())
                    colors.push(recycle.pop());
                Collections.shuffle(colors);
            }
            Pair<Integer,Integer> color= colors.pop();
            recycle.push(color);
            return color;
        }
    }

}
