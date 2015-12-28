
/***********************************************************************
* @author 			:		LAKSHMI BS & Shreyas
* @description		: 		Test scripts of Recharge,Encash & Billing module.
* @module			:		Notification
* @testmethod		:		testRecharge()
* @testmethod		:		testRechargeJoinRide()
* @testmethod		:		testEncashAmountShareLater()
* @testmethod 		:		testEncashAmountShare()
* @testmethod 		:		testBillingZeroPricing()
* @testmethod		:		testBillingCustomisedPricing()

*/
package com.quickride.scripts;

import java.sql.SQLException;
import java.util.Arrays;

import org.apache.bcel.generic.Select;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.quickride.PO.NewUserRegPO;
import com.quickride.PO.QRProfilePO;
import com.quickride.PO.RidesPO;
import com.quickride.PO.FeedbackPO;
import com.quickride.PO.MyProfilePO;
import com.quickride.PO.MyRewardsPO;
import com.quickride.baselib.GenericLib;
import com.quickride.baselib.QRBaseLib;


import io.appium.java_client.TouchAction;

public class RechargeEncashTest extends QRBaseLib
{
	public Logger qrLog = Logger.getLogger(this.getClass());
	public String sData[]=null;
	public String sTestCaseID=null;
	NewUserRegPO newUserRegPo;
	QRProfilePO qrProfilePo;
	MyRewardsPO myRewardsPo;
	RidesPO ridesPo;
	FeedbackPO feedbackPo;
	MyProfilePO myProfilePo;
		
	/*
	 * @description: User can Recharge the account online
	 * @author: LAKSHMI BS
	 * 
	 */	
	@Test(priority=1,enabled=true)
	public void testRecharge()
	{
		sTestCaseID="Recharge_01";
		sData= GenericLib.toReadExcelData(sTestCaseID);
		String sAmt=sData[6];
		int ibalAmt=0;
		newUserRegPo = new NewUserRegPO(driver);
		myRewardsPo = new MyRewardsPO(driver); 
		qrProfilePo = new QRProfilePO(driver);
		try
		{	newUserRegPo.signUPorLogin(sData[1], sData[2],sData[3]);
			qrProfilePo.getEleMenuLst().click();
			qrProfilePo.getEleMyRewardsBtn().click();
			Assert.assertTrue(myRewardsPo.getEleRechargeBtn().isDisplayed(), "Recharge page is not displayed");
			qrLog.info("Recharge page is displayed");
			ibalAmt=Integer.parseInt(myRewardsPo.getEleBalReChargeTxt().getText());
			qrLog.info("Currently balance amount is "+ibalAmt);
			myRewardsPo.getEleAmountTxtFld().sendKeys(sAmt);
			driver.hideKeyboard();
			myRewardsPo.getEleRechargeBtn().click(); 
			Assert.assertTrue(myRewardsPo.getElePayULogin().isDisplayed(), "PayUMoney page is not displayed");
			qrLog.info("PayUMoney page is displayed");
			GenericLib.setDBdata(GenericLib.getCongigValue(sConfigFile, "ACCOUNT"), "balance", sAmt, "id",sData[1]);
			driver.resetApp();
			do
			{
			newUserRegPo.login(sData[1], sData[2]);
			qrProfilePo.getEleMenuLst().click();
			qrProfilePo.getEleMyRewardsBtn().click();
			if(myRewardsPo.getEleBalReChargeTxt().getText().equals(sAmt))
			{
				Assert.assertTrue(myRewardsPo.getEleBalReChargeTxt().getText().equals(sAmt), "Recharge is not successfull");
				qrLog.info("Recharge is successfull");
				break;
			}else
			{
				qrLog.info("Restarting app as db update is taking long");
				driver.resetApp();
			}
			}while(true);
		} catch (Exception e) {
			qrLog.error("Exception at testRecharge");
			e.printStackTrace();
			Assert.fail();
		}
	}
	/*
	 * @description: User can Recharge the account online
	 * @author: LAKSHMI BS
	 * 
	 */	
	@Test(priority=2,enabled=true)
	public void testRechargeJoinRide()
	{
		sTestCaseID="Recharge_02";
		sData= GenericLib.toReadExcelData(sTestCaseID);
		System.out.println(Arrays.toString(sData));
		String sAmt=sData[6];
	
		newUserRegPo = new NewUserRegPO(driver);
		ridesPo = new RidesPO(driver);
		feedbackPo=new FeedbackPO(driver);
		myRewardsPo = new MyRewardsPO(driver);
		qrProfilePo = new QRProfilePO(driver);
		try
		{
			qrLog.info("Passenger Login the ride");
			newUserRegPo.signUPorLogin(sData[1], sData[2],sData[3]);
			qrProfilePo.logout();
			qrLog.info("Setting the balance point of passenger to less than 100 to get alert to recharge");
			GenericLib.setDBdata(GenericLib.getCongigValue(sConfigFile, "ACCOUNT"), "balance", sAmt, "id",sData[1]);
			newUserRegPo.signUPorLogin(sData[8], sData[9],sData[10]);
			qrLog.info("Rider creating the ride");
			feedbackPo.createRide(sData[4], sData[5],ridesPo);
			driver.resetApp();
		
			qrLog.info("Passenger joing the ride");
			newUserRegPo.login(sData[1], sData[2]);
			ridesPo.rideNow(sData[4], sData[5]);
			
			feedbackPo.getEleFindRideTab().click();
			driver.findElement(By.name(sData[10])).click();
			Assert.assertTrue(feedbackPo.getEleJoinBtn().isDisplayed(), "Join button is not displayed for passenger");
			qrLog.info("Join Button is displayed");
			feedbackPo.getEleJoinBtn().click();
			Assert.assertTrue(ridesPo.getEleInviteFailedTxt().isDisplayed(), "Invite Failed Popup is not displayed");
			qrLog.info("Invite Failed Alert Popup is displayed, user need to Recharge");
			ridesPo.getEleRechargeBtn().click();
			sAmt=sData[7];
			myRewardsPo.rechargeAmt(sData[7], sData[1],sData[2], myRewardsPo, newUserRegPo, qrProfilePo,feedbackPo); 
			qrProfilePo.logout();
		} catch (Exception e) {
			qrLog.error("Exception at testRecharge");
			e.printStackTrace();
			Assert.fail();
		}
	}

