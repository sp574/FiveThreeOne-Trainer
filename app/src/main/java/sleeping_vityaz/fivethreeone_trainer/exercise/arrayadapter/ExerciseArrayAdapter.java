package sleeping_vityaz.fivethreeone_trainer.exercise.arrayadapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import sleeping_vityaz.fivethreeone_trainer.R;
import java.util.ArrayList;
import java.util.List;

import sleeping_vityaz.fivethreeone_trainer.exercise.object.ExerciseObject;

/**
 * Created by naja-ox on 12/30/14.
 */
public class ExerciseArrayAdapter extends ArrayAdapter<ExerciseObject> {


    private LayoutInflater inflater;
    private List<ExerciseObject> exerciseObjectList;

    public ExerciseArrayAdapter(Context context, List<ExerciseObject> exerciseObjectList) {
        super(context, 0, exerciseObjectList);
    }


    @Override
    public ExerciseObject getItem(int location) {
        return exerciseObjectList.get(location);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.exercise_entry, parent, false);
        }

        TextView exerciseID = (TextView) convertView.findViewById(R.id.exerciseID);
        TextView week = (TextView) convertView.findViewById(R.id.week);
        TextView cycle = (TextView) convertView.findViewById(R.id.cycle);
        TextView m_exercise = (TextView) convertView.findViewById(R.id.m_exercise);

        // getting movie data for the row
//        ExerciseObject m = exerciseObjectList.get(position);

        // title
        //title.setText(m.getTitle());

        // rating
        //rating.setText("Rating: " + String.valueOf(m.getRating()));

        // genre
       /* String genreStr = "";
        for (String str : m.getGenre()) {
            genreStr += str + ", ";
        }
        genreStr = genreStr.length() > 0 ? genreStr.substring(0,
                genreStr.length() - 2) : genreStr;
        genre.setText(genreStr);

        // release year
        year.setText(String.valueOf(m.getYear()));
*/
        return convertView;
    }



}
