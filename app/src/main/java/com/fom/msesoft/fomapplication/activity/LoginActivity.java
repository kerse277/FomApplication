package com.fom.msesoft.fomapplication.activity;
import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.UiThread;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.fom.msesoft.fomapplication.R;
import com.fom.msesoft.fomapplication.model.Person;
import com.fom.msesoft.fomapplication.repository.PersonRepository;

import org.androidannotations.annotations.AfterTextChange;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;

import java.io.Serializable;


@EActivity(R.layout.activity_login)
@Fullscreen
public class LoginActivity extends AppCompatActivity {
    public Person person;

    @RestService
    PersonRepository personRepository;


    @ViewById(R.id.input_layout_id)
    TextInputLayout inputLayoutId;

    @ViewById(R.id.input_layout_login_password)
    TextInputLayout inputLayoutLoginPassword;

    @ViewById(R.id.input_id)
    EditText name;

    @ViewById(R.id.input_login_password)
    EditText password;

    @ViewById(R.id.btn_signin)
    Button sign;

    @Click(R.id.btn_signin)
    void btn_signin(){
        sign(name.getText().toString(),password.getText().toString());
    }

    @Background
    public void sign(String email, String password) {
        person = personRepository.signIn(email,password);
        chechSign(person);
    }
    @UiThread
    void chechSign (Person person) {
       if(person!=null) {
           Intent intent = new Intent(this, MainActivity_.class);
           intent.putExtra("person",person);
           startActivity(intent);
           this.finish();
       }
    }


    @AfterTextChange(R.id.input_id)
    void onTextChancedInputName(){
        validateName();
    }


    @AfterTextChange(R.id.input_login_password)
    void onTextChancedInputPassword(){
        validatePassword();
    }

    private void submitForm() {
        if (!validateName()) {
            return;
        }


        if (!validatePassword()) {
            return;
        }


    }

    private boolean validateName() {
        if (name.getText().toString().trim().isEmpty()) {
            inputLayoutId.setError(getString(R.string.err_msg_name));
            requestFocus(name);
            return false;
        } else {
            inputLayoutId.setErrorEnabled(false);
        }

        return true;
    }


    private boolean validatePassword() {
        if (password.getText().toString().trim().isEmpty()) {
            inputLayoutLoginPassword.setError(getString(R.string.err_msg_password));
            requestFocus(password);
            return false;
        } else {
            inputLayoutLoginPassword.setErrorEnabled(false);
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }


}
