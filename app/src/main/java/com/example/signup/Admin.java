package com.example.signup;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Admin extends AppCompatActivity {
    Button add,b1;
    EditText e1,e2,e3,e4,e5,e6,e7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        FirebaseApp.initializeApp(this);
        b1=(Button)findViewById(R.id.button4);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Admin.this,Login.class);
                startActivity(i);
                finish();
            }
        });
        final DatabaseReference myRef=FirebaseDatabase.getInstance().getReference("doctors");


        add=(Button)findViewById(R.id.button);
        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                e1=(EditText)findViewById(R.id.city);
                e2=(EditText)findViewById(R.id.Hospital);
                e3=(EditText)findViewById(R.id.docname);
                e4=(EditText)findViewById(R.id.type);
                e5=(EditText)findViewById(R.id.from);
                e6=(EditText)findViewById(R.id.to);
                e7=(EditText)findViewById(R.id.fees);
                while(!verifyTime()){
                    Toast.makeText(getApplicationContext(),
                            "Incorrect Time", Toast.LENGTH_LONG).show();
                    e5.setText("");
                    e6.setText("");

                }
                final Doctors doc=new Doctors(e3.getText().toString(),e5.getText().toString()+"-"+e6.getText().toString(),e7.getText().toString(),e1.getText().toString(),e2.getText().toString(),e4.getText().toString());
                myRef.push().setValue(doc);
            }
        });
    }
    private boolean verifyTime() {
        String s1 = e5.getText().toString(), s2 = e6.getText().toString();
        if (s1.length() == 5 && s2.length() == 5 && s1.charAt(2) == ':' && s2.charAt(2) == ':') {
            int h1 = Integer.parseInt(s1.substring(0, 2));
            int m1 = Integer.parseInt(s1.substring(3));
            int h2 = Integer.parseInt(s2.substring(0, 2));
            int m2 = Integer.parseInt(s2.substring(3));
            if (h1 >= 0 && h1 < 24 && m1 >= 0 && m1 < 60 && h2 >= 0 && h2 < 24 && m1 >= 0 && m2 < 60)
                return true;
            else return false;
        } else
            return false;

        }
    }

