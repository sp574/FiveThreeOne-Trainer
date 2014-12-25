package sleeping_vityaz.fivethreeone_trainer.tabsswipe.adapter;

import sleeping_vityaz.fivethreeone_trainer.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CalendarFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(R.layout.calendar_layout, container, false);

        return rootView;
    }

}
