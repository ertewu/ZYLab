package zystudio.demo;

import zystudio.cases.dataprocess.net.CaseVelloyNetActivity;
import zystudio.cases.graphics.CaseAsyncGenerateBitmap;
import zystudio.cases.ui.CaseCustViewPager;
import android.app.Activity;

public class CaseInvoke {

    public static void invokeCase(Activity activity) {

        CaseAsyncGenerateBitmap.getInstance(activity).work();
        // CaseCustViewPager.getInstance().showCase(activity);
        // CaseOOMErrorCatch.obtain(activity).work();
        // CaseRandomAccessFile.showDemo();
        // MixColorTextActivity.startMixColorActivity(activity);
        // CaseAES.getInstance(activity).showCase();
        // CaseLoadExternalApkActivity.launch(activity);
        // CaseShow.show();
        // CaseForAnnotation.getInstance(activity).work();
        // CaseAOP.obtain().performTest();
        // CaseDynamicProxy.obtain().perform();
        // CaseVelloyNetActivity.start(activity);
        // CaseBlockingQueue.getInstance().showCase();
        // CaseStaticLayout.getInstance(activity).work();
        // CaseDrawText.obtain(activity).work();
        // CaseInstanceof.obtain(activity).work();
        // CaseTimerAndTimerTask.intance().work();
        // CaseThreadPriority.getInstance(activity).work();
        // CaseForCustViewAttr.obtain(activity).work();
        // CaseParamDelivery.work();
        // CaseAddAdd.work();
        // CaseCallableAndFuture.instance().work();
        // CaseJavaRandom.obtain().work();
        // CaseDeepCopy.obtain().work();
        // CaseShallowCopy.obtain().work();
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
        // CaseShiftOperation.obtain(activity).work();
        // CaseForCustomAnim1.obtain(activity).work();
        // CaseThreadJoin.getInstance(activity).work();
        // CaseForMath.obtain().work();
        // CaseForFinal.getInstance(activity).work();
        // CaseForTryFinally.obtain().work();
        // CaseForResourceUri.obtain().work(activity);
        // CaseForNullInvoke.obtain().work();
        // CaseForFinal.getInstance(activity).work();
        // CaseForAnim1.getInstance(activity).work();
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
