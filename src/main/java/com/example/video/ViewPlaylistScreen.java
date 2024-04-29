package com.example.video;

import android.database.Cursor;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.video.DBHelper;

import java.util.ArrayList;

public class ViewPlaylistScreen extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdapter adapter;
    DBHelper myDB;
    ArrayList<String> playlist_id, view_playlist_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_playlist_screen);

        recyclerView = findViewById(R.id.recyclerview);
        myDB = new DBHelper(ViewPlaylistScreen.this);
        view_playlist_url = new ArrayList<>();
        playlist_id = new ArrayList<>();
        adapter = new MyAdapter(this, playlist_id, view_playlist_url);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        displayData();
    }

    private void displayData() {
        Cursor cursor = myDB.getAllPlaylistData();

        while (cursor.moveToNext()){
            playlist_id.add(cursor.getString(0));
            view_playlist_url.add(cursor.getString(1));
        }
    }
}