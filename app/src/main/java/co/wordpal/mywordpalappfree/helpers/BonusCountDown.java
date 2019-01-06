package co.wordpal.mywordpalappfree.helpers;

import android.os.CountDownTimer;
import android.widget.ProgressBar;

/**
 * Created by salaz on 10/8/2016.
 */

public class BonusCountDown extends CountDownTimer {

    private int i = 101;
    private ProgressBar mProgressbar;
    private TickerUpdateListener mTickerUpdate;

    public BonusCountDown(long millisInFuture, long countDownInterval, ProgressBar progressBar, TickerUpdateListener tickerUpdateListener) {
        super(millisInFuture, countDownInterval);
        mProgressbar = progressBar;
        mTickerUpdate = tickerUpdateListener;
    }

    public interface TickerUpdateListener {
        void onCheckTicker(int progress, ProgressBar progressBar);
    }

    @Override
    public void onTick(long l) {
        i--;
        mProgressbar.setProgress(i);
        mTickerUpdate.onCheckTicker(i, mProgressbar);
    }


    @Override
    public void onFinish() {
        mProgressbar.setProgress(i);
    }


}
