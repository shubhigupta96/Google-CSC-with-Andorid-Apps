package com.google.engedu.ghost;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class GhostActivity extends ActionBarActivity {
    private static final String COMPUTER_TURN = "Computer's turn";
    private static final String USER_TURN = "Your turn";
    private static final String TAG = "ghost activity";
    private GhostDictionary dictionary;
    private boolean userTurn = false;
    private Random random = new Random();
    TextView ghostText;
    TextView gameStatus;
    String mWordFragment;
   Button restart_button;
Button challenge_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ghost);
        ghostText=(TextView)findViewById(R.id.ghostText);
        gameStatus=(TextView)findViewById(R.id.gameStatus);
        challenge_button=(Button)findViewById(R.id.challenge_button);
        restart_button=(Button)findViewById(R.id.restart_button);

        mWordFragment=ghostText.getText().toString();
        try {
            dictionary = new FastDictionary(getAssets().open("words.txt"));
        }catch (Exception e) {
            Log.d(TAG, "exception" + e);
        }


        restart_button.setOnClickListener(new View.OnClickListener()
        {
            @Override

            public void onClick(View view)
            {
             ghostText.setText("");
                gameStatus.setText("");
                onStart(null);

            }
                                          });


                challenge_button.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View view)
            {
                if(mWordFragment.length()>=4 && dictionary.isWord(mWordFragment)){
                    gameStatus.setText("u won");
                }else if(dictionary.getAnyWordStartingWith(mWordFragment)!= null){
                    String mPossibleLongerWord = dictionary.  getAnyWordStartingWith(mWordFragment);
                    if(mPossibleLongerWord==null)
                    gameStatus.setText("u win");
                    else
                        gameStatus.setText("comp wins"+mPossibleLongerWord);

                    // mValidWordText.setText("possible word"+mPossibleLongerWord);


                }

            }


        });


        onStart(null);
    }

    @Override
protected void onSaveInstanceState(Bundle outState)
{
    super.onSaveInstanceState(outState);
    //String current_word
    outState.putString("current word", ghostText.getText().toString());
    outState.putString("current status", gameStatus.getText().toString());
   // Toast.makeText(GhostActivity.this,,Toast.LENGTH_LONG).show();

//    super.onSaveInstanceState(outState);
}
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        CharSequence restored_word = savedInstanceState.getCharSequence("current word");
        CharSequence restored_game_status = savedInstanceState.getCharSequence("current status");
     /*   if(restored_word != "")
        {
            Toast.makeText(GhostActivity.this,restored_word,Toast.LENGTH_LONG).show();
        }
*/

        ghostText.setText(restored_word);
        gameStatus.setText(restored_game_status);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ghost, menu);
        return true;
    }
    @Override
    public boolean onKeyUp(int keyCode,KeyEvent event){
        int pressedkey=event.getUnicodeChar();
        char pressedkeychar=(char) pressedkey;
        String pressedchar = ""+pressedkeychar;

       /* if(ghostText.getText().toString().length()>=4&&dictionary.isWord(ghostText.getText().toString()))
        {
            label.setText("com wins");
            return;
        }
       */
        if((pressedkey>=65 && pressedkey<=90) ||(pressedkey>=97 && pressedkey<=122) )
    {
        String user_new = ghostText.getText()+pressedchar;

        Log.d(TAG,"my new word is : " + user_new );
        ghostText.setText(user_new);

        String word=dictionary.getAnyWordStartingWith(user_new);

      //  Log.d(TAG,"my word is : " + word);

        if(word == "")

        {

            Log.d(TAG," NO such word ");
            ghostText.setText("COmp wins");
        }

        // userTurn=false;
       else
       computerTurn();

    }

        return super.onKeyUp(keyCode, event);
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

    private void computerTurn() {
        TextView label = (TextView) findViewById(R.id.gameStatus);
        userTurn=false;

if(ghostText.getText().toString().length()>=4&&dictionary.isWord(ghostText.getText().toString()))
{
    label.setText("com wins");
    return;
}
        String word=dictionary.getGoodWordStartingWith(ghostText.getText().toString());
        Log.d(TAG,"rak"+word);
        if(word=="")
        {
            label.setText("no such word");
        }
        else
        {
            Log.d(TAG,"raksha");
            String new_word= ghostText.getText().toString()+ word.charAt(ghostText.getText().toString().length());
            ghostText.setText(new_word);
        }
        // Do computer turn stuff then make it the user's turn again
        userTurn = true;
        label.setText(USER_TURN);
    }

    /**
     * Handler for the "Reset" button.
     * Randomly determines whether the game starts with a user turn or a computer turn.
     * @param view
     * @return true
     */
    public boolean onStart(View view) {
        userTurn = random.nextBoolean();
        TextView text = (TextView) findViewById(R.id.ghostText);
        text.setText("");
        TextView label = (TextView) findViewById(R.id.gameStatus);
        if (userTurn) {
            label.setText(USER_TURN);

        } else {
            label.setText(COMPUTER_TURN);
            computerTurn();
        }
        return true;
    }
}
