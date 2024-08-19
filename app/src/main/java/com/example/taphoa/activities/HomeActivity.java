package com.example.taphoa.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.taphoa.Home1Activity;
import com.example.taphoa.R;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity {
    ProgressBar progressBar;
    FirebaseAuth auth;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
       /* if (auth.getCurrentUser()!=null){
            startActivity(new Intent(HomeActivity.this, Home1Activity.class));
            Toast.makeText(this,"Bạn đã đăng nhập trước đó rồi!", Toast.LENGTH_SHORT).show();
            finish();
        }*/
    }



    public void login(View view){
        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
    }
    public void reg(View view){
        startActivity(new Intent(HomeActivity.this, RegistrationActivity.class));
    }
}
