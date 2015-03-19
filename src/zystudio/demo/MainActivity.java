package zystudio.demo;

import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;

public class MainActivity extends Activity {

    private static Activity sActivity;

    public static Activity getActivity(){
        return sActivity;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sActivity=this;
        // CaseInvoke.invokeCase(this);
        CaseEvenChecker.test();

        SparseArray<String> mArray=new SparseArray<String>();
        mArray.put(0x11, "HaHa");
        String mStr=mArray.get(-1);
        Log.i("ZYStudio", "mStr is:"+mStr);
        mStr=mArray.get(0x11);
        Log.i("ZYStudio", "mStr is:"+mStr);

        HashMap<Integer,String> mHash=new HashMap<Integer,String>();
        mHash.put(0x11, "HaHa");
        mStr=mHash.get(-1);
        Log.i("ZYStudio", "mStr r28 is:"+mStr);
        mStr=mHash.get(0x11);
        Log.i("ZYStudio", "mStr r30 is:"+mStr);
        // long aYear=60*60*24*365*1000;
        // LogUtil.log("aYear is:"+aYear);

        String toCutStr="abcde";
        String cutStr=toCutStr.substring(0, 3);
        Log.i("ZYStudio","cut str is:"+cutStr);
        CaseInvoke.invokeCase(this);
        // String text =
        // getResources().getString((R.string.novel_offline_progress), 23);
        // LogUtil.log(text);

        // Collection<Integer> sets= new HashSet<Integer>(5);
        // Log.i("ertewu", "r20 size is:"+sets.size());
        //
        // sets.add(111);
        // sets.add(222);
        // sets.add(333);
        // Log.i("ertewu", "r25 size is:"+sets.size());
        //
        // sets.clear();
        // Log.i("ertewu", "r28 size is:"+sets.size());
        //
        // HashMap<String,String>map=new HashMap<String,String>();
        // map.put("nihao","p1");
        // map.put("nihao","p2");
        //
        // String value=map.get("nihao");
        // LogUtil.log("value is:"+value);

//         setContentView(R.layout.activity_main);
//         TextView myTv=(TextView)findViewById(R.id.my_txtview);
//         myTv.setText(Html.fromHtml("你好啊&quot;"));
//         myTv.setText(Html.fromHtml("<h2>Title</h2><br><p>Description here</p>"));
        // Spannable word = new SpannableString("Your message\n");
        // word.setSpan(new ForegroundColorSpan(Color.BLUE), 0, word.length(),
        // Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        // myTv.setText(word);
        // Spannable wordTwo = new SpannableString("Your new message");
        // wordTwo.setSpan(new ForegroundColorSpan(Color.RED), 0,
        // wordTwo.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        // myTv.append(wordTwo);

        // int compare="3.3.0".compareTo("3.2.0");
        // int compare2="3.2.0".compareTo("3.2.0");
        // int compare3="3.1.5".compareTo("3.2.0");
        // int compare4="3.2".compareTo("3.2.0");
        // Log.i("ertewu",
        // "compares:"+compare+"|"+compare2+"|"+compare3+"|"+compare4);
    }



    public static class MyBean implements Cloneable {
        private String Name;
        private int Index;

        public MyBean(String name, int index) {
            Name = name;
            Index = index;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public int getIndex() {
            return Index;
        }

        public void setIndex(int index) {
            Index = index;
        }

        @Override
        public String toString() {
            return Name + "|" + Index;
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }
}
