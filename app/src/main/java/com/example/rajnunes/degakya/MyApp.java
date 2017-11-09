package com.example.rajnunes.degakya;

import android.app.Application;
import android.os.SystemClock;

/**
 * Created by rajnunes on 7/11/17.
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SystemClock.sleep(2000);
    }
}
