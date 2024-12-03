package com.capacitor.appodeal;

import android.util.Log;

public class AppodealPlugin {

    public String echo(String value) {
        Log.i("Echo", value);
        return value;
    }
}
