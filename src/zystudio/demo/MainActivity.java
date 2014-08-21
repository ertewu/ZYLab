package zystudio.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

    private ListView mListView;
    private MyListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qq_navigation);
        mListView = (ListView) findViewById(R.id.navigation);
        mAdapter = new MyListAdapter();
        mListView.setAdapter(mAdapter);
    }

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
            NavigationItemView view = new NavigationItemView(MainActivity.this);
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
