package co.wordpal.mywordpalappfree.elementary;

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
 * Created by salaz on 10/20/2016.
 * This fragment will house all difficulty levels of the Elementary Area
 */

public class ElementaryAreaFragment extends AreaLevelListSetup {

    private static final String TAG = ElementaryAreaFragment.class.getName();

    private RecyclerView mElementaryRecyclerView;
    private QuizUserList mQuizUserList;
    private AdView mElementaryAd;

    public ElementaryAreaFragment() {
    }

    public static ElementaryAreaFragment newInstance() {
        ElementaryAreaFragment fragment = new ElementaryAreaFragment();

        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.elementary_levels, container, false);

        mElementaryRecyclerView = view.findViewById(R.id.elementary_recyclerview);
        mElementaryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mQuizUserList = QuizUserList.get(getActivity());
        this.setAreaTable(QuizDbSchema.AreaTables.ELEMENTARY_TABLE);

        mElementaryAd = view.findViewById(R.id.elementary_adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mElementaryAd.loadAd(adRequest);

        updateUI(mQuizUserList, mElementaryRecyclerView);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI(mQuizUserList, mElementaryRecyclerView);

    }


}
