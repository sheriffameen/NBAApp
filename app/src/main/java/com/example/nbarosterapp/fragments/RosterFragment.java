package com.example.nbarosterapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nbarosterapp.R;
import com.example.nbarosterapp.navigator.NBANavigator;


public class RosterFragment extends Fragment {

    private static final String URLNAME_TAG = "urlName key";
    private View rootView;
    private String urlName;
    private TextView rosterTextView;


    public RosterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param urlName Parameter 1.
     * @return A new instance of fragment RosterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RosterFragment newInstance(String urlName) {
        RosterFragment fragment = new RosterFragment();
        Bundle args = new Bundle();
        args.putString(URLNAME_TAG, urlName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            urlName = getArguments().getString(URLNAME_TAG);
        }


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        rootView =  inflater.inflate(R.layout.fragment_roster, container, false);

        rosterTextView = rootView.findViewById(R.id.roster_textView);

        rosterTextView.setText(urlName);

        return rootView;
    }














    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }



    }

