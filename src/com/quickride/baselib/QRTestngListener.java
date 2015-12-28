/***********************************************************************
* @author 			:		LAKSHMI BS
* @description		: 		Implemented ITestListener interface and overrided methods as per requirement. It listenes to all the events performed by Testng and keep track of it.
* @method			:		onTestStart()
* @method			:		onTestSuccess()
* @method			:		onTestFailure()
* @method 			:		onTestSkipped()
* @method			:		onTestFailedButWithinSuccessPercentage()		
* @method 			:		onStart()
* @method 			:		onFinish()
* @method 
*/



package com.quickride.baselib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.SimpleLayout;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.sun.jna.platform.win32.WinNT.GENERIC_MAPPING;



public class QRTestngListener implements ITestListener 
{
	public Logger qrLog;
	public static int iPassCount=0;
	public static int iFailCount=0;
	public static int iSkippedCount=0;
	public static ArrayList sTestName= new ArrayList<String>();
	public static ArrayList sStatus= new ArrayList<String>();
	public static String sSheet1="Results";
	public static String sSheet2="Test Status";
	int cnt=0;
	static int i=0;
	HashMap hStatus = new HashMap();
	

	public  QRTestngListener() 
	{
	
		Date date = new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy_hh_mm_ss");
		String sdate1 = sdf.format(date);
		System.setProperty("sFileName",sdate1);
		qrLog= Logger.getLogger(this.getClass());
		
	try{
		
		GenericLib.sFile=GenericLib.sFile+sdate1+".xlsx";
		OutputStream fos = new FileOutputStream(GenericLib.sFile);
		HSSFWorkbook hwb = new HSSFWorkbook();
		HSSFSheet sheet = hwb.createSheet(sSheet1);
		hwb.createSheet(sSheet2);
		FileOutputStream fileOut = new FileOutputStream(GenericLib.sFile);
		hwb.write(fos);
		fileOut.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	@Override
	public void onTestStart(ITestResult result) 
	{
		qrLog.info("_____________	   RUNNING TESTCASE 	___________");
		qrLog.info("TESTCASE ID  =  	"+result.getName().toString()+"                    ");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) 
	{
		GenericLib.setStatus(result.getName().toString(), "Passed",sTestName,sStatus);
		qrLog.info("TEST STATUS  =   	PASSED"+"                    ");
		qrLog.info("___________________________________________________");
		
	}

	@Override
	public void onTestFailure(ITestResult result) 
	{
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy_hh_mm_ss");
		String sdate = sdf.format(date);
		String sImage=result.getName().toString().replace("test","");
		Object obj=result.getInstance();
		QRBaseLib baseLib = (QRBaseLib)obj;
		qrLog.error("TEST STATUS  =   	FAILED"+"                    ");
		qrLog.info("___________________________________________________");
		
		File imgFile = ((TakesScreenshot) baseLib.driver).getScreenshotAs(OutputType.FILE);
		System.out.println(imgFile.getAbsolutePath());
            try {
        	   
			FileUtils.copyFile(imgFile, new File(QRBaseLib.sDirPath+"\\Reports\\ScreenShots\\"+sImage+"__"+sdate+".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       
            GenericLib.setStatus(result.getName().toString(), "Failed",sTestName,sStatus);
          
	}

	@Override
	public void onTestSkipped(ITestResult result) 
	{
		// TODO Auto-generated method stub
		qrLog.error("TEST STATUS  =   	SKIPPED"+"                    ");
		qrLog.info("___________________________________________________");
		
		GenericLib.setStatus(result.getName(), "Skipped",sTestName,sStatus);
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) 
	{
		// TODO Auto-generated method stub
		qrLog.warn("");
		
	}

	@Override
	public void onStart(ITestContext context) 
	{
		// TODO Auto-generated method stub
		qrLog.info("######################     START OF THE TEST    ######################");
	}

	@Override
	public void onFinish(ITestContext context) 
	{
	
		GenericLib.toWriteIntoExcel(sTestName,sStatus);
		qrLog.info("                                                                    ");
		qrLog.info("######################     END OF THE TEST    ######################");
		
	}
	

			
	
}
