package com.example.libpro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Upload extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);


     Spinner sp = (Spinner) findViewById(R.id.spinner);
ArrayAdapter<String> categories = new ArrayAdapter<String>(this ,
        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.categories));
        categories.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item );
sp.setAdapter(categories);

    }
}
