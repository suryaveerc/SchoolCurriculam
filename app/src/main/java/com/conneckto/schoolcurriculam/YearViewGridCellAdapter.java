package com.conneckto.schoolcurriculam;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.conneckto.schoolcurriculam.Utils.Util;

import java.util.Calendar;
import java.util.Map;

/**
 * Created by suryaveer on 2016-06-17.
 */
public class YearViewGridCellAdapter extends BaseAdapter {
    private final static String tag="YearViewGridCellAdapter";
    Context _context;
    int year;
    private Button gridcell;
    private Map<String,String> priorityEvent;
    public YearViewGridCellAdapter(Context context, int textViewResourceId, int year, Map<String, String> priorityEvent)
    {
        super();
        _context = context;
        this.year = year;
        this.priorityEvent = priorityEvent;
        Log.d(tag,"constructor called");
    }
    @Override
    public int getCount() {
        return Util.MONTHS_ABBR.length;
    }

    @Override
    public Object getItem(int position) {
        return Util.MONTHS_ABBR[position];
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
            row = inflater.inflate(R.layout.annual_view_gridcell, parent, false);
        }

        // Get a reference to the Month gridcell
        gridcell = (Button) row.findViewById(R.id.calendar_month_gridcell);

        Log.d(tag, "Setting GridCell "+ Util.MONTHS_ABBR[position]+"-"+ year);
        // Set the Day GridCell
        Calendar calTag = Calendar.getInstance();
        calTag.clear();
        calTag.set(Calendar.MONTH,position);
        calTag.set(Calendar.YEAR,year);
        gridcell.setText(Util.MONTHS_ABBR[position]);
        gridcell.setTextColor(_context.getResources()
                .getColor(R.color.darkorrange));
        gridcell.setTag(calTag);
        gridcell.setOnClickListener(new CustomListener(_context));
        String eventDrawable = priorityEvent.get(Util.MONTHS[position]);
        if (eventDrawable != null)
            gridcell.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, _context.getResources().getIdentifier(eventDrawable, "drawable", _context.getPackageName()));
        return row;
    }
}
