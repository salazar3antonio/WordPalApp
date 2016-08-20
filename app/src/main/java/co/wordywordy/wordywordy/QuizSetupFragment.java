package co.wordywordy.wordywordy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.json.JSONObject;

import co.wordywordy.wordywordy.data.model.Quiz;


public class QuizSetupFragment extends Fragment {

    public static final String TAG = QuizSetupFragment.class.getName();
    public static final String AREA_SELECTED = "area_selected";
    public static final String LEVEL_SELECTED = "level_selected";

    private RadioGroup mAreaRadioGroup;
    private RadioGroup mLevelRadioGroup;
    private Button mTakeQuizButton;
    private Quiz mQuiz;
    private TextView mJSONtest;

    private JSONObject mJSONObject;
    private String mJSON_String;

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
        mQuiz = new Quiz();

        mAreaRadioGroup = (RadioGroup) view.findViewById(R.id.area_radioGroup);
        mLevelRadioGroup = (RadioGroup) view.findViewById(R.id.level_radioGroup);

        mAreaRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (mAreaRadioGroup.getCheckedRadioButtonId()) {
                    case R.id.es_radio:
                        mQuiz.setArea("es");
                        break;
                    case R.id.ms_radio:
                        mQuiz.setArea("ms");
                        break;
                    case R.id.hs_radio:
                        mQuiz.setArea("hs");
                        break;
                    case R.id.sat_radio:
                        mQuiz.setArea("sat");
                        break;
                    case R.id.all_radio:
                        mQuiz.setArea("overall");
                        break;
                }
            }
        });

        mLevelRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (mLevelRadioGroup.getCheckedRadioButtonId()) {
                    case R.id.two_radio:
                        mQuiz.setLevel(2);
                        break;
                    case R.id.four_radio:
                        mQuiz.setLevel(4);
                        break;
                    case R.id.six_radio:
                        mQuiz.setLevel(6);
                        break;
                    case R.id.eight_radio:
                        mQuiz.setLevel(8);
                        break;
                    case R.id.ten_radio:
                        mQuiz.setLevel(10);
                        break;
                }
            }
        });

        mTakeQuizButton = (Button) view.findViewById(R.id.take_quiz_button);
        mTakeQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), QuizListActivity.class);
                intent.putExtra(AREA_SELECTED, mQuiz.getArea());
                intent.putExtra(LEVEL_SELECTED, mQuiz.getLevel());
                startActivity(intent);

            }

        });

        return view;
    }


}
