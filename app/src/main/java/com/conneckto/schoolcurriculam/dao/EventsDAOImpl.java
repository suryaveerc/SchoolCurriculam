package com.conneckto.schoolcurriculam.dao;

import com.conneckto.schoolcurriculam.ApplicationContextProvider;
import com.conneckto.schoolcurriculam.model.Event;

import java.util.List;
import java.util.Map;

/**
 * Created by suryaveer on 2016-06-16.
 */
public class EventsDAOImpl implements EventsDAO{
    private List<Event> eventList;
    @Override
    public List<Event> fetchEvents(int month) {
        ApplicationContextProvider applicationContextProvider = new ApplicationContextProvider();
        eventList = applicationContextProvider.eventList;
        return eventList;
    }

    @Override
    public List<String> fetchEventTypes() {
        return ApplicationContextProvider.eventTypes;
    }
    @Override
    public Map<String, String> fetchPriorityEventForEachMonth(int year)
    {
        return ApplicationContextProvider.priorityEvents;
    }
}
