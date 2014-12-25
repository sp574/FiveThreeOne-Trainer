package sleeping_vityaz.fivethreeone_trainer.tabsswipe.adapter;

import sleeping_vityaz.fivethreeone_trainer.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by naja-ox on 12/24/14.
 */
public class GraphFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(R.layout.graph_layout, container, false);

        return rootView;
    }

}
