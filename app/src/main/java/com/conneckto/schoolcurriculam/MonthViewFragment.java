package com.conneckto.schoolcurriculam;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.conneckto.schoolcurriculam.Utils.Util;
import com.conneckto.schoolcurriculam.dao.EventsDAO;
import com.conneckto.schoolcurriculam.dao.EventsDAOImpl;
import com.conneckto.schoolcurriculam.model.Event;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class MonthViewFragment extends Fragment implements OnClickListener {
    private static final String tag = "MyCalendarActivity";

    private TextView currentMonth;
    private Button selectedDayMonthYearButton;
    private ImageView prevMonth;
    private ImageView nextMonth;
    private GridView calendarView;
    private GridCellAdapter adapter;
    private Calendar _calendar;
    private int month, year;
    private final DateFormat dateFormatter = new DateFormat();
    private static final String dateTemplate = "MMMM yyyy";
    private View v;
    private MultiSelectionSpinner spinner;
    private List<String> eventTypes;
    private EventsDAO eventsDAO = new EventsDAOImpl();
    private Button filterBtn;
    private List<String> filteredEventTypes; // selected filters in app
    private List<Event> eventList; // original list from DB, do not change. access via getEventList.
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.month_calendar_view, null);
        spinner = (MultiSelectionSpinner) v.findViewById(R.id.mySpinner1);
        filterBtn = (Button)v.findViewById(R.id.filterBtn);

        eventTypes = eventsDAO.fetchEventTypes();
        ApplicationContextProvider.eventTypes = eventTypes;
        spinner.setItems(eventTypes);
        spinner.setSelection(eventTypes);
        filterBtn.setOnClickListener(this);
        month = _calendar.get(Calendar.MONTH) + 1;
        year = _calendar.get(Calendar.YEAR);

        eventList = eventsDAO.fetchEvents(month);
        if(filteredEventTypes == null)
            filteredEventTypes = eventTypes;//by default all are selected. otherwise already set filter is used from previous months.
        ApplicationContextProvider.filteredEvents = filteredEventTypes;
        Log.d(tag, "Calendar Instance:= " + "Month: " + month + " " + "Year: "
                + year);

        prevMonth = (ImageView) v.findViewById(R.id.prevMonth);
        prevMonth.setOnClickListener(this);

        currentMonth = (TextView) v.findViewById(R.id.currentMonth);
        currentMonth.setText(DateFormat.format(dateTemplate,
                _calendar.getTime()));

        nextMonth = (ImageView) v.findViewById(R.id.nextMonth);
        nextMonth.setOnClickListener(this);

        calendarView = (GridView) v.findViewById(R.id.calendar);

        // Initialised
        adapter = new GridCellAdapter(v.getContext(),
                R.id.calendar_day_gridcell, month, year,  filterEvents(eventList));
        adapter.notifyDataSetChanged();
        calendarView.setAdapter(adapter);
        return v;
    }

    private void setGridCellAdapterToDate(int month, int year, List<Event> finalList) {
        adapter = new GridCellAdapter(v.getContext(),
                R.id.calendar_day_gridcell, month, year, finalList);
        _calendar.set(year, month - 1, _calendar.get(Calendar.DAY_OF_MONTH));
        currentMonth.setText(DateFormat.format(dateTemplate,
                _calendar.getTime()));
        adapter.notifyDataSetChanged();
        calendarView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if (v == prevMonth) {
            if (month <= 1) {
                month = 12;
                year--;
            } else {
                month--;
            }
            Log.d(tag, "Setting Prev Month in GridCellAdapter: " + "Month: "
                    + month + " Year: " + year);
            setGridCellAdapterToDate(month, year, getEventList());
        }
        else if (v == nextMonth) {
            if (month > 11) {
                month = 1;
                year++;
            } else {
                month++;
            }
            Log.d(tag, "Setting Next Month in GridCellAdapter: " + "Month: "
                    + month + " Year: " + year);
            setGridCellAdapterToDate(month, year, getEventList());
        }
        else if (v==filterBtn)
        {
            filteredEventTypes = spinner.getSelectedStrings();
            ApplicationContextProvider.filteredEvents = filteredEventTypes;
            Log.d(tag, "Filtered events: " + filteredEventTypes + " Count: "+ filteredEventTypes.size());

            setGridCellAdapterToDate(month, year,filterEvents(getEventList()));
        }

    }

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
    private List<Event> getEventList()
    {
        return eventList;
    }
    public void setMonth(int month)
    {this.month = month;}

    public void setYear(int year)
    {this.year = year;}

    public void setCalendar(Calendar calendar)
    {
        _calendar = calendar;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(tag,"onActivityCreated");
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setTag(Util.MONTH_VIEW_TAG);
        getView().setOnKeyListener(new CustomListener(getContext()));
    }
}
