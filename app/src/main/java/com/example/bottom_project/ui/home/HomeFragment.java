package com.example.bottom_project.ui.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.bottom_project.Models.MyEvent;
import com.example.bottom_project.MyEventAdapter;
import com.example.bottom_project.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.rengwuxian.materialedittext.MaterialEditText;

public class HomeFragment extends Fragment {

    FloatingActionButton btnNewEvent;
    private HomeViewModel homeViewModel;

    RelativeLayout rela;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        btnNewEvent = root.findViewById(R.id.btnNewEvent);

        btnNewEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNewEvent();
            }
        });

        MyEvent[] events = makeEvents();
        MyEventAdapter adapter = new MyEventAdapter(requireContext(), events);
        ListView lv = (ListView) root.findViewById(R.id.list);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener((parent, view, position, id) -> {
            //int id =  ((MyEvent)parent.getItemAtPosition(position)).id;
            Intent i = new Intent(Intent.ACTION_VIEW);
            startActivity(i);
        });

        return root;
    }

    private void setNewEvent() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(requireContext());
        dialog.setTitle("Авторизация");
        dialog.setMessage("Введите данные для авторизации");
        LayoutInflater inflater = LayoutInflater.from(requireContext());
        View add_new_event = inflater.inflate(R.layout.add_event_window, null);
        dialog.setView(add_new_event);


        final MaterialEditText place = add_new_event.findViewById(R.id.placeField);
        final MaterialEditText name = add_new_event.findViewById(R.id.nameField);
        final MaterialEditText date = add_new_event.findViewById(R.id.dateField);
        final MaterialEditText time = add_new_event.findViewById(R.id.timeField);

        dialog.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });

        dialog.setPositiveButton("Добавить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (TextUtils.isEmpty(name.getText().toString())){
                    Snackbar.make(rela, "Введите название", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(place.getText().toString())){
                    Snackbar.make(rela, "Введите место", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(date.getText().toString())){
                    Snackbar.make(rela, "Введите дату проведения", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(time.getText().toString())){
                    Snackbar.make(rela, "Введите время", Snackbar.LENGTH_SHORT).show();
                    return;
                }

            }
        });
        dialog.show();
    }

    MyEvent[] makeEvents() {
        String[] names = {"event1", "event2",  "event3", "event4","event5", "event6", "event7",  "event8", "event9","event10"};
        MyEvent[] events = new MyEvent[10];
        MyEvent event;
        for (int i = 0; i < events.length; i++){
            event = new MyEvent(names[i]);
            events[i] = event;
        }
        return events;
    }
}