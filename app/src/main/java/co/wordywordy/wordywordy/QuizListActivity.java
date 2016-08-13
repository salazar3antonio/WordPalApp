package co.wordywordy.wordywordy;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by salaz on 8/7/2016.
 */
public class QuizListActivity extends SingleFragmentActivity {

    private String mJSONasString;


    @Override
    protected Fragment createFragment() {

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mJSONasString = bundle.getString(QuizSetupFragment.JSON_RESPONSE);
        }

        return QuizListFragment.newInstance(mJSONasString);
    }
}
