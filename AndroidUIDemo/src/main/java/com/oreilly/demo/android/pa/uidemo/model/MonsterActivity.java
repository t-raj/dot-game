package com.oreilly.demo.android.pa.uidemo.model;

import android.util.Log;
import static com.oreilly.demo.android.pa.uidemo.constants.Constants.*;
import java.util.Random;

/**
 * Created by akelsch on 4/19/2015.
 */
public class MonsterActivity {

    public MonsterActivity (){
        int x;
        for (int i = 0; i < g; i++){
            for (int j = 0; j < g; j++){
                Random random = new Random();
                x = random.nextInt(2);
                Log.d(TAG, "x is: " +x);
                monsterMatrix[i][j] = x;
            }
        }
    }

    private int g = GRID_SIZE; //size of the grid.
    private String TAG = "MonsterActivity log: ";
    private int[][] monsterMatrix = new int[g][g];


    public int[][] getMonsterMatrix(){ return monsterMatrix; }

    public void startActivity() {
        makeMonsters(monsterMatrix);
        int x = 0;
        while(x != 1){
            try {
                Thread.sleep(1000); //make a constant, and change with levels? //may extract this to touch me... or not.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            monsterMatrix = removeMonsters(monsterMatrix);// which recursively calls makeMonsters with old positions.
            makeMonsters(monsterMatrix);
        }
    }
    public int[][] removeMonsters(int[][] monsterMatrix){
        int x = 0;
        int y = 0;
        int z = 0;
        for (int i = 0; i < g; i++) {
            for (int j = 0; i < g; i++) {
                z = 0;
                if (monsterMatrix[i][j] == 1) {
                    while (z != 1) {
                        //randomly select two numbers between (x any y, (from -1,1)
                        //the monster will be sent to i+x, i+y
                        //check if that spot is 1 already, if so, randomly select again.
                        Random random = new Random();
                        x = random.nextInt(2);
                        if (x == 0) {
                            x = -1;
                        }
                        y = random.nextInt(2);
                        if (y == 0) {
                            y = -1;
                        }
                        if (monsterMatrix[i + x][i + y] != 1) {
                            monsterMatrix[i + x][i + y] = 1; //space is open, and monster moves to it
                            z = 1;//process would repeat until an open space is found
                        }
                    }
                }
            }
        }
        this.monsterMatrix = monsterMatrix;
        return monsterMatrix; // send this to monsterView?
    }
    //get locations of current monsters, and remove them from the grid.

    void makeMonsters(int[][] monsterMatrix){ //consider making a separate 'monster' class that has simplified methods for creating and removing monsters.
        //grab location of monsters from last move (given as parameter by the removeMonsters method)
        //draw a rectangle for the monsters at places based on grid (use a [x][y] notation)
        //randomly choose color between protected and vulnerable.
    }
}
