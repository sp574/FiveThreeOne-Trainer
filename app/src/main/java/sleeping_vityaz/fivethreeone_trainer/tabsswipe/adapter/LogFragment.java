package sleeping_vityaz.fivethreeone_trainer.tabsswipe.adapter;

import sleeping_vityaz.fivethreeone_trainer.AddWorkoutActivity;
import sleeping_vityaz.fivethreeone_trainer.DBTools;
import sleeping_vityaz.fivethreeone_trainer.exercise.arrayadapter.ExerciseArrayAdapter;
import sleeping_vityaz.fivethreeone_trainer.exercise.object.ExerciseObject;

import sleeping_vityaz.fivethreeone_trainer.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.andexert.expandablelayout.library.ExpandableLayoutListView;


public class LogFragment extends Fragment {

    private static final String DEADLIFT = "deadlift";
    private static final String BENCH = "bench";
    private static final String SQUAT = "squat";
    private static final String PRESS = "press";

    // Table Names
    private static final String MAIN_E = "main_e_table";
    private static final String ASSISTANCE = "assistance_table";
    private static final String RM_LOG = "repmax_table";


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(R.layout.log_layout, container, false);
        DBTools dbTools = DBTools.getInstance(this.getActivity());
        Button bt_squats, bt_bench, bt_deadlifts, bt_press;

        bt_squats = (Button) rootView.findViewById(R.id.bt_squats);
        bt_bench = (Button) rootView.findViewById(R.id.bt_bench);
        bt_deadlifts = (Button) rootView.findViewById(R.id.bt_deadlifts);
        bt_press = (Button) rootView.findViewById(R.id.bt_press);

        // open exercise activity: squats, bench, deadlifts, press
        bt_squats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddWorkoutActivity.class);
                intent.putExtra("workout_type", SQUAT);
                startActivity(intent);
            }
        });

        bt_bench.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddWorkoutActivity.class);
                intent.putExtra("workout_type", BENCH);
                startActivity(intent);
            }
        });

        bt_deadlifts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddWorkoutActivity.class);
                intent.putExtra("workout_type", DEADLIFT);
                startActivity(intent);
            }
        });

        bt_press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddWorkoutActivity.class);
                intent.putExtra("workout_type", PRESS);
                startActivity(intent);
            }
        });


        List<ExerciseObject> exercise_list = dbTools.getExerciseInfo("", MAIN_E);


        if(exercise_list.size() != 0){

            ListView listView = (ListView) rootView.findViewById(R.id.exercise_listview);
           /* listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    movieId = (TextView) view.findViewById(R.id.movieId);
                    String movieIdValue = movieId.getText().toString();
                    Intent theIntent = new Intent(getApplication(), EditMovie.class);
                    theIntent.putExtra("movieId", movieIdValue);
                    startActivity(theIntent);
                    finish();
                }
            });*/

            ExerciseArrayAdapter adapter = new ExerciseArrayAdapter(this.getActivity(), exercise_list);

            listView.setAdapter(adapter);
            /*--------------------------------------------------------
            final String[] array = {"Hello", "World"};
            List arrayList2 = dbTools.getExerciseInfo("", MAIN_E);

            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this.getActivity(), R.layout.view_row, R.id.header_text, array);

            final ExerciseArrayAdapter arrayAdapter2 = new ExerciseArrayAdapter(this.getActivity(), R.layout.view_row, R.id.header_text, arrayList2);

            final ExpandableLayoutListView expandableLayoutListView = (ExpandableLayoutListView) rootView.findViewById(R.id.listview);

            expandableLayoutListView.setAdapter(arrayAdapter2);*/

       }


        return rootView;
    }


}
