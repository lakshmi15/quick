/***********************************************************************
* @author 			:		LAKSHMI BS
* @description		: 		Generic library with reusable methods that can be used across porjects.
* @method			:		getCongigValue()
* @method			:		toReadExcelData()
* @method			:		toWriteIntoExcel()
* @method 			:		setStatus()
* @method 
*/

package com.quickride.baselib;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class GenericLib 
{
	public static String sTestDataFile=QRBaseLib.sDirPath+"\\QR_TestData.xlsx";
	public static String sFile=QRBaseLib.sDirPath+"\\Reports\\Results\\TestResult_"; 
	public static int iPassCount=0;
	public static int iFailCount=0;
	public static int iSkippedCount=0;
	public static String sSheet1="Results";
	public static String sSheet2="Test Status";
	public static String sConfigFile=QRBaseLib.sConfigFile;
	
	/*@author: LAKSHMi BS
	 *Description: To read the basic environment settings data from config file
	 */
	public static String getCongigValue(String sFile, String sKey)
	{
		Properties prop = new Properties();
		String sValue=null;
		try {
			InputStream input = new FileInputStream(sFile);
			prop.load(input);
			sValue=prop.getProperty(sKey);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sValue;
	}
	/*
	 * @author: LAKSHMI BS
	 * Description: To read test data from excel sheet
	 */
	public static String[] toReadExcelData(String sTestCaseID)
	{
		String sData[]=null;
		try
		{
	
		FileInputStream fis = new FileInputStream(sTestDataFile);
		Workbook wb = (Workbook) WorkbookFactory.create(fis);
		Sheet sht = wb.getSheet("QR_TestData");
		int iRowNum= sht.getLastRowNum();
		for(int i=1;i<=iRowNum;i++)
		{
			if(sht.getRow(i).getCell(0).toString().equals(sTestCaseID))
			{
				int iCellNum=sht.getRow(i).getLastCellNum();
				sData= new String[iCellNum];
				for(int j=0;j<iCellNum;j++)		
				{
					sData[j]=sht.getRow(i).getCell(j).getStringCellValue();
				}
				break;
			}
		}	
		}
		catch(Exception e)
		{	
			e.printStackTrace();
		}
		return sData;
		}

	/*
	 * @author: LAKSHMI BS
	 * Description: To write test results into excel sheet
	 */
	public  static void toWriteIntoExcel(ArrayList sTestName, ArrayList sStatus )
	{
		try
		{
		
		FileInputStream fis = new FileInputStream(sFile);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sht = wb.getSheet("Test Status");
		sht.createRow(0);
		sht.getRow(0).createCell(0).setCellValue("TestName");
		sht.getRow(0).createCell(1).setCellValue("Status");
		
		int j=1;
		for(int i=0;i<sTestName.size();i++)
		{
			sht.createRow(j);
			sht.getRow(j).createCell(0).setCellValue((String)sTestName.get(i));
			sht.getRow(j).createCell(1).setCellValue((String)sStatus.get(i));
			j++;
		}
		
		Sheet sht2 = wb.getSheet("Results");
		sht2.createRow(0).createCell(0).setCellValue("Result Summary");
		
		sht2.createRow(1).createCell(0).setCellValue("Passed");
		sht2.getRow(1).createCell(1).setCellValue(iPassCount);
		
		sht2.createRow(2).createCell(0).setCellValue("Failed");
		sht2.getRow(2).createCell(1).setCellValue(iFailCount);
		
		sht2.createRow(3).createCell(0).setCellValue("Skipped");
		sht2.getRow(3).createCell(1).setCellValue(iSkippedCount);
		
		sht2.createRow(5).createCell(0).setCellValue("Total Executed");
		sht2.getRow(5).createCell(1).setCellValue(iPassCount+iFailCount+iSkippedCount);
			
		FileOutputStream fos = new FileOutputStream(sFile);
		wb.write(fos);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/*
	 * @author: LAKSHMI BS
	 * Description: To set status of every testcases with its execution results
	 */
	public  static void setStatus(String sName, String sResult,ArrayList sTestName, ArrayList sStatus)
	{
		sName=sName.replace("test","");
		sTestName.add(sName);
		sStatus.add(sResult);
		
		if(sResult.equals("Passed"))
		{iPassCount=iPassCount+1;
		}else if(sResult.equals("Failed"))
		{iFailCount = iFailCount+1;
		}else
		{iSkippedCount = iSkippedCount+1;
		}
	}
	
	/*
	 * @author: LAKSHMI BS
	 * @Description: To get DBdata giving key and its value
	 * @param: tablename, data for which we need to fetch value, unique key, key value
	 */
	public static String getDBdata(String sTable, String sData, String sKey,String sValue) throws SQLException
	{	 String sResult=null;
		 String jdbcUrl = "jdbc:mysql://" + GenericLib.getCongigValue(sConfigFile, "HOSTNAME") + ":" + 3306 + "/" + GenericLib.getCongigValue(sConfigFile, "DBNAME") + "?user=" + GenericLib.getCongigValue(sConfigFile, "DBUSN")  + "&password=" + GenericLib.getCongigValue(sConfigFile, "DBPWD") ;
		 Connection con =null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con =     DriverManager.getConnection(jdbcUrl);
			String sql="Select "+sData+" from "+sTable+" WHERE "+sKey+"="+sValue;
			ResultSet r = con.createStatement().executeQuery(sql);
			int columncnt=r.getMetaData().getColumnCount();
			while( r.next())
			{
			   for(int j=1;j<=columncnt;j++)
			  {   sResult=r.getString(j);
			  }
		  }
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			  con.close();
			 }
		return sResult;
	}
	
	/*
	 * @author: LAKSHMI BS
	 * @Description: To set DBdata giving key and its value
	 * @param: tablename, data for which we need to set value, datas value, unique key, key value
	 */
	
	public static void setDBdata(String sTable, String sData, String sRes,String sKey,String sValue) throws SQLException
	{

		 String jdbcUrl = "jdbc:mysql://" + GenericLib.getCongigValue(sConfigFile, "HOSTNAME") + ":" + 3306 + "/" + GenericLib.getCongigValue(sConfigFile, "DBNAME") + "?user=" + GenericLib.getCongigValue(sConfigFile, "DBUSN")  + "&password=" + GenericLib.getCongigValue(sConfigFile, "DBPWD") ;
		 Connection con =null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con =     DriverManager.getConnection(jdbcUrl);
			String sql="UPDATE "+sTable+" SET "+sData+"='"+sRes+"' WHERE "+sKey+"='"+sValue+"'";
			System.out.println(sql);
			con.createStatement().executeUpdate(sql);
		} catch (InstantiationException e) {
				e.printStackTrace();
		} catch (IllegalAccessException e) {
				e.printStackTrace();
		} catch (ClassNotFoundException e) {
				e.printStackTrace();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		finally {
			  con.close();
			}
		}
	
	public static void main(String[] args) throws SQLException 
	{
		GenericLib.setDBdata(GenericLib.getCongigValue(sConfigFile, "ACCOUNT"), "balance", "20", "id","9886174868");
		System.out.println("Done");
		
		
	}
}