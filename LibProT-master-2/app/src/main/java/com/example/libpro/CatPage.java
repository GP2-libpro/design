package com.example.libpro;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CatPage extends AppCompatActivity {
    String catname = "" ;
    private DatabaseReference db ;
    ListView listViewbooks ;
    ArrayAdapter Bookadapter;
    Books BookObj;
     ArrayList <String> BooksArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_page);
        catname = getIntent().getStringExtra("Catname");
        setTitle(catname);
        listViewbooks =  (ListView) findViewById(R.id.catbooks);
        db = FirebaseDatabase.getInstance().getReference("projLib/Books/"+catname);
        BooksArray = new ArrayList<>();
        BookObj = new Books();
        //Bookadapter = new ArrayAdapter (this,android.R.layout.simple_list_item_1, BooksArray);
        final ArrayAdapter Bookadapter = new ArrayAdapter(this,R.layout.my_item3,BooksArray);

        db.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren()){

                    BookObj = ds.getValue(Books.class);
                    BooksArray.add(BookObj.getTitle());


                }
                listViewbooks.setAdapter(Bookadapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





    }




}
