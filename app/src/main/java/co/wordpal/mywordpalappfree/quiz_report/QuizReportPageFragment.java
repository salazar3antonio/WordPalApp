package co.wordpal.mywordpalappfree.quiz_report;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import co.wordpal.mywordpalappfree.helpers.AreaLevelListSetup;
import co.wordpal.mywordpalappfree.R;

/**
 * Created by salaz on 10/11/2016.
 */

public class QuizReportPageFragment extends Fragment {

    public static final String TAG = QuizReportPageFragment.class.getName();

    public static final String QUIZANSWER = "quizAnswer";
    public static final String QUIZCHOICES = "quizChoices";
    public static final String QUIZWORDS = "quizWords";
    public static final String USERCHOICE = "userChoice";

    private int mQuizAnswer;
    private String[] mQuizChoices;
    private String[] mQuizWords;
    private int mUserChoice;

    private TextView mWordOne_view;
    private TextView mWordTwo_view;
    private TextView mWordThree_view;
    private Button mChoiceOne_butt;
    private Button mChoiceTwo_butt;

    WordDefinitionListener mWordDefinitionListener;

    public QuizReportPageFragment() {
        // Required empty public constructor
    }

    public interface WordDefinitionListener {
        void onClickedForMeaning(String word);
    }


    public static QuizReportPageFragment newInstance(int quizAnswer, String[] quizChoices, String[] quizWords, int userChoice) {
        QuizReportPageFragment fragment = new QuizReportPageFragment();
        Bundle args = new Bundle();
        args.putInt(QUIZANSWER, quizAnswer);
        args.putStringArray(QUIZCHOICES, quizChoices);
        args.putStringArray(QUIZWORDS, quizWords);
        args.putInt(USERCHOICE, userChoice);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mWordDefinitionListener = (WordDefinitionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement WordDefinitionListener");
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mQuizChoices = getArguments().getStringArray(QUIZCHOICES);
            mQuizWords = getArguments().getStringArray(QUIZWORDS);
            mQuizAnswer = getArguments().getInt(QUIZANSWER);
            mUserChoice = getArguments().getInt(USERCHOICE);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_quiz_list_page, container, false);

        mWordOne_view = view.findViewById(R.id.quiz_word_one);
        mWordTwo_view = view.findViewById(R.id.quiz_word_two);
        mWordThree_view = view.findViewById(R.id.quiz_word_three);
        mChoiceOne_butt = view.findViewById(R.id.choiceOne_butt);
        mChoiceTwo_butt = view.findViewById(R.id.choiceTwo_butt);


        mWordOne_view.setText(mQuizWords[0]);
        mWordTwo_view.setText(mQuizWords[1]);
        mWordThree_view.setText(mQuizWords[2]);
        mChoiceOne_butt.setText(mQuizChoices[0]);
        mChoiceTwo_butt.setText(mQuizChoices[1]);

        //click words to bring up meaning
        mChoiceOne_butt.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (AreaLevelListSetup.hasInternetConnection(getActivity())) {
                    mWordDefinitionListener.onClickedForMeaning(mChoiceOne_butt.getText().toString());
                } else {
                    Toast.makeText(getActivity(), "No internet connection.", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });


        mChoiceTwo_butt.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (AreaLevelListSetup.hasInternetConnection(getActivity())) {
                    mWordDefinitionListener.onClickedForMeaning(mChoiceTwo_butt.getText().toString());
                }else {
                    Toast.makeText(getActivity(), "No internet connection.", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        //set color of buttons to show incorrect and correct answers
        if (mUserChoice == 1) {
            if (mQuizAnswer == 1) {
                mChoiceOne_butt.setBackgroundTintList(ContextCompat.getColorStateList(getActivity(), R.color.md_green_300));

            } else if (mQuizAnswer == 2) {
                mChoiceOne_butt.setBackgroundTintList(ContextCompat.getColorStateList(getActivity(), R.color.md_red_300));
            }
        } else {
            if (mQuizAnswer == 1) {
                mChoiceTwo_butt.setBackgroundTintList(ContextCompat.getColorStateList(getActivity(), R.color.md_red_300));

            } else if (mQuizAnswer == 2) {
                mChoiceTwo_butt.setBackgroundTintList(ContextCompat.getColorStateList(getActivity(), R.color.md_green_300));
            }
        }

        return view;

    }
}








