package com.example.challengeday5_project7_update_buildtriviaapp.util;

import android.app.Activity;
import android.content.SharedPreferences;

public class SharedPrefs {

    private SharedPreferences sharedPreferences;

    public SharedPrefs(Activity activity) {

        this.sharedPreferences = activity.getPreferences(activity.MODE_PRIVATE);
    }

    public SharedPrefs() {
    }

    public void saveHighScore(int score){
        int currentScore = score;
        int lastScore = sharedPreferences.getInt("high_score",0);
        if(currentScore>lastScore){
            sharedPreferences.edit().putInt("high_score",currentScore).apply();
        }
    }

    public int getHighScore(){
        return sharedPreferences.getInt("high_score",0);
    }

    public void setState(int index){
        sharedPreferences.edit().putInt("current_state",index).apply();
    }

    public int getState(){
        return sharedPreferences.getInt("current_state",0);
    }

    public void setLastScore(int score){
        sharedPreferences.edit().putInt("last_score",score).apply();
    }

    public int getLastScore(){
        return sharedPreferences.getInt("last_score",0);
    }
}
