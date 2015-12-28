/***********************************************************************
* @author 			:		Raghukiran MR
* @description		: 		Test scripts of Ride View
* @module			:		Ride View
* @testmethod		:	   testCreateMultipleRiders()
* @testmethod		:	   testStartRide()
* @testmethod		:	   testPassengerCheckIn()
* @testmethod		:	   testPassengerCheckOut()
* @testmethod		:	   testRiderStopRide()
* @testmethod		:	   testRiderCancelRide()
* @testmethod		:	   testGroupChatIcn()
* @testmethod		:	   testLocation()
* @testmethod		:	   testpassengercancelRide()
*/


package com.quickride.scripts;

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

public class RideViewTest extends QRBaseLib {
	public String sData[]=null;
	public String sTestCaseID=null;
	NewUserRegPO newUserRegPo;
	QRProfilePO qrProfilePo;
	FeedbackPO feedbackPo;
	RidesPO ridesPo;
	MyProfilePO myProfilePo;
	public Logger qrLog = Logger.getLogger(this.getClass());
	
	/*@Test id:RideView_01
	 * @description: Verify if passenger can join any rider of his choice when there are multiple riders available for the same route
	 * @author:Raghukiran MR
	 * 
	 */
	@Test(priority=1,enabled=true)
	public void testCreateMultipleRiders(){
		sTestCaseID="RideView_01";
		newUserRegPo = new NewUserRegPO(driver);
		qrProfilePo=new QRProfilePO(driver);
		myProfilePo =new MyProfilePO(driver);
		feedbackPo=new FeedbackPO(driver);
		sData= GenericLib.toReadExcelData(sTestCaseID);
		try{
			//UserA create the ride
			newUserRegPo.signUPorLogin(sData[1],sData[2],sData[3]);
			feedbackPo.createRide(sData[4],sData[5], ridesPo);
			qrProfilePo.logout();
			//UserB create the ride
			newUserRegPo.signUPorLogin(sData[6], sData[7], sData[8]);
			feedbackPo.createRide(sData[4],sData[5], ridesPo);
			qrProfilePo.logout();
			//UserC Join the ride
			newUserRegPo.signUPorLogin(sData[10], sData[11], sData[12]);
			feedbackPo.getEleSearchBtn().click();
			feedbackPo.getEleEnterAddTxtFld().sendKeys(sData[4]);
			feedbackPo.getEleFirstOption().click();
			feedbackPo.getEleRideNwBtn().click();
			feedbackPo.getEleEndAddressTxtFld().click();
			feedbackPo.getEleEnterAddTxtFld().sendKeys(sData[5]);
			feedbackPo.getEleFirstOption().click();
			feedbackPo.getEleFindRideTab().click();
			driver.findElement(By.name(sData[9])).click();
			Assert.assertTrue(feedbackPo.getEleJoinBtn().isDisplayed(), "Join button is not displayed");
			qrLog.info("Join button is displayed");
			feedbackPo.getEleJoinBtn().click();
			Assert.assertTrue(feedbackPo.getEleArrowIcn().isDisplayed(), "User join is not successfully");
			qrLog.info("User join is  successfully");
			feedbackPo.getEleArrowIcn().click();
			qrProfilePo.logout();
			//UserA accept the ride 
			newUserRegPo.getEleLoginBtn().click();
			newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[1]);
			newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[2]);
			newUserRegPo.getEleLoginBtn().click();
			feedbackPo.acceptRide();
			qrProfilePo.logout();
			//UserB Start the ride
			newUserRegPo.getEleLoginBtn().click();
			newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[6]);
			newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[7]);
			newUserRegPo.getEleLoginBtn().click();
			feedbackPo.getEleStartBtn().click();
			feedbackPo.getEleYesBtn().click();
			feedbackPo.getEleStopBtn().click();
			feedbackPo.getEleYesBtn().click();
			qrProfilePo.logout();
			//UserC check in and check out 
			newUserRegPo.getEleLoginBtn().click();
			newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[10]);
			newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[11]);
			newUserRegPo.getEleLoginBtn().click();
			feedbackPo.getEleCheckInBtn().click();
			feedbackPo.getEleYesBtn().click();
			feedbackPo.getEleCheckOutBtn().click();
			feedbackPo.getEleYesBtn().click();
			feedbackPo.getEleNextBtn().click();
			feedbackPo.getEleSubmitBtn().click();
			qrProfilePo.logout();
			//Cancellation code UserA stop the ride
			newUserRegPo.getEleLoginBtn().click();
			newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[1]);
			newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[2]);
			newUserRegPo.getEleLoginBtn().click();
			feedbackPo.getEleStopBtn().click();
			feedbackPo.getEleYesBtn().click();
			feedbackPo.getEleNextBtn().click();
			feedbackPo.getEleSubmitBtn().click();
			qrProfilePo.logout();
			}catch(Exception e){
			qrLog.error("Exception in  case testCreateMultipleRiders()");
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	/*@Test id:RideView_02
	 * @description:Verify if 'start' button is available when the ride is created by the rider
	 * @author:Raghukiran MR
	 * 
	 */
	@Test(priority=2,enabled=true)
	public void testStartRide(){
	sTestCaseID="RideView_02";
	newUserRegPo = new NewUserRegPO(driver);
	qrProfilePo=new QRProfilePO(driver);
	myProfilePo =new MyProfilePO(driver);
	feedbackPo=new FeedbackPO(driver);
	sData= GenericLib.toReadExcelData(sTestCaseID);
	try{
		//UserA create the ride and verify the start button
		newUserRegPo.signUPorLogin(sData[1], sData[2],sData[3]);
		feedbackPo.getEleSearchBtn().click();
		feedbackPo.getEleEnterAddTxtFld().sendKeys(sData[4]);
		feedbackPo.getEleFirstOption().click();
		feedbackPo.getEleRideNwBtn().click();
		feedbackPo.getEleEndAddressTxtFld().click();
		feedbackPo.getEleEnterAddTxtFld().sendKeys(sData[5]);
		feedbackPo.getEleFirstOption().click();
		feedbackPo.getEleOfferRideTab().click();
		feedbackPo.getEleOfferRideBtn().click();
		Assert.assertTrue(feedbackPo.getEleStartBtn().isDisplayed(), "Start button is not displayed");
		qrLog.info("Start button is displayed");
		feedbackPo.getEleStartBtn().click();
		feedbackPo.getEleYesBtn().click();
		feedbackPo.getEleStopBtn().click();
		feedbackPo.getEleYesBtn().click();
	}catch(Exception e){
		qrLog.error("Exception in  case testStartRide()");
		e.printStackTrace();
		Assert.fail();
	}

}
	/*@Test id:RideView_03
	 * @description:Verify if 'check in' button is displayed on the passenger's ride view screen when the ride is started by the rider
	 * @author:Raghukiran MR
	 * 
	 */
	@Test(priority=3,enabled=true)
	public void testPassengerCheckIn(){
	sTestCaseID="RideView_03";
	newUserRegPo = new NewUserRegPO(driver);
	qrProfilePo=new QRProfilePO(driver);
	myProfilePo =new MyProfilePO(driver);
	feedbackPo=new FeedbackPO(driver);
	sData= GenericLib.toReadExcelData(sTestCaseID);
	try{
		//UserA create the ride 
		newUserRegPo.signUPorLogin(sData[1], sData[2],sData[3]);
		feedbackPo.createRide(sData[4],sData[5], ridesPo);
		qrProfilePo.logout();
		//UserB join the ride 
		newUserRegPo.signUPorLogin(sData[6], sData[7],sData[9]);
		feedbackPo.getEleSearchBtn().click();
		feedbackPo.getEleEnterAddTxtFld().sendKeys(sData[4]);
		feedbackPo.getEleFirstOption().click();
		feedbackPo.getEleRideNwBtn().click();
		feedbackPo.getEleEndAddressTxtFld().click();
		feedbackPo.getEleEnterAddTxtFld().sendKeys(sData[5]);
		feedbackPo.getEleFirstOption().click();
		feedbackPo.getEleFindRideTab().click();
		driver.findElement(By.name(sData[3])).click();
		feedbackPo.getEleJoinBtn().click();
		Assert.assertTrue(feedbackPo.getEleArrowIcn().isDisplayed(), "Join button is not successfully");
		qrLog.info("Join button is  successfully");
		feedbackPo.getEleArrowIcn().click();
		qrProfilePo.logout();
		//UserA accept the ride 
		newUserRegPo.getEleLoginBtn().click();
		newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[1]);
		newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[2]);
		newUserRegPo.getEleLoginBtn().click();
		feedbackPo.acceptRide();
		qrProfilePo.logout();
		//UserB Check in 
		newUserRegPo.getEleLoginBtn().click();
		newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[6]);
		newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[7]);
		newUserRegPo.getEleLoginBtn().click();
		Assert.assertTrue(feedbackPo.getEleCheckInBtn().isDisplayed(), "Check In button is not displayed");
		qrLog.info("Check In button is displayed");
		feedbackPo.getEleCheckInBtn().click();
		feedbackPo.getEleYesBtn().click();
		feedbackPo.getEleCheckOutBtn().click();
		feedbackPo.getEleYesBtn().click();
		feedbackPo.getEleNextBtn().click();
		feedbackPo.getEleSubmitBtn().click();
		qrProfilePo.logout();
		//Clean up the code
		newUserRegPo.getEleLoginBtn().click();
		newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[1]);
		newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[2]);
		newUserRegPo.getEleLoginBtn().click();
		feedbackPo.getEleStopBtn().click();
		feedbackPo.getEleYesBtn().click();
		feedbackPo.getEleNextBtn().click();
		feedbackPo.getEleSubmitBtn().click();
		qrProfilePo.logout();
	}catch(Exception e){
		qrLog.error("Exception in  case testPassengerCheckIn()");
		e.printStackTrace();
		Assert.fail();
	}
	}
	/*@Test id:RideView_04
	 * @description:Verify if checkout button is present for the passenger after the passenger has checked in to the started ride
	 * @author:Raghukiran MR
	 * 
	 */
	@Test(priority=4,enabled=true)
	public void testPassengerCheckOut(){
	sTestCaseID="RideView_04";
	newUserRegPo = new NewUserRegPO(driver);
	qrProfilePo=new QRProfilePO(driver);
	myProfilePo =new MyProfilePO(driver);
	feedbackPo=new FeedbackPO(driver);
	sData= GenericLib.toReadExcelData(sTestCaseID);
	try{
		//UserA create the ride 
		newUserRegPo.signUPorLogin(sData[1], sData[2],sData[3]);
		feedbackPo.createRide(sData[4],sData[5], ridesPo);
		qrProfilePo.logout();
		//UserB join the ride
		newUserRegPo.signUPorLogin(sData[6], sData[7],sData[8]);
		feedbackPo.getEleSearchBtn().click();
		feedbackPo.getEleEnterAddTxtFld().sendKeys(sData[4]);
		feedbackPo.getEleFirstOption().click();
		feedbackPo.getEleRideNwBtn().click();
		feedbackPo.getEleEndAddressTxtFld().click();
		feedbackPo.getEleEnterAddTxtFld().sendKeys(sData[5]);
		feedbackPo.getEleFirstOption().click();
		feedbackPo.getEleFindRideTab().click();
		driver.findElement(By.name(sData[3])).click();
		feedbackPo.getEleJoinBtn().click();
		Assert.assertTrue(feedbackPo.getEleArrowIcn().isDisplayed(), "Join button is not successfully");
		qrLog.info("Join button is  successfully");
		feedbackPo.getEleArrowIcn().click();
		qrProfilePo.logout();
		//UserA accept the ride
		newUserRegPo.getEleLoginBtn().click();
		newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[1]);
		newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[2]);
		newUserRegPo.getEleLoginBtn().click();
		feedbackPo.acceptRide();
		qrProfilePo.logout();
		//UserB Check in and verify the check In button
		newUserRegPo.getEleLoginBtn().click();
		newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[6]);
		newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[7]);
		newUserRegPo.getEleLoginBtn().click();
		Assert.assertTrue(feedbackPo.getEleCheckInBtn().isDisplayed(), "Check In button is not displayed");
		qrLog.info("Check In button is displayed");
		feedbackPo.getEleCheckInBtn().click();
		feedbackPo.getEleYesBtn().click();
		Assert.assertTrue(feedbackPo.getEleCheckOutBtn().isDisplayed(), "Check Out button is not displayed");
		qrLog.info("Check Out button is displayed");
		feedbackPo.getEleCheckOutBtn().click();
		feedbackPo.getEleYesBtn().click();
		feedbackPo.getEleNextBtn().click();
		feedbackPo.getEleSubmitBtn().click();
		qrProfilePo.logout();
		//Clean up the code
		newUserRegPo.getEleLoginBtn().click();
		newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[1]);
		newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[2]);
		newUserRegPo.getEleLoginBtn().click();
		feedbackPo.getEleStopBtn().click();
		feedbackPo.getEleYesBtn().click();
		feedbackPo.getEleNextBtn().click();
		feedbackPo.getEleSubmitBtn().click();
		qrProfilePo.logout();
	}catch(Exception e){
			qrLog.error("Exception in  case testPassengerCheckOut()");
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	/*@Test id:RideView_05
	 * @description:Verify if 'stop' option is available for rider to stop the ride
	 * @author:Raghukiran MR
	 * 
	 */
	@Test(priority=5,enabled=true)
	public void testRiderStopRide(){
	sTestCaseID="RideView_05";
	newUserRegPo = new NewUserRegPO(driver);
	qrProfilePo=new QRProfilePO(driver);
	myProfilePo =new MyProfilePO(driver);
	feedbackPo=new FeedbackPO(driver);
	sData= GenericLib.toReadExcelData(sTestCaseID);
	try{
		
		newUserRegPo.signUPorLogin(sData[1], sData[2],sData[3]);
		feedbackPo.createRide(sData[4],sData[5], ridesPo);
		qrProfilePo.logout();
		
		newUserRegPo.signUPorLogin(sData[6], sData[7],sData[8]);
		feedbackPo.getEleSearchBtn().click();
		feedbackPo.getEleEnterAddTxtFld().sendKeys(sData[4]);
		feedbackPo.getEleFirstOption().click();
		feedbackPo.getEleRideNwBtn().click();
		feedbackPo.getEleEndAddressTxtFld().click();
		feedbackPo.getEleEnterAddTxtFld().sendKeys(sData[5]);
		feedbackPo.getEleFirstOption().click();
		feedbackPo.getEleFindRideTab().click();
		driver.findElement(By.name(sData[3])).click();
		feedbackPo.getEleJoinBtn().click();
		Assert.assertTrue(feedbackPo.getEleArrowIcn().isDisplayed(), "Join button is not successfully");
		qrLog.info("Join button is  successfully");
		feedbackPo.getEleArrowIcn().click();
		qrProfilePo.logout();
		
		newUserRegPo.getEleLoginBtn().click();
		newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[1]);
		newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[2]);
		newUserRegPo.getEleLoginBtn().click();
		feedbackPo.acceptRide();
		qrProfilePo.logout();
		
		newUserRegPo.getEleLoginBtn().click();
		newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[6]);
		newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[7]);
		newUserRegPo.getEleLoginBtn().click();
		Assert.assertTrue(feedbackPo.getEleCheckInBtn().isDisplayed(), "Check In button is not displayed");
		qrLog.info("Check In button is displayed");
		feedbackPo.getEleCheckInBtn().click();
		feedbackPo.getEleYesBtn().click();
		Assert.assertTrue(feedbackPo.getEleCheckOutBtn().isDisplayed(), "Check Out button is not displayed");
		qrLog.info("Check Out button is displayed");
		feedbackPo.getEleCheckOutBtn().click();
		feedbackPo.getEleYesBtn().click();
		feedbackPo.getEleNextBtn().click();
		feedbackPo.getEleSubmitBtn().click();
		qrProfilePo.logout();
		
		newUserRegPo.getEleLoginBtn().click();
		newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[1]);
		newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[2]);
		newUserRegPo.getEleLoginBtn().click();
		Assert.assertTrue(feedbackPo.getEleStopBtn().isDisplayed(), "Stop button button is not displayed");
		qrLog.info("Stop button button is displayed");
		feedbackPo.getEleStopBtn().click();
		feedbackPo.getEleYesBtn().click();
		feedbackPo.getEleNextBtn().click();
		feedbackPo.getEleSubmitBtn().click();
		qrProfilePo.logout();
		}catch(Exception e){
			qrLog.error("Exception in  case  testRiderStopRide()");
			e.printStackTrace();
			Assert.fail();
		}
	}
	/*@Test id:RideView_06
	 * @description:Verify if 'cancel' button is available to cancel the ride created by the rider
	 * @author:Raghukiran MR
	 * 
	 */
	@Test(priority=6,enabled=true)
	public void testRiderCancelRide(){
	sTestCaseID="RideView_06";
	newUserRegPo = new NewUserRegPO(driver);
	qrProfilePo=new QRProfilePO(driver);
	myProfilePo =new MyProfilePO(driver);
	feedbackPo=new FeedbackPO(driver);
	ridesPo=new RidesPO(driver);
	sData= GenericLib.toReadExcelData(sTestCaseID);
	try{		
		newUserRegPo.signUPorLogin(sData[1], sData[2],sData[3]);
		feedbackPo.createRide(sData[4],sData[5], ridesPo);
		qrProfilePo.logout();
		newUserRegPo.signUPorLogin(sData[6], sData[7],sData[8]);
		feedbackPo.getEleSearchBtn().click();
		feedbackPo.getEleEnterAddTxtFld().sendKeys(sData[4]);
		feedbackPo.getEleFirstOption().click();
		feedbackPo.getEleRideNwBtn().click();
		feedbackPo.getEleEndAddressTxtFld().click();
		feedbackPo.getEleEnterAddTxtFld().sendKeys(sData[5]);
		feedbackPo.getEleFirstOption().click();
		feedbackPo.getEleFindRideTab().click();
		driver.findElement(By.name(sData[3])).click();
		feedbackPo.getEleJoinBtn().click();
		Assert.assertTrue(feedbackPo.getEleArrowIcn().isDisplayed(), "Join button is not successfully");
		qrLog.info("Join button is  successfully");
		feedbackPo.getEleArrowIcn().click();
		qrProfilePo.logout();
		
		newUserRegPo.getEleLoginBtn().click();
		newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[1]);
		newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[2]);
		newUserRegPo.getEleLoginBtn().click();
		Assert.assertTrue(feedbackPo.getEleStartBtn().isDisplayed(), "Start button is not displayed");
		qrLog.info("Start button is displayed");
		feedbackPo.getEleArrowIcn().click();
		
		feedbackPo.getEleAcceptLnk().click();
		feedbackPo.getEleStartBtn().click();
		feedbackPo.getEleYesBtn().click();
		feedbackPo.getEleArrowIcn().click();
		qrProfilePo.logout();
		newUserRegPo.getEleLoginBtn().click();
		newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[6]);
		newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[7]);
		newUserRegPo.getEleLoginBtn().click();
		feedbackPo.getEleCheckInBtn().click();
		feedbackPo.getEleYesBtn().click();
		feedbackPo.getEleArrowIcn().click();
		qrProfilePo.logout();
		
		newUserRegPo.getEleLoginBtn().click();
		newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[1]);
		newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[2]);
		newUserRegPo.getEleLoginBtn().click();
		ridesPo.getEleCancelBtn().click();
		ridesPo. getEleAlertYesBtn().click();
		Assert.assertTrue(feedbackPo.getEleRideNwBtn().isDisplayed(), "Ride is not canceled");
		qrLog.info("Ride is canceled");
	}catch(Exception e){
		qrLog.error("Exception in  case testRiderCancelRide()");
		e.printStackTrace();
		Assert.fail();
	}
	
}
	/*@Test id:RideView_07
	 * @description:Verify if chat button is available to chat with co-passengers
	 * @author:Raghukiran MR
	 * 
	 */
	@Test(priority=7,enabled=true)
	public void testGroupChatIcn(){
	sTestCaseID="RideView_07";
	newUserRegPo = new NewUserRegPO(driver);
	qrProfilePo=new QRProfilePO(driver);
	myProfilePo =new MyProfilePO(driver);
	feedbackPo=new FeedbackPO(driver);
	ridesPo=new RidesPO(driver);
	sData= GenericLib.toReadExcelData(sTestCaseID);
	try{
		newUserRegPo.signUPorLogin(sData[1], sData[2],sData[3]);
		feedbackPo.createRide(sData[4],sData[5], ridesPo);
		qrProfilePo.logout();
		
		newUserRegPo.signUPorLogin(sData[6], sData[7],sData[8]);
		feedbackPo.getEleSearchBtn().click();
		feedbackPo.getEleEnterAddTxtFld().sendKeys(sData[4]);
		feedbackPo.getEleFirstOption().click();
		feedbackPo.getEleRideNwBtn().click();
		feedbackPo.getEleEndAddressTxtFld().click();
		feedbackPo.getEleEnterAddTxtFld().sendKeys(sData[5]);
		feedbackPo.getEleFirstOption().click();
		feedbackPo.getEleFindRideTab().click();
		driver.findElement(By.name(sData[3])).click();
		feedbackPo.getEleJoinBtn().click();
		Assert.assertTrue(feedbackPo.getEleArrowIcn().isDisplayed(), "Join button is not successfully");
		qrLog.info("Join button is  successfully");
		feedbackPo.getEleArrowIcn().click();
		qrProfilePo.logout();
		
		newUserRegPo.getEleLoginBtn().click();
		newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[1]);
		newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[2]);
		newUserRegPo.getEleLoginBtn().click();
		Assert.assertTrue(myProfilePo.getEleChatBtn().isDisplayed(), "Chat Icon is not displayed");
		qrLog.info("Chat Icon is displayed");
		feedbackPo.getEleArrowIcn().click();
		feedbackPo.getEleNotificationIcon().click();
		feedbackPo.getEleAcceptLnk().click();
		
		feedbackPo.getEleStartBtn().click();
		feedbackPo.getEleYesBtn().click();
		qrProfilePo.logout();
		
		newUserRegPo.getEleLoginBtn().click();
		newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[6]);
		newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[7]);
		newUserRegPo.getEleLoginBtn().click();
		Assert.assertTrue(myProfilePo.getEleChatBtn().isDisplayed(), "Chat button is not displayed");
		qrLog.info("Chat button is displayed");
		feedbackPo.getEleCheckInBtn().click();
		feedbackPo.getEleYesBtn().click();
		feedbackPo.getEleCheckOutBtn().click();
		feedbackPo.getEleYesBtn().click();
		feedbackPo.getEleNextBtn().click();
		feedbackPo.getEleSubmitBtn().click();
		qrProfilePo.logout();
		//Cancellation code
		newUserRegPo.getEleLoginBtn().click();
		newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[1]);
		newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[2]);
		newUserRegPo.getEleLoginBtn().click();
		feedbackPo.getEleStopBtn().click();
		feedbackPo.getEleYesBtn().click();
		feedbackPo.getEleNextBtn().click();
		feedbackPo.getEleSubmitBtn().click();
		qrProfilePo.logout();
		}
	catch(Exception e){
		qrLog.error("Exception in  case testGroupChat()");
		e.printStackTrace();
		Assert.fail();
}
	}
	/*@Test id:RideView_08
	 * @description:Verify if  ride path along with location of all participants accurately displayed before starting the ride 
	 * @author:Raghukiran MR
	 * 
	 */
	@Test(priority=8,enabled=true)
	public void testLocation(){
	sTestCaseID="RideView_08";
	newUserRegPo = new NewUserRegPO(driver);
	qrProfilePo=new QRProfilePO(driver);
	myProfilePo =new MyProfilePO(driver);
	feedbackPo=new FeedbackPO(driver);
	ridesPo=new RidesPO(driver);
	sData= GenericLib.toReadExcelData(sTestCaseID);
	try{ 
		newUserRegPo.signUPorLogin(sData[1], sData[2],sData[3]);
		feedbackPo.createRide(sData[4],sData[5], ridesPo);
		qrProfilePo.logout();
		newUserRegPo.signUPorLogin(sData[6], sData[7],sData[8]);
		feedbackPo.getEleSearchBtn().click();
		feedbackPo.getEleEnterAddTxtFld().sendKeys(sData[4]);
		feedbackPo.getEleFirstOption().click();
		feedbackPo.getEleRideNwBtn().click();
		feedbackPo.getEleEndAddressTxtFld().click();
		feedbackPo.getEleEnterAddTxtFld().sendKeys(sData[5]);
		feedbackPo.getEleFirstOption().click();
		feedbackPo.getEleFindRideTab().click();
		driver.findElement(By.name(sData[3])).click();
		feedbackPo.getEleJoinBtn().click();
		Assert.assertTrue(feedbackPo.getEleArrowIcn().isDisplayed(), "Join button is not successfully");
		qrLog.info("Join button is  successfully");
		feedbackPo.getEleArrowIcn().click();
		qrProfilePo.logout();
		
		newUserRegPo.getEleLoginBtn().click();
		newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[1]);
		newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[2]);
		newUserRegPo.getEleLoginBtn().click();
		feedbackPo.getEleArrowIcn().click();
		feedbackPo.getEleNotificationIcon().click();
		feedbackPo.getEleAcceptLnk().click();
		Assert.assertTrue(feedbackPo.getEleParticipantIcn().isDisplayed(), "Participants accurately is not displayed");
		qrLog.info("Participants accurately displayed");
		feedbackPo.getEleStartBtn().click();
		feedbackPo.getEleYesBtn().click();
		feedbackPo.getEleStopBtn().click();
		feedbackPo.getEleYesBtn().click();
		qrProfilePo.logout();
		
		newUserRegPo.getEleLoginBtn().click();
		newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[6]);
		newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[7]);
		newUserRegPo.getEleLoginBtn().click();
		ridesPo.getEleCancelBtn().click();
		ridesPo.getEleYesBtn().click();
		qrProfilePo.logout();
	}catch(Exception e){
		qrLog.error("Exception in  case testLocation()");
		e.printStackTrace();
		Assert.fail();
	}
}
	/*@Test id:RideView_06
	 * @description:Verify if 'cancel' button is available to cancel the ride created by the rider
	 * @author:Raghukiran MR
	 * 
	 */
	@Test(priority=9,enabled=true)
	public void testpassengercancelRide(){
	sTestCaseID="RideView_09";
	newUserRegPo = new NewUserRegPO(driver);
	qrProfilePo=new QRProfilePO(driver);
	myProfilePo =new MyProfilePO(driver);
	feedbackPo=new FeedbackPO(driver);
	ridesPo=new RidesPO(driver);
	sData= GenericLib.toReadExcelData(sTestCaseID);
	try{
		
		newUserRegPo.signUPorLogin(sData[1], sData[2],sData[3]);
		feedbackPo.createRide(sData[4],sData[5], ridesPo);
		qrProfilePo.logout();
		newUserRegPo.signUPorLogin(sData[6], sData[7],sData[8]);
		feedbackPo.getEleSearchBtn().click();
		feedbackPo.getEleEnterAddTxtFld().sendKeys(sData[4]);
		feedbackPo.getEleFirstOption().click();
		feedbackPo.getEleRideNwBtn().click();
		feedbackPo.getEleEndAddressTxtFld().click();
		feedbackPo.getEleEnterAddTxtFld().sendKeys(sData[5]);
		feedbackPo.getEleFirstOption().click();
		feedbackPo.getEleFindRideTab().click();
		driver.findElement(By.name(sData[3])).click();
		feedbackPo.getEleJoinBtn().click();
		Assert.assertTrue(feedbackPo.getEleArrowIcn().isDisplayed(), "Join button is not successfully");
		qrLog.info("Join button is  successfully");
		feedbackPo.getEleArrowIcn().click();
		qrProfilePo.logout();
		
		
		newUserRegPo.getEleLoginBtn().click();
		newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[1]);
		newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[2]);
		newUserRegPo.getEleLoginBtn().click();
		Assert.assertTrue(feedbackPo.getEleStartBtn().isDisplayed(), "Start button is not displayed");
		qrLog.info("Start button is displayed");
		feedbackPo.getEleArrowIcn().click();
		feedbackPo.getEleAcceptLnk().click();
		feedbackPo.getEleStartBtn().click();
		feedbackPo.getEleYesBtn().click();
		feedbackPo.getEleArrowIcn().click();
		qrProfilePo.logout();
		

		newUserRegPo.getEleLoginBtn().click();
		newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[6]);
		newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[7]);
		newUserRegPo.getEleLoginBtn().click();
		Assert.assertTrue(ridesPo.getEleCancelBtn().isDisplayed(), "Cancel button is not displayed");
		qrLog.info("Cancel button is displayed");
		feedbackPo.getEleCheckInBtn().click();
		feedbackPo.getEleYesBtn().click();
		feedbackPo.getEleCheckOutBtn().click();
		feedbackPo.getEleYesBtn().click();
		feedbackPo.getEleNextBtn().click();
		feedbackPo.getEleSubmitBtn().click();
		qrProfilePo.logout();
		
		newUserRegPo.getEleLoginBtn().click();
		newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[1]);
		newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[2]);
		newUserRegPo.getEleLoginBtn().click();
		feedbackPo.getEleStopBtn().click();
		feedbackPo.getEleYesBtn().click();
		feedbackPo.getEleNextBtn().click();
		feedbackPo.getEleSubmitBtn().click();
		qrProfilePo.logout();
		}catch(Exception e){
		qrLog.error("Exception in  case cancelButton()");
		e.printStackTrace();
		Assert.fail();
	}
	
}
}