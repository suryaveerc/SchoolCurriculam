package com.conneckto.schoolcurriculam;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.conneckto.schoolcurriculam.Utils.Util;
import com.conneckto.schoolcurriculam.dao.EventsDAO;
import com.conneckto.schoolcurriculam.dao.EventsDAOImpl;
import com.conneckto.schoolcurriculam.model.Event;

import java.util.ArrayList;
import java.util.List;


public class EventDetailsFragment extends Fragment implements View.OnClickListener{

    private final static String tag= "EventDetailsFragment";
    private int day;
    private ListView eventListView;
    private List<String> filteredEventTypes;
    private MultiSelectionSpinner spinner;
    private List<String> eventTypes;
    private Button filterBtn;
    private ItemsAdapter itemsAdapter;
    private List<Event> eventList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        
        Log.d(tag,day+"");
        View v = inflater.inflate(R.layout.event_details_list_view, container, false);
        eventListView = (ListView) v.findViewById(R.id.eventDetailListView);
        eventList = ApplicationContextProvider.getCollegeEventMap().get(day);
        Log.d(tag,"Number of events on" + day + eventList.size());
        filteredEventTypes = ApplicationContextProvider.filteredEvents;
        spinner = (MultiSelectionSpinner) v.findViewById(R.id.mySpinner1);
        filterBtn = (Button)v.findViewById(R.id.filterBtn);
        eventTypes = ApplicationContextProvider.eventTypes;
        spinner.setItems(eventTypes);
        spinner.setSelection(eventTypes);
        filterBtn.setOnClickListener(this);
        itemsAdapter = new ItemsAdapter(getContext(), R.layout.event_details, filterEvents(eventList));
        eventListView.setAdapter(itemsAdapter);
        eventListView.setOnItemClickListener(new CustomListener(getContext()));
        return v;
    }

    public void setDay(int day)
    {this.day = day;}


    private List<Event> filterEvents(List<Event> eventList)
    {
        List<Event> filteredEventsList = new ArrayList<>();
        for(Event event : eventList)
        {
            Log.d(tag,"Checking filter for event: "+event.getEventName());
            if(filteredEventTypes.contains(event.getEventType()))
                filteredEventsList.add(event);
        }
        return filteredEventsList;
    }

    @Override
    public void onClick(View v) {
     if (v==filterBtn)
        {
            filteredEventTypes = spinner.getSelectedStrings();
            ApplicationContextProvider.filteredEvents = filteredEventTypes;
            Log.d(tag, "Filtered events: " + filteredEventTypes + " Count: "+ filteredEventTypes.size());

            itemsAdapter = new ItemsAdapter(getContext(), R.layout.event_details, filterEvents(eventList));
            eventListView.setAdapter(itemsAdapter);
            eventListView.setOnItemClickListener(new CustomListener(getContext()));
        }

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(tag,"onActivityCreated");
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setTag(Util.EVENT_LIST_VIEW_TAG);
        getView().setOnKeyListener(new CustomListener(getContext()));
    }
}
