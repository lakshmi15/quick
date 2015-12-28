/***********************************************************************
* @author 			:		Raghukiran MR
* @description		: 		Test scripts of Group Chat
* @module			:		Group Chat
* @testmethod		:	   	testGroupChat()
* @testmethod		:		testOfflineGroupChat()
* @testmethod		:		
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

public class GroupChatTest extends QRBaseLib{
	public String sData[]=null;
	public String sTestCaseID=null;
	NewUserRegPO newUserRegPo;
	QRProfilePO qrProfilePo;
	FeedbackPO feedbackPo;
	RidesPO ridesPo;
	MyProfilePO myProfilePo;
	public Logger qrLog = Logger.getLogger(this.getClass());
	
	/*@Test id:GroupChat_01
	 * @description:Test group chat functionality among all participants of ride when they are online
	 * @author:Raghukiran MR
	 * 
	 */
	@Test(priority=1,enabled=true)
	public void  testGroupChat(){
		sTestCaseID="GroupChat_01";
		sData= GenericLib.toReadExcelData(sTestCaseID);
		newUserRegPo=new NewUserRegPO(driver);
		qrProfilePo= new QRProfilePO(driver);
		feedbackPo=new FeedbackPO(driver);
		myProfilePo =new MyProfilePO(driver);
		ridesPo = new RidesPO(driver);
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
			feedbackPo.getEleArrowIcn().click();
			feedbackPo.getEleNotificationIcon().click();
			feedbackPo.getEleAcceptLnk().click();
			feedbackPo.getEleArrowIcn().click();
			feedbackPo.getEleArrowIcn().click();
			qrProfilePo.logout();
			//UserC Join the ride
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
			//UserD Joint the ride
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
			//UserA accept the ride and send the message
			newUserRegPo.getEleLoginBtn().click();
			newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[1]);
			newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[2]);
			newUserRegPo.getEleLoginBtn().click();
			feedbackPo.getEleArrowIcn().click();
			feedbackPo.getEleArrowIcn().click();
			feedbackPo.getEleNotificationIcon().click();
			feedbackPo.getEleAcceptLnk().click();
			feedbackPo.getEleArrowIcn().click();
			feedbackPo.getEleArrowIcn().click();
			feedbackPo.getEleScheduled().click();
			myProfilePo.getEleChatBtn().click();
			feedbackPo.getEleChatTxtfld().sendKeys(sData[15]);
			Assert.assertTrue(feedbackPo.getEleChatSendBtn().isDisplayed(), "Chat message sent is not successfully");
			qrLog.info("Chat message sent successfully");
			feedbackPo.getEleChatSendBtn().click();
			feedbackPo.getEleChatArrowIcn().click();
			feedbackPo.getEleArrowIcn().click();
			qrProfilePo.logout();
			//UserB send the message
			newUserRegPo.getEleLoginBtn().click();
			newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[6]);
			newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[7]);
			newUserRegPo.getEleLoginBtn().click();
			feedbackPo.getEleArrowIcn().click();
			feedbackPo.getEleArrowIcn().click();
			feedbackPo.getEleScheduled().click();
			myProfilePo.getEleChatBtn().click();
			feedbackPo.getEleChatTxtfld().sendKeys(sData[16]);
			Assert.assertTrue(feedbackPo.getEleChatSendBtn().isDisplayed(), "Chat message sent is not successfully");
			qrLog.info("Chat message sent successfully");
			feedbackPo.getEleChatSendBtn().click();
			feedbackPo.getEleChatArrowIcn().click();
			feedbackPo.getEleArrowIcn().click();
			qrProfilePo.logout();
			//UserC send the message
			newUserRegPo.getEleLoginBtn().click();
			newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[9]);
			newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[10]);
			newUserRegPo.getEleLoginBtn().click();
			Assert.assertTrue(myProfilePo.getEleChatBtn().isDisplayed(), "Chat button is not successfully");
			qrLog.info("Chat button is  successfully");
			myProfilePo.getEleChatBtn().click();
			feedbackPo.getEleChatTxtfld().sendKeys(sData[17]);
			Assert.assertTrue(feedbackPo.getEleChatSendBtn().isDisplayed(), "Chat message sent is not successfully");
			qrLog.info("Chat message sent successfully");
			feedbackPo.getEleChatSendBtn().click();
			feedbackPo.getEleChatArrowIcn().click();
			feedbackPo.getEleArrowIcn().click();
			qrProfilePo.logout();
			
			//UserD send the message
			newUserRegPo.getEleLoginBtn().click();
			newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[12]);
			newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[13]);
			newUserRegPo.getEleLoginBtn().click();
			Assert.assertTrue(myProfilePo.getEleChatBtn().isDisplayed(), "Chat button is not successfully");
			qrLog.info("Chat button is  successfully");
			myProfilePo.getEleChatBtn().click();
			feedbackPo.getEleChatTxtfld().sendKeys(sData[18]);
			Assert.assertTrue(feedbackPo.getEleChatSendBtn().isDisplayed(), "Chat message sent is not successfully");
			qrLog.info("Chat message sent successfully");
			feedbackPo.getEleChatSendBtn().click();
			feedbackPo.getEleChatArrowIcn().click();
			feedbackPo.getEleArrowIcn().click();
			qrProfilePo.logout();
			//Clear up the code
			newUserRegPo.getEleLoginBtn().click();
			newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[1]);
			newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[2]);
			newUserRegPo.getEleLoginBtn().click();
			feedbackPo.getEleStartBtn().click();
			feedbackPo.getEleYesBtn().click();
			feedbackPo.getEleStopBtn().click();
			feedbackPo.getEleYesBtn().click();
			qrProfilePo.logout();
			}catch(Exception e){
			qrLog.error("Exception in  case testGroupChat()");
			
			e.printStackTrace();
			Assert.fail();
		}
	}
	/*@Test id:GroupChat_02
	 * @description:Test group chat functionality among all participants of ride when some of them  are offline
	 * @author:Raghukiran MR
	 * 
	 */
	@Test(priority=2,enabled=true)
	public void  testOfflineGroupChat(){
		sTestCaseID="GroupChat_02";
		sData= GenericLib.toReadExcelData(sTestCaseID);
		newUserRegPo=new NewUserRegPO(driver);
		qrProfilePo= new QRProfilePO(driver);
		feedbackPo=new FeedbackPO(driver);
		myProfilePo =new MyProfilePO(driver);
		ridesPo = new RidesPO(driver);
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
			feedbackPo.getEleArrowIcn().click();
			feedbackPo.getEleNotificationIcon().click();
			feedbackPo.getEleAcceptLnk().click();
			feedbackPo.getEleArrowIcn().click();
			feedbackPo.getEleArrowIcn().click();
			qrProfilePo.logout();
			//UserC Join the ride
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
			//UserD Joint the ride
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
			//UserA accept the ride and send the message
			newUserRegPo.getEleLoginBtn().click();
			newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[1]);
			newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[2]);
			newUserRegPo.getEleLoginBtn().click();
			feedbackPo.getEleArrowIcn().click();
			feedbackPo.getEleNotificationIcon().click();
			feedbackPo.getEleAcceptLnk().click();
			feedbackPo.getEleArrowIcn().click();
			feedbackPo.getEleArrowIcn().click();
			feedbackPo.getEleScheduled().click();
			myProfilePo.getEleChatBtn().click();
			feedbackPo.getEleChatTxtfld().sendKeys(sData[15]);
			Assert.assertTrue(feedbackPo.getEleChatSendBtn().isDisplayed(), "Chat message sent is not successfully");
			qrLog.info("Chat message sent successfully");
			feedbackPo.getEleChatSendBtn().click();
			feedbackPo.getEleChatArrowIcn().click();
			feedbackPo.getEleArrowIcn().click();
			qrProfilePo.logout();
			//UserB send the message
			newUserRegPo.getEleLoginBtn().click();
			newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[6]);
			newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[7]);
			newUserRegPo.getEleLoginBtn().click();
			feedbackPo.getEleArrowIcn().click();
			feedbackPo.getEleArrowIcn().click();
			feedbackPo.getEleScheduled().click();
			myProfilePo.getEleChatBtn().click();
			feedbackPo.getEleChatTxtfld().sendKeys(sData[16]);
			Assert.assertTrue(feedbackPo.getEleChatSendBtn().isDisplayed(), "Chat message sent is not successfully");
			qrLog.info("Chat message sent successfully");
			feedbackPo.getEleChatSendBtn().click();
			feedbackPo.getEleChatArrowIcn().click();
			feedbackPo.getEleArrowIcn().click();
			qrProfilePo.logout();
			//UserD send the message
			newUserRegPo.getEleLoginBtn().click();
			newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[12]);
			newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[13]);
			newUserRegPo.getEleLoginBtn().click();
			feedbackPo.getEleArrowIcn().click();
			feedbackPo.getEleArrowIcn().click();
			feedbackPo.getEleScheduled().click();
			myProfilePo.getEleChatBtn().click();
			feedbackPo.getEleChatTxtfld().sendKeys(sData[17]);
			Assert.assertTrue(feedbackPo.getEleChatSendBtn().isDisplayed(), "Chat message sent is not successfully");
			qrLog.info("Chat message sent successfully");
			feedbackPo.getEleChatSendBtn().click();
			feedbackPo.getEleChatArrowIcn().click();
			feedbackPo.getEleArrowIcn().click();
			qrProfilePo.logout(); 
			//UserC is in offline
			newUserRegPo.getEleLoginBtn().click();
			newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[9]);
			newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[10]);
			newUserRegPo.getEleLoginBtn().click();
			myProfilePo.getEleChatBtn().click();
			Assert.assertTrue(feedbackPo.getEleChatArrowIcn().isDisplayed(), "All the message is not visible");
			qrLog.info("All the message is visible");
			feedbackPo.getEleChatArrowIcn().click();
			feedbackPo.getEleArrowIcn().click();
			qrProfilePo.logout();
			//Clean up the code
			newUserRegPo.getEleLoginBtn().click();
			newUserRegPo.getEleLoginPhTxtFld().sendKeys(sData[1]);
			newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sData[2]);
			newUserRegPo.getEleLoginBtn().click();
			feedbackPo.getEleStartBtn().click();
			feedbackPo.getEleYesBtn().click();
			feedbackPo.getEleStopBtn().click();
			feedbackPo.getEleYesBtn().click();
			qrProfilePo.logout();
		}catch(Exception e){
			qrLog.error("Exception in  case testOfflineGroupChat()");
			e.printStackTrace();
			Assert.fail();
		}
}
}
