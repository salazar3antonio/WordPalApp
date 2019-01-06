package co.wordpal.mywordpalappfree.quiz_list;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import co.wordpal.mywordpalappfree.R;


public class QuizListPageFragment extends Fragment {

    public static final String TAG = QuizListPageFragment.class.getName();

    public static final String QUIZANSWER = "quizAnswer";
    public static final String QUIZCHOICES = "quizChoices";
    public static final String QUIZWORDS = "quizWords";

    private int mAnswer;
    private String[] mChoices;
    private String[] mQuizWords;

    private TextView mWordOne_view;
    private TextView mWordTwo_view;
    private TextView mWordThree_view;
    private Button mChoiceOne_butt;
    private Button mChoiceTwo_butt;

    UserAnswerListener mUserAnswerListener;

    public interface UserAnswerListener {
        void onUserChoiceClicked(int userChoice);
    }

    public QuizListPageFragment() {
        // Required empty public constructor
    }

    public static QuizListPageFragment newInstance(int quizAnswer, String[] quizChoices, String[] quizWords) {
        QuizListPageFragment fragment = new QuizListPageFragment();
        Bundle args = new Bundle();
        args.putInt(QUIZANSWER, quizAnswer);
        args.putStringArray(QUIZCHOICES, quizChoices);
        args.putStringArray(QUIZWORDS, quizWords);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mUserAnswerListener = (UserAnswerListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement UserAnswerListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mChoices = getArguments().getStringArray(QUIZCHOICES);
            mQuizWords = getArguments().getStringArray(QUIZWORDS);
            mAnswer = getArguments().getInt(QUIZANSWER);
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
        mChoiceOne_butt.setText(mChoices[0]);
        mChoiceTwo_butt.setText(mChoices[1]);

        mChoiceOne_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUserAnswerListener.onUserChoiceClicked(1);
            }
        });

        mChoiceTwo_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUserAnswerListener.onUserChoiceClicked(2);
            }
        });

        return view;
    }

    @Override
    public void onDetach() {
        mUserAnswerListener = null;
        super.onDetach();
    }
}


