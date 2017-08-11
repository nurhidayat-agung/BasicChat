package com.example.kazt.basicchat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ChatRoom extends AppCompatActivity {
    private TextView tvMsg;
    private Button btnSnd;
    private EditText edtMsg;
    private String userName, roomName;
    private DatabaseReference root;
    private String temp_key;

    //new add
    private List<ChatModel> chats = new ArrayList<>();
    private RecyclerView recyclerView;
    private RVChatAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        //new add
        recyclerView = (RecyclerView) findViewById(R.id.rv_chat);
        adapter = new RVChatAdapter(chats, ChatRoom.this);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        btnSnd = (Button) findViewById(R.id.btn_send);
        edtMsg = (EditText) findViewById(R.id.edt_msg);

        // cacth data from mainActivity
        userName = getIntent().getStringExtra("user_name");
        roomName = getIntent().getStringExtra("room_name");

        // enter chat room in firebase
        root = FirebaseDatabase.getInstance().getReference().child(roomName);

        // post data to chat room
        btnSnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> map = new HashMap<String, Object>();

                temp_key = root.push().getKey();
                root.updateChildren(map);
                DatabaseReference msg_root = root.child(temp_key);

                Map<String, Object> msgMap = new HashMap<>();
                msgMap.put("name", userName);
                msgMap.put("msg", edtMsg.getText().toString());
                msg_root.updateChildren(msgMap);
                edtMsg.setText("");
            }
        });

        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                // what we must do
                broadCastMsg(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                // what we must do
                broadCastMsg(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                // what we must do
                broadCastMsg(dataSnapshot);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void broadCastMsg(DataSnapshot dataSnapshot) {
        Iterator i = dataSnapshot.getChildren().iterator();
        String username, msg;
        while (i.hasNext()){
            // actioan  what will we do
            ChatModel chatModel = new ChatModel();
            chatModel.setContent((String) ((DataSnapshot)i.next()).getValue());
            chatModel.setUsername((String) ((DataSnapshot)i.next()).getValue());
            chats.add(chatModel);
            adapter.notifyDataSetChanged();
            recyclerView.scrollToPosition(chats.size()-1);
        }
    }

}
