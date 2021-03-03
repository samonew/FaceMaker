/**
 * @author Samone Watkins
 * @Date March 2, 2021
 *
 *
 *
 *
 * */
package com.example.facemarker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;

public class FaceSurfaceView extends SurfaceView implements View.OnClickListener,
        SeekBar.OnSeekBarChangeListener, AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener{

    private Face face;
    private int buttonClicked;


    public FaceSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);

        face = new Face();
    }

    protected void onDraw(Canvas canvas){
        //This is where we draw the face
        face.onDraw(canvas);
    }

    @Override
    public void onClick(View view) {

        //save all of the old values
        SeekBar red = view.findViewById(R.id.redSeekBar);
        SeekBar green = view.findViewById(R.id.greenSeekBar);
        SeekBar blue = view.findViewById(R.id.blueSeekBar);
        int greenProg = green.getProgress();
        int blueProg = blue.getProgress();
        int redProg = red.getProgress();

        //change the button click
        if(buttonClicked == R.id.eyeRadioButton){
            face.setEyeColor(Color.rgb(redProg, blueProg, greenProg));
        }
        else if(buttonClicked == R.id.hairRadioButton){
            face.setHairColor(Color.rgb(redProg, greenProg, blueProg));
        }
        else if(buttonClicked == R.id.skinRadioButton){
            face.setSkinColor(Color.rgb(redProg, greenProg, blueProg));
        }

        //tells the program which button is clicked and what color to change
        buttonClicked = view.getId();

        if(view.getId() == R.id.randomFaceButton){
            face.randomize();
        }

        //set the progress for each progress bar based off of the button clicked

        invalidate();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean fromUser) {

        if (fromUser) {
            int red = 0;
            int green = 0;
            int blue = 0;

            if(buttonClicked == R.id.eyeRadioButton){
                red = Color.red(face.getEyeColor());
                green = Color.green(face.getEyeColor());
                blue = Color.blue(face.getEyeColor());
            }
            else if(buttonClicked == R.id.skinRadioButton){
                red = Color.red(face.getSkinColor());
                green = Color.green(face.getSkinColor());
                blue = Color.blue(face.getSkinColor());
            }
            else if(buttonClicked == R.id.hairRadioButton){
                red = Color.red(face.getHairColor());
                green = Color.green(face.getHairColor());
                blue = Color.blue(face.getHairColor());
            }

            if(seekBar.getId() == R.id.blueSeekBar){
                blue = i;
            }
            else if(seekBar.getId() == R.id.greenSeekBar){
                green = i;
            }
            else if(seekBar.getId() == R.id.redSeekBar){
                red = i;
            }

            if(buttonClicked == R.id.eyeRadioButton){
                face.setEyeColor(Color.rgb(red, green, blue));
            }
            else if(buttonClicked == R.id.skinRadioButton){
                face.setSkinColor(Color.rgb(red, green, blue));
            }
            else if(buttonClicked == R.id.hairRadioButton){
                face.setHairColor(Color.rgb(red, green, blue));
            }

            invalidate();
        }

    }

    //do nothing for the next two
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) { }
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) { }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        if(adapterView.getItemAtPosition(i).toString().equalsIgnoreCase("Afro")){
            //then hair style is Afro
            face.setHairStyle(0);
        }

        else if (adapterView.getItemAtPosition(i).toString().equalsIgnoreCase("Bangs")){
            //then hair style is bangs
            face.setHairStyle(1);
        }

        else if(adapterView.getItemAtPosition(i).toString().equalsIgnoreCase("ponytail")){
            //then hair style is ponytails
            face.setHairStyle(2);
        }

        invalidate();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) { }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if(adapterView.getAdapter() == adapterView.getAdapter().getItem(0)){
            //then hair style is Afro
            face.setHairStyle(0);
        }

        else if (adapterView.getAdapter() == adapterView.getAdapter().getItem(1)){
            //then hair style is bangs
            face.setHairStyle(1);
        }

        else if(adapterView.getAdapter() == adapterView.getAdapter().getItem(2)){
            //then hair style is ponytails
            face.setHairStyle(2);
        }
        invalidate();
    }
}
