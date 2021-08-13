package com.example.tournament;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActivitySettings extends AppCompatActivity {
    MyApplication app;
    private static final String TAG = ActivitySettings.class.getSimpleName();
    public static final int ACTIVITY_ID=113;
    TextView textViewUUID;
    EditText editText_Username;
    EditText editText_Email;
    EditText editText_TeamName;
    EditText editText_TeamSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        app = (MyApplication) getApplication();
        textViewUUID = findViewById(R.id.textViewUUID);
        textViewUUID.setText("UUID: " + app.APP_UUID);
        editText_Username = findViewById(R.id.editText_Username);
        editText_Email = findViewById(R.id.editText_Email);
        editText_TeamName = findViewById(R.id.editText_ArrayListTeamName);
        editText_TeamSize = findViewById(R.id.editText_ArrayListTeamSize);
        editText_Username.setText(app.username.toString());
        editText_Email.setText(app.email.toString());
        editText_TeamName.setText(app.myTeam.getName().toString());
        editText_TeamSize.setText(String.valueOf(app.myTeam.getNumberOfPlayers()));

    }

    public void onClickCancelSettings(View view) {
        finish();
    }

    public void onClickSaveSettings(View view) {
        try {
            returnValue(editText_Username.getText().toString(),editText_Email.getText().toString(),
                    editText_TeamName.getText().toString(),Integer.parseInt(editText_TeamSize.getText().toString()));
        }catch (Exception e){
            Log.i(TAG,"Wrong format.");
        }
    }

    private void returnValue(String username, String email, String teamName, int teamSize){
        Intent data = getIntent();
        data.putExtra("username",username);
        data.putExtra("email",email);
        data.putExtra("teamName",teamName);
        data.putExtra("teamSize",teamSize);
        setResult(RESULT_OK,data);
        finish();
    }
}