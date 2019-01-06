package co.wordpal.mywordpalappfree.helpers;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import co.wordpal.mywordpalappfree.R;
import co.wordpal.mywordpalappfree.data.model.Quiz;
import co.wordpal.mywordpalappfree.database.QuizUserList;
import co.wordpal.mywordpalappfree.quiz_list.QuizListActivity;
import co.wordpal.mywordpalappfree.quiz_report.QuizReportActivity;

/**
 * Created by salaz on 12/3/2016.
 */

public class AreaLevelListSetup extends Fragment {

    public static final String TAG = AreaLevelListSetup.class.getName();
    public static final String QUIZ_UUID = "quiz_uuid";
    public static final String QUIZ_AREA_TABLE = "quiz_area_table";

    private QuizViewAdapter mViewAdapter;
    private int mLevel;
    private String mAreaTable;

    // class that holds the unlocked area views of a level list
    public class UnLockedQuizHolder extends RecyclerView.ViewHolder {

        private TextView mLevelTotalPoints;
        private TextView mLevelTotalCorrect;
        private TextView mLevelNumberText;
        private RatingBar mLevelTotalStars;
        private Button mTakeQuiz_butt;
        private Button mReviewQuiz_butt;
        private Quiz mQuiz;

        public UnLockedQuizHolder(View itemView) {
            super(itemView);

            mLevelTotalPoints = itemView.findViewById(R.id.level_total_points);
            mLevelTotalCorrect = itemView.findViewById(R.id.level_correct_answers);
            mLevelTotalStars = itemView.findViewById(R.id.level_total_stars);
            mLevelNumberText = itemView.findViewById(R.id.level_number);
            mTakeQuiz_butt = itemView.findViewById(R.id.take_quiz_butt);
            mReviewQuiz_butt = itemView.findViewById(R.id.review_quiz_butt);

            mTakeQuiz_butt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (hasInternetConnection(getActivity())) {
                        mQuiz = QuizUserList.get(getActivity()).getQuiz(mAreaTable, mQuiz.getID());
                        newQuizListIntent(mQuiz);
                    } else {
                        Toast.makeText(getActivity(), "No internet connection.", Toast.LENGTH_SHORT).show();
                    }
                }
            });


            mReviewQuiz_butt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), QuizReportActivity.class);
                    intent.putExtra(QUIZ_UUID, mQuiz.getID());
                    intent.putExtra(QUIZ_AREA_TABLE, mAreaTable);
                    startActivity(intent);
                }
            });

        }

        public void bindUnLockedQuizData(Quiz quiz) {
            mQuiz = quiz;
            mLevelTotalCorrect.setText(String.valueOf(mQuiz.getTotalCorrect()));
            mLevelTotalPoints.setText(String.valueOf(mQuiz.getTotalUserScore()));
            mLevelTotalStars.setRating(mQuiz.getTotalStars());
            mLevelNumberText.setText(String.valueOf(mLevel));

            if (mQuiz.getCompletedState() == 1) {
                mReviewQuiz_butt.setVisibility(View.VISIBLE);
            } else {
                mReviewQuiz_butt.setVisibility(View.INVISIBLE);
            }

        }

    }

    // class that holds the locked area views of a level list
    public class LockedQuizHolder extends RecyclerView.ViewHolder {

        private TextView mLevelNumberText;

        public LockedQuizHolder(View itemView) {
            super(itemView);
            mLevelNumberText = itemView.findViewById(R.id.level_number);

        }

        public void bindLockedQuizData() {
            mLevelNumberText.setText(String.valueOf(mLevel));
        }

    }

    //adapter that holds logic that determines which locked/unlocked quiz state to show
    public class QuizViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private final int UN_LOCKED_ITEM = 1;
        private final int LOCKED_ITEM = 0;

        private List<Quiz> mQuizzes;

        public QuizViewAdapter(List<Quiz> quizzes) {
            mQuizzes = quizzes;

        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            RecyclerView.ViewHolder viewHolder = null;
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            switch (viewType) {
                case UN_LOCKED_ITEM:
                    View unLockedView = layoutInflater.inflate(R.layout.fragment_level_list_unlocked_item_nohs, parent, false);
                    viewHolder = new UnLockedQuizHolder(unLockedView);
                    break;
                case LOCKED_ITEM:
                    View lockedView = layoutInflater.inflate(R.layout.fragment_level_list_locked_item_nohs, parent, false);
                    viewHolder = new LockedQuizHolder(lockedView);
                    break;
            }

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            switch (holder.getItemViewType()) {
                case UN_LOCKED_ITEM:
                    Quiz quiz = mQuizzes.get(position);
                    mLevel = position + 1;
                    UnLockedQuizHolder unLockedQuizHolder = (UnLockedQuizHolder) holder;
                    unLockedQuizHolder.bindUnLockedQuizData(quiz);
                    break;
                case LOCKED_ITEM:
                    mLevel = position + 1;
                    LockedQuizHolder lockedQuizHolder = (LockedQuizHolder) holder;
                    lockedQuizHolder.bindLockedQuizData();

            }

        }

        @Override
        public int getItemCount() {
            return mQuizzes.size();
        }

        @Override
        public int getItemViewType(int position) {

            unlockNextQuiz(mQuizzes, position);

            if (mQuizzes.get(position).getLockedState() == 1) {
                return UN_LOCKED_ITEM;
            } else if (mQuizzes.get(position).getLockedState() == 0) {
                return LOCKED_ITEM;
            }
            return -1;

        }

        public void setQuizzes(List<Quiz> quizzes) {
            mQuizzes = quizzes;
        }
    }

    public void updateUI(QuizUserList quizUserList, RecyclerView areaRecyclerView) {

        List<Quiz> quizzes = quizUserList.getQuizzes(mAreaTable);

        if (mViewAdapter == null) {

            mViewAdapter = new QuizViewAdapter(quizzes);
            //create a new object QuizAdapter and pass in the ListArray of quizzes
            areaRecyclerView.setAdapter(mViewAdapter);

        } else {
            //call setQuizzes on mAdapter and pass in the list of quizzes
            mViewAdapter.setQuizzes(quizzes);
            mViewAdapter.notifyDataSetChanged();

            Log.d(TAG, "Update UI called ");

        }

    }

    public void unlockNextQuiz(List<Quiz> quizzes, int position) {

        int listSize = quizzes.size();
        int nextPosition;

        Quiz currentQuiz = quizzes.get(position);

        if (position == (listSize - 1)) {
            nextPosition = position;
        } else {
            nextPosition = position + 1;
        }

        if (currentQuiz.getTotalCorrect() >= 9) {
                Quiz nextQuiz = quizzes.get(nextPosition);
                nextQuiz.setLockedState(1);
                QuizUserList.get(getActivity()).updateQuiz(mAreaTable, nextQuiz);
        }
    }

    public void newQuizListIntent(Quiz quiz) {
        Intent intent = new Intent(getContext(), QuizListActivity.class);
        intent.putExtra(QUIZ_UUID, quiz.getID());
        intent.putExtra(QUIZ_AREA_TABLE, mAreaTable);
        startActivity(intent);
    }

    public void setAreaTable(String areaTable) {
        mAreaTable = areaTable;
    }

    public static boolean hasInternetConnection(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // return if device has network connection. all properties must not return null or true.
        return connectivityManager != null &&
                connectivityManager.getActiveNetworkInfo() != null &&
                connectivityManager.getActiveNetworkInfo().isConnected();
    }

}
