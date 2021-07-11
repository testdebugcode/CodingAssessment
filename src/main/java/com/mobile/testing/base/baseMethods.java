package com.mobile.testing.base;

import java.io.IOException;
import java.net.MalformedURLException;

import io.appium.java_client.android.AndroidDriver;

public interface baseMethods {
	public AndroidDriver launchApp(String url) throws IOException;
	public void clickByID(String ID);
	public void clickByXpath(String xpathh);
	public void enterById(String id, String enterValue);
	public void enterByXpath(String xpath, String enterValue);
	public String getTextOfElement(String xpathOfGetValue);
	public void waitAndenterByXpath(String xpathhh,String xpathdata );
	String getOrientation();
	public boolean setPortraitOrientation();
	public boolean setLanscapeOrientation();
	public void takeSnap();
	public void captureLogcat();
}
