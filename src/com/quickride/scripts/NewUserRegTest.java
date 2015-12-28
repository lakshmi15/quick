/***********************************************************************
* @author 			:		LAKSHMI BS
* @description		: 		Test scripts of New User Registration module.
* @module			:		New User Registration
* @testmethod		:		testNewUserSignUP()
* @testmethod		:		testSignUpIncomplete()
* @testmethod		:		testSignUpErrCode()
* @testmethod 		:		testSignUpSameUser()
* @testmethod 		:		testLogoutReLogin()
* @testmethod		:		testLoginWrongPwd()
* @testmethod 		:		testCloseReOpenQR()		
* @testmethod 		:		testNewUserSignUPProfile()		
* @testmethod 		:		testLogoutNoVerifyQuickRide		
*/

package com.quickride.scripts;

import java.sql.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.quickride.PO.MyProfilePO;
import com.quickride.PO.NewUserRegPO;
import com.quickride.PO.QRProfilePO;
import com.quickride.baselib.GenericLib;
import com.quickride.baselib.QRBaseLib;
import com.quickride.baselib.QRTestngListener;

import io.appium.java_client.TouchAction;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
public class NewUserRegTest extends QRBaseLib
{
	public String sData[]=null;
	public String sTestCaseID=null;
	NewUserRegPO newUserRegPo;
	QRProfilePO qrProfilePo;
	MyProfilePO myProfilePo;
	public Logger qrLog = Logger.getLogger(this.getClass());
	
	/*
	 * @description: Test New User registration  Skip to Login
	 * @author: LAKSHMI BS
	 * 
	 */	
	@Test(priority=1,enabled=true)
	public void testNewUserSignUP()
	{
		sTestCaseID="NewUserRG_01";
		sData= GenericLib.toReadExcelData(sTestCaseID);
		newUserRegPo = new NewUserRegPO(driver);
		String sVerificationCode=null;
		
		try
		{
			newUserRegPo.signUP(sData[1], sData[2], sData[3]);
			while(true)
			{
				sVerificationCode=GenericLib.getDBdata(GenericLib.getCongigValue(QRBaseLib.sConfigFile, "VERIFICATION"), "verifycode", "subject", sData[1]);
				System.out.println(sVerificationCode);
				if(!(sVerificationCode.isEmpty()))
				{
					break;
				}
			}
			newUserRegPo.getEleActivationTxtFld().sendKeys(sVerificationCode);			
			newUserRegPo.getEleActivateBtn().click();
			Assert.assertTrue(newUserRegPo.getEleAccActivatedTxt().isDisplayed(), "Account activated popup is not displayed");
			qrLog.info("Account Activated popup is successfully displayed");
			newUserRegPo.getEleSkipBtn().click();
			Assert.assertTrue(newUserRegPo.getEleCurrentLocTxt().isDisplayed(), "New Ride page is not displayed");
			qrLog.info("New Ride page is succesfully displayed");
			
		} catch (Exception e) {
			qrLog.error("Exception in  case testSignUpIncomplete");
			e.printStackTrace();
			Assert.fail();
		}
		
	}
	
