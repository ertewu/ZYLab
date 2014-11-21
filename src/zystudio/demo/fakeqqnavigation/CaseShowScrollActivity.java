package zystudio.demo.fakeqqnavigation;

import zystudio.demo.R;
import zystudio.mylib.utils.LogUtil;
import android.app.Activity;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class CaseShowScrollActivity extends Activity {

    private ListView mListView;
    private MyListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        showQQNavigationLayout();
        // showViewConfigurationParams();
        // showOverScrollView();
        // showMyLinearLayout();
        // showMyScrollView();
    }

    private void showViewConfigurationParams() {
        final ViewConfiguration configuration = ViewConfiguration.get(this);
        int mTouchSlop = configuration.getScaledTouchSlop();
        int mMinimumVelocity = configuration.getScaledMinimumFlingVelocity();
        int mMaximumVelocity = configuration.getScaledMaximumFlingVelocity();
        int mOverscrollDistance = configuration.getScaledOverscrollDistance();
        int mOverflingDistance = configuration.getScaledOverflingDistance();
        // 这两项是OverScroller中的splineOverScroller中，进行减速度时间计算(getSplineFlingDuration)
        // 的常数，我需要了解一下
        float friction = ViewConfiguration.getScrollFriction(); // 0.015
        float mPhysicalCoeff = SensorManager.GRAVITY_EARTH;// g (m/s^2)
                                                           // //9.80665

        String str = mTouchSlop + "|" + mMinimumVelocity + "|"
                + mMaximumVelocity + "|" + mOverscrollDistance + "|"
                + mOverflingDistance + "|" + friction + "|" + mPhysicalCoeff;
        LogUtil.log(str);
    }

    private void showQQNavigationLayout() {
        setContentView(R.layout.qqnavigation_layout);
        QQNavigationLayout layout = (QQNavigationLayout) findViewById(R.id.qq_navigation);
        NavigationItemView v1 = (NavigationItemView) findViewById(R.id.itemview_1);
        v1.setTitle("Title 0");
        v1.setImageViewHeight(100);
        v1.setImageViewColor(android.graphics.Color.YELLOW);

        NavigationItemView v2 = (NavigationItemView) findViewById(R.id.itemview_2);
        v2.setTitle("Title 1");
        v2.setImageViewHeight(150);
        v2.setImageViewColor(android.graphics.Color.BLUE);

        NavigationItemView v3 = (NavigationItemView) findViewById(R.id.itemview_3);
        v3.setTitle("Title 2");
        v3.setImageViewHeight(1000);
        v3.setImageViewColor(android.graphics.Color.GREEN);

        // 刚才在这里发现v4中的ImageView有1000的高，但是v4只有690的全屏高，说明NavigationItemView没做好..
        NavigationItemView v4 = (NavigationItemView) findViewById(R.id.itemview_4);
        v4.setTitle("Title 3");
        v4.setImageViewHeight(900);
        v4.setImageViewColor(android.graphics.Color.RED);
    }

    private void showOverScrollView() {
        setContentView(R.layout.overscrollview);
        NavigationItemView v3 = (NavigationItemView) findViewById(R.id.itemview);
        v3.setTitle("NavigationItemView");
        v3.setImageViewHeight(1000);
        v3.setImageViewColor(android.graphics.Color.GREEN);
    }

    private void showMyScrollView() {
        setContentView(R.layout.myscrollview);
        NavigationItemView v3 = (NavigationItemView) findViewById(R.id.itemview);
        v3.setTitle("NavigationItemView");
        v3.setImageViewHeight(1000);
        v3.setImageViewColor(android.graphics.Color.GREEN);
    }


    private void showMyLinearLayout() {
        setContentView(R.layout.mylinearlayout);
        NavigationItemView v1 = (NavigationItemView) findViewById(R.id.itemview_1);
        v1.setTitle("Title 0");
        v1.setImageViewHeight(100);
        v1.setImageViewColor(android.graphics.Color.YELLOW);

        NavigationItemView v2 = (NavigationItemView) findViewById(R.id.itemview_2);
        v2.setTitle("Title 1");
        v2.setImageViewHeight(150);
        v2.setImageViewColor(android.graphics.Color.BLUE);

        NavigationItemView v3 = (NavigationItemView) findViewById(R.id.itemview_3);
        v3.setTitle("Title 2");
        v3.setImageViewHeight(1000);
        v3.setImageViewColor(android.graphics.Color.GREEN);
    }

    // private void showListView() {
    // setContentView(R.layout.qq_navigation);
    // mListView = (ListView) findViewById(R.id.navigation);
    // mAdapter = new MyListAdapter();
    // mListView.setAdapter(mAdapter);
    // }

    private class MyListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            NavigationItemView view = new NavigationItemView(CaseShowScrollActivity.this);
            switch (position) {
            case 0:
                view.setTitle("Title 0");
                view.setImageViewHeight(100);
                view.setImageViewColor(android.graphics.Color.YELLOW);
                break;

            case 1:
                view.setTitle("Title 1");
                view.setImageViewHeight(150);
                view.setImageViewColor(android.graphics.Color.BLUE);
                break;

            case 2:
                view.setTitle("Title 2");
                view.setImageViewHeight(1000);
                view.setImageViewColor(android.graphics.Color.GREEN);
                break;

            case 3:
                view.setTitle("Title 3");
                view.setImageViewHeight(1300);
                view.setImageViewColor(android.graphics.Color.RED);
                break;

            }
            return view;
        }
    }
}
