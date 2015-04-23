package com.oreilly.demo.android.pa.uidemo.view;

import android.content.Context;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

import com.oreilly.demo.android.pa.uidemo.model.Dot;
import com.oreilly.demo.android.pa.uidemo.model.Dots;
import static com.oreilly.demo.android.pa.uidemo.constants.Constants.*;


/**
 * I see spots!
 *
 * @author <a href="mailto:android@callmeike.net">Blake Meike</a>
 */
public class DotView extends View {

    private volatile Dots dots;
    private int g = GRID_SIZE;

    /**
     * @param context the rest of the application
     */
    public DotView(Context context) {
        super(context);
        setFocusableInTouchMode(true);
    }

    /**
     * @param context
     * @param attrs
     */
    public DotView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFocusableInTouchMode(true);
    }

    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public DotView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setFocusableInTouchMode(true);
    }

    /**
     * @param dots
     */
    public void setDots(Dots dots) { this.dots = dots; }

    /**
     * @see android.view.View#onDraw(android.graphics.Canvas)
     */
    @Override protected void onDraw(Canvas canvas) {//use a bool to check if grid isDrawn. establish set of monsters and have them drawn each time.
        Paint paint = new Paint();
        paint.setStyle(Style.STROKE);
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
