package co.wordywordy.wordywordy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
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
    private String mLevel;

    private RecyclerView mQuizListRecyclerView;
    private RadioButton mOptionOne_radio;
    private RadioButton mOptionTwo_radio;
    private TextView mQuizWordOne;
    private TextView mQuizWordTwo;
    private TextView mQuizWordThree;

    private List<Quizlist> mQuizlists;

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
            mLevel = getArguments().getString(LEVEL);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.quiz_list_pager, container, false);

     //   mQuizListRecyclerView = (RecyclerView) view.findViewById(R.id.quiz_list_recyclerView);
        mViewPager = (ViewPager) view.findViewById(R.id.list_page);
        mPagerAdapter = new QuizListPagerAdapter(getActivity().getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.END_POINT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        QuizAPI quizAPI = retrofit.create(QuizAPI.class);

        final Call<Quiz> quizes = quizAPI.getQuiz("sat", 10);

        quizes.enqueue(new Callback<Quiz>() {
            @Override
            public void onResponse(Call<Quiz> call, Response<Quiz> response) {
                String logMsg = response.body().getResultMsg();
                String logCode = response.body().getResultCode();

                if (logCode != "Success") {
                    mQuizlists = response.body().getQuizlist();

                 //   mQuizListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                 //   mQuizListRecyclerView.setAdapter(new ViewAdapter(mQuizlists));




                    Log.d(TAG, logCode + " " + logMsg);
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

        public ViewHolder(View itemView) {
            super(itemView);
            mQuizWordOne = (TextView) itemView.findViewById(R.id.quiz_word_one);
            mQuizWordTwo = (TextView) itemView.findViewById(R.id.quiz_word_two);
            mQuizWordThree = (TextView) itemView.findViewById(R.id.quiz_word_three);
            mOptionOne_radio = (RadioButton) itemView.findViewById(R.id.optionOne_radio);
            mOptionTwo_radio = (RadioButton) itemView.findViewById(R.id.optionTwo_radio);

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
            String quizWordOne = mQuizlists.get(position).getQuiz().get(0);
            String quizWordTwo = mQuizlists.get(position).getQuiz().get(1);
            String quizWordThree = mQuizlists.get(position).getQuiz().get(2);
            String optionOne = mQuizlists.get(position).getOption().get(0);
            String optionTwo = mQuizlists.get(position).getOption().get(1);

            mQuizWordOne.setText(quizWordOne);
            mQuizWordTwo.setText(quizWordTwo);
            mQuizWordThree.setText(quizWordThree);
            mOptionOne_radio.setText(optionOne);
            mOptionTwo_radio.setText(optionTwo);

        }

        @Override
        public int getItemCount() {
            return mQuizlists.size();
        }
    }

    private class QuizListPagerAdapter extends FragmentStatePagerAdapter {

        public QuizListPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new QuizListItemFragment();
        }

        @Override
        public int getCount() {
            return 10;
        }
    }



}


