package com.fom.msesoft.fomapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fom.msesoft.fomapplication.R;
import com.fom.msesoft.fomapplication.model.Person;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolders> {


    private List<Person> itemList;
    private Context context;

    public RecyclerViewAdapter(Context context, List<Person> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listrow, null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        holder.setPerson(itemList.get(position));
        Picasso.with(context)
                .load(itemList.get(position).getPhoto())
                .into(holder.personPhoto);


    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    };

}

