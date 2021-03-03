/**
 * @author Samone Watkins
 * @Date March 2, 2021
 *
 *class is successfully implemented with no known bugs or deficiencies
 *
 * ------------EXTERNAL CITATION-------------------
 * Date: March 2, 2021
 * Problem: Confusion about how to correctly use one int as a color
 * Solution: use the color class to convert rgb values to one int
 * Source: Dr. Tribelhorn's office hours & the link he sent me:
 *         https://developer.android.com/reference/android/graphics/Color#blue(int)
 *
 * */

package com.example.facemarker;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Face {

    //instance variables that represent the face
    private int eyeColor;
    private int skinColor;
    private int hairColor;
    private int hairStyle;

    public Face(){
        randomize();
    }

    /**
     * Randomly draws a face with randomly generated colors and randomly picking the hair style.
     *
     * Takes no parameters and returns nothing
     */
    public void randomize() {

        //setting all of the colors to random values
        int red =(int) (255 * Math.random());
        int blue = (int)(255 * Math.random());
        int green = (int)(255 * Math.random());
        eyeColor = Color.rgb(red, green, blue);

        red = (int)(255 * Math.random());
        blue = (int)(255 * Math.random());
        green = (int)(255 * Math.random());
        skinColor = Color.rgb(red, green, blue);

        red = (int)(255 * Math.random());
        blue = (int)(255 * Math.random());
        green = (int)(255 * Math.random());
        hairColor = Color.rgb(red, green, blue);

        int style = (int)(2 * Math.random());
        hairStyle = style;

    }

    /**
     * The method that draws all of the facial features.
     * Works by getting the center of the canvas and then calculating face, eyes, and hair.
     *
     * @param canvas
     */
    public void onDraw(Canvas canvas){
        canvas.drawColor(Color.WHITE);

        int cx = canvas.getWidth() / 2;
        int cy = canvas.getHeight() / 2;

        Paint p = new Paint();

        //draw the back hair if the style wants it
        if(hairStyle == 1) {
            p.setColor(hairColor);
            canvas.drawOval(cx - 275, cy - 275, cx + 275, cy + 300, p);
        }
        if(hairStyle == 0){
            p.setColor(hairColor);
            canvas.drawCircle(cx, cy - 100, 300, p);
        }

        //draw skin
        p.setColor(skinColor);
        canvas.drawCircle(cx, cy, 200, p);

        //draw eyes (Whites, then irises, then pupils)
        p.setColor(Color.WHITE);
        canvas.drawCircle(cx + 75, cy -25, 50, p);
        canvas.drawCircle(cx - 75, cy - 25, 50, p);
        p.setColor(eyeColor);
        canvas.drawCircle(cx + 75, cy - 25, 25, p);
        canvas.drawCircle(cx - 75, cy - 25, 25, p);
        p.setColor(Color.BLACK);
        canvas.drawCircle(cx + 75, cy - 25, 10, p);
        canvas.drawCircle(cx - 75, cy - 25, 10, p);

        //draw a smile
        p.setColor(Color.BLACK);
        canvas.drawArc( cx - 150, cy + 150, cx + 150, cy + 75, 0,
                190, false, p );

        //draw front hair
        p.setColor(hairColor);
        if(hairStyle == 0){ //then it's an afro
            canvas.drawOval(cx - 150, cy - 200, cx + 150, cy - 100, p);
        }
        if(hairStyle == 1){ // then it's bangs
            canvas.drawRect(cx - 150, cy - 200, cx + 150, cy - 100, p);
        }
        if(hairStyle == 2){ // then it's a ponytail
            canvas.drawArc(cx - 350, cy - 300, cx - 200, cy + 200,
                    180, 360, true, p);

        }


    }

    //getters and setters for each instance variable
    public int getEyeColor() { return eyeColor; }

    public int getHairStyle() { return hairStyle; }

    public int getSkinColor() { return skinColor; }

    public int getHairColor() { return hairColor; }

    public void setEyeColor(int eyeColor) { this.eyeColor = eyeColor; }

    public void setHairColor(int hairColor) { this.hairColor = hairColor; }

    public void setHairStyle(int hairStyle) { this.hairStyle = hairStyle; }

    public void setSkinColor(int skinColor) { this.skinColor = skinColor; }


}
