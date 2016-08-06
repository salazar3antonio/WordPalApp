package co.wordywordy.wordywordy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import co.wordywordy.wordywordy.data.QuizList;


public class QuizSetupFragment extends Fragment {

    private Spinner areaSpinner;
    private Spinner levelSpinner;
    private Button takeQuizButton;
    private QuizList mQuizList;

    public QuizSetupFragment() {
        // Required empty public constructor
    }

    public static QuizSetupFragment newInstance() {
        QuizSetupFragment fragment = new QuizSetupFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //inflate view and pass in the container ViewGroup
        View view = inflater.inflate(R.layout.fragment_quiz_setup, container, false);
        mQuizList = new QuizList();

        takeQuizButton = (Button) view.findViewById(R.id.take_quiz_button);
        takeQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        return view;
    }

}
