package com.fom.msesoft.fomapplication.fragment;

import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.TextView;

import com.fom.msesoft.fomapplication.activity.MainActivity;
import com.fom.msesoft.fomapplication.adapter.CircleTransform;
import com.fom.msesoft.fomapplication.R;
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

    @AfterViews
    void profileView () {
        profileConnection();
    }

    @Background
    void profileConnection () {
         Person person = personRepository.findByPhoto();
          List<Person> firstDegreeFriend = Arrays.asList(personRepository.findByFirstDegreeFriend(((MainActivity)getActivity()).getPerson().getUniqueId()));
          profileNumber(firstDegreeFriend,person);
    }

    @UiThread
    void profileNumber(List<Person> firstDegreeFriend, Person person) {
        friendNumber.setText(firstDegreeFriend.size() + "");
        Picasso.with(getActivity())
                .load(person.getPhoto().toString())
                .resize(150,150)
                .transform(new CircleTransform())
                .into(profilePicture);
        profileName.setText(person.getFirstName()+" "+person.getLastName());
    }
}