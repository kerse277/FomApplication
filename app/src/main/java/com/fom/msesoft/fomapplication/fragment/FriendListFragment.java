package com.fom.msesoft.fomapplication.fragment;


import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fom.msesoft.fomapplication.R;
import com.fom.msesoft.fomapplication.activity.MainActivity;
import com.fom.msesoft.fomapplication.adapter.RecyclerViewAdapter;
import com.fom.msesoft.fomapplication.model.Person;
import com.fom.msesoft.fomapplication.repository.PersonRepository;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.ArrayList;
import java.util.List;

@EFragment(R.layout.friend_fragment)
public class FriendListFragment extends Fragment {
    @RestService
    PersonRepository personRepository;

    @ViewById(R.id.recyclerView)
    RecyclerView recyclerView;

    @AfterViews
    void excute(){
        listAll();
    }

    ProgressDialog progressDialog;

    private GridLayoutManager lLayout;

    @Background
    void listAll(){
        preExecute();
        List<String> itemsData=new ArrayList<>();

        Person[] persons = personRepository.findByFirstDegreeFriend(((MainActivity)getActivity()).getPerson().getUniqueId());

        //itemsData.add(new ItemData(R.drawable.icon_instagram,R.drawable.icon_instagram,R.drawable.icon_instagram));

        for(int i = 0 ;i<persons.length;i++){
            itemsData.add(persons[i].getPhoto());
        }
     /*   if((persons.length%3)==1)
            itemsData.add(new ItemData(persons[persons.length-2].getPhoto(),"",""));

        if((persons.length%3)==2)
            itemsData.add(new ItemData(persons[persons.length-2].getPhoto(),persons[persons.length-1].getPhoto(),""));
*/
        postExecute(itemsData);

    }

    @UiThread
    void preExecute(){
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Lütfen Bekleyiniz...");
        progressDialog.setIndeterminate(false);
        progressDialog.show();

    }

    @UiThread
    void postExecute(List<String> itemsData){

        lLayout = new GridLayoutManager(getActivity(),3);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(lLayout);

        RecyclerViewAdapter mAdapter = new RecyclerViewAdapter(getActivity(),itemsData);

        recyclerView.setAdapter(mAdapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        progressDialog.hide();

    }

}