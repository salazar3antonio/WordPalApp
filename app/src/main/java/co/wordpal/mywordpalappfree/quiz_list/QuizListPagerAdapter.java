package co.wordpal.mywordpalappfree.quiz_list;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import co.wordpal.mywordpalappfree.data.model.QuizList;

public class QuizListPagerAdapter extends FragmentPagerAdapter {

    public static final String TAG = QuizListPagerAdapter.class.getName();

    private String mWordOne;
    private String mWordTwo;
    private String mWordThree;
    private String mChoiceOne;
    private String mChoiceTwo;
    private List<QuizList> mQuizList;


    public QuizListPagerAdapter(FragmentManager fm, List<QuizList> quizlists) {
        super(fm);
        mQuizList = quizlists;
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

        return QuizListPageFragment.newInstance(quizAnswer, quizChoices, quizWords);
    }

    @Override
    public int getCount() {
        return mQuizList.size();
    }


}
