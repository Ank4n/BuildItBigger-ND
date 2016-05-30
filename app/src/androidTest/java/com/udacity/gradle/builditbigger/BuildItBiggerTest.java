package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.test.InstrumentationTestCase;

import java.util.concurrent.TimeUnit;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class BuildItBiggerTest extends InstrumentationTestCase {
    public void testGceResponse() throws Throwable {

        final Context context = getInstrumentation().getTargetContext();
        final CallbackListener helper = new CallbackListener();
        final JokeAsyncTask networkTask = new JokeAsyncTask(context, helper);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                networkTask.execute();
            }
        });


        helper.getSignal().await(30, TimeUnit.SECONDS);
        assertTrue("Network Task failed", helper.getData() != null);

    }


}