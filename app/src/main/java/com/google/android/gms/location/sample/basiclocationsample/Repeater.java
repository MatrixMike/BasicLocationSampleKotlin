package com.google.android.gms.location.sample.basiclocationsample;

import java.util.ArrayList;
import java.util.List;

public class Repeater {

    private static final Object TAG = 666;
    // String name;
    final Double latitude;
    final String name;

    Repeater(String nameArg, Double latitudeArg) {

        name = nameArg;
        latitude = latitudeArg;
    }

    public static List<Repeater> createRepeaterList() {
        List<Repeater> allRepeaters = new ArrayList<> ();
        allRepeaters.add (
                new Repeater (
                        "vk3rgl",
                        2.4));
        allRepeaters.add (
                new Repeater (
                        "vk3rglx",
                        2.3));
        System.out.println ("before exit of Java create...");
        // TODO      Log.i((String) TAG, "after Snackbar");
        return allRepeaters;
    }


}
