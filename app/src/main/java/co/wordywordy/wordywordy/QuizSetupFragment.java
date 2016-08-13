package co.wordywordy.wordywordy;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.json.JSONArray;
import org.json.JSONObject;

import co.wordywordy.wordywordy.data.QuizList;


public class QuizSetupFragment extends Fragment {

    public static final String TAG = QuizSetupFragment.class.getName();
    public static final String AREA_SELECTED = "area_selected";
    public static final String LEVEL_SELECTED = "level_selected";

    private RadioGroup mAreaRadioGroup;
    private RadioGroup mLevelRadioGroup;
    private Button mTakeQuizButton;
    private QuizList mQuizList;

    private JSONArray mQuizListArray;

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

        mAreaRadioGroup = (RadioGroup) view.findViewById(R.id.area_radioGroup);
        mLevelRadioGroup = (RadioGroup) view.findViewById(R.id.level_radioGroup);

        mAreaRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (mAreaRadioGroup.getCheckedRadioButtonId()) {
                    case R.id.es_radio:
                        mQuizList.setArea("es");
                        break;
                    case R.id.ms_radio:
                        mQuizList.setArea("ms");
                        break;
                    case R.id.hs_radio:
                        mQuizList.setArea("hs");
                        break;
                    case R.id.sat_radio:
                        mQuizList.setArea("sat");
                        break;
                    case R.id.all_radio:
                        mQuizList.setArea("overall");
                        break;
                }
            }
        });

        mLevelRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (mLevelRadioGroup.getCheckedRadioButtonId()) {
                    case R.id.two_radio:
                        mQuizList.setLevel("2");
                        break;
                    case R.id.four_radio:
                        mQuizList.setLevel("4");
                        break;
                    case R.id.six_radio:
                        mQuizList.setLevel("6");
                        break;
                    case R.id.eight_radio:
                        mQuizList.setLevel("8");
                        break;
                    case R.id.ten_radio:
                        mQuizList.setLevel("10");
                        break;
                }
            }
        });

        mTakeQuizButton = (Button) view.findViewById(R.id.take_quiz_button);
        mTakeQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CallAPI().execute();

//                Intent intent = new Intent(getContext(), QuizListActivity.class);
//                intent.putExtra(AREA_SELECTED, mQuizList.getArea());
//                intent.putExtra(LEVEL_SELECTED, mQuizList.getLevel());
//                startActivity(intent);
            }
        });

        return view;
    }

    private class CallAPI extends AsyncTask<String, Integer, HttpResponse<JsonNode>> {

        @Override
        protected HttpResponse<JsonNode> doInBackground(String... strings) {
            HttpResponse<JsonNode> request = null;
            try {
                request = Unirest.get("https://twinword-word-association-quiz.p.mashape.com/type1/?area={area}&level={level}")
                        .header("X-Mashape-Authorization", Constants.API_PRODUCTION_KEY)
                        .routeParam("area", mQuizList.getArea())
                        .routeParam("level", mQuizList.getLevel())
                        .asJson();
            } catch (UnirestException e) {
                e.printStackTrace();
            }

            return request;
        }

        @Override
        protected void onPostExecute(HttpResponse<JsonNode> jsonResponse) {
            super.onPostExecute(jsonResponse);

            JSONObject jsonObject = jsonResponse.getBody().getObject();
            Log.d(TAG, jsonObject.toString());




        }
    }

}
