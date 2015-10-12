package com.example.arjun.bt_signalsender;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by arjun on 11/10/15.
 */
public class BtPromptDialog extends DialogFragment {
    final String MYLOGTAG = "BT_SignalSender";

    public interface BtPromptListener   {
        public void onPositiveClick(DialogFragment dialog);
        public void onNegativeClick(DialogFragment dialog);
    }

    BtPromptListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mListener = (BtPromptListener) activity;
        } catch (ClassCastException E)  {
            Log.v(MYLOGTAG, "BtPromptListener: ClassCaseException");

            throw new ClassCastException(activity.toString()
                    + " must implement BtPromptListener");
        }
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Log.v(MYLOGTAG, "BtPromptDialog: onCreateDialog()");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage(R.string.btprompt_message)
                .setTitle(R.string.btprompt_title)
                .setPositiveButton(R.string.btprompt_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.v(MYLOGTAG, "BtPromptDialog: Positive button clicked!");
                        mListener.onPositiveClick(BtPromptDialog.this);

                    }
                })
                .setNegativeButton(R.string.btprompt_negative, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.v(MYLOGTAG, "BtPromptDialog: Negative button clicked!");
                        mListener.onNegativeClick(BtPromptDialog.this);
                    }
                });
        //return super.onCreateDialog(savedInstanceState);
        return builder.create();
    }
}

