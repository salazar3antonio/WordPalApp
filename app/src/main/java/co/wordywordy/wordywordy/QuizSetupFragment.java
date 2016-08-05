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
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Spinner areaSpinner;
    private Spinner levelSpinner;
    private Button takeQuizButton;

    public QuizSetupFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuizSetupFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuizSetupFragment newInstance(String param1, String param2) {
        QuizSetupFragment fragment = new QuizSetupFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //inflate view and pass in the container ViewGroup
        View view = inflater.inflate(R.layout.fragment_quiz_setup, container, false);

        // now that the view is inflated we can assign our members to ids
        areaSpinner = (Spinner) view.findViewById(R.id.area_spinner);
        ArrayAdapter<CharSequence> areaAdapter = ArrayAdapter.createFromResource(getContext(), R.array.area_choice_spinner, android.R.layout.simple_spinner_item);
        areaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        areaSpinner.setAdapter(areaAdapter);

        levelSpinner = (Spinner) view.findViewById(R.id.level_spinner);
        ArrayAdapter<CharSequence> levelAdapter = ArrayAdapter.createFromResource(getContext(), R.array.level_choice_spinner, android.R.layout.simple_spinner_item);
      //  ArrayAdapter<CharSequence> levelAdapter = new ArrayAdapter<CharSequence>(getContext(), android.R.layout.simple_spinner_item, );
        levelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        levelSpinner.setAdapter(levelAdapter);

        takeQuizButton = (Button) view.findViewById(R.id.take_quiz_button);
        takeQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz_setup, container, false);
    }

}
