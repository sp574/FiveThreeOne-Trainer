package sleeping_vityaz.fivethreeone_trainer.tabsswipe.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sleeping_vityaz.fivethreeone_trainer.R;

public class SettingsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(R.layout.settings_layout, container, false);

        return rootView;
    }
}
