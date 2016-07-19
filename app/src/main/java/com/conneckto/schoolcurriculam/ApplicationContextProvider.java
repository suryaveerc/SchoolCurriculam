package com.conneckto.schoolcurriculam;

import com.conneckto.schoolcurriculam.Utils.Util;
import com.conneckto.schoolcurriculam.model.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by suryaveer on 2016-06-16.
 */
public class ApplicationContextProvider {
    private final static SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    public List<Event> eventList;
    private static Map<Integer, List<Event>> holidayMap;
    private static Map<Integer, List<Event>> collegeEventMap;
    public static Map<String, String> priorityEvents;
    public static List<String> eventTypes;
    public static List<String> filteredEvents;

    static
    {
        priorityEvents = new HashMap<>();
        priorityEvents.put("January","exam");
        priorityEvents.put("June","christmas");
        eventTypes = new ArrayList<>();
        eventTypes.add("sports");
        eventTypes.add("academic");
        eventTypes.add("extra Curricular");
        eventTypes.add("outdoor");
        eventTypes.add("exams");
        eventTypes.add("concert");
        eventTypes.add("holiday");


    }
    public ApplicationContextProvider()
    {



        Event event1 = new Event();
        Event event2 = new Event();
        Event event3 = new Event();
        Event event4 = new Event();
        try {
            event1.setEventEndDate(formatter.parse("02-06-2016 11:20"));
            event1.setEventStartDate(formatter.parse("01-06-2016 10:20"));

            event2.setEventEndDate(formatter.parse("06-06-2016 00:00"));
            event2.setEventStartDate(formatter.parse("06-06-2016 00:00"));

            event3.setEventEndDate(formatter.parse("14-06-2016 00:00"));
            event3.setEventStartDate(formatter.parse("14-06-2016 00:00"));

            event4.setEventEndDate(formatter.parse("08-06-2016 13:20"));
            event4.setEventStartDate(formatter.parse("08-06-2016 10:20"));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        event1.setClassId(4);
        event1.setEventDescription("Concert");
        event1.setEventName("concert");
        event1.setEventType("concert");
        event1.setEventVenue("School");
        event1.setOpenForParents(false);
        event1.setSchoolLevel(true);


        event2.setClassId(5);
        event2.setEventDescription("christmas");
        event2.setEventName("christmas");
        event2.setEventType(Util.EVENT_HOLIDAY.toLowerCase());
        event2.setEventVenue("School");
        event2.setOpenForParents(true);
        event2.setSchoolLevel(true);

        event3.setClassId(5);
        event3.setEventDescription("Desc");
        event3.setEventName("chess");
        event3.setEventType("sports");
        event3.setEventVenue("School");
        event3.setOpenForParents(true);
        event3.setSchoolLevel(true);

        event4.setClassId(5);
        event4.setEventDescription("Desc");
        event4.setEventName("Badminton");
        event4.setEventType("sports");
        event4.setEventVenue("School");
        event4.setOpenForParents(true);
        event4.setSchoolLevel(true);



        eventList = new ArrayList<>();
        eventList.add(event1);
        eventList.add(event2);
        eventList.add(event3);
        eventList.add(event4);

    }
    public static SimpleDateFormat getDateFormat(){
        return formatter;
    }

    public static Map<Integer, List<Event>> getCollegeEventMap() {
        return collegeEventMap;
    }

    public static void setCollegeEventMap(Map<Integer, List<Event>> map) {
        collegeEventMap = map;

    }

    public static Map<Integer, List<Event>> getHolidayMap() {
        return holidayMap;
    }

    public static void setHolidayMap(Map<Integer, List<Event>> map) {
        holidayMap = map;
    }
}
