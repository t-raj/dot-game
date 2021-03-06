package com.oreilly.demo.android.pa.uidemo.model.clock;

/*import static edu.luc.etl.cs313.android.simplestopwatch.common.Constants.*;
import static edu.luc.etl.cs313.android.simplestopwatch.common.Constants.SEC_PER_HOUR;
import static edu.luc.etl.cs313.android.simplestopwatch.common.Constants.SEC_PER_TICK;
*/

import java.util.Timer;
import java.util.TimerTask;

/**
 * An implementation of the stopwatch data model. Added from project 4
 */
public class DefaultTimeModel implements TimeModel {
    private Timer timer;
    private OnTickListener listener;

	private int runningTime = 0;

	//private int lapTime = -1;

    @Override
	public void resetRuntime() {
		runningTime = 0;
	}

   // @Override
	public void incRuntime() {
		runningTime = (runningTime); //+ SEC_PER_TICK) % SEC_PER_HOUR;
	}

    @Override
    public void decRuntime() {
        runningTime = (runningTime);// - SEC_PER_TICK) % SEC_PER_HOUR;
    } //method to decrease the time by 1 second

    @Override
	public int getRuntime() {
		return runningTime;
	}

    public void setRuntime(int time)
    {
        runningTime = time;
    }


    }


