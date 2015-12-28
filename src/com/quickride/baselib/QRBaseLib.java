/***********************************************************************
* @author 			:		LAKSHMI BS
* @description		: 		Generic library with reusable methods that can be used only for QuickRide app.
* @method			:		setobjects()
* @method			:		setUp()
* @method			:		tearDown()
* @method 			:		
* @method 
*/
package com.quickride.baselib;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class QRBaseLib 
{
	public AndroidDriver driver;
	public static int iLongSleep=10000;
	public static int iMediumSleep=6000;
	public static int iSmallSleep =3000;
	static public String sDirPath=System.getProperty("user.dir");
	static public String sConfigFile=sDirPath+"\\Capabilities.Properties";
	static public String sAPKFile=sDirPath+"\\QuickRide.apk";
	public DesiredCapabilities capabilities=null;
	GenericLib genericLib;
	@BeforeTest
	public void setobjects()
	{
		genericLib = new GenericLib();
	}
	@BeforeMethod
	public void setUp() throws MalformedURLException{
		File app = new File(sAPKFile);
		capabilities = new DesiredCapabilities();
		capabilities.setCapability("automationName",genericLib.getCongigValue(sConfigFile, "AUTOMATIONNAME") );
		capabilities.setCapability("platformName",genericLib.getCongigValue(sConfigFile, "PLATFORMNAME") );
		capabilities.setCapability("platformVersion",genericLib.getCongigValue(sConfigFile, "PLATFORMVERSION"));//"4.4");
		capabilities.setCapability("deviceName",genericLib.getCongigValue(sConfigFile, "DEVICENAME") );//"ASUS_T00J");
		capabilities.setCapability("app",app.getAbsolutePath());
		capabilities.setCapability("apppackage", genericLib.getCongigValue(sConfigFile, "APPPACKAGE") );//"com.disha.quickride"); // This is package name of your app (you can get it from apk info app)
		capabilities.setCapability("appactivity", genericLib.getCongigValue(sConfigFile, "APPACTIVITY") );//"com.disha.quickride.androidapp.startup.SplashActivity"); // This is Launcher activity of your app (you can get it from apk info app)
	    capabilities.setCapability("unicodeKeyboard", true);
		//capabilities.setCapability("resetKeyboard", true);
		driver=new AndroidDriver(new URL(genericLib.getCongigValue(sConfigFile, "URL")), capabilities);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}

}
