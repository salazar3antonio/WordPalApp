package co.wordywordy.wordywordy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class QuizListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String AREA = "area";
    private static final String LEVEL = "level";

    private String mArea;
    private String mLevel;
    private TextView mAreaTest;
    private TextView mLevelTest;

    public QuizListFragment() {
        // Required empty public constructor
    }

    public static QuizListFragment newInstance(String area, String level) {
        QuizListFragment fragment = new QuizListFragment();
        Bundle args = new Bundle();
        args.putString(AREA, area);
        args.putString(LEVEL, level);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mArea = getArguments().getString(AREA);
            mLevel = getArguments().getString(LEVEL);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_quiz_list, container, false);

        Toast.makeText(getContext(), "The Level is " + mLevel, Toast.LENGTH_LONG).show();

        return view;
    }



 }
