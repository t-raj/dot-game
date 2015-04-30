package com.oreilly.demo.android.pa.uidemo.test.model.clock;

import com.oreilly.demo.android.pa.uidemo.model.clock.ClockModel;
import com.oreilly.demo.android.pa.uidemo.model.clock.OnTickListener;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;


/**
 * Test case superclass for the autonomous clock model abstraction.
 * Unit-tests the pseudo-real-time behavior of the clock.
 * Uses a simple stub object to satisfy the clock's dependency.
 *
 * @author laufer
 */
public abstract class AbstractClockModelTest {

    private ClockModel model;

    /**
     * Setter for dependency injection. Usually invoked by concrete testcase
     * subclass.
     *
     * @param model
     */
    protected void setModel(final ClockModel model) {
        this.model = model;
    }

    protected ClockModel getModel() {
        return model;
    }

    /**
     * Verifies that a stopped clock does not emit any tick events.
     *
     * @throws InterruptedException
     */
    @Test
    public void testStopped() throws InterruptedException {
        // use a thread-safe object because the timer inside the
        // clock has its own thread
        final AtomicInteger i = new AtomicInteger(0);
        model.setOnTickListener(new OnTickListener() {
            @Override
            public void onTick() {
                i.incrementAndGet();
            }
        });
        Thread.sleep(5500);
        assertEquals(0, i.get());
    }

    /**
     * Verifies that a running clock emits about one tick event per second.
     *
     * @throws InterruptedException
     */
    @Test
    public void testRunning() throws InterruptedException {
        final AtomicInteger i = new AtomicInteger(0);
        model.setOnTickListener(new OnTickListener() {
            @Override
            public void onTick() {
                i.incrementAndGet();
            }
        });
        model.start();
        Thread.sleep(5500);
        model.stop();
        assertEquals(5, i.get());
    }
}
