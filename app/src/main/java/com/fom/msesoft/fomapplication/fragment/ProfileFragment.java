package com.fom.msesoft.fomapplication.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.fom.msesoft.fomapplication.activity.MainActivity;
import com.fom.msesoft.fomapplication.adapter.CircleTransform;
import com.fom.msesoft.fomapplication.R;
import com.fom.msesoft.fomapplication.adapter.PagerAdapter;
import com.fom.msesoft.fomapplication.adapter.ProfilePagerAdapter;
import com.fom.msesoft.fomapplication.model.Person;
import com.fom.msesoft.fomapplication.repository.PersonRepository;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.Arrays;
import java.util.List;


@EFragment(R.layout.profile_fragment)
public class ProfileFragment extends Fragment {

    @RestService
    PersonRepository personRepository;

    @ViewById(R.id.profilePicture)
    ImageView profilePicture;

    @ViewById(R.id.textView)
    TextView textView;

    @ViewById(R.id.friendNumber)
    TextView friendNumber;

    @ViewById(R.id.profileName)
    TextView profileName;

    @ViewById(R.id.profilePager)
    ViewPager viewPager;

    @ViewById(R.id.profileTab)
    TabLayout tabLayout;


    @AfterViews
    void profileView () {
        profileConnection();

        tabLayout.addTab(tabLayout.newTab().setText("A"));
        tabLayout.addTab(tabLayout.newTab().setText("B"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ProfilePagerAdapter adapter = new ProfilePagerAdapter
                (((MainActivity)getActivity()).getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Background
    void profileConnection () {
         Person person = ((MainActivity)getActivity()).getPerson();
          List<Person> firstDegreeFriend = Arrays.asList(personRepository.findByFirstDegreeFriend(((MainActivity)getActivity()).getPerson().getUniqueId()));
          profileNumber(firstDegreeFriend,person);
    }

    @UiThread
    void profileNumber(List<Person> firstDegreeFriend, Person person) {
        friendNumber.setText(firstDegreeFriend.size() + "");
        Picasso.with(getActivity())
                .load(person.getPhoto().toString())
                .resize(250,250)
                .transform(new CircleTransform())
                .into(profilePicture);
        profileName.setText(person.getFirstName()+" "+person.getLastName());
    }
}