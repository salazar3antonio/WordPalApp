package co.wordpal.mywordpalappfree.quiz_report;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import co.wordpal.mywordpalappfree.data.model.QuizList;

/**
 * Created by salaz on 10/11/2016.
 */

public class QuizReportPagerAdapter extends FragmentPagerAdapter {

    private String mWordOne;
    private String mWordTwo;
    private String mWordThree;
    private String mChoiceOne;
    private String mChoiceTwo;
    private List<QuizList> mQuizList;

    public QuizReportPagerAdapter(FragmentManager fm, List<QuizList> quizLists) {
        super(fm);
        mQuizList = quizLists;
    }

    @Override
    public Fragment getItem(int position) {
        QuizList quizlist = mQuizList.get(position);
        mWordOne = quizlist.getQuiz().get(0);
        mWordTwo = quizlist.getQuiz().get(1);
        mWordThree = quizlist.getQuiz().get(2);
        mChoiceOne = quizlist.getOption().get(0);
        mChoiceTwo = quizlist.getOption().get(1);
        String[] quizChoices = {mChoiceOne, mChoiceTwo};
        String[] quizWords = {mWordOne, mWordTwo, mWordThree};

        int quizAnswer = quizlist.getCorrect();
        int userChoice = quizlist.getUserChoice();

        return QuizReportPageFragment.newInstance(quizAnswer, quizChoices, quizWords, userChoice);
    }

    @Override
    public int getCount() {
        return mQuizList.size();
    }
}
