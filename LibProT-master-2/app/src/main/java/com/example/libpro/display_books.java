package com.example.libpro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class display_books extends AppCompatActivity {

private Button rating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_books);

        rating =(Button) findViewById(R.id.rate);
        rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent display_books = new Intent(display_books.this, rate_books.class);
                startActivity(display_books);
            }
        });
    }
}
