package sleeping_vityaz.fivethreeone_trainer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;


public class AddWorkoutActivity extends Activity {

    boolean status_wu_1 = false,
            status_wu_2 = false,
            status_wu_3 = false,
            status_m_1 = false,
            status_m_2 = false,
            status_m_3 = false;
    TextView tv_warmupex, tv_mainex;
    Button bt_warmupex_1, bt_warmupex_2, bt_warmupex_3, bt_mainex_1, bt_mainex_2, bt_mainex_3;

    // Table Names
    private static final String DEADLIFT = "deadlift";
    private static final String BENCH = "bench";
    private static final String SQUAT = "squat";
    private static final String PRESS = "press";
    private static final String ASSISTANCE = "assistance";

    // Common column names
    private static final String KEY_ID = "exerciseID";
    private static final String WEEK = "week_num";
    private static final String CYCLE = "cycle_num";
    private static final String WEIGHT = "weight_num";
    private static final String DATE_CREATED = "date_created";
    private static final String NOTES = "notes";
    private static final String A_EXERCISE = "a_exercise";
    private static final String MAIN_EXERCISE = "m_exercise";


    String exercise_type;
    DBTools dbTools = new DBTools(this);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addworkout_layout);

        Intent logFragmentIntent = getIntent();

        exercise_type = logFragmentIntent.getStringExtra("workout_type");

        tv_warmupex = (TextView) findViewById(R.id.tv_warmupex);
        tv_mainex = (TextView) findViewById(R.id.tv_mainex);
        bt_warmupex_1 = (Button) findViewById(R.id.bt_warmupex_1);
        bt_warmupex_2 = (Button) findViewById(R.id.bt_warmupex_2);
        bt_warmupex_3 = (Button) findViewById(R.id.bt_warmupex_3);
        bt_mainex_1 = (Button) findViewById(R.id.bt_mainex_1);
        bt_mainex_2 = (Button) findViewById(R.id.bt_mainex_2);
        bt_mainex_3 = (Button) findViewById(R.id.bt_mainex_3);

    }

    public void warmupOneOnClick(View view) {
        if (!status_wu_1) // not clicked or clicked even number of times
        {
            status_wu_1 = true;
            bt_warmupex_1.setBackgroundColor(0xff00ff00);

        } else {
            status_wu_1 = false;
            bt_warmupex_1.setBackgroundColor(0xffcccccc); // button clicked once/odd number
        }
    }

    public void warmupTwoOnClick(View view) {
        if (!status_wu_2) // not clicked or clicked even number of times
        {
            status_wu_2 = true;
            bt_warmupex_2.setBackgroundColor(0xff00ff00);

        } else {
            status_wu_2 = false;
            bt_warmupex_2.setBackgroundColor(0xffcccccc); // button clicked once/odd number
        }
    }

    public void warmupThreeOnClick(View view) {
        if (!status_wu_3) // not clicked or clicked even number of times
        {
            status_wu_3 = true;
            bt_warmupex_3.setBackgroundColor(0xff00ff00);

        } else {
            status_wu_3 = false;
            bt_warmupex_3.setBackgroundColor(0xffcccccc); // button clicked once/odd number
        }
    }

    public void mainOneOnClick(View view) {
        if (!status_m_1) // not clicked or clicked even number of times
        {
            status_m_1 = true;
            bt_mainex_1.setBackgroundColor(0xff00ff00);

        } else {
            status_m_1 = false;
            bt_mainex_1.setBackgroundColor(0xffcccccc); // button clicked once/odd number
        }
    }

    public void mainTwoOnClick(View view) {
        if (!status_m_2) // not clicked or clicked even number of times
        {
            status_m_2 = true;
            bt_mainex_2.setBackgroundColor(0xff00ff00);

        } else {
            status_m_2 = false;
            bt_mainex_2.setBackgroundColor(0xffcccccc); // button clicked once/odd number
        }
    }

    public void mainThreeOnClick(View view) {
        if (!status_m_3) // not clicked or clicked even number of times
        {
            status_m_3 = true;
            bt_mainex_3.setBackgroundColor(0xff00ff00);

        } else {
            status_m_3 = false;
            bt_mainex_3.setBackgroundColor(0xffcccccc); // button clicked once/odd number
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.addworkout_menu, menu);
        return true;
    }

    public void completeWorkout(MenuItem item) {

        // add only iff all buttons are clicked (warmup and main)

        if (status_wu_1&&status_wu_2&&status_wu_3&&status_m_1&&status_m_2&&status_m_3) {
            // add workout to database

            HashMap<String, String> queryValuesMap = new HashMap<String, String>();

            //check if there are any assistance exercises
            // loop through all exercises
            // while (exercises != empty)
            queryValuesMap.put("table", DEADLIFT);
            queryValuesMap.put(WEEK, "week");
            queryValuesMap.put(CYCLE, "cycle");
            queryValuesMap.put(WEIGHT, "weight");
            queryValuesMap.put(DATE_CREATED, "12-12-12");
            queryValuesMap.put(NOTES, "notes");
            //queryValuesMap.put(A_EXERCISE, "assistance");
            //queryValuesMap.put(MAIN_EXERCISE, "main exercise");

            dbTools.insertExercise(queryValuesMap);

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            // display a toast confirming workout submission
            // show UNDO button
        }else{
            /*if not all buttons are clicked, show a toast
              telling user to click all buttons to complete workout
            */
        }
    }
}
/*

week_num";
private static final String CYCLE = "cycle_num";
private static final String WEIGHT = "weight_num";
private static final String DATE_CREATED = "date_created";
private static final String NOTES = "notes";
private static final String A_EXERCISE = "a_exercise";
private static final String MAIN_EXERCISE = "m_exercise";*/
