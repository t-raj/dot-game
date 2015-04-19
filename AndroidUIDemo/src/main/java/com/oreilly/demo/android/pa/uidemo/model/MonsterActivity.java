package com.oreilly.demo.android.pa.uidemo.model;

import java.util.Random;

/**
 * Created by akelsch on 4/19/2015.
 */
public class MonsterActivity {

    public MonsterActivity (){
        int x;
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                Random random = new Random();
                x = random.nextInt(2);
                monsterMatrix[i][j] = x;
            }
        }
    }
    private int[][] monsterMatrix;

    public void startActivity() throws InterruptedException {
        makeMonsters(monsterMatrix);
        int x = 0;
        while(x != 1){
            Thread.sleep(1000); //make a constant, and change with levels?
            monsterMatrix = removeMonsters(monsterMatrix);// which recursively calls makeMonsters with old positions.
            makeMonsters(monsterMatrix);
        }
    }
    int[][] removeMonsters(int[][] monsterMatrix){
        int x = 0;
        int y = 0;
        int z = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; i < 4; i++) {
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
        return monsterMatrix;
    }
    //get locations of current monsters, and remove them from the grid.

    void makeMonsters(int[][] monsterMatrix){ //consider making a separate 'monster' class that has simplified methods for creating and removing monsters.
        //grab location of monsters from last move (given as parameter by the removeMonsters method)
        //draw a rectangle for the monsters at places based on grid (use a [x][y] notation)
        //randomly choose color between protected and vulnerable.
    }
}
