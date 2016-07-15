package com.fom.msesoft.fomapplication.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.fom.msesoft.fomapplication.R;
import com.squareup.picasso.Picasso;


public class Main2Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ImageView profilePicture = (ImageView)findViewById(R.id.imageView);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Picasso.with(this)
                .load("http://fomdb.cloudapp.net/aaa.png")
                .resize(400,400)
                .into(profilePicture);
   //     profilePicture.setImageResource(R.drawable.aaa);
    }
}
