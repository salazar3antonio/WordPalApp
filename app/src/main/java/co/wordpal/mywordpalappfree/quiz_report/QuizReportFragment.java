package co.wordpal.mywordpalappfree.quiz_report;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.viewpagerindicator.CirclePageIndicator;

import java.util.List;
import java.util.UUID;

import co.wordpal.mywordpalappfree.R;
import co.wordpal.mywordpalappfree.data.model.Quiz;
import co.wordpal.mywordpalappfree.data.model.QuizList;
import co.wordpal.mywordpalappfree.database.QuizUserList;

import static co.wordpal.mywordpalappfree.helpers.AreaLevelListSetup.QUIZ_AREA_TABLE;
import static co.wordpal.mywordpalappfree.elementary.ElementaryAreaFragment.QUIZ_UUID;

/**
 * Created by salaz on 11/27/2016.
 */

public class QuizReportFragment extends Fragment {



    private Quiz mQuiz;
    private String mAreaTable;
    private UUID mQuizID;
    private List<QuizList> mQuizLists;
    private ViewPager mViewPager;
    private CirclePageIndicator mPagerIndicator;
    private PagerAdapter mPagerAdapter;
    private TextView mUserFinalScore;
    private TextView mUserNumberCorrect;

    public static QuizReportFragment newInstance() {
        return new QuizReportFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        savedInstanceState = getArguments();
        if (savedInstanceState != null) {
            mQuizID = (UUID) savedInstanceState.getSerializable(QUIZ_UUID);
            mAreaTable = savedInstanceState.getString(QUIZ_AREA_TABLE);
        }

        mQuiz = QuizUserList.get(getActivity()).getQuiz(mAreaTable, mQuizID);
        String jsonString = mQuiz.getQuizListJSONString();
        mQuizLists = mQuiz.quizListFROMjsonString(jsonString);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_quiz_report_nohs, container, false);

        mUserFinalScore = view.findViewById(R.id.user_final_score);
        mUserNumberCorrect = view.findViewById(R.id.user_number_correct);
        mPagerIndicator = view.findViewById(R.id.quiz_report_page_indicator);

        mViewPager = view.findViewById(R.id.quiz_report_viewPager);
        mPagerAdapter = new QuizReportPagerAdapter(getFragmentManager(), mQuizLists);
        mViewPager.setAdapter(mPagerAdapter);
        mPagerIndicator.setViewPager(mViewPager);

        //disable the page indicators
        mPagerIndicator.setClickable(false);
        mPagerIndicator.setOnClickListener(null);

        //set the user total number correct and their total score
        mUserNumberCorrect.setText(String.valueOf(mQuiz.getTotalCorrect()));
        mUserFinalScore.setText(String.valueOf(mQuiz.getTotalUserScore()));

        return view;
        
    }

}
