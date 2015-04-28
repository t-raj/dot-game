package com.oreilly.demo.android.pa.uidemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.oreilly.demo.android.pa.uidemo.model.MonsterActivity;
import static com.oreilly.demo.android.pa.uidemo.Constants.Constants.*;

/**
 * Created by Austin Kelsch on 4/21/2015.
 */
public class MonsterView extends View {

    private MonsterActivity monsterActivity;// = new MonsterActivity();//access its monsterMatrices for drawing on view.
        //above is set by TouchMe
    private int g = GRID_SIZE;
    public MonsterView(Context context) {
        super(context);
        setFocusableInTouchMode(true);
    }

    /**
     * @param context
     * @param attrs
     */
    public MonsterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFocusableInTouchMode(true);
    }

    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public MonsterView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setFocusableInTouchMode(true);
    }

    public void setMonsterActivity (MonsterActivity newMonsterActivity){ //also set the same activity in touchme and send it to this view.
        monsterActivity = newMonsterActivity;
        monsterActivity.startActivity();
    }

    @Override protected void onDraw(Canvas canvas) {//use a bool to check if grid isDrawn. establish set of monsters and have them drawn each time.
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        canvas.drawRect(0, 0, getWidth() - 1, getHeight() -1, paint);
        //extract this loop to a separate method.
        for (int i = 1; i <= g-1; i++){
            canvas.drawLine(0, getHeight()/g*i, getWidth(), getHeight()/g*i, paint);
        }
        for (int i = 1; i <= g-1; i++){
            canvas.drawLine(getWidth()/g*i, 0, getWidth()/g*i, getHeight(), paint);
        }

        //then do monster activity stuff?
        //drawing monsters...
        /*
        int[][] monsters = monsterActivity.getMonsterMatrix();
        for (int i =0; i <= g; i++){
            for (int j = 0; i <= g; i++){
                canvas.
            }
        }*/


        /*if (null == dots) { return; }

        paint.setStyle(Style.FILL);
        for (Dot dot : dots.getDots()) {
            paint.setColor(dot.getColor());
            canvas.drawCircle(
                dot.getX(),
                dot.getY(),
                dot.getDiameter(),
                paint);
        }*/
    }
}
