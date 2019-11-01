package com.example.libpro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;

public class Home extends AppCompatActivity {
    private  ArrayAdapter arrayAdapter;
    private Button rating;
   EditText search;
    ListView listViewHome ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("Home");

        search= (EditText) findViewById(R.id.search);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                (Home.this).arrayAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        listViewHome = (ListView) findViewById(R.id.listviewhome);

        final ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("4");
        arrayList.add("5");
        arrayList.add("6");
        arrayList.add("7");
        arrayList.add("8");
        arrayList.add("9");
        arrayList.add("10");

         //arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,R.layout.my_item2,arrayList);

        listViewHome.setAdapter(arrayAdapter);






       listViewHome.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int Item_num = parent.getPositionForView(view);
                if (position==Item_num)
                {
                    Intent i = new Intent(view.getContext(), display_books.class);
                    startActivity(i);

                }


            }
        });


    }
}
