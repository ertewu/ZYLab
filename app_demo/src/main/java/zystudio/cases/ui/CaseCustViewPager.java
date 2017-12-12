package zystudio.cases.ui;


import zystudio.demo.R;
import zystudio.demo.views.customviewpager.CustomPagerAdapter;
import zystudio.demo.views.customviewpager.CustomViewPager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CaseCustViewPager {
    private static CaseCustViewPager sCase;

    public static CaseCustViewPager getInstance() {
        if (sCase == null) {
            sCase = new CaseCustViewPager();
        }
        return sCase;
    }

    private CaseCustViewPager() {

    }

    private Handler mHandler = new Handler();
    private CustomViewPager mPager;
    private MyAdapter mAdapter;
    private Activity mActivity;

    public void showCase(Activity activity) {

        ViewGroup contentView = (ViewGroup) LayoutInflater.from(activity).inflate(R.layout.case_custviewpager, null);
        mAdapter= new MyAdapter(activity);
        mPager = (CustomViewPager) contentView.findViewById(R.id.cust_vpager);
        mActivity = activity;
        Button btn = (Button) contentView.findViewById(R.id.call_viewpager);
        activity.setContentView(contentView);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // mPager.setAdapter(mAdapter);
                startSemobActivatePingbackService(mActivity, "wzychannel");
            }
        });
    }

    public void startSemobActivatePingbackService(Context context, String currentChannel) {
        try {
            Log.i("ZYStudio", "demo client currentChannel = " + currentChannel);
            Intent intent = new Intent();
            intent.setClassName("sogou.mobile.explorer", "sogou.mobile.explorer.ActivateBrowserService");
            if (!TextUtils.isEmpty(currentChannel)) {
                intent.putExtra("hotwords_active_semob_params_channel_name_key", currentChannel);
            }
            context.startService(intent);
        } catch (Exception e) {
        }
    }

    private static class MyAdapter extends CustomPagerAdapter {

        private Context mCtx;

        public MyAdapter(Context context) {
            mCtx = context;
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "标题" + position;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // TODO Auto-generated method stub
            // super.destroyItem(container, position, object);
            Log.i("ZYStudio", "destoryItem occured, position is:" + position);
            ((CustomViewPager) container).removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TextView textView = new TextView(mCtx);
            textView.setText("内容" + position);
            // 用代码创建一个layout
            LinearLayout layout = new LinearLayout(mCtx);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            layout.setGravity(Gravity.CENTER);
            layout.setLayoutParams(params);
            layout.addView(textView);
            container.addView(layout);
            return layout;
        }

    }
}
