package com.example.libpro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class category extends AppCompatActivity {

    ListView listViewCat ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        setTitle("Category");


        listViewCat = (ListView) findViewById(R.id.listviewcat);

        final ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("Design");
        arrayList.add("Database");
        arrayList.add("Network");
        arrayList.add("Security");
        arrayList.add("Programing");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,R.layout.my_item,arrayList);
        listViewCat.setAdapter(arrayAdapter);

        listViewCat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Bundle bundle = new Bundle();
                bundle.putString("Catname",arrayList.get(position));

                Intent intent = new Intent(category.this, CatPage.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


    }
}
