package com.example.arjun.bt_signalsender;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    final String MYLOGTAG = "BT_SignalSender";
    Button genericButton; boolean genericButtonStatus;


    Fragment genericFragment = new GenericSenderFragment();
    FragmentManager fragmentManager = getFragmentManager();
    FragmentTransaction transaction = getFragmentManager().beginTransaction();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        genericButton = (Button) findViewById(R.id.gen_button);

        genericButtonStatus = false;


        genericButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Log.v(MYLOGTAG, "Button Clicked!");

                if(!genericButtonStatus)    {
                    transaction.add(R.id.fragment, genericFragment);
                    transaction.commit();
                    genericButtonStatus = true;
                }

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
