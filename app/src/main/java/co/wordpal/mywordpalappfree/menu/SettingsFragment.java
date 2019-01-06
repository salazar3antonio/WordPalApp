package co.wordpal.mywordpalappfree.menu;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import co.wordpal.mywordpalappfree.R;

/**
 * Created by salaz on 11/25/2016.
 */

public class SettingsFragment extends PreferenceFragment {

    public SettingsFragment() {

    }

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);


    }
}
