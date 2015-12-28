/***********************************************************************
* @author 			:		Raghukiran MR
* @description		: 		Test scripts of User configures module.
* @module			:		User Configures
* @testmethod		:	   	testUsersetting()
* @testmethod		:	   	testForgetPassword()
* @testmethod		:		testChangePassword()
* @testmethod		:		testUserupdating()
* @testmethod 		:		testUserProfileSocialLinks()
* @testmethod 		:		testUserDifferentDevices()
* 
*/



package com.quickride.scripts;

import java.net.MalformedURLException;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.quickride.PO.MyProfilePO;
import com.quickride.PO.NewUserRegPO;
import com.quickride.PO.QRProfilePO;
import com.quickride.baselib.GenericLib;
import com.quickride.baselib.QRBaseLib;

public class UserConfigTest extends QRBaseLib
{
	public String sData[]=null;
	public String sTestCaseID=null;
	//NotificationsPO notificationsPo;
	NewUserRegPO newUserRegPo;
	MyProfilePO myProfilePo;
	QRBaseLib qrBaseLib;
	QRProfilePO qrProfilePo;

	public Logger qrLog = Logger.getLogger(this.getClass());
	
	/*@Test id:Usercong_01
	 * @description:Test User setting the profile details without Vehicle on new Account Activation with Camera 
	 * @author: Raghukiran MR
	 * 
	 */	
	@Test(priority=1,enabled=true)
	public void testUsersetting(){
		sTestCaseID="Usercong_01";
		myProfilePo=new MyProfilePO(driver);
		newUserRegPo=new NewUserRegPO(driver);
		sData= GenericLib.toReadExcelData(sTestCaseID);
		System.out.println(Arrays.toString(sData));
		
		try
		{
				newUserRegPo.signUPorLogin(sData[1],sData[2],sData[3]);
				myProfilePo.userConfig(sData[4],sData[5]);
				qrLog.info("ProfileDetails is updated successfully");
		}
		catch(Exception e){
			qrLog.error("Exception in  case testUsersetting");
			e.printStackTrace();
			Assert.fail();
		}
	}

