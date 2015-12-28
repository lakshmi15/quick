/***********************************************************************
* @author 			:		DIGANTA D
* @description		: 		Test scripts of My Rides module.
* @module			:		My Rides
* @testmethod		:		testScheduledStatusRider()
* @testmethod		:		testScheduledStatusPassenger()
* @testmethod		:		testStartedStatusRider()
* @testmethod 		:		testStartedStatusPassenger()
* @testmethod		:		testRequestedStatusRider()
*  @testmethod		:		testRequestedStatusPassenger()
* @testmethod		:		testCompletedStatusRider()
* @testmethod 		:		testCompletedStatusPassenger()
* @testmethod		:		testCancelledStsRider()
* @testmethod 		:		testCancelledStsPassenger()
* @testmethod		:		testPassengerCancelStatus()
* @testmethod		:		testRiderAcceptsPassengerCancelsSts()
*/

package com.quickride.scripts;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.quickride.PO.FeedbackPO;
import com.quickride.PO.NewUserRegPO;
import com.quickride.PO.QRProfilePO;
import com.quickride.PO.RidesPO;
import com.quickride.baselib.GenericLib;
import com.quickride.baselib.QRBaseLib;

public class RidesTest extends QRBaseLib
{
	public Logger qrLog = Logger.getLogger(this.getClass());
	public String sData[]=null;
	public String sTestCaseID=null;
	NewUserRegPO newUserRegPo;
	QRProfilePO qrProfilePo;
	RidesPO ridesPo;
	FeedbackPO feedbackPo;
	/*
	 * @Description: MyRides display Upcoming Scheduled Ride
	 * @author: Diganta D
	 * @param: sTestCaseID
	 * @param: sData
	 */
	@Test(priority=1, enabled=true)
	public void testScheduledStatusRider(){
		sTestCaseID="UpcomingRides";
		sData= GenericLib.toReadExcelData(sTestCaseID);
		newUserRegPo = new NewUserRegPO(driver);
		qrProfilePo = new QRProfilePO(driver);
		ridesPo = new RidesPO(driver);
		feedbackPo=new FeedbackPO(driver);
		String sAmt = "500";
		try
		{
			newUserRegPo.signUPorLogin(sData[1], sData[2], sData[3]);
			feedbackPo.createRide(sData[4], sData[5], ridesPo);
			ridesPo.upcomingTab(qrProfilePo);
			Assert.assertTrue(ridesPo.getEleScheduledTxt().isDisplayed(), "Scheduled status is not displayed in upcoming for rider");
			qrLog.info("Scheduled status is displayed in upcoming tab for rider");
			ridesPo.getEleScheduledTxt().click();
			ridesPo.getEleCancelBtn().click();
			ridesPo.getEleYesBtn().click();
			qrProfilePo.logout();
			GenericLib.setDBdata(GenericLib.getCongigValue(sConfigFile, "ACCOUNT"), "balance", sAmt, "id",sData[1]);
			GenericLib.setDBdata(GenericLib.getCongigValue(sConfigFile, "ACCOUNT"), "balance", sAmt, "id",sData[6]);
		}
		catch(Exception e){
			qrLog.error("Exception in  case testScheduledStatusRider");
			e.printStackTrace();
			Assert.fail();
		}
	}
	/*
	 * @Description: MyRides display Upcoming Scheduled Ride(when passenger joins ride)
	 * @author: Diganta D
	 * @param: sTestCaseID
	 * @param: sData
	 */
	@Test(priority=2, enabled=true)
	public void testScheduledStatusPassenger(){
		sTestCaseID="UpcomingRides";
		sData= GenericLib.toReadExcelData(sTestCaseID);
		newUserRegPo = new NewUserRegPO(driver);
		qrProfilePo = new QRProfilePO(driver);
		ridesPo = new RidesPO(driver);
		feedbackPo=new FeedbackPO(driver);
		try
		{
			//rider login
			newUserRegPo.signUPorLogin(sData[1], sData[2], sData[3]);
			feedbackPo.createRide(sData[4], sData[5], ridesPo);
			qrProfilePo.logout();
			//passenger login
			newUserRegPo.signUPorLogin(sData[6], sData[7], sData[8]);
			feedbackPo.joinRide(sData[9], sData[10], sData[3]);
			qrProfilePo.logout();
			//rider login
			newUserRegPo.login(sData[1], sData[2]);
			feedbackPo.acceptRide();
			qrProfilePo.logout();
			//passenger login
			newUserRegPo.login(sData[6], sData[7]);
			ridesPo.getEleBackArrowIcon().click();
			ridesPo.upcomingTab(qrProfilePo);
			Assert.assertTrue(ridesPo.getEleScheduledTxt().isDisplayed(), "Scheduled status is not displayed in upcoming for passenger");
			qrLog.info("Scheduled status is displayed in upcoming tab for passenger");
			ridesPo.getEleScheduledTxt().click();
			ridesPo.getEleCancelBtn().click();
			ridesPo.getEleAlertYesBtn().click();
			qrProfilePo.logout();
			//rider login
			newUserRegPo.login(sData[1], sData[2]);
			ridesPo.getEleStopBtn().click();
			ridesPo.getEleYesBtn().click();
			qrProfilePo.logout();
		}
		catch(Exception e)
		{
			qrLog.error("Exception in  case testScheduledStatusPassenger");
			e.printStackTrace();
			Assert.fail();
		}
	}
	/*
	 * @Description: MyRides display Upcoming Started Ride
	 * @author: Diganta D
	 * @param: sTestCaseID
	 * @param: sData
	 */
	@Test(priority=3, enabled=true)
	public void testStartedStatusRider(){
		sTestCaseID="UpcomingRides";
		sData= GenericLib.toReadExcelData(sTestCaseID);
		newUserRegPo = new NewUserRegPO(driver);
		qrProfilePo = new QRProfilePO(driver);
		ridesPo = new RidesPO(driver);
		try
		{
			newUserRegPo.signUPorLogin(sData[1], sData[2], sData[3]);
			ridesPo.rideNow(sData[4], sData[5]);
			ridesPo.getEleOfferRideTab().click();
			ridesPo.getEleOfferRideBtn().click();
			ridesPo.getEleStartBtn().click();
			ridesPo.getEleYesBtn().click();
			ridesPo.getEleBackArrowIcon().click();
			ridesPo.upcomingTab(qrProfilePo);
			Assert.assertTrue(ridesPo.getEleStartedTxt().isDisplayed(), "Started status is not displayed in upcoming for rider");
			qrLog.info("Started status is displayed in upcoming for rider");
			ridesPo.getEleStartedTxt().click();
			ridesPo.getEleStopBtn().click();
			ridesPo.getEleYesBtn().click();
			qrProfilePo.logout();
		}
		catch(Exception e)
		{
			qrLog.error("Exception in  case testStartedStatusRider");
			e.printStackTrace();
			Assert.fail();
		}
	}
	/*
	 * @Description: MyRides display Upcoming Started Ride(when passenger joins ride)
	 * @author: Diganta D
	 * @param: sTestCaseID
	 * @param: sData
	 */
	@Test(priority=4, enabled=true)
	public void testStartedStatusPassenger(){
		sTestCaseID="UpcomingRides";
		sData= GenericLib.toReadExcelData(sTestCaseID);
		newUserRegPo = new NewUserRegPO(driver);
		qrProfilePo = new QRProfilePO(driver);
		ridesPo = new RidesPO(driver);
		feedbackPo=new FeedbackPO(driver);
		try
		{	
			//rider login
			newUserRegPo.signUPorLogin(sData[1], sData[2], sData[3]);
			feedbackPo.createRide(sData[4], sData[5], ridesPo);
			qrProfilePo.logout();
			//passenger login
			newUserRegPo.signUPorLogin(sData[6], sData[7], sData[8]);
			feedbackPo.joinRide(sData[9], sData[10], sData[3]);
			qrProfilePo.logout();
			//rider login
			newUserRegPo.login(sData[1], sData[2]);
			feedbackPo.acceptRide();
			ridesPo.upcomingTab(qrProfilePo);
			Assert.assertTrue(ridesPo.getEleStartedTxt().isDisplayed(), "Started status is not displayed in upcoming tab for rider");
			qrLog.info("Started status is displayed in upcoming tab for rider");
			qrProfilePo.logout();
			//passenger login
			newUserRegPo.login(sData[6], sData[7]);
			feedbackPo.checkInRide();
			feedbackPo.getEleArrowIcn().click();
			ridesPo.upcomingTab(qrProfilePo);
			Assert.assertTrue(ridesPo.getEleStartedTxt().isDisplayed(), "Started status is not displayed in upcoming tab for passenger");
			qrLog.info("Started status is displayed in upcoming tab for passenger");
			ridesPo.getEleStartedTxt().click();
			feedbackPo.checkOutRide();
			ridesPo.getEleNextBtn().click();
			ridesPo.getEleSubmitBtn().click();
			qrProfilePo.logout();
			
			//Ride Cancellation code - rider login to stop ride.
			newUserRegPo.login(sData[1], sData[2]);
			ridesPo.getEleStopBtn().click();
			ridesPo.getEleYesBtn().click();
			ridesPo.getEleNextBtn().click();
			ridesPo.getEleSubmitBtn().click();
			qrProfilePo.logout();
		}
		catch(Exception e){
			qrLog.error("Exception in  case testStartedStatusPassenger");
			e.printStackTrace();
			Assert.fail();
		}
	}
	/*
	 * @Description: MyRides display Upcoming Requested Ride
	 * @author: Diganta D
	 * @param: sTestCaseID
	 * @param: sData
	 */
	@Test(priority=5, enabled=true)
	public void testRequestedStatusRider(){
		sTestCaseID="UpcomingRides";
		sData= GenericLib.toReadExcelData(sTestCaseID);
		newUserRegPo = new NewUserRegPO(driver);
		qrProfilePo = new QRProfilePO(driver);
		ridesPo = new RidesPO(driver);
		try
		{
			newUserRegPo.signUPorLogin(sData[1], sData[2], sData[3]);
			ridesPo.rideNow(sData[4], sData[5]);
			ridesPo.getEleFindRideTab().click();
			ridesPo.getEleRequestRideBtn().click();
			ridesPo.getEleBackArrowIcon().click();
			ridesPo.upcomingTab(qrProfilePo);
			Assert.assertTrue(ridesPo.getEleRequestedTxt().isDisplayed(), "Requested status is not displayed in upcoming for passenger");
			qrLog.info("Requested status is displayed in upcoming for passenger");
			ridesPo.getEleRequestedTxt().click();
			ridesPo.getEleCancelBtn().click();
			ridesPo.getEleYesBtn().click();
			qrProfilePo.logout();
			
		}
		catch(Exception e){
			qrLog.error("Exception in  case testRequestedStatusRider");
			e.printStackTrace();
			Assert.fail();
		}
	}
	/*
	 * @Description: MyRides display Upcoming Requested Ride(when passenger joins ride)
	 * @author: Diganta D
	 * @param: sTestCaseID
	 * @param: sData
	 */
	@Test(priority=6, enabled=true)
	public void testRequestedStatusPassenger(){
		sTestCaseID="UpcomingRides";
		sData= GenericLib.toReadExcelData(sTestCaseID);
		newUserRegPo = new NewUserRegPO(driver);
		qrProfilePo = new QRProfilePO(driver);
		ridesPo = new RidesPO(driver);
		feedbackPo=new FeedbackPO(driver);
		try
		{
			//rider login		
			newUserRegPo.signUPorLogin(sData[1], sData[2], sData[3]);
			feedbackPo.createRide(sData[4], sData[5], ridesPo);
			qrProfilePo.logout();
			//passenger login
			newUserRegPo.signUPorLogin(sData[6], sData[7], sData[8]);
			feedbackPo.joinRide(sData[9], sData[10], sData[3]);
			ridesPo.upcomingTab(qrProfilePo);
			Assert.assertTrue(ridesPo.getEleRequestedTxt().isDisplayed(), "Requested status is not displayed in upcoming for passenger");
			qrLog.info("Requested status is displayed in upcoming for passenger");
			ridesPo.getEleRequestedTxt().click();
			ridesPo.getEleCancelBtn().click();
			ridesPo.getEleYesBtn().click();
			qrProfilePo.logout();
			//rider login
			newUserRegPo.login(sData[1], sData[2]);
			ridesPo.getEleCancelBtn().click();
			ridesPo.getEleYesBtn().click();
			qrProfilePo.logout();
		}
		catch(Exception e)
		{
			qrLog.error("Exception in  case testRequestedStatusPassenger");
			e.printStackTrace();
			Assert.fail();
		}
	}
	/*
	 * @Description: MyRides display Completed Ride
	 * @author: Diganta D
	 * @param: sTestCaseID
	 * @param: sData
	 */
	@Test(priority=7, enabled=true)
	public void testCompletedStatusRider(){
		sTestCaseID="CompletedRides";
		sData= GenericLib.toReadExcelData(sTestCaseID);
		newUserRegPo = new NewUserRegPO(driver);
		qrProfilePo = new QRProfilePO(driver);
		ridesPo = new RidesPO(driver);
		String sAmt = "500";
		try
		{
			newUserRegPo.signUPorLogin(sData[1], sData[2], sData[3]);
			ridesPo.rideNow(sData[4], sData[5]);
			ridesPo.getEleOfferRideTab().click();
			ridesPo.getEleOfferRideBtn().click();
			ridesPo.getEleStartBtn().click();
			ridesPo.getEleYesBtn().click();
			ridesPo.getEleStopBtn().click();
			ridesPo.getEleYesBtn().click();
			ridesPo.completedTab(qrProfilePo);
			Assert.assertTrue(ridesPo.getEleCompletedTxt().isDisplayed(), "Completed status is not displayed in completed for rider");
			qrLog.info("Completed status is displayed in completed for rider");
			qrProfilePo.logout();
			GenericLib.setDBdata(GenericLib.getCongigValue(sConfigFile, "ACCOUNT"), "balance", sAmt, "id",sData[1]);
			GenericLib.setDBdata(GenericLib.getCongigValue(sConfigFile, "ACCOUNT"), "balance", sAmt, "id",sData[6]);
			
		}catch(Exception e)
		{
			qrLog.error("Exception in  case testCompletedStatusRider");
			e.printStackTrace();
			Assert.fail();
		}
	}
	/*
	 * @Description: MyRides display Completed Ride(when passenger joins ride)
	 * @author: Diganta D
	 * @param: sTestCaseID
	 * @param: sData
	 */
	@Test(priority=8, enabled=true)
	public void testCompletedStatusPassenger(){
		sTestCaseID="CompletedRides";
		sData= GenericLib.toReadExcelData(sTestCaseID);
		newUserRegPo = new NewUserRegPO(driver);
		qrProfilePo = new QRProfilePO(driver);
		ridesPo = new RidesPO(driver);
		feedbackPo=new FeedbackPO(driver);
		try
		{
			//rider login
			newUserRegPo.signUPorLogin(sData[1], sData[2],sData[3]);
			feedbackPo.createRide(sData[4], sData[5], ridesPo);
			qrProfilePo.logout();
			//passenger login
			newUserRegPo.signUPorLogin(sData[6], sData[7], sData[8]);
			feedbackPo.joinRide(sData[9], sData[10], sData[3]);
			qrProfilePo.logout();
			//rider login
			newUserRegPo.login(sData[1], sData[2]);
			feedbackPo.acceptRide();
			qrProfilePo.logout();
			//passenger login
			newUserRegPo.login(sData[6], sData[7]);
			feedbackPo.checkInRide();
			feedbackPo.checkOutRide();
			ridesPo.getEleNextBtn().click();
			ridesPo.getEleSubmitBtn().click();
			ridesPo.completedTab(qrProfilePo);
			Assert.assertTrue(ridesPo.getEleCompletedTxt().isDisplayed(), "Completed status is not displayed in completed for passenger");
			qrLog.info("Completed status is displayed in completed for passenger");
			qrProfilePo.logout();
			//rider login
			newUserRegPo.login(sData[1], sData[2]);
			ridesPo.getEleStopBtn().click();
			ridesPo.getEleYesBtn().click();
			ridesPo.getEleNextBtn().click();
			ridesPo.getEleSubmitBtn().click();
			ridesPo.completedTab(qrProfilePo);
			Assert.assertTrue(ridesPo.getEleCompletedTxt().isDisplayed(), "Completed status is not displayed in completed for rider");
			qrLog.info("Completed status is displayed in completed for rider");
			qrProfilePo.logout();
		}
		catch(Exception e)
		{
			qrLog.error("Exception in  case testCompletedStatusPassenger");
			e.printStackTrace();
			Assert.fail();
		}
	}
	/*
	 * @Description: MyRides display Cancelled Ride(when passenger joins ride)
	 * @author: Diganta D
	 * @param: sTestCaseID
	 * @param: sData
	 */
	@Test(priority=9, enabled=true)
	public void testCancelledStsRider(){
		sTestCaseID="CompletedRides";
		sData= GenericLib.toReadExcelData(sTestCaseID);
		newUserRegPo = new NewUserRegPO(driver);
		qrProfilePo = new QRProfilePO(driver);
		ridesPo = new RidesPO(driver);
		try
		{
			newUserRegPo.signUPorLogin(sData[1], sData[2], sData[3]);
			ridesPo.rideNow(sData[4], sData[5]);
			ridesPo.getEleOfferRideTab().click();
			ridesPo.getEleOfferRideBtn().click();
			ridesPo.getEleCancelBtn().click();
			ridesPo.getEleYesBtn().click();
			ridesPo.completedTab(qrProfilePo);
			Assert.assertTrue(ridesPo.getEleCompletedTxt().isDisplayed(), "Cancelled status is not displayed in completed for rider");
			qrLog.info("Completed status is displayed in completed");
			qrProfilePo.logout();
		}catch(Exception e)
		{
			qrLog.error("Exception in  case testCancelledStsRider");
			e.printStackTrace();
			Assert.fail();
		}
	}
	/*
	 * @Description: MyRides display Cancelled Ride(when passenger joins ride)
	 * @author: Diganta D
	 * @param: sTestCaseID
	 * @param: sData
	 */
	@Test(priority=10, enabled=true)
	public void testCancelledStsPassenger(){
		sTestCaseID="CompletedRides";
		sData= GenericLib.toReadExcelData(sTestCaseID);
		newUserRegPo = new NewUserRegPO(driver);
		qrProfilePo = new QRProfilePO(driver);
		ridesPo = new RidesPO(driver);
		feedbackPo=new FeedbackPO(driver);
		try
		{	
			//rider login
			newUserRegPo.signUPorLogin(sData[1], sData[2], sData[3]);
			feedbackPo.createRide(sData[4], sData[5], ridesPo);
			qrProfilePo.logout();
			//passenger login
			newUserRegPo.signUPorLogin(sData[6], sData[7], sData[8]);
			ridesPo.rideNow(sData[9], sData[10]);
			ridesPo.getEleFindRideTab().click();
			while(true)
			{
				if(driver.findElement(By.name(sData[3])).isDisplayed())
				{
					driver.findElement(By.name(sData[3])).click();
					break;
				}
			}
			ridesPo.getEleJoinBtn().click();
			ridesPo.getEleCancelBtn().click();
			ridesPo.getEleYesBtn().click();
			ridesPo.completedTab(qrProfilePo);
			Assert.assertTrue(ridesPo.getEleCancelledTxt().isDisplayed(), "Cancelled status is not displayed for passenger");
			qrLog.info("Cancelled status is displayed for passenger");
			qrProfilePo.logout();
			//rider login
			newUserRegPo.login(sData[1], sData[2]);
			ridesPo.getEleCancelBtn().click();
			qrProfilePo.logout();
		}
		catch(Exception e)
		{
			qrLog.error("Exception in  case testCancelledStsPassenger");
			e.printStackTrace();
			Assert.fail();
		}
	}
	/*
	 * @Description: MyRides display Cancelled Ride(when passenger joins ride)
	 * @author: Diganta D
	 * @param: sTestCaseID
	 * @param: sData
	 */
	@Test(priority=11, enabled=true)   // To be clarified
	public void testPassengerCancelStatus(){
		sTestCaseID="CompletedRides";
		sData= GenericLib.toReadExcelData(sTestCaseID);
		newUserRegPo = new NewUserRegPO(driver);
		qrProfilePo = new QRProfilePO(driver);
		ridesPo = new RidesPO(driver);
		feedbackPo=new FeedbackPO(driver);
		String sRiderName=sData[3];
		try
		{
			//rider login
			newUserRegPo.signUPorLogin(sData[1], sData[2], sData[3]);
			feedbackPo.createRide(sData[4], sData[5], ridesPo);
			qrProfilePo.logout();
			//passenger login
			newUserRegPo.signUPorLogin(sData[6], sData[7], sData[8]);
			ridesPo.rideNow(sData[9], sData[10]);
			ridesPo.getEleFindRideTab().click();
			while(true)
			{
				if(driver.findElement(By.name(sRiderName)).isDisplayed())
				{
					driver.findElement(By.name(sRiderName)).click();
					break;
				}
			}
			ridesPo.getEleJoinBtn().click();
			ridesPo.getEleCancelBtn().click();
			ridesPo.getEleYesBtn().click();
			ridesPo.completedTab(qrProfilePo);
			
			qrProfilePo.logout();
			//rider login
			newUserRegPo.login(sData[1], sData[2]);
			ridesPo.getEleBackArrowIcon().click();
			ridesPo.getEleNotificationIcn().click();
			while(true)
			{
				if(ridesPo.getEleAcceptLnk().isDisplayed())
				{
					ridesPo.getEleAcceptLnk().click();
					break;
				}
			}
			ridesPo.getEleBackArrowIcon().click();
			qrProfilePo.logout();
			//passenger login
			newUserRegPo.login(sData[6], sData[7]);
			ridesPo.getEleCancelBtn().click();
			ridesPo.getEleYesBtn().click();
			ridesPo.completedTab(qrProfilePo);
			Assert.assertTrue(ridesPo.getEleCancelledTxt().isDisplayed(), "Cancelled status is not displayed for passenger");
			qrLog.info("Cancelled status is displayed for passenger");
			qrProfilePo.logout();
			//rider login
			newUserRegPo.login(sData[1], sData[2]);
			ridesPo.getEleBackArrowIcon().click();
			qrProfilePo.logout();
		}
		catch(Exception e)
		{
			qrLog.error("Exception in  case testPassengerCancelStatus");
			e.printStackTrace();
			Assert.fail();
	}
	}
	/*
	 * @Description: MyRides display Cancelled Ride(when passenger joins ride)
	 * @author: Diganta D
	 * @param: sTestCaseID
	 * @param: sData
	 */
	@Test(priority=12, enabled=true)
	public void testRiderAcceptsPassengerCancelsSts(){
		sTestCaseID="CompletedRides";
		sData= GenericLib.toReadExcelData(sTestCaseID);
		newUserRegPo = new NewUserRegPO(driver);
		qrProfilePo = new QRProfilePO(driver);
		ridesPo = new RidesPO(driver);
		feedbackPo=new FeedbackPO(driver);
		String sRiderName=sData[3];
		try
		{
			//rider login
			newUserRegPo.signUPorLogin(sData[1], sData[2], sData[3]);
			feedbackPo.createRide(sData[4], sData[5], ridesPo);
			qrProfilePo.logout();
			//passenger login
			newUserRegPo.signUPorLogin(sData[6], sData[7], sData[8]);
			ridesPo.rideNow(sData[9], sData[10]);
			ridesPo.getEleFindRideTab().click();
			while(true)
			{
				if(driver.findElement(By.name(sRiderName)).isDisplayed())
				{
					driver.findElement(By.name(sRiderName)).click();
					break;
				}
			}
			ridesPo.getEleJoinBtn().click();
			ridesPo.getEleBackArrowIcon().click();
			qrProfilePo.logout();
			//rider login
			newUserRegPo.login(sData[1], sData[2]);
			ridesPo.getEleBackArrowIcon().click();
			ridesPo.getEleNotificationIcn().click();
			while(true)
			{
				if(ridesPo.getEleAcceptLnk().isDisplayed())
				{
					ridesPo.getEleAcceptLnk().click();
					break;
				}
			}
			ridesPo.getEleStartBtn().click();
			ridesPo.getEleYesBtn().click();
			ridesPo.getEleBackArrowIcon().click();
			qrProfilePo.logout();
			//passenger login
			newUserRegPo.login(sData[6], sData[7]);
			ridesPo.getEleCancelBtn().click();
			ridesPo.getEleYesBtn().click();
			ridesPo.completedTab(qrProfilePo);
			Assert.assertTrue(ridesPo.getEleCancelledTxt().isDisplayed(), "Cancelled status is not displayed");
			qrLog.info("Cancelled status is displayed");
			qrProfilePo.logout();
		}
		catch(Exception e)
		{
			qrLog.error("Exception in  case testRiderAcceptsPassengerCancelsSts");
			
			e.printStackTrace();
			Assert.fail();
	}
}
}
