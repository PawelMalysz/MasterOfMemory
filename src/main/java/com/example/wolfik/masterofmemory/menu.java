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
import android.widget.Toast;

/**
 * Created by Wolfik on 22.01.2018.
 */

public class menu extends AppCompatActivity{

    EditText nick;
    Button start;
    Button options;
    Button exit;
    CompoundButton difficulty;
    Boolean difficult=false;




    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        nick = (EditText) findViewById(R.id.inputNickname);
        start = (Button) findViewById(R.id.menuStart);
        exit = (Button) findViewById(R.id.menuExit);
        options = (Button) findViewById(R.id.menuOptions);
        difficulty = (CompoundButton) findViewById(R.id.menuDifficulty);




        start.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);

                if(difficulty.isChecked())
                    i.putExtra("hard",true);

                else
                    i.putExtra("hard",false);

                i.putExtra("nickname",nick.getText().toString());

                startActivity(i);
            }

        });




    };


}
