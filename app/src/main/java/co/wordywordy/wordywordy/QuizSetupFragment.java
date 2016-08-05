package co.wordywordy.wordywordy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


public class QuizSetupFragment extends Fragment {

    private Spinner areaSpinner;
    private Spinner levelSpinner;
    private Button takeQuizButton;

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

        // now that the view is inflated we can assign our members to ids
        areaSpinner = (Spinner) view.findViewById(R.id.area_spinner);
        ArrayAdapter<CharSequence> areaAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.area_choice_spinner, android.R.layout.simple_spinner_item);
        areaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        areaSpinner.setAdapter(areaAdapter);

        levelSpinner = (Spinner) view.findViewById(R.id.level_spinner);
        ArrayAdapter<CharSequence> levelAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.level_choice_spinner, android.R.layout.simple_spinner_item);
        levelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        levelSpinner.setAdapter(levelAdapter);

        takeQuizButton = (Button) view.findViewById(R.id.take_quiz_button);
        takeQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        return view;
    }

}
