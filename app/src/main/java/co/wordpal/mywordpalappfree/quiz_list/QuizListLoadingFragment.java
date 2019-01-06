package co.wordpal.mywordpalappfree.quiz_list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.wordpal.mywordpalappfree.R;

/**
 * Created by Desktop on 11/19/2016.
 */

public class QuizListLoadingFragment extends Fragment {

    public static QuizListLoadingFragment newInstance() {
        return new QuizListLoadingFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz_list_loading, container, false);
        return view;
    }
}
