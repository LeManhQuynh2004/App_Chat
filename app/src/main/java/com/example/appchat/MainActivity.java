package com.example.appchat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.appchat.Adapter.UserAdapter;
import com.example.appchat.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference usersRef;
    RecyclerView recyclerView;
    Toolbar toolbar;
    List<User> list = new ArrayList<>();
    UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        database = FirebaseDatabase.getInstance();
        usersRef = database.getReference("User");
        selectAll();
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.chatListView);
        getSupportActionBar().setTitle("App Chat");
        renderItem();
    }
    private void selectAll() {
        usersRef.get().addOnSuccessListener(dataSnapshot -> {
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                list.clear();
                User user_date = snapshot.getValue(User.class);
                if (user_date != null) {
                    list.add(user_date);
                    userAdapter.notifyDataSetChanged();
                }
            }
        }).addOnFailureListener(er -> {
            Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }
    private void renderItem (){
        userAdapter = new UserAdapter(getApplication(),list);
        recyclerView.setAdapter(userAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplication()));
    }
}


//        findViewById(R.id.bt_send).setOnClickListener(v -> {
//            String username = "quynhlm.dev";
//            String email = "quynhlm.dev@gmail.com";
//            User newUser = new User(username, email);
//
//            DatabaseReference newUserRef = usersRef.push();
//            newUserRef.setValue(newUser).addOnSuccessListener(suc -> {
//                Toast.makeText(this, "Add Success", Toast.LENGTH_SHORT).show();
//            }).addOnFailureListener(er -> {
//                Toast.makeText(this, er.getMessage() + "", Toast.LENGTH_SHORT).show();
//            });
//        });