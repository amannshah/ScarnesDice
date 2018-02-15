package aman.com.scarnesdice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int computerTurn=0;
    int turnScore = 0;
    TextView textView1,textView2;
    ImageView imageView;
    Button rollButton,holdButton,resetButton,playAgainButton;
    int playerScore=0,computerScore=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playAgainButton = (Button)findViewById(R.id.playAgainButton);
        rollButton = (Button)findViewById(R.id.rollButton);
        holdButton = (Button)findViewById(R.id.holdButton);
        resetButton = (Button)findViewById(R.id.resetButton);
        textView1 = (TextView)findViewById(R.id.textView1);
        textView2 = (TextView)findViewById(R.id.textView2);
        imageView = (ImageView)findViewById(R.id.imageView);
    }

    public void holdButton (View view) {
        playerScore += turnScore;
        if (playerScore > 100) {
            Toast.makeText(getApplicationContext(),"Computer Wins",Toast.LENGTH_LONG).show();
            playAgainButton.setVisibility(View.VISIBLE);
            resetButton.setEnabled(false);
            holdButton.setEnabled(false);
            rollButton.setEnabled(false);
        } else {
            textView1.setText("Your Score: " + playerScore + " Computer Score: " + computerScore);
            turnScore = 0;
            computerPlays();
        }
    }

    public void resetButton (View view) {
        imageView.setImageResource(R.drawable.dice1);
        imageView.setTranslationX(-1000f);
        imageView.animate().translationXBy(1000f).setDuration(100);
        turnScore=computerScore=playerScore=computerTurn=0;
        textView1.setText("Your Score: " + playerScore + " Computer Score: " + computerScore);
    }

    public void rollButton (View view) {

        Random random = new Random();
        int current = random.nextInt(6) + 1;
        if (current == 1) {
            imageView.setImageResource(R.drawable.dice1);
            imageView.setTranslationX(-1000f);
            imageView.animate().translationXBy(1000f).setDuration(100);
            turnScore = 0;
            computerPlays();
        } else {
            switch (current)
            {
                case 2: imageView.setImageResource(R.drawable.dice2);
                    imageView.setTranslationX(-1000f);
                    imageView.animate().translationXBy(1000f).setDuration(100);
                    break;
                case 3: imageView.setImageResource(R.drawable.dice3);
                    imageView.setTranslationX(-1000f);
                    imageView.animate().translationXBy(1000f).setDuration(100);
                    break;
                case 4: imageView.setImageResource(R.drawable.dice4);
                    imageView.setTranslationX(-1000f);
                    imageView.animate().translationXBy(1000f).setDuration(100);
                    break;
                case 5: imageView.setImageResource(R.drawable.dice5);
                    imageView.setTranslationX(-1000f);
                    imageView.animate().translationXBy(1000f).setDuration(100);
                    break;
                case 6: imageView.setImageResource(R.drawable.dice6);
                    imageView.setTranslationX(-1000f);
                    imageView.animate().translationXBy(1000f).setDuration(100);
                    break;
            }
            turnScore += current;
            textView2.setText("Turn's Score: " + turnScore);
        }
    }

    public void computerPlays () {
        int compCurrent;
        int keepTurn=1;boolean passTurn = false;
        Random random = new Random();
        compCurrent = random.nextInt(6) + 1;
        Log.i("Computer 1 score: ",Integer.toString(compCurrent));
        computerTurn += compCurrent;
        textView2.setText("Computer turn score: " + computerTurn);
        if (compCurrent == 1) {
            computerTurn = 0;
            textView1.setText("Your Score: " + playerScore + " Computer Score: " + computerScore);
            Toast.makeText(getApplicationContext(),"Computer plays a 1, Its your turn",Toast.LENGTH_LONG).show();
            textView2.setText("It's your turn");
            passTurn = true;
        }
        if (compCurrent != 1) {
            Random random1 = new Random();
            keepTurn = random1.nextInt(10) + 1;
            Log.i("Keep turn score",Integer.toString(keepTurn));
            if (keepTurn % 3 != 0) {
                computerPlays();
            }
            passTurn = true;
            Toast.makeText(getApplicationContext(),"Computer passes",Toast.LENGTH_LONG).show();
        }
        if (compCurrent != 1  && keepTurn % 3 == 0 && passTurn == true) {
            computerScore += computerTurn;
            textView1.setText("Your Score: " + playerScore + " Computer Score: " + computerScore);
            textView2.setText("It's your turn");
            if (computerScore > 100) {
                Toast.makeText(getApplicationContext(),"Computer Wins",Toast.LENGTH_LONG).show();
                playAgainButton.setVisibility(View.VISIBLE);
                resetButton.setEnabled(false);
                holdButton.setEnabled(false);
                rollButton.setEnabled(false);
            }
        }
    }
    public void playAgain (View view) {
        resetButton(view);
        playAgainButton.setVisibility(View.INVISIBLE);
        resetButton.setEnabled(true);
        holdButton.setEnabled(true);
        rollButton.setEnabled(true);
    }
}
