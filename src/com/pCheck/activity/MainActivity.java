package com.pCheck.activity;

import java.util.ArrayList;
import java.util.List;

import com.example.pcheck.R;
import com.pCheck.util.AppInfo;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
	private List<PackageInfo> pinfo = new ArrayList<PackageInfo>();
	private List<AppInfo> appInfo = new ArrayList();
	private ListView appListView;
	private Button startTest;
	private boolean isRadioChecked = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		appListView = (ListView)findViewById(R.id.ListView01);
		startTest = (Button)findViewById(R.id.test);
		startTest.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				
			}
		});
		appListView.setAdapter(new ListAdapter(this));
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
	
	
	private class ListAdapter extends BaseAdapter{
		View[] itemViews;		
		public ListAdapter(Context context){
			//获取手机内非系统预装的应用程序列表
			PackageManager pManager = context.getPackageManager();
			List<PackageInfo> paklist = pManager.getInstalledPackages(0);
			int len = paklist.size();
			for (int i=0; i<len; i++){
				PackageInfo pak = (PackageInfo) paklist.get(i);
				if ((pak.applicationInfo.flags & pak.applicationInfo.FLAG_SYSTEM) <= 0){
					String text = pManager.getApplicationLabel(pak.applicationInfo).toString();
					String pname = pak.applicationInfo.packageName;
					Drawable img = pManager.getApplicationIcon(pak.applicationInfo);
					itemViews[i] = makeItemView(img, text);
				}
			}
		}
	
		private View makeItemView(Drawable resImg, String text){
			LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
			View item = inflater.inflate(R.layout.list_app, null);
			RadioButton rb = (RadioButton)item.findViewById(R.id.rbutton);
			TextView appText = (TextView)item.findViewById(R.id.appText);
			appText.setText(text);
			ImageView appImg = (ImageView)item.findViewById(R.id.appImage);
			appImg.setImageDrawable(resImg);
			rb.setOnCheckedChangeListener(new OnCheckedChangeListener(){
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
					if(isChecked){
						isRadioChecked = true;
					}
				}
			});
			return item;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return itemViews.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return itemViews[position];
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			if(convertView == null)
				return itemViews[position];
			return convertView;
		}
	}
}
