package zystudio.demo.fakeqqnavigation;

import zystudio.demo.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NavigationItemView extends LinearLayout {

    private TextView mTitleView;
    private ImageView mImageView;

    public NavigationItemView(Context context) {
        super(context, null);
        init();
    }

    public NavigationItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setOrientation(LinearLayout.VERTICAL);
        inflate(getContext(), R.layout.qq_navigation_item, this);
        mTitleView = (TextView) findViewById(R.id.titleview);
        mImageView = (ImageView) findViewById(R.id.colorview);
        mTitleView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mImageView.getVisibility() == View.VISIBLE) {
                    mImageView.setVisibility(View.GONE);
                } else {
                    mImageView.setVisibility(View.VISIBLE);
                    if (getParent() instanceof QQNavigationLayout) {
                        getHandler().postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                QQNavigationLayout parent = (QQNavigationLayout) getParent();
                                try {
                                    parent.scrollChildToTop(NavigationItemView.this);
                                } catch (Exception o) {

                                }
                            }
                        }, 200);
                    }
                }
            }
        });
    }

    public void setTitle(String text) {
        mTitleView.setText(text);
    }

    public void setImageViewHeight(int height) {
        mImageView.setLayoutParams(new LayoutParams(480, height));
    }

    public void setImageViewColor(int color) {
        mImageView.setBackgroundColor(color);
    }

    public void setImageViewVisiable(int i) {
        mImageView.setVisibility(i);
    }
}
