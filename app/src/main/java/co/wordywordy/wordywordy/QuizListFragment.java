package co.wordywordy.wordywordy;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import co.wordywordy.wordywordy.data.QuizList;


public class QuizListFragment extends Fragment {

    public static final String TAG = QuizListFragment.class.getName();
    public static final String AREA = "area";
    public static final String LEVEL = "level";

    private TextView mOptionOne;
    private String mArea;
    private String mLevel;
    private JSONArray mJSONArray;
    private JSONObject mJSONObject;
    private String mJSONasString;
    private QuizList mQuizList = new QuizList();

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
            new CallAPI().execute();

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_quiz_list, container, false);

        mOptionOne = (TextView) view.findViewById(R.id.optionOne);

        // Toast.makeText(getContext(), "Passed JSON " + mJSONasString, Toast.LENGTH_LONG).show();

        return view;
    }

    private class CallAPI extends AsyncTask<String, Integer, HttpResponse<JsonNode>> {

        @Override
        protected HttpResponse<JsonNode> doInBackground(String... strings) {
            HttpResponse<JsonNode> request = null;
            try {
                request = Unirest.get("https://twinword-word-association-quiz.p.mashape.com/type1/?area={area}&level={level}")
                        .header("X-Mashape-Authorization", Constants.API_PRODUCTION_KEY)
                        .routeParam("area", mArea)
                        .routeParam("level", mLevel)
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
            try {
                mJSONArray = jsonObject.getJSONArray("quizlist");
                JSONObject jsonObject1 = mJSONArray.getJSONObject(0);
                mOptionOne.setText(jsonObject1.get("quiz").toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }




        }


    }




}


