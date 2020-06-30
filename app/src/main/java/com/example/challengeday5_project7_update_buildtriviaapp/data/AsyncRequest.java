package com.example.challengeday5_project7_update_buildtriviaapp.data;

import com.example.challengeday5_project7_update_buildtriviaapp.model.Question;

import java.util.ArrayList;

public interface AsyncRequest {
    void processFinished(ArrayList<Question> questionArrayList);
}
