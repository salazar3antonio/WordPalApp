package co.wordpal.mywordpalappfree.helpers;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import co.wordpal.mywordpalappfree.R;

/**
 * Created by salaz on 2/15/2016.
 */
public abstract class SingleFragmentActivity extends AppCompatActivity {

    protected abstract Fragment createFragment();

    //this abstract class overrides the oncreate() method of AppCompatActivity

    @LayoutRes
    protected int getLayoutResId() {
        return R.layout.activity_fragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        // this gets the support fragment manager from v4 dependency
        FragmentManager fragmentManager = getSupportFragmentManager();
        //points fragment to the fragment container
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);

        //look for the fragment if null (not present) then create  new transaction add layout fragment
        if (fragment == null) {
            fragment = createFragment();
            fragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }


    }

}
