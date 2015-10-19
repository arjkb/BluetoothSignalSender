package com.example.arjun.bt_signalsender;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link com.example.arjun.bt_signalsender.GenericSenderFragment.GenericFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GenericSenderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GenericSenderFragment extends Fragment {

    final String MYLOGTAG = "BT_SignalSender";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private GenericFragmentInteractionListener mListener;

    protected Button sendButton;
    private View rootView;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GenericSenderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GenericSenderFragment newInstance(String param1, String param2) {
        GenericSenderFragment fragment = new GenericSenderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public GenericSenderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        //return inflater.inflate(R.layout.generic_sender, container, false);

        rootView = inflater.inflate(R.layout.generic_sender, container, false);

        sendButton = (Button) rootView.findViewById(R.id.send_button);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mListener.onSendPress(v);
                } catch (NullPointerException E)    {
                    Log.v(MYLOGTAG, " SendButton: onClick: NullPointerException " + E);
                } catch (Exception E)   {
                    Log.v(MYLOGTAG, " SendButton: onClick: Exception " + E);
                }
            }
        });
    }

/*

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
      //      mListener.onFragmentInteraction(uri);
        }
    }
*/

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (GenericFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
         //   throw new ClassCastException(activity.toString()
         // + " must implement OnFragmentInteractionListener");
            Log.v(MYLOGTAG, "ClassCastException: (Generic) GenericFragmentInteractionListener not implemented!");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface GenericFragmentInteractionListener {
        // TODO: Update argument type and name
//        public void onFragmentInteraction(Uri uri);
        void onSendPress(View v);
    }
}
