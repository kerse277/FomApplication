package com.fom.msesoft.fomapplication.adapter;
import android.app.Dialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.fom.msesoft.fomapplication.R;
import com.fom.msesoft.fomapplication.activity.MainActivity;
import com.fom.msesoft.fomapplication.fragment.FriendListFragment;
import com.fom.msesoft.fomapplication.model.Person;
import com.squareup.picasso.Picasso;

import lombok.Setter;

public class RecyclerViewHolders extends RecyclerView.ViewHolder {

    @Setter
    private Person person;



    public ImageView personPhoto;

    public RecyclerViewHolders(final View itemView) {
        super(itemView);
        personPhoto = (ImageView)itemView.findViewById(R.id.personPhoto);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(itemView.getContext());
                dialog.setContentView(R.layout.person_info_dialog);
                dialog.setTitle("Person Info");

                // set the custom dialog components - text, image and button
                TextView text = (TextView) dialog.findViewById(R.id.dialogText);
                text.setText(person.getEmail());
                ImageView image = (ImageView) dialog.findViewById(R.id.dialogImg);
                Picasso.with(itemView.getContext())
                        .load(person.getPhoto())
                        .resize(250,250)
                        .transform(new CircleTransform())
                        .into(image);



                dialog.show();
            }
        });
    }



}