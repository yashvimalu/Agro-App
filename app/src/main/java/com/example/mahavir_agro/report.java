package com.example.mahavir_agro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
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

public class report extends AppCompatActivity {

    TableLayout t1;
    String customer_name,grain,date;
    long bags_loaded;
    int count_quantity=0;
    TextView display;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        display=(TextView)findViewById(R.id.display);

        Button b1=(Button)findViewById(R.id.back);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
        t1=(TableLayout)findViewById(R.id.tl2);

        TableRow tr_head = new TableRow(this);
        tr_head.setId(10);
        tr_head.setBackgroundColor(Color.GRAY);
        tr_head.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.WRAP_CONTENT,
                TableLayout.LayoutParams.WRAP_CONTENT));

        TextView label_date = new TextView(this);
        label_date.setId(20);
        label_date.setText("Date");
        label_date.setTextColor(Color.WHITE);
        label_date.setPadding(5, 5, 10, 5);
        tr_head.addView(label_date);// add the column to the table row here

        TextView label_bags = new TextView(this);
        label_bags.setId(21);// define id that must be unique
        label_bags.setText("Number of Bags Loaded"); // set the text for the header
        label_bags.setTextColor(Color.WHITE); // set the color
        label_bags.setPadding(10, 5, 5, 5); // set the padding (if required)
        tr_head.addView(label_bags); // add the column to the table row here

        /*TextView label_bags = new TextView(this);
        label_bags.setId(20);
        label_bags.setText("Size of jute bag");
        label_bags.setTextColor(Color.WHITE);
        label_bags.setPadding(5, 5, 5, 5);
        tr_head.addView(label_bags);// add the column to the table row here*/

        t1.addView(tr_head, new TableLayout.LayoutParams(
                TableLayout.LayoutParams.WRAP_CONTENT,
                TableLayout.LayoutParams.WRAP_CONTENT));


        showReport();
    }
    public void back(){
        Intent intent=new Intent(report.this, outWard1.class);
        startActivity(intent);
    }
    public void showReport(){

        customer_name=getIntent().getStringExtra("Name of Customer");
        //Toast.makeText(report.this, customer_name+"", Toast.LENGTH_SHORT).show();

        grain=getIntent().getStringExtra("Grain");

        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                t1=(TableLayout)findViewById(R.id.tl2);

                for (DataSnapshot dataSnapshot1 : dataSnapshot.child("OUT_DATA").getChildren()) {
                  // for (DataSnapshot dataSnapshot2 : dataSnapshot1.getChildren()) {
                        if(customer_name.equals(dataSnapshot1.child("noc").getValue()) && grain.equals(dataSnapshot1.child("typeGrain").getValue())) {
                            date = (String) dataSnapshot1.child("dor").getValue();
                            bags_loaded = (long) dataSnapshot1.child("bags").getValue();

                            TableRow tr = new TableRow(report.this);
                            tr.setLayoutParams(new TableLayout.LayoutParams(
                                    TableLayout.LayoutParams.MATCH_PARENT,
                                    TableLayout.LayoutParams.WRAP_CONTENT));

                            TextView labelDate = new TextView(report.this);
                            labelDate.setText(date);
                            labelDate.setPadding(5, 5, 10, 5);
                            labelDate.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                            tr.addView(labelDate);

                            TextView labelbags = new TextView(report.this);
                            labelbags.setText(Long.toString(bags_loaded));
                            labelbags.setPadding(10, 5, 5, 5); // set the padding (if required)
                            labelbags.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                            count_quantity +=bags_loaded;
                            tr.addView(labelbags);

                            t1.addView(tr);
                        }
                    }
                display.setText(String.valueOf(count_quantity));
                /*display = new TextView(CustNameData.this);*/
                //}
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        /*;*/
    }
}