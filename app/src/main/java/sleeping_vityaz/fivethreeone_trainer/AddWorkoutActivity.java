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


public class AddWorkoutActivity extends Activity {

    boolean status_wu_1 = false,
            status_wu_2 = false,
            status_wu_3 = false,
            status_m_1 = false,
            status_m_2 = false,
            status_m_3 = false;
    TextView tv_warmupex, tv_mainex;
    Button bt_warmupex_1, bt_warmupex_2, bt_warmupex_3, bt_mainex_1, bt_mainex_2, bt_mainex_3;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addworkout_layout);

        tv_warmupex = (TextView) findViewById(R.id.tv_warmupex);
        tv_mainex = (TextView) findViewById(R.id.tv_mainex);
        bt_warmupex_1 = (Button) findViewById(R.id.bt_warmupex_1);
        bt_warmupex_2 = (Button) findViewById(R.id.bt_warmupex_2);
        bt_warmupex_3 = (Button) findViewById(R.id.bt_warmupex_3);
        bt_mainex_1 = (Button) findViewById(R.id.bt_mainex_1);
        bt_mainex_2 = (Button) findViewById(R.id.bt_mainex_2);
        bt_mainex_3 = (Button) findViewById(R.id.bt_mainex_3);

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
        // add workout to database

        // return to main activity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        // display a toast confirming workout submission
    }
}

