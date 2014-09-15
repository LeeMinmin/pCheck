package com.example.pcheck;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity {
	private List<PackageInfo> pinfo = new ArrayList<PackageInfo>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	//获取手机内非系统预装的应用程序列表
	private void setApplicationList(Context context){
		PackageManager pManager = context.getPackageManager();
		List<PackageInfo> paklist = pManager.getInstalledPackages(0);
		int len = paklist.size();
		for (int i=0; i<len; i++){
			PackageInfo pak = (PackageInfo) paklist.get(i);
			if ((pak.applicationInfo.flags & pak.applicationInfo.FLAG_SYSTEM) <= 0){
				pinfo.add(pak);
			}
		}
	}
}
