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

public class outWard1 extends AppCompatActivity {
    private Spinner spinner4,spinner2;
    private DatabaseReference mDatabase;
    public String nocus,grain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_ward1);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("NEWENTRY2");

        spinner2 = (Spinner)findViewById(R.id.spinner2);
        spinner4 = (Spinner)findViewById(R.id.spinner4);

        Query query = mDatabase.orderByChild("noc");
        final ProgressDialog dialog = new ProgressDialog(outWard1.this);
        dialog.setMessage("Loading...");
        dialog.show();
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final List<String> titleList = new ArrayList<String>();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    nocus = dataSnapshot1.child("noc").getValue(String.class);

                    if (!titleList.contains(nocus))
                            titleList.add(nocus);
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(outWard1.this, android.R.layout.simple_spinner_item, titleList);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(arrayAdapter);
                dialog.hide();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Toast.makeText(CustNameSpinner.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final List<String> grainList = new ArrayList<String>();

                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot dataSnapshot3: dataSnapshot.getChildren()) {
                            if (spinner2.getSelectedItem().equals(dataSnapshot3.child("noc").getValue(String.class)))
                            {
                                grain = dataSnapshot3.child("typeGrain").getValue(String.class);
                                grainList.add(grain);
                            }
                        }
                        System.out.println(spinner2.getSelectedItem().toString());
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(outWard1.this, android.R.layout.simple_spinner_item, grainList);
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner4.setAdapter(arrayAdapter);

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
        Button button2=(Button)findViewById(R.id.BACK);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });

        Button b1=(Button)findViewById(R.id.NEXT);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next();
            }
        });

        Button b3=(Button)findViewById(R.id.generateReport);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateReport();
            }
        });
    };

    public void back(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void next(){
        Intent intent = new Intent(getBaseContext(), outData.class);
        String customer=spinner2.getSelectedItem().toString();
        String grain=spinner4.getSelectedItem().toString();
        intent.putExtra("Name of Customer", customer);
        intent.putExtra("Grain", grain);
        startActivity(intent);
    }
    public void generateReport()
    {
        Intent intent=new Intent(this,report.class);
        String customer=spinner2.getSelectedItem().toString();
        String grain=spinner4.getSelectedItem().toString();
        intent.putExtra("Name of Customer", customer);
        intent.putExtra("Grain", grain);
        startActivity(intent);
    }
}