package com.oreilly.demo.android.pa.uidemo.test;

import android.annotation.TargetApi;
import android.test.ActivityInstrumentationTestCase2;
import com.oreilly.demo.android.pa.uidemo.TouchMe;
import com.oreilly.demo.android.pa.uidemo.test.android.AbstractTouchMeTest;

/**
 * Created by Kyle on 4/29/2015.
 */
public class TouchMeTest extends ActivityInstrumentationTestCase2<TouchMe> {

    @TargetApi(8)
    public TouchMeTest(){
        super(TouchMe.class);
        actualTest = new AbstractTouchMeTest() {
            @Override
            protected TouchMe getActivity() {
                return TouchMeTest.this.getActivity();
            }
        };
    }

    private AbstractTouchMeTest actualTest;


    public void testActivityCheckTestCaseSetUpProperly() {
        actualTest.testActivityCheckTestCaseSetUpProperly();
    }

    public void testTouchMeAllOver(){
        actualTest.testRemoveVulnerableMonster();
        actualTest.testRemoveInvulnerableMonster();
        //actualTest.testOnTouch(int[] location);
    }

}
