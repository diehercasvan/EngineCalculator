package com.edibca.enginecalculator;

/**
 * Created by DIEGO CASALLAS on 8/03/2016.
 */

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import java.util.HashMap;

public class TrackingAnalytics extends Application {
    private static final String PROPERTY_ID = "UA-67282532-2";

    public enum TrackerName {
        APP_TRACKER,
        // GLOBAL_TRACKER,
        //E_COMMERCE_TRACKER,

    }
    HashMap<TrackerName,Tracker>hashMap=new HashMap<>();

    public synchronized  Tracker getTracker(TrackerName trackerId)
    {
        if (!hashMap.containsKey(trackerId)) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);

            //analytics.getLogger().setLogLevel(Logger.LogLevel.VERBOSE);
            Tracker t = null;
            if(trackerId== TrackerName.APP_TRACKER){
                t= analytics.newTracker(PROPERTY_ID);
            }
            hashMap.put(trackerId, t);
        }
        return hashMap.get(trackerId);
    };

}
