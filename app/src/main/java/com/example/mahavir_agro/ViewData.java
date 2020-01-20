package com.example.mahavir_agro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.net.Inet4Address;

public class ViewData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        Button button1=(Button)findViewById(R.id.CustName);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustName();
            }
        });
        Button b2=(Button)findViewById(R.id.back);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
    }

    public void openCustName()
    {
        Intent intent=new Intent(this, CustNameSpinner.class);
        startActivity(intent);
    }
    public void back(){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
