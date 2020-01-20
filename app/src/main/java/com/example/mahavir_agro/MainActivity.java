package com.example.mahavir_agro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1=(Button)findViewById(R.id.NewEntry);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewEntry();
            }
        }); // calling onClick() method


        Button button2=(Button)findViewById(R.id.bViewData);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openViewData();
            }
        });

        Button button3=(Button)findViewById(R.id.OutWard);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                outWard();
            }
        });

        /*Button button4=(Button)findViewById(R.id.outReport);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                outReport();
            }
        });*/
    };

        public void openNewEntry(){
            Intent intent = new Intent(this,new_entry2.class);
            startActivity(intent);
        }
        public void openViewData(){
            Intent intent=new Intent(this,ViewData.class);
            startActivity(intent);
        }
        public void outWard(){
        Intent intent=new Intent(this,outWard1.class);
        startActivity(intent);
    }

}