package zystudio.demo;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         CaseInvoke.invokeCase(this);

//         Collection<Integer> sets= new HashSet<Integer>(5);
//         Log.i("ertewu", "r20 size is:"+sets.size());
//
//         sets.add(111);
//         sets.add(222);
//         sets.add(333);
//         Log.i("ertewu", "r25 size is:"+sets.size());
//
//         sets.clear();
//         Log.i("ertewu", "r28 size is:"+sets.size());
//
//         HashMap<String,String>map=new HashMap<String,String>();
//         map.put("nihao","p1");
//         map.put("nihao","p2");
//
//         String value=map.get("nihao");
//         LogUtil.log("value is:"+value);

        // setContentView(R.layout.activity_main);

        // int compare="3.3.0".compareTo("3.2.0");
        // int compare2="3.2.0".compareTo("3.2.0");
        // int compare3="3.1.5".compareTo("3.2.0");
        // int compare4="3.2".compareTo("3.2.0");
        // Log.i("ertewu",
        // "compares:"+compare+"|"+compare2+"|"+compare3+"|"+compare4);
    }



}
