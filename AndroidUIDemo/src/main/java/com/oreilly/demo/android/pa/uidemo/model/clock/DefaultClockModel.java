package com.oreilly.demo.android.pa.uidemo.model.clock;

import java.util.Timer;
import java.util.TimerTask;

/**
 * An implementation of the internal clock.
 *
 * @author laufer
 */
public class DefaultClockModel implements ClockModel {
	private Timer timer;

	private OnTickListener listener;

	@Override
	public void setOnTickListener(final OnTickListener listener) {
		this.listener = listener;
	}

	@Override
	public void start() {
		timer = new Timer();

		// The clock model runs onTick every 1000 milliseconds
		timer.schedule(new TimerTask() {
			@Override public void run() {
				// fire event
				listener.onTick();
			}
		}, /*initial delay*/ 1000, /*periodic delay*/ 1000);
	}

	@Override
	public void stop() {
		timer.cancel();
	}
}