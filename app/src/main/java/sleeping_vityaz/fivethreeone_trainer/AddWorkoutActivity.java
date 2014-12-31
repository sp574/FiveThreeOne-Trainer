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


    private static final String DEADLIFT = "deadlift";
    private static final String BENCH = "bench";
    private static final String SQUAT = "squat";
    private static final String PRESS = "press";

    // Table Names
    private static final String MAIN_E = "main_e_table";
    private static final String ASSISTANCE = "assistance_table";
    private static final String RM_LOG = "repmax_table";



    // Common column names
    private static final String KEY_ID = "col_exerciseID";
    private static final String WEEK = "col_week_num";
    private static final String CYCLE = "col_cycle_num";
    private static final String WEIGHT = "col_weight_num";
    private static final String DATE_CREATED = "col_date_created";
    private static final String NOTES = "col_notes";
    private static final String A_EXERCISE = "col_a_exercise";
    private static final String MAIN_EXERCISE = "col_m_exercise";


    String workout_type;
    DBTools dbTools = new DBTools(this);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addworkout_layout);

        Intent logFragmentIntent = getIntent();

        workout_type = logFragmentIntent.getStringExtra("workout_type");

        setUpLayout(workout_type);


    }

    private void setUpLayout(String workout_type){



        double oneRepMax = dbTools.getExerciseRepMax(workout_type);
        System.out.println(workout_type+": "+oneRepMax);

        // cycle, week
        int[] e_info = dbTools.getLastCycleWeek(workout_type);
        e_info[0]++; //next cycle to record to the table
        e_info[1]++; //next week to record to the table

        tv_warmupex = (TextView) findViewById(R.id.tv_warmupex);
        tv_mainex = (TextView) findViewById(R.id.tv_mainex);
        bt_warmupex_1 = (Button) findViewById(R.id.bt_warmupex_1);
        bt_warmupex_2 = (Button) findViewById(R.id.bt_warmupex_2);
        bt_warmupex_3 = (Button) findViewById(R.id.bt_warmupex_3);
        bt_mainex_1 = (Button) findViewById(R.id.bt_mainex_1);
        bt_mainex_2 = (Button) findViewById(R.id.bt_mainex_2);
        bt_mainex_3 = (Button) findViewById(R.id.bt_mainex_3);

        bt_mainex_1.setText(getNumber(e_info, oneRepMax, 4));
        bt_mainex_2.setText(getNumber(e_info, oneRepMax, 5));
        bt_mainex_3.setText(getNumber(e_info, oneRepMax, 6));

    }

    // setNum 1-3: warmup, 4-6: working
    private String getNumber(int[] e_info, double oneRepMax, int setNum) {
        // week 0,4,8,12...
       if (e_info[1]%4==0 && e_info[1]!=0){
            switch(setNum) {
                case 1: return "";
                case 2: return "";
                case 3: return "";
                case 4: return (""+Calculations.getWeightWeekFourSetOne(oneRepMax));
                case 5: return (""+Calculations.getWeightWeekFourSetTwo(oneRepMax));
                case 6: return (""+Calculations.getWeightWeekFourSetThree(oneRepMax));
            }
        }
        else if (e_info[1]%4==1) {
            switch(setNum) {
                case 1: return "";
                case 2: return "";
                case 3: return "";
                case 4: return (""+Calculations.getWeightWeekOneSetOne(oneRepMax));
                case 5: return (""+Calculations.getWeightWeekOneSetTwo(oneRepMax));
                case 6: return (""+Calculations.getWeightWeekOneSetThree(oneRepMax));
            }
        }
        else if (e_info[1]%4==2){
            switch(setNum) {
                case 1: return "";
                case 2: return "";
                case 3: return "";
                case 4: return (""+Calculations.getWeightWeekTwoSetOne(oneRepMax));
                case 5: return (""+Calculations.getWeightWeekTwoSetTwo(oneRepMax));
                case 6: return (""+Calculations.getWeightWeekTwoSetThree(oneRepMax));
            }
        }
        else if (e_info[1]%4==3){
            switch(setNum) {
                case 1: return "";
                case 2: return "";
                case 3: return "";
                case 4: return (""+Calculations.getWeightWeekThreeSetOne(oneRepMax));
                case 5: return (""+Calculations.getWeightWeekThreeSetTwo(oneRepMax));
                case 6: return (""+Calculations.getWeightWeekThreeSetThree(oneRepMax));
            }
        }


        return null;
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
            queryValuesMap.put("table", MAIN_E);
            queryValuesMap.put(WEEK, "1"); // get latest cycle/week for this exercise and +1 to add to the table
            queryValuesMap.put(CYCLE, "cycle");
            queryValuesMap.put(WEIGHT, "weight");
            queryValuesMap.put(DATE_CREATED, "12-12-12");
            queryValuesMap.put(NOTES, "notes");
            //queryValuesMap.put(A_EXERCISE, "assistance");
            queryValuesMap.put(MAIN_EXERCISE, workout_type);

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

    //remove this once done making the app. For testing purposes only.
    public void populateTables(){

        // just testing adding/getting back 1RM
        HashMap<String, String> queryValues = new HashMap<String, String>();

        //check if there are any assistance exercises
        // loop through all exercises
        // while (exercises != empty)
        queryValues.put("table", RM_LOG);
        queryValues.put(WEIGHT, "415.0");
        queryValues.put(DATE_CREATED, "11-15-2014");
        queryValues.put(MAIN_EXERCISE, DEADLIFT);
        dbTools.insertExerciseRepMax(queryValues);

        queryValues.put("table", RM_LOG);
        queryValues.put(WEIGHT, "310.0");
        queryValues.put(DATE_CREATED, "11-16-2014");
        queryValues.put(MAIN_EXERCISE, SQUAT);
        dbTools.insertExerciseRepMax(queryValues);

        queryValues.put("table", RM_LOG);
        queryValues.put(WEIGHT, "225.0");
        queryValues.put(DATE_CREATED, "11-17-2014");
        queryValues.put(MAIN_EXERCISE, BENCH);
        dbTools.insertExerciseRepMax(queryValues);

        queryValues.put("table", RM_LOG);
        queryValues.put(WEIGHT, "155.0");
        queryValues.put(DATE_CREATED, "11-18-2014");
        queryValues.put(MAIN_EXERCISE, PRESS);
        dbTools.insertExerciseRepMax(queryValues);
        // ---------------


        /*HashMap<String, String> queryValuesMap = new HashMap<String, String>();

        //check if there are any assistance exercises
        // loop through all exercises
        // while (exercises != empty)
        queryValuesMap.put("table", MAIN_E);
        queryValuesMap.put(WEEK, "1");
        queryValuesMap.put(CYCLE, "1");
        queryValuesMap.put(WEIGHT, "120,150,160,165,175,185");
        queryValuesMap.put(DATE_CREATED, "12-12-12");
        queryValuesMap.put(NOTES, "notes");
        //queryValuesMap.put(A_EXERCISE, "assistance");
        queryValuesMap.put(MAIN_EXERCISE, DEADLIFT);
        dbTools.insertExercise(queryValuesMap);

        queryValuesMap.put("table", MAIN_E);
        queryValuesMap.put(WEEK, "1");
        queryValuesMap.put(CYCLE, "1");
        queryValuesMap.put(WEIGHT, "150,160,170,185,190,195");
        queryValuesMap.put(DATE_CREATED, "12-13-12");
        queryValuesMap.put(NOTES, "notes");
        //queryValuesMap.put(A_EXERCISE, "assistance");
        queryValuesMap.put(MAIN_EXERCISE, BENCH);
        dbTools.insertExercise(queryValuesMap);


        queryValuesMap.put("table", MAIN_E);
        queryValuesMap.put(WEEK, "1");
        queryValuesMap.put(CYCLE, "1");
        queryValuesMap.put(WEIGHT, "180,185,185,195,200,225");
        queryValuesMap.put(DATE_CREATED, "12-14-12");
        queryValuesMap.put(NOTES, "notes");
        //queryValuesMap.put(A_EXERCISE, "assistance");
        queryValuesMap.put(MAIN_EXERCISE, SQUAT);
        dbTools.insertExercise(queryValuesMap);

        queryValuesMap.put("table", MAIN_E);
        queryValuesMap.put(WEEK, "1");
        queryValuesMap.put(CYCLE, "1");
        queryValuesMap.put(WEIGHT, "95,100,105,115,120,135");
        queryValuesMap.put(DATE_CREATED, "12-15-12");
        queryValuesMap.put(NOTES, "notes");
        //queryValuesMap.put(A_EXERCISE, "assistance");
        queryValuesMap.put(MAIN_EXERCISE, PRESS);
        dbTools.insertExercise(queryValuesMap);
*/
    }

}

