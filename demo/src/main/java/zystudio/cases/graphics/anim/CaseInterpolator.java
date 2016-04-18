package zystudio.cases.graphics.anim;

import android.app.Activity;
import android.util.Log;
import android.view.animation.DecelerateInterpolator;

public class CaseInterpolator {

    private static CaseInterpolator sCase;
    private Activity mAct;
    private DecelerateInterpolator mInterpolator;

    public static CaseInterpolator obtain(Activity act) {
        if (sCase == null) {
            sCase = new CaseInterpolator();
            sCase.mAct = act;
        }
        return sCase;
    }

    public void work() {
        showDecelerateInterpolatorDemo();
    }

    private void showDecelerateInterpolatorDemo() {

        mInterpolator = new DecelerateInterpolator();
        final long startTime = System.currentTimeMillis();
        final long end = 2000;
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {

                long diff = 0;
                while (diff < end) {

                    long currentTime = System.currentTimeMillis();
                    diff = currentTime - startTime;
                    //getInterpolation()这个计算在源码中可以轻易看到了..
                    float value = mInterpolator
                            .getInterpolation((diff / end) * 2000);
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        Log.i("ertewu", "r45:errorOccured");
                    } finally {
                        Log.i("ertewu", "r47:" + value);
                    }
                }
            }
        });
        thread.start();
    }
}
