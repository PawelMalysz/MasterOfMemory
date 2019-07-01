package com.example.wolfik.masterofmemory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.view.View;
import android.content.Context;


public class score extends AppCompatActivity {

    ZarzadzajDanymi zd;
    EditText scoreTable;
    String data = "";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);


        for(int i =0; i< zd.selectAll().getCount();i++)
        {
            data +=(zd.selectAll().getString(i)+"/n");
        }
        scoreTable.setText(data);

    };










}
