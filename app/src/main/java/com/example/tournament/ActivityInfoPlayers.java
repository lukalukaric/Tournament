package com.example.tournament;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.example.data.Player;
import com.example.data.Team;

public class ActivityInfoPlayers extends AppCompatActivity {
    EditText editTextInfo;
    MyApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_players);
        editTextInfo = findViewById(R.id.editTextInfo);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String tmp = extras.getString("myTeam");
            editTextInfo.setText(tmp);
        }
        app = (MyApplication) getApplication();
    }
}