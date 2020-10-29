package com.example.signup;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

public class Login extends AppCompatActivity {
    Button b1,b2;
    EditText e1,e2;
    Calendar myCalendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        b1=(Button)findViewById(R.id.logbtn);
        b2=(Button)findViewById(R.id.supbtn);
        e1=(EditText)findViewById(R.id.phone);
        e2=(EditText)findViewById(R.id.dob1);
        myCalendar = Calendar.getInstance();

        //EditText edittext= (EditText) findViewById(R.id.dob1);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        e2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(Login.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        FirebaseApp.initializeApp(this);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Login.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(e1.getText().toString().equals("9876543210")&&e2.getText().toString().equals("01/01/2000"))
                {
                    Intent j=new Intent(Login.this,Admin.class);
                    startActivity(j);
                    finish();
                }
                else {
                    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("users");

                    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Log.e("Count ", "" + dataSnapshot.getChildrenCount());
                            boolean flag = false;
                            for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                                Users post = postSnapshot.getValue(Users.class);
                                if (e1.getText().toString().equals(post.mob) && e2.getText().toString().equals(post.dob)) {
                                    Toast.makeText(getApplicationContext(), "Login success", Toast.LENGTH_LONG).show();
                                    flag = true;

                                     Intent k= new Intent(Login.this,Finder.class);
                                     startActivity(k);
                                     finish();
                                    break;
                                }

                            }
                            if (!flag)
                                Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_LONG).show();

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });
    }
    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        e2.setText(sdf.format(myCalendar.getTime()));
    }
}
