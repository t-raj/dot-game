package com.oreilly.demo.android.pa.uidemo.test.android;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;

import com.oreilly.demo.android.pa.uidemo.TouchMe;
import com.oreilly.demo.android.pa.uidemo.constants.Constants;
import com.oreilly.demo.android.pa.uidemo.model.MonsterActivity;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


/**
 * Created by Kyle on 4/29/2015.
 */
public abstract class AbstractTouchMeTest {
    /**
     * Verifies that the activity under test can be launched.
     */
    private final int g = Constants.GRID_SIZE;

    @Test
    public void testActivityCheckTestCaseSetUpProperly() {
        assertNotNull("activity should be launched successfully", getActivity());
    }


//    /**
//     * Verifies that onTouch() removes a Monster iff it matches the location and is Vulnerable
//     */
//    @Test
//    public void testOnTouch(int[] location) throws Throwable{
//        float[] spot = findCoords(location, DotView.findViewById(R.id.dots));
//        simulateEventDown(View.findViewById(R.id.dots), spot[0],spot[1]);
//    }


    /**
     * Verifies that removeMonster(location) works for both vulnerable and invulnerable monsters
     * @throws Throwable
     */
    @Test
    public void testRemoveVulnerableMonster(){
        int[] x = getMonster(1);
        monsterActivityActivity.removeMonster(x[0],x[1]);
        assertTrue(getMonsterStatus(x) == 0);
    }
    @Test
    public void testRemoveInvulnerableMonster(){
        int[] x = getMonster(2);
        monsterActivityActivity.removeMonster(x[0],x[1]);
        assertTrue(getMonsterStatus(x) == 2);
    }

    /**
     * Series of Getter and Setter methods to make testing easier
     * @return
     */
    protected abstract TouchMe getActivity();
    public MonsterActivity monsterActivityActivity = new MonsterActivity();

    void simulateEventDown(View v, long x, long y){
        MotionEvent e = MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, x, y, 0);
        v.dispatchTouchEvent(e);
    }

    float[] findCoords(int[] location, View v){
        float[] r = {location[0]* v.getWidth()/g, location[1]*v.getHeight()/g};
        return r;
    }


    protected int getMonsterStatus(int[] location){
        return monsterActivityActivity.getMonsterMatrix()[location[0]][location[1]];
    }

    // gets the first Monster that matches the vulnerable/invulnerable status
    protected int[] getMonster(int status){
        int temp[] = {-1,-1};
        int[][] m = monsterActivityActivity.getMonsterMatrix();
        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m.length; j++){
                if (m[i][j] == status){
                    temp[0] = i;
                    temp[1] = j;
                    break;
                }
            }
        return temp;
    }

  /* protected Button getStartStopButton() {
        return (Button) getActivity().findViewById(R.id.startStop);
    }
*/

}
