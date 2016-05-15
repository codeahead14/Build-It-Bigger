package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;
import android.util.Log;

/**
 * Created by Nivedita on 15-05-2016.
 */
public class NonEmptyStringTest extends AndroidTestCase{
    EndpointsAsyncTask endpointsAsyncTask;
    AsyncResponse asyncResponse = null;

    private static final String LOG_TAG = NonEmptyStringTest.class.getName();

    public void checkAsyncTaskReturn(){
        endpointsAsyncTask = new EndpointsAsyncTask(getContext(), new AsyncResponse() {
            @Override
            public void processFinish(String output) {
                Log.v(LOG_TAG,"Process Finished");
            }
        });
    }
}
