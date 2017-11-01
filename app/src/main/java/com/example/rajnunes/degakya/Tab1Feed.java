package com.example.rajnunes.degakya;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by rajnunes on 30/10/17.
 */

public class Tab1Feed extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        return rootView;
    }
}
