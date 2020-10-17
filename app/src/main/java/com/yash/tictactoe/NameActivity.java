package com.yash.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NameActivity extends AppCompatActivity {

    EditText e1;
    EditText e2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
        e1 = findViewById(R.id.player1);
        e2 = findViewById(R.id.player2);
    }

    public void startTheGame(View v)
    {

        Intent i = new Intent(NameActivity.this,GameActivity.class);
        String p1 = e1.getText().toString();
        String p2 = e2.getText().toString();
        if (p1.isEmpty() || p2.isEmpty()){
            Toast.makeText(NameActivity.this,"Please Enter your name",Toast.LENGTH_LONG).show();
        }
        else{
            i.putExtra("Player1 Name",p1);
            i.putExtra("Player2 Name",p2);
            startActivity(i);
        }
    }
}