package co.wordywordy.wordywordy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
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
    private int mLevel;
    private RecyclerView mQuizListRecyclerView;
    private List<Quizlist> mQuizlists;
    private Quizlist mQuizlist;


    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;


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
            mLevel = getArguments().getInt(LEVEL);

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

        final Call<Quiz> quizes = quizAPI.getQuiz(mArea, mLevel);

        quizes.enqueue(new Callback<Quiz>() {
            @Override
            public void onResponse(Call<Quiz> call, Response<Quiz> response) {
                String logMsg = response.body().getResultMsg();
                String logCode = response.body().getResultCode();

                if (logCode != "Success") {
                    mQuizlists = response.body().getQuizlist();
                    mQuizListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    mQuizListRecyclerView.setAdapter(new ViewAdapter(mQuizlists));
                    Log.d(TAG, logCode + " " + logMsg);
                    Log.d(TAG, "Area is: " + mArea + " & " + "Level is: " + mLevel);
                } else {
                    Log.d(TAG, logCode + " " + logMsg);
                }
            }

            @Override
            public void onFailure(Call<Quiz> call, Throwable t) {
                Log.d(TAG, t.getMessage());
            }
        });

        return view;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private RadioButton mOptionOne_radio;
        private RadioButton mOptionTwo_radio;
        private TextView mQuizWordOne;
        private TextView mQuizWordTwo;
        private TextView mQuizWordThree;

        public ViewHolder(View itemView) {
            super(itemView);
            mQuizWordOne = (TextView) itemView.findViewById(R.id.quiz_word_one);
            mQuizWordTwo = (TextView) itemView.findViewById(R.id.quiz_word_two);
            mQuizWordThree = (TextView) itemView.findViewById(R.id.quiz_word_three);
            mOptionOne_radio = (RadioButton) itemView.findViewById(R.id.optionOne_radio);
            mOptionTwo_radio = (RadioButton) itemView.findViewById(R.id.optionTwo_radio);

        }

        public void bindQuizList(Quizlist quizlist) {
            mQuizlist = quizlist;
            mQuizWordOne.setText(mQuizlist.getQuiz().get(0));
            mQuizWordTwo.setText(mQuizlist.getQuiz().get(1));
            mQuizWordThree.setText(mQuizlist.getQuiz().get(2));
            mOptionOne_radio.setText(mQuizlist.getOption().get(0));
            mOptionTwo_radio.setText(mQuizlist.getOption().get(1));

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
            Quizlist quizlist = mQuizlists.get(position);
            holder.bindQuizList(quizlist);
        }


        @Override
        public int getItemCount() {
            return mQuizlists.size();
        }
    }

}


