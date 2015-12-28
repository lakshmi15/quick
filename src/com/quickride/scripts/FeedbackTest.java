/***********************************************************************
* @author 			:		Raghukiran MR
* @description		: 		Test scripts of Rating & Feedback
* @module			:		Rating & Feedback
* @testmethod		:	   	testRatingFeedback()
* @testmethod		:		testratingFeedback()
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

import io.appium.java_client.android.AndroidKeyCode;

public class FeedbackTest extends QRBaseLib {
	public String sData[]=null;
	public String sTestCaseID=null;
	NewUserRegPO newUserRegPo;
	QRProfilePO qrProfilePo;
	FeedbackPO feedbackPo;
	RidesPO ridesPo;
	MyProfilePO myProfilePo;
	public Logger qrLog = Logger.getLogger(this.getClass());
	
	/*@Test id:FeedBack_01
	 * @description:Test Passenger giving feedback to Rider 
	 * @author:Raghukiran MR
	 * 
	 */
	@Test(priority=1,enabled=true)
	public void  testRatingFeedback(){
		sTestCaseID="FeedBack_01";
		sData= GenericLib.toReadExcelData(sTestCaseID);
		newUserRegPo=new NewUserRegPO(driver);
		qrProfilePo= new QRProfilePO(driver);
		feedbackPo=new FeedbackPO(driver);
		myProfilePo =new MyProfilePO(driver);
		ridesPo = new RidesPO(driver);
		try{
				
			newUserRegPo.signUPorLogin(sData[1], sData[2],sData[3]);
			qrLog.info("Rider creating the ride");
			feedbackPo.createRide(sData[4], sData[5],ridesPo);
			qrProfilePo.logout();
			
			qrLog.info("Passenger joing the ride");
			newUserRegPo.signUPorLogin(sData[7], sData[8],sData[9]);
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
			
			qrLog.info("Passenger checkin to ride");
			newUserRegPo.getEleLoginBtn().click();
			newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[7]);
			newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[8]);
			driver.hideKeyboard();
			newUserRegPo.getEleLoginBtn().click();
			feedbackPo.checkInRide();
			
			qrLog.info("Passenger try to check out from ride");
			feedbackPo.checkOutRide();
			
			
			feedbackPo.getEleNextBtn().click();
			Assert.assertTrue(feedbackPo.getEleCommmentTxtFld().isDisplayed(), "Feedback page is not displayed ");
			qrLog.info("Feedback page is displayed ");
			
			feedbackPo.getEleCommmentTxtFld().sendKeys(sData[6]);
			int x=driver.findElementById("com.disha.quickride:id/userRatingBar").getLocation().getX();
		    int y=driver.findElementById("com.disha.quickride:id/userRatingBar").getLocation().getY();
		    driver.tap(1, x+500, y+70, 100);
		    qrLog.info("Passenger gives rating successfully");
			
		    feedbackPo.getEleSubmitBtn().click();
		    qrLog.info("Feedback submitted successfully");
		    
			Assert.assertTrue(newUserRegPo.getEleCurrentLocTxt().isDisplayed(), "Login is not Successful");
			qrLog.info("Passenger completed the Ride");

		}catch(Exception e){
			qrLog.error("Exception in  case testRatingFeedback");
			
			e.printStackTrace();
			Assert.fail();
		}
		
	}
	/*@Test id:FeedBack_02
	 * @description:Verify if feedback can be given to 3 passengers after the completion of the ride 
	 * @author:Raghukiran MR
	 * 
	 */
	 
	@Test(priority=2,enabled=true)
	public void  testratingFeedback(){
		sTestCaseID="FeedBack_02";
		sData= GenericLib.toReadExcelData(sTestCaseID);
		newUserRegPo=new NewUserRegPO(driver);
		qrProfilePo= new QRProfilePO(driver);
		feedbackPo=new FeedbackPO(driver);
		ridesPo = new RidesPO(driver);
		
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
			feedbackPo.getEleStartBtn().click();
			feedbackPo.getEleYesBtn().click();
			feedbackPo.getEleArrowIcn().click();
			qrProfilePo.logout();
			newUserRegPo.signUPorLogin(sData[9], sData[10],sData[11]);
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
			feedbackPo.getEleArrowIcn().click();
			feedbackPo.getEleArrowIcn().click();
			qrProfilePo.logout();
			
			newUserRegPo.signUPorLogin(sData[12], sData[13],sData[14]);
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
			feedbackPo.getEleArrowIcn().click();
			feedbackPo.getEleArrowIcn().click();
			qrProfilePo.logout();
			newUserRegPo.getEleLoginBtn().click();
			newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[6]);
			newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[7]);
			newUserRegPo.getEleLoginBtn().click();
			feedbackPo.getEleCheckInBtn().click();
			feedbackPo.getEleYesBtn().click();
			Assert.assertTrue(feedbackPo.getEleCheckOutBtn().isDisplayed(), "Check In button is not successfully");
			qrLog.info("Check In button is successfully");
			feedbackPo.getEleArrowIcn().click();
			qrProfilePo.logout();
			
			newUserRegPo.getEleLoginBtn().click();
			newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[9]);
			newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[10]);
			newUserRegPo.getEleLoginBtn().click();
			feedbackPo.getEleCheckInBtn().click();
			feedbackPo.getEleYesBtn().click();
			Assert.assertTrue(feedbackPo.getEleCheckOutBtn().isDisplayed(), "Check In button is not successfully");
			qrLog.info("Check In button is successfully");
			feedbackPo.getEleArrowIcn().click();
			qrProfilePo.logout();
			
			newUserRegPo.getEleLoginBtn().click();
			newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[12]);
			newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[13]);
			newUserRegPo.getEleLoginBtn().click();
			feedbackPo.getEleCheckInBtn().click();
			feedbackPo.getEleYesBtn().click();
			Assert.assertTrue(feedbackPo.getEleCheckOutBtn().isDisplayed(), "Check In button is not successfully");
			qrLog.info("Check In button is successfully");
			feedbackPo.getEleArrowIcn().click();
			qrProfilePo.logout();
			newUserRegPo.getEleLoginBtn().click();
			newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[1]);
			newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[2]);
			newUserRegPo.getEleLoginBtn().click();
			feedbackPo.getEleStopBtn().click();
			feedbackPo.getEleYesBtn().click();
			feedbackPo.getEleRideNextBtn().click();
			feedbackPo.getEleCommmentTxtFld().sendKeys(sData[15]);
			int x=driver.findElementById("com.disha.quickride:id/userRatingBar").getLocation().getX();
		    int y=driver.findElementById("com.disha.quickride:id/userRatingBar").getLocation().getY();
		    driver.tap(1, x+500, y+70, 100);
		    feedbackPo.getEleSubmitBtn().click();
		    qrLog.info("Passenger gives rating successfully");
		 }catch(Exception e){
			qrLog.error("Exception in  case testRatingFeedback");
			
			e.printStackTrace();
			Assert.fail();
		}

	}
}