	/*
	 * @description: Test user can encash the points earned from his account, share later and purchasing Petrol/Diesel 
	 * @author: LAKSHMI BS
	 * 
	 */	
	@Test(priority=3,enabled=true)
	public void testEncashAmountShareLater()
	{
		sTestCaseID="Encash_01";
		sData= GenericLib.toReadExcelData(sTestCaseID);
		String  sEncashPts="100";
		newUserRegPo = new NewUserRegPO(driver);
		myRewardsPo = new MyRewardsPO(driver);
		qrProfilePo = new QRProfilePO(driver);
		try
		{
			newUserRegPo.signUPorLogin(sData[1], sData[2],sData[3]);
			qrProfilePo.getEleMenuLst().click();
			qrProfilePo.getEleMyRewardsBtn().click();
			myRewardsPo.getEleEncashTab().click();
			Assert.assertTrue(myRewardsPo.getEleEncashBtn().isDisplayed(), "Encash page is not displayed");
			qrLog.info("Encash page is successfully displayed");
			myRewardsPo.getEleEncashTxtFld().sendKeys(sEncashPts);
			myRewardsPo.getEleEncashBtn().click();
			myRewardsPo.getEncashPts(qrProfilePo);
		} catch (Exception e) {
			qrLog.error("Exception at testEncashAmount");
			e.printStackTrace();
			Assert.fail();
		}
	}

	
	/*
	 * @description: Test user can encash the points earned from his account, Share now and purchasing Petrol/Diesel 
	 * @author: LAKSHMI BS
	 * 
	 */	
	@Test(priority=4,enabled=true)
	public void testEncashAmountShare()
	{
		sTestCaseID="Encash_01";
		sData= GenericLib.toReadExcelData(sTestCaseID);
		String  sEncashPts="100";
		newUserRegPo = new NewUserRegPO(driver);
		myRewardsPo = new MyRewardsPO(driver);
		qrProfilePo = new QRProfilePO(driver);
		try
		{
			newUserRegPo.signUPorLogin(sData[1], sData[2],sData[3]);
			qrProfilePo.getEleMenuLst().click();
			qrProfilePo.getEleMyRewardsBtn().click();
			myRewardsPo.getEleEncashTab().click();
			Assert.assertTrue(myRewardsPo.getEleEncashBtn().isDisplayed(), "Encash page is not displayed");
			qrLog.info("Encash page is successfully displayed");
			myRewardsPo.getEleEncashTxtFld().sendKeys(sEncashPts);
			myRewardsPo.getEleEncashBtn().click();
			myRewardsPo.getEncashPtsShare(qrProfilePo);
		} catch (Exception e) {
			qrLog.error("Exception at testEncashAmount");
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	
	/*
	 * @description: Verifying the Billing information shown in rewards for Customized prizing.
	 * @author: Shreyas MS
	 * 
	 */	
	@Test(priority=5,enabled=true)
	public void testBillingCustomisedPricing()
	{
		sTestCaseID="Billing_01";
		sData= GenericLib.toReadExcelData(sTestCaseID);
		int sBalance = 0;
		int pBalance = 0;
		int finalRiderBalance = 0;
		int finalPassBalance = 0;
		newUserRegPo = new NewUserRegPO(driver);
		qrProfilePo = new QRProfilePO(driver);
		ridesPo = new RidesPO(driver);
		myRewardsPo = new MyRewardsPO(driver);
		feedbackPo=new FeedbackPO(driver);
		myProfilePo=new MyProfilePO(driver) ;
		try
		{
			//Rider login and Offer a ride for Customized prizing
			newUserRegPo.signUPorLogin(sData[1], sData[2],sData[3]);
			qrProfilePo.getEleMenuLst().click();
			
			
			
			qrProfilePo.getEleMyRewardsBtn().click();
			Assert.assertTrue(myRewardsPo.getEleRechargeBtn().isDisplayed(), "Recharge page is not displayed");
			qrLog.info("Recharge page is displayed");
			sBalance=Integer.parseInt(myRewardsPo.getEleBalReChargeTxt().getText());
			qrLog.info("Currently balance amount is "+sBalance);// Get default balance
			
			myProfilePo.getEleMenuBar().click();
			myProfilePo.getEleArrowBtn().click();
			myProfilePo.getEleEditLnk().click();
			driver.scrollTo("Ride Preferences");
			myProfilePo.getEleVehicleDetailsLnk().click();
			myProfilePo.getEleFareTxtFld().clear();
			myProfilePo.getEleFareTxtFld().sendKeys(sData[14]); // Change the ride prizing.
			myProfilePo.getEleArrowIcn().click();
			myProfilePo.getEleSaveLnk().click();
			myProfilePo.getEleArrowIcn().click();
			
			myProfilePo.getEleMenuBar().click();
			
			qrProfilePo.getEleNewRideBtn().click();
			feedbackPo.createRide(sData[4], sData[5], ridesPo);
				
			qrProfilePo.logout();
			
			
			//Passenger1 login and joins the ride And Note/Verify the Rewards amount.
			newUserRegPo.signUPorLogin(sData[6], sData[7], sData[8]);
			
			qrProfilePo.getEleMenuLst().click();
			qrProfilePo.getEleMyRewardsBtn().click();
			Assert.assertTrue(myRewardsPo.getEleRechargeBtn().isDisplayed(), "Recharge page is not displayed");
			qrLog.info("Recharge page is displayed");
			pBalance=Integer.parseInt(myRewardsPo.getEleBalReChargeTxt().getText());
			qrLog.info("Currently balance amount is "+pBalance);
			qrProfilePo.getEleMenuLst().click();
			qrProfilePo.getEleNewRideBtn().click();
			
			feedbackPo.joinRide(sData[4], sData[5], sData[3]);
			qrProfilePo.logout();
			
			
			//Rider login and accept the join request
			newUserRegPo.login(sData[1], sData[2]);
			feedbackPo.getEleArrowIcn().click();
			feedbackPo.navigateNotification();	
			feedbackPo.getEleAcceptLnk().click();
			feedbackPo.getEleAcceptLnk().click();
			feedbackPo.getEleArrowIcn().click();
			qrProfilePo.logout();
			
			//Passenger2 login join the same ride
			newUserRegPo.signUPorLogin(sData[11], sData[12], sData[13]);
			feedbackPo.joinRide(sData[4], sData[5], sData[3]);
			qrProfilePo.logout();
			
			//Rider login accept the join request and start the ride
			newUserRegPo.login(sData[1], sData[2]);
			feedbackPo.getEleArrowIcn().click();
			feedbackPo.navigateNotification();	
			feedbackPo.getEleAcceptLnk().click();
			feedbackPo.getEleStartBtn().click();
			feedbackPo.getEleYesBtn().click();
			feedbackPo.getEleArrowIcn().click();
			qrProfilePo.logout();
			
			//Passenger1 login And check in - check out of ride.
			newUserRegPo.login(sData[6], sData[7]);
			feedbackPo.checkInRide();
			feedbackPo.checkOutRide();
			ridesPo.getEleNextBtn().click();
			ridesPo.getEleSubmitBtn().click();
			ridesPo.completedTab(qrProfilePo);
			qrProfilePo.logout();
			
			//Passenger2 login And check in - check out of ride.
			newUserRegPo.login(sData[11], sData[12]);
			feedbackPo.checkInRide();
			feedbackPo.checkOutRide();
			ridesPo.getEleNextBtn().click();
			ridesPo.getEleSubmitBtn().click();
			qrProfilePo.logout();
			
			
			//Rider login Stop the ride And verify the whether Rewards is increased[Verify the balance].
			newUserRegPo.login(sData[1], sData[2]);
			ridesPo.getEleStopBtn().click();
			ridesPo.getEleYesBtn().click();
			ridesPo.getEleNextBtn().click();
			ridesPo.getEleNextBtn().click();
			ridesPo.getEleSubmitBtn().click();
			ridesPo.completedTab(qrProfilePo);
			
			qrProfilePo.getEleMenuLst().click();
			qrProfilePo.getEleMyRewardsBtn().click();
			Assert.assertTrue(myRewardsPo.getEleRechargeBtn().isDisplayed(), "Recharge page is not displayed");
			qrLog.info("Recharge page is displayed");
			finalRiderBalance=Integer.parseInt(myRewardsPo.getEleBalReChargeTxt().getText());
			qrLog.info("Currently balance amount is "+finalRiderBalance);
			if(sBalance < finalRiderBalance)
			{
				System.out.println("1.Final Balance of Rider is" +finalRiderBalance);
			}else
			{
				System.out.println("1.Balance has is not changed" +sBalance);
			}
			qrProfilePo.logout();
			
			
			// Passenger1 balance check
			newUserRegPo.login(sData[6], sData[7]);
			qrProfilePo.getEleMenuLst().click();
			qrProfilePo.getEleMyRewardsBtn().click();
			Assert.assertTrue(myRewardsPo.getEleRechargeBtn().isDisplayed(), "Rewards page is not displayed");
			qrLog.info("Rewards page is displayed");
			finalPassBalance=Integer.parseInt(myRewardsPo.getEleBalReChargeTxt().getText());
			qrLog.info("Currently balance amount is "+finalPassBalance);
			if(pBalance > finalPassBalance)
			{
				System.out.println("1.Final Balance of Passenger is" +finalPassBalance);
			}else
			{
				System.out.println("1.Balance has is not changed" +pBalance);
			}
			
		}
		catch(Exception e)
		{
			qrLog.error("Exception in  case testCompletedStatusPassenger");
			e.printStackTrace();
			Assert.fail();
		}
	}


	/*
	 * @description: Verifying the Billing information shown in rewards for Zero in prizing[Customized prizing].
	 * Test with Zero pricing with 2 ride Passengers.
	 * @author: Shreyas MS
	 * 
	 */	
		@Test(priority=6,enabled=true)
		public void testBillingZeroPricing()
		{
			sTestCaseID="Billing_02";
			sData= GenericLib.toReadExcelData(sTestCaseID);
			int sBal = 0;
			int pBal = 0;
			int finalRiderBal = 0;
			int finalPassBal = 0;
			newUserRegPo = new NewUserRegPO(driver);
			qrProfilePo = new QRProfilePO(driver);
			ridesPo = new RidesPO(driver);
			myRewardsPo = new MyRewardsPO(driver);
			feedbackPo=new FeedbackPO(driver);
			myProfilePo=new MyProfilePO(driver) ;
			try
			{
				//Rider login and Offer a ride.
				newUserRegPo.signUPorLogin(sData[1], sData[2],sData[3]);
				qrProfilePo.getEleMenuLst().click();
				qrProfilePo.getEleMyRewardsBtn().click();
				Assert.assertTrue(myRewardsPo.getEleRechargeBtn().isDisplayed(), "Recharge page is not displayed");
				qrLog.info("Recharge page is displayed");
				sBal=Integer.parseInt(myRewardsPo.getEleBalReChargeTxt().getText());
				qrLog.info("Currently balance amount is "+sBal);
				
				
				myProfilePo.getEleMenuBar().click();
				myProfilePo.getEleArrowBtn().click();
				myProfilePo.getEleEditLnk().click();
				driver.scrollTo("Ride Preferences");
				myProfilePo.getEleVehicleDetailsLnk().click();
				myProfilePo.getEleFareTxtFld().clear();
				myProfilePo.getEleFareTxtFld().sendKeys(sData[14]); // Change the ride prizing to zero.
				myProfilePo.getEleSaveLnk().click();
				myProfilePo.getEleArrowIcn().click();
				myProfilePo.getEleMenuBar().click();
				
				qrProfilePo.getEleNewRideBtn().click();
				feedbackPo.createRide(sData[4], sData[5], ridesPo);
					
				qrProfilePo.logout();
				
				//Passenger1 login and joins the ride And Note/Verify the Rewards amount.
				newUserRegPo.signUPorLogin(sData[6], sData[7], sData[8]);
				
				qrProfilePo.getEleMenuLst().click();
				qrProfilePo.getEleMyRewardsBtn().click();
				Assert.assertTrue(myRewardsPo.getEleRechargeBtn().isDisplayed(), "Recharge page is not displayed");
				qrLog.info("Recharge page is displayed");
				pBal=Integer.parseInt(myRewardsPo.getEleBalReChargeTxt().getText());
				qrLog.info("Currently balance amount is "+pBal);
				qrProfilePo.getEleMenuLst().click();
				qrProfilePo.getEleNewRideBtn().click();
				
				feedbackPo.joinRide(sData[4], sData[5], sData[3]);
				qrProfilePo.logout();
				
				//Rider login and accept the join request
				newUserRegPo.login(sData[1], sData[2]);
				feedbackPo.getEleArrowIcn().click();
				feedbackPo.navigateNotification();	
				feedbackPo.getEleAcceptLnk().click();
				feedbackPo.getEleAcceptLnk().click();
				qrProfilePo.logout();
				
				//Passenger2 login join the same ride.
				newUserRegPo.signUPorLogin(sData[11], sData[12], sData[13]);
				feedbackPo.joinRide(sData[4], sData[14], sData[3]);
				qrProfilePo.logout();
				
				//Rider login accept the join request and start the ride.
				newUserRegPo.login(sData[1], sData[2]);
				feedbackPo.getEleArrowIcn().click();
				feedbackPo.navigateNotification();	
				feedbackPo.getEleAcceptLnk().click();
				feedbackPo.getEleAcceptLnk().click();
				feedbackPo.getEleStartBtn().click();
				feedbackPo.getEleYesBtn().click();
				feedbackPo.getEleArrowIcn().click();
				qrProfilePo.logout();
				
				//Passenger1 login And check in - check out of ride.
				newUserRegPo.login(sData[6], sData[7]);
				feedbackPo.checkInRide();
				feedbackPo.checkOutRide();
				ridesPo.getEleNextBtn().click();
				ridesPo.getEleSubmitBtn().click();
				ridesPo.completedTab(qrProfilePo);
				qrProfilePo.logout();
				
				//Passenger2 login And check in - check out of ride.
				newUserRegPo.login(sData[11], sData[12]);
				feedbackPo.checkInRide();
				feedbackPo.checkOutRide();
				ridesPo.getEleNextBtn().click();
				ridesPo.getEleSubmitBtn().click();
				qrProfilePo.logout();
				
				
				//Rider login Stop the ride And verify the whether Rewards is increased[Verify the balance].
				newUserRegPo.login(sData[1], sData[2]);
				ridesPo.getEleStopBtn().click();
				ridesPo.getEleYesBtn().click();
				ridesPo.getEleNextBtn().click();
				ridesPo.getEleNextBtn().click();
				ridesPo.getEleSubmitBtn().click();
				ridesPo.completedTab(qrProfilePo);
				
				
				qrProfilePo.getEleMenuLst().click();
				qrProfilePo.getEleMyRewardsBtn().click();
				Assert.assertTrue(myRewardsPo.getEleRechargeBtn().isDisplayed(), "Rewards page is not displayed");
				qrLog.info("Rewards page is displayed");
				finalRiderBal=Integer.parseInt(myRewardsPo.getEleBalReChargeTxt().getText());
				qrLog.info("Currently balance amount is "+finalRiderBal);
				if(sBal < finalRiderBal)
				{
					System.out.println("2.Final Balance of Rider is" +finalRiderBal);
				}else
				{
					System.out.println("2.Balance has is not changed" +sBal);
				}
				
				qrProfilePo.logout();
				
				// Passenger1 balance check
				newUserRegPo.login(sData[6], sData[7]);
				qrProfilePo.getEleMenuLst().click();
				qrProfilePo.getEleMyRewardsBtn().click();
				Assert.assertTrue(myRewardsPo.getEleRechargeBtn().isDisplayed(), "Rewards page is not displayed");
				qrLog.info("Reards page is displayed");
				finalPassBal=Integer.parseInt(myRewardsPo.getEleBalReChargeTxt().getText());
				qrLog.info("Currently balance amount is "+finalPassBal);
				if(pBal > finalPassBal)
				{
					System.out.println("2.Final Balance of Passenger is" +finalPassBal);
				}else
				{
					System.out.println("2.Balance has is not changed" +pBal);
				}
				
			}
			catch(Exception e)
			{
				qrLog.error("Exception in  case testCompletedStatusPassenger");
				e.printStackTrace();
				Assert.fail();
			}
	}
	
}
