package co.wordpal.mywordpalappfree.middleschool;

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
 * Created by salaz on 12/8/2016.
 */

public class MiddleSchoolAreaFragment extends AreaLevelListSetup {

    private static final String TAG = MiddleSchoolAreaFragment.class.getName();

    private RecyclerView mMiddleSchoolRecyclerView;
    private QuizUserList mQuizUserList;
    private AdView mMiddleSchoolAd;

    public MiddleSchoolAreaFragment() {
    }

    public static MiddleSchoolAreaFragment newInstance() {
        MiddleSchoolAreaFragment fragment = new MiddleSchoolAreaFragment();

        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.middle_school_levels, container, false);

        mMiddleSchoolRecyclerView = view.findViewById(R.id.middle_school_recyclerview);
        mMiddleSchoolRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mMiddleSchoolAd = view.findViewById(R.id.middleschool_adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mMiddleSchoolAd.loadAd(adRequest);

        mQuizUserList = QuizUserList.get(getActivity());
        this.setAreaTable(QuizDbSchema.AreaTables.MIDDLESCHOOL_TABLE);

        updateUI(mQuizUserList, mMiddleSchoolRecyclerView);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI(mQuizUserList, mMiddleSchoolRecyclerView);

    }


}
