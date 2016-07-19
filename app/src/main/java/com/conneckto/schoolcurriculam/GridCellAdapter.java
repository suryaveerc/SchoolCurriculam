package com.conneckto.schoolcurriculam;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.conneckto.schoolcurriculam.Utils.Util;
import com.conneckto.schoolcurriculam.dao.EventsDAO;
import com.conneckto.schoolcurriculam.dao.EventsDAOImpl;
import com.conneckto.schoolcurriculam.model.Event;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by suryaveer on 2016-06-19.
 */
public class GridCellAdapter extends BaseAdapter {
    private static final String tag = "GridCellAdapter";
    private final Context _context;

    private final List<String> list;
    private static final int DAY_OFFSET = 1;
    private final String[] weekdays = new String[]{"Sun", "Mon", "Tue",
            "Wed", "Thu", "Fri", "Sat"};
    private final String[] months = {"January", "February", "March",
            "April", "May", "June", "July", "August", "September",
            "October", "November", "December"};
    private final int[] daysOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30,
            31, 30, 31};
    private int daysInMonth;
    private int currentDayOfMonth;
    private int currentWeekDay;
    private int currentMonth;
    private Button gridcell;


    private final SimpleDateFormat dateFormatter = new SimpleDateFormat(
            "dd-MM-yyyy");
    private EventsDAO eventsDAO = new EventsDAOImpl();
    private List<Event> eventList;
    private Map<Integer, List<Event>> holidayMap;
    private Map<Integer, List<Event>> collegeEventMap;
    private GregorianCalendar cal;


    // Days in Current Month
    public GridCellAdapter(Context context, int textViewResourceId,
                           int month, int year, List<Event> eventList) {
        super();
        this._context = context;
        this.list = new ArrayList<String>();
        this.eventList = eventList;
        Log.d(tag, "==> Passed in Date FOR Month: " + month + " "
                + "Year: " + year);
        Calendar calendar = Calendar.getInstance();
        setCurrentDayOfMonth(calendar.get(Calendar.DAY_OF_MONTH));
        setCurrentWeekDay(calendar.get(Calendar.DAY_OF_WEEK));
        Log.d(tag, "New Calendar:= " + calendar.getTime().toString());
        Log.d(tag, "CurrentDayOfWeek :" + getCurrentWeekDay());
        Log.d(tag, "CurrentDayOfMonth :" + getCurrentDayOfMonth());
        currentMonth = month;

        holidayMap = new HashMap<Integer, List<Event>>();
        collegeEventMap = new HashMap<Integer, List<Event>>();
        // Print Month

        printMonth(month, year);
        ApplicationContextProvider.setHolidayMap(holidayMap);
        ApplicationContextProvider.setCollegeEventMap(collegeEventMap);
        //   Log.d(tag, "Holidays: " + holidayMap.toString() + holidayMap.size());
        //   Log.d(tag, "Events: " + collegeEventMap.toString() + collegeEventMap.size());
    }

    private String getMonthAsString(int i) {
        return months[i];
    }

    private String getWeekDayAsString(int i) {
        return weekdays[i];
    }

    private int getNumberOfDaysOfMonth(int i) {
        return daysOfMonth[i];
    }

    public String getItem(int position) {
        return list.get(position);
    }

    private void checkEventOnThisDay(Calendar cal) {

        for (Event event : eventList) {
            Calendar dateStart = Calendar.getInstance();
            dateStart.setTime(event.getEventStartDate());
            Calendar dateEnd = Calendar.getInstance();
            dateEnd.setTime(event.getEventEndDate());


            if (dateStart.get(Calendar.DAY_OF_MONTH) <= cal.get(Calendar.DAY_OF_MONTH) && dateEnd.get(Calendar.DAY_OF_MONTH) >= cal.get(Calendar.DAY_OF_MONTH))
            //if(dateStart.compareTo(currDate) >= 0 && dateEnd.compareTo(currDate) <= 0 )
            {
                //  Log.d(tag,"*************************  "+event.getEventType());
                if (event.getEventType().equalsIgnoreCase(Util.EVENT_HOLIDAY))
                    Util.putObjects(holidayMap, cal.get(Calendar.DAY_OF_MONTH), event);
                else
                    Util.putObjects(collegeEventMap, cal.get(Calendar.DAY_OF_MONTH), event);

                        /*if(currDate.compareTo(dateEnd) == 0)
                            eventList.remove(event); // to remove event which is past current date so as to reduce number of loops.*/

            }
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    private void printMonth(int mm, int yy) {
        Log.d(tag, "==> printMonth: mm: " + mm + " " + "yy: " + yy);

        int trailingSpaces = 0;
        int daysInPrevMonth = 0;
        int prevMonth = 0;
        int prevYear = 0;
        int nextMonth = 0;
        int nextYear = 0;

        int currentMonth = mm - 1;
        String currentMonthName = getMonthAsString(currentMonth);
        daysInMonth = getNumberOfDaysOfMonth(currentMonth);

        Log.d(tag, "Current Month: " + " " + currentMonthName + " having "
                + daysInMonth + " days.");

        cal = new GregorianCalendar(yy, currentMonth, 1);
        Log.d(tag, "Gregorian Calendar:= " + cal.getTime().toString());

        if (currentMonth == 11) {
            prevMonth = currentMonth - 1;
            daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
            nextMonth = 0;
            prevYear = yy;
            nextYear = yy + 1;
            Log.d(tag, "*->PrevYear: " + prevYear + " PrevMonth:"
                    + prevMonth + " NextMonth: " + nextMonth
                    + " NextYear: " + nextYear);
        } else if (currentMonth == 0) {
            prevMonth = 11;
            prevYear = yy - 1;
            nextYear = yy;
            daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
            nextMonth = 1;
            Log.d(tag, "**--> PrevYear: " + prevYear + " PrevMonth:"
                    + prevMonth + " NextMonth: " + nextMonth
                    + " NextYear: " + nextYear);
        } else {
            prevMonth = currentMonth - 1;
            nextMonth = currentMonth + 1;
            nextYear = yy;
            prevYear = yy;
            daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
            Log.d(tag, "***---> PrevYear: " + prevYear + " PrevMonth:"
                    + prevMonth + " NextMonth: " + nextMonth
                    + " NextYear: " + nextYear);
        }

        int currentWeekDay = cal.get(Calendar.DAY_OF_WEEK) - 1;
        trailingSpaces = currentWeekDay;

        Log.d(tag, "Week Day:" + currentWeekDay + " is "
                + getWeekDayAsString(currentWeekDay));
        Log.d(tag, "No. Trailing space to Add: " + trailingSpaces);
        Log.d(tag, "No. of Days in Previous Month: " + daysInPrevMonth);

        if (cal.isLeapYear(cal.get(Calendar.YEAR)))
            if (mm == 2)
                ++daysInMonth;
            else if (mm == 3)
                ++daysInPrevMonth;

        // Trailing Month days
        for (int i = 0; i < trailingSpaces; i++) {
                /*Log.d(tag,
                        "PREV MONTH:= "
                                + prevMonth
                                + " => "
                                + getMonthAsString(prevMonth)
                                + " "
                                + String.valueOf((daysInPrevMonth
                                - trailingSpaces + DAY_OFFSET)
                                + i));*/
            list.add(String
                    .valueOf((daysInPrevMonth - trailingSpaces + DAY_OFFSET)
                            + i)
                    + "-GREY"
                    + "-"
                    + prevMonth
                    + "-"
                    + prevYear + "-" + "SKIP");
        }

        String daySetup;

        // Current Month Days
        for (int i = 1; i <= daysInMonth; i++) {
            // Log.d(currentMonthName, String.valueOf(i) + " "
            //       + getMonthAsString(currentMonth) + " " + yy);
            cal.set(yy, mm - 1, i);
            checkEventOnThisDay(cal);
            if (i == getCurrentDayOfMonth()) {
                daySetup = String.valueOf(i) + "-BLUE" + "-"
                        + currentMonth + "-" + yy;

            } else {
                daySetup = String.valueOf(i) + "-WHITE" + "-"
                        + currentMonth + "-" + yy;
            }


            list.add(daySetup);
        }

        // Leading Month days
        for (int i = 0; i < list.size() % 7; i++) {
            //  Log.d(tag, "NEXT MONTH:= " + getMonthAsString(nextMonth));
            list.add(String.valueOf(i + 1) + "-GREY" + "-"
                    + nextMonth + "-" + nextYear + "-" + "SKIP");
        }
    }

    public boolean isWeekend(Calendar cal) {
        return cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) _context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.month_view_gridcell, parent, false);
        }

        // Get a reference to the Day gridcell
        gridcell = (Button) row.findViewById(R.id.calendar_day_gridcell);

        String[] day_color = list.get(position).split("-");
        String theday = day_color[0];
        String themonth = day_color[2];
        String theyear = day_color[3];

        cal.set(Integer.valueOf(theyear), Integer.valueOf(themonth), Integer.valueOf(theday));
        // Set the Day GridCell
        gridcell.setText(theday);
        gridcell.setTag(Integer.parseInt(theday));
        //Log.d(tag, "Setting GridCell "+ currentMonth +" ** " + theday + "-" + themonth + "-"                    + theyear);

        if (day_color[1].equals("GREY")) {
            gridcell.setTextColor(_context.getResources()
                    .getColor(R.color.lightgray));
        }
        if (day_color[1].equals("WHITE")) {
            gridcell.setTextColor(_context.getResources().getColor(
                    R.color.lightgray02));
        }
        if (day_color[1].equals("BLUE")) {
            gridcell.setTextColor(_context.getResources().getColor(R.color.orrange));
        }

        List<Event> holidayList = holidayMap.get(Integer.parseInt(theday));
        if (currentMonth - 1 == Integer.valueOf(themonth) && isWeekend(cal) || currentMonth - 1 == Integer.valueOf(themonth) && holidayList != null) {
            gridcell.setBackgroundColor(_context.getResources().getColor(R.color.sky));
            if (holidayList != null) {
                String holidayDrawable = holidayList.get(0).getEventName();
                if (holidayDrawable != null)
                    gridcell.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, _context.getResources().getIdentifier(holidayDrawable, "drawable", _context.getPackageName()));
            }
        }
        List<Event> otherEventList = collegeEventMap.get(Integer.parseInt(theday));
        if (currentMonth - 1 == Integer.valueOf(themonth) && otherEventList != null) {
            gridcell.setOnClickListener(new CustomListener(_context));
            String eventDrawable = otherEventList.get(0).getEventName();
            if (eventDrawable != null)
                gridcell.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, _context.getResources().getIdentifier(eventDrawable, "drawable", _context.getPackageName()));

        }

        return row;
    }


    public int getCurrentDayOfMonth() {
        return currentDayOfMonth;
    }

    private void setCurrentDayOfMonth(int currentDayOfMonth) {
        this.currentDayOfMonth = currentDayOfMonth;
    }

    public void setCurrentWeekDay(int currentWeekDay) {
        this.currentWeekDay = currentWeekDay;
    }

    public int getCurrentWeekDay() {
        return currentWeekDay;
    }
}
