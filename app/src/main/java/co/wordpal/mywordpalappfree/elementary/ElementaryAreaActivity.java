package co.wordpal.mywordpalappfree.elementary;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;

import com.google.android.gms.ads.MobileAds;

import co.wordpal.mywordpalappfree.helpers.SingleFragmentActivity;

/**
 * Created by salaz on 10/20/2016.
 */

public class ElementaryAreaActivity extends SingleFragmentActivity {

    public static final String TITLE = "elementary_title";

    private String mTitle = "Elementary";

    @Override
    protected Fragment createFragment() {

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(mTitle);

        return ElementaryAreaFragment.newInstance();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(TITLE, "Elementary");

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        mTitle = savedInstanceState.getString(TITLE);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(mTitle);

    }
}
