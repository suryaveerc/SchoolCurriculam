package com.conneckto.schoolcurriculam;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.conneckto.schoolcurriculam.Utils.Util;
import com.conneckto.schoolcurriculam.dao.EventsDAO;
import com.conneckto.schoolcurriculam.dao.EventsDAOImpl;
import com.conneckto.schoolcurriculam.model.Event;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by suryaveer on 2016-06-19.
 */

public class YearViewFragment extends Fragment implements View.OnClickListener{
    private final static String tag = "YearViewFragment";
    private View v;
    private TextView currentYear;
    private ImageView prevYear;
    private ImageView nextYear;
    private GridView calendarView;
    private YearViewGridCellAdapter adapter;
    private Calendar _calendar;
    private int year;
    private Map<String,String> priorityEvent;
    private EventsDAO eventsDAO = new EventsDAOImpl();
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.annual_calendar_view, null);


        _calendar = Calendar.getInstance(Locale.getDefault());

        year = _calendar.get(Calendar.YEAR);
        priorityEvent  = eventsDAO.fetchPriorityEventForEachMonth(year);
        Log.d(tag, "Calendar Instance:= " + "Year: "+ year);

        prevYear = (ImageView) v.findViewById(R.id.prevYear);
        prevYear.setOnClickListener(this);

        currentYear = (TextView) v.findViewById(R.id.currentYear);
        currentYear.setText(year+"");

        nextYear = (ImageView) v.findViewById(R.id.nextYear);
        nextYear.setOnClickListener(this);

        calendarView = (GridView) v.findViewById(R.id.annual_calendar);
        //calendarView.setNumColumns(4);
        // Initialised
        adapter = new YearViewGridCellAdapter(v.getContext(), R.id.calendar_month_gridcell, year, priorityEvent);
        adapter.notifyDataSetChanged();
        calendarView.setAdapter(adapter);
        return v;
    }


    private void setYearViewGridCellAdapter(int year) {
        Log.d(tag, "setYearViewGridCellAdapter: " + year);
        adapter = new YearViewGridCellAdapter(v.getContext(), R.id.calendar_month_gridcell,  year, priorityEvent);
        _calendar.set(Calendar.YEAR,year);
        currentYear.setText(year+"");
        Log.d(tag, "setYearViewGridCellAdapter: " + year + " " + _calendar.get(Calendar.YEAR));
        adapter.notifyDataSetChanged();
        calendarView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if (v == prevYear) {
                year--;
            Log.d(tag, "Setting Prev Year in GridCellAdapter: " + year);
            setYearViewGridCellAdapter(year);
        }
        else if (v == nextYear) {
                year++;

            Log.d(tag, "Setting Next Year in GridCellAdapter: " + year);
            setYearViewGridCellAdapter(year);
        }

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(tag,"onActivityCreated");
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setTag(Util.YEAR_VIEW_TAG);
        getView().setOnKeyListener(new CustomListener(getContext()));
    }

}