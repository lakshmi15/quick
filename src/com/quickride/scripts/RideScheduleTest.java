/***********************************************************************
* @author 			:		DIGANTA D
* @description		: 		Test scripts of New User Registration module.
* @module			:		Ride Schedule
* @testmethod		:		testOffrRdCurrentLoc()
* @testmethod		:		testReqstRdCurrentLoc()
* @testmethod		:		testRdrRejectsPassngrSrchRider()
* @testmethod 		:		testRdrRejectsPassngr()
* @testmethod		:		testUpcomingRequested()
* @testmethod		:		testSelectRide()
* @testmethod 		:		testMoreCarCapacitySeats()
* @testmethod		:		testUpcomingRequested()
* @testmethod		:		testCarCapacityCancelRide()
* 
*
*/

package com.quickride.scripts;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.quickride.PO.FeedbackPO;
import com.quickride.PO.MyProfilePO;
import com.quickride.PO.MyRewardsPO;
import com.quickride.PO.NewUserRegPO;
import com.quickride.PO.QRProfilePO;
import com.quickride.PO.RidesPO;
import com.quickride.baselib.GenericLib;
import com.quickride.baselib.QRBaseLib;

public class RideScheduleTest extends QRBaseLib
{
	public Logger qrLog = Logger.getLogger(this.getClass());
	public String sData[]=null;
	public String sTestCaseID=null;
	NewUserRegPO newUserRegPo;
	QRProfilePO qrProfilePo;
	RidesPO ridesPo;
	FeedbackPO feedbackPo;
	MyRewardsPO myRewards;
	MyProfilePO myProfilePo;
	/*
	 * @Description: Rider creating a Ride Immediately from current location, with no passengers matched.
	 * @author: Diganta D
	 * @param: sTestCaseID
	 * @param: sData
	 */
	@Test(priority=1, enabled=true)
	public void testOffrRdCurrentLoc(){
		sTestCaseID="RideSched_01";
		sData= GenericLib.toReadExcelData(sTestCaseID);
		newUserRegPo = new NewUserRegPO(driver);
		qrProfilePo = new QRProfilePO(driver);
		ridesPo = new RidesPO(driver);	
		try
		{	
			//rider login
			newUserRegPo.signUPorLogin(sData[1], sData[2], sData[3]);
			ridesPo.offerRideFrmCurrLoc(sData[5]);
			ridesPo.getEleBackArrowIcon().click();
			qrProfilePo.logout();
			//passenger login
			newUserRegPo.signUPorLogin(sData[6], sData[7], sData[8]);
			ridesPo.reqstRideFrmCurrentLoc(sData[10]);
			ridesPo.getEleFindRideTab().click();
			Assert.assertTrue(driver.findElement(By.name(sData[3])).isDisplayed(), "Rider created the ride is not visible in Find Ride");
			qrLog.info("Rider created the ride is visible in Find Ride");
			ridesPo.getEleRidrBackArrwIcn().click();
			qrProfilePo.logout();
			//rider login
			newUserRegPo.login(sData[1], sData[2]);
			ridesPo.getEleCancelBtn().click();
			ridesPo.getEleYesBtn().click();
			qrProfilePo.logout();
		}
		catch(Exception e)
		{
			qrLog.error("Exception in  case testCompletedRideInCompleted");
			e.printStackTrace();
			Assert.fail();
		}
	}
	/*
	 * @Description: Passenger looking for a Ride immediately from current location and Scheduling it dont get any options,Create Ride Request.
	 * @author: Diganta D
	 * @param: sTestCaseID
	 * @param: sData
	 */
	@Test(priority=2, enabled=true)
	public void testReqstRdCurrentLoc(){
		sTestCaseID="RideSched_01";
		sData= GenericLib.toReadExcelData(sTestCaseID);
		newUserRegPo = new NewUserRegPO(driver);
		qrProfilePo = new QRProfilePO(driver);
		ridesPo = new RidesPO(driver);
		String passenger=sData[8];
		try
		{
			//passenger login
			newUserRegPo.signUPorLogin(sData[6], sData[7], sData[8]);
			ridesPo.reqstRideFrmCurrentLoc(sData[10]);
			ridesPo.getEleBackArrowIcon().click();
			qrProfilePo.logout();
			//rider login
			newUserRegPo.signUPorLogin(sData[1], sData[2], sData[3]);
			ridesPo.reqstRideFrmCurrentLoc(sData[10]);
			ridesPo.getEleOfferRideTab().click();
			Assert.assertTrue(driver.findElement(By.name(passenger)).isDisplayed(), "Passenger requested ride is not displayed");
			qrLog.info("Passenger requested ride is displayed");
			ridesPo.getEleRidrBackArrwIcn().click();
			qrProfilePo.logout();
			//passenger login
			newUserRegPo.login(sData[6], sData[7]);
			ridesPo.getEleCancelBtn().click();
			ridesPo.getEleYesBtn().click();
			qrProfilePo.logout();
			
		}
		catch(Exception e)
		{
			qrLog.error("Exception in  case testCompletedRideInCompleted");
			e.printStackTrace();
			Assert.fail();
		}
	}
	/*
	 * @Description: Passenger looking for ride, and send join request, Rider rejects and Passenger to get other Rider option.
	 * @author: Diganta D
	 * @param: sTestCaseID
	 * @param: sData
	 */
	@Test(priority=3, enabled=true)
	public void testRdrRejectsPassngrSrchRider(){
		sTestCaseID="RideSched_02";
		sData= GenericLib.toReadExcelData(sTestCaseID);
		newUserRegPo = new NewUserRegPO(driver);
		qrProfilePo = new QRProfilePO(driver);
		ridesPo = new RidesPO(driver);
		feedbackPo=new FeedbackPO(driver);
		String rider1=sData[3];
		String rider2=sData[13];
		try
		{
			//rider1 login
			newUserRegPo.signUPorLogin(sData[1], sData[2], sData[3]);
			feedbackPo.createRide(sData[4], sData[5], ridesPo);
			qrProfilePo.logout();
			//rider2 login
			newUserRegPo.signUPorLogin(sData[11], sData[12], sData[13]);
			feedbackPo.createRide(sData[14], sData[15], ridesPo);
			qrProfilePo.logout();
			//passenger login
			newUserRegPo.signUPorLogin(sData[6], sData[7], sData[8]);
			feedbackPo.joinRide(sData[9], sData[10], rider1);
			qrProfilePo.logout();
			//rider1 login
			newUserRegPo.login(sData[1], sData[2]);
			feedbackPo.getEleArrowIcn().click();
			feedbackPo.getEleRejectLnk().click();
			ridesPo.getEleNotiBackArrowIcn().click();
			ridesPo.getEleCancelBtn().click();
			ridesPo.getEleYesBtn().click();
			qrProfilePo.logout();
			//passenger login
			newUserRegPo.login(sData[6], sData[7]);
			ridesPo.getEleFindRiderIcn().click();
			Assert.assertTrue(driver.findElement(By.name(rider2)).isDisplayed(), "Second rider is not displayed");
			qrLog.info("Second rider is displayed");
			ridesPo.getEleRidrBackArrwIcn().click();
			ridesPo.getEleCancelBtn().click();
			ridesPo.getEleYesBtn().click();
			qrProfilePo.logout();
			//rider2 login
			newUserRegPo.login(sData[11], sData[12]);
			ridesPo.getEleCancelBtn().click();
			ridesPo.getEleYesBtn().click();
			qrProfilePo.logout();
		}
		catch(Exception e)
		{
			qrLog.error("Exception in  case testRdrRejectsPassngrSrchRider");
			e.printStackTrace();
			Assert.fail();
		}
	}
	/*
	 * @Description: Passenger looking for ride, and send join request, Rider rejects and Passenger to get other Rider option. If there are no Riders available, Ride request is still in Pending state.
	 * @author: Diganta D
	 * @param: sTestCaseID
	 * @param: sData
	 */
	@Test(priority=4, enabled=true)
	public void testRdrRejectsPassngr(){
		sTestCaseID="RideSched_02";
		sData= GenericLib.toReadExcelData(sTestCaseID);
		newUserRegPo = new NewUserRegPO(driver);
		qrProfilePo = new QRProfilePO(driver);
		ridesPo = new RidesPO(driver);
		feedbackPo=new FeedbackPO(driver);
		String rider1=sData[3];
		String rider2=sData[13];
		try
		{
			//rider1 login
			newUserRegPo.signUPorLogin(sData[1], sData[2], sData[3]);
			feedbackPo.createRide(sData[4], sData[5], ridesPo);
			qrProfilePo.logout();
			//passenger login
			newUserRegPo.signUPorLogin(sData[6], sData[7], sData[8]);
			feedbackPo.joinRide(sData[9], sData[10], rider1);
			qrProfilePo.logout();
			//rider1 login
			newUserRegPo.login(sData[1], sData[2]);
			feedbackPo.getEleArrowIcn().click();
			feedbackPo.getEleRejectLnk().click();
			ridesPo.getEleNotiBackArrowIcn().click();
			feedbackPo.getEleArrowIcn().click();
			ridesPo.getEleCancelBtn().click();
			ridesPo.getEleYesBtn().click();
			qrProfilePo.logout();
			//passenger login
			newUserRegPo.login(sData[6], sData[7]);
			ridesPo.getEleFindRiderIcn().click();
			Assert.assertFalse(driver.findElement(By.name(rider2)).isDisplayed(), "Rider2 is not displayed");
			qrLog.info("Rider2 is displayed");
			ridesPo.getEleRidrBackArrwIcn().click();
			ridesPo.getEleBackArrowIcon().click();
			ridesPo.getEleUpcomingTab();
			Assert.assertTrue(ridesPo.getEleRequestedTxt().isDisplayed(), "Requested status is not displayed");
			qrLog.info("Requested status is displayed");
			ridesPo.getEleRequestedTxt().click();
			ridesPo.getEleCancelBtn().click();
			ridesPo.getEleYesBtn().click();
			qrProfilePo.logout();
		}
		catch(Exception e)
		{
			qrLog.error("Exception in  case testRdrRejectsPassngr");
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test(priority=5, enabled=true)
	public void testUpcomingRequested(){
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
			ridesPo.getEleRideActionOverflow().click();
			ridesPo.getEleReturnRideOptn().click();
			ridesPo.getEleCancelBtn().click();
			ridesPo.getEleYesBtn().click();
			qrProfilePo.logout();
			driver.resetApp();
		}
		catch(Exception e){
			qrLog.error("Exception in  case testUpcomingRequested1");
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	/*
	 * @Description: Test Creation of Favourite Location for Home and Office , Test Deleting a Favourite location 
	 * @author: Lakshmi BS
	 * @param: sTestCaseID
	 * @param: sData
	 */
	@Test(priority=6, enabled=true)
	public void testMyRidesFavourite(){
		sTestCaseID="RideSched_06";
		sData= GenericLib.toReadExcelData(sTestCaseID);
		newUserRegPo = new NewUserRegPO(driver);
		qrProfilePo = new QRProfilePO(driver);
		ridesPo = new RidesPO(driver);
		try
		{
			newUserRegPo.signUPorLogin(sData[1], sData[2], sData[3]);
			ridesPo.getEleFavouriteIcn().click();
			ridesPo.getEleAddBtn().click();
			ridesPo.getEleNameFavTxtFld().sendKeys(sData[4]);
			ridesPo.getEleSaveLnk().click();
			ridesPo.getEleAddBtn().click();
			ridesPo.getEleNameFavTxtFld().sendKeys(sData[5]);
			ridesPo.getEleFavAddTxt().click();
			ridesPo.getEleEntrAddrssTxtField().sendKeys(sData[6]);
			ridesPo.getEleFirstSrcOptn().click();
			ridesPo.getEleSaveLnk().click();
			Assert.assertTrue(driver.findElement(By.name(sData[4])).isDisplayed(), "Home favourite is not displayed in Favourites page");
			qrLog.info("Home favourite is not displayed in Favourites page");
			
			Assert.assertTrue(driver.findElement(By.name(sData[5])).isDisplayed(), "Office favourite is not displayed in Favourites page");
			qrLog.info("Office favourite is not displayed in Favourites page");
			
			while(ridesPo.getEleFavCancelIcn().isDisplayed())
			{
				ridesPo.getEleFavCancelIcn().click();
				ridesPo.getEleYesBtn().click();
				
			}
			
			Assert.assertTrue(ridesPo.getEleFavouritesTxt().isDisplayed(), "Favourite page is not displayed in Favourites page");
			qrLog.info("Favourite page  is not displayed in Favourites page");
			ridesPo.getEleAddBtn().click();
			ridesPo.getEleNameFavTxtFld().sendKeys(sData[4]);
			ridesPo.getEleSaveLnk().click();
			Assert.assertTrue(driver.findElement(By.name(sData[4])).isDisplayed(), "Home favourite is not displayed in Favourites page");
			qrLog.info("Home favourite is not displayed in Favourites page");
			ridesPo.getEleFavCancelIcn().click();
			ridesPo.getEleYesBtn().click();
			ridesPo.getEleFavBackIcn().click();
			qrProfilePo.logout();
			
		}
		catch(Exception e){
			qrLog.error("Exception in  case testUpcomingRequested1");
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	/*
	 * @Description:Passenger selecting Rider, but Passenger account dont have enough balance
	 * @author: Raghukiran MR
	 * @param: sTestCaseID
	 * @param: sData
	 */
	@Test(priority=7, enabled=true)
	public void testSelectRide(){
		sTestCaseID="RideSched_07";
		sData= GenericLib.toReadExcelData(sTestCaseID);
		newUserRegPo = new NewUserRegPO(driver);
		qrProfilePo = new QRProfilePO(driver);
		ridesPo = new RidesPO(driver);	
		myRewards = new MyRewardsPO(driver);
		feedbackPo=new FeedbackPO(driver);
		try{
			//UserA create the ride
			newUserRegPo.signUPorLogin(sData[1], sData[2], sData[3]);
			feedbackPo.createRide(sData[4],sData[5], ridesPo);
			qrProfilePo.logout();
			//UserB join the ride 
			newUserRegPo.signUPorLogin(sData[6], sData[7], sData[8]);
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
			Assert.assertTrue(myRewards.getEleRechargeBtn().isDisplayed(), "Rechargebutton page is not displayed");
			qrLog.info("Rechargebutton page is displayed");
			}catch(Exception e){
			qrLog.error("Exception in  case testSelectRide");
			e.printStackTrace();
			Assert.fail();
}
}
	/*
	 * @Description:Rider Scheduling Ride with passengers selected more than car capacity 
	 * @author: Raghukiran MR
	 * @param: sTestCaseID
	 * @param: sData
	 */
	@Test(priority=8, enabled=true)
	public void testMoreCarCapacitySeats(){
		sTestCaseID="RideSched_08";
		sData= GenericLib.toReadExcelData(sTestCaseID);
		newUserRegPo = new NewUserRegPO(driver);
		qrProfilePo = new QRProfilePO(driver);
		ridesPo = new RidesPO(driver);	
		myRewards = new MyRewardsPO(driver);
		feedbackPo=new FeedbackPO(driver);
		myProfilePo = new MyProfilePO(driver);
		try{
			//UserA create the ride
			newUserRegPo.signUPorLogin(sData[1], sData[2], sData[3]);
			Assert.assertTrue(myProfilePo.getEleArrowBtn().isDisplayed(), "Profile icon is not displayed");
			qrLog.info("Profile icon is displayed");
			myProfilePo.getEleArrowBtn().click();
			myProfilePo.getEleEditLnk().click();
			driver.scrollTo("Ride Preferences");
			myProfilePo.getEleVehicleDetailsLnk().click();
			myProfilePo.getEleOfferdSeatsTxtFld().clear();
			myProfilePo.getEleOfferdSeatsTxtFld().sendKeys(sData[12]);
			feedbackPo.createRide(sData[4],sData[5], ridesPo);
			qrProfilePo.logout();
			//UserB join the ride 
			newUserRegPo.signUPorLogin(sData[6], sData[7], sData[8]);
			feedbackPo.joinRide(sData[4],sData[5],sData[3]);
			qrProfilePo.logout();
			//UserA accept the ride 
			newUserRegPo.getEleLoginBtn().click();
			newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[1]);
			newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[2]);
			newUserRegPo.getEleLoginBtn().click();
			feedbackPo.getEleArrowIcn().click();
			feedbackPo.getEleNotificationIcon().click();
			feedbackPo.getEleAcceptLnk().click();
			feedbackPo.getEleArrowIcn().click();
			feedbackPo.getEleArrowIcn().click();
			qrProfilePo.logout();
			//UserC join the ride
			newUserRegPo.signUPorLogin(sData[9], sData[10], sData[11]);
			feedbackPo.joinRide(sData[4],sData[5],sData[3]);
			qrProfilePo.logout();
			//UserA accept the ride
			newUserRegPo.getEleLoginBtn().click();
			newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[1]);
			newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[2]);
			newUserRegPo.getEleLoginBtn().click();
			feedbackPo.getEleArrowIcn().click();
			feedbackPo.getEleNotificationIcon().click();
			feedbackPo.getEleAcceptLnk().click();
			feedbackPo.getEleArrowIcn().click();
			feedbackPo.getEleArrowIcn().click();
			qrProfilePo.logout();
			//UserD search the ride
			newUserRegPo.signUPorLogin(sData[13], sData[14], sData[15]);
			feedbackPo.getEleSearchBtn().click();
			feedbackPo.getEleEnterAddTxtFld().sendKeys(sData[4]);
			feedbackPo.getEleFirstOption().click();
			feedbackPo.getEleRideNwBtn().click();
			feedbackPo.getEleEndAddressTxtFld().click();
			feedbackPo.getEleEnterAddTxtFld().sendKeys(sData[5]);
			feedbackPo.getEleFirstOption().click();
			Assert.assertTrue(feedbackPo.getEleFindRideTab().isDisplayed(), "Rider name is displayed");
			qrLog.info("Rider name is not displayed");
			feedbackPo.getEleFindRideTab().click();
		}catch(Exception e){
	qrLog.error("Exception in  case testCarCapacity");
	e.printStackTrace();
	Assert.fail();
}
	}
	/*
	 * @Description:If one passenger cancel the ride request that seat to be available for new passenger
	 * @author: Raghukiran MR
	 * @param: sTestCaseID
	 * @param: sData
	 */
	@Test(priority=9, enabled=true)
	public void testCarCapacityCancelRide(){
		sTestCaseID="RideSched_09";
		sData= GenericLib.toReadExcelData(sTestCaseID);
		newUserRegPo = new NewUserRegPO(driver);
		qrProfilePo = new QRProfilePO(driver);
		ridesPo = new RidesPO(driver);	
		myRewards = new MyRewardsPO(driver);
		feedbackPo=new FeedbackPO(driver);
		myProfilePo = new MyProfilePO(driver);
		try{
			//UserA create the ride
			newUserRegPo.signUPorLogin(sData[1], sData[2], sData[3]);
			Assert.assertTrue(myProfilePo.getEleArrowBtn().isDisplayed(), "Profile icon is not displayed");
			qrLog.info("Profile icon is displayed");
			myProfilePo.getEleArrowBtn().click();
			myProfilePo.getEleEditLnk().click();
			driver.scrollTo("Ride Preferences");
			myProfilePo.getEleVehicleDetailsLnk().click();
			myProfilePo.getEleOfferdSeatsTxtFld().clear();
			myProfilePo.getEleOfferdSeatsTxtFld().sendKeys(sData[12]);
			feedbackPo.createRide(sData[4],sData[5], ridesPo);
			qrProfilePo.logout();
			//UserB Join the ride
			newUserRegPo.signUPorLogin(sData[6], sData[7], sData[8]);
			feedbackPo.joinRide(sData[4],sData[5],sData[3]);
			qrProfilePo.logout();
			//UserA accept the ride
			newUserRegPo.getEleLoginBtn().click();
			newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[1]);
			newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[2]);
			newUserRegPo.getEleLoginBtn().click();
			feedbackPo.acceptRide();
			qrProfilePo.logout();
			//UserC join the ride
			newUserRegPo.signUPorLogin(sData[9], sData[10], sData[11]);
			feedbackPo.joinRide(sData[4],sData[5],sData[3]);
			qrProfilePo.logout();
			//UserA accept the ride
			newUserRegPo.getEleLoginBtn().click();
			newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[1]);
			newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[2]);
			newUserRegPo.getEleLoginBtn().click();
			feedbackPo.acceptRide();
			qrProfilePo.logout();
			//UserB cancel the ride
			newUserRegPo.getEleLoginBtn().click();
			newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[6]);
			newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[7]);
			newUserRegPo.getEleLoginBtn().click();
			feedbackPo.getEleCanelMenu().click();
			feedbackPo.getEleCancelbtn().click();
			feedbackPo.getEleYesBtn().click();
			qrProfilePo.logout();
			//UserD Join the ride 
			newUserRegPo.signUPorLogin(sData[13], sData[14], sData[15]);
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
			Assert.assertTrue(feedbackPo.getEleArrowIcn().isDisplayed(), "Rider name is not displayed");
			qrLog.info("Rider name is displayed");
			feedbackPo.getEleArrowIcn().click();
			qrProfilePo.logout();
		}catch(Exception e){
	qrLog.error("Exception in  case testCarCapacity");
	e.printStackTrace();
	Assert.fail();
}
	}
}