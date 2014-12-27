package sleeping_vityaz.fivethreeone_trainer.tabsswipe.adapter;

import sleeping_vityaz.fivethreeone_trainer.AddWorkoutActivity;
import sleeping_vityaz.fivethreeone_trainer.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class LogFragment extends Fragment {

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


        return rootView;
    }


}
