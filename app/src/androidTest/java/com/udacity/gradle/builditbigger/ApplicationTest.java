package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.content.Context;
import android.support.v4.util.Pair;
import android.test.ApplicationTestCase;
import android.text.TextUtils;
import android.util.Log;

import java.util.concurrent.CountDownLatch;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    private static final String LOG_TAG = ApplicationTest.class.getName();
    CountDownLatch signal = null;
    String asyncTaskString = null;

    public ApplicationTest() {
        super(Application.class);
    }

    @Override
    protected void setUp() throws Exception {
        signal = new CountDownLatch(1);
    }

    @Override
    protected void tearDown() throws Exception {
        signal.countDown();
    }

    public void checkAsynTask() throws InterruptedException{
        new EndpointsAsyncTask(getContext(), new AsyncResponse() {
            @Override
            public void processFinish(String output) {
                Log.v(LOG_TAG,"Process Finished");
                asyncTaskString = output;
            }
        }).execute(new Pair<Context, String>(getContext(), null));
        signal.await();

        Log.v(LOG_TAG,"Test Finished"+asyncTaskString);
        //assertNull(asyncTaskString);
        assertTrue(TextUtils.isEmpty(asyncTaskString));
    }
}