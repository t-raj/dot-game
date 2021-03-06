package com.oreilly.demo.android.pa.uidemo.model;

import java.util.Random;

import static com.oreilly.demo.android.pa.uidemo.constants.Constants.GRID_SIZE;

/**
 * Created by akelsch on 4/19/2015.
 */
public class MonsterActivity {
    private int g = GRID_SIZE; //size of the grid.
    //private String TAG = "MonsterActivity log: ";
    private int[][] monsterMatrix = new int[g][g];
    int monsterTally = 0;

    public int getMonsterTally()
    {
        return monsterTally;
    }


    public MonsterActivity() {
        int x;
        Random random = new Random();
        for (int i = 0; i < g; i++) {
            for (int j = 0; j < g; j++) {
                x = random.nextInt(3);
                if (x == 2) // to favor the odds for the board to be approximately 1/3 filled on start.
                {

                    x = 0;

                }
                if(x == 1)
                {
                    monsterTally++;
                }
                //Log.d(TAG, "x is: " +x);
                monsterMatrix[i][j] = x;

            }
        }
    }

    public int[][] getMonsterMatrix() {
        return monsterMatrix;
    }

    public void setMonsterMatrix(int[][] Matrix) { this.monsterMatrix = Matrix;}

    public int[][] monsterGridMove() { // 0's represent empty space, 1's represent invulnerable monsters, 2's represent vulnerable monsters.
        int newX,newY,x,y,z;
        Random random = new Random();
        int currentMonster = 0;
        int[][] skipList = new int[g][g];
        for (int i = 0; i < g; i++) {
            for (int j = 0; j < g; j++) {
                z = 0;
                //Log.d(TAG, "Current spot being checked: ["+i+"]["+j+"]" );
                if ((monsterMatrix[i][j] == 1 || monsterMatrix[i][j] == 2) && skipList[i][j] != 1) { //if there is a monster at this space.
                   // Log.d(TAG, "Current spot being checked: [" + i + "][" + j + "]");
                    if (monsterMatrix[i][j] == 1 || monsterMatrix[i][j] == 2) { //if there is a monster at this space.
                        while (z != 3) {
                            //randomly select two numbers between (x any y, (from -1,1)
                            //the monster will be sent to i+x, i+y
                            //check if that spot is 1 or 2 already, if so, randomly select again

                            currentMonster = random.nextInt(6); // pick a number, 0-4 (if it is 0 or 1, monster is invulnerable. if it is 2, the monster is vulnerable).
                            if (currentMonster == 0 || currentMonster > 2){     //if number picked is 0, set it 1
                              currentMonster = 1;
                            }
                            x = random.nextInt(3);
                            if (x == 2) {
                                x = -1;
                            }
                            y = random.nextInt(3);
                            if (y == 2) {
                                y = -1;
                            }
                            newX = i + x;
                            newY = j + y;
                            //exception (if x or y are zero, and i or j are zero), cant assign it to a negative index.
                            if (newX == -1) {
                                newX = g - 1;
                            }
                            //exception (if x or y are zero, and i or j are zero), cant assign it to a negative index.
                            if (newY == -1) {
                                newY = g - 1;
                            }
                            //Log.d(TAG, "mod G index: ["+((i+x)%g)+"]["+((j+y)%g)+"]" );
                            if ((monsterMatrix[(newX) % g][(newY) % g] != 1) && (monsterMatrix[(newX) % g][(newY) % g] != 2)) { //modify this so it only tries 3 times, in case all neighbors are full.
                                monsterMatrix[(newX) % g][(newY) % g] = currentMonster;//space is open, and monster moves to it
                                monsterMatrix[i][j] = 0; //need to remove monster from its current spot.
                                skipList[(newX) % g][(newY) % g] = 1;
                                //also want to give this index a "flag" of some sorts. If its moved, and the loop gets to that index later, it shouldnt be moved again.
                                z = 3;//process would repeat until an open space is found. only tries 3 times, otherwise it'll stay put.
                            } else {
                                z++;
                            }
                        }
                    }
                }
            }
        }
            return monsterMatrix;
    }

    public void removeMonster(int x, int y){ //call this when a monster is touched.  for testing purposes
        monsterMatrix[x][y] = 0;
    }
}
