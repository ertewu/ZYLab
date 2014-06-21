package com.example.demo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CaseInvoke.invokeCase(this);
        Flower flow = new Flower();
        Log.i("ertewu", "r14 :" + System.currentTimeMillis());
        flow = null;
        // System.gc();
        Log.i("ertewu", "r16 :" + System.currentTimeMillis());
    }

    public static class Flower {
        int petalCount = 0;
        String s = "initial value";

        Flower(int petals) {
            petalCount = petals;
            System.out.print("Constructor w/ int arg only,petalCount= " + petalCount);
        }

        Flower(String ss) {
            s = ss;
        }

        Flower(String s, int petals) {
            this(petals);
            // this(s);
            System.out.print("String & int args");
        }

        Flower() {
            this("hi", 47);
            System.out.print("default constructor(no args)");
        }

        @Override
        protected void finalize() throws Throwable {
            Log.i("ertewu", "flower finalize :" + System.currentTimeMillis());
            super.finalize();
        }
    }

}
