package com.oreilly.demo.android.pa.uidemo.model;

import android.graphics.Canvas;

/**
 * Created by akelsch on 4/19/2015.
 */
public class Monster {
    public Monster(int x, int y){
        xLocation = x;
        yLocation = y;
    }
    private int xLocation;
    private int yLocation;

    void drawMonster(Monster monster, Canvas canvas){ //make sure its the same canvas that the grid is drawn on.
        //canvas.drawRect maybe have these methods in an extension of the view instead.
    }

    void removeMonster(Monster monster, Canvas canvas){

    }
}
