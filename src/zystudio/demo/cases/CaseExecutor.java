package zystudio.demo.cases;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.util.Log;

public class CaseExecutor {

    private static CaseExecutor sCase;
    private ExecutorService mService;
    private int var = 1;

    public static CaseExecutor getInstance() {
        if (sCase == null) {
            sCase = new CaseExecutor();
        }
        return sCase;
    }

    private CaseExecutor() {
        // mService=Executors.newSingleThreadExecutor();

    }

    private void setOpt(final int opt) {
        /**
         * </br>Opt为0 代表： mService=Executors.newSingleThreadExecutor();
         * </br>Opt为1 代表：mService=Executors.newCachedThreadPool();
         */
        switch (opt) {
            case 0:
                mService = Executors.newSingleThreadExecutor();
                break;
            case 1:
                mService = Executors.newCachedThreadPool();
                break;
        }
    }

    public void work(final int opt) {
        setOpt(opt);
        Runnable run1 = new Runnable() {

            @Override
            public void run() {

                for (int i = 0; i < 50; i++) {
                    Log.i("ertewu", "run1:" + Thread.currentThread().getId() + "|" + var);
                    var++;
                }
            }
        };

        Runnable run2 = new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    Log.i("ertewu", "run2:" + Thread.currentThread().getId() + "|" + var);
                    var++;
                }
            }
        };
        mService.execute(run1);
        mService.execute(run2);
    }
}