	/*
	 * @description: Test New user registration, where Activation not completed
	 * @author: LAKSHMI BS
	 * 
	 */
	@Test(priority=2,enabled=true)
	public void testSignUpIncomplete()
	{
		sTestCaseID="NewUserRG_02";
		sData= GenericLib.toReadExcelData(sTestCaseID);
		newUserRegPo = new NewUserRegPO(driver);
	try
		{
			newUserRegPo.signUP(sData[1], sData[2], sData[3]);
			driver.navigate().back();
			if(newUserRegPo.getEleLoginBtn().isDisplayed())
			{
				qrLog.info("Login button is displayed");
				newUserRegPo.getEleLoginBtn().click();
				newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[1]);
				newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[2]);
				newUserRegPo.getEleLoginBtn().click();
				Assert.assertTrue(newUserRegPo.getEleActivateBtn().isDisplayed(), "Account to be activated page is not displayed");
				qrLog.info("Account to activated page is displayed");
			}
		} catch (Exception e) {
			qrLog.error("Exception in  case testSignUpIncomplete");
			e.printStackTrace();
			Assert.fail();
		}
	}
	/* @description: Test New user registration, where Activation Code error
	 * @author: LAKSHMI BS
	 * 
	 */
	@Test(priority=3,enabled=true)			
	public void testSignUpErrCode()
	{
	sTestCaseID="NewUserRG_03";	
	sData= GenericLib.toReadExcelData(sTestCaseID);
	String sErrorTxt="xx12";
	newUserRegPo = new NewUserRegPO(driver);
	try
	{
		newUserRegPo.signUP(sData[1], sData[2], sData[3]);
		newUserRegPo.getEleActivationTxtFld().sendKeys(sData[4]);
		newUserRegPo.getEleActivateBtn().click();
		Assert.assertTrue(newUserRegPo.getEleActivateBtn().isDisplayed(), "Activation page is not displayed to enter valid code");  
		qrLog.info("Activation code is successfully displayed to re-enter code");
	} catch (Exception e) {
		qrLog.error("Exception in  case testSignUpErrCode");
		e.printStackTrace();
		Assert.fail();
	}
}

	/*
	 * @description: Test New user registration, where user already exists 
	 * @author: LAKSHMI BS
	 * 
	 */
	@Test(priority=4,enabled=true)
	public void testSignUpSameUser()
	{
		sTestCaseID="NewUserRG_04";
		sData= GenericLib.toReadExcelData(sTestCaseID);
		newUserRegPo = new NewUserRegPO(driver);
		try
		{
			if(newUserRegPo.getEleSignUpLaunchBtn().isDisplayed())
			{
				qrLog.info("SignUp button is displayed");
				newUserRegPo.getEleSignUpLaunchBtn().click();
				newUserRegPo.getElePhoneNOTxtFld().sendKeys(sData[1]);
				newUserRegPo.getElePwdTxtFld().sendKeys(sData[2]);
				newUserRegPo.getEleNameTxtFld().sendKeys(sData[3]);
				driver.hideKeyboard();
				newUserRegPo.getEleFemaleRdBtn().click();
				newUserRegPo.getEleSignUpRegBtn().click();
			}
			Assert.assertTrue(newUserRegPo.getEleAcntExistsTxt().isDisplayed(), "Account already exists alert message is not displayed");
			qrLog.info("Account already exists popup is displayed");
			newUserRegPo.getElePopupLoginBtn().click();
			driver.hideKeyboard();
			newUserRegPo.getEleForgotPwdLnk().click();
			Assert.assertTrue(newUserRegPo.getEleResetPwdBtn().isDisplayed(), "Password reset page is not displayed");
			qrLog.info("Forgot password reset page is successfully displayed");
	
		} catch (Exception e) {
			qrLog.error("Exception in  case testSignUpSameUser");
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	/*
	 * @description: Test User logout and Relogin with correct credentials
	 * @author: LAKSHMI BS
	 * 
	 */
	@Test(priority=5,enabled=true)
	public void testLogoutReLogin()
	{
		sTestCaseID="NewUserRG_05";
		sData= GenericLib.toReadExcelData(sTestCaseID);
		newUserRegPo = new NewUserRegPO(driver);
		qrProfilePo= new QRProfilePO(driver);
		try
		{	
			newUserRegPo.login(sData[1], sData[2]);
			Assert.assertTrue(newUserRegPo.getEleCurrentLocTxt().isDisplayed(), "Login is not Successful");
			qrLog.info("Login is completed");
			
			qrProfilePo.logout();
			newUserRegPo.login(sData[1], sData[2]);
			Assert.assertTrue(newUserRegPo.getEleCurrentLocTxt().isDisplayed(), "Login is not Successful");
			qrLog.info("Login is completed");
			
		}
		 catch (Exception e) {
			qrLog.error("Exception in  case testLogoutReLogin");
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	/*
	 * @description: Test User login with wrong password
	 * @author: LAKSHMI BS
	 * 
	 */
	@Test(priority=6,enabled=true)
	public void testLoginWrongPwd()
	{
		sTestCaseID="NewUserRG_06";
		sData= GenericLib.toReadExcelData(sTestCaseID);
		newUserRegPo = new NewUserRegPO(driver);
		try
		{	
			if(newUserRegPo.getEleLoginBtn().isDisplayed())
			{
				qrLog.info("Login button is displayed");
				newUserRegPo.getEleLoginBtn().click();
				newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[1]);
				newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[2]);
				newUserRegPo.getEleLoginBtn().click();
				Assert.assertTrue(newUserRegPo.getEleInvalidAccTxt().isDisplayed(), "Login is Successful");   
				qrLog.info("Login Failed successfully");
			}
		}
		 catch (Exception e) {
			qrLog.error("Exception in  case testLoginWrongPwd");
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	/*
	 * @description: Test User login first time, and credentials remembered. Close the app, reopen it should be directly navigated to the Ride creation activity after login
	 * @author: LAKSHMI BS
	 * 
	 */
	@Test(priority=7,enabled=true)
	public void testCloseReOpenQR()
	{
		sTestCaseID="NewUserRG_07";
		sData= GenericLib.toReadExcelData(sTestCaseID);
		newUserRegPo = new NewUserRegPO(driver);
		try
		{	
			newUserRegPo.login(sData[1], sData[2]);
			driver.closeApp();
			qrLog.info("Quick Ride app is closed");
			driver.launchApp();
			newUserRegPo.getEleLoginBtn().click();
			Assert.assertTrue(newUserRegPo.getEleLoginBtn().isDisplayed(), "App did not launch again");
			qrLog.info("Quick Ride app is launched successfully again");
		}
		 catch (Exception e) {
			qrLog.error("Exception in  case testCloseReOpenQR");
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	/*
	 * @description: Test New User registration  Select Profile
	 * @author: LAKSHMI BS
	 * 
	 */	
	@Test(priority=8,enabled=true)
	public void testNewUserSignUPProfile()
	{
		sTestCaseID="NewUserRG_08";
		sData= GenericLib.toReadExcelData(sTestCaseID);
		newUserRegPo = new NewUserRegPO(driver);
		myProfilePo = new MyProfilePO(driver);
		String sVerificationCode=null;
		try
		{
			newUserRegPo.signUP(sData[1], sData[2], sData[3]);
			while(true)
			{
				sVerificationCode=GenericLib.getDBdata(GenericLib.getCongigValue(QRBaseLib.sConfigFile, "VERIFICATION"), "verifycode", "subject", sData[1]);
				System.out.println(sVerificationCode);
				if(!(sVerificationCode.isEmpty()))
				{
					break;
				}
			}
			newUserRegPo.getEleActivationTxtFld().sendKeys(sVerificationCode);			
			newUserRegPo.getEleActivateBtn().click();
			Assert.assertTrue(newUserRegPo.getEleAccActivatedTxt().isDisplayed(), "Account activated popup is not displayed");
			qrLog.info("Account Activated popup is successfully displayed");
			newUserRegPo.getEleProfileBtn().click();
			Assert.assertTrue(myProfilePo.getEleEditProfileTxt().isDisplayed(), "Edit Profile page is not displayed");
			qrLog.info("Edit Profile page is succesfully displayed");
		} catch (Exception e) {
			qrLog.error("Exception in  case testNewUserSignUPProfile");
			e.printStackTrace();
			Assert.fail();
		}
	}
	

	/*
	 * @description: Test User try to logout but click no in popup
	 * @author: LAKSHMI BS
	 * 
	 */
	@Test(priority=9,enabled=true)
	public void testLogoutNoVerifyQuickRide()
	{
		sTestCaseID="NewUserRG_05";
		sData= GenericLib.toReadExcelData(sTestCaseID);
		newUserRegPo = new NewUserRegPO(driver);
		qrProfilePo= new QRProfilePO(driver);
		try
		{	
			newUserRegPo.signUPorLogin(sData[1], sData[2],sData[3]);
			qrProfilePo.getEleMenuLst().click();
			qrProfilePo.getEleLogoutBtn().click();
			qrProfilePo.getEleNoBtn().click();
			Assert.assertTrue(qrProfilePo.getEleQuickRideTxt().isDisplayed(), "Logout successful but expected not to logout");
			qrLog.info("Logout did not succeed and Quick Ride page is retained");
		
		}
		 catch (Exception e) {
			qrLog.error("Exception in  case testLogoutReLogin");
			e.printStackTrace();
			Assert.fail();
		}
	}
	

}