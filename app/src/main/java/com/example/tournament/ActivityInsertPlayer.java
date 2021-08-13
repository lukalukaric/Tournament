package com.example.tournament;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.data.Player;
import com.example.data.Team;

public class ActivityInsertPlayer extends AppCompatActivity {
    public static final int ACTIVITY_ID=111;

    private static final String TAG = ActivityInsertPlayer.class.getSimpleName();
    public static final String PLAYER_ID_INSERT_UPDATE = "PLAYER_ID_INSERT_UPDATE";
    public static final int PLAYER_INSERT = 0;
    public static final int PLAYER_UPDATE = 1;

    public static final String POSITION = "POSITON";
    int position;
    Player tmp;

    public static final String DATA_ACTION = "DATA_ACTION";
    public static final int DATA_EXIT = 0;
    public static final int DATA_UPDATED = 1;
    int playerMode;
    EditText editText_Nickname;
    EditText editText_FirstName;
    EditText editText_LastName;
    EditText editText_Age;
    EditText editText_KDRatio;
    Button btn_action;
    MyApplication app;

    Player temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (MyApplication) getApplication();
        setContentView(R.layout.activity_insert);
        editText_Nickname = findViewById(R.id.editText_Nickname);
        editText_FirstName = findViewById(R.id.editText_FirstName);
        editText_LastName = findViewById(R.id.editText_LastName);
        editText_Age = findViewById(R.id.editText_Age);
        editText_KDRatio = findViewById(R.id.editText_KDRatio);
        btn_action = findViewById(R.id.btn_action);

        setPlayerIdFromIntent();
        temp = app.myTeam.getPlayerAtPos(position);
        updateGUI();

    }
    private void setPlayerIdFromIntent(){
        playerMode = PLAYER_INSERT;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            playerMode = extras.getInt(PLAYER_ID_INSERT_UPDATE);
            position = extras.getInt(POSITION);
            Log.i(TAG,"Set Player id mode :" + playerMode);
        }
    }
    void updateGUI(){
        if (playerMode == PLAYER_UPDATE){
            btn_action.setText("Update");
            editText_Nickname.setText(temp.getNickname());
            editText_FirstName.setText(temp.getFirstName());
            editText_LastName.setText(temp.getLastName());
            editText_Age.setText(String.valueOf(temp.getAge()));
            editText_KDRatio.setText(String.valueOf(temp.getKDRatio()));
        }
        else if (playerMode == PLAYER_INSERT){
            btn_action.setText(R.string.insert);
        }
    }

    public void buttonExit(View view) {
        finish();
    }

    public void clearTextOnAddPlayer(){
        editText_Nickname.setText("");
        editText_FirstName.setText("");
        editText_LastName.setText("");
        editText_Age.setText("");
        editText_KDRatio.setText("");
    }
    public void addPlayerOnClickButton(View view) {
        try {
            if(playerMode == PLAYER_INSERT){
                tmp = new Player(editText_Nickname.getText().toString(),editText_FirstName.getText().toString(),editText_LastName.getText().toString(),
                        Integer.parseInt(editText_Age.getText().toString()),Double.parseDouble(editText_KDRatio.getText().toString()));
                app.getMyTeam().addPlayer(tmp);
                returnValue(DATA_UPDATED,editText_Nickname.getText().toString(),editText_FirstName.getText().toString(),
                        editText_LastName.getText().toString(), Integer.parseInt(editText_Age.getText().toString()),
                        Double.parseDouble(editText_KDRatio.getText().toString()));
                clearTextOnAddPlayer();
            }
            else if (playerMode == PLAYER_UPDATE){
                app.myTeam.getPlayerAtPos(position).setNickname(editText_Nickname.getText().toString());
                app.myTeam.getPlayerAtPos(position).setFirstName(editText_FirstName.getText().toString());
                app.myTeam.getPlayerAtPos(position).setLastName(editText_LastName.getText().toString());
                app.myTeam.getPlayerAtPos(position).setAge(Integer.parseInt(editText_Age.getText().toString()));
                app.myTeam.getPlayerAtPos(position).setKDRatio(Double.parseDouble(editText_KDRatio.getText().toString()));
                Intent data = getIntent();
                data.putExtra("mode","update");
                setResult(RESULT_OK,data);
                finish();
                clearTextOnAddPlayer();
            }

        }catch (Exception e){
            Log.i(TAG,"Wrong format.");
        }
    }

    private void returnValue(int dataStatus,String nickName, String firstName, String lastName, int age, double KDRatio){
        Intent data = getIntent();
        data.putExtra(DATA_ACTION,dataStatus);
        data.putExtra("mode","insert");
        data.putExtra("nickName",nickName);
        data.putExtra("firstName",firstName);
        data.putExtra("lastName",lastName);
        data.putExtra("age",age);
        data.putExtra("KDRatio",KDRatio);
        setResult(RESULT_OK,data);
        finish();
    }
}