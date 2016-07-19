package com.conneckto.schoolcurriculam.dao;

import com.conneckto.schoolcurriculam.model.Event;

import java.util.List;
import java.util.Map;

/**
 * Created by suryaveer on 2016-06-16.
 */
public interface EventsDAO {


    public abstract List<Event> fetchEvents(int month);
    public abstract List<String> fetchEventTypes();
    public abstract Map<String, String> fetchPriorityEventForEachMonth(int year);
}
