package com.example.arjun.bt_signalsender;

import android.app.Activity;
import android.app.DialogFragment;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity implements BtPromptDialog.BtPromptListener {

    final String MYLOGTAG = "BT_SignalSender";
    Intent openBtSettings;


    private BroadcastReceiver btReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if(BluetoothDevice.ACTION_ACL_CONNECTED.equals(action)) {
                Log.v(MYLOGTAG, " BroadcastReceiver: Connected with a bluetooth device");
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                Log.v(MYLOGTAG, " BroadcastReceiver: " + device.getName() + " " + device.getAddress());
            }
        }
    };

    IntentFilter filter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openBtSettings = new Intent();
        openBtSettings.setAction(Settings.ACTION_BLUETOOTH_SETTINGS);

        filter = new IntentFilter(BluetoothDevice.ACTION_ACL_CONNECTED);
        registerReceiver(btReceiver, filter);

        showBtPrompt();
    }


    @Override
    protected void onStart() {
        super.onStart();
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
        } else if( id == R.id.action_pair) {
            Log.v(MYLOGTAG, "MainActivity: onOptionsItemSelected: Pair action selected!");
            showBtPrompt();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    public void showBtPrompt()  {
        DialogFragment dialog = new BtPromptDialog();
        dialog.show(getFragmentManager(), "btprompt");
    }

    /* Implement BtPromptDialog.BtPromptListener */
    @Override
    public void onPositiveClick(DialogFragment dialog) {
        Log.v(MYLOGTAG, "MainActivity: Recording positive click!");

        //open up bluetooth settings as a new intent
        startActivity(openBtSettings);

        Log.v(MYLOGTAG, "MainActivity: Returned after startActivity(openBtIntent)!");
    }

    @Override
    public void onNegativeClick(DialogFragment dialog) {
        Log.v(MYLOGTAG, "MainActivity: Recording negative click!");
    }

}
