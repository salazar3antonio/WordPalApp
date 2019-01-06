package co.wordpal.mywordpalappfree.highschool;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import co.wordpal.mywordpalappfree.R;
import co.wordpal.mywordpalappfree.database.QuizDbSchema;
import co.wordpal.mywordpalappfree.database.QuizUserList;
import co.wordpal.mywordpalappfree.helpers.AreaLevelListSetup;

/**
 * Created by salaz on 12/12/2016.
 */

public class HighSchoolAreaFragment extends AreaLevelListSetup {

    private static final String TAG = HighSchoolAreaFragment.class.getName();

    private RecyclerView mHighSchoolRecyclerView;
    private QuizUserList mQuizUserList;
    private AdView mHighSchoolAd;

    public HighSchoolAreaFragment() {
    }

    public static HighSchoolAreaFragment newInstance() {
        HighSchoolAreaFragment fragment = new HighSchoolAreaFragment();

        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.high_school_levels, container, false);

        mHighSchoolRecyclerView = view.findViewById(R.id.high_school_recyclerview);
        mHighSchoolRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mHighSchoolAd = view.findViewById(R.id.high_school_adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mHighSchoolAd.loadAd(adRequest);

        mQuizUserList = QuizUserList.get(getActivity());
        this.setAreaTable(QuizDbSchema.AreaTables.HIGHSCHOOL_TABLE);

        updateUI(mQuizUserList, mHighSchoolRecyclerView);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI(mQuizUserList, mHighSchoolRecyclerView);

    }

}
