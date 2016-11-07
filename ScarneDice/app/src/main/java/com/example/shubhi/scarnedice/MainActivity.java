package com.example.shubhi.scarnedice;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int userTotalScore, userTurnScore, compTotalScore, compTurnScore;
    Button roll, hold, reset;
    TextView score;
    ImageView dice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
        userTotalScore = 0;
        userTurnScore = 0;
        compTotalScore = 0;
        compTurnScore = 0;
        roll = (Button) findViewById(R.id.button);
        hold = (Button) findViewById(R.id.button2);
        reset = (Button) findViewById(R.id.button3);
        dice = (ImageView) findViewById(R.id.imageView);
        score = (TextView) findViewById(R.id.textView);

        roll.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                int min = 1;
                int max = 6;

                Log.e("Shubhi", "Inside Roll");
                int random_dice_value = rollDice();
                if (random_dice_value == 1) {
                    userTurnScore = 0;

                } else
                    userTurnScore += random_dice_value;
                score.setText("Your Turn Score:" + userTurnScore + "  Computer SCore:" + compTotalScore);

                if (random_dice_value == 1) {
                    computerTurn();
                }

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userTotalScore = 0;
                userTurnScore = 0;
                compTotalScore = 0;
                compTurnScore = 0;

                score.setText("Your Score: 0    Computer Score: 0");

            }
        });

        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userTotalScore += userTurnScore;
                userTurnScore = 0;
                score.setText("Your Score: " + userTotalScore + "   Computer Score:" + compTotalScore);
                compTurnScore = 0;
                computerTurn();
            }
        });
    }

    private void computerTurn() {

        roll.setEnabled(false);
        hold.setEnabled(false);
        int random_dice_value = rollDice();

        if (random_dice_value == 1) {
            compTurnScore = 0;
            roll.setEnabled(true);
            hold.setEnabled(true);
        } else
            compTurnScore += random_dice_value;


        if (compTurnScore < 20) {
            if (compTurnScore == 0) {
                score.setText("Your SCore:" + userTotalScore + "  Computer Score:" + compTotalScore);
            }
            else {
                Toast.makeText(MainActivity.this, "Computers Turn", 2000);
               // computerTurn();

                new Handler().postDelayed(new Runnable(){


                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "Computers Turn", 2000);

                        computerTurn();
                    }
                },2000);

            }

        }
        else
        {
            roll.setEnabled(true);
            hold.setEnabled(true);
            compTotalScore += compTurnScore;
            score.setText("Your score :" + userTotalScore + "   Computer Score:" + compTotalScore);
        }
    }


    private int rollDice() {
        int min=1;
        int max=6;
        int value= (int) (min+ Math.random()*(max-min+1));
        switch (value)
        {
            case 1:
                dice.setImageDrawable(getResources().getDrawable(R.drawable.dice1));
                break;
            case 2:
                dice.setImageDrawable(getResources().getDrawable(R.drawable.dice2));
                break;
            case 3:
                dice.setImageDrawable(getResources().getDrawable(R.drawable.dice3));
                break;
            case 4:
                dice.setImageDrawable(getResources().getDrawable(R.drawable.dice4));
                break;
            case 5:
                dice.setImageDrawable(getResources().getDrawable(R.drawable.dice5));
                break;
            case 6:
                dice.setImageDrawable(getResources().getDrawable(R.drawable.dice6));
                break;
        }

        return value;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
