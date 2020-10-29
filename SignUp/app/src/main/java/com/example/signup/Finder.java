package com.example.signup;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Finder extends AppCompatActivity {
    static Spinner s1,s2;
    Button b1;
static ArrayList<Doctors> post,post1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finder);
        s1=(Spinner)findViewById(R.id.spinner);
        s2=(Spinner)findViewById(R.id.spinner2);
        b1=(Button)findViewById(R.id.button2);
        FirebaseApp.initializeApp(this);
        post=new ArrayList<Doctors>();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /*final DatabaseReference myRef=FirebaseDatabase.getInstance().getReference("doctors");
                Query query1=myRef.orderByChild("city").equalTo(s1.getSelectedItem().toString());

                query1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Log.e("Count ", "" + dataSnapshot.getChildrenCount());
                        int k=0;
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            post.add(postSnapshot.getValue(Doctors.class));
                            if(post.get(k).type.equals(s2.getSelectedItem().toString()))
                            {
                                post1.add()
                            }

                        }
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });*/
              /*  for(int i=0;i<100;i++)
                    System.out.println(post1.size());

                for(Doctors i:post1)
                    System.out.println(i.name);*/


                Intent i=new Intent(Finder.this,Show.class);
                startActivity(i);
                finish();


            }
        });


    }
    private ArrayList<Doctors> query1(ArrayList<Doctors> docs){
        post1=new ArrayList<Doctors>();

        for(Doctors i:post){
            if(i.type.equals(s2.getSelectedItem().toString()))
                post1.add(i);

            System.out.println(i.name);

        }
        return post1;
    }
}
