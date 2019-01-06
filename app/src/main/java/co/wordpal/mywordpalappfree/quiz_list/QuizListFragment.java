package co.wordpal.mywordpalappfree.quiz_list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.viewpagerindicator.CirclePageIndicator;

import java.util.List;
import java.util.UUID;

import co.wordpal.mywordpalappfree.R;
import co.wordpal.mywordpalappfree.data.model.Quiz;
import co.wordpal.mywordpalappfree.data.model.QuizList;
import co.wordpal.mywordpalappfree.database.QuizUserList;
import co.wordpal.mywordpalappfree.helpers.BonusCountDown;
import co.wordpal.mywordpalappfree.helpers.NoSwipeViewPager;

import static co.wordpal.mywordpalappfree.helpers.AreaLevelListSetup.QUIZ_AREA_TABLE;
import static co.wordpal.mywordpalappfree.elementary.ElementaryAreaFragment.QUIZ_UUID;

/**
 * Created by Desktop on 11/19/2016.
 * This is the fragment that will hold the questions, the pager indicators, and countdown bar.
 */

public class QuizListFragment extends Fragment {

    public static final String TAG = QuizListFragment.class.getName();

    private NoSwipeViewPager mViewPager;
    private PagerAdapter mPagerAdapter;
    private CirclePageIndicator mPagerIndicator;
    private ProgressBar mProgressBar;
    private BonusCountDown mBonusCountDown;
    private LinearLayout mBonusPointsAnimate;
    private TextView mBonusPointsNumber;
    private TextView mBonusPointsPlusSign;

    private Quiz mQuiz;
    private String mAreaTable;
    private List<QuizList> mQuizLists;
    private UUID mQuizID;
    private int mUserChoice;

    public static QuizListFragment newInstance() {
        return new QuizListFragment();
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
        View view = inflater.inflate(R.layout.fragment_quiz_list, container, false);

        mViewPager = view.findViewById(R.id.viewPager);
        mPagerIndicator = view.findViewById(R.id.quiz_report_page_indicator);
        mBonusPointsAnimate = view.findViewById(R.id.bonus_points_animate);
        mBonusPointsNumber = view.findViewById(R.id.bonus_points_plus);
        mBonusPointsPlusSign = view.findViewById(R.id.bonus_plus_sign);
        mProgressBar = view.findViewById(R.id.progressbar);

        mPagerAdapter = new QuizListPagerAdapter(getFragmentManager(), mQuizLists);
        mViewPager.setAdapter(mPagerAdapter);
        mPagerIndicator.setViewPager(mViewPager);


        mPagerIndicator.setClickable(false);
        mPagerIndicator.setOnClickListener(null);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

            mProgressBar.setProgress(100);
            mProgressBar.setProgressTintList(ContextCompat.getColorStateList(getActivity(), R.color.md_green_300));
            mBonusCountDown = new BonusCountDown(100000, 100, mProgressBar, new BonusCountDown.TickerUpdateListener() {
                @Override
                public void onCheckTicker(int progress, ProgressBar progressBar) {
                    switch (progress) {
                        case 75:
                            progressBar.setProgressTintList(ContextCompat.getColorStateList(getActivity(), R.color.md_yellow_300));
                            break;
                        case 50:
                            progressBar.setProgressTintList(ContextCompat.getColorStateList(getActivity(), R.color.md_orange_300));
                            break;
                        case 25:
                            progressBar.setProgressTintList(ContextCompat.getColorStateList(getActivity(), R.color.md_red_300));
                            break;
                    }
                }
            });

            mBonusCountDown.start();

    }

    @Override
    public void onPause() {
        stopCountDown();
        super.onPause();
    }

    @Override
    public void onStop() {
        stopCountDown();
        super.onStop();

    }

    public void reportUserAnswers() {

        int bonusPoints = mProgressBar.getProgress();
        int currentPage = mViewPager.getCurrentItem();
        QuizList quizlist = mQuizLists.get(currentPage);
        quizlist.setUserChoice(mUserChoice);
        quizlist.setBonusPoints(bonusPoints);
        fadeBonusPoints(mBonusPointsAnimate);
        mBonusPointsPlusSign.setText("+");
        mBonusPointsNumber.setText(String.valueOf(bonusPoints));

    }

