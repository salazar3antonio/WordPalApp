package co.wordpal.mywordpalappfree.overall;

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

public class OverallAreaFragment extends AreaLevelListSetup {

    private static final String TAG = OverallAreaFragment.class.getName();

    private RecyclerView mOverallRecyclerView;
    private QuizUserList mQuizUserList;
    private AdView mOverallAd;

    public OverallAreaFragment() {
    }

    public static OverallAreaFragment newInstance() {
        OverallAreaFragment fragment = new OverallAreaFragment();

        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.overall_levels, container, false);

        mOverallRecyclerView = view.findViewById(R.id.overall_recyclerview);
        mOverallRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mOverallAd = view.findViewById(R.id.overall_adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mOverallAd.loadAd(adRequest);

        mQuizUserList = QuizUserList.get(getActivity());
        this.setAreaTable(QuizDbSchema.AreaTables.OVERALL_TABLE);

        updateUI(mQuizUserList, mOverallRecyclerView);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI(mQuizUserList, mOverallRecyclerView);

    }

}