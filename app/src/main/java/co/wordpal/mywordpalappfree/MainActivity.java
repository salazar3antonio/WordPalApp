package co.wordpal.mywordpalappfree;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.List;

import co.wordpal.mywordpalappfree.data.model.Quiz;
import co.wordpal.mywordpalappfree.database.QuizDbSchema;
import co.wordpal.mywordpalappfree.database.QuizListDBInitializer;
import co.wordpal.mywordpalappfree.database.QuizUserList;
import co.wordpal.mywordpalappfree.menu.SettingsActivity;


public class MainActivity extends AppCompatActivity {

    private MainFragmentPagerAdapter mPagerAdapter;
    private ViewPager mViewPager;
    private int[] tabIcons = {R.drawable.star_white, R.drawable.heart_white};
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private QuizUserList mQuizUserList;
    private QuizListDBInitializer mQuizListDBInitializer;
    private AdView mMainAreasAd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, "PLACE_AD_ID_HERE");

        mCollapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        mCollapsingToolbarLayout.setTitleEnabled(false);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mPagerAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager());
        mViewPager = findViewById(R.id.main_container);
        mViewPager.setAdapter(mPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.main_tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);

        mQuizUserList = QuizUserList.get(getApplicationContext());
        mQuizListDBInitializer = new QuizListDBInitializer(mQuizUserList);

        mMainAreasAd = findViewById(R.id.main_areas_adview);
        AdRequest adRequest = new AdRequest.Builder().build();
        mMainAreasAd.loadAd(adRequest);

        initAllQuizzes();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_settings:
                openSettings();
                return true;
//            case R.id.menu_clear_quiz_db:
//                getApplication().deleteDatabase(QuizBaseHelper.DATABASE_FILE_NAME);
//                Toast.makeText(this, "Quiz DB Cleared. Kill app and restart.", Toast.LENGTH_LONG).show();
//                return true;
//            case R.id.menu_clear_favword_db:
//                getApplication().deleteDatabase(FavWordBaseHelper.DATABASE_FILE_NAME);
//                Toast.makeText(this, "FavWord DB Cleared. Kill app and restart.", Toast.LENGTH_LONG).show();
//                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void openSettings() {
        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivity(intent);
    }

    public void initAllQuizzes() {

        List<Quiz> esQuizzes = mQuizUserList.getQuizzes(QuizDbSchema.AreaTables.ELEMENTARY_TABLE);
        List<Quiz> msQuizzes = mQuizUserList.getQuizzes(QuizDbSchema.AreaTables.MIDDLESCHOOL_TABLE);
        List<Quiz> hsQuizzes = mQuizUserList.getQuizzes(QuizDbSchema.AreaTables.HIGHSCHOOL_TABLE);
        List<Quiz> satQuizzes = mQuizUserList.getQuizzes(QuizDbSchema.AreaTables.SAT_TABLE);
        List<Quiz> gmatQuizzes = mQuizUserList.getQuizzes(QuizDbSchema.AreaTables.GMAT_TABLE);
        List<Quiz> overallQuizzes = mQuizUserList.getQuizzes(QuizDbSchema.AreaTables.OVERALL_TABLE);


        if (esQuizzes.size() == 0) {
            mQuizListDBInitializer.createQuizzesInDB(esQuizzes, "es", 5, QuizDbSchema.AreaTables.ELEMENTARY_TABLE);
            mQuizListDBInitializer.createQuizzesInDB(msQuizzes, "ms", 7, QuizDbSchema.AreaTables.MIDDLESCHOOL_TABLE);
            mQuizListDBInitializer.createQuizzesInDB(hsQuizzes, "hs", 9, QuizDbSchema.AreaTables.HIGHSCHOOL_TABLE);
            mQuizListDBInitializer.createQuizzesInDB(satQuizzes, "sat", 10, QuizDbSchema.AreaTables.SAT_TABLE);
            mQuizListDBInitializer.createQuizzesInDB(gmatQuizzes, "gmat", 10, QuizDbSchema.AreaTables.GMAT_TABLE);
            mQuizListDBInitializer.createQuizzesInDB(overallQuizzes, "overall", 10, QuizDbSchema.AreaTables.OVERALL_TABLE);
        }

    }


}
