package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.udacity.gradle.builditbigger.networkAPI.EndPoint;
import com.udacity.gradle.builditbigger.networkAPI.onJokeReceived;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by S.Shivasurya on 5/8/2016.
 */
public class JokeTest extends ApplicationTestCase<Application> {

    CountDownLatch signal;
    String mJoke;

    public JokeTest() {
        super(Application.class);
    }

    public void testJoke() {
        try {
            signal = new CountDownLatch(1);
            new EndPoint().execute(new onJokeReceived() {
                @Override
                public void OnJokeReceivedListener(String joke) {
                    mJoke = joke;
                    signal.countDown();
                }
            });
            signal.await(10, TimeUnit.SECONDS);
            assertNotNull("joke is null", mJoke);
            assertFalse("joke is empty", mJoke.isEmpty());
        } catch (Exception ex) {
            fail();
        }
    }
}
