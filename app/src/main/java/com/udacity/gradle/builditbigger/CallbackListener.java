package com.udacity.gradle.builditbigger;

import java.util.concurrent.CountDownLatch;

/**
 * Created by ankan.
 * TODO: Add a class comment
 */
public class CallbackListener {

    private CountDownLatch signal;
    private String data;

    public CallbackListener() {
        this.signal = new CountDownLatch(1);
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public CountDownLatch getSignal() {

        return signal;
    }

    public void setSignal(CountDownLatch signal) {
        this.signal = signal;
    }
}
