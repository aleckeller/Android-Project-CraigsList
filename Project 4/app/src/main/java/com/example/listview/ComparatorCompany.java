package com.example.listview;

import java.util.Comparator;

/**
 * Created by aleckeller on 3/31/16.
 */
public class ComparatorCompany implements Comparator<BikeData> {
    @Override
    public int compare(BikeData myData1, BikeData myData2) {
        return myData1.COMPANY.compareTo(myData2.COMPANY);
    }
}
