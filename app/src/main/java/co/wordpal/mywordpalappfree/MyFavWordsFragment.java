package co.wordpal.mywordpalappfree;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import co.wordpal.mywordpalappfree.data.model.Word;
import co.wordpal.mywordpalappfree.database.FavWordUserList;
import co.wordpal.mywordpalappfree.helpers.EmptyRecyclerView;
import co.wordpal.mywordpalappfree.quiz_report.WordMeaningFragment;

import static co.wordpal.mywordpalappfree.quiz_report.QuizReportActivity.ADJECTIVE_MEANING;
import static co.wordpal.mywordpalappfree.quiz_report.QuizReportActivity.ADVERB_MEANING;
import static co.wordpal.mywordpalappfree.quiz_report.QuizReportActivity.NOUN_MEANING;
import static co.wordpal.mywordpalappfree.quiz_report.QuizReportActivity.VERB_MEANING;
import static co.wordpal.mywordpalappfree.quiz_report.QuizReportActivity.WORD;

/**
 * Created by salaz on 1/12/2017.
 * This fragment class holds the favorite words
 */

public class MyFavWordsFragment extends Fragment {

    public static final String TAG = MyFavWordsFragment.class.getName();
    public static final String ARG_PAGE = "arg_page";
    public static final int FAV_WORD_REQUEST_CODE = 1;
    private int mPage;

    private EmptyRecyclerView mRecyclerView;
    private FavWordAdapter mFavWordAdapter;

    private View mEmptyView;

    public MyFavWordsFragment() {
        // Required empty public constructor
    }

    public static MyFavWordsFragment newInstance(int section) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, section);
        MyFavWordsFragment fragment = new MyFavWordsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
        Log.d(TAG, "onCreate called");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my_fav_words, container, false);

        mRecyclerView = view.findViewById(R.id.fav_word_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mEmptyView = view.findViewById(R.id.fav_word_empty_view);
        mRecyclerView.setEmptyView(mEmptyView);

        updateFavWordList();

        return view;
    }

    //need to define inner class for View Holder
    private class FavWordHolder extends RecyclerView.ViewHolder {

        private Word mFavWord;
        private TextView mFavWord_view;


        public FavWordHolder(View itemView) {
            super(itemView);

            mFavWord_view = itemView.findViewById(R.id.fav_word);


            //populate a new WordMeaningFragment when user clicks on word
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    WordMeaningFragment wordMeaningFragment = new WordMeaningFragment();
                    Bundle wordBundle = new Bundle();
                    wordBundle.putString(WORD, mFavWord.getFavoriteWord());
                    wordBundle.putString(NOUN_MEANING, mFavWord.getNounMeaning());
                    wordBundle.putString(VERB_MEANING, mFavWord.getVerbMeaning());
                    wordBundle.putString(ADVERB_MEANING, mFavWord.getAdverbMeaning());
                    wordBundle.putString(ADJECTIVE_MEANING, mFavWord.getAdjectiveMeaning());
                    wordMeaningFragment.setArguments(wordBundle);

                    wordMeaningFragment.setTargetFragment(MyFavWordsFragment.this, FAV_WORD_REQUEST_CODE);
                    wordMeaningFragment.show(getFragmentManager(), "favWord_dialog");

                }
            });
        }

        public void bindFavWord(Word favWord) {
            mFavWord = favWord;
            mFavWord_view.setText(mFavWord.getFavoriteWord());

        }
    }

    //need to define inner class for Adapter
    private class FavWordAdapter extends RecyclerView.Adapter<FavWordHolder> {

        private List<Word> mFavWords;

        public FavWordAdapter(List<Word> favWords) {
            mFavWords = favWords;
        }

        @Override
        public FavWordHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.fragment_fav_word_item, parent, false);
            return new FavWordHolder(view);
        }

        @Override
        public void onBindViewHolder(FavWordHolder holder, int position) {
            Word word = mFavWords.get(position);
            holder.bindFavWord(word);
        }

        @Override
        public int getItemCount() {
            return mFavWords.size();
        }

        private void setFavWords(List<Word> favWords) {
            mFavWords = favWords;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        updateFavWordList();
    }

    private void updateFavWordList() {

        List<Word> favWordList = FavWordUserList.get(getActivity()).getFavWords();

        if (mFavWordAdapter == null) {
            mFavWordAdapter = new FavWordAdapter(favWordList);
            mRecyclerView.setAdapter(mFavWordAdapter);
        } else {

            // sort favWords in ascending order
            Collections.sort(favWordList, new Comparator<Word>() {
                @Override
                public int compare(Word lhs, Word rhs) {
                    return lhs.getFavoriteWord().compareTo(rhs.getFavoriteWord());
                }
            });

            mFavWordAdapter.setFavWords(favWordList);
            mFavWordAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == 1) {
            if(resultCode == 2) {
                updateFavWordList();
            }
        }


    }
}
