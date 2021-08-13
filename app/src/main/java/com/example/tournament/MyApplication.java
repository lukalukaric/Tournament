package com.example.tournament;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.data.Team;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class MyApplication extends Application {
    public Team myTeam;
    private Gson gson;
    private static final String TAG = MyApplication.class.getSimpleName();
    private static final String MY_FILE_NAME = "podatki.json";
    private File file;

    public static final String TAG_NAME = "UUID";
    public String APP_UUID = UUID.randomUUID().toString().replace("-", "");
    SharedPreferences sp;
    /*
    Zagotovljeni edinstveni identifikator vključuje sklic na omrežni naslov gostitelja,
     ki ustvarja UUID, časovni žig in poljubno komponento.
     Ker se omrežni naslovi za vsak računalnik razlikujejo,
      je časovni žig drugačen tudi za vsak ustvarjen UUID.
      Tako dva različna gostiteljska stroja kažeta zadostno
      stopnjo edinstvenosti. Za večjo varnost je dodana naključno ustvarjena
      poljubna komponenta.
     */
    public String username = "";
    public static final String TAG_USERNAME = "Username";
    public String email = "";
    public static final String TAG_EMAIL = "Email";
    public static final String TAG_TEAM_NAME = "Team name";
    public static final String TAG_TEAM_SIZE = "Team size";

    public Team getMyTeam() {
        return myTeam;
    }

    public void setMyTeam(Team myTeam) {
        this.myTeam = myTeam;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (!readFromFile())
            myTeam = new Team();
        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if (sp.contains(TAG_NAME))
            APP_UUID = sp.getString(TAG_NAME,"DEFAULT VALUE ");
        else { //FIRST TIME GENERATE ID AND SAVE IT
            SharedPreferences.Editor editor = sp.edit();
            editor.putString(TAG_NAME,APP_UUID);
            editor.apply();
        }
        Log.i(TAG, "UUID: " + APP_UUID);

        if (sp.contains(TAG_USERNAME))
            username = sp.getString(TAG_USERNAME,"DEFAULT VALUE ");
        else {
            SharedPreferences.Editor editor = sp.edit();
            editor.putString(TAG_USERNAME,username);
            editor.apply();
        }

        if (sp.contains(TAG_EMAIL))
            email = sp.getString(TAG_EMAIL,"DEFAULT VALUE ");
        else {
            SharedPreferences.Editor editor = sp.edit();
            editor.putString(TAG_EMAIL,email);
            editor.apply();
        }

        if (sp.contains(TAG_TEAM_NAME))
            myTeam.setName(sp.getString(TAG_TEAM_NAME,"DEFAULT VALUE "));
        else {
            SharedPreferences.Editor editor = sp.edit();
            editor.putString(TAG_TEAM_NAME,myTeam.getName());
            editor.apply();
        }

        if (sp.contains(TAG_TEAM_SIZE))
            myTeam.setNumberOfPlayers(sp.getInt(TAG_TEAM_SIZE,5));
        else {
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt(TAG_TEAM_SIZE,myTeam.getNumberOfPlayers());
            editor.apply();
        }
    }

    public void saveData() {
        saveToFile();
    }


    private Gson getGson() {
        if (gson == null)
            gson = new GsonBuilder().setPrettyPrinting().create();
        return gson;
    }

    private File getFile() {
        if (file == null) {
            File filesDir = getFilesDir();
            file = new File(filesDir, MY_FILE_NAME);
        }
        Log.i(TAG, "File saved at: " + file.getPath());
        return file;
    }

    private void saveToFile() {
        try {
            FileUtils.writeStringToFile(getFile(), getGson().toJson(myTeam));
        } catch (IOException e) {
            Log.i(TAG, "cannot save file at: " + file.getPath());
        }
    }

    private boolean readFromFile() {
        if (!getFile().exists())  return false;
        try {
            myTeam = getGson().fromJson(FileUtils.readFileToString(getFile()), Team.class);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public void updateSharedPreferences() {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(TAG_USERNAME,username);
        editor.putString(TAG_EMAIL,email);
        editor.putString(TAG_TEAM_NAME,myTeam.getName());
        editor.putInt(TAG_TEAM_SIZE,myTeam.getNumberOfPlayers());
        editor.putString(TAG_NAME,APP_UUID);
        editor.apply();
    }
}
