package co.wordpal.mywordpalappfree;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import co.wordpal.mywordpalappfree.data.model.Quiz;
import co.wordpal.mywordpalappfree.database.QuizDbSchema;
import co.wordpal.mywordpalappfree.database.QuizUserList;
import co.wordpal.mywordpalappfree.elementary.ElementaryAreaActivity;
import co.wordpal.mywordpalappfree.gmat.GmatAreaActivity;
import co.wordpal.mywordpalappfree.highschool.HighSchoolAreaActivity;
import co.wordpal.mywordpalappfree.middleschool.MiddleSchoolAreaActivity;
import co.wordpal.mywordpalappfree.overall.OverallAreaActivity;
import co.wordpal.mywordpalappfree.sat.SatAreaActivity;

/**
 * Created by salaz on 1/12/2017.
 */

public class MyAreasFragment extends Fragment{

    public static final String TAG = MyAreasFragment.class.getName();
    public static final String ARG_PAGE = "arg_page";

    private int mPage;

    private LinearLayout mElementaryQuizClick;
    private LinearLayout mMiddleSchoolQuizClick;
    private LinearLayout mHighSchoolQuizClick;
    private LinearLayout mSatQuizClick;
    private LinearLayout mGmatQuizClick;
    private LinearLayout mOverallQuizClick;
    private TextView mElementaryStars;
    private TextView mMiddleSchoolStars;
    private TextView mHighSchoolStars;
    private TextView mSatStars;
    private TextView mGmatStars;
    private TextView mOverallStars;
    private QuizUserList mQuizUserList;
    private List<Quiz> mQuizzes;

    public MyAreasFragment() {
        // Required empty public constructor
    }

    public static MyAreasFragment newInstance(int section) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, section);
        MyAreasFragment fragment = new MyAreasFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //inflate view and pass in the container ViewGroup
        View view = inflater.inflate(R.layout.fragment_my_areas, container, false);

        mElementaryStars = view.findViewById(R.id.elementary_main_stars);
        mMiddleSchoolStars = view.findViewById(R.id.middleschool_main_stars);
        mHighSchoolStars = view.findViewById(R.id.highschool_main_stars);
        mSatStars = view.findViewById(R.id.sat_main_stars);
        mGmatStars = view.findViewById(R.id.gmat_main_stars);
        mOverallStars = view.findViewById(R.id.overall_main_stars);

        updateTotalStars(mElementaryStars, QuizDbSchema.AreaTables.ELEMENTARY_TABLE);
        updateTotalStars(mMiddleSchoolStars, QuizDbSchema.AreaTables.MIDDLESCHOOL_TABLE);
        updateTotalStars(mHighSchoolStars, QuizDbSchema.AreaTables.HIGHSCHOOL_TABLE);
        updateTotalStars(mSatStars, QuizDbSchema.AreaTables.SAT_TABLE);
        updateTotalStars(mGmatStars, QuizDbSchema.AreaTables.GMAT_TABLE);
        updateTotalStars(mOverallStars, QuizDbSchema.AreaTables.OVERALL_TABLE);

        mElementaryQuizClick = view.findViewById(R.id.elementary_quiz_click);
        mElementaryQuizClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ElementaryAreaActivity.class);
                startActivity(intent);

            }
        });

        mMiddleSchoolQuizClick = view.findViewById(R.id.middleschool_quiz_click);
        mMiddleSchoolQuizClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MiddleSchoolAreaActivity.class);
                startActivity(intent);
            }
        });

        mHighSchoolQuizClick = view.findViewById(R.id.highschool_quiz_click);
        mHighSchoolQuizClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), HighSchoolAreaActivity.class);
                startActivity(intent);
            }
        });

        mSatQuizClick = view.findViewById(R.id.sat_quiz_click);
        mSatQuizClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SatAreaActivity.class);
                startActivity(intent);
            }
        });

        mGmatQuizClick = view.findViewById(R.id.gmat_quiz_click);
        mGmatQuizClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), GmatAreaActivity.class);
                startActivity(intent);
            }
        });

        mOverallQuizClick = view.findViewById(R.id.overal_quiz_click);
        mOverallQuizClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), OverallAreaActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateTotalStars(mElementaryStars, QuizDbSchema.AreaTables.ELEMENTARY_TABLE);
        updateTotalStars(mMiddleSchoolStars, QuizDbSchema.AreaTables.MIDDLESCHOOL_TABLE);
        updateTotalStars(mHighSchoolStars, QuizDbSchema.AreaTables.HIGHSCHOOL_TABLE);
        updateTotalStars(mSatStars, QuizDbSchema.AreaTables.SAT_TABLE);
        updateTotalStars(mGmatStars, QuizDbSchema.AreaTables.GMAT_TABLE);
        updateTotalStars(mOverallStars, QuizDbSchema.AreaTables.OVERALL_TABLE);
    }

    //fix this to update all stars from all tables
    public void updateTotalStars(TextView textView, String areaTable) {

        mQuizUserList = QuizUserList.get(getActivity());
        mQuizzes = mQuizUserList.getQuizzes(areaTable);

        int stars = 0;
        for (Quiz q : mQuizzes) {
            stars = stars + q.getTotalStars();
        }

        textView.setText(String.valueOf(stars));

    }

}
