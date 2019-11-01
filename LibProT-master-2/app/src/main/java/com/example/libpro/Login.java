package com.example.libpro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    private UserInfo userinfo;
    private DatabaseReference db;
    private EditText txt_email, txt_password;
    private String email, password;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z]+\\.+[a-zA-Z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Sign In");
        userinfo = new UserInfo(this);
        // Check if a user has logged in to the app before and has not logged off
        if (!userinfo.getUserEmail().isEmpty()) {
            // go to main screen with saved user data
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }else {
            txt_email = findViewById(R.id.EDemail);
            txt_password = findViewById(R.id.EDpassword);

            Button login_btn=findViewById(R.id.LogIn_btn);
            Button signup_btn=findViewById(R.id.Signup_btn);

            login_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SignIn();
                }
            });

            signup_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Login.this, Register.class);
                    startActivity(intent);
                    finish();
                }
            });
            db = FirebaseDatabase.getInstance().getReference("projLib");
        }



    }

    public void SignIn(){

        email = txt_email.getText().toString().trim();
        password = txt_password.getText().toString().trim().toLowerCase();

        if (email.isEmpty() || !email.matches(emailPattern)) {
            txt_email.setError("please enter your email");

            return;
        } else if (password.isEmpty()) {
            txt_password.setError("please enter your password");

            return;
        }

        loginUser();
    }

    private void loginUser(){
        db.child("USER")
                .child(setEmailAsKey(email))
                .child("Data")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()){
                             String db_username = dataSnapshot.child("username").getValue().toString();
                            String db_email = dataSnapshot.child("email").getValue().toString();
                            String db_password = dataSnapshot.child("password").getValue().toString();

                            if (email.equals(db_email)) {
                                // Make sure that the password entered is equal to the password that was returned
                                if (password.equals(db_password)) {
                                    userinfo.saveUserInfo(db_username,db_email,db_password);
                                    // close this screen and go to main screen
                                    Intent intent = new Intent(Login.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // set error at input password
                                    txt_password.setError("password incorrect ");

                                }
                            }
                        }else {
                            Toast.makeText(Login.this, "this email is not registered", Toast.LENGTH_SHORT).show();

                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {}

                });
    }


    public static String setEmailAsKey(String email) {
        return email.replace(".", "-");
    }
}

