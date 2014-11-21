package zystudio.cases.graphics.anim;

import java.lang.reflect.Method;

import android.app.Activity;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class CaseTransformation {

    private Activity mActivity;
    private Transformation mTransformation = new Transformation();

    public CaseTransformation(Activity activity) {
        this.mActivity = activity;
    }

    public void work() {

    }

    // Animation的applyTransformation(float,Transformation)方法
    private static Method getApplyTransformation() {
        try {
            final Method m = Animation.class.getDeclaredMethod(
                    "applyTransformation", float.class, Transformation.class);
            m.setAccessible(true);
            return m;
        } catch (NoSuchMethodException e) {
            // we don't care because this exception should never happen
        }
        return null;
    }
}
