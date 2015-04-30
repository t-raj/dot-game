package com.oreilly.demo.android.pa.uidemo.test.model.clock;

import com.oreilly.demo.android.pa.uidemo.model.clock.DefaultClockModel;

import org.junit.After;
import org.junit.Before;


/**
 * Concrete testcase subclass for the default clock model implementation.
 *
 * @author laufer
 * @see xunitpatterns.com/Testcase%20Superclass.html
 */
public class DefaultClockModelTest extends AbstractClockModelTest {

    @Before
    public void setUp() throws Exception {
        setModel(new DefaultClockModel());
    }

    @After
    public void tearDown() throws Exception {
        setModel(null);
    }
}
