package com.oreilly.demo.android.pa.uidemo;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;

import com.oreilly.demo.android.pa.uidemo.model.Dot;
import com.oreilly.demo.android.pa.uidemo.model.Dots;
import com.oreilly.demo.android.pa.uidemo.model.MonsterActivity;
import com.oreilly.demo.android.pa.uidemo.model.clock.ClockModel;
import com.oreilly.demo.android.pa.uidemo.model.clock.DefaultClockModel;
import com.oreilly.demo.android.pa.uidemo.model.clock.DefaultTimeModel;
import com.oreilly.demo.android.pa.uidemo.model.clock.OnTickListener;
import com.oreilly.demo.android.pa.uidemo.view.DotView;

import static com.oreilly.demo.android.pa.uidemo.constants.Constants.*;

/**
 * Android UI demo program
 */
public class TouchMe extends Activity implements OnTickListener {
    /** Dot diameter */
    ClockModel cl  = new DefaultClockModel();
    int score,time1;
    public static final int DOT_DIAMETER = 6;
    public MonsterActivity monsterActivityActivity = new MonsterActivity();

    /** Listen for taps. */
    private final class TrackingTouchListener implements View.OnTouchListener
    {
        private final Dots mDots;
        private List<Integer> tracks = new ArrayList<Integer>();
        TrackingTouchListener(Dots dots) { mDots = dots; }
        @Override public boolean onTouch(View v, MotionEvent evt) {
            int idx;
            int action = evt.getAction();
            switch (action & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_POINTER_DOWN:
                    idx = (action & MotionEvent.ACTION_POINTER_INDEX_MASK)
                            >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
                    tracks.add(Integer.valueOf(evt.getPointerId(idx)));
                    break;

                case MotionEvent.ACTION_POINTER_UP:
                    idx = (action & MotionEvent.ACTION_POINTER_INDEX_MASK)
                            >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
                    tracks.remove(Integer.valueOf(evt.getPointerId(idx)));
                    break;
                default:
                    return false;
            }
            for (Integer i : tracks) {   //if the indices coincide with a space where a vulnerable monster is, remove it from the grid.
                idx = evt.findPointerIndex(i.intValue());
                mDots.setCoords(evt.getX(idx), evt.getY(idx));
                TouchMe.this.removeMonster();
            }
                return true;
        }
    }
    private final Random rand = new Random();

    /** The application model */
    final Dots dotModel = new Dots();

    /** The application view */
    DotView dotView; //refactor to be called, monster view.

