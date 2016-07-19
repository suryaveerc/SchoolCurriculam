package com.conneckto.schoolcurriculam;

/**
 * Created by suryaveer on 2016-06-19.
 */

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import com.conneckto.schoolcurriculam.dao.EventsDAOImpl;
import com.conneckto.schoolcurriculam.model.Event;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ItemsAdapter extends ArrayAdapter<Event> {
    private final static String tag = "ItemsAdapter";
    Context context;
    int layoutResourceId;
    List<Event> eventList;
    private EventsDAOImpl eventsDAO;
    private LinearLayout parent_layout;

    public ItemsAdapter(Context context, int layoutResourceId, List<Event> eventList) {
        super(context, layoutResourceId, eventList);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.eventList = eventList;
        Log.d(tag,"in constructor "+ eventList.size());
    }


    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;
        Log.d(tag,"In getview");
        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder(row);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        final Event event = eventList.get(position);
        Calendar date= Calendar.getInstance();
        date.setTime(event.getEventStartDate());
        date.clear(Calendar.ZONE_OFFSET);
        date.clear(Calendar.DAY_OF_WEEK);
        holder.date.setText(date.getTime().toString());
        holder.day.setText(date.getDisplayName(Calendar.DAY_OF_WEEK,Calendar.LONG, Locale.US));
        holder.eventName.setText(event.getEventName());
        holder.venue.setText(event.getEventVenue());
        holder.desc.setText(event.getEventDescription());
        holder.parentsAllowed.setText(event.isOpenForParents()? "Yes": "No");
        holder.eventType.setText(event.getEventType());

        return row;
    }
}
