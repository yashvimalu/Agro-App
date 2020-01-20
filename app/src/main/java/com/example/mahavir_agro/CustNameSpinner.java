package com.example.mahavir_agro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.hardware.camera2.TotalCaptureResult;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CustNameSpinner extends AppCompatActivity {
    private Spinner spinner1,spinner2;
    private DatabaseReference mDatabase;
    public String nocus,grain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cust_name_spinner);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("NEWENTRY2");
        spinner1 = (Spinner)findViewById(R.id.spinner);
        spinner2 = (Spinner)findViewById(R.id.grain);

        Query query = mDatabase.orderByChild("noc");
        final ProgressDialog dialog = new ProgressDialog(CustNameSpinner.this);
        dialog.setMessage("Loading...");
        dialog.show();
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                 final List<String> titleList = new ArrayList<String>();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    nocus = dataSnapshot1.child("noc").getValue(String.class);
                    //String grain = dataSnapshot1.child("typeGrain").getValue(String.class);
                    if (!titleList.contains(nocus))
                            titleList.add(nocus);
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(CustNameSpinner.this, android.R.layout.simple_spinner_item, titleList);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner1.setAdapter(arrayAdapter);
                dialog.hide();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Toast.makeText(CustNameSpinner.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
            //String compa=spinner1.getSelectedItem().toString();
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final List<String> grainList = new ArrayList<String>();

                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       for(DataSnapshot dataSnapshot3: dataSnapshot.getChildren()) {
                           if (spinner1.getSelectedItem().equals(dataSnapshot3.child("noc").getValue(String.class)))
                            {
                                grain = dataSnapshot3.child("typeGrain").getValue(String.class);
                                if(!grainList.contains(grain));
                                       grainList.add(grain);
                            }
                        }
                        System.out.println(spinner1.getSelectedItem().toString());
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(CustNameSpinner.this, android.R.layout.simple_spinner_item, grainList);
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner2.setAdapter(arrayAdapter);

                        //Toast.makeText(CustNameSpinner.this, customer+"", Toast.LENGTH_SHORT).show();
                        //Toast.makeText(CustNameSpinner.this, grain+"", Toast.LENGTH_SHORT).show();

                }


                    @Override

                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Button button2=(Button)findViewById(R.id.back);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewData();
            }
    });

        Button b1=(Button)findViewById(R.id.next);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                next();
            }
        });

    };

public void ViewData(){
    Intent intent = new Intent(this,ViewData.class);
    startActivity(intent);
}
public void next(){
    Intent intent = new Intent(getBaseContext(), CustNameData.class);
    String customer=spinner1.getSelectedItem().toString();
    String grain=spinner2.getSelectedItem().toString();
    intent.putExtra("Name of Customer", customer);
    intent.putExtra("Grain", grain);
    startActivity(intent);
}
}