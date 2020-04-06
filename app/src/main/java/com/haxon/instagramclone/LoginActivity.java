package com.haxon.instagramclone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText edtLoginEmail, edtLoginPassword;
    private Button btnSignUpActivity, btnLogInActivity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("LogIn");

        edtLoginEmail = findViewById(R.id.edtLoginEmail);
        edtLoginPassword = findViewById(R.id.edtLoginPassword);
        btnLogInActivity = findViewById(R.id.btnLoginActivity);
        btnSignUpActivity = findViewById(R.id.btnSignUpLoginActivity);

        edtLoginPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN ){
                    onClick(btnLogInActivity);
                }
                return false;
            }
        });

        btnLogInActivity.setOnClickListener(LoginActivity.this);
        btnSignUpActivity.setOnClickListener(LoginActivity.this);

        if (ParseUser.getCurrentUser() != null){
            //ParseUser.logOut();
            transitionToSocialMediaActivity();
        }
    }
    @Override
    public void onClick(View v) {
     switch (v.getId()){
         case R.id.btnLoginActivity:
             if (edtLoginEmail.getText().toString().equals("") || edtLoginPassword.getText().toString().equals("")){
                 FancyToast.makeText(LoginActivity.this, " Email, Password is Required!",
                         FancyToast.LENGTH_LONG, FancyToast.INFO, true).show();
             }else {
                 final ProgressDialog progressDialog = new ProgressDialog(this);
                 progressDialog.setMessage("Login In User");
                 progressDialog.show();
                 ParseUser.logInInBackground(edtLoginEmail.getText().toString(), edtLoginPassword.getText().toString(), new LogInCallback() {
                     @Override
                     public void done(ParseUser user, ParseException e) {
                         if (user != null && e == null) {
                             FancyToast.makeText(LoginActivity.this, user.getUsername() + " is Logged In",
                                     FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                             transitionToSocialMediaActivity();
                         } else {
                             FancyToast.makeText(LoginActivity.this, e.getMessage() + " \n Try again!",
                                     FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                         }
                         progressDialog.dismiss();
                     }
                 });
             }
             break;
         case R.id.btnSignUpLoginActivity:
             Intent intent = new Intent(LoginActivity.this, SignUp.class);
             startActivity(intent);
             break;
     }
    }
    public void loginLayoutTapped(View view){
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void transitionToSocialMediaActivity(){
        Intent intent = new Intent(LoginActivity.this, SocialMediaActivity.class);
        startActivity(intent);
    }
}