    public void totalUpUserAnswers() {

        int totalPoints = 0;
        int totalCorrect = 0;
        int totalBonusPoints = 0;

        for (int i = 0; i < mQuizLists.size(); i++) {

            QuizList quizList = mQuizLists.get(i);
            int correctAnswer = quizList.getCorrect();
            int userChoice = quizList.getUserChoice();
            int bonusPoints = quizList.getBonusPoints();

            if (correctAnswer == userChoice) {
                totalCorrect = totalCorrect + 1;
                totalBonusPoints = totalBonusPoints + bonusPoints;
                totalPoints = totalCorrect * 10;
            } else {
                quizList.setBonusPoints(0);
            }

        }

        mQuiz.setTotalUserScore(totalPoints + totalBonusPoints);
        mQuiz.setTotalCorrect(totalCorrect);

        if (totalCorrect == 1 || totalCorrect == 2) {
            mQuiz.setTotalStars(1);
        }

        if (totalCorrect == 3 || totalCorrect == 4) {
            mQuiz.setTotalStars(2);
        }

        if (totalCorrect == 5 || totalCorrect == 6 || totalCorrect == 7) {
            mQuiz.setTotalStars(3);
        }

        if (totalCorrect == 8 || totalCorrect == 9) {
            mQuiz.setTotalStars(4);
        }

        if (totalCorrect == 10) {
            mQuiz.setTotalStars(5);
        }

        //sets the completed status of the quiz
        mQuiz.setCompletedState(1);

        // serialize mQuizLists back to a String and set it to mQuiz
        String jsonString = mQuiz.quizListToJSONString(mQuizLists);
        mQuiz.setQuizListJSONString(jsonString);

        //save all new data to the quiz row in the DB
        QuizUserList.get(getActivity()).updateQuiz(mAreaTable, mQuiz);

        Log.d(TAG, "QuizList with User answers " + mQuiz.getQuizListJSONString());
    }

    public void resetCountDown() {

        mBonusCountDown.cancel();
        mProgressBar.setProgressTintList(ContextCompat.getColorStateList(getActivity(), R.color.md_green_300));
        mBonusCountDown = new BonusCountDown(100000, 100, mProgressBar, new BonusCountDown.TickerUpdateListener() {
            @Override
            public void onCheckTicker(int progress, ProgressBar progressBar) {
                switch (progress) {
                    case 75:
                        progressBar.setProgressTintList(ContextCompat.getColorStateList(getActivity(), R.color.md_yellow_300));
                        break;
                    case 50:
                        progressBar.setProgressTintList(ContextCompat.getColorStateList(getActivity(), R.color.md_orange_300));
                        break;
                    case 25:
                        progressBar.setProgressTintList(ContextCompat.getColorStateList(getActivity(), R.color.md_red_300));
                        break;
                }
            }
        });
        mBonusCountDown.start();
    }

    public void resumeCountDown() {

        mProgressBar.setProgress(mProgressBar.getProgress());
        mBonusCountDown.start();

    }

    public void stopCountDown() {
        mBonusCountDown.cancel();
    }

    public void fadeBonusPoints(LinearLayout linearLayout) {
        Animation fadeIn = AnimationUtils.loadAnimation(getActivity(), R.anim.bonus_point_fade_in);
        linearLayout.startAnimation(fadeIn);
        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                blankOutBonusPoints();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    public void blankOutBonusPoints() {
        mBonusPointsPlusSign.setText("");
        mBonusPointsNumber.setText("");
//        mBonusPointsText.setText("");
    }

    public void setUserChoice(int userChoice) {
        mUserChoice = userChoice;
    }

    public int getCurrentViewPagerPage() {
        return mViewPager.getCurrentItem();
    }

    public void nextPage() {
        mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
    }


}
