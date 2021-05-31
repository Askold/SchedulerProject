package com.example.bottom_project.ui.messenger;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bottom_project.Models.Message;
import com.example.bottom_project.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class MessengerFragment extends Fragment {

    FloatingActionButton sendBtn;

    private RelativeLayout relativeLayout;
    private FirebaseRecyclerAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_messenger, container, false);

        sendBtn = root.findViewById(R.id.btnSendMessage);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText textField= root.findViewById(R.id.message_field);
                if(textField.getText().toString() == "") return;
                FirebaseDatabase.getInstance().getReference().child("Messages").push().setValue(
                        new Message(
                                FirebaseAuth.getInstance().getCurrentUser().getEmail(),
                                textField.getText().toString()
                        )
                );
                textField.setText("");
            }


        });

        RecyclerView listOfMessages = root.findViewById(R.id.list_of_messages);
        listOfMessages.setLayoutManager(new LinearLayoutManager(requireContext()));
        Query query = FirebaseDatabase.getInstance().getReference().child("Messages");

        FirebaseRecyclerOptions<Message> options =
                new FirebaseRecyclerOptions.Builder<Message>()
                        .setQuery(query, Message.class)
                        .build();
        adapter = new MessagesAdapter(options);
        listOfMessages.setAdapter(adapter);


        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }


}