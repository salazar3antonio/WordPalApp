package co.wordpal.mywordpalappfree.database;

import android.util.Log;

import java.util.List;

import co.wordpal.mywordpalappfree.data.model.Quiz;

import static android.content.ContentValues.TAG;

/**
 * Created by salaz on 1/31/2017.
 */

public class QuizListDBInitializer {

    private QuizUserList mQuizUserList;

    // this class will take in a list of quizzes and a level number and create
    // the levels in the corresponding area tables


    public QuizListDBInitializer(QuizUserList quizUserList) {

        mQuizUserList = quizUserList;

    }

    public void createQuizzesInDB(List<Quiz> quizList, String areaName, int numLevels, String areaTable) {

        if (quizList.size() == 0) {

            for (int i = 1; i <= numLevels; i++) {
                Quiz quiz = new Quiz();
                quiz.setArea(areaName);
                quiz.setLevel(i);
                if (i == 1) {
                    quiz.setLockedState(1);
                } else {
                    quiz.setLockedState(0);
                }
                mQuizUserList.addQuiz(areaTable, quiz);
                Log.d(TAG, "Quiz Added " + QuizUserList.getContentValues(quiz));

            }


        }

    }

}
