package com.example.bottom_project.ui.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.bottom_project.Models.MyEvent;
import com.example.bottom_project.MyEventAdapter;
import com.example.bottom_project.R;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.rengwuxian.materialedittext.MaterialEditText;

public class HomeFragment extends Fragment {

    FloatingActionButton btnNewEvent;
    private HomeViewModel homeViewModel;

    private RelativeLayout rela;
    private FirebaseListAdapter<MyEvent> adapter;

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

        ListView listOfEvents = root.findViewById(R.id.list_of_events);
        Query query = FirebaseDatabase.getInstance().getReference().child("Events");

        FirebaseListOptions<MyEvent> options =
                new FirebaseListOptions.Builder<MyEvent>()
                        .setQuery(query, MyEvent.class)
                        .setLayout(R.layout.list_events)
                        .build();
        adapter = new FirebaseListAdapter<MyEvent>(options) {
            @Override
            protected void populateView(@NonNull View v, @NonNull MyEvent model, int position) {
                TextView place, name, date, time;
                name = root.findViewById(R.id.name);
                place = root.findViewById(R.id.place);
                date = root.findViewById(R.id.date);
                time = root.findViewById(R.id.time);

                name.setText(model.getName());
                time.setText(model.getTime());
                place.setText(model.getPlace());
                date.setText(model.getDate());
            }
        };

        listOfEvents.setAdapter(adapter);

        return root;
    }

    private void setNewEvent() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(requireContext());
        dialog.setTitle("Новое мероприятие");
        dialog.setMessage("Введите данные нового мероприятия");
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
                FirebaseDatabase.getInstance().getReference().child("Events").setValue(
                        new MyEvent(name.getText().toString(),
                                place.getText().toString(),
                                date.getText().toString(),
                                time.getText().toString()));
            }
        });
        dialog.show();
    }

}