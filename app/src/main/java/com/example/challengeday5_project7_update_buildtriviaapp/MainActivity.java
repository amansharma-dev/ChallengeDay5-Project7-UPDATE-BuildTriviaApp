package com.example.challengeday5_project7_update_buildtriviaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.challengeday5_project7_update_buildtriviaapp.data.AsyncRequest;
import com.example.challengeday5_project7_update_buildtriviaapp.data.QuestionBank;
import com.example.challengeday5_project7_update_buildtriviaapp.model.Question;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String  TAG = "MainActivity";
    private List<Question> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        questionList = new QuestionBank().getQuestions(new AsyncRequest() {
            @Override
            public void processFinished(ArrayList<Question> questionArrayList) {
                Log.d(TAG, "processFinished: "+questionArrayList);
            }
        });

    }
}