	/*@Test id:Usercong_02
	 * @description: Test Forget Password 
	 * @author:Raghukiran MR
	 * 
	 */
	@Test(priority=2,enabled=true)
	public void testForgetPassword(){
		sTestCaseID="Usercong_02";
		myProfilePo = new MyProfilePO(driver);
		newUserRegPo = new NewUserRegPO(driver);
		sData= GenericLib.toReadExcelData(sTestCaseID);
		try
		{
			myProfilePo.forgotPassword(sData[1],newUserRegPo);
			qrLog.info("Password reset is successful");
		}
		catch(Exception e){
			qrLog.error("Exception in  case testForgetPassword");
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	/*@Test id:Usercong_03
	 * @description: Test Change Password
	 * @author:Raghukiran MR
	 * 
	 */
	@Test(priority=3,enabled=true)
	public void testChangePassword(){
		sTestCaseID="Usercong_03";
		newUserRegPo=new NewUserRegPO(driver);
		myProfilePo = new MyProfilePO(driver);
		sData= GenericLib.toReadExcelData(sTestCaseID);
		try
		{
			newUserRegPo.signUPorLogin(sData[1], sData[2],sData[3]);
			myProfilePo.changePassword(sData[6],sData[7],sData[8],newUserRegPo);
			qrLog.info("changePassword is successfull");
		}
		catch(Exception e){
			qrLog.error("Exception in  case testChangePassword");
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	/*@Test id:Usercong_04
	 * @description:Test User updating  the profile details with Vehicle 
	 * @author:Raghukiran MR
	 * 
	 */
	@Test(priority=4,enabled=true)
	public void testUserupdating(){
		sTestCaseID="Usercong_04";
		myProfilePo=new MyProfilePO(driver);
		newUserRegPo = new NewUserRegPO(driver);
		sData= GenericLib.toReadExcelData(sTestCaseID);
		try
		{
			newUserRegPo.signUPorLogin(sData[1], sData[2],sData[3]);
			myProfilePo.vehicleDetails(sData[4],sData[5],sData[6]);
			qrLog.info("VehicleDetails is update successfully");
		}
		catch(Exception e){
			qrLog.error("Exception in  case testUserupdating");
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	/*@Test id:Usercong_05
	 * @description:Test User verifying display of Social links in profile page 
	 * @author:Lakshmi BS
	 * 
	 */
	@Test(priority=5,enabled=true)
	public void testUserProfileSocialLinks(){
		sTestCaseID="Usercong_05";
		sData= GenericLib.toReadExcelData(sTestCaseID);
		myProfilePo=new MyProfilePO(driver);
		newUserRegPo = new NewUserRegPO(driver);
		myProfilePo = new MyProfilePO(driver);
		qrProfilePo = new QRProfilePO(driver);
		
		try
		{
			newUserRegPo.signUPorLogin(sData[1], sData[2],sData[3]);
			qrProfilePo.getEleMenuLst().click();
			myProfilePo.getEleArrowBtn().click();
			myProfilePo.getEleEditLnk().click();
			driver.hideKeyboard();
			driver.scrollTo("Ride Preferences");
			Assert.assertTrue(myProfilePo.getEleProfileFBIcn().isDisplayed(), "FaceBook icon is not displayed in profile page");
			qrLog.info("FaceBook icon is successfully displayed in profile page");
			
			//Need to implement to navigate to FB but Facebook link is not working here.
			
			Assert.assertTrue(myProfilePo.getEleProfileLinkedInIcn().isDisplayed(), "LinkedIn icon is not displayed in profile page");
			qrLog.info("LinkedIn icon is successfully displayed in profile page");
			
			myProfilePo.getEleProfileLinkedInIcn().click();
			Assert.assertTrue(myProfilePo.getEleSocialWebPge().isDisplayed(), "LinkedIn page is not displayed.");
			qrLog.info("LinkedIn page is displayed.");
			driver.navigate().back();
			
			Assert.assertTrue(myProfilePo.getEleProfileTwitterIcn().isDisplayed(), "Twitter icon is not displayed in profile page");
			qrLog.info("Twitter icon is successfully displayed in profile page");
			
			myProfilePo.getEleProfileTwitterIcn().click();
			Assert.assertTrue(myProfilePo.getEleSocialWebPge().isDisplayed(), "Twitter page is not displayed.");
			qrLog.info("Twitter page is displayed.");
			driver.navigate().back();
			
			myProfilePo.getEleArrowIcn().click();
			myProfilePo.getEleArrowIcn().click();
			qrProfilePo.logout();
			
		}
		catch(Exception e){
			qrLog.error("Exception in  case testUserProfileSocialLinks");
			e.printStackTrace();
			Assert.fail();
			
		}
	}
	
	/*@Test id:Usercong_06
	 * @description:Test User login through different device, when he loose/misplace/change his mobile
	 * @author:Raghukiran MR
	 * 
	 */
	@Test(priority=6,enabled=true)
	public void testUserDifferentDevices(){
		sTestCaseID="Usercong_04";
		myProfilePo=new MyProfilePO(driver);
		newUserRegPo = new NewUserRegPO(driver);
		sData= GenericLib.toReadExcelData(sTestCaseID);
		try
		{
			newUserRegPo.signUPorLogin(sData[1], sData[2],sData[3]);
			myProfilePo.getEleMenuBar().click();
			myProfilePo.getEleArrowBtn().click();
			Assert.assertTrue(myProfilePo.getEleEditLnk().isDisplayed(), "Profile details is not displayed");
			qrLog.info("Profile details is displayed");
		}catch(Exception e){
			qrLog.error("Exception in  case testUserDifferentDevices");
			
			e.printStackTrace();
			Assert.fail();
		}
	}

}


