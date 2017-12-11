package zystudio.demo;

import zystudio.cases.androidmechanism.CaseContextKinds;
import zystudio.cases.androidmechanism.CaseDefaultBrowser;
import zystudio.cases.dataprocess.CaseCreateTempFile;
import zystudio.cases.dataprocess.net.CaseGson;
import zystudio.cases.designpattern.CaseDecorator;
import zystudio.cases.designpattern.CaseDynamicProxy;
import zystudio.cases.designpattern.CaseObserver;
import zystudio.cases.graphics.CaseBitmapOperate;
import zystudio.cases.javabase.CaseAutoBoxingUnboxing;
import zystudio.cases.javabase.CaseForTryFinally;
import zystudio.cases.javabase.CaseForTryFinally2;
import zystudio.cases.javabase.CaseShiftOperation;
import zystudio.cases.javabase.annotation.CaseForAnnotation;
import zystudio.cases.javabase.annotation_fruit.CaseAnnotationFruit;
import zystudio.cases.javabase.clsload.CaseClsLoadOrder;
import zystudio.cases.javabase.container.CaseLinkedHashMapAccessOrder;
import zystudio.cases.javabase.container.CaseSynchronousQueue;
import zystudio.cases.javabase.initfieldload.CaseInitFieldDemo;
import zystudio.cases.multithread.CaseExecutor;
import zystudio.cases.multithread.CaseNotifyVsNotifyAll;
import zystudio.cases.multithread.CaseReentrantLock;
import zystudio.cases.multithread.CaseReentrantLock3;
import zystudio.cases.prepare.CaseBufferAllocate;
import zystudio.cases.prepare.CaseForLinkedHashMap;
import zystudio.cases.prepare.CaseForViewRootImpl;
import zystudio.demo.activitylifecycle.CaseThreeActivityStart;
import zystudio.demo.activitylifecycle2.CaseActivityLifeCycle2;
import zystudio.demo.plugin.CaseAndroidPluginDemo;
import zystudio.demo.threeactivitylifecycle.CaseMultiActivityLifeCycle;
import zystudio.mylib.utils.LogUtil;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class CaseInvoke {

    private static void invokeCase(Class<?> clazz, Activity act) {
        //判断clazz 的构造函数，有没有不需要参数的构造默认函数
        /**
         * class类有四个得到构造函数的method:
         * getConstructors : 这个只返回public的构造函数
         * getDeclaredConstructors: 这个返回包括public 与private的所有构造函数，就是这个类里所有的构造函数，都在这里边
         * getConstructor(Class<?> ...parameterTypes)
         * getDeclaredConstructor( Class<?>... parameterTypes)
         *
         * 同时构造一个新实例，也有这么几个函数：
         *
         */
        try {
            Constructor consMethod = clazz.getConstructor();

            //可变参数，不写参数也可以，写空参数也可以
            //Constructor consMethod=clazz.getConstructor(new Class[]{});
            if (consMethod != null) {
                Object obj = clazz.newInstance();
//                method.invoke(obj,null);
                //Method method=clazz.getMethod("work",new Class<?>[]{});
                Method method = clazz.getMethod("work");
                method.invoke(obj);
                return;
            }

            //后两个是带有context或activity的构造函数，这两个还没经过测试，不过看样子还不算太麻烦
            consMethod = clazz.getConstructor(Context.class);
            if (consMethod == null) {
                consMethod = clazz.getConstructor(Activity.class);
            }
            if (consMethod != null) {
                Object obj = consMethod.newInstance(act);
                Method method = clazz.getMethod("work");
                method.invoke(obj);
                return;
            }

        } catch (Exception e) {
            if (e != null) {
                LogUtil.log(e.getMessage());
            }
        }
    }

    public static void invokeCase(Activity activity) {

        CaseSynchronousQueue.obtain().showCase();
        CaseDynamicProxy.obtain().perform();
        CaseClsLoadOrder.showCase(activity);
        CaseActivityLifeCycle2.showCase(activity);
        CaseContextKinds.obtain(activity).work();
        CaseThreeActivityStart.work(activity);
        CaseForTryFinally.obtain().work();
        CaseForTryFinally2.obtain().work();
        CaseBufferAllocate.obtain().work();
//-----------------------------------develop start-----------------------------/
        (new CaseAndroidPluginDemo()).work(activity);
        (new CaseInitFieldDemo()).work();
        (new CaseReentrantLock3()).work();
        (new CaseReentrantLock()).work();
        CaseNotifyVsNotifyAll.work();
        (new CaseMultiActivityLifeCycle(activity)).work();
        invokeCase(CaseAutoBoxingUnboxing.class, activity);
        invokeCase(CaseLinkedHashMapAccessOrder.class, activity);
        invokeCase(CaseAnnotationFruit.class,activity);
        CaseForAnnotation.getInstance(activity).work();
//-----------------------------------develop End-----------------------------/
//        (new CaseHashSet()).work();
//        (new CaseIOReader()).work();
//        (new CaseCharEncode(activity)).showCase();
//        (new CaseArrayListNew()).work();
//        (new CaseMaps()).work();
//        CaseAsyncGenerateBitmap.getInstance(activity).work();
//        (new CaseForEachIterator()).work();
//        CaseAsyncGenerateBitmap.getInstance(activity).work();
//         CaseCustViewPager.getInstance().showCase(activity);
//         CaseOOMErrorCatch.obtain(activity).work();
//         CaseRandomAccessFile.showDemo();
//         MixColorTextActivity.startMixColorActivity(activity);
//         CaseAES.getInstance(activity).showCase();
//         CaseLoadExternalApkActivity.launch(activity);
//         CaseShow.show();
//         CaseAOP.obtain().performTest();
//         CaseVelloyNetActivity.start(activity);
//         CaseBlockingQueue.getInstance().showCase();
//         CaseStaticLayout.getInstance(activity).work();
//         CaseDrawText.obtain(activity).work();
//         CaseInstanceof.obtain(activity).work();
//         CaseTimerAndTimerTask.intance().work();
//         CaseThreadPriority.getInstance(activity).work();
//         CaseForCustViewAttr.obtain(activity).work();
//         CaseParamDelivery.work();
//         CaseAddAdd.work();
//        CaseCallableAndFuture.instance().work();
//        CaseFutureCancel.instance().work();
//        CaseCountDownLatch.obtain().work();
//        CaseThreadJoin.obtain().work();
//         CaseJavaRandom.obtain().work();
//         CaseDeepCopy.obtain().work();
//         CaseShallowCopy.obtain().work();
//         CaseURLEncoder.obtain().work();
//         CaseBase64.obtain().work();
//         CaseMD5Digest.obtain().work();
//         CaseScrolls.obtain(activity).work();
//         CaseInvokeFinalStatic.obtain().work();
//         CaseTrigger.obtain(activity).work();
//         CaseLikeEscape.obtain(activity).work();
//        CaseForTouch2.obtain(activity).work();
//         CaseDecorMeasureInfo.instance(activity).work();
//         CaseForRegex.obtain().work();
//         CaseDrawables.obtain(activity).work();
//         CaseJavaContainer.obtain().work();
//         CaseCanvas.obtain(activity).work();
//         CaseSpecialDrawable.obtain(activity).work();
//         CaseDownloadActivity.start(activity);
//         CaseGson.obtain(activity).work();
//         CaseBitmapOperate.obtain(activity).work();
//         CaseForLinkedHashMap.instance(activity).work();
//         CaseCreateTempFile.showDemo(activity);
//         CaseForViewRootImpl.getInstance(activity).work();
//         CaseShiftOperation.obtain(activity).work();
//         CaseForCustomAnim1.obtain(activity).work();
//         CaseForMath.obtain().work();
//         CaseForFinal.getInstance(activity).work();
//         CaseForTryFinally.obtain().work();
//         CaseForResourceUri.obtain().work(activity);
//         CaseForNullInvoke.obtain().work();
//         CaseForFinal.getInstance(activity).work();
//         CaseForAnim1.getInstance(activity).work();
//         CaseForDraw.getInstance(activity).work();
//         CaseInterpolator.obtain(this).work();
//         CaseViewConfiguration.obtain(this).work();
//         CaseBitMask.obtain().work();
//         CaseForTouch.obtain(this).work4();
//         CaseExecutor.getInstance().work(0);
//         CaseObserver.getInstance().work();
//         (new Handler()).postDelayed(new Runnable(){
//         @Override
//         public void run() {
//         CaseDefaultBrowser.getInstance().work1(MainActivity.this);
//         }
//         }, 1000);
//
//         CaseDecorator.getInstance().work();
//
//         String str= CaseForJson.assembleMsgIdArrayStr("wangzhengyu");
        // Log.i("ertewu","str is:"+str);
    }
}
