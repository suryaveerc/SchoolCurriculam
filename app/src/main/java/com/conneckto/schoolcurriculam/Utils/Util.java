package com.conneckto.schoolcurriculam.Utils;

import com.conneckto.schoolcurriculam.model.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by suryaveer on 2016-06-06.
 */
public class Util {
    public static final String MONTHS[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    public static final String MONTHS_ABBR[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    private static final String DAYS[]= { "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};

    public static final String MONTH_VIEW_TAG = "MONTH_VIEW";
    public static final String YEAR_VIEW_TAG = "YEAR_VIEW";
    public static final String EVENT_LIST_VIEW_TAG = "EVENT_LIST_VIEW";

    public final static String EVENT_HOLIDAY = "HOLIDAY";
    public static void putObjects (Map<Integer, List<Event>> map, Integer key, Event value) {
        List<Event> myClassList = map.get(key);
        if(myClassList == null) {
            myClassList = new ArrayList<Event>();
            map.put(key, myClassList);
        }
        myClassList.add(value);

    }
}
