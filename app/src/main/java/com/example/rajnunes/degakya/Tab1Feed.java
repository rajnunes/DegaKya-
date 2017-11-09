package com.example.rajnunes.degakya;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by rajnunes on 30/10/17.
 */

public class Tab1Feed extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1, container, false);

        ImageView imageView = (ImageView)rootView.findViewById(R.id.image);
//        imageView.setImageBitmap(bitmap);
        imageView.setImageResource(R.drawable.cycle);

        ImageView imageView1 = (ImageView)rootView.findViewById(R.id.image1);
//        imageView.setImageBitmap(bitmap);
        imageView1.setImageResource(R.drawable.guitar);

        ImageView imageView2 = (ImageView)rootView.findViewById(R.id.image2);
//        imageView.setImageBitmap(bitmap);
        imageView2.setImageResource(R.drawable.tie);

        ImageView imageView3 = (ImageView)rootView.findViewById(R.id.image3);
//        imageView.setImageBitmap(bitmap);
        imageView3.setImageResource(R.drawable.formalshoes);

        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        return rootView;
    }
}
