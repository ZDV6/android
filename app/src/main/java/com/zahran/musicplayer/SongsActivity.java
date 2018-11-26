package com.zahran.musicplayer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SongsActivity extends AppCompatActivity {
    ListView listView;
    List<String> list;
    ListAdapter adapter;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);

listView = (ListView) findViewById(R.id.list_item);

list = new ArrayList<>();

Field[] fields = R.raw.class.getFields();
for (int i = 0;i < fields.length;i++){
    list.add(fields[i].getName());
}



adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
listView.setAdapter(adapter);


listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }

        int resID = getResources().getIdentifier(list.get(i), "raw", getPackageName());
        mediaPlayer = mediaPlayer.create(SongsActivity.this, resID);
        mediaPlayer.start();

    }
});
}
}