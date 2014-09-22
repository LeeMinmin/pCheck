package com.pCheck.activity;

import java.util.ArrayList;
import java.util.List;

import com.example.pcheck.R;
import com.pCheck.util.AppInfo;
import com.pCheck.util.ViewHolder;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
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
	private int tempPositon = -1;

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
		appListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
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
		List<View> itemViews;
		LayoutInflater mInflater;
		public ListAdapter(Context context){
			//获取手机内非系统预装的应用程序列表
			this.mInflater = LayoutInflater.from(context);
			PackageManager pManager = context.getPackageManager();
			List<PackageInfo> paklist = pManager.getInstalledPackages(0);
			int len = paklist.size();
			//appInfo = new ArrayList();
			for (int i=0; i<len; i++){
				PackageInfo pak = (PackageInfo) paklist.get(i);
				if ((pak.applicationInfo.flags & pak.applicationInfo.FLAG_SYSTEM) <= 0){
					AppInfo app = new AppInfo();
					app.setAppText(pManager.getApplicationLabel(pak.applicationInfo).toString());
					app.setAppImg(pManager.getApplicationIcon(pak.applicationInfo));
					app.setAppPackage(pak.applicationInfo.packageName);
					appInfo.add(app);
				}
			}
		}
		/*
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
		*/
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			//Log.d("itemViews length", itemViews.size()+"");		
			return appInfo.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			//return itemViews.get(position);
			return appInfo.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			//return position;
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHolder holder;
			if(convertView == null){
				convertView = mInflater.inflate(R.layout.list_app, null);
				holder = new ViewHolder();
				holder.app_text = (TextView) convertView.findViewById(R.id.appText);
				holder.app_img = (ImageView) convertView.findViewById(R.id.appImage);
				holder.app_radio = (RadioButton) convertView.findViewById(R.id.rbutton);
				convertView.setTag(holder);
			}else{
				holder = (ViewHolder)convertView.getTag();
			}			
			holder.app_text.setText(appInfo.get(position).getAppText());
			holder.app_img.setImageDrawable(appInfo.get(position).getAppImg());
			holder.app_radio.setOnCheckedChangeListener(new OnCheckedChangeListener(){
				@Override
				public void onCheckedChanged(CompoundButton buttonView,
						boolean isChecked) {
					// TODO Auto-generated method stub
					isRadioChecked = true;
					tempPositon = position;
				}
			});
			return convertView;
		}
	}
}
