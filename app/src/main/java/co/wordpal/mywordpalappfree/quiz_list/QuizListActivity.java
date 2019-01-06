package co.wordpal.mywordpalappfree.quiz_list;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.List;
import java.util.UUID;

import co.wordpal.mywordpalappfree.R;
import co.wordpal.mywordpalappfree.data.api.QuizAPI;
import co.wordpal.mywordpalappfree.data.model.Quiz;
import co.wordpal.mywordpalappfree.data.model.QuizList;
import co.wordpal.mywordpalappfree.database.QuizUserList;
import co.wordpal.mywordpalappfree.helpers.Constants;
import co.wordpal.mywordpalappfree.quiz_report.QuizReportActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static co.wordpal.mywordpalappfree.elementary.ElementaryAreaFragment.QUIZ_UUID;
import static co.wordpal.mywordpalappfree.helpers.AreaLevelListSetup.QUIZ_AREA_TABLE;

/**
 * Created by Desktop on 11/19/2016.
 * This activity needs to load a spinner by default while the api is being called.
 * Upon a successful result of the api, populate a fragment that contains the quizList and bonus count down.
 * This activity will need to set the QuizList of the response so the quizList fragment can get it from the DB.
 */

public class QuizListActivity extends AppCompatActivity implements QuizListPageFragment.UserAnswerListener {

    public static final String TAG = QuizListActivity.class.getName();
    public static final String QUIZ_LIST_FRAGMENT_TAG = "quiz_list_fragment";

    private List<QuizList> mResponseQuizLists;
    private Quiz mQuiz;
    private String mAreaTable;
    private UUID mQuizID;
    private Call<Quiz> mQuizzesCall;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mQuizID = (UUID) bundle.getSerializable(QUIZ_UUID);
            mAreaTable = bundle.getString(QUIZ_AREA_TABLE);
        }

        mQuiz = QuizUserList.get(getApplicationContext()).getQuiz(mAreaTable, mQuizID);

        setActionBarTitles(mQuiz.getArea(), mQuiz.getLevel());

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {

            //placing in the loading screen for when quiz api is being called
            QuizListLoadingFragment quizListLoadingFragment = QuizListLoadingFragment.newInstance();
            quizListLoadingFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, quizListLoadingFragment).commit();

        }

        //make call using Retrofit and fetch a Word Quiz
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.QUIZ_END_POINT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        QuizAPI quizAPI = retrofit.create(QuizAPI.class);
        mQuizzesCall = quizAPI.getQuiz(mQuiz.getArea(), mQuiz.getLevel());
        mQuizzesCall.enqueue(new Callback<Quiz>() {
            @Override
            public void onResponse(Call<Quiz> call, Response<Quiz> response) {

                if (response.isSuccessful()) {
                    String logMsg = response.body().getResultMsg();
                    String logCode = response.body().getResultCode();

                    mResponseQuizLists = response.body().getQuizList();
                    mQuiz.setQuizList(mResponseQuizLists);

                    String jsonString = mQuiz.quizListToJSONString(mQuiz.getQuizList());
                    mQuiz.setQuizListJSONString(jsonString);

                    QuizUserList.get(getApplicationContext()).updateQuiz(mAreaTable, mQuiz);

                    // prepare quizList fragment to replace loading progress if response is successful
                    QuizListFragment quizListFragment = QuizListFragment.newInstance();

                    Bundle quizListBundle = new Bundle();
                    quizListBundle.putSerializable(QUIZ_UUID, mQuiz.getID());
                    quizListBundle.putString(QUIZ_AREA_TABLE, mAreaTable);
                    quizListFragment.setArguments(quizListBundle);

                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, quizListFragment, QUIZ_LIST_FRAGMENT_TAG);
                    fragmentTransaction.commit();

                    Log.d(TAG, logCode + " " + logMsg + " " + mQuiz.getArea() + " " + mQuiz.getLevel());

                } else {
                    Toast.makeText(getApplicationContext(), "Add API KEY in Constants.java", Toast.LENGTH_LONG).show();
                }
            }


            @Override
            public void onFailure(Call<Quiz> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Response failed: " + t.getMessage() + "Add API KEY in Constants.java", Toast.LENGTH_LONG).show();
                Log.d(TAG, "Response failed: " + t.getMessage() + "Add API KEY in Constants.java");
            }
        });

    }

    @Override
    public void onUserChoiceClicked(int userChoice) {

        QuizListFragment quizListFragment = (QuizListFragment) getSupportFragmentManager().findFragmentByTag(QUIZ_LIST_FRAGMENT_TAG);

        quizListFragment.setUserChoice(userChoice);

        //check to see if user is at last question, if they are total up answers and intent QuizReport
        if (quizListFragment.getCurrentViewPagerPage() == 9) {
            quizListFragment.reportUserAnswers();
            quizListFragment.totalUpUserAnswers();

            //launch a new QuizReport intent with UUID and AREA passed in as extras
            Intent intent = new Intent(getApplicationContext(), QuizReportActivity.class);
            intent.putExtra(QUIZ_UUID, mQuiz.getID());
            intent.putExtra(QUIZ_AREA_TABLE, mAreaTable);
            startActivity(intent);

        } else {
            //else if not on the last question page, report answers and go to next question
            quizListFragment.reportUserAnswers();
            quizListFragment.resetCountDown();
            quizListFragment.nextPage();
        }


    }

    @Override
    public void onBackPressed() {

        QuizListFragment quizListFragment = (QuizListFragment) getSupportFragmentManager().findFragmentByTag(QUIZ_LIST_FRAGMENT_TAG);

        if (quizListFragment != null) {

            quizListFragment.stopCountDown();

            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
            alertBuilder.setMessage("Your progress for this level will be lost. Are you sure you want to quit?").setTitle("Quit Quiz").setCancelable(false);

            alertBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });

            alertBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    QuizListFragment quizListFragment = (QuizListFragment) getSupportFragmentManager().findFragmentByTag(QUIZ_LIST_FRAGMENT_TAG);
                    quizListFragment.resumeCountDown();
                }
            });

            AlertDialog dialog = alertBuilder.create();
            dialog.show();

        } else {
            mQuizzesCall.cancel();
            super.onBackPressed();
        }

    }

    private void setActionBarTitles(String mainTitle, int subTitle) {

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(mainTitle);
        actionBar.setSubtitle("Level " + String.valueOf(subTitle));

        switch (mainTitle) {
            case "es":
                actionBar.setTitle("Elementary");
                break;
            case "ms":
                actionBar.setTitle("Middle School");
                break;
            case "hs":
                actionBar.setTitle("High School");
                break;
            case "sat":
                actionBar.setTitle("SAT");
                break;
            case "gmat":
                actionBar.setTitle("GMAT");
            case "overall":
                actionBar.setTitle("Overall Vocabulary Test");
                break;
        }


    }

}

