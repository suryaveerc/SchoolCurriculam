package com.conneckto.schoolcurriculam;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by suryaveer on 2016-06-17.
 */
public class ViewHolder {

    Button approveBtn;
    Button rejectBtn;
    TextView date;
    TextView day;
    TextView eventName;
    TextView eventType;
    TextView venue;
    TextView parentsAllowed;
    TextView desc;


    public ViewHolder(View v){setViewHolder(v);}
    public void setViewHolder(View v)
    {
        date = (TextView) v.findViewById(R.id.dateValuetextView);
        day = (TextView) v.findViewById(R.id.dayValueTextView);
        eventName = (TextView) v.findViewById(R.id.eventNameValuetextView);
        eventType = (TextView) v.findViewById(R.id.eventTypeValuetextView);
        venue = (TextView) v.findViewById(R.id.venueValuetextView);
        parentsAllowed = (TextView) v.findViewById(R.id.parentsAllowedtextView);
        desc = (TextView) v.findViewById(R.id.descValuetextView);

        approveBtn = (Button) v.findViewById(R.id.approvebutton);
        rejectBtn = (Button) v.findViewById(R.id.rejectbutton);
    }

}
