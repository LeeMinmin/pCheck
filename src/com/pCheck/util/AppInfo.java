package com.pCheck.util;

import android.graphics.drawable.Drawable;

public class AppInfo {
	private String appText;
	private String appPackage;
	private Drawable appImg;
	private int pid;
	private int uid;
	
	public AppInfo(){
		
	}
	
	public AppInfo(String appText, String appPackage, Drawable appImg, int pid, int uid){
		this.appImg = appImg;
		this.appPackage = appPackage;
		this.appText = appText;
		this.pid = pid;
		this.uid = uid;
	}

	public String getAppText() {
		return appText;
	}

	public void setAppText(String appText) {
		this.appText = appText;
	}

	public String getAppPackage() {
		return appPackage;
	}

	public void setAppPackage(String appPackage) {
		this.appPackage = appPackage;
	}

	public Drawable getAppImg() {
		return appImg;
	}

	public void setAppImg(Drawable appImg) {
		this.appImg = appImg;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}
	
}
