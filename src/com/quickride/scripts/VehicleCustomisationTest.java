/***********************************************************************
* @author 			:		Raghukiran MR
* @description		: 		Test scripts of Ride specific Vehicle Customization
* @module			:		Ride specific Vehicle Customization
* @testmethod		:	   	testDefaultVehicleAdded()
* @testmethod		:		testFare()
* @testmethod		:		testSupportBike()
*/

package com.quickride.scripts;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.quickride.PO.FeedbackPO;
import com.quickride.PO.MyProfilePO;
import com.quickride.PO.NewUserRegPO;
import com.quickride.PO.QRProfilePO;
import com.quickride.PO.RidesPO;
import com.quickride.baselib.GenericLib;
import com.quickride.baselib.QRBaseLib;

public class VehicleCustomisationTest extends QRBaseLib {
	public String sData[]=null;
	public String sTestCaseID=null;
	public Logger qrLog = Logger.getLogger(this.getClass());
	NewUserRegPO newUserRegPo;
	QRProfilePO qrprofile;
	MyProfilePO myProfilePo;
	FeedbackPO feedbackPo;
	RidesPO ridesPo;
	
	
	/*@Test id:RideVehicle_01
	 * @description:Ride specific Vehicle Customization
	 * @author:Raghukiran MR
	 * 
	 */
	@Test(priority=1,enabled=true)
	public void testDefaultVehicleAdded(){
		sTestCaseID="RideVehicle_01";
		newUserRegPo = new NewUserRegPO(driver);
		qrprofile=new QRProfilePO(driver);
		myProfilePo =new MyProfilePO(driver);
		feedbackPo=new FeedbackPO(driver);
		ridesPo = new RidesPO(driver);
		sData= GenericLib.toReadExcelData(sTestCaseID);
	try{
		//UserA create the ride and set the profile 
		newUserRegPo.signUPorLogin(sData[1],sData[2],sData[8]);
		myProfilePo.getEleMenuBar().click();
		Assert.assertTrue(myProfilePo.getEleArrowBtn().isDisplayed(), "Profile icon is not displayed");
		qrLog.info("Profile icon is displayed");
		myProfilePo.getEleArrowBtn().click();
		myProfilePo.getEleEditLnk().click();
		driver.scrollTo("Ride Preferences");
		myProfilePo.vehicledetails(sData[3],sData[6],sData[7]);
		myProfilePo.getEleArrowIcn().click();
		myProfilePo.getEleSearchBtn().click();
		feedbackPo.createRide(sData[4],sData[5], ridesPo);
		qrprofile.logout();
		//UserB join the ride
		newUserRegPo.signUPorLogin(sData[9],sData[10],sData[11]);
		feedbackPo.getEleSearchBtn().click();
		feedbackPo.getEleEnterAddTxtFld().sendKeys(sData[4]);
		feedbackPo.getEleFirstOption().click();
		feedbackPo.getEleRideNwBtn().click();
		feedbackPo.getEleEndAddressTxtFld().click();
		feedbackPo.getEleEnterAddTxtFld().sendKeys(sData[5]);
		feedbackPo.getEleFirstOption().click();
		feedbackPo.getEleFindRideTab().click();
		driver.findElement(By.name(sData[8])).click();
		feedbackPo.getEleJoinBtn().click();
		Assert.assertTrue(feedbackPo.getEleArrowIcn().isDisplayed(), "Join button is not successfully");
		qrLog.info("Join button is  successfully");
		feedbackPo.getEleArrowIcn().click();
		qrprofile.logout();
		//UserA accept the ride
		newUserRegPo.getEleLoginBtn().click();
		newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[1]);
		newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[2]);
		newUserRegPo.getEleLoginBtn().click();
		feedbackPo.getEleArrowIcn().click();
		feedbackPo.getEleNotificationIcon().click();
		feedbackPo.getEleAcceptLnk().click();
		feedbackPo.getEleStartBtn().click();
		feedbackPo.getEleYesBtn().click();
		feedbackPo.getEleArrowIcn().click();
		qrprofile.logout();
		//UserB check in 
		newUserRegPo.getEleLoginBtn().click();
		newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[9]);
		newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[10]);
		newUserRegPo.getEleLoginBtn().click();
		feedbackPo.getEleCheckInBtn().click();
		feedbackPo.getEleYesBtn().click();
		Assert.assertTrue(feedbackPo.getEleCheckOutBtn().isDisplayed(), "Check In button is not successfully");
		qrLog.info("Check In button is successfully");
		feedbackPo.getEleCheckOutBtn().click();
		feedbackPo.getEleYesBtn().click();
		Assert.assertTrue(feedbackPo.getEleNextBtn().isDisplayed(), "Price amount is not dispalyed");
		qrLog.info("Price amount is  dispalyed");
		feedbackPo.getEleNextBtn().click();
		feedbackPo.getEleSubmitBtn().click();
		qrprofile.logout();
		//Clean up the code 
		newUserRegPo.getEleLoginBtn().click();
		newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[1]);
		newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[2]);
		newUserRegPo.getEleLoginBtn().click();
		ridesPo.getEleStopBtn().click();
		ridesPo.getEleYesBtn().click();
		feedbackPo.getEleNextBtn().click();
		feedbackPo.getEleSubmitBtn().click();
		qrprofile.logout();
		newUserRegPo.login(sData[1],sData[2]);
		myProfilePo.getEleMenuBar().click();
		myProfilePo.getEleArrowBtn().click();
		myProfilePo.getEleEditLnk().click();
		driver.scrollTo("Ride Preferences");
		Assert.assertTrue(myProfilePo.getEleVehicleNoTxtFld().isDisplayed(), "Default Vehicle is not added");
		qrLog.info("Default Vehicle is added successfully");
		}
	catch(Exception e){
		qrLog.error("Exception in  case testDefaultVehicleAdded()");
		e.printStackTrace();
		Assert.fail();
	}
}

	/*@Test id:RideVehicle_02
	 * @description:Verify 	If user set fare of 0 only for that ride, zero fare is applicable.
	 * @author:Raghukiran MR
	 * 
	 */
	@Test(priority=2,enabled=true)
	public void testFare(){
		sTestCaseID="RideVehicle_02";
		newUserRegPo = new NewUserRegPO(driver);
		qrprofile=new QRProfilePO(driver);
		myProfilePo =new MyProfilePO(driver);
		feedbackPo=new FeedbackPO(driver);
		sData= GenericLib.toReadExcelData(sTestCaseID);
	try{
		//UserA create the ride
		newUserRegPo.signUPorLogin(sData[1],sData[2],sData[8]);
		myProfilePo.getEleMenuBar().click();
		Assert.assertTrue(myProfilePo.getEleArrowBtn().isDisplayed(), "Profile icon is not displayed");
		qrLog.info("Profile icon is displayed");
		myProfilePo.getEleArrowBtn().click();
		myProfilePo.getEleEditLnk().click();
		driver.scrollTo("Ride Preferences");
		myProfilePo.vehicledetails(sData[3],sData[6],sData[7]);
		myProfilePo.getEleArrowIcn().click();
		feedbackPo.createRide(sData[4],sData[5], ridesPo);
		qrprofile.logout();
		
		//UserB join the ride
		newUserRegPo.signUPorLogin(sData[9],sData[10],sData[11]);
		feedbackPo.getEleSearchBtn().click();
		feedbackPo.getEleEnterAddTxtFld().sendKeys(sData[4]);
		feedbackPo.getEleFirstOption().click();
		feedbackPo.getEleRideNwBtn().click();
		feedbackPo.getEleEndAddressTxtFld().click();
		feedbackPo.getEleEnterAddTxtFld().sendKeys(sData[5]);
		feedbackPo.getEleFirstOption().click();
		feedbackPo.getEleFindRideTab().click();
		driver.findElement(By.name(sData[8])).click();
		feedbackPo.getEleJoinBtn().click();
		Assert.assertTrue(feedbackPo.getEleArrowIcn().isDisplayed(), "Join button is not successfully");
		qrLog.info("Join button is  successfully");
		feedbackPo.getEleArrowIcn().click();
		qrprofile.logout();
		
		//UserA accept the ride of UserB
		newUserRegPo.getEleLoginBtn().click();
		newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[1]);
		newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[2]);
		newUserRegPo.getEleLoginBtn().click();
		feedbackPo.getEleArrowIcn().click();
		feedbackPo.getEleNotificationIcon().click();
		feedbackPo.getEleAcceptLnk().click();
		feedbackPo.getEleStartBtn().click();
		feedbackPo.getEleYesBtn().click();
		feedbackPo.getEleArrowIcn().click();
		qrprofile.logout();
		
		//UserC join the ride
		newUserRegPo.signUPorLogin(sData[12],sData[13],sData[14]);
		feedbackPo.getEleSearchBtn().click();
		feedbackPo.getEleEnterAddTxtFld().sendKeys(sData[4]);
		feedbackPo.getEleFirstOption().click();
		feedbackPo.getEleRideNwBtn().click();
		feedbackPo.getEleEndAddressTxtFld().click();
		feedbackPo.getEleEnterAddTxtFld().sendKeys(sData[5]);
		feedbackPo.getEleFirstOption().click();
		feedbackPo.getEleFindRideTab().click();
		driver.findElement(By.name(sData[8])).click();
		feedbackPo.getEleJoinBtn().click();
		Assert.assertTrue(feedbackPo.getEleArrowIcn().isDisplayed(), "Join button is not successfully");
		qrLog.info("Join button is  successfully");
		feedbackPo.getEleArrowIcn().click();
		qrprofile.logout();
		
		//UserB accept the ride of UserC
		newUserRegPo.getEleLoginBtn().click();
		newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[1]);
		newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[2]);
		newUserRegPo.getEleLoginBtn().click();
		feedbackPo.getEleArrowIcn().click();
		feedbackPo.getEleNotificationIcon().click();
		feedbackPo.getEleAcceptLnk().click();
		feedbackPo.getEleStartBtn().click();
		feedbackPo.getEleYesBtn().click();
		feedbackPo.getEleArrowIcn().click();
		qrprofile.logout();
		//UserB Check in
		newUserRegPo.getEleLoginBtn().click();
		newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[9]);
		newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[10]);
		newUserRegPo.getEleLoginBtn().click();
		feedbackPo.getEleCheckInBtn().click();
		feedbackPo.getEleYesBtn().click();
		Assert.assertTrue(feedbackPo.getEleCheckOutBtn().isDisplayed(), "Check In button is not successfully");
		qrLog.info("Check In button is successfully");
		feedbackPo.getEleArrowIcn().click();
		qrprofile.logout();
		//UserC check in
		newUserRegPo.getEleLoginBtn().click();
		newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[12]);
		newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[13]);
		newUserRegPo.getEleLoginBtn().click();
		feedbackPo.getEleCheckInBtn().click();
		feedbackPo.getEleYesBtn().click();
		Assert.assertTrue(feedbackPo.getEleCheckOutBtn().isDisplayed(), "Check In button is not successfully");
		qrLog.info("Check In button is successfully");
		feedbackPo.getEleArrowIcn().click();
		qrprofile.logout();
		//Clear up the code
		newUserRegPo.getEleLoginBtn().click();
		newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[9]);
		newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[10]);
		newUserRegPo.getEleLoginBtn().click();
		feedbackPo.getEleCheckOutBtn().click();
		feedbackPo.getEleYesBtn().click();
		Assert.assertTrue(feedbackPo.getEleNextBtn().isDisplayed(), "Price amount is not dispalyed");
		qrLog.info("Price amount is  dispalyed");
		feedbackPo.getEleNextBtn().click();
		feedbackPo.getEleSubmitBtn().click();
		qrprofile.logout();
		//Clear up the code
		newUserRegPo.getEleLoginBtn().click();
		newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[12]);
		newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[13]);
		newUserRegPo.getEleLoginBtn().click();
		feedbackPo.getEleCheckOutBtn().click();
		feedbackPo.getEleYesBtn().click();
		Assert.assertTrue(feedbackPo.getEleNextBtn().isDisplayed(), "Price amount is not dispalyed");
		qrLog.info("Price amount is  dispalyed");
		feedbackPo.getEleNextBtn().click();
		feedbackPo.getEleSubmitBtn().click();
		qrprofile.logout();
	}catch(Exception e){
		qrLog.error("Exception in  case testDefaultVehicleAdded() ");
		e.printStackTrace();
		Assert.fail();
	}
	}
	/*@Test id:SupportBike_01
	 * @description:Support Bike for ride sharing
	 * @author:Raghukiran MR
	 * 
	 */
	@Test(priority=3,enabled=true)
	public void testSupportBike(){
		sTestCaseID="SupportBike_01";
		newUserRegPo = new NewUserRegPO(driver);
		qrprofile=new QRProfilePO(driver);
		myProfilePo =new MyProfilePO(driver);
		feedbackPo=new FeedbackPO(driver);
		sData= GenericLib.toReadExcelData(sTestCaseID);
		try
		{
			//UserA create the ride
			newUserRegPo.signUPorLogin(sData[1], sData[2], sData[8]);
			myProfilePo.getEleMenuBar().click();
			Assert.assertTrue(myProfilePo.getEleArrowBtn().isDisplayed(), "Profile icon is not displayed");
			qrLog.info("Profile icon is displayed");
			myProfilePo.getEleArrowBtn().click();
			myProfilePo.getEleEditLnk().click();
			driver.scrollTo("Ride Preferences");
			myProfilePo.bikeSupport(sData[3]);
			myProfilePo.getEleArrowIcn().click();
			feedbackPo.getEleSearchBtn().click();
			feedbackPo.getEleEnterAddTxtFld().sendKeys(sData[4]);
			feedbackPo.getEleFirstOption().click();
			feedbackPo.getEleRideNwBtn().click();
			feedbackPo.getEleEndAddressTxtFld().click();
			feedbackPo.getEleEnterAddTxtFld().sendKeys(sData[5]);
			feedbackPo.getEleFirstOption().click();
			feedbackPo.getEleOfferRideTab().click();
			feedbackPo.getEleOfferRideBtn().click();
			Assert.assertTrue(ridesPo.getEleBikeImageicon().isDisplayed(), "Bike image icon is not displayed");
			qrLog.info("Bike image icon is  displayed");
			feedbackPo.getEleArrowIcn().click();
			qrprofile.logout();
			//UserB join the ride 
			newUserRegPo.signUPorLogin(sData[6], sData[7], sData[9]);
			feedbackPo.getEleSearchBtn().click();
			feedbackPo.getEleEnterAddTxtFld().sendKeys(sData[4]);
			feedbackPo.getEleFirstOption().click();
			feedbackPo.getEleRideNwBtn().click();
			feedbackPo.getEleEndAddressTxtFld().click();
			feedbackPo.getEleEnterAddTxtFld().sendKeys(sData[5]);
			feedbackPo.getEleFirstOption().click();
			feedbackPo.getEleFindRideTab().click();
			driver.findElement(By.name(sData[8])).click();
			feedbackPo.getEleJoinBtn().click();
			Assert.assertTrue(feedbackPo.getEleArrowIcn().isDisplayed(), "Join button is not successfully");
			qrLog.info("Join button is  successfully");
			feedbackPo.getEleArrowIcn().click();
			qrprofile.logout();
			//UserA accept the ride
			newUserRegPo.getEleLoginBtn().click();
			newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[1]);
			newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[2]);
			newUserRegPo.getEleLoginBtn().click();
			feedbackPo.getEleArrowIcn().click();
			feedbackPo.getEleNotificationIcon().click();
			feedbackPo.getEleAcceptLnk().click();
			feedbackPo.getEleStartBtn().click();
			feedbackPo.getEleYesBtn().click();
			feedbackPo.getEleArrowIcn().click();
			qrprofile.logout();
			//UserB Check In
			newUserRegPo.getEleLoginBtn().click();
			newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[6]);
			newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[7]);
			newUserRegPo.getEleLoginBtn().click();
			Assert.assertTrue(feedbackPo.getEleCheckInBtn().isDisplayed(), "Bike image is not dispalyed in Map");
			qrLog.info("Bike image should be dispalyed in Map");
			feedbackPo.getEleCheckInBtn().click();
			feedbackPo.getEleYesBtn().click();
			feedbackPo.getEleNextBtn().click();
			feedbackPo.getEleSubmitBtn().click();
			qrprofile.logout();
			//UserA Stop the ride
			newUserRegPo.getEleLoginBtn().click();
			newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[1]);
			newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[2]);
			newUserRegPo.getEleLoginBtn().click();
			Assert.assertTrue(feedbackPo.getEleStopBtn().isDisplayed(), "Bike image is not dispalyed in Map");
			qrLog.info("Bike image should be dispalyed in Map");
			feedbackPo.getEleStopBtn().click();
			feedbackPo.getEleYesBtn().click();
			feedbackPo.getEleNextBtn().click();
			feedbackPo.getEleSubmitBtn().click();
		}
		catch(Exception e){
			qrLog.error("Exception in  case testSupportBike()");
			e.printStackTrace();
			Assert.fail();
		}
		
	}
	
}
