package com.quickride.PO;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.thoughtworks.selenium.webdriven.commands.KeyEvent;

import io.appium.java_client.android.AndroidDriver;

public class MyProfilePO {
	AndroidDriver driver = null;
	public Logger qrLog = Logger.getLogger(this.getClass());
	public MyProfilePO(AndroidDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(id = "android:id/up")
	private WebElement  eleMenuBar;

	public WebElement getEleMenuBar()
	{
		return eleMenuBar;
	}
	
	@FindBy(id = "com.disha.quickride:id/profile_arrow")
	private  WebElement eleArrowBtn;
	
	public 	WebElement getEleArrowBtn(){
		return eleArrowBtn;
	}
	
	@FindBy(id="com.disha.quickride:id/user_profile_edit_save_text")
	private  WebElement eleEditLnk;
	
	public 	WebElement getEleEditLnk(){
		return eleEditLnk;
	}

	@FindBy(id ="com.disha.quickride:id/edit_profile_image")
	private  WebElement eleUploadIcn;
	
	public 	WebElement getEleUploadIcn(){
		return eleUploadIcn;
	}
	
	@FindBy(id="android:id/text1")
	private WebElement eleTakePhotoIcn;
	
	public WebElement getEleTakePhotoIcn(){
		return eleTakePhotoIcn;
	}
	
	@FindBy(id="com.android.camera2:id/shutter_button")
	private WebElement eleCameraBtn;
	
	public WebElement getEleCameraBtn(){
		return eleCameraBtn;
	}
	@FindBy(id="com.android.camera2:id/done_button")
	private WebElement elePressBtn;
	
	public WebElement getElePressBtn(){
		return elePressBtn;
	}
	@FindBy(name="enter company name")
	private WebElement eleCompanyTxtFld;
	
	public WebElement getEleCompanyTxtFld(){
		return eleCompanyTxtFld;
	}
	@FindBy(id="com.disha.quickride:id/removeTextForCompanyLocation")
	private WebElement eleRemoveCompanyTxt;
	
	public WebElement getEleRemoveCompanyTxt(){
		return eleRemoveCompanyTxt;
	}
	@FindBy(id ="com.disha.quickride:id/designation")
	private WebElement eleDesignTxtFld;
	
	public WebElement getEleDesignTxtFld(){
		return eleDesignTxtFld;
	}
	@FindBy(id ="com.disha.quickride:id/company_name")
	private WebElement eleCompanyIcn;
	
	public WebElement getEleCompanyIcn(){
		return eleCompanyIcn;
	}
	@FindBy(id ="com.moblynx.galleryics:id/filtershow_done")
	private WebElement eleSaveIcn;
	
	public WebElement getEleSaveIcn(){
		return eleSaveIcn;
	}
	

	@FindBy(id = "com.disha.quickride:id/forgot_pwd_textView")
		private WebElement  eleForgotLnk;
		
		public WebElement getEleForgotLnk()
		{
			return eleForgotLnk ;
		}
	@FindBy(id ="com.disha.quickride:id/phoneNo_editText")
		private WebElement  elePhoneTxtFld;
		public WebElement getElePhoneTxtFld()
		{
			return elePhoneTxtFld;
		}
		@FindBy(id ="com.disha.quickride:id/reset_pwd_button")
		private WebElement  eleResetBtn;
		public WebElement getEleResetBtn()
		{
			return eleResetBtn;
		}
		
		@FindBy(name="Password")
		private WebElement elePwdBtn;
		
		public WebElement getElePwdBtn()
		{
			return elePwdBtn ;
		}
		@FindBy(id ="com.disha.quickride:id/old_password_editText")
		private WebElement eleCurrentPwdTxtFld;
		
		public WebElement getEleCurrentPwdTxtFld()
		{
			return eleCurrentPwdTxtFld;
		}
		
		@FindBy(id ="com.disha.quickride:id/new_password_editText")
		private WebElement eleNewPwdTxtFld;
		
		public WebElement getEleNewPwdTxtFld()
		{
			return eleNewPwdTxtFld;
		}
		@FindBy(id ="com.disha.quickride:id/confirm_password_editText")
		private WebElement eleConfirmPwdTxtFld;
		
		public WebElement getEleConfirmPwdTxtFld()
		{
			return eleConfirmPwdTxtFld;
		}
		@FindBy(id ="com.disha.quickride:id/changepassword_button")
		private WebElement eleChngPwdBtn;
		
		public WebElement getEleChngPwdBtn(){
			return  eleChngPwdBtn;
		}
		
		
		
		@FindBy(id ="com.disha.quickride:id/desc")
		private WebElement eleVehicleDetailsLnk;
		
		public WebElement getEleVehicleDetailsLnk(){
			return eleVehicleDetailsLnk;
		}
		@FindBy(id ="com.disha.quickride:id/vehicle_number") 
		private WebElement eleVehicleNoTxtFld;
		
		public WebElement getEleVehicleNoTxtFld(){
			return eleVehicleNoTxtFld;
		}
		@FindBy(id ="com.disha.quickride:id/icon_car_models_list")
		private WebElement eleVehModelOption;
		
		public WebElement getEleVehModelOption(){
			return eleVehModelOption;
		}
		@FindBy(name ="Sedan")                       
		private WebElement eleSedanItm;
		
		public WebElement getEleSedanItm(){
			return eleSedanItm;
		}
		
		@FindBy(id="com.disha.quickride:id/fare_field")                        
		private WebElement eleFareTxtFld;
		
		public WebElement getEleFareTxtFld(){
			return eleFareTxtFld ;
		}

		@FindBy(id="com.disha.quickride:id/vehicle_capacity_field")                        
		private WebElement eleSeatsTxtFld;
		
		public WebElement getEleSeatsTxtFld(){
			return eleSeatsTxtFld;
		}
		@FindBy(name="Edit Profile")                        
		private WebElement eleEditProfileTxt;
		
		public WebElement getEleEditProfileTxt(){
			return eleEditProfileTxt;
		}
		@FindBy(name="My Profile")                        
		private WebElement eleMyProfileTxt;
		
		public WebElement getEleMyProfileTxt(){
			return eleMyProfileTxt;
		}
		
		@FindBy(name="Save")                        
		private WebElement eleSaveLnk;
		
		public WebElement getEleSaveLnk(){
			return eleSaveLnk;
		}
		@FindBy(id="com.disha.quickride:id/company_name_selected")                        
		private WebElement eleCompanyLst;
		
		public WebElement getEleCompanyLst(){
			return eleCompanyLst;
		}
		
		@FindBy(name="Premium")		
		private static WebElement elePremiumCarIcn;
		public WebElement getElePremiumCarIcn()
		{
			return elePremiumCarIcn;
		}
	
		@FindBy(id="com.disha.quickride:id/vehicle_capacity_field")		
		private static WebElement eleOfferdSeatsTxtFld;
		public WebElement getEleOfferdSeatsTxtFld()
		{
			return  eleOfferdSeatsTxtFld;
		}
		
		@FindBy(name="Bike")		
		private static WebElement eleBikeIcn;
		public WebElement getEleBikeIcn()
		{
			return eleBikeIcn;
		}
		@FindBy(id="com.disha.quickride:id/profile_actionbar_backPress_image")		
		private static WebElement eleArrowIcn;
		public WebElement getEleArrowIcn()
		{
			return eleArrowIcn;
		}
		
		@FindBy(id="com.disha.quickride:id/chat_icon_rideview")		
		private static WebElement eleChatBtn;
		public WebElement getEleChatBtn()
		{
			return  eleChatBtn;
		}
		
		@FindBy(id="com.disha.quickride:id/addPassengers")		
		private static WebElement eleAddPassIcn;
		public WebElement getEleAddPassIcn()
		{
			return  eleAddPassIcn;
		}
		@FindBy(id ="com.disha.quickride:id/search_icon")
		private WebElement eleSearchBtn;
		
		public WebElement getEleSearchBtn()
		{
			return eleSearchBtn;
		}
		@FindBy(id ="com.disha.quickride:id/gender_checkBox")
		private WebElement eleSameGender;
		
		public WebElement getEleSameGender()
		{
			return eleSameGender;
		}
		@FindBy(id ="com.disha.quickride:id/company_checkBox")
		private WebElement eleSameCompany;
		
		public WebElement getEleSameCompany()
		{
			return eleSameCompany;
		}
		@FindBy(id ="com.disha.quickride:id/myConstraints")
		private WebElement eleRidePrefLnk;
		
		public WebElement getEleRidePrefLnk()
		{
			return eleRidePrefLnk;
		}
		
		@FindBy(id ="com.disha.quickride:id/user_desc_layout")
		private WebElement eleRideAddress;
		
		public WebElement getEleRideAddress()
		{
			return eleRideAddress;
		}
		@FindBy(id ="com.disha.quickride:id/backButton")
		private WebElement eleFindRideArrow;
		
		public WebElement getEleFindRideArrow()
		{
			return eleFindRideArrow;
		}
		@FindBy(id="com.disha.quickride:id/user_profile_edit_facebookprofile")
		private  WebElement eleProfileFBIcn;
		
		public 	WebElement getEleProfileFBIcn(){
			return eleProfileFBIcn;
		}
		@FindBy(id="com.disha.quickride:id/user_profile_edit_linkedinprofile")
		private  WebElement eleProfileLinkedInIcn;
		
		public 	WebElement getEleProfileLinkedInIcn(){
			return eleProfileLinkedInIcn;
		}
		@FindBy(id="com.disha.quickride:id/user_profile_edit_twitterprofile")
		private  WebElement eleProfileTwitterIcn;
		public 	WebElement getEleProfileTwitterIcn(){
			return eleProfileFBIcn;
		}
		@FindBy(id="com.disha.quickride:id/webViewId")
		private  WebElement eleSocialWebPge;
		public 	WebElement getEleSocialWebPge(){
			return eleSocialWebPge;
		}
		//com.disha.quickride:id/top_layout
		/*@Test id:Usercong_01
		 * @description:Test User setting the profile details without Vehicle on new Account Activation Picture from camera
		 * @author:Raghukiran MR
		 * 
		 */
	
	public void userConfig(String designation,String company ){
		try{
		
			getEleMenuBar().click();
			Assert.assertTrue(getEleArrowBtn().isDisplayed(), "Profile icon is not displayed");
			qrLog.info("Profile icon is displayed");
			getEleArrowBtn().click();
			getEleEditLnk().click();
			getEleDesignTxtFld().sendKeys(designation);
			getEleCompanyIcn().click();
			
			getEleCompanyTxtFld().sendKeys(company);
			getEleCompanyLst().click();
			getEleUploadIcn().click();
			getEleTakePhotoIcn().click();
			getEleCameraBtn().click();
			getElePressBtn().click();
			eleSaveIcn.click();
			getEleSaveLnk().click();
			
			Assert.assertTrue(getEleEditLnk().isDisplayed(), "Upload is not successful");
			qrLog.info("Upload of profile photo is successful");
			
		
		}catch(Exception e)
		{
			qrLog.error("Exception in userConfig()");
			e.printStackTrace();
			Assert.fail();
		}
				
		
	}
	public void forgotPassword(String sPhoneNum, NewUserRegPO newUserRegPo){
		try{
			
		newUserRegPo.getEleLoginBtn().click();
		Assert.assertTrue(getEleForgotLnk().isDisplayed(),"Forgot link is not displayed");
		qrLog.info("Forgot link is  displayed");
		
		getEleForgotLnk().click();
		getElePhoneTxtFld().sendKeys(sPhoneNum);
		Assert.assertTrue( getElePhoneTxtFld().isDisplayed(), "PhoneNumber is not dispalyed");
		qrLog.info("PhoneNumber is  displayed");
		getEleResetBtn().click();
		
		}catch(Exception e)
		{
			qrLog.error("Exception in forgotPassword()");
			e.printStackTrace();
			Assert.fail();
		}
	}
	public void changePassword(String Current,String Password,String Confpass,NewUserRegPO newUserRegPo){
		try{
			getEleMenuBar().click();
			Assert.assertTrue(getElePwdBtn().isDisplayed(), "Password option is not displayed");
			qrLog.info("Password option is  displayed");
			
			getElePwdBtn().click();
			getEleCurrentPwdTxtFld().sendKeys(Current);
			getEleNewPwdTxtFld().sendKeys(Password);
			getEleConfirmPwdTxtFld().sendKeys(Confpass);
			driver.hideKeyboard();
			
			getEleChngPwdBtn().click();
			Assert.assertTrue(newUserRegPo.getEleCurrentLocTxt().isDisplayed(), "Change Password failed");
			qrLog.info("Change Password was successful");
			
			}catch(Exception e)
		{
			qrLog.error("Exception in changePassword()");
			e.printStackTrace();
			Assert.fail();
		}
		
		
		
		
	}
	public void vehicleDetails(String Vehiclenum,String Fare,String Offeredseats){
		try{
			
			
			getEleMenuBar().click();
			Assert.assertTrue(getEleArrowBtn().isDisplayed(), "Profile icon is not displayed");
			qrLog.info("Profile icon is displayed");
			getEleArrowBtn().click();
			getEleEditLnk().click();
			driver.scrollTo("Vehicle details");
			Assert.assertTrue(getEleVehicleDetailsLnk().isDisplayed(), "Vehicle link is not displayed");
			qrLog.info("Vehicle link is displayed");
			getEleVehicleDetailsLnk().click();
			getEleVehicleNoTxtFld().sendKeys(Vehiclenum);
			getEleVehModelOption().click();
			getEleSedanItm().click();
			getEleFareTxtFld().sendKeys(Fare);
			getEleSeatsTxtFld().sendKeys(Offeredseats);
			getEleSaveLnk().click();
			
			}catch(Exception e)
		{
			qrLog.error("Exception in vehicleDetails() ");
			e.printStackTrace();
			Assert.fail();
		}
		
		
		
		
	}


	
	public void customSeats(String sFare,String sOffersseats){
		
		
		elePremiumCarIcn.click();
		getEleFareTxtFld().clear();
		getEleFareTxtFld().sendKeys(sFare);
		getEleOfferdSeatsTxtFld().clear();
		getEleOfferdSeatsTxtFld().sendKeys(sOffersseats);
		getEleSaveLnk().click();
		
	}
	public void vehicledetails(String vehiclenumber,String fare,String offerseats){
		try{
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			if(getEleVehicleDetailsLnk().isDisplayed())
			{
				getEleVehicleDetailsLnk().click();
				qrLog.info("Vehicle details tap successfully");
			}
			
		}catch(Exception e)
		{
			qrLog.info("Vehicle details filled successfully");
		}
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			
				getEleVehicleNoTxtFld().clear();
				getEleVehicleNoTxtFld().sendKeys(vehiclenumber);//Adding the vehicle number from datashreet
				Assert.assertTrue(getEleVehModelOption().isDisplayed(), " Taxi Vehicle number is not added ");
				qrLog.info("Taxi vehicle number is added successfully");
				getEleVehModelOption().click();
				Assert.assertTrue(getEleSedanItm().isDisplayed(), "Seadan drop down list is not selected");
				qrLog.info("Seadan drop down list is  selected");
				getEleSedanItm().click();
				getEleFareTxtFld().clear();
				getEleFareTxtFld().sendKeys(fare);
				getEleOfferdSeatsTxtFld().clear();
				getEleOfferdSeatsTxtFld().sendKeys(offerseats);
				getEleSaveLnk().click();
				
			}
	public void bikeSupport(String vehiclenumber){
		try{
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			if(getEleVehicleDetailsLnk().isDisplayed())
			{
				getEleVehicleDetailsLnk().click();
				qrLog.info("Vehicle details tap successfully");
			}
			
		}catch(Exception e)
		{
			qrLog.info("Vehicle details filled successfully");
		}
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				getEleVehicleNoTxtFld().clear();
				getEleVehicleNoTxtFld().sendKeys(vehiclenumber);//Adding the vehicle number from datashreet
				Assert.assertTrue(getEleVehModelOption().isDisplayed(), " Taxi Vehicle number is not added ");
				qrLog.info("Taxi vehicle number is added successfully");
				getEleVehModelOption().click();
				Assert.assertTrue(getEleBikeIcn().isDisplayed(), "Bike drop down is not selected");
				qrLog.info("Bike drop down is  selected");
				getEleBikeIcn().click();
				Assert.assertTrue(getEleSaveLnk().isDisplayed(), "default fare of 3rs should not displayed");
				qrLog.info("default fare of 3rs should be dispalyed");
				getEleSaveLnk().click();
				
	}
	public void firstHandling(){
		try{
			if(getEleSearchBtn().isDisplayed()){
				getEleSearchBtn().click();
				
			}
		}catch(Exception e){
			
		}
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		getEleArrowIcn().click();
	}
	
}

	