package com.example.wolfik.masterofmemory;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.TransitionDrawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.os.CountDownTimer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import android.widget.TextView;
import android.os.Handler;
import android.database.Cursor;
import android.util.Log;




public class MainActivity extends AppCompatActivity implements View.OnClickListener{



    ImageView btnGreen;
    ImageView btnRed;
    ImageView btnBlue;
    ImageView btnYellow;
    ImageView btnStart;

    TextView info;

    ZarzadzajDanymi zd;

    public static final int sound1 = R.raw.alesisc2;
    public static final int sound2 = R.raw.ec3;
    public static final int sound3 = R.raw.ec4;
    public static final int sound4 = R.raw.alesisc5;

    int rndId;
    Random rnd = new Random();
    List list = new ArrayList<Integer>();
    long startTime = 0;
    int tmp=-1; //-1 aby mieć chwilkę do rozpoczecia zabawy po wciśnięciu start
    int click=0;
    boolean player = false;
    boolean hard;
    String nick;
    int score=0;
    int id_gracza;



    @Override
    public void onClick(View view) {

    }

    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable()
    {
        @Override
        public void run(){
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;

            //to do

            timerHandler.postDelayed(this,500);//co ile ma się "odświeżać"
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = getIntent();
        nick = i.getExtras().getString("nickname");

        btnGreen = (ImageView)findViewById(R.id.btnGreen);
        btnRed = (ImageView)findViewById(R.id.btnRed);
        btnBlue = (ImageView)findViewById(R.id.btnBlue);
        btnYellow = (ImageView)findViewById(R.id.btnYellow);
        btnStart = (ImageView) findViewById(R.id.btnStart);

        btnGreen.setEnabled(false);
        btnRed.setEnabled(false);
        btnBlue.setEnabled(false);
        btnYellow.setEnabled(false);


        zd = new ZarzadzajDanymi(this);




        btnStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startGame(view);
                //btnStart.setEnabled(false);
            }
        });

        btnGreen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Sound(1);

                ((TransitionDrawable) btnGreen.getDrawable()).startTransition(100);

                ((TransitionDrawable) btnGreen.getDrawable()).reverseTransition(500);

                if(player)
                {
                    if((int)list.get(click)== 1 )
                    {
                        click++;
                        if(click == list.size())
                            NextStep(view);


                    }

                    else
                        endGame(view);

                }

            }
        });

        btnRed.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Sound(2);
                ((TransitionDrawable) btnRed.getDrawable()).startTransition(100);

                ((TransitionDrawable) btnRed.getDrawable()).reverseTransition(500);

                if(player)
                {
                    if((int)list.get(click)== 2 )
                    {
                        click++;
                        if(click == list.size())
                            NextStep(view);


                    }

                    else
                        endGame(view);

                }
            }
        });

        btnBlue.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Sound(3);
                ((TransitionDrawable) btnBlue.getDrawable()).startTransition(100);

                ((TransitionDrawable) btnBlue.getDrawable()).reverseTransition(500);

                if(player)
                {
                    if((int)list.get(click)== 3 )
                    {
                        click++;
                        if(click == list.size())
                            NextStep(view);


                    }

                    else
                        endGame(view);

                }
            }
        });

        btnYellow.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Sound(4);
                ((TransitionDrawable) btnYellow.getDrawable()).startTransition(100);

                ((TransitionDrawable) btnYellow.getDrawable()).reverseTransition(500);

                if(player)
                {
                    if((int)list.get(click)== 4 )
                    {
                        click++;
                        if(click == list.size())
                            NextStep(view);


                    }

                    else
                        endGame(view);

                }
            }
        });




    }


    public void startGame(View v) {

        //EnableButtons(v);
        NextStep(v);


    }

    public void endGame(View v)
    {
        score=list.size()*10;
        zd.insert(nick,""+score);
        Toast.makeText(this,"PRZEGRALES "+nick,Toast.LENGTH_SHORT).show();

        Intent i = new Intent(getApplicationContext(),score.class);
        startActivity(i);

    }

    public void NextStep(View v) // losowanie przycisku i pokazanie go graczowi
    {
        DisableButtons();
        player=false;
        click = 0;

        rndId = rnd.nextInt(4)+1;

        list.add(rndId);
        Toast.makeText(this,"id: "+rndId,Toast.LENGTH_SHORT).show();

        new CountDownTimer((list.size()+1)*2000,1990)
        {

            @Override
            public void onTick(long l) {

                if(tmp >= 0) {
                    ShowColor((int) list.get(tmp));
                }
                    tmp++;
            }

            @Override
            public void onFinish() {
                tmp=-1;
                playersTurn();
            }
        }.start();


    }

    public void playersTurn()
    {
        EnableButtons();
        player=true;


    }

    public void EnableButtons()
    {
        btnGreen.setEnabled(true);
        btnRed.setEnabled(true);
        btnBlue.setEnabled(true);
        btnYellow.setEnabled(true);
    }

    public void DisableButtons()
    {
        btnGreen.setEnabled(false);
        btnRed.setEnabled(false);
        btnBlue.setEnabled(false);
        btnYellow.setEnabled(false);
    }

    public void PlaySound(Context context, int soundID)
    {
        MediaPlayer mp = MediaPlayer.create(context,soundID);
        if(mp != null) {
            mp.start();
        }
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.release();
            }
        });

    }


    //Metody pozwalające na zmianę id na numer i odwrotnie
   /* public int btnDic(String btnId)
    {
        switch(btnId)
        {
            case "btnGreen":
            {
                return 1;
            }
            case "btnRed":
            {
                return 2;
            }
            case "btnBlue":
            {
                return 3;
            }
            case "btnYellow":
            {
                return 4;
            }
            default:
            {
                return 0;
            }

        }
    }
    */

    public void ShowColor(int btnNumber)
    {
        switch(btnNumber)
        {
            case 1:
            {
                Sound(btnNumber);
                btnGreen.performClick();
                break;
            }
            case 2:
            {
                Sound(btnNumber);
                btnRed.performClick();
                break;
            }
            case 3:
            {
                Sound(btnNumber);
                btnBlue.performClick();
                break;
            }
            case 4:
            {
                Sound(btnNumber);
                btnYellow.performClick();
                break;
            }
            default:
                ;

        }
    }

    public void Sound(int s)
    {
        switch(s)
        {
            case 1:
            {
                PlaySound(this,sound1);
                break;
            }
            case 2:
            {
                PlaySound(this,sound2);
                break;
            }
            case 3:
            {
                PlaySound(this,sound3);
                break;
            }
            case 4:
            {
                PlaySound(this,sound4);
                break;
            }
            default:
                ;

        }
    }



}
