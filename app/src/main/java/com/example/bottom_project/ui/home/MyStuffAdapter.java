package com.example.bottom_project.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.bottom_project.MyEvent;
import com.example.bottom_project.R;


public class MyStuffAdapter extends ArrayAdapter<MyWorker> {
    public MyStuffAdapter(@NonNull Context context, @NonNull MyWorker[] objects) {
        super(context, R.layout.stuff_item, objects);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyWorker worker = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.stuff_item, null);

            ((TextView) convertView.findViewById(R.id.surname)).setText(worker.surname);
            ((TextView) convertView.findViewById(R.id.name)).setText(worker.name);
        }
        return convertView;
    }
}
