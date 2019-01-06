package co.wordpal.mywordpalappfree.quiz_report;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import co.wordpal.mywordpalappfree.R;
import co.wordpal.mywordpalappfree.data.model.Word;
import co.wordpal.mywordpalappfree.database.FavWordUserList;

/**
 * Created by salaz on 11/28/2016.
 */

public class WordMeaningFragment extends DialogFragment {

    private Word mWord;

    private TextView mWord_view;
    private TextView mNoun_view;
    private TextView mVerb_view;
    private TextView mAdverb_view;
    private TextView mAdjective_view;

    private LinearLayout mNounGroup;
    private LinearLayout mVerbGroup;
    private LinearLayout mAdverbGroup;
    private LinearLayout mAdjectiveGroup;

    private CheckBox mFavWord_check;
    private List<Word> mFavWordList;


    public static WordMeaningFragment newInstance() {
        return new WordMeaningFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mWord = new Word();

        savedInstanceState = getArguments();
        if (savedInstanceState != null) {
            mWord.setWord(savedInstanceState.getString(QuizReportActivity.WORD));
            mWord.setNounMeaning(savedInstanceState.getString(QuizReportActivity.NOUN_MEANING));
            mWord.setVerbMeaning(savedInstanceState.getString(QuizReportActivity.VERB_MEANING));
            mWord.setAdverbMeaning(savedInstanceState.getString(QuizReportActivity.ADVERB_MEANING));
            mWord.setAdjectiveMeaning(savedInstanceState.getString(QuizReportActivity.ADJECTIVE_MEANING));
        }

        mFavWordList = FavWordUserList.get(getActivity()).getFavWords();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_word_meanings_full, container, false);

        mWord_view = view.findViewById(R.id.selected_word);
        mNoun_view = view.findViewById(R.id.noun_meaning);
        mVerb_view = view.findViewById(R.id.verb_meaning);
        mAdverb_view = view.findViewById(R.id.adverb_meaning);
        mAdjective_view = view.findViewById(R.id.adjective_meaning);

        mNounGroup = view.findViewById(R.id.noun_group);
        mVerbGroup = view.findViewById(R.id.verb_group);
        mAdverbGroup = view.findViewById(R.id.adverb_group);
        mAdjectiveGroup = view.findViewById(R.id.adjective_group);

        mFavWord_check = view.findViewById(R.id.fav_word_check);

        //check to see if word is already in favorites
        //if it is, then check the heart icon
        for (Word word : mFavWordList) {
            if (word.getFavoriteWord().equals(mWord.getWord())) {
                mFavWord_check.setChecked(true);
                break;
            }
        }

        mWord_view.setText(mWord.getWord());
        mNounGroup.setVisibility(View.GONE);
        mVerbGroup.setVisibility(View.GONE);
        mAdverbGroup.setVisibility(View.GONE);
        mAdjectiveGroup.setVisibility(View.GONE);

        if (mWord.getNounMeaning().length() != 0) {
            mNounGroup.setVisibility(View.VISIBLE);
            mNoun_view.setText(mWord.getNounMeaning());
        }
        if (mWord.getVerbMeaning().length() != 0) {
            mVerbGroup.setVisibility(View.VISIBLE);
            mVerb_view.setText(mWord.getVerbMeaning());
        }
        if (mWord.getAdverbMeaning().length() != 0) {
            mAdverbGroup.setVisibility(View.VISIBLE);
            mAdverb_view.setText(mWord.getAdverbMeaning());
        }
        if (mWord.getAdjectiveMeaning().length() != 0) {
            mAdjectiveGroup.setVisibility(View.VISIBLE);
            mAdjective_view.setText(mWord.getAdjectiveMeaning());
        }

        mFavWord_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b) {
                    mWord.setFavoriteWord(mWord.getWord());
                    FavWordUserList.get(getActivity()).addFavWord(mWord);
                    Toast.makeText(getActivity(), "Word saved.", Toast.LENGTH_SHORT).show();
                } else {
                    FavWordUserList.get(getActivity()).deleteFavWord(mWord.getWord());
                    Toast.makeText(getActivity(), "Word unsaved.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;

    }

    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }

    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

        if (getTargetFragment() != null) {
            getTargetFragment().onActivityResult(getTargetRequestCode(), 2, getActivity().getIntent());
        }


    }
}
