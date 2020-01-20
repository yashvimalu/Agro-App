package com.example.mahavir_agro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CustNameData extends AppCompatActivity {

    TableLayout t1;
    long quantity,bags;
    int count_quantity=0;
    String date;
    TextView display;
    String customername;
    String grain;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cust_name_data);

        t1=(TableLayout)findViewById(R.id.tableLayout1);

        display=(TextView)findViewById(R.id.display);
        //t1.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,TableLayout.LayoutParams.WRAP_CONTENT));
       //Toast.makeText(CustNameData.this, customer+"", Toast.LENGTH_SHORT).show();
        //Toast.makeText(CustNameData.this, grain+"", Toast.LENGTH_SHORT).show();

        TableRow tr_head = new TableRow(this);
        tr_head.setId(10);
        tr_head.setBackgroundColor(Color.GRAY);
        tr_head.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.WRAP_CONTENT,
                TableLayout.LayoutParams.WRAP_CONTENT));

        TextView label_date = new TextView(this);
        label_date.setId(20);
        label_date.setText("Date of Receiving");
        label_date.setTextColor(Color.WHITE);
        label_date.setPadding(5, 5, 5, 5);
        tr_head.addView(label_date);// add the column to the table row here

        TextView label_quan = new TextView(this);
        label_quan.setId(21);// define id that must be unique
        label_quan.setText("Quantity Received"); // set the text for the header
        label_quan.setTextColor(Color.WHITE); // set the color
        label_quan.setPadding(5, 5, 5, 5); // set the padding (if required)
        tr_head.addView(label_quan); // add the column to the table row here

        TextView label_bags = new TextView(this);
        label_bags.setId(20);
        label_bags.setText("Number of bags");
        label_bags.setTextColor(Color.WHITE);
        label_bags.setPadding(5, 5, 5, 5);
        tr_head.addView(label_bags);// add the column to the table row here

        TextView label_truck = new TextView(this);
        label_truck.setId(21);// define id that must be unique
        label_truck.setText("Truck Number"); // set the text for the header
        label_truck.setTextColor(Color.WHITE); // set the color
        label_truck.setPadding(5, 5, 5, 5); // set the padding (if required)
        tr_head.addView(label_truck); // add the column to the table row here
        t1.addView(tr_head, new TableLayout.LayoutParams(
                TableLayout.LayoutParams.WRAP_CONTENT,
                TableLayout.LayoutParams.WRAP_CONTENT));

        addData();
        Button b1=(Button)findViewById(R.id.button2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });

    }
    public void addData()
    {
        customername=getIntent().getStringExtra("Name of Customer");
        //Toast.makeText(CustNameData.this, customername+"", Toast.LENGTH_SHORT).show();

        grain=getIntent().getStringExtra("Grain");

        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                t1=(TableLayout)findViewById(R.id.tableLayout1);

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    for (DataSnapshot dataSnapshot2 : dataSnapshot1.getChildren()) {
                        if(customername.equals(dataSnapshot2.child("noc").getValue()) && grain.equals(dataSnapshot2.child("typeGrain").getValue())) {
                            date = (String) dataSnapshot2.child("dor").getValue();
                            quantity = (long) dataSnapshot2.child("quanQuin").getValue();
                            bags = (long) dataSnapshot2.child("bags").getValue();
                            String truckNo = (String) dataSnapshot2.child("truckNo").getValue();
                            //Toast.makeText(CustNameData.this, date+"", Toast.LENGTH_SHORT).show();
                            //Toast.makeText(CustNameData.this, quantity+"", Toast.LENGTH_SHORT).show();
                            //Toast.makeText(CustNameData.this, bags+"", Toast.LENGTH_SHORT).show();

                            TableRow tr = new TableRow(CustNameData.this);
                            tr.setLayoutParams(new TableLayout.LayoutParams(
                                    TableLayout.LayoutParams.MATCH_PARENT,
                                    TableLayout.LayoutParams.WRAP_CONTENT));

                            TextView labelDate = new TextView(CustNameData.this);
                            labelDate.setText(date);
                            labelDate.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                            tr.addView(labelDate);

                            TextView labelquan = new TextView(CustNameData.this);
                            labelquan.setText(Long.toString(quantity));
                            labelquan.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                            count_quantity +=quantity;
                            tr.addView(labelquan);

                            TextView labelbags = new TextView(CustNameData.this);
                            labelbags.setText(Long.toString(bags));
                            labelbags.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                            tr.addView(labelbags);

                            TextView labeltruckNo = new TextView(CustNameData.this);
                            labeltruckNo.setText(truckNo);
                            labeltruckNo.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                            tr.addView(labeltruckNo);

                            t1.addView(tr);

                        }
                    }
                    /*display = new TextView(CustNameData.this);*/
                    display.setText(String.valueOf(count_quantity));
                }
                //Toast.makeText(CustNameData.this, count_quantity+"", Toast.LENGTH_SHORT).show();
               //display = new TextView(CustNameData.this);
                //display.setText(count_quantity);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        /*;*/

    }
    public void back(){
        Intent intent=new Intent(CustNameData.this,CustNameSpinner.class);
        startActivity(intent);

    }

}