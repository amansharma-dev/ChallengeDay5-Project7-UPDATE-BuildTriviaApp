package com.example.challengeday5_project7_update_buildtriviaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.challengeday5_project7_update_buildtriviaapp.data.AsyncRequest;
import com.example.challengeday5_project7_update_buildtriviaapp.data.QuestionBank;
import com.example.challengeday5_project7_update_buildtriviaapp.model.Question;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String  TAG = "MainActivity";
    private List<Question> questionList;
    private TextView counterQuestionTv;
    private CardView cardView;
    private TextView answerTextViewCv;
    private Button previousBtn;
    private Button nextBtn;
    private Button trueBtn;
    private Button falseBtn;

    private int currentQuestionIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counterQuestionTv = findViewById(R.id.questionCounterTextView);
        cardView = findViewById(R.id.cardView);
        answerTextViewCv = findViewById(R.id.answerCardView);
        previousBtn = findViewById(R.id.previousButton);
        nextBtn = findViewById(R.id.nextButton);
        trueBtn = findViewById(R.id.trueButton);
        falseBtn = findViewById(R.id.falseButton);

        cardView.setCardBackgroundColor(getResources().getColor(R.color.colorTextTitle));
        previousBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);
        trueBtn.setOnClickListener(this);
        falseBtn.setOnClickListener(this);

        questionList = new QuestionBank().getQuestions(new AsyncRequest() {
            @Override
            public void processFinished(ArrayList<Question> questionArrayList) {

                answerTextViewCv.setText(questionArrayList.get(currentQuestionIndex).getAnswer());
                counterQuestionTv.setText(currentQuestionIndex+" / "+questionArrayList.size());
                Log.d(TAG, "processFinished: "+questionArrayList);
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.previousButton:
                if(currentQuestionIndex>0){
                    currentQuestionIndex = (currentQuestionIndex -1) % questionList.size();
                    updateQuestion();
                }else Toast.makeText(getApplicationContext(),"Can\'t go back",Toast.LENGTH_SHORT).show();

                break;

            case R.id.nextButton:
                currentQuestionIndex = (currentQuestionIndex + 1) % questionList.size();
                updateQuestion();
                break;

            case R.id.trueButton:
                checkIfAnswerTrue(true);
                break;

            case R.id.falseButton:
                checkIfAnswerTrue(false);
                break;
        }
    }

    private void updateQuestion(){
        answerTextViewCv.setText(questionList.get(currentQuestionIndex).getAnswer());
        counterQuestionTv.setText(currentQuestionIndex+" / "+questionList.size());
    }
    private void checkIfAnswerTrue(boolean isAnswerTrue){
        boolean userAnswer = questionList.get(currentQuestionIndex).isAnswerTrue();
        if(userAnswer == isAnswerTrue){
            Toast.makeText(getApplicationContext(),"Correct Answer",Toast.LENGTH_SHORT).show();
        }else Toast.makeText(getApplicationContext(),"Wrong Answer",Toast.LENGTH_SHORT).show();
    }
}
