package zystudio.utils;

import zystudio.demo.MainActivity;
import android.widget.Toast;

public class Util {

    public static void showMsg(String str){
        Toast.makeText(MainActivity.getActivity(),str,Toast.LENGTH_SHORT).show();
    }

}
