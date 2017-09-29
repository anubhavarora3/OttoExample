package com.falcon.anubhav.ottoexample;

import com.squareup.otto.Bus;

/**
 * Created by anubhav on 28/09/17.
 */

public class GlobalBus {
    private static Bus bus;

    public static Bus getBus() {
        if (bus == null) {
            bus = new Bus();
        }
        return bus;
    }
}
