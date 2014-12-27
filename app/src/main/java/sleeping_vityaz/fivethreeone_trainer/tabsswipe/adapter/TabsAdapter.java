package sleeping_vityaz.fivethreeone_trainer.tabsswipe.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class TabsAdapter extends FragmentStatePagerAdapter {

    final private int NUM_TABS = 4;

    public TabsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return new LogFragment();
            case 1: return new CalendarFragment();
            case 2: return new GraphFragment();
            case 3: return new SettingsFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return NUM_TABS;
    }

    @Override
    public CharSequence getPageTitle (int position) {

        switch (position) {
            case 0: return "Log";
            case 1: return "Calendar";
            case 2: return "Graph";
            case 3: return "Settings";
        }
        return null;
    }

}
