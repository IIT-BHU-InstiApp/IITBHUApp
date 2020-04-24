package com.example.anant.iitbhuvaranasi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class InterestedNamesAdapter extends ArrayAdapter<String>
{
    private final Context mContext;
    private final ArrayList<String> mNames;

    public InterestedNamesAdapter(Context context, ArrayList<String> namesOfInterested)
    {
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

        /*
        ImageView profilePic = listItemView.findViewById(R.id.initials_of_person);
        Bitmap bitmap = Bitmap.createBitmap(42, 42, Bitmap.Config.ARGB_8888);

        Paint paint = new Paint();
        (new Canvas(bitmap)).drawText(initials,0,0,paint);
        profilePic.setImageBitmap(bitmap);
         */

        return listItemView;
    }


}