    /** The dot generator */
  // DotGenerator dotGenerator;
    public void removeMonster()
    {
        int g = GRID_SIZE;
        Float tempX = dotModel.getX();
        Float tempY = dotModel.getY();
        int ux = dotView.getIndexX(tempX);
        int uy = dotView.getIndexY(tempY);
        int[][] temp = monsterActivityActivity.getMonsterMatrix();
       if(temp[ux][uy]== 2)
       {
           score++;
           updateScore(score);
           temp[ux][uy]=0;
           monsterActivityActivity.setMonsterMatrix(temp);
           runOnUiThread(new Runnable() {
               @Override
               public void run() {
                   View v = findViewById(R.id.dots);
                   v.invalidate();
               }
           });
           dotModel.clearDots();
           for (int i = 0; i < g; i++) {
               for (int j = 0; j < g; j++) {
                   if (temp[i][j] == 1) { //invulnerable monster.
                       //Log.d(TAG, "There is a monster at this location" + i + "    " + j);
                       dotModel.addDot(i, j, R.color.green, 30);
                   }
                   if(temp[i][j] == 2){ //vulnerable monster
                       //Log.d(TAG, "There is a monster at this location" + i + "    " + j);
                       dotModel.addDot(i, j, R.color.yellow, 30);
                   }
               }
           }
           dotView.setDots(dotModel);
       }
    }
    public void createPopup()
    {
        final PopupWindow popupwind;
        TextView popupText;
        Button insidePopupButton;
        LinearLayout layoutofPop;
        insidePopupButton = new Button(this);
        layoutofPop = new LinearLayout(this);
        insidePopupButton.setTextColor(Color.RED);
        insidePopupButton.setText("Game Over!\n Your score is: " + score);
        layoutofPop.setOrientation(LinearLayout.VERTICAL);
        layoutofPop.addView(insidePopupButton);
        popupwind = new PopupWindow(layoutofPop,LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
        popupwind.setContentView(layoutofPop);
        popupwind.showAtLocation(findViewById(R.id.dots),Gravity.CENTER,100,100);
    }
    public void onTick(){
        time1--;
        Log.d("Score", "The score is "+ score);
        Log.d("Max monster", "Max monster is: "+ monsterActivityActivity.getMonsterTally());
        updateTime(time1);
        if(time1 == 0 || score == monsterActivityActivity.getMonsterTally()){
            Looper.prepare();
            runOnUiThread(new Runnable() {
               @Override
                public void run() {
                    createPopup();
                }
            });
            cl.stop();}
        else{
            monsterMove();
        }
    }

    public void monsterMove(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                View v = findViewById(R.id.dots);
                v.invalidate();
            }
        });
        String TAG = "MonsterActivity log: ";
        int g = GRID_SIZE;
        monsterActivityActivity.monsterGridMove();
        int[][] matrix = monsterActivityActivity.getMonsterMatrix();
        dotModel.clearDots();
        for (int i = 0; i < g; i++) {
            for (int j = 0; j < g; j++) {
                if (matrix[i][j] == 1) { //invulnerable monster.
                   // Log.d(TAG, "There is a monster at this location" + i + "    " + j);
                    dotModel.addDot(i, j, R.color.green, 30);
                }
                if(matrix[i][j] == 2){ //vulnerable monster
                        //Log.d(TAG, "There is a monster at this location" + i + "    " + j);
                        dotModel.addDot(i, j, R.color.yellow, 30);
                }
                    // Add the actual monsters to the screen in this loop
                }
            }
        dotView.setDots(dotModel);
    }

    /** Called when the activity is first created. */
    @Override public void onCreate(Bundle state) {
        String TAG = "MonsterActivity log: ";
        super.onCreate(state);
        time1 = round1time;// change to round 1 time-- 10 is just to check
        int g = GRID_SIZE, k = 0;
        int[][] matrix = monsterActivityActivity.getMonsterMatrix();
        for (int i = 0; i < g; i++)
            for (int j = 0; j < g; j++) {
                if (matrix[i][j] == 1) {
                    Log.d(TAG, "There is a monster at this location" + i + "    " + j);
                    dotModel.addDot(i, j, R.color.green, 30);
                }
            }
        cl.setOnTickListener(this);
        cl.start();

        // install the view
        setContentView(R.layout.main);

        // find the dots view
        dotView = (DotView) findViewById(R.id.dots);
        dotView.setDots(dotModel);
        dotView.setOnCreateContextMenuListener(this);

        // dotView.setOnTouchListener(new TrackingTouchListener(dotModel));  the next two lines do the exact same thing
        TrackingTouchListener temp = new TrackingTouchListener(dotModel);
        dotView.setOnTouchListener(temp);
        dotModel.setDotsChangeListener(new Dots.DotsChangeListener() {
            @Override public void onDotsChange(Dots dots) {
                Dot d = dots.getLastDot();
                // This code makes the UI unacceptably unresponsive.
                // ... investigating - in March, 2014, this was not a problem
                //tb1.setText((null == d) ? "" : String.valueOf(d.getX())); // uncommented
                //tb2.setText((null == d) ? "" : String.valueOf(d.getY())); // uncommented
                //dotView.invalidate();
            } });
    }

    /** Install an options menu. */
    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.simple_menu, menu);
        return true;
    }


    public void updateScore(int score1)
    {
        // UI adapter responsibility to schedule incoming events on UI thread
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final TextView tvS = (TextView) findViewById(R.id.level);

                final int score1 = score;

                tvS.setText(Integer.toString(score1));

            }
        });
    }

    public void updateTime(int time)
    {
        // UI adapter responsibility to schedule incoming events on UI thread
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final TextView tvS = (TextView) findViewById(R.id.time);

                final int time = time1;

                tvS.setText(Integer.toString(time));

            }
        });
    }

    /** Respond to an options menu selection. */
    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_clear:
                dotModel.clearDots();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /** Install a context menu. */
    @Override public void onCreateContextMenu(
        ContextMenu menu,
        View v,
        ContextMenuInfo menuInfo)
    {
        menu.add(Menu.NONE, 1, Menu.NONE, "Clear")
            .setAlphabeticShortcut('x');
    }

    /** Respond to a context menu selection. */
    @Override public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                dotModel.clearDots();
                return true;
            default: ;
        }

        return false;
    }

    /**
     * @param dots the dots we're drawing
     * @param view the view in which we're drawing dots
     * @param color the color of the dot
     */
    void makeDot(Dots dots, DotView view, int color) { //changed from makeDot, creates a monsterr
        int pad = (DOT_DIAMETER + 2) * 2;
        dots.addDot(
            DOT_DIAMETER + (rand.nextFloat() * (view.getWidth() - pad)),
            DOT_DIAMETER + (rand.nextFloat() * (view.getHeight() - pad)),
            color,
            DOT_DIAMETER);
    }
}