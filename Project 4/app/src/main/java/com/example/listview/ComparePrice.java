package com.example.listview;

import java.util.Comparator;

/**
 * Created by aleckeller on 3/31/16.
 */
    public class ComparePrice implements Comparator<BikeData> {
        @Override
        public int compare(BikeData myData1, BikeData myData2) {
            return Double.compare(myData1.PRICE,myData2.PRICE);
        }
    }
