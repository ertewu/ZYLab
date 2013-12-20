package com.example.demo.cases.anims;


import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.demo.R;

public class CaseForAnim1 {
	private static CaseForAnim1 sCase;
	private Activity mAct;
	Button mBtn1;
	Button mBtn2;

	Animator mAnimhide;

	public static CaseForAnim1 getInstance(Activity act){
		if(sCase==null){
			sCase=new CaseForAnim1();
		}
		sCase.init(act);
		return sCase;
	}

	private void init(Activity act){
		this.mAct=act;
	}

	public void work(){
		work1();
	}

	private void work1(){
		mAct.setContentView(R.layout.case_anim1);
		mBtn1=(Button) mAct.findViewById(R.id.btn1);
		mBtn2=(Button) mAct.findViewById(R.id.btn2);
		mBtn2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//刚才试了试，在动作运行期间，是可以点击的，所以如果想没bug这里肯定要作一些代码
				if(mBtn1.getVisibility()==View.GONE){
					show();
				}else{
					hide();
				}
			}
		});
	}

private void hide(){

		/**
		 * 让我履一下思路：
		 * 有两种方法是么？一是动画完了，再弄真实动作。二是先弄真实动作，再进行动画是么？
		 * 第一种思路：
		 *    1、先动画，让mBtn1从alpha从1到0,再让mBtn1 从 TranslationY从0到-mbtn1.getHeight()，同时mBtn2从TranslationY到-mBtn1.getHeight()
		 *    2、动画进行之后，mBtn1 gone掉，但是为了下次动画，要把mBtn1的 translationY设为0,
		 *    3、在mBtn1 gone掉后，mBtn2的 translationY,如果还是-mBtn1.getHeight，那他就显示不出来了（在屏幕上方），
		 *      所以这时就也把mBtn2的TranslationY也设置为0
		 *
		 * 目前这种思路可行
		 *
		 * 第二种思路：
		 *   1、先让mBtn1 gone掉，gone 掉之后，mBtn2 的translationY 设定为 mBtn1.getHeight， 移到0
		 *   如果这样的话 mBtn1 就不会有 透明度的变化了，起码在hide的这个动作，这个不可行
		 */

		if(mAnimhide!=null){
			mAnimhide.end();
		}
		AnimatorSet anim=new AnimatorSet();
		AnimatorSet.Builder b = anim.play(android.animation.ObjectAnimator.ofFloat(mBtn1, "alpha", 0));
		b.with(ObjectAnimator.ofFloat(mBtn1, "translationY", -mBtn1.getHeight()));
		b.with(ObjectAnimator.ofFloat(mBtn2, "translationY", 0,-mBtn1.getHeight()));
		anim.addListener(new AnimatorListener() {

			@Override
			public void onAnimationStart(Animator animation) {

			}

			@Override
			public void onAnimationRepeat(Animator animation) {

			}

			@Override
			public void onAnimationEnd(Animator animation) {
				mBtn1.setVisibility(View.GONE);
				mBtn1.setTranslationX(0);
				mBtn2.setTranslationY(0);

			}

			@Override
			public void onAnimationCancel(Animator animation) {

			}
		});
		anim.start();
	}

	private void show(){
		if(mAnimhide!=null){
			mAnimhide.end();
		}
		mBtn1.setVisibility(View.VISIBLE);
		/**
		 * 在mBtn1 Visible后的一瞬间 mBtn1的translationY 为0时,mBtn1已经是第一行的那个位置了
		 * mBtn2的translationY 为0时，mBtn2已经是在第二行的那个位置了 可以想像 此时的View 的各种标记为：
		 * mBtn1与mBtn2的Alpha为1,mBtn1与mBtn2的translationY都为0
		 *
		 * 这时再想运行这个效果的动画，需要作的是： mBtn1的alpha为0,以便从不透明到透明
		 * 设置mBtn1与mBtn2的translationY 开始都为 -mBtn1.getHeight，以便
		 * 过一会变成translation为0时，就是正常的了
		 */

		mBtn1.setAlpha(0);
		mBtn1.setTranslationY(-mBtn1.getHeight());

		AnimatorSet anim=new AnimatorSet();
		AnimatorSet.Builder b = anim.play(android.animation.ObjectAnimator.ofFloat(mBtn1, "alpha", 1));
		b.with(ObjectAnimator.ofFloat(mBtn1, "translationY",0));
		b.with(ObjectAnimator.ofFloat(mBtn2, "translationY", -mBtn1.getHeight(),0));
		anim.addListener(new AnimatorListener() {

			@Override
			public void onAnimationStart(Animator animation) {

			}

			@Override
			public void onAnimationRepeat(Animator animation) {

			}

			@Override
			public void onAnimationEnd(Animator animation) {
				//下边两个其实不设定也没什么关系，动画最终的属性与动画后的view的属性， 是一致匹配的
//				mBtn2.setTranslationY(0);
//				mBtn1.setTranslationX(0);
			}

			@Override
			public void onAnimationCancel(Animator animation) {

			}
		});
		anim.start();
	}
}
