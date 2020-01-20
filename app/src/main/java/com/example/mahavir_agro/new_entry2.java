package com.example.mahavir_agro;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.Date;

public class new_entry2 extends AppCompatActivity {

    private Button button1, saveBut;
    EditText noc, quan, bag, grain, truNo, tor, dor;
    FirebaseDatabase db;
    DatabaseReference reff;
    double bags;
    NEWENTRY2 ne2;
    float quantity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry2);

        button1 = (Button) findViewById(R.id.Back);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMain();
            }
        });

        noc = (EditText) findViewById(R.id.NOC);
        quan = (EditText) findViewById(R.id.quan);
        bag = (EditText) findViewById(R.id.Bags);
        grain = (EditText) findViewById(R.id.Grain);
        truNo = (EditText) findViewById(R.id.TruckNo);
        tor = (EditText) findViewById(R.id.TOR);
        dor = (EditText) findViewById(R.id.DOR);
        saveBut = (Button) findViewById(R.id.save);

        ne2 = new NEWENTRY2();
        db = FirebaseDatabase.getInstance();
        reff = db.getReference().child("NEWENTRY2");

        dor.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    Log.e("dor", "onClick: " );
                    DatePickerDialog dialog = new DatePickerDialog(new_entry2.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            dor.setText(dayOfMonth + "/" + month + "/" + year);
                        }
                    }, 2019, 1, 1);
                    dialog.show();   //to remove calendar
                }
            }
        });

        tor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog dialog = new TimePickerDialog(new_entry2.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        tor.setText(hourOfDay + ":" + minute);
                    }
                }, 0, 0, false);

                dialog.show();
            }
        });

        saveBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if(TextUtils.isEmpty(noc.getText().toString().trim()))
                    noc.setError("Enter valid Name");
                else if (TextUtils.isEmpty(quan.getText().toString().trim()))
                    noc.setError("Enter Quantity in Quintal");
                    //Toast.makeText(this,"Enter Quantity in Quintal",Toast.LENGTH_SHORT).show();
                else if (TextUtils.isEmpty(bag.getText().toString().trim()))
                    noc.setError("Enter number of bags");
                    //Toast.makeText(this,"Enter Number of Bags",Toast.LENGTH_SHORT).show();
                else if (TextUtils.isEmpty(grain.getText().toString().trim()))
                    noc.setError("Enter Type of Grain");
                    //Toast.makeText(this, "Enter Type of Grain", Toast.LENGTH_SHORT).show();
                else if (TextUtils.isEmpty(truNo.getText().toString().trim()))
                    noc.setError("Enter Truck Number");

//        Toast.makeText(this, "Enter Truck Number", Toast.LENGTH_SHORT).show();
                else if (TextUtils.isEmpty(tor.getText().toString().trim()))
                    noc.setError("Time of Receiving");
                    // Toast.makeText(this, "Enter Time of Receiving", Toast.LENGTH_SHORT).show();
                else if (TextUtils.isEmpty(dor.getText().toString().trim()))
                    noc.setError("Enter Date of Receiving");
//        Toast.makeText(this, "Enter Date of Receiving", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(NewEntry.this, "Saved Successfully", Toast.LENGTH_SHORT).show();*/
                if (check()) {


                    quantity = Float.parseFloat(quan.getText().toString().trim());
                    bags = Double.parseDouble(bag.getText().toString().trim());
                    ne2.setNoc(noc.getText().toString().trim());
                    ne2.setQuanQuin(quantity);
                    ne2.setBags(bags);
                    ne2.setTruckNo(truNo.getText().toString().trim());
                    ne2.setTypeGrain(grain.getText().toString().trim());
                    ne2.setDor(dor.getText().toString().trim());
                    ne2.setTor(tor.getText().toString().trim());
                    reff.push().setValue(ne2);
                    //Toast.makeText(NewEntry.this, "Data added to firebase", Toast.LENGTH_LONG).show();
                }
            }
        });

        noc.requestFocus();
    }

    public void openMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public boolean check() {
        if (noc.getText().toString().equals("")) {
            Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (quan.getText().toString().equals("")) {
            // noc.setError("Enter Quantity in Quintal");
            Toast.makeText(this, "Enter Quantity in Quintal", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (bag.getText().toString().equals("")) {
            //noc.setError("Enter number of bags");
            Toast.makeText(this,"Enter Number of Bags",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (grain.getText().toString().equals("")) {
            //noc.setError("Enter Type of Grain");

            Toast.makeText(this, "Enter Type of Grain", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (truNo.getText().toString().equals("")) {
            //noc.setError("Enter Truck Number");

        Toast.makeText(this, "Enter Truck Number", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (tor.getText().toString().equals("")) {
            //noc.setError("Time of Receiving");
             Toast.makeText(this, "Enter Time of Receiving", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (dor.getText().toString().equals("")) {
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

