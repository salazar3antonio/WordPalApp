package co.wordywordy.wordywordy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class QuizListFragment extends Fragment {

    public static final String JSON_Response = "json_response";

    private String mJSONasString;

    public QuizListFragment() {
        // Required empty public constructor
    }

    public static QuizListFragment newInstance(String jsonResponse) {
        QuizListFragment fragment = new QuizListFragment();
        Bundle args = new Bundle();
        args.putString(JSON_Response, jsonResponse);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mJSONasString = getArguments().getString(JSON_Response);

        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_quiz_list, container, false);

        Toast.makeText(getContext(), "Passed JSON " + mJSONasString, Toast.LENGTH_LONG).show();

        return view;
    }



 }
