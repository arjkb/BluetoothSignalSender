package com.example.arjun.bt_signalsender;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    final String MYLOGTAG = "BT_SignalSender";

    Button genericButton;
    boolean genericButtonStatus;

    Button specialButton;
    boolean specialButtonStatus;

    Fragment genericFragment;// = new GenericSenderFragment();
    Fragment specialFragment;// = new SpecialSenderFragment();

    FragmentManager fragmentManager;// = getFragmentManager();

    View rootView;
    ViewGroup rvg;

    public MainActivityFragment() {
        genericFragment = new GenericSenderFragment();
        specialFragment = new SpecialSenderFragment();
        fragmentManager = getFragmentManager();

      //  rootView = inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //return inflater.inflate(R.layout.fragment_main, container, false);

        Log.v(MYLOGTAG, "MainActivityFragment: onCreateView()");



        rootView = inflater.inflate(R.layout.fragment_main, container, false);

        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.v(MYLOGTAG, "MainActivityFragment: onCreate()");


    }

    @Override
    public void onStart() {
        super.onStart();

        genericButton = (Button) rootView.findViewById(R.id.gen_button);
        specialButton = (Button) rootView.findViewById(R.id.spl_button);

        genericButtonStatus = false;
        specialButtonStatus = false;

        genericButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                Log.v(MYLOGTAG, "Generic Button Clicked!");


                if(!genericButtonStatus)    {

                    /*  if specialFragment is present, replace it with genericFragment
                     *  else add genericFragment straightaway
                     */
                    if( specialButtonStatus )   {
                        transaction.replace(R.id.fragment, genericFragment);
                    } else  {
                        transaction.add(R.id.fragment, genericFragment);
                    }

                    transaction.commit();

                    genericButtonStatus = true;
                    specialButtonStatus = false;
                }

            }
        });

        specialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                Log.v(MYLOGTAG, "Special Button Clicked!");

                if (!specialButtonStatus) {

                    /*  if genericFragment is present, replace it with specialFragment
                     *  else add specialFragment straightaway
                     */
                    if (genericButtonStatus) {
                        transaction.replace(R.id.fragment, specialFragment);
                    } else {
                        transaction.add(R.id.fragment, specialFragment);
                    }

                    transaction.commit();

                    genericButtonStatus = false;
                    specialButtonStatus = true;
                }

            }
        });
    }
}
