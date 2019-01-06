package co.wordpal.mywordpalappfree.gmat;

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

public class GmatAreaFragment extends AreaLevelListSetup {

    private static final String TAG = GmatAreaFragment.class.getName();

    private RecyclerView mGmatlRecyclerView;
    private QuizUserList mQuizUserList;
    private AdView mGmatAd;

    public GmatAreaFragment() {
    }

    public static GmatAreaFragment newInstance() {
        GmatAreaFragment fragment = new GmatAreaFragment();

        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gmat_levels, container, false);

        mGmatlRecyclerView = view.findViewById(R.id.gmat_recyclerview);
        mGmatlRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mGmatAd = view.findViewById(R.id.gmat_adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mGmatAd.loadAd(adRequest);

        mQuizUserList = QuizUserList.get(getActivity());
        this.setAreaTable(QuizDbSchema.AreaTables.GMAT_TABLE);

        updateUI(mQuizUserList, mGmatlRecyclerView);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI(mQuizUserList, mGmatlRecyclerView);

    }

}

