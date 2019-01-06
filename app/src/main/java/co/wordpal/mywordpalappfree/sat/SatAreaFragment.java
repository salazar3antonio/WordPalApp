package co.wordpal.mywordpalappfree.sat;

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

public class SatAreaFragment extends AreaLevelListSetup {

    private static final String TAG = SatAreaFragment.class.getName();

    private RecyclerView mSatRecyclerView;
    private QuizUserList mQuizUserList;
    private AdView mSatAd;

    public SatAreaFragment() {
    }

    public static SatAreaFragment newInstance() {
        SatAreaFragment fragment = new SatAreaFragment();

        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sat_levels, container, false);

        mSatRecyclerView = view.findViewById(R.id.sat_recyclerview);
        mSatRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mSatAd = view.findViewById(R.id.sat_adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mSatAd.loadAd(adRequest);

        mQuizUserList = QuizUserList.get(getActivity());
        this.setAreaTable(QuizDbSchema.AreaTables.SAT_TABLE);

        updateUI(mQuizUserList, mSatRecyclerView);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI(mQuizUserList, mSatRecyclerView);

    }

}
