package com.yash.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    private String p1;
    private String p2;
    private int turn=0;
    private int player1Count;
    private int player2Count;
    private MediaPlayer p;
    private Vibrator v;
    private boolean isSound = true;
    private int activePlayer    = 0;
    private int gridPosition[]  = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    private int winPosition[][] = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        p = MediaPlayer.create(this, R.raw.tap);
        v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        Intent i = getIntent();
        p1 = i.getStringExtra("Player1 Name");
        p2 = i.getStringExtra("Player2 Name");
        if (p1.isEmpty())p1 = "Player1";
        if (p2.isEmpty())p2 = "Player2";
        player1Count=0;
        player2Count=0;
        TextView status = findViewById(R.id.status);
        status.setText(p1+"'s turn-Please Tap");



    }
    boolean winner = false;
    public void ticTacToe(View view)
    {
        if (!winner && isSound) {
            p.start();
        }
        ImageView img   = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());

        if (gridPosition[tappedImage] == 2 && !winner)
        {
            gridPosition[tappedImage] = activePlayer;
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer    = 1;
                TextView status = findViewById(R.id.status);
                if (turn%2==0)
                status.setText(p2+"'s turn-Please Tap");
                else
                status.setText(p1+"'s turn-Please Tap");
            }
            else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                if (turn%2==0)
                    status.setText(p1+"'s turn-Please Tap");
                else
                    status.setText(p2+"'s turn-Please Tap");
            }
        }


        if (!winner)
        for (int g[]: winPosition)
            if (gridPosition[g[0]] == gridPosition[g[1]] && gridPosition[g[1]] == gridPosition[g[2]] && gridPosition[g[0]] != 2)
        {
            {
                winner = true;
                if (gridPosition[g[0]] == 1)
                {
                    for (int i = 0; i <= 2; i++)
                    {
                        int n = g[i];
                        switch (n)
                        {
                            case 0: ((ImageView)findViewById(R.id.imageView0)).setImageResource(R.drawable.ored);break;
                            case 1: ((ImageView)findViewById(R.id.imageView1)).setImageResource(R.drawable.ored);break;
                            case 2: ((ImageView)findViewById(R.id.imageView2)).setImageResource(R.drawable.ored);break;
                            case 3: ((ImageView)findViewById(R.id.imageView3)).setImageResource(R.drawable.ored);break;
                            case 4: ((ImageView)findViewById(R.id.imageView4)).setImageResource(R.drawable.ored);break;
                            case 5: ((ImageView)findViewById(R.id.imageView5)).setImageResource(R.drawable.ored);break;
                            case 6: ((ImageView)findViewById(R.id.imageView6)).setImageResource(R.drawable.ored);break;
                            case 7: ((ImageView)findViewById(R.id.imageView7)).setImageResource(R.drawable.ored);break;
                            case 8: ((ImageView)findViewById(R.id.imageView8)).setImageResource(R.drawable.ored);break;
                        }
                    }
                    TextView s = findViewById(R.id.status);
                    if (turn%2==0)
                    {s.setText(p2+" has won");player2Count++;}
                    else
                    {s.setText(p1+" has won");player1Count++;};


                }
                else
                {
                    for (int i = 0; i <= 2; i++)
                    {
                        int n = g[i];
                        switch (n)
                        {
                            case 0: ((ImageView)findViewById(R.id.imageView0)).setImageResource(R.drawable.xred);break;
                            case 1: ((ImageView)findViewById(R.id.imageView1)).setImageResource(R.drawable.xred);break;
                            case 2: ((ImageView)findViewById(R.id.imageView2)).setImageResource(R.drawable.xred);break;
                            case 3: ((ImageView)findViewById(R.id.imageView3)).setImageResource(R.drawable.xred);break;
                            case 4: ((ImageView)findViewById(R.id.imageView4)).setImageResource(R.drawable.xred);break;
                            case 5: ((ImageView)findViewById(R.id.imageView5)).setImageResource(R.drawable.xred);break;
                            case 6: ((ImageView)findViewById(R.id.imageView6)).setImageResource(R.drawable.xred);break;
                            case 7: ((ImageView)findViewById(R.id.imageView7)).setImageResource(R.drawable.xred);break;
                            case 8: ((ImageView)findViewById(R.id.imageView8)).setImageResource(R.drawable.xred);break;
                        }
                    }
                    TextView status = findViewById(R.id.status);
                    if (turn%2==0)
                    {status.setText(p1+" has won");player1Count++;}
                    else
                    {status.setText(p2+" has won");player2Count++;}
                }
                turn++;
                String win = "SCORE"+"\n" +
                        p1+" - "+player1Count+"\n"+
                        p2+" - "+player2Count;
                Toast.makeText(this,win,Toast.LENGTH_LONG).show();
                TextView t = findViewById(R.id.textView6);
                t.setText("Please Click on Play again");
                break;
            }
        }
        if (!winner)
        {
            boolean check = false;
            for (int i = 0; i < 9; i++)
            {
                if (gridPosition[i] != 2)
                {
                    check = true;
                }
                else {
                    check = false;
                    break;
                }
            }
            if (check)
            {
                Button button = findViewById(R.id.button);
                button.setVisibility(View.VISIBLE);
                TextView t = findViewById(R.id.status);
                t.setText("Match tie");
                turn++;
                TextView s = findViewById(R.id.textView6);
                s.setText("Please Click on Play again");
            }
        }
        if (winner)
        {
            Button button = findViewById(R.id.button);
            button.setVisibility(View.VISIBLE);
        }
    }

    public void gameReset(View view)
    {
        TextView status = findViewById(R.id.status);
        if (turn%2==0)
            status.setText(p1+"'s turn-Please Tap");
        else
        status.setText(p2+"'s turn-Please Tap");
        TextView a = findViewById(R.id.textView6);
        a.setText("");
        for (int i = 0; i < 9; i++)
            gridPosition[i] = 2;
        activePlayer = 0;
        winner = false;
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
    }

    public void restartTheGame(View view)
    {

        if (isSound)
            v.vibrate(100);
        Button button = findViewById(R.id.button);
        button.setVisibility(View.INVISIBLE);
        gameReset(view);
    }

    public void sound(View view)
    {
        Switch s = (Switch) findViewById(R.id.sound);
        isSound = s.isChecked();

    }
}
