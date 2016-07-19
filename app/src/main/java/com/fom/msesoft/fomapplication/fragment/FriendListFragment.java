package com.fom.msesoft.fomapplication.fragment;


import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fom.msesoft.fomapplication.R;
import com.fom.msesoft.fomapplication.activity.MainActivity;
import com.fom.msesoft.fomapplication.activity.MainActivity_;
import com.fom.msesoft.fomapplication.adapter.CircleTransform;
import com.fom.msesoft.fomapplication.adapter.OnLoadMoreListener;
import com.fom.msesoft.fomapplication.adapter.RecyclerViewAdapter;
import com.fom.msesoft.fomapplication.model.Person;
import com.fom.msesoft.fomapplication.repository.PersonRepository;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

@EFragment(R.layout.friend_fragment)
public class FriendListFragment extends Fragment {

    RecyclerViewAdapter mAdapter;

    @ViewById(R.id.progress_bar)
    ProgressBar progressBar;

    @RestService
    PersonRepository personRepository;

    @ViewById(R.id.recyclerView)
    RecyclerView recyclerView;

    @TargetApi(Build.VERSION_CODES.M)
    @AfterViews
    void execute(){
        listAll();
        progressBar.setVisibility(View.VISIBLE);


    }

    private GridLayoutManager lLayout;


    @Background
    void listAll(){
        preExecute();
        List<Person> itemsData=new ArrayList<>();

        Person[] persons = personRepository.findByFirstDegreeFriend(((MainActivity)getActivity()).getPerson().getUniqueId());

        for(int i = 0 ;i<persons.length;i++){
            itemsData.add(persons[i]);
        }

        postExecute(itemsData);

    }

    @UiThread
    void preExecute(){
        progressBar.setVisibility(View.VISIBLE);
    }

    @UiThread
    void postExecute(final List<Person> itemsData){

        progressBar.setVisibility(View.GONE);

        lLayout = new GridLayoutManager(getActivity(),3);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(lLayout);

        mAdapter = new RecyclerViewAdapter(getActivity(),itemsData,recyclerView);

        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                Log.e("haint", "Load More");
                itemsData.add(null);
                mAdapter.notifyItemInserted(itemsData.size() - 1);

                //Load more data for reyclerview
                listAll();
            }
        });

        recyclerView.setAdapter(mAdapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());


    }

}