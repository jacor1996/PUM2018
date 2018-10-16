package com.example.a286020.geoquiz;

public class Question {
    private int mTextResId;
    private boolean mAnswerTrue;
    private boolean mIsAnswered;

    public Question(int textResId, boolean anserTrue)
    {
        mTextResId = textResId;
        mAnswerTrue = anserTrue;
        mIsAnswered = false;
    }

    public int getTextResId()
    {
        return mTextResId;
    }

    public void setTextResId(int textResId)
    {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue)
    {
        mAnswerTrue = answerTrue;
    }

    public boolean getIsAnswered()
    {
        return mIsAnswered;
    }

    public void setAnswered(boolean isAnswered)
    {
        mIsAnswered = isAnswered;
    }
}
