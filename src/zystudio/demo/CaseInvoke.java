package zystudio.demo;

import zystudio.cases.javabase.CaseTimerAndTimerTask;
import android.app.Activity;

public class CaseInvoke {

    public static void invokeCase2(Activity activity) {
        /*
         * 目前的思路是这样的,有几个功能:
         * 1, 所有的case都继承一个个基类,或一个接口(我猜应该是一个接口),应该是都可以接纳activity
         * 2, 有一个函数可以遍历得到所有case 的集合,反回的是class信息
         * 3,给出一个关键字,可以找到相应的case所对应的类,并构造这个类的case运行,如果没有这个,要有UI反馈
         * 4,工场类根据我写的字符串创造新的case,传activity,并调用运行
         * 可不想再单例那样了用了...
         */
    }

    public static void invokeCase(Activity activity) {
//        CaseDrawText.obtain(activity).work();
//        CaseInstanceof.obtain(activity).work();
        CaseTimerAndTimerTask.intance().work();
        // CaseCallableAndFuture.instance().work();
        // CaseJavaRandom.obtain().work();
        // CaseDeepCopy.obtain().work();
        // CaseShallowCopy.obtain().work();
        // CaseForCustViewAttr.obtain(activity).work();
        // CaseThreadJoin.obtain().work();
        // CaseURLEncoder.obtain().work();
        // CaseBase64.obtain().work();
        // CaseMD5Digest.obtain().work();
        // CaseScrolls.obtain(activity).work();
        // CaseInvokeFinalStatic.obtain().work();
        // CaseTrigger.obtain(activity).work();
        // CaseLikeEscape.obtain(activity).work();
        // CaseForTouch2.obtain(activity).work();
        // CaseDecorMeasureInfo.instance(activity).work();
        // CaseForRegex.obtain().work();
        // CaseDrawables.obtain(activity).work();
        // CaseJavaContainer.obtain().work();
        // CaseCanvas.obtain(activity).work();
        // CaseSpecialDrawable.obtain(activity).work();
        // CaseDownloadActivity.start(activity);
        // CaseGson.obtain(activity).work();
        // CaseBitmapOperate.obtain(activity).work();
        // CaseForLinkedHashMap.instance(activity).work();
        // CaseCreateTempFile.showDemo(activity);
        // CaseForViewRootImpl.getInstance(activity).work();
//         CaseShiftOperation.obtain(activity).work();
        // CaseForCustomAnim1.obtain(activity).work();
        // CaseThreadPriority.getInstance(activity).work();
        // CaseThreadJoin.getInstance(activity).work();
        // CaseForMath.obtain().work();
        // CaseForFinal.getInstance(activity).work();

        // CaseForTryFinally.obtain().work();
        // CaseForResourceUri.obtain().work(activity);

        // CaseForNullInvoke.obtain().work();

        // CaseForFinal.getInstance(activity).work();

        // CaseForAnim1.getInstance(activity).work();
        // CaseForAnnotation.getInstance(this).work();

        // CaseForDraw.getInstance(activity).work();

        // CaseForDraw.getInstance(activity).work();

        // CaseInterpolator.obtain(this).work();

        // CaseViewConfiguration.obtain(this).work();

        // CaseBitMask.obtain().work();

        // CaseForTouch.obtain(this).work4();

        // CaseExecutor.getInstance().work(0);

        // CaseObserver.getInstance().work();

        // (new Handler()).postDelayed(new Runnable(){
        // @Override
        // public void run() {
        // CaseDefaultBrowser.getInstance().work1(MainActivity.this);
        // }
        // }, 1000);

        // CaseDecorator.getInstance().work();

        // String str= CaseForJson.assembleMsgIdArrayStr("wangzhengyu");
        // Log.i("ertewu","str is:"+str);
    }
}
