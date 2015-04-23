package com.oreilly.demo.android.pa.uidemo.model.clock;

/**
 * The active model of the internal clock that periodically emits tick events.
 *
 * @author laufer
 */
public interface ClockModel extends OnTickSource {
	void start();
	void stop();
}
