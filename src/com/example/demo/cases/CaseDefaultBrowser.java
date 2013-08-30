package com.example.demo.cases;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

public class CaseDefaultBrowser {
	private static CaseDefaultBrowser sDefaultBrowserCase;
	private Runnable mRunnable;
	
	public static CaseDefaultBrowser getInstance(){
		if(sDefaultBrowserCase==null){
			sDefaultBrowserCase=new CaseDefaultBrowser(); 
		}
		return sDefaultBrowserCase;
	}
	
	private CaseDefaultBrowser(){
		
	}
	
	public void work(Activity activity){
		//设为默认浏览器
		Intent intent=new Intent();
		intent.setAction(Intent.ACTION_VIEW);
		intent.addCategory(Intent.CATEGORY_BROWSABLE);
		intent.setComponent(new ComponentName("android", "com.android.internal.app.ResolverActivity"));
		final String sogouUrl="http://mse.sogou.com/?pc=1";
		intent.setData(Uri.parse(sogouUrl));
		activity.startActivity(intent);
	}
	
	public void work1(Activity activity){
		//这个现在能初步找到 名字,我现在需要找到package name
//		Intent i = (new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com")));
		Intent intent=new Intent();
		intent.setAction(Intent.ACTION_VIEW);
		intent.addCategory(Intent.CATEGORY_BROWSABLE);
		final String sogouUrl="http://mse.sogou.com/?pc=1";
		intent.setData(Uri.parse(sogouUrl));
		
		PackageManager pm = activity.getPackageManager();
		final ResolveInfo mInfo = pm.resolveActivity(intent, 0);
		Log.i("ertewu", "r49:"+mInfo.resolvePackageName);
		//这个应该可以
		Log.i("ertewu", "r50:"+mInfo.activityInfo.packageName);
		Log.i("ertewu", "r51:"+mInfo.activityInfo.applicationInfo);
		
//		Toast.makeText(activity, pm.getApplicationLabel(mInfo.activityInfo.applicationInfo), Toast.LENGTH_LONG).show();
		//ResolveInfo  是什么东西？
	}
	
	public void work2(Activity activity){
//		Intent intent=new Intent();
//		intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
//		ComponentName component=new ComponentName("com.android.settings",".applications.InstalledAppDetails");
//		intent.setComponent(component);
//		intent.setPackage("sogou.mobile.explorer");
//		activity.startActivity(intent);
		//搞定！
		activity.startActivity(new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:sogou.mobile.explorer")));
	}
}
