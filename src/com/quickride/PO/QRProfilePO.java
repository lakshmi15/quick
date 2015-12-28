package com.quickride.PO;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import io.appium.java_client.android.AndroidDriver;
public class QRProfilePO {

	AndroidDriver driver = null;
	public Logger qrLog = Logger.getLogger(this.getClass());
	
	public QRProfilePO(AndroidDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	
	@FindBy(id="android:id/up")
	private WebElement eleMenuLst;
	
	public WebElement getEleMenuLst()
	{
		return eleMenuLst;
	}

	@FindBy(name="Profile")
	private WebElement eleProfileTxt;
	public WebElement getEleProfileTxt()
	{
		return eleProfileTxt;
	}

	@FindBy(xpath="//com.disha.quickride:id/alert_msg_body[text()='Register to enjoy services']")		// INCOMPLETE
	private static WebElement eleRegisterTxt;
	public WebElement getEleRegisterTxt()
	{
		return eleRegisterTxt;
	}
	
	
	@FindBy(id="com.disha.quickride:id/positive_button")		// INCOMPLETE
	private static WebElement eleSignUpBtn;
	public WebElement getEleSignUpBtn()
	{
		return eleSignUpBtn;
	}
	
	
	@FindBy(name="My Rewards")		
	private static WebElement eleMyRewardsBtn;
	public WebElement getEleMyRewardsBtn()
	{
		return eleMyRewardsBtn;
	}
	@FindBy(name="My Rides")
	private static WebElement eleMyRidesBtn;
	public WebElement getEleMyRidesBtn()
	{
		return eleMyRidesBtn;
	}
	@FindBy(name="New Ride")		
	private static WebElement eleNewRideBtn;
	public WebElement getEleNewRideBtn()
	{
		return eleNewRideBtn;
	}
	@FindBy(name="Logout")		
	private static WebElement eleLogoutBtn;
	public WebElement getEleLogoutBtn()
	{
		return eleLogoutBtn;
	}
		
	@FindBy(xpath="android.widget.TextView[@contains(text()='Do you want to logout?')]")
	private WebElement eleDoYouWnLogoutTxt;
	public WebElement getEleDoYouWnLogoutTxt()
	{
		return eleDoYouWnLogoutTxt;
	}
	@FindBy(name="Yes")
	private WebElement eleYesBtn;
	
	public WebElement getEleYesBtn()
	{
		return eleYesBtn;
	}

	@FindBy(name="No")
	private WebElement eleNoBtn;
	
	public WebElement getEleNoBtn()
	{
		return eleNoBtn;
	}

	@FindBy(name="Quick Ride")
	private WebElement eleQuickRideTxt;
	
	public WebElement getEleQuickRideTxt()
	{
		return eleQuickRideTxt;
	}

	
	/*@author: LAKSHMi BS
	 *Description: To logout from QuickRide
	 */
	public void logout()			
	{
		try{
			NewUserRegPO newUserRegPo = new NewUserRegPO(driver);
			getEleMenuLst().click();
			getEleLogoutBtn().click();
			getEleYesBtn().click();
			Assert.assertTrue(newUserRegPo.getEleLoginBtn().isDisplayed(), "Failed to Logout from QuickRide");
			qrLog.info("Logout is successful");
		}
		catch(Exception e)
		{
			e.getStackTrace();
			Assert.fail();
		}
			
			
		
	}
	
	
}

	

