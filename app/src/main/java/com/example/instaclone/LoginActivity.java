package com.example.instaclone;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    EditText usernameEditText;
    EditText passwordEditText;
    Button loginButton;
    ProgressBar loadingProgressBar;
    Button btnSignup;

    private static final String TAG = "LoginActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (ParseUser.getCurrentUser() != null) {
            Log.i(TAG, "Found session: " + ParseUser.getCurrentUser());
            goMainActivity();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);
        loadingProgressBar = findViewById(R.id.loading);
        btnSignup = findViewById(R.id.btnSignup);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                login(username, password);
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goSignupActivity();
            }
        });
    }

    private void login(String username, String password) {
        Log.i(TAG, "login method called");
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null) {
                    Log.d(TAG, "Issue with login");
                    Toast.makeText(getApplicationContext(), "Username and/or password invalid", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                    return;
                }
                Log.i(TAG, "Going to main activity");
                Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                goMainActivity();
            }
        });
    }

    private void goMainActivity() {
        Log.i(TAG, "Navigating to Main Activity");
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }


    private void goSignupActivity() {
        Log.d(TAG, "Navigating to Signup Activity");
        Intent i = new Intent(this, SignupActivity.class);
        startActivity(i);
    }
}
