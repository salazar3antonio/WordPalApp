package co.wordpal.mywordpalappfree.quiz_report;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.UUID;

import co.wordpal.mywordpalappfree.R;
import co.wordpal.mywordpalappfree.data.api.WordAPI;
import co.wordpal.mywordpalappfree.data.model.Meaning;
import co.wordpal.mywordpalappfree.data.model.Word;
import co.wordpal.mywordpalappfree.helpers.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static co.wordpal.mywordpalappfree.helpers.AreaLevelListSetup.QUIZ_AREA_TABLE;
import static co.wordpal.mywordpalappfree.elementary.ElementaryAreaFragment.QUIZ_UUID;

/**
 * Created by salaz on 11/27/2016.
 */

public class QuizReportActivity extends AppCompatActivity implements QuizReportPageFragment.WordDefinitionListener {

    private static final String TAG = QuizReportActivity.class.getName();

    public static final String QUIZ_REPORT_FRAGMENT_TAG = "quiz_report_fragment_tag";
    public static final String WORD = "word";
    public static final String NOUN_MEANING = "noun_word";
    public static final String VERB_MEANING = "verb_word";
    public static final String ADVERB_MEANING = "adverb_word";
    public static final String ADJECTIVE_MEANING = "adjective_word";

    private UUID mQuizID;
    private String mAreaTable;
    private String mWord;
    private Call<Word> mWordCall;
    private Meaning mMeaning;
    private WordMeaningLoadingFragment mWordMeaningLoadingFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        mWordMeaningLoadingFragment = new WordMeaningLoadingFragment();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mQuizID = (UUID) bundle.getSerializable(QUIZ_UUID);
            mAreaTable = bundle.getString(QUIZ_AREA_TABLE);
        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.quiz_report_title);
        actionBar.setHomeAsUpIndicator(R.drawable.x_mark);
        actionBar.setDisplayHomeAsUpEnabled(true);

        QuizReportFragment quizReportFragment = QuizReportFragment.newInstance();
        Bundle quizListBundle = new Bundle();
        quizListBundle.putSerializable(QUIZ_UUID, mQuizID);
        quizListBundle.putString(QUIZ_AREA_TABLE, mAreaTable);
        quizReportFragment.setArguments(quizListBundle);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, quizReportFragment, QUIZ_REPORT_FRAGMENT_TAG);
        fragmentTransaction.commit();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClickedForMeaning(String word) {

        mWord = word;
        mWordMeaningLoadingFragment.show(getSupportFragmentManager(), "loading");

        getWordMeanings(word);

        Log.d(TAG, "Word clicked: " + word);

    }

    public void getWordMeanings(String word) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.WORD_DEFINITION_END_POINT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WordAPI wordAPI = retrofit.create(WordAPI.class);
        mWordCall = wordAPI.getWord(word);
        mWordCall.enqueue(new Callback<Word>() {
            @Override
            public void onResponse(Call<Word> call, Response<Word> response) {
                if (response.isSuccessful()) {
                    String logMsg = response.body().getResultMsg();
                    String logCode = response.body().getResultCode();

                    mMeaning = response.body().getMeaning();

                    Log.d(TAG, logCode + " " + logMsg + " " + "Word Meanings: " + mMeaning.getNoun() + "\n" + mMeaning.getVerb() +
                            "\n" + mMeaning.getAdverb() + "\n" + mMeaning.getAdjective());

                    if (mMeaning != null) {

                        String oneNounLine = getFirstLineOfMeaning(mMeaning.getNoun());
                        String oneVerbLine = getFirstLineOfMeaning(mMeaning.getVerb());
                        String oneAdverbLine = getFirstLineOfMeaning(mMeaning.getAdverb());
                        String oneAdjectiveLine = getFirstLineOfMeaning(mMeaning.getAdjective());

                        WordMeaningFragment wordMeaningFragment = new WordMeaningFragment();
                        Bundle wordBundle = new Bundle();
                        wordBundle.putString(WORD, mWord);
                        wordBundle.putString(NOUN_MEANING, oneNounLine);
                        wordBundle.putString(VERB_MEANING, oneVerbLine);
                        wordBundle.putString(ADVERB_MEANING, oneAdverbLine);
                        wordBundle.putString(ADJECTIVE_MEANING, oneAdjectiveLine);
                        wordMeaningFragment.setArguments(wordBundle);

                        wordMeaningFragment.show(getSupportFragmentManager(), "dialog");

                        mWordMeaningLoadingFragment.dismiss();


                    } else {
                        Toast.makeText(getApplicationContext(), "Add API KEY in Constants.java", Toast.LENGTH_LONG).show();
                        Log.d(TAG, "Meaning response is null");
                    }

                }
            }

            @Override
            public void onFailure(Call<Word> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Response failed: " + t.getMessage() + "Add API KEY in Constants.java", Toast.LENGTH_LONG).show();
                Log.d(TAG, "Response failed: " + t.getMessage() + "Add API KEY in Constants.java");
            }
        });
    }

    public String getFirstLineOfMeaning(String grammarPart) {

        String string;

        if (grammarPart.isEmpty()) {
            return grammarPart;
        } else {
            char newLine = '\n';
            int startNewLine = grammarPart.indexOf(newLine);
            if (startNewLine == -1) {
                string = grammarPart.substring(6, grammarPart.length());
            } else {
                string = grammarPart.substring(6, startNewLine);
            }
        }

        return string;


    }

}

