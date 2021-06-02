package com.google.android.gms.location.sample.basiclocationsample;

import java.util.ArrayList;
import java.util.List;

public class Repeater {

    private static final Object TAG = 666;

    Repeater(String nameArg, Double latitudeArg, Double longitudeArg) {
    final Double latitude, longitude;
    final String name;
        name = nameArg;
        latitude = latitudeArg;
        longitude = longitudeArg;
    }

    public static List<Repeater> createRepeaterList() {
        List<Repeater> allRepeaters = new ArrayList<> ();
        allRepeaters.add (
                new Repeater (
                        "vk3rgl",
                        -37.886427,
                        144.269036));
        allRepeaters.add (
                new Repeater (
                        "vk3rmh",
                        -37.689789,
                        145.222296));
        allRepeaters.add (
                new Repeater (
                        "vk3rmm",
                        -37.383441,
                        144.57678));
        System.out.println ("before exit of Java create...");
        // TODO      Log.i((String) TAG, "after Snackbar");
        return allRepeaters;
    }


}
