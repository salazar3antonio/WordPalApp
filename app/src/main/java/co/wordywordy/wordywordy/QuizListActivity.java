package co.wordywordy.wordywordy;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by salaz on 8/7/2016.
 */
public class QuizListActivity extends SingleFragmentActivity {

    private String mArea;
    private String mLevel;


    @Override
    protected Fragment createFragment() {

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mArea = bundle.getString(QuizSetupFragment.AREA_SELECTED);
            mLevel = bundle.getString(QuizSetupFragment.LEVEL_SELECTED);
        }

        return QuizListFragment.newInstance(mArea, mLevel);
    }
}
