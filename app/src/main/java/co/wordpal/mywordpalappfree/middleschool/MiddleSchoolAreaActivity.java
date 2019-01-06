package co.wordpal.mywordpalappfree.middleschool;

import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import co.wordpal.mywordpalappfree.helpers.SingleFragmentActivity;

/**
 * Created by salaz on 12/8/2016.
 */

public class MiddleSchoolAreaActivity extends SingleFragmentActivity {

    private static final String TITLE = "middleschool_title";

    private String mTitle = "Middle School";

    @Override
    protected Fragment createFragment() {

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(mTitle);

        return MiddleSchoolAreaFragment.newInstance();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(TITLE, "Middle School");

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        mTitle = savedInstanceState.getString(TITLE);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(mTitle);

    }

}
