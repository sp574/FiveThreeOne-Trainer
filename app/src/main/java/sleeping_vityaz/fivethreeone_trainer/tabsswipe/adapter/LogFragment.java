package sleeping_vityaz.fivethreeone_trainer.tabsswipe.adapter;

import sleeping_vityaz.fivethreeone_trainer.AddWorkoutActivity;
import sleeping_vityaz.fivethreeone_trainer.DBTools;
import sleeping_vityaz.fivethreeone_trainer.exercise.object.ExerciseObject;

import sleeping_vityaz.fivethreeone_trainer.R;
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

import java.util.List;
import com.andexert.expandablelayout.library.ExpandableLayoutListView;

public class LogFragment extends Fragment {

    // Table Names
    private static final String DEADLIFT = "deadlift";
    private static final String BENCH = "bench";
    private static final String SQUAT = "squat";
    private static final String PRESS = "press";
    private static final String ASSISTANCE = "assistance";

    DBTools dbTools = new DBTools(this.getActivity());

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(R.layout.log_layout, container, false);

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
                intent.putExtra("workout_type", "squats");
                startActivity(intent);
            }
        });

        bt_bench.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddWorkoutActivity.class);
                intent.putExtra("workout_type", "bench");
                startActivity(intent);
            }
        });

        bt_deadlifts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddWorkoutActivity.class);
                intent.putExtra("workout_type", "deadlifts");
                startActivity(intent);
            }
        });

        bt_press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddWorkoutActivity.class);
                intent.putExtra("workout_type", "press");
                startActivity(intent);
            }
        });


        //List<ExerciseObject> exercise_list = dbTools.getExerciseInfo("", DEADLIFT);
        //exercise_list.addAll(dbTools.getExerciseInfo("", BENCH));
        //exercise_list.addAll(dbTools.getExerciseInfo("", SQUAT));
        //exercise_list.addAll(dbTools.getExerciseInfo("", PRESS));

       // if(exercise_list.size() != 0){

//            ListView listView = (ListView) rootView.getRootView();
            /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
/*
            ListAdapter adapter = new SimpleAdapter(this.getActivity(), exercise_list ,R.layout.exercise_entry,
                    new String[] {"exerciseID", "week", "cycle", "m_exercise"},
                    new int[] {R.id.exerciseID, R.id.week, R.id. cycle, R.id.m_exercise});

            setListAdapter(adapter);*/
            final String[] array = {"Hello", "World"};
            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this.getActivity(), R.layout.view_row, R.id.header_text, array);
            final ExpandableLayoutListView expandableLayoutListView = (ExpandableLayoutListView) rootView.findViewById(R.id.listview);

            expandableLayoutListView.setAdapter(arrayAdapter);

  //      }


        return rootView;
    }


}
