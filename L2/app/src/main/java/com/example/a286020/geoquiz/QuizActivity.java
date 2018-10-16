package com.example.a286020.geoquiz;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    private ImageButton mFalseButton;
    private ImageButton mTrueButton;
    private ImageButton mPreviousButton;
    private TextView mQuestionTextView;

    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_stolica_polski, true),
            new Question(R.string.question_stolica_dolnego_slaska, false),
            new Question(R.string.question_sniezka, true),
            new Question(R.string.question_wisla, true)
    };

    private int mCurrentIndex = 0;
    private int numberOfCorrectAnswers = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mQuestionTextView = findViewById(R.id.question_text);
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkIfQuizEnded()) {
                    mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                    updateQuestion();
                } else {
                    showQuizResults();
                }

            }
        });

        mFalseButton = findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mQuestionBank[mCurrentIndex].getIsAnswered()) {
                    checkAnswer(false);
                }
            }
        });

        mTrueButton = findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mQuestionBank[mCurrentIndex].getIsAnswered()) {
                    checkAnswer(true);
                }
            }
        });

        mPreviousButton = findViewById(R.id.previous_button);
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkIfQuizEnded()) {
                    mCurrentIndex = (mCurrentIndex + (mQuestionBank.length - 1) ) % mQuestionBank.length;
                    updateQuestion();
                } else {
                    showQuizResults();
                }
            }
        });

        updateQuestion();
    }

    void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();

        mQuestionTextView.setText(question);
    }

    void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageResId = 0;

        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
            numberOfCorrectAnswers += 1;
        } else {
            messageResId = R.string.incorrect_toast;
        }

        Toast t = Toast.makeText(this, messageResId, Toast.LENGTH_SHORT);
        t.setGravity(Gravity.TOP, 0, 200);
        t.show();

        mQuestionBank[mCurrentIndex].setAnswered(true);
    }

    boolean checkIfQuizEnded() {
        int answeredCount = 0;

        for (Question q : mQuestionBank) {
            if (q.getIsAnswered()) {
                answeredCount += 1;
            }
        }

        return answeredCount == mQuestionBank.length;
    }

    void showQuizResults() {
        CharSequence seq = "Quiz ended\n Correct answers: " + numberOfCorrectAnswers + " / " + mQuestionBank.length;
        Toast toast = Toast.makeText(getApplicationContext(), seq, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
