package co.wordpal.mywordpalappfree;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by salaz on 1/14/2017.
 */

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {

    public static MyFavWordsFragment sMyFavWordsFragment = new MyFavWordsFragment();
    public static MyAreasFragment sMyAreasFragment = new MyAreasFragment();

    public MainFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return MyAreasFragment.newInstance(position);
            case 1:
                return MyFavWordsFragment.newInstance(position);
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

}
