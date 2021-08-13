package com.example.tournament;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Toast;

import com.example.data.Player;
import com.example.data.Team;
import com.google.android.material.tabs.TabLayout;

public class ActivityMain extends AppCompatActivity {
    private static final String TAG = ActivityMain.class.getSimpleName();
    Player temp;
    MyApplication app;

    private RecyclerView recyclerView;
    private AdapterTeam adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        app = (MyApplication) getApplication();

        recyclerView = findViewById(R.id.recyclerView);
        initAdapter();
        //app.myTeam.addPlayer(Player.generatePlayers(100)); generiramo
        Log.i(TAG,"stevilo je: " + app.myTeam.getSize());
    }

    private void initAdapter() {
        adapter = new AdapterTeam(app, new AdapterTeam.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Log.i(TAG,"click on: " + position );
                onClickOpenUpdatePlayerForResult(itemView,position);
                app.saveData();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onItemLongClick(View itemView, int position) {
                Log.i(TAG,"long click on: " + position );
                app.myTeam.removeAt(position);
                app.saveData();
                adapter.notifyDataSetChanged();
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void onClickOpenInsertPlayerForResult(View view) {
        Intent i = new Intent(getBaseContext(), ActivityInsertPlayer.class);
        i.putExtra(ActivityInsertPlayer.PLAYER_ID_INSERT_UPDATE, ActivityInsertPlayer.PLAYER_INSERT);
        startActivityForResult(i,ActivityInsertPlayer.ACTIVITY_ID);
    }

    public void onClickOpenUpdatePlayerForResult(View view, int position) {
        Intent i = new Intent(getBaseContext(), ActivityInsertPlayer.class);
        i.putExtra(ActivityInsertPlayer.PLAYER_ID_INSERT_UPDATE, ActivityInsertPlayer.PLAYER_UPDATE);
        i.putExtra(ActivityInsertPlayer.POSITION, position);
        startActivityForResult(i,ActivityInsertPlayer.ACTIVITY_ID);
    }

    /*public void onClickOpenUpdatePlayerForResult(View view) {
        Intent i = new Intent(getBaseContext(), ActivityInsertPlayer.class);
        i.putExtra(ActivityInsertPlayer.PLAYER_ID_INSERT_UPDATE, ActivityInsertPlayer.PLAYER_UPDATE);
        startActivityForResult(i,ActivityInsertPlayer.ACTIVITY_ID);
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ActivityInsertPlayer.ACTIVITY_ID)
            if (resultCode == RESULT_OK) {
                Log.i(TAG, "Received value: " + data.getExtras().get(ActivityInsertPlayer.DATA_ACTION));
                Toast.makeText(this, R.string.toastOnActivitiResultMainActivity, Toast.LENGTH_LONG).show();
                String mode = data.getExtras().getString("mode");
                if(mode == "insert") {
                    String nickname = data.getExtras().getString("nickName");
                    String firstname = data.getExtras().getString("firstName");
                    String lastname = data.getExtras().getString("lastName");
                    int age = data.getExtras().getInt("age");
                    double kdratio = data.getExtras().getDouble("KDRatio");
                    temp = new Player(nickname, firstname, lastname, age, kdratio);
                    Log.i(TAG, temp.toString());
                    app.getMyTeam().addPlayer(temp);

                }
                app.saveData();
                adapter.notifyDataSetChanged();
                Log.i(TAG, app.getMyTeam().toString());
                vibrate();
            }
            else {
                Toast.makeText(this, R.string.toastOnActivityResultMainActivityFailed, Toast.LENGTH_LONG).show();
                vibrate();
            }
        else if (requestCode == ActivitySettings.ACTIVITY_ID) {
            if (resultCode == RESULT_OK) {
                String username = data.getExtras().getString("username");
                String email = data.getExtras().getString("email");
                String teamName = data.getExtras().getString("teamName");
                int teamSize = data.getExtras().getInt("teamSize");
                app.username = username;
                app.email = email;
                app.myTeam.setName(teamName);
                app.myTeam.setNumberOfPlayers(teamSize);
                app.updateSharedPreferences();
                Toast.makeText(this,R.string.toastOnActivitiResultMainActivity, Toast.LENGTH_LONG).show();
                vibrate();
            }
            else{
                Toast.makeText(this,R.string.toastOnActivityResultMainActivityFailed, Toast.LENGTH_LONG).show();
                vibrate();
            }
        }
        else{
            Toast.makeText(this,R.string.toastOnActivityResultMainActivityFailed, Toast.LENGTH_LONG).show();
            vibrate();
        }
    }

    public void onClickOpenGoogle(View view) {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.si/"));
        startActivity(i);
    }

    public void onClickOpenInfoActivity(View view) {
        Intent i = new Intent(getBaseContext(), ActivityInfoPlayers.class);
        i.putExtra("myTeam", app.getMyTeam().toString());
        startActivity(i);
    }
    private void vibrate(){
        Vibrator v = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            v.vibrate(VibrationEffect.createOneShot(500,VibrationEffect.DEFAULT_AMPLITUDE));
        else
            v.vibrate(500);
    }

    public void onClickOpenSettingsActivity(View view) {
        Intent i = new Intent(getBaseContext(), ActivitySettings.class);
        startActivityForResult(i,ActivitySettings.ACTIVITY_ID);
    }
}