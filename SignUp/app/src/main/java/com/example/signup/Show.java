package com.example.signup;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import javax.crypto.NullCipher;

import static com.example.signup.Finder.post;
import static com.example.signup.Finder.post1;

public class Show extends AppCompatActivity {
     ScrollView sv;
     TextView t;
     String s;
     Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        s="";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        sv=(ScrollView)findViewById(R.id.View1);
        /*s1=(Spinner)findViewById(R.id.spinner);
        s2=(Spinner)findViewById(R.id.spinner2);*/
        t=(TextView)findViewById(R.id.textView9);
        t.setTextColor(Color.BLACK);

        final DatabaseReference myRef= FirebaseDatabase.getInstance().getReference("doctors");
        Query query1=myRef.orderByChild("city").equalTo(Finder.s1.getSelectedItem().toString());

        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e("Count ", "" + dataSnapshot.getChildrenCount());
                int k=0;
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    if(postSnapshot.child("type").getValue().toString().equals(Finder.s2.getSelectedItem().toString())) {
                        String name=postSnapshot.child("name").getValue().toString();
                        String hospital=postSnapshot.child("hospital").getValue().toString();
                        String city=postSnapshot.child("city").getValue().toString();
                        String time=postSnapshot.child("time").getValue().toString();
                        String fees=postSnapshot.child("fees").getValue().toString();




                        s+="Name:" + name + "\nHospital:" + hospital + "\nCity:" + city + "\nTime:" + time + "\nFees:" + fees + "\n\n";
                        t.setText(s);
                    }
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
        b1=(Button)findViewById(R.id.button3);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(Show.this,Finder.class);
                startActivity(i);
                finish();
            }
        });
        b2=(Button)findViewById(R.id.button5);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
        if(s.equals(""))
            t.setText("No Doctor found \n");

        }
    }

