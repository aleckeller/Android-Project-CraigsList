package com.example.listview;

import java.util.Comparator;

/**
 * Created by aleckeller on 3/31/16.
 */
public class ComparatorModel implements Comparator<BikeData> {
    @Override
    public int compare(BikeData myData1, BikeData myData2) {
        return myData1.MODEL.compareTo(myData2.MODEL);
    }
}
