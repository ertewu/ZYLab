package com.example.demo.cases;

import java.util.Observable;
import java.util.Observer;

import android.util.Log;

public class CaseObserver {

    private static CaseObserver sSingleton;

    public static CaseObserver getInstance(){
        if(sSingleton==null){
            sSingleton=new CaseObserver();
        }
        return sSingleton;
    }

    private CaseObserver(){

    };

    public void work(){
        MyObservable observable=new MyObservable();
        MyObserver observer1=new MyObserver();
        MyObserver observer2=new MyObserver();
        observable.addObserver(observer1);
        observable.addObserver(observer2);
        observable.processChange();
    }

    public static class MyObservable extends Observable{
        int i=0;
        public void processChange(){
            for(int k=0;k<2;k++){
                Log.i("ertewu", "MyObservable:"+this.hashCode());
                setChanged();
                notifyObservers(i);
                i++;
            }
        }
    }

    public static class MyObserver implements Observer{

        @Override
        public void update(Observable observable, Object data) {
            Log.i("ertewu", this.hashCode()+":update Observable:"+observable.hashCode()+"  |data:"+data );
        }
    }
}
