package com.conneckto.schoolcurriculam.model;

import java.util.Date;

/**
 * Created by suryaveer on 2016-06-08.
 */
public class Event {

    private Date eventStartDate;
    private Date eventEndDate;
    private String eventName;
    private String eventType;
    private String eventDescription ;
    private String eventVenue ;
    private boolean openForParents;
    private boolean schoolLevel;
    private long classId;
    private int priority;

    @Override
    public String toString() {
        return "Event{" +
                "eventStartDate=" + eventStartDate +
                ", eventEndDate=" + eventEndDate +
                ", eventName='" + eventName + '\'' +
                ", eventType='" + eventType + '\'' +
                ", eventDescription='" + eventDescription + '\'' +
                ", eventVenue='" + eventVenue + '\'' +
                ", classId=" + classId +
                '}';
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public long getClassId() {
        return classId;
    }

    public void setClassId(long classId) {
        this.classId = classId;
    }

    public Date getEventStartDate() {
        return eventStartDate;
    }

    public void setEventStartDate(Date eventStartDate) {
        this.eventStartDate = eventStartDate;
    }

    public Date getEventEndDate() {
        return eventEndDate;
    }

    public void setEventEndDate(Date eventEndDate) {
        this.eventEndDate = eventEndDate;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventVenue() {
        return eventVenue;
    }

    public void setEventVenue(String eventVenue) {
        this.eventVenue = eventVenue;
    }

    public boolean isOpenForParents() {
        return openForParents;
    }

    public void setOpenForParents(boolean openForParents) {
        this.openForParents = openForParents;
    }

    public boolean isSchoolLevel() {
        return schoolLevel;
    }

    public void setSchoolLevel(boolean schoolLevel) {
        this.schoolLevel = schoolLevel;
    }
}
