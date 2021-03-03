/**
 * @author Samone Watkins
 * @Date March 2, 2021
 *
 * Class is successfully implemented with no known bugs
 *
 * ------------------EXTERNAL CITATION-------------------------
 * Date: February 27, 2021
 * Problem: I was unsure how to correctly implement a Spinner and set it's onClickListener
 * Solution: Find
 * Source: https://developer.android.com/guide/topics/ui/controls/spinner#java
 *
 *
 * */

package com.example.facemarker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instantiate the surface view
        FaceSurfaceView face = findViewById(R.id.faceSurfaceView);

        //set all seek bars and their listeners
        SeekBar redBar = findViewById(R.id.redSeekBar);
        SeekBar blueBar = findViewById(R.id.blueSeekBar);
        SeekBar greenBar = findViewById(R.id.greenSeekBar);
        redBar.setOnSeekBarChangeListener(face);
        blueBar.setOnSeekBarChangeListener(face);
        greenBar.setOnSeekBarChangeListener(face);

        //set all buttons and their listeners
        RadioButton eyeButton = findViewById(R.id.eyeRadioButton);
        RadioButton skinButton = findViewById(R.id.skinRadioButton);
        RadioButton hairButton = findViewById(R.id.hairRadioButton);
        skinButton.setOnClickListener(face);
        eyeButton.setOnClickListener(face);
        hairButton.setOnClickListener(face);

        //set the random button and it's listener
        Button randomButton = findViewById(R.id.randomFaceButton);
        randomButton.setOnClickListener(face);

        //set up the spinner, it's options, and it's listener
        Spinner selectSpinner= findViewById(R.id.spinnerObject);
        ArrayAdapter<CharSequence> styles = ArrayAdapter.createFromResource(this,
                R.array.hairStyles_array, android.R.layout.simple_spinner_item);
        styles.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectSpinner.setAdapter(styles);
        selectSpinner.setOnItemSelectedListener(face);

    }
}