package co.wordywordy.wordywordy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import co.wordywordy.wordywordy.data.api.QuizAPI;
import co.wordywordy.wordywordy.data.model.Quiz;
import co.wordywordy.wordywordy.data.model.Quizlist;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


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
    private String mChoiceOneText;
    private String mChoiceTwoText;
    private String mOptionOneText;
    private String mOptionTwoText;
    private String mOptionThreeText;

    private List<Quizlist> mQuizlists;


    public QuizListFragment() {
        // Required empty public constructor
    }

    public static QuizListFragment newInstance(String area, int level) {
        QuizListFragment fragment = new QuizListFragment();
        Bundle args = new Bundle();
        args.putString(AREA, area);
        args.putInt(LEVEL, level);
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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.END_POINT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        QuizAPI quizAPI = retrofit.create(QuizAPI.class);

        final Call<Quiz> quizes = quizAPI.getQuiz("sat", 10);
        quizes.enqueue(new Callback<Quiz>() {
            @Override
            public void onResponse(Call<Quiz> call, Response<Quiz> response) {
                mQuizlists = response.body().getQuizlist();
                Log.d(TAG, response.body().getResultMsg());

                mQuizListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                mQuizListRecyclerView.setAdapter(new ViewAdapter(mQuizlists));

            }

            @Override
            public void onFailure(Call<Quiz> call, Throwable t) {
                Log.d(TAG, t.getMessage());
            }
        });

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

        public ViewAdapter(List<Quizlist> quizlists) {
            mQuizlists = quizlists;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.quiz_list_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            mOptionOneText = mQuizlists.get(position).getQuiz().get(0);
            mOptionTwoText = mQuizlists.get(position).getQuiz().get(1);
            mOptionThreeText = mQuizlists.get(position).getQuiz().get(2);
            mChoiceOneText = mQuizlists.get(position).getOption().get(0);
            mChoiceTwoText = mQuizlists.get(position).getOption().get(1);


            mOptionOne.setText(mOptionOneText);
            mOptionTwo.setText(mOptionTwoText);
            mOptionThree.setText(mOptionThreeText);
            mChoiceOne.setText(mChoiceOneText);
            mChoiceTwo.setText(mChoiceTwoText);


        }

        @Override
        public int getItemCount() {
            return mQuizlists.size();
        }
    }


}


