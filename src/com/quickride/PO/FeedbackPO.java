package com.quickride.PO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;



import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

public class FeedbackPO {
	AndroidDriver driver = null;
	public Logger qrLog = Logger.getLogger(this.getClass());
	public FeedbackPO(AndroidDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(id ="com.disha.quickride:id/search_icon")
	private WebElement eleSearchBtn;
	
	public WebElement getEleSearchBtn()
	{
		return eleSearchBtn;
	}
	
	@FindBy(name ="Start Exploring")
	private WebElement eleStartExploringTxt;
	
	public WebElement getEleStartExploringTxt()
	{
		return eleStartExploringTxt;
	}

	@FindBy(name="Enter ride start address")
	private WebElement eleEntRdStSearchIcn;
	public WebElement getEleEntRdStSearchIcn() {
		return eleEntRdStSearchIcn;
	}
	@FindBy(id="com.disha.quickride:id/searchForLocation")
	private WebElement eleEnterAddTxtFld;
	public WebElement getEleEnterAddTxtFld()
	{
		return eleEnterAddTxtFld;
	}


	@FindBy(id="com.disha.quickride:id/selectedLocation_desc_layout")
	private WebElement eleFirstOption;
	public WebElement getEleFirstOption()
	{
		return  eleFirstOption ;
	}

	@FindBy(id = "com.disha.quickride:id/findNow")
	private WebElement eleRideNwBtn;
	public WebElement getEleRideNwBtn()
	{
		return eleRideNwBtn;
	}


	@FindBy(name="Find Ride")
	private WebElement eleFindRideTab;
	public WebElement getEleFindRideTab() {
		return eleFindRideTab;
	}

	@FindBy(id="com.disha.quickride:id/addPassengers")
	private WebElement eleAddPassengerBtn;
	public WebElement getEleAddPassengerBtn()
	{
		return eleAddPassengerBtn;
	}

	@FindBy(id="com.disha.quickride:id/joinToRide")
	private WebElement eleJoinBtn;
	public WebElement getEleJoinBtn() {
		return eleJoinBtn;
	}

	@FindBy(name="Done")
	private WebElement eleDoneBtn;
	public WebElement getEleDoneBtn()
	{
		return eleDoneBtn;
	}

	@FindBy(name="Start")
	private WebElement eleStartBtn;
	public WebElement getEleStartBtn() {
		return eleStartBtn;
	}

	@FindBy(id="com.disha.quickride:id/positive_button")
	private WebElement eleYesBtn;
	public WebElement getEleYesBtn() 
	{
		return eleYesBtn;
	}

	@FindBy(name="Stop")
	private WebElement eleStopBtn;
	public WebElement getEleStopBtn()
	{
		return eleStopBtn;
	}
	
		
	
	@FindBy(id="com.disha.quickride:id/next_Button")
	private WebElement eleNextBtn;
	public WebElement getEleNextBtn() 
	{
		return eleNextBtn;
	}
	@FindBy(name="Next")
	private WebElement eleRideNextBtn;
	public WebElement getEleRideNextBtn() 
	{
		return eleRideNextBtn;
	}
		
	@FindBy(id = "com.disha.quickride:id/userRatingBar //the feed back")
	private WebElement  eleRatingBar;
	public WebElement getEleRatingbar()
	{
		return eleRatingBar;
	}
	
	@FindBy(name="comments")
	private WebElement  eleCommmentTxtFld;
	public WebElement getEleCommmentTxtFld()
	{
		return eleCommmentTxtFld;
	}
	
	
	@FindBy(name="Submit")
	private WebElement  eleSubmitBtn;

	public WebElement getEleSubmitBtn()
	{
		return eleSubmitBtn;
	}
	
	@FindBy(name="Check out")
	private WebElement eleCheckOutBtn;
	public WebElement getEleCheckOutBtn(){
		return eleCheckOutBtn;
	}

	
	
	@FindBy(name= "Create ride")
	private WebElement  eleCreateRideBtn;

	public WebElement getEleCreateRideBtn()
	{
		return eleCreateRideBtn;
	}

	@FindBy(name= "Offer Ride")
	private WebElement  eleOfferRideTab;

	public WebElement getEleOfferRideTab()
	{
		return eleOfferRideTab;
	}

	@FindBy(name= "Enter ride end address")
	private WebElement  eleEndAddressTxtFld;

	public WebElement getEleEndAddressTxtFld()
	{
		return eleEndAddressTxtFld;
	}
	
	
	@FindBy(id="com.disha.quickride:id/scheduleRide")
	private WebElement  eleOfferRideBtn;

	public WebElement getEleOfferRideBtn()
	{
		return  eleOfferRideBtn;
	}

	@FindBy(id="com.disha.quickride:id/back_icon_ride_view_image")
	private WebElement  eleArrowIcn;
	public WebElement getEleArrowIcn()
	{
		return eleArrowIcn;
	}

	@FindBy(name="New Ride")
	private WebElement  elepassArrowIcn;
	public WebElement getElepassArrowIcn()
	{
		return elepassArrowIcn;
	}
	@FindBy(id="com.disha.quickride:id/profile_actionbar_backPress_image")		
	private static WebElement eleProfileArrowIcn;
	public WebElement getEleProfileArrowIcn()
	{
		return eleProfileArrowIcn;
	}
	
	

	@FindBy(name="Accept")
	private WebElement  eleAcceptLnk;

	public WebElement getEleAcceptLnk()
	{
		return eleAcceptLnk;
	}
	
	@FindBy(id="com.disha.quickride:id/drawer_notification_icon")
	private WebElement  eleNotificationIcon;

	public WebElement getEleNotificationIcon()
	{
		return eleNotificationIcon;
	}
	
	@FindBy(id="com.disha.quickride:id/description")
	private WebElement  eleNotificationTxt;

	public WebElement getEleNotificationTxt()
	{
		return eleNotificationTxt;
	}
	@FindBy(name="QuickRide Wallet")
	private WebElement  eleQRWalletNotiTxt;

	public WebElement getEleQRWalletNotiTxt()
	{
		return eleQRWalletNotiTxt;
	}
	
	
	@FindBy(id="com.disha.quickride:id/notification_time")
	private WebElement  eleNotiTimeTxt;
	public WebElement getEleNotiTimeTxt()
	{
		return eleQRWalletNotiTxt;
	}
	
	
	@FindBy(name="Check in")		
	private static WebElement eleCheckInBtn;
	public WebElement getEleCheckInBtn()
	{
		return eleCheckInBtn;
	}
	@FindBy(name="Find rider")
	private WebElement eleFindRiderIcn;
	public WebElement getEleFindRiderIcn(){
		return eleFindRiderIcn;
}
	@FindBy(name="Reject")		
	private static WebElement eleRejectLnk;
	public WebElement getEleRejectLnk()
	{
		return eleRejectLnk;
	}
	@FindBy(name="Profile")		
	private static WebElement eleProfileLnk;
	public WebElement getEleProfileLnk()
	{
		return eleProfileLnk;
	}
	@FindBy(id="com.disha.quickride:id/rideParticipantName_rideview")		
	private static WebElement eleParticipantIcn;
	public WebElement getEleParticipantIcn()
	{
		return eleParticipantIcn;
	}
	@FindBy(id="com.disha.quickride:id/settingsRideView")		
	private static WebElement eleCanelMenu;
	public WebElement getEleCanelMenu(){
		return eleCanelMenu;
	}
	@FindBy(name="Cancel")
	private WebElement eleCancelbtn;
	public WebElement getEleCancelbtn(){
		return eleCancelbtn;
	}
	@FindBy(name="Type your message")		
	private static WebElement eleChatTxtFld;
	public WebElement getEleChatTxtfld()
	{
		return eleChatTxtFld;
	}
	
	@FindBy(id="com.disha.quickride:id/sendButton")		
	private static WebElement eleChatSendBtn;
	public WebElement getEleChatSendBtn()
	{
		return eleChatSendBtn;
	}
	@FindBy(id="com.disha.quickride:id/actionbar_chat_back_icon2")		
	private static WebElement eleChatArrowIcn;
	public WebElement getEleChatArrowIcn()
	{
		return eleChatArrowIcn;
	}
	//com.disha.quickride:id/passengerDetails
	@FindBy(id="com.disha.quickride:id/passengerDetails")		
	private static WebElement eleScheduled;
	public WebElement getEleScheduled()
	{
		return eleScheduled;
	}
	public void joinRide(String src,String dest,String sName) {
		try{
			
			
			getEleSearchBtn().click();
			getEleEnterAddTxtFld().sendKeys(src);
			getEleFirstOption().click();
			getEleRideNwBtn().click();
			getEleEndAddressTxtFld().click();
			getEleEnterAddTxtFld().sendKeys(dest);
			getEleFirstOption().click();
			getEleFindRideTab().click();
			driver.findElement(By.name(sName)).click();
			Assert.assertTrue(getEleJoinBtn().isDisplayed(), "Join button is not displayed for passenger");
			qrLog.info("Join Button is displayed");
			getEleJoinBtn().click();
			
			getEleArrowIcn().click();
			
			}catch(Exception e)
		{
			qrLog.error("Exception in passengerJoin()");
			e.printStackTrace();
			Assert.fail();
		}
		
	}
	public void createRide(String src,String dest, RidesPO ridesPo){
		try{
			
			getEleSearchBtn().click();
			getEleEnterAddTxtFld().sendKeys(src);
			getEleFirstOption().click();
			getEleRideNwBtn().click();
			getEleEndAddressTxtFld().click();
			getEleEnterAddTxtFld().sendKeys(dest);
			getEleFirstOption().click();
			
			getEleOfferRideTab().click();
			
			verifyOfferRide();
			
			
			Assert.assertTrue(getEleArrowIcn().isDisplayed(), "Ride Scheduled is not displayed");
			qrLog.info("Ride Scheduled is displayed");
			getEleArrowIcn().click();
			}catch(Exception e)
		{
			qrLog.error("Exception in createRide()");
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void verifyOfferRide()
	{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			try
			{
				getEleCreateRideBtn().click();
			}
			catch(Exception e)
			{
				getEleOfferRideBtn().click();
			
			}
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			
	}
	public void acceptRide(){
	try{
		
		
		getEleArrowIcn().click();
		navigateNotification();	
		getEleAcceptLnk().click();
		getEleStartBtn().click();
		getEleYesBtn().click();
		Assert.assertTrue(getEleStopBtn().isDisplayed(), "Ride is not started ");
		qrLog.info("Ride is started successfully");
		
		getEleArrowIcn().click();
		
		
		}catch(Exception e)
	{
		qrLog.error("Exception in acceptRide()");
		e.printStackTrace();
		Assert.fail();
	}
}
	public void rejectRide(){
		try{
			
			
			getEleArrowIcn().click();
			getEleRejectLnk().click();
			
			getEleArrowIcn().click();
			
			
			}catch(Exception e)
		{
			qrLog.error("Exception in acceptRide()");
			e.printStackTrace();
			Assert.fail();
		}
	}
	
public void checkInRide(){
	try{
			
			
			
			getEleCheckInBtn().click();
			getEleYesBtn().click();
			Assert.assertTrue(getEleCheckOutBtn().isDisplayed(), "Ride is not started ");
			qrLog.info("Ride is available for Passenger");
			
		
			
			
			
			}catch(Exception e)
		{
			qrLog.error("Exception in checkInRide()");
			e.printStackTrace();
			Assert.fail();
		}
}
public void checkOutRide()
{
	try{
		
		getEleCheckOutBtn().click();
		getEleYesBtn().click();
		Assert.assertTrue(getEleNextBtn().isDisplayed(), "Passenger failed to check out from ride");
		qrLog.info("Passenger checked out from ride successfully");
		
		
		
	}catch(Exception e)
	{
		qrLog.error("Exception in checkOutRide()");
		e.printStackTrace();
		Assert.fail();
	
	}
}


public void findRider(String rider2){
	getEleFindRiderIcn().click();
}










public void navigateNotification()
{
	try
	{
		if(eleNotificationIcon.isDisplayed())
		{
			eleNotificationIcon.click();
			
		}
		
	}
	catch(Exception e)
	{
		qrLog.info("Notification is directly displayed");
	}
}
	


}
		
	
		
		
	
	
		


