package co.wordywordy.wordywordy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import co.wordywordy.wordywordy.data.QuizList;


public class QuizListFragment extends Fragment {

    public static final String TAG = QuizListFragment.class.getName();
    public static final String AREA = "area";
    public static final String LEVEL = "level";

    private String mArea;
    private String mLevel;

    private RecyclerView mQuizListRecyclerView;
    private Button mChoiceOne;
    private Button mChoiceTwo;
    private TextView mOptionOne;
    private TextView mOptionTwo;
    private TextView mOptionThree;

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
           // new CallAPI().execute();

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_quiz_list, container, false);

        mQuizListRecyclerView = (RecyclerView) view.findViewById(R.id.quiz_list_recyclerView);



        // Toast.makeText(getContext(), "Passed JSON " + mJSONasString, Toast.LENGTH_LONG).show();

        return view;
    }

    public class ViewHolder  extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
            mOptionOne = (TextView) itemView.findViewById(R.id.optionOne);
            mOptionTwo = (TextView) itemView.findViewById(R.id.optionTwo);
            mOptionThree = (TextView) itemView.findViewById(R.id.optionThree);
            mChoiceOne = (Button) itemView.findViewById(R.id.choiceOne_button);
            mChoiceTwo = (Button) itemView.findViewById(R.id.choiceTwo_button);

        }
    }

    public class ViewAdapter extends RecyclerView.Adapter<ViewHolder> {

        public ViewAdapter(JSONArray jsonArray) {
            mJSONArray = jsonArray;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.quiz_list_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            String options = null;
            try {
                options = mJSONArray.getString(position);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mOptionOne.setText(options);

        }

        @Override
        public int getItemCount() {
            return 10;
        }
    }

//    private class CallAPI extends AsyncTask<String, Integer, HttpResponse<JsonNode>> {
//
//        @Override
//        protected HttpResponse<JsonNode> doInBackground(String... strings) {
//            HttpResponse<JsonNode> request = null;
//            try {
//                request = Unirest.get("https://twinword-word-association-quiz.p.mashape.com/type1/?area={area}&level={level}")
//                        .header("X-Mashape-Authorization", Constants.API_PRODUCTION_KEY)
//                        .routeParam("area", mArea)
//                        .routeParam("level", mLevel)
//                        .asJson();
//            } catch (UnirestException e) {
//                e.printStackTrace();
//            }
//
//            return request;
//        }
//
//        @Override
//        protected void onPostExecute(HttpResponse<JsonNode> jsonResponse) {
//            super.onPostExecute(jsonResponse);
//
//            JSONObject jsonObject = jsonResponse.getBody().getObject();
//            try {
//                mJSONArray = jsonObject.getJSONArray("quizlist");
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//            mQuizListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//            mQuizListRecyclerView.setAdapter(new ViewAdapter(mJSONArray));
//
//        }
//
//
//    }




}


