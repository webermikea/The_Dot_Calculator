package info.eruditesoftware.TheDotCalculator;
//test

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import java.util.Random;

public class The_Dot_Calculator extends Activity implements OnClickListener {

    private GameArena canvas;
    RelativeLayout graphicsArea;
    Button dotTrait1, encTrait1, runEnc, dotTrait3, encTrait3;
    ProgressBar[][] traits = new ProgressBar[3][5];
    Random randPos = new Random();
    public static final int MAX_TRAIT = 10;

    // Startup procedure, links visible elements to Java elements
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_the__dot__calculator);

        // Create the frame layout and canvas for graphics
        canvas = new GameArena(this); // Circle
        graphicsArea = (RelativeLayout) findViewById(R.id.graphicsArea);
        graphicsArea.addView(canvas);

        // Set up the buttons
        dotTrait1 = (Button) findViewById(R.id.buttonDotTrait1Inc);
        dotTrait1.setOnClickListener(this);
        encTrait1 = (Button) findViewById(R.id.buttonEncTrait1Inc);
        encTrait1.setOnClickListener(this);
        dotTrait3 = (Button) findViewById(R.id.buttonDotTrait3Inc);
        dotTrait3.setOnClickListener(this);
        encTrait3 = (Button) findViewById(R.id.buttonEncTrait3Inc);
        encTrait3.setOnClickListener(this);
        runEnc = (Button) findViewById(R.id.buttonRunEnc);
        runEnc.setOnClickListener(this);

        // Set up the progress bars
        traits[1][1] = (ProgressBar) findViewById(R.id.barDot1);
        traits[1][2] = (ProgressBar) findViewById(R.id.barDot2);
        traits[1][3] = (ProgressBar) findViewById(R.id.barDot3);
        traits[1][4] = (ProgressBar) findViewById(R.id.barDot4);
        traits[2][1] = (ProgressBar) findViewById(R.id.barEnc1);
        traits[2][2] = (ProgressBar) findViewById(R.id.barEnc2);
        traits[2][3] = (ProgressBar) findViewById(R.id.barEnc3);
        traits[2][4] = (ProgressBar) findViewById(R.id.barEnc4);

        // Set up text field
        //textField = (EditText) findViewById(R.id.editText1);

        // Initialize the traits to starting values
        for (int i = 1; i <= 2; i++) {
            for (int j = 1; j <= 4; j++) {
                traits[i][j].setMax(MAX_TRAIT); // Must set max for all 4 traits
                traits[i][j].setProgress(MAX_TRAIT / 2);
            }
        }
        changeTrait(); // This is redundant, but is here in case we add rules, etc.

    } // public void onCreate()

    public void onClick(View v) {
        String logResults;

        switch (v.getId()) {
            case R.id.buttonDotTrait1Inc: // Redistribute Dot traits randomly
                logResults = increaseTrait(1, 1);
                break;
            case R.id.buttonEncTrait1Inc: // Redistribute Encounter traits randomly
                logResults = increaseTrait(2, 1);
                break;
            case R.id.buttonDotTrait3Inc: // Change alpha manually
                logResults = increaseTrait(1, 3);
                break;
            case R.id.buttonEncTrait3Inc: // Change alpha manually
                logResults = increaseTrait(2, 3);
                break;
            case R.id.buttonRunEnc: // Run the encounter routine
                logResults = runEncounter();
                break;
            default:
                logResults = "Nothing done";
                break;
        } // switch (v.getId())

        Log.i("dot", logResults);
    } // public void onClick(View v)

    public String increaseTrait(int circleNum, int trait) {
        int value = traits[circleNum][trait].getProgress();
        value++;
        value %= MAX_TRAIT;
        changeTrait(circleNum, trait, value);
        return "Trait " + trait + " increased.";
    }

    public void changeTrait(int i, int j, int value) {
        // Saturate value to between 0 and the maximum trait value
        if (value > MAX_TRAIT) {
            value = MAX_TRAIT;
        } else if (value < 0) {
            value = 0;
        }

        // If i or j are outside valid range then just fix the 2 and 4 traits
        if ((i >= 1) && (i <= 2) && (j >= 1) && (j <= 4)) {
            // A trait must be updated - so update 1 or 3 appropriately
            if (j % 2 == 0) {
                j--;
                value = MAX_TRAIT - value;
            }
            traits[i][j].setProgress(value);
        }

        // Fix the 2 and 4 to match
        for (i = 1; i <= 2; i++)
            for (j = 1; j <= 4; j += 2) {
                traits[i][j + 1].setProgress(MAX_TRAIT - traits[i][j].getProgress());
            }

        updateCirclesFromTraits();
        updateDisplay();
    }

    // Overload
    public void changeTrait() {
        changeTrait(0, 0, 0);
    }

    public void updateCirclesFromTraits() {
        canvas.setColors(traits[1][1].getProgress(), traits[1][3].getProgress(), traits[2][1].getProgress(), traits[2][3].getProgress());
        canvas.setLocation(traits[1][4].getProgress(), traits[2][4].getProgress());
        canvas.setAngle(traits[1][2].getProgress(), traits[2][2].getProgress());
    }

    public void createNextAnimationFrame() {

    }

    public void updateDisplay() {
        canvas.invalidate();
    }

    // Evaluate the encounter between The Dot and Encounter
    public String runEncounter() {
        return originalRules();
    } // public String runEncounter()

    // Original rules using personality traits, 1&3 sympathetic and 2&4 competitive
    public String originalRules() {
        String response = "The Dot did not win...";
        int winningTrait = 0;
        int lowestWinnerValue = MAX_TRAIT;
        int half = MAX_TRAIT / 2;
        boolean results_1_3 = false, results_2_4 = false;

        // Compare to see if trait 1 or 3 are >= halfway for both participants
        if ((traits[1][1].getProgress() >= half) && (traits[2][1].getProgress() >= half)) {
            results_1_3 = true;
            winningTrait = 1;
            lowestWinnerValue = traits[1][1].getProgress();
        }
        if ((traits[1][3].getProgress() >= half) && (traits[2][3].getProgress() >= half)) {
            results_1_3 = true;
            if (lowestWinnerValue > traits[1][3].getProgress()) {
                winningTrait = 3;
                lowestWinnerValue = traits[1][3].getProgress();
            }
        }

        // Compare to see if the higher of trait 2 or 4 are higher for our participant
        if (Math.max(traits[1][2].getProgress(), traits[2][2].getProgress()) >= Math.max(traits[1][4].getProgress(), traits[2][4].getProgress())) {
            if (traits[1][2].getProgress() >= traits[2][2].getProgress()) {
                results_2_4 = true;
                if (lowestWinnerValue > traits[1][2].getProgress()) {
                    winningTrait = 2;
                    lowestWinnerValue = traits[1][2].getProgress();
                }
            }
        } else {
            if (traits[1][4].getProgress() >= traits[2][4].getProgress()) {
                results_2_4 = true;
                if (lowestWinnerValue > traits[1][4].getProgress()) {
                    winningTrait = 4;
                    lowestWinnerValue = traits[1][4].getProgress();
                }
            }
        }

        // IF(MAX(D1,D3,E1,E3)>=MAX(D2,D4,E2,E4),Result1&3,Result2&4)
        if (Math.max(Math.max(traits[1][1].getProgress(), traits[1][3].getProgress()), Math.max(traits[2][1].getProgress(), traits[2][3].getProgress())) >= Math.max(Math.max(traits[1][2].getProgress(), traits[1][4].getProgress()), Math.max(traits[2][2].getProgress(), traits[2][4].getProgress()))) {
            if (results_1_3) {
                response = "The Dot wins with soft skills! " + winningTrait + " - " + lowestWinnerValue;
                // Here we will raise the lowest winning stat by 1
                changeTrait(1, winningTrait, lowestWinnerValue + 1);
            } else {
                // Lower the highest stat of 1 and 3
                if (traits[1][1].getProgress() >= traits[1][3].getProgress()) {
                    changeTrait(1, 1, traits[1][1].getProgress() - 1);
                } else {
                    changeTrait(1, 3, traits[1][3].getProgress() - 1);
                }
            }
        } else {
            if (results_2_4) {
                response = "The Dot wins with hard skills! " + winningTrait + " - " + lowestWinnerValue;
                // Here we will raise the lowest winning stat by 1
                changeTrait(1, winningTrait, lowestWinnerValue + 1);
            } else {
                // Lower the highest stat of 2 and 4
                if (traits[1][2].getProgress() >= traits[1][4].getProgress()) {
                    changeTrait(1, 2, traits[1][2].getProgress() - 1);
                } else {
                    changeTrait(1, 4, traits[1][4].getProgress() - 1);
                }
            }
        }
        return response;
    } // public String originalRules()

} // public class The_Dot_Calculator

