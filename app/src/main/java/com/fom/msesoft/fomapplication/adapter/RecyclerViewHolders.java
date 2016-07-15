package com.fom.msesoft.fomapplication.adapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fom.msesoft.fomapplication.R;

public class RecyclerViewHolders extends RecyclerView.ViewHolder {

    public TextView countryName;
    public ImageView countryPhoto;

    public RecyclerViewHolders(View itemView) {
        super(itemView);
        countryPhoto = (ImageView)itemView.findViewById(R.id.country_photo);
    }


}