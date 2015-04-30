package com.oreilly.demo.android.pa.uidemo.model.clock;

/**
 * The passive data model of the stopwatch.
 * It does not emit any events.
 * added from project 4
 *
 * @author laufer
 */
public interface TimeModel {
	void resetRuntime();
	//void incRuntime();
    void decRuntime();
	public int getRuntime();
    void setRuntime(int time);
}
