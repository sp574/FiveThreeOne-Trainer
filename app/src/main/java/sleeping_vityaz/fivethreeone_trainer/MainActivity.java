package sleeping_vityaz.fivethreeone_trainer;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.astuetz.PagerSlidingTabStrip;


import java.util.HashMap;

import sleeping_vityaz.fivethreeone_trainer.tabsswipe.adapter.TabsAdapter;
import sleeping_vityaz.fivethreeone_trainer.AddWorkoutActivity;


public class MainActivity extends FragmentActivity {

    private TabsAdapter tabsAdapter;
    private ViewPager viewPager;

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

    DBTools dbTools = new DBTools(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabsAdapter = new TabsAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(tabsAdapter);

        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);

        tabs.setViewPager(viewPager);
        populateTables();

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
