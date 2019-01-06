package co.wordpal.mywordpalappfree.overall;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;

import co.wordpal.mywordpalappfree.helpers.SingleFragmentActivity;

/**
 * Created by salaz on 12/12/2016.
 */
public class OverallAreaActivity extends SingleFragmentActivity {

    private static final String TITLE = "overall_title";

    private String mTitle = "Overall Vocabulary Test";

    @Override
    protected Fragment createFragment() {

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(mTitle);

        return OverallAreaFragment.newInstance();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(TITLE, mTitle);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        mTitle = savedInstanceState.getString(TITLE);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(mTitle);

    }


}
