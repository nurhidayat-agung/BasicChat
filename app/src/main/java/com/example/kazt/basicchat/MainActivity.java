package com.example.kazt.basicchat;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private ListView lvGrupChat;
    private EditText edtGrupChat;
    private Button btnAddGrup;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> listGrupChat = new ArrayList<>();
    private String username;

    //firebase variabel
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference().getRoot();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = getIntent().getStringExtra("Username");

        lvGrupChat = (ListView) findViewById(R.id.lv_grupchat);
        edtGrupChat = (EditText) findViewById(R.id.edt_grup_chat);
        btnAddGrup = (Button) findViewById(R.id.btn_create_grup_chat);

        // set up list view
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,listGrupChat);
        lvGrupChat.setAdapter(arrayAdapter);


        // post data listener on click button
        // inside method onCreate
        btnAddGrup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> map = new HashMap<String, Object>();
                map.put(edtGrupChat.getText().toString(),"");
                root.updateChildren(map);
                edtGrupChat.setText("");
            }
        });

        // inside method onCreate
        // bellow post data listener
        // this is get Data Listener
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Set<String> set = new HashSet<String>();
                Iterator i = dataSnapshot.getChildren().iterator();
                while (i.hasNext()){
                    set.add(((DataSnapshot)i.next()).getKey());
                }
                listGrupChat.clear();
                listGrupChat.addAll(set);

                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        // inside oncreate method
        lvGrupChat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ChatRoom.class);
                intent.putExtra("room_name", ((TextView)view).getText().toString());
                intent.putExtra("user_name", username);
                startActivity(intent);
            }
        });


    }
}
