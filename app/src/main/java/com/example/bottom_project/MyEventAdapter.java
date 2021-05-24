package com.example.bottom_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.bottom_project.Models.MyEvent;

public class MyEventAdapter extends ArrayAdapter<MyEvent> {
    public MyEventAdapter(@NonNull Context context, @NonNull MyEvent[] objects) {
        super(context, R.layout.list_events, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyEvent event = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_events, null);
        }
        ((TextView) convertView.findViewById(R.id.name)).setText(event.name);
        CheckBox ch = (CheckBox) convertView.findViewById(R.id.possible);
        ch.setChecked(event.possible);
        ch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                event.possible = ((CheckBox) v).isChecked();
            }
        });

        return convertView;
    }
}
