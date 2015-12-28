package com.quickride.PO;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;

public class RidesPO 
{
	AndroidDriver driver = null;
	public Logger qrLog = Logger.getLogger(this.getClass());
	
	public RidesPO(AndroidDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	@FindBy(id = "com.disha.quickride:id/findNow")
	private WebElement eleRideNwBtn;
	
	public WebElement getEleRideNwBtn()
	{
		return eleRideNwBtn;
	}
	@FindBy(id="com.disha.quickride:id/locationList")
	private WebElement eleLocationLst;
	public WebElement getEleLocationLst() {
		return eleLocationLst;
	}
	@FindBy(name="Offer Ride")
	private WebElement eleOfferRideTab;
	public WebElement getEleOfferRideTab() {
		return eleOfferRideTab;
	}
	@FindBy(name="Find Ride")
	private WebElement eleFindRideTab;
	public WebElement getEleFindRideTab() {
		return eleFindRideTab;
	}
	@FindBy(name="Find Ride")
	private WebElement eleFindRideTxt;
	public WebElement getEleFindRideTxt() {
		return eleFindRideTxt;
	}

	@FindBy(id="com.disha.quickride:id/joinToRide")
	private WebElement eleJoinBtn;
	public WebElement getEleJoinBtn() {
		return eleJoinBtn;
	}
	
	@FindBy(id = "com.disha.quickride:id/findLater")
	private WebElement eleRideLaterBtn;
	public WebElement getEleRideLaterBtn()
	{
		return eleRideLaterBtn;
	}
	
	@FindBy(name="Completed")
	private WebElement eleCompletedTab;
	public WebElement getEleCompletedTab()
	{
		return eleCompletedTab;
	}
	
	
	@FindBy(id="com.disha.quickride:id/searchForLocation")
	private WebElement eleEnterAddTxtFld;
	public WebElement getEleEnterAddTxtFld()
	{
		return eleEnterAddTxtFld;
	}
	
	@FindBy(id="com.disha.quickride:id/selectedLocation_desc_layout")
	private WebElement eleFirstSrcOptn;
	public WebElement getEleFirstSrcOptn()
	{
		return eleFirstSrcOptn;
	}
	@FindBy(name="Enter ride start address")
	private WebElement eleEntRdStSearchIcn;
	public WebElement getEleEntRdStSearchIcn() {
		return eleEntRdStSearchIcn;
	}
	@FindBy(id="com.disha.quickride:id/destLocation")
	private WebElement eleEntRdEndAddressIcn;
	public WebElement getEleEntRdEndAddressIcn(){
		return eleEntRdEndAddressIcn;
	}
	@FindBy(id="com.disha.quickride:id/searchForLocation")
	private WebElement eleEntrAddrssTxtField;
	public WebElement getEleEntrAddrssTxtField() {
		return eleEntrAddrssTxtField;
	}
	@FindBy(name="Yes")
	private WebElement eleYesBtn;
	public WebElement getEleYesBtn() 
	{
		return eleYesBtn;
	}
	@FindBy(name="No")
	private WebElement eleRdNoBtn;
	public WebElement getEleRdNoBtn(){
		return eleRdNoBtn;
	}
	
	@FindBy(name="This ride is already closed.")
	private WebElement eleRideClosedTxt;
	public WebElement getEleRideClosedTxt(){
		return eleRideClosedTxt;
	}
	
	@FindBy(name="Cancel")
	private WebElement eleCancelBtn;
	public WebElement getEleCancelBtn() {
		return eleCancelBtn;
	}
	@FindBy(id="com.disha.quickride:id/controlButton")
	private WebElement eleStartBtn;
	public WebElement getEleStartBtn(){
		return eleStartBtn;
	}

	@FindBy(id="com.disha.quickride:id/controlButton")
	private WebElement eleStopBtn;
	public WebElement getEleStopBtn(){
		return eleStopBtn;
	}
	@FindBy(id="com.disha.quickride:id/end_location_user")
	private WebElement eleRideCreateByUser;
	public WebElement getEleRideCreateByUser(){
		return eleRideCreateByUser;
	}
	
	@FindBy(name="Done")
	private WebElement eleDoneBtn;
	public WebElement getEleDoneBtn(){
		return eleDoneBtn;
	}
	@FindBy(id="android:id/button1")
	private WebElement eleCalendrOkBtn;
	public WebElement getEleCalendrOkBtn(){
		return eleCalendrOkBtn;
	}
	@FindBy(id="android:id/button2")
	private WebElement eleCalendrCancelBtn;
	public WebElement getEleCalendrCancelBtn(){
		return eleCalendrCancelBtn;
	}
	@FindBy(name="Create ride")
	private WebElement eleCreateRideBtn;
	public WebElement getEleCreateRideBtn(){
		return eleCreateRideBtn;
	}
	@FindBy(id="com.disha.quickride:id/scheduleRide")
	private WebElement eleOfferRideBtn;
	public WebElement getEleOfferRideBtn(){
		return eleOfferRideBtn;
	}
	@FindBy(name="Request Ride")
	private WebElement eleRequestRideBtn;
	public WebElement getEleRequestRideBtn(){
		return eleRequestRideBtn;
	}
	@FindBy(name="Check in")
	private WebElement eleCheckInBtn;
	public WebElement getEleCheckInBtn(){
		return eleCheckInBtn;
	}
	
	@FindBy(name="Check out")
	private WebElement eleCheckOutBtn;
	public WebElement getEleCheckOutBtn(){
		return eleCheckOutBtn;
	}
	@FindBy(id="com.disha.quickride:id/next_Button")
	private WebElement eleNextBtn;
	public WebElement getEleNextBtn(){
		return eleNextBtn;
	}
	@FindBy(name="Submit")
	private WebElement eleSubmitBtn;
	public WebElement getEleSubmitBtn(){
		return eleSubmitBtn;
	}
	@FindBy(id="com.disha.quickride:id/drawer_notification_icon")
	private WebElement eleNotificationIcn;
	public WebElement getEleNotificationIcn(){
		return eleNotificationIcn;
	}

	@FindBy(id="com.disha.quickride:id/cancelAlert_cancelButton")
	private WebElement eleAlertYesBtn;
	public WebElement getEleAlertYesBtn(){
		return eleAlertYesBtn;
	}
/*
	@FindBy(id="com.disha.quickride:id/back_icon_ride_view")//doubt
	private WebElement eleBackRideViewIcn;
	public WebElement getEleBackRideViewIcn(){
		return eleBackRideViewIcn;
	}
	*/
	@FindBy(id="com.disha.quickride:id/description")
	private WebElement eleJoinedNotiTxt;
	public WebElement getEleJoinedNotiTxt(){
		return eleJoinedNotiTxt;
	}

	
	
	@FindBy(name="Ride started")
	private WebElement eleRideStartedTxt;
	public WebElement getEleRideStartedTxt(){
		return eleRideStartedTxt;
	}
	
	@FindBy(id="com.disha.quickride:id/backButton")
	private WebElement eleNotiBackArrowIcn;
	public WebElement getEleNotiBackArrowIcn(){
		return eleNotiBackArrowIcn;
	}
	@FindBy(name="Scheduled")
	private WebElement eleScheduledTxt;
	public WebElement getEleScheduledTxt(){
		return eleScheduledTxt;
	}
	
	@FindBy(id="com.disha.quickride:id/Positive_Action_ID")  
	private WebElement eleAcceptLnk;
	public WebElement getEleAcceptLnk(){
		return eleAcceptLnk;
	}
	@FindBy(name="QuickRide Wallet")
	private WebElement eleQRWalletNotiTxt;
	public WebElement getEleQRWalletNotifctn(){
		return eleQRWalletNotiTxt;
	}
	@FindBy(xpath="//com.disha.quickride:id/title[contains(text(),'cancelled the Pickup')]")
	private WebElement eleCancelledNotiTxt;
	public WebElement getEleCancelledNotiTxt(){
		return eleCancelledNotiTxt;
	}
	@FindBy(name="OK")
	private WebElement eleOkBtn;
	public WebElement getEleOkBtn(){
		return eleOkBtn;
	}
	@FindBy(name="Delayed")
	private WebElement eleDelayedNotiTxt;
	public WebElement getEleDelayedNotiTxt(){
		return eleDelayedNotiTxt;
	}
	@FindBy(name="Started")				
	private WebElement eleStartedTxt;
	public WebElement getEleStartedTxt(){
		return eleStartedTxt;
	}
	
	
	@FindBy(name="Invite failed")				
	private WebElement eleInviteFailedTxt;
	public WebElement getEleInviteFailedTxt(){
		return eleInviteFailedTxt;
	}
	@FindBy(name="Recharge")				
	private WebElement eleRechargeBtn;
	public WebElement getEleRechargeBtn(){
		return eleRechargeBtn;
	}
	@FindBy(name="Upcoming")				
	private WebElement eleUpcomingTab;
	public WebElement getEleUpcomingTab(){
		return eleUpcomingTab;
	}
	@FindBy(id="com.disha.quickride:id/rideType_indicator")				
	private WebElement eleBikeImageicon;
	public WebElement getEleBikeImageicon(){
		return  eleBikeImageicon;
	}
		@FindBy(name="Ride scheduled")
	private WebElement eleRideScheduledTxt;
	public WebElement getEleRideScheduledTxt(){
		return eleRideScheduledTxt;
	}
	@FindBy(name="Ride Requested")
	private WebElement eleRideRequestedTxt;
	public WebElement getEleRideRequestedTxt(){
		return eleRideRequestedTxt;
	}
	@FindBy(name="Start ride")
	private WebElement eleStartRideNotiTxt;
	public WebElement getEleStartRideNotiTxt(){
		return eleStartRideNotiTxt;
	}
	@FindBy(name="Notifications")
	private WebElement eleNotificationsTxt;
	public WebElement getEleNotificationsTxt(){
		return eleNotificationsTxt;
	}
	@FindBy(name="Requested")
	private WebElement eleRequestedTxt;
	public WebElement getEleRequestedTxt(){
		return eleRequestedTxt;
	}
	@FindBy(id="com.disha.quickride:id/back_icon_ride_view_image")
	private WebElement eleBackArrowIcon;
	public WebElement getEleBackArrowIcon(){
		return eleBackArrowIcon;
	}
	@FindBy(name="Cancelled")
	private WebElement eleCancelledTxt;
	public WebElement getEleCancelledTxt(){
		return eleCancelledTxt;
	}
	@FindBy(name="Completed")
	private WebElement eleCompletedTxt;
	public WebElement getEleCompletedTxt(){
		return eleCompletedTxt;
	}
	@FindBy(name="Reject")
	private WebElement eleRejectNotiTxt;
	public WebElement getEleRejectNotiTxt(){
		return eleRejectNotiTxt;
	}
	@FindBy(name="Find rider")
	private WebElement eleFindRiderIcn;
	public WebElement getEleFindRiderIcn(){
		return eleFindRiderIcn;
	}
	@FindBy(id="com.disha.quickride:id/matched_users_back_image")
	private WebElement eleRidrBackArrwIcn;
	public WebElement getEleRidrBackArrwIcn(){
		return eleRidrBackArrwIcn;
	}
	@FindBy(xpath="//com.disha.quickride:id/cancelRide_layout/android.widget.ImageView")
	private WebElement eleRideActionOverflow;
	public WebElement getEleRideActionOverflow(){
		return eleRideActionOverflow;
	}
	@FindBy(name="Return ride")
	private WebElement eleReturnRideOptn;
	public WebElement getEleReturnRideOptn(){
		return eleReturnRideOptn;
	}
	@FindBy(xpath="(//android:id/numberpicker_input)[2]")
	private WebElement eleMonthDateYear;
	public WebElement getEleMonthDateYear(){
		return eleMonthDateYear;
	}
	
	@FindBy(id="com.disha.quickride:id/drawer_unread_notifications_background")
	private WebElement eleNotiCountTxt;
	public WebElement getEleNotiCountTxt(){
		return eleNotiCountTxt;
	}
	
	
	@FindBy(id="com.disha.quickride:id/addToFavouritesLocation")
	private WebElement eleFavouriteIcn;
	public WebElement getEleFavouriteIcn(){
		return eleFavouriteIcn;
	}
	@FindBy(name="Add")
	private WebElement eleAddBtn;
	public WebElement getEleAddBtn(){
		return eleAddBtn;
	}
	
	@FindBy(name="Name your favourite")
	private WebElement eleNameFavTxtFld;
	public WebElement getEleNameFavTxtFld(){
		return eleNameFavTxtFld;
	}
	@FindBy(name="Save")
	private WebElement eleSaveLnk;
	public WebElement getEleSaveLnk(){
		return eleSaveLnk;
	}
	
	@FindBy(id="com.disha.quickride:id/user_address")
	private WebElement eleFavAddTxt;
	public WebElement getEleFavAddTxt(){
		return eleFavAddTxt;
	}

	@FindBy(id="com.disha.quickride:id/cancel_image")
	private WebElement eleFavCancelIcn;
	public WebElement getEleFavCancelIcn(){
		return eleFavCancelIcn;
	}
	@FindBy(name="Favourites")
	private WebElement eleFavouritesTxt;
	public WebElement getEleFavouritesTxt(){
		return eleFavouritesTxt;
	}
	
	@FindBy(id="com.disha.quickride:id/arrow_left_image")
	private WebElement eleFavBackIcn;
	public WebElement getEleFavBackIcn(){
		return eleFavBackIcn;
	}
	/*
	 *@author Diganta
	 * Description: Entering source and destination required for creating ride
	 * @param: source
	 * @param: dest
	 */
	public void rideNow(String source, String dest) {
		if(getEleEntRdStSearchIcn().isDisplayed())
		{
			getEleEntRdStSearchIcn().click();
			getEleEntrAddrssTxtField().sendKeys(source);
			getEleFirstSrcOptn().click();
			
			getEleRideNwBtn().click();
			try
			{
			if(getEleEntRdEndAddressIcn().isDisplayed())
			{
				getEleEntRdEndAddressIcn().click();
			}
			}
			finally
			{
			getEleEntrAddrssTxtField().sendKeys(dest);
			getEleFirstSrcOptn().click();
			}
		}
	}
	
	public void verifyOfferRdeBtn(){
		if(getEleOfferRideBtn().isDisplayed())
		{
		getEleOfferRideBtn().click();
		}
		else{
			getEleCreateRideBtn().click();
			}
	}
	
	
	public void upcomingTab(QRProfilePO qrProfilePo)
	{
		qrProfilePo.getEleMenuLst().click();
		qrProfilePo.getEleMyRidesBtn().click();
		getEleUpcomingTab().click();
	}
	public void completedTab(QRProfilePO qrProfilePo){
		qrProfilePo.getEleMenuLst().click();
		qrProfilePo.getEleMyRidesBtn().click();
		getEleCompletedTab().click();
	}
	
	
	public void offerRideFrmCurrLoc(String dest){
		getEleRideNwBtn().click();
		try
		{
		if(getEleEntRdEndAddressIcn().isDisplayed())
		{
			qrLog.info("Enter Ride End Search icon is displayed");
			getEleEntRdEndAddressIcn().click();
		}
		}
		finally
		{
		getEleEntrAddrssTxtField().sendKeys(dest);
		getEleFirstSrcOptn().click();
		getEleOfferRideTab().click();
		getEleOfferRideBtn().click();
		}
		
	}
	
	
	public void reqstRideFrmCurrentLoc(String dest){
		getEleRideNwBtn().click();
		try
		{
		if(getEleEntRdEndAddressIcn().isDisplayed())
		{
			qrLog.info("Enter Ride End Search icon is displayed");
			getEleEntRdEndAddressIcn().click();
		}
		}
		finally{
		getEleEntrAddrssTxtField().sendKeys(dest);
		getEleFirstSrcOptn().click();
		getEleFindRideTab().click();
		getEleRequestRideBtn().click();
		}
	}
}
