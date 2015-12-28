/***********************************************************************
* @author 			:		LAKSHMI BS
* @description		: 		Test scripts of Notification module.
* @module			:		Notification
* @testmethod		:		testRiderPassengerJoinNoti()
* @testmethod		:		testRiderStartedNoti()
* @testmethod		:		testRiderStartedCancelled()
* @testmethod 		:		testPassengerJoinsCancels()
* @testmethod 		:		testRideNotiPassengerJoinsCancels()
* @testmethod		:		testNotiPassengerJoinsCancelsAfterStart()
* @testmethod 		:		testNotiDeletedLongPress()		
* @testmethod 		:		testNotiDeleteBySwipe()		
* @testmethod 		:		testNotiReject()		
* @testmethod 		:		testNotiProfile()		
* @testmethod 		:		testNotiProfile()	
*/

package com.quickride.scripts;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.javascript.host.Touch;
import com.quickride.PO.FeedbackPO;
import com.quickride.PO.MyProfilePO;
import com.quickride.PO.NewUserRegPO;
import com.quickride.PO.QRProfilePO;
import com.quickride.PO.RidesPO;
import com.quickride.baselib.GenericLib;
import com.quickride.baselib.QRBaseLib;

import io.appium.java_client.MobileElement;
import io.appium.java_client.SwipeElementDirection;
import io.appium.java_client.TouchAction;

public class NotificationsTest extends QRBaseLib
{
	public String sData[]=null;
	public String sTestCaseID=null;
	NewUserRegPO newUserRegPo;
	QRProfilePO qrProfilePo;
	MyProfilePO myProfilePo;
	FeedbackPO feedbackPo;
	RidesPO ridesPo;
	public Logger qrLog = Logger.getLogger(this.getClass());
	/*
	 * @description: Verify if notification is received when the passenger joins the ride created by rider and Rider accepts the passenger to join
	 * @author: LAKSHMI BS
	 * 
	 */	
	@Test(priority=1,enabled=true)
	public void testRiderPassengerJoinNoti()
	{
		sTestCaseID="Notification_01";
		sData= GenericLib.toReadExcelData(sTestCaseID);
		newUserRegPo = new NewUserRegPO(driver);
		qrProfilePo= new QRProfilePO(driver);
		feedbackPo=new FeedbackPO(driver);
		ridesPo = new RidesPO(driver);
		try
		{
			newUserRegPo.signUPorLogin(sData[1], sData[2],sData[3]);
			qrLog.info("Rider creating the ride");
		
			feedbackPo.createRide(sData[4], sData[5],ridesPo);
			qrProfilePo.logout();
			
			qrLog.info("Passenger joining the ride");
			newUserRegPo.signUPorLogin(sData[6], sData[7],sData[8]);
			feedbackPo.joinRide(sData[4],sData[5],sData[3]);
			qrProfilePo.logout();
		
			qrLog.info("Rider logins receive notification");
			newUserRegPo.getEleLoginBtn().click();
			newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[1]);
			newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[2]);
			driver.hideKeyboard();
			newUserRegPo.getEleLoginBtn().click();
			feedbackPo.getEleArrowIcn().click();
			feedbackPo.navigateNotification();
			
			Assert.assertTrue(feedbackPo.getEleAcceptLnk().isDisplayed(), "Passenger request to join ride notification is not displayed");
			qrLog.info("Passenger request to join ride notification is displayed for Rider");
			
			feedbackPo.getEleAcceptLnk().click();
			feedbackPo.getEleArrowIcn().click();
			qrProfilePo.logout();
			
			qrLog.info("Passenger logins to recieve joined notification");
			newUserRegPo.getEleLoginBtn().click();
			
			newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[6]);
			newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[7]);
			driver.hideKeyboard();
			newUserRegPo.getEleLoginBtn().click();
			feedbackPo.getEleArrowIcn().click();
			feedbackPo.navigateNotification();
			
			Assert.assertTrue(driver.findElement(By.name("Joined "+sData[3]+"'s ride")).isDisplayed(), "Rider accepts, Passenger joined notification is not displayed");
			qrLog.info("Rider accepts, Passenger joined notification is displayed for Passenger");
		} catch (Exception e) {
			qrLog.error("Exception in  case testRiderPassengerJoinNoti");
			e.printStackTrace();
			Assert.fail();
		}
		
	}
	/*
	 * @description: Verify if notification is received by the passenger when the ride is started by rider
	 * @author: LAKSHMI BS
	 * 
	 */	
	@Test(priority=2,enabled=true)
	public void testRiderStartedNoti()
	{
		sTestCaseID="Notification_02";
		sData= GenericLib.toReadExcelData(sTestCaseID);
		newUserRegPo = new NewUserRegPO(driver);
		qrProfilePo= new QRProfilePO(driver);
		feedbackPo=new FeedbackPO(driver);
		ridesPo = new RidesPO(driver);
		try
		{
			newUserRegPo.signUPorLogin(sData[1], sData[2],sData[3]);
			qrLog.info("Rider creating the ride");
			feedbackPo.createRide(sData[4], sData[5],ridesPo);
			qrProfilePo.logout();
			
			qrLog.info("Passenger joining the ride");
			newUserRegPo.signUPorLogin(sData[6], sData[7],sData[8]);
			feedbackPo.joinRide(sData[4],sData[5],sData[3]);
			qrProfilePo.logout();
			
			qrLog.info("Rider accepting passenger request");
			newUserRegPo.getEleLoginBtn().click();
			newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[1]);
			newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[2]);
			driver.hideKeyboard();
			newUserRegPo.getEleLoginBtn().click();
			feedbackPo.acceptRide();
					
			qrProfilePo.logout();
	
			qrLog.info("Passenger logins to recieve Ride started notification");
			newUserRegPo.getEleLoginBtn().click();
			newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[6]);
			newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[7]);
			driver.hideKeyboard();
			newUserRegPo.getEleLoginBtn().click();
					
			Assert.assertTrue(ridesPo.getEleRideStartedTxt().isDisplayed(), "Rider started notification to Passenger is not displayed");
			qrLog.info("Rider started notification to Passenger is displayed and is in Ridde started page");
		} catch (Exception e) {
			qrLog.error("Exception in  case testRiderStartedNoti");
			e.printStackTrace();
			Assert.fail();
		}
	}
	/*
	 * @description: Verify if notification is received by the passenger when the ride is cancelled by rider
	 * @author: LAKSHMI BS
	 * 
	 */	
	@Test(priority=3,enabled=true)
	public void testRiderStartedCancelled()
	{
		sTestCaseID="Notification_03";
		sData= GenericLib.toReadExcelData(sTestCaseID);
		newUserRegPo = new NewUserRegPO(driver);
		qrProfilePo= new QRProfilePO(driver);
		feedbackPo=new FeedbackPO(driver);
		ridesPo = new RidesPO(driver);
		try
		{
			
			newUserRegPo.signUPorLogin(sData[1], sData[2],sData[3]);
			qrLog.info("Rider creating the ride");
			feedbackPo.createRide(sData[4], sData[5],ridesPo);
			qrProfilePo.logout();
			
			qrLog.info("Passenger joining the ride");
			newUserRegPo.signUPorLogin(sData[6], sData[7],sData[8]);
			feedbackPo.joinRide(sData[4],sData[5],sData[3]);
			qrProfilePo.logout();
		
			qrLog.info("Rider accepting passenger request");
			newUserRegPo.getEleLoginBtn().click();
			newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[1]);
			newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[2]);
			driver.hideKeyboard();
			newUserRegPo.getEleLoginBtn().click();
			
			feedbackPo.getEleArrowIcn().click();
			feedbackPo.navigateNotification();	
			feedbackPo.getEleAcceptLnk().click();
			
			ridesPo.getEleCancelBtn().click();
			ridesPo.getEleYesBtn().click();
			
			feedbackPo.navigateNotification();	
			Assert.assertTrue(feedbackPo.getEleQRWalletNotiTxt().isDisplayed(), "Quick Ride Wallet notification to Passenger is not displayed");
			qrLog.info("Quick Ride Wallet notification to Passenger is displayed");
			ridesPo.getEleNotiBackArrowIcn().click();
			qrProfilePo.logout();
		
			qrLog.info("Passenger logins to recieve Ride cancelled notification");
			newUserRegPo.getEleLoginBtn().click();
			newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[6]);
			newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[7]);
			driver.hideKeyboard();
			newUserRegPo.getEleLoginBtn().click();
			Assert.assertTrue(ridesPo.getEleRideClosedTxt().isDisplayed(), "Ride closed message to Passenger is not displayed");
			qrLog.info("Ride closed message to Passenger is displayed");
			
			ridesPo.getEleOkBtn().click();
			qrProfilePo.logout();
		} catch (Exception e) {
			qrLog.error("Exception in  case testRiderStartedCancelled");
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	/*
	 * @description: Verify if notification is received when passenger joins and cancels after Rider accepts the join request
	 * @author: LAKSHMI BS
	 * 
	 */	
	@Test(priority=4,enabled=true)
	public void testPassengerJoinsCancels()
	{
		sTestCaseID="Notification_04";
		sData= GenericLib.toReadExcelData(sTestCaseID);
		newUserRegPo = new NewUserRegPO(driver);
		qrProfilePo= new QRProfilePO(driver);
		feedbackPo=new FeedbackPO(driver);
		ridesPo = new RidesPO(driver);
		try
		{
			newUserRegPo.signUPorLogin(sData[1], sData[2],sData[3]);
			qrLog.info("Rider creating the ride");
			feedbackPo.createRide(sData[4], sData[5],ridesPo);
			qrProfilePo.logout();
			
			qrLog.info("Passenger joining the ride");
			newUserRegPo.signUPorLogin(sData[6], sData[7],sData[8]);
			feedbackPo.joinRide(sData[4],sData[5],sData[3]);
			qrProfilePo.logout();
						
			qrLog.info("Rider accepting passenger request");
			newUserRegPo.getEleLoginBtn().click();
			newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[1]);
			newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[2]);
			driver.hideKeyboard();
			newUserRegPo.getEleLoginBtn().click();
			feedbackPo.acceptRide();
			
			qrProfilePo.logout();
			qrLog.info("Passenger logins to recieve Ride cancelled notification");
			newUserRegPo.getEleLoginBtn().click();
			newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[6]);
			newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[7]);
			driver.hideKeyboard();
			newUserRegPo.getEleLoginBtn().click();
			ridesPo.getEleCancelBtn().click();
			ridesPo.getEleYesBtn().click();
			
			feedbackPo.navigateNotification();	
			Assert.assertTrue(feedbackPo.getEleQRWalletNotiTxt().isDisplayed(), "Quick Ride Wallet notification to Passenger is not displayed");
			qrLog.info("Quick Ride Wallet notification to Passenger is displayed");
			ridesPo.getEleNotiBackArrowIcn().click();
			qrProfilePo.logout();
	} catch (Exception e) {
			qrLog.error("Exception in  case testPassengerJoinsCancels");
			e.printStackTrace();
			Assert.fail();
		}
		
	}
	
	/*
	 * @description: Verify if notification is received to Rider when passenger joins and cancels after accepting the join request
	 * @author: LAKSHMI BS
	 * 
	 */	
	@Test(priority=5,enabled=true)
	public void testRideNotiPassengerJoinsCancels()
	{
		sTestCaseID="Notification_05";
		sData= GenericLib.toReadExcelData(sTestCaseID);
		newUserRegPo = new NewUserRegPO(driver);
		qrProfilePo= new QRProfilePO(driver);
		feedbackPo=new FeedbackPO(driver);
		ridesPo = new RidesPO(driver);
		try
		{
			
			newUserRegPo.signUPorLogin(sData[1], sData[2],sData[3]);
			qrLog.info("Rider creating the ride");
			feedbackPo.createRide(sData[4], sData[5],ridesPo);
			qrProfilePo.logout();
			
			qrLog.info("Passenger joining the ride");
			newUserRegPo.signUPorLogin(sData[6], sData[7],sData[8]);
			feedbackPo.joinRide(sData[4],sData[5],sData[3]);
			qrProfilePo.logout();
					
			qrLog.info("Rider accepting passenger request");
			newUserRegPo.getEleLoginBtn().click();
			newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[1]);
			newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[2]);
			driver.hideKeyboard();
			newUserRegPo.getEleLoginBtn().click();
			feedbackPo.acceptRide();
			qrProfilePo.logout();
			
			qrLog.info("Passenger logins to cancel the ride");
			newUserRegPo.getEleLoginBtn().click();
			newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[6]);
			newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[7]);
			driver.hideKeyboard();
			newUserRegPo.getEleLoginBtn().click();
			ridesPo.getEleCancelBtn().click();
			ridesPo.getEleYesBtn().click();
			feedbackPo.navigateNotification();	
			
			Assert.assertTrue(feedbackPo.getEleQRWalletNotiTxt().isDisplayed(), "Quick Ride Wallet notification to Passenger is not displayed");
			qrLog.info("Quick Ride Wallet notification to Passenger is displayed");
			
			ridesPo.getEleNotiBackArrowIcn().click();
			qrProfilePo.logout();
			
			qrLog.info("Rider receiving passenger unjoined notification");
			newUserRegPo.getEleLoginBtn().click();
			newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[1]);
			newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[2]);
			driver.hideKeyboard();
			newUserRegPo.getEleLoginBtn().click();
			feedbackPo.getEleArrowIcn().click();
			feedbackPo.navigateNotification();	
	
			Assert.assertTrue(driver.findElement(By.name(sData[8]+" unjoined your ride")).isDisplayed(), "Passenger Unjoined notification to Rider is not displayed");
			qrLog.info("Passenger Unjoined notification to Rider is displayed");
			
			ridesPo.getEleNotiBackArrowIcn().click();
			qrProfilePo.logout();
		} catch (Exception e) {
			qrLog.error("Exception in  case testRideNotiPassengerJoinsCancels");
			e.printStackTrace();
			Assert.fail();
		}
		
	}

	/*
	 * @description: Verify if notification is received when Rider accepts, starts rides and stops
	 * @author: LAKSHMI BS
	 * 
	 */	
	@Test(priority=6,enabled=true)
	public void testNotiPassengerJoinsCancelsAfterStart()
	{
		sTestCaseID="Notification_06";
		sData= GenericLib.toReadExcelData(sTestCaseID);
		newUserRegPo = new NewUserRegPO(driver);
		qrProfilePo= new QRProfilePO(driver);
		feedbackPo=new FeedbackPO(driver);
		ridesPo = new RidesPO(driver);
		try
		{
			newUserRegPo.signUPorLogin(sData[1], sData[2],sData[3]);
			qrLog.info("Rider creating the ride");
			feedbackPo.createRide(sData[4], sData[5],ridesPo);
			qrProfilePo.logout();
			
			qrLog.info("Passenger joining the ride");
			newUserRegPo.signUPorLogin(sData[6], sData[7],sData[8]);
			feedbackPo.joinRide(sData[4],sData[5],sData[3]);
			qrProfilePo.logout();
			
			qrLog.info("Rider accepting passenger request");
			newUserRegPo.getEleLoginBtn().click();
			newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[1]);
			newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[2]);
			driver.hideKeyboard();
			newUserRegPo.getEleLoginBtn().click();
			feedbackPo.getEleArrowIcn().click();
			feedbackPo.navigateNotification();	
			feedbackPo.getEleAcceptLnk().click();
			feedbackPo.getEleStartBtn().click();
			feedbackPo.getEleYesBtn().click();
	
			Assert.assertTrue(ridesPo.getEleRideStartedTxt().isDisplayed(), "Ride is not started");
			qrLog.info("Ride started page is displayed");
			
			ridesPo.getEleStopBtn().click();
			ridesPo.getEleYesBtn().click();
			feedbackPo.navigateNotification();	
			Assert.assertTrue(feedbackPo.getEleQRWalletNotiTxt().isDisplayed(), "Quick Ride Wallet notification to Rider is not displayed");
			qrLog.info("Quick Ride Wallet notification to Rider is displayed");
			
			qrProfilePo.logout();
			} catch (Exception e) {
			qrLog.error("Exception in  case testNotiPassengerJoinsCancelsAfterStart");
			e.printStackTrace();
			Assert.fail();
		}
	}


	/*
	 * @description: Verify if the notifications can be deleted on long press of the notification
	 * @author: LAKSHMI BS
	 * 
	 */	
	@Test(priority=7,enabled=true)
	public void testNotiDeletedLongPress()
	{
		sTestCaseID="Notification_07";
		sData= GenericLib.toReadExcelData(sTestCaseID);
		newUserRegPo = new NewUserRegPO(driver);
		qrProfilePo= new QRProfilePO(driver);
		feedbackPo=new FeedbackPO(driver);
		ridesPo = new RidesPO(driver);
		try
		{
			newUserRegPo.signUPorLogin(sData[1], sData[2],sData[3]);
			qrLog.info("Rider creating the ride");
			feedbackPo.createRide(sData[4], sData[5],ridesPo);
			qrProfilePo.logout();
			
			qrLog.info("Passenger joining the ride");
			newUserRegPo.signUPorLogin(sData[6], sData[7],sData[8]);
			feedbackPo.joinRide(sData[4],sData[5],sData[3]);
			qrProfilePo.logout();
		
			qrLog.info("Rider logins receive notification");
			newUserRegPo.getEleLoginBtn().click();
			newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[1]);
			newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[2]);
			driver.hideKeyboard();
			newUserRegPo.getEleLoginBtn().click();
			
			feedbackPo.acceptRide();
			qrProfilePo.logout();
			qrLog.info("Passenger logins to recieve Ride cancelled notification");
			newUserRegPo.getEleLoginBtn().click();
			newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[6]);
			newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[7]);
			driver.hideKeyboard();
			newUserRegPo.getEleLoginBtn().click();
			ridesPo.getEleCancelBtn().click();
			ridesPo.getEleYesBtn().click();
			feedbackPo.navigateNotification();	
			
			Assert.assertTrue(feedbackPo.getEleQRWalletNotiTxt().isDisplayed(), "Quick Ride Wallet notification to Passenger is not displayed");
			qrLog.info("Quick Ride Wallet notification to Passenger is displayed");
					
			TouchAction act = new TouchAction(driver);
			act.longPress(625, 202).release().perform();
			Assert.assertTrue(feedbackPo.getEleStartExploringTxt().isDisplayed(), "Passenger request to join ride notification is not swiped off");
			qrLog.info("Passenger request to join ride notification is swiped off by Rider");
			ridesPo.getEleNotiBackArrowIcn().click();
			qrProfilePo.logout();
		} catch (Exception e) {
			qrLog.error("Exception in  case testNotiDeletedLongPress");
			e.printStackTrace();
			Assert.fail();
		}
	}
	/*
	 * @description: Verify if the notifications can be deleted on swipe of the notification
	 * @author: LAKSHMI BS
	 * 
	 */	
	@Test(priority=8,enabled=true)
	public void testNotiDeleteBySwipe()
	{
		sTestCaseID="Notification_08";
		sData= GenericLib.toReadExcelData(sTestCaseID);
		newUserRegPo = new NewUserRegPO(driver);
		qrProfilePo= new QRProfilePO(driver);
		feedbackPo=new FeedbackPO(driver);
		ridesPo = new RidesPO(driver);
		try
		{		
			newUserRegPo.signUPorLogin(sData[1], sData[2],sData[3]);
			qrLog.info("Rider creating the ride");
			feedbackPo.createRide(sData[4], sData[5],ridesPo);
			qrProfilePo.logout();
			
			qrLog.info("Passenger joining the ride");
			newUserRegPo.signUPorLogin(sData[6], sData[7],sData[8]);
			feedbackPo.joinRide(sData[4],sData[5],sData[3]);
			qrProfilePo.logout();
			
			qrLog.info("Rider logins receive notification");
			newUserRegPo.getEleLoginBtn().click();
			newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[1]);
			newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[2]);
			driver.hideKeyboard();
			newUserRegPo.getEleLoginBtn().click();
			
			feedbackPo.getEleArrowIcn().click();
			feedbackPo.navigateNotification();
			
			Assert.assertTrue(feedbackPo.getEleAcceptLnk().isDisplayed(), "Passenger request to join ride notification is not displayed");
			qrLog.info("Passenger request to join ride notification is displayed for Rider");
			driver.swipe(625, 202, 73, 194, 3000);
			Assert.assertTrue(feedbackPo.getEleStartExploringTxt().isDisplayed(), "Passenger request to join ride notification is not swiped off");
			qrLog.info("Passenger request to join ride notification is swiped off by Rider");
			ridesPo.getEleNotiBackArrowIcn().click();
			qrProfilePo.logout();
		} catch (Exception e) {
			qrLog.error("Exception in  case testNotiDeleteBySwipe");
			e.printStackTrace();
			Assert.fail();
		}
	}
	/*
	 * @description: Verify if the notifications can be deleted when Rider rejects the ride
	 * @author: LAKSHMI BS
	 * 
	 */	
	@Test(priority=9,enabled=true)
	public void testNotiReject()
	{
		sTestCaseID="Notification_09";
		sData= GenericLib.toReadExcelData(sTestCaseID);
		newUserRegPo = new NewUserRegPO(driver);
		qrProfilePo= new QRProfilePO(driver);
		feedbackPo=new FeedbackPO(driver);
		ridesPo = new RidesPO(driver);
		try
		{
			newUserRegPo.signUPorLogin(sData[1], sData[2],sData[3]);
			qrLog.info("Rider creating the ride");
			feedbackPo.createRide(sData[4], sData[5],ridesPo);
			qrProfilePo.logout();
			
			qrLog.info("Passenger joining the ride");
			newUserRegPo.signUPorLogin(sData[6], sData[7],sData[8]);
			feedbackPo.joinRide(sData[4],sData[5],sData[3]);
			qrProfilePo.logout();
		
			qrLog.info("Rider logins receive notification");
			newUserRegPo.getEleLoginBtn().click();
			newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[1]);
			newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[2]);
			driver.hideKeyboard();
			newUserRegPo.getEleLoginBtn().click();
			feedbackPo.getEleArrowIcn().click();
			feedbackPo.navigateNotification();
			
			Assert.assertTrue(feedbackPo.getEleRejectLnk().isDisplayed(), "Passenger request to join ride notification is not displayed");
			qrLog.info("Passenger request to join ride notification is displayed for Rider");
			qrLog.info("Reject link is displayed for Rider to reject");
			feedbackPo.getEleRejectLnk().click();
			Assert.assertTrue(feedbackPo.getEleStartExploringTxt().isDisplayed(), "Passenger request to join ride notification is still displayed");
			qrLog.info("Passenger request to join ride notification is deleted from Notifications");
			ridesPo.getEleNotiBackArrowIcn().click();
			qrProfilePo.logout();
		} catch (Exception e) {
			qrLog.error("Exception in  case testNotiReject");
			e.printStackTrace();
			Assert.fail();
		}
	}
	/*
	 * @description: Verify if the Rider can navigate to passengers profile
	 * @author: LAKSHMI BS
	 * 
	 */	
	@Test(priority=10,enabled=true)
	public void testNotiProfile()
	{
		sTestCaseID="Notification_10";
		sData= GenericLib.toReadExcelData(sTestCaseID);
		newUserRegPo = new NewUserRegPO(driver);
		qrProfilePo= new QRProfilePO(driver);
		feedbackPo=new FeedbackPO(driver);
		ridesPo = new RidesPO(driver);
		myProfilePo = new MyProfilePO(driver);
		try
		{
			newUserRegPo.signUPorLogin(sData[1], sData[2],sData[3]);
			qrLog.info("Rider creating the ride");
			feedbackPo.createRide(sData[4], sData[5],ridesPo);
			qrProfilePo.logout();
			
			qrLog.info("Passenger joining the ride");
			newUserRegPo.signUPorLogin(sData[6], sData[7],sData[8]);
			feedbackPo.joinRide(sData[4],sData[5],sData[3]);
			qrProfilePo.logout();
		
			qrLog.info("Rider logins receive notification");
			newUserRegPo.getEleLoginBtn().click();
			newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[1]);
			newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[2]);
			driver.hideKeyboard();
			newUserRegPo.getEleLoginBtn().click();
			feedbackPo.getEleArrowIcn().click();
			feedbackPo.navigateNotification();
			
			Assert.assertTrue(feedbackPo.getEleProfileLnk().isDisplayed(), "Passenger request to join ride notification is not displayed");
			qrLog.info("Passenger request to join ride notification is displayed for Rider");
			qrLog.info("Rider has got option to reject the request");
			feedbackPo.getEleProfileLnk().click();
			Assert.assertTrue(qrProfilePo.getEleProfileTxt().isDisplayed(), "Profile page is not displayed");
			qrLog.info("Profile page is displayed");
			
			myProfilePo.getEleArrowIcn().click();
			ridesPo.getEleNotiBackArrowIcn().click();
			qrProfilePo.logout();
	
		} catch (Exception e) {
			qrLog.error("Exception in  case testNotiProfile");
			e.printStackTrace();
			Assert.fail();
		}
		
		
	}
	/*
	 * @description: Verify if the Rider can navigate to passengers profile
	 * @author: LAKSHMI BS
	 * 
	 */	
	@Test(priority=11,enabled=true)
	public void testNotiCountTxt()
	{
		sTestCaseID="Notification_11";
		sData= GenericLib.toReadExcelData(sTestCaseID);
		newUserRegPo = new NewUserRegPO(driver);
		qrProfilePo= new QRProfilePO(driver);
		feedbackPo=new FeedbackPO(driver);
		ridesPo = new RidesPO(driver);
		myProfilePo = new MyProfilePO(driver);
		try
		{
			
			newUserRegPo.signUPorLogin(sData[1], sData[2],sData[3]);
			qrLog.info("Rider creating the ride");
			feedbackPo.createRide(sData[4], sData[5],ridesPo);
			qrProfilePo.logout();
			
			qrLog.info("Passenger joining the ride");
			newUserRegPo.signUPorLogin(sData[6], sData[7],sData[8]);
			feedbackPo.joinRide(sData[4],sData[5],sData[3]);
			qrProfilePo.logout();
		
			qrLog.info("Rider logins to receive start ride notification");
			newUserRegPo.getEleLoginBtn().click();
			newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[1]);
			newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[2]);
			driver.hideKeyboard();
			newUserRegPo.getEleLoginBtn().click();
			feedbackPo.getEleArrowIcn().click();
			Assert.assertTrue(ridesPo.getEleNotiCountTxt().isDisplayed(), "Notification count is not displayed");
			qrLog.info("Notification count is displayed");
			feedbackPo.navigateNotification();
			Assert.assertTrue(ridesPo.getEleStartRideNotiTxt().isDisplayed(), "Start ride notification is not displayed");
			qrLog.info("Start Ride notification is displayed for Rider");
			ridesPo.getEleNotiBackArrowIcn().click();
			qrProfilePo.logout();
	
		} catch (Exception e) {
			qrLog.error("Exception in  case testNotiCountTxt");
			e.printStackTrace();
			Assert.fail();
		}
		
		
	}
		
		
}

