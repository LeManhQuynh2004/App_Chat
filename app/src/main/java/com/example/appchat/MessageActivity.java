package com.example.appchat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appchat.Adapter.ChatRecyclerAdapter;
import com.example.appchat.Model.Message;
import com.example.appchat.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends AppCompatActivity {
    Toolbar toolbar;
    private EditText messageEditText;
    private Button btn_send;
    private RecyclerView recyclerView;
    FirebaseDatabase database;
    DatabaseReference messageRef;
    ChatRecyclerAdapter chatRecyclerAdapter;
    List<Message> list = new ArrayList<>();
    public void initView() {
        toolbar = findViewById(R.id.toolbarMessage);
        setSupportActionBar(toolbar);
        messageEditText = findViewById(R.id.messageEditText);
        btn_send = findViewById(R.id.sendButton);
        database = FirebaseDatabase.getInstance();
        messageRef = database.getReference("Message");
        recyclerView = findViewById(R.id.chatMessage);
        selectAll();
        chatRecyclerAdapter = new ChatRecyclerAdapter(getApplication(),list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplication()));
        recyclerView.setAdapter(chatRecyclerAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        initView();
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = messageEditText.getText().toString().trim();
                if (!message.isEmpty()) {
                    DatabaseReference newUserRef = messageRef.push();
                    boolean sentByCurrentUser = true;
                    Message message1 = new Message(message,sentByCurrentUser);
                    newUserRef.setValue(message1).addOnSuccessListener(suc -> {
                        Toast.makeText(getApplication(), "Send thành công", Toast.LENGTH_SHORT).show();
                        list.add(message1);
                        chatRecyclerAdapter.notifyDataSetChanged();
                    }).addOnFailureListener(er -> {
                        Toast.makeText(getApplication(), er.getMessage() + "", Toast.LENGTH_SHORT).show();
                    });
                    messageEditText.setText("");
                } else {
                    Toast.makeText(MessageActivity.this, "Please enter a message", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void selectAll() {
        messageRef.get().addOnSuccessListener(dataSnapshot -> {
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                Message message = snapshot.getValue(Message.class);
                if (message != null) {
                    Log.e("TAG", "selectAll: "+message.getContent());
                    list.add(message);
                    chatRecyclerAdapter.notifyDataSetChanged();
                }
            }
        }).addOnFailureListener(er -> {
            Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }
}