package co.wordywordy.wordywordy;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by salaz on 2/15/2016.
 */
public abstract class SingleFragmentActivity extends AppCompatActivity {

    protected abstract Fragment createFragment();

    //this abstract class overrides the oncreate() method of AppCompatActivity
    // thus every time a new class extends this class a call to onCreate must be made

    //LayoutRes tells AS to return a valid layout resource id
    @LayoutRes
    protected int getLayoutResId() {
        // created a protected method that allows a subclass of this class to ovrride the fragment layout that is being inflated
        return R.layout.activity_fragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        // this gets the support fragment manager from v4 dependency
        FragmentManager fragmentManager = getSupportFragmentManager();
        //points fragment to the fragment container, a framelayout in xml
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);

        //look for the fragment if null (not present) then create a new transaction add a frame layout fragment
        if (fragment == null) {
            fragment = createFragment();
            fragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }


    }

}
