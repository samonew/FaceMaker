/**
 * @author Samone Watkins
 * @Date March 2, 2021
 *
 *
 * Class is successfully implemented with no known bugs or deficiencies
 *
 * ----------EXTERNAL CITATION-------------------------
 * Date: February 28, 2021
 * Problem: I wasn't sure how to correctly implement listeners. Each listener I had to google.
 * Solution: Go to the Android Developer site and look at each feature's listener needs
 * Sources: https://developer.android.com/guide/topics/ui/controls/spinner#java
 *          https://developer.android.com/reference/android/widget/TextView
 *          https://developer.android.com/reference/android/widget/SeekBar#public-methods
 *          https://developer.android.com/guide/topics/ui/controls/radiobutton
 *          https://developer.android.com/reference/android/widget/Button
 *          https://developer.android.com/reference/android/view/View
 *
 *
 *----------EXTERNAL CITATION--------------------------
 * Date: February 27 - March 1, 2021
 * Problem: I had issues knowing what feature goes where
 * Solution: Rewatching Tribelhorn's videos to code alongside with and adapt those skills.
 * Sources: All of Dr. Tribelhorn's videos on Moodle. I've rewatched each one
 *
 * --------EXTERNAL CITATION--------------------------
 * Date: March 2, 2021
 * Problem: The spinner wasn't changing according to the tab clicked
 * Solution: Use view.getViewById() to get the button that was clicked
 * Source: Dr. Tribelhorn's office hours & Android SDK for view
 *
 *--------EXTERNAL CITATION---------------------------
 * Date: March 3, 2021
 * Problem: My seekBar's weren't changing with the color levels.
 * Solution: Instead of view.findViewById to instantiate the seekBars in onClick().
 *           I must use (MainActivity)getContext()
 * Source: Anabel Hilerio helped me realize
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
        SeekBar.OnSeekBarChangeListener, AdapterView.OnItemSelectedListener{

    //instance variables for the class
    private Face face;
    private int buttonClicked;

    public FaceSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);

        face = new Face();
    }

    /**
     * Draws the face by calling the Face's onDraw method
     *
     * @param canvas
     */
    protected void onDraw(Canvas canvas){
        face.onDraw(canvas);
    }

    /**
     * This method saves the id so that the program knows what feature's colors should change. If
     * the button is the randomize button then call Face's randomize(). Then invalidate so it will
     * redraw.
     *
     * @param view
     */
    @Override
    public void onClick(View view) {


        //tells the program which button is clicked and what color to change
        buttonClicked = view.getId();

        if(buttonClicked == R.id.randomFaceButton){
            face.randomize();
        }

        else{
            SeekBar red = ((MainActivity)getContext()).findViewById(R.id.redSeekBar);
            SeekBar green = ((MainActivity)getContext()).findViewById(R.id.greenSeekBar);
            SeekBar blue = ((MainActivity)getContext()).findViewById(R.id.blueSeekBar);

            if(buttonClicked == R.id.skinRadioButton){
                onProgressChanged(red, Color.red(face.getSkinColor()), false);
                onProgressChanged(green, Color.green(face.getSkinColor()), false);
                onProgressChanged(blue, Color.blue(face.getSkinColor()), false);
            }
            else if(buttonClicked == R.id.hairRadioButton){
                onProgressChanged(red, Color.red(face.getHairColor()), false);
                onProgressChanged(green, Color.green(face.getHairColor()), false);
                onProgressChanged(blue, Color.blue(face.getHairColor()), false);
            }
            else if(buttonClicked == R.id.eyeRadioButton){
                onProgressChanged(red, Color.red(face.getEyeColor()), false);
                onProgressChanged(green, Color.green(face.getEyeColor()), false);
                onProgressChanged(blue, Color.blue(face.getEyeColor()), false);
            }
        }


        invalidate();
    }

    /**
     * This saves the color values of the seekbar that was changed and changes the color of the
     * feature that is selected as it is scrubbed.
     *
     * @param seekBar
     * @param i
     * @param fromUser
     */
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

        else{
            seekBar.setProgress(i);
            invalidate();
        }

    }

    //do nothing for the next two
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) { }
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) { }

    /**
     * This determines which tab on the spinner was selected and sets that as the face's new hair
     * style.
     *
     * @param adapterView
     * @param view
     * @param i
     * @param l
     */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        if(adapterView.getItemAtPosition(i).toString().equalsIgnoreCase("Afro")){
            //then hair style is Afro
            face.setHairStyle(0);
        }

        else if(adapterView.getItemAtPosition(i).toString().equalsIgnoreCase("Bangs")){
            //then hair style is bangs
            face.setHairStyle(1);
        }

        else if(adapterView.getItemAtPosition(i).toString().equalsIgnoreCase("buns")){
            //then hair style is buns
            face.setHairStyle(2);
        }

        invalidate();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) { }

}
