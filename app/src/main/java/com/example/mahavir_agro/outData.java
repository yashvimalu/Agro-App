package com.example.mahavir_agro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class outData extends AppCompatActivity {

    long quantity;
    Button back;
    Double bags;
    EditText bags_loaded,date,vehicle_number,driver_name,custname,grain_type;
    String customer_name;
    String grain;
    FirebaseDatabase db;
    DatabaseReference reff;
    OUT_DATA od;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_data);

        custname=(EditText)findViewById(R.id.noc_out);
        grain_type=(EditText)findViewById(R.id.grain_out);
        bags_loaded=(EditText)findViewById(R.id.bags_out);
        date=(EditText)findViewById(R.id.date_out);
        vehicle_number=(EditText)findViewById(R.id.vehicle_out);
        driver_name=(EditText)findViewById(R.id.driver_out);

        bags_loaded.requestFocus();

        od = new OUT_DATA();
        db = FirebaseDatabase.getInstance();
        reff = db.getReference().child("OUT_DATA");


        back=(Button)findViewById(R.id.button3);
        if (back == null)
            Log.e("back", "onCreate: "+"null" );
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
        date.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    Log.e("dor", "onClick: " );
                    DatePickerDialog dialog = new DatePickerDialog(outData.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            date.setText(dayOfMonth + "/" + month + "/" + year);
                        }
                    }, 2019, 1, 1);
                    dialog.show();   //to remove calendar
                }
            }
        });

        customer_name=getIntent().getStringExtra("Name of Customer");
        grain=getIntent().getStringExtra("Grain");
        
        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    for (DataSnapshot dataSnapshot2 : dataSnapshot1.getChildren()) {
                        if(customer_name.equals(dataSnapshot2.child("noc").getValue()) && grain.equals(dataSnapshot2.child("typeGrain").getValue())) {
                            custname.setText(customer_name);
                            grain_type.setText(grain);
                        }
                    }
                }
                //Toast.makeText(CustNameData.this, count_quantity+"", Toast.LENGTH_SHORT).show();
                //display = new TextView(CustNameData.this);
                //display.setText(count_quantity);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Button save=(Button)findViewById(R.id.save_out);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check()) {


                    bags = Double.parseDouble(bags_loaded.getText().toString().trim());
                    od.setVehicle_num(vehicle_number.getText().toString().trim());
                    od.setDor(date.getText().toString().trim());
                    od.setNoc(custname.getText().toString().trim());
                    od.setTypeGrain(grain_type.getText().toString().trim());
                    od.setDriver_name(driver_name.getText().toString().trim());
                    od.setBags(bags);
                    reff.push().setValue(od);
                    //Toast.makeText(NewEntry.this, "Data added to firebase", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    public void back()
    {
        Intent intent=new Intent(outData.this,outWard1.class);
        startActivity(intent);
    }
    public boolean check() {
        /*if (noc.getText().toString().equals("")) {
            Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (quan.getText().toString().equals("")) {
            // noc.setError("Enter Quantity in Quintal");
            Toast.makeText(this, "Enter Quantity in Quintal", Toast.LENGTH_SHORT).show();
            return false;
        }
        else*/
        if (bags_loaded.getText().toString().equals("")) {
            //noc.setError("Enter number of bags");
            Toast.makeText(this,"Enter Number of Bags",Toast.LENGTH_SHORT).show();
            return false;
        }
        /*else if (grain.getText().toString().equals("")) {
            //noc.setError("Enter Type of Grain");

            Toast.makeText(this, "Enter Type of Grain", Toast.LENGTH_SHORT).show();
            return false;
        }
        else*/ if (vehicle_number.getText().toString().equals("")) {
            //noc.setError("Enter Truck Number");

            Toast.makeText(this, "Enter Truck Number", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (date.getText().toString().equals("")) {
            //noc.setError("Time of Receiving");
            Toast.makeText(this, "Enter Time of Receiving", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (driver_name.getText().toString().equals("")) {
            // noc.setError("Enter Date of Receiving");
            Toast.makeText(this, "Enter Date of Receiving", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            Toast.makeText(this, "Saved Successfully", Toast.LENGTH_SHORT).show();
            return true;
        }
    }
}