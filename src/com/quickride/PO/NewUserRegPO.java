package com.quickride.PO;


import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.bcel.generic.Select;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;

import com.quickride.baselib.GenericLib;
import com.quickride.baselib.QRBaseLib;
import com.quickride.baselib.QRTestngListener;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class NewUserRegPO 
{
	AndroidDriver driver = null;
	public Logger qrLog = Logger.getLogger(this.getClass());
	
	public NewUserRegPO(AndroidDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	
	@FindBy(id = "com.disha.quickride:id/signup_text_view")
	private WebElement eleSignUpLaunchBtn;
	
	public WebElement getEleSignUpLaunchBtn()
	{
		return eleSignUpLaunchBtn;
	}
	
	@FindBy(id = "com.disha.quickride:id/reg_phoneno_editText")
	private WebElement  elePhoneNOTxtFld;
	
	public WebElement getElePhoneNOTxtFld()
	{
		return elePhoneNOTxtFld;
	}
	
	
	@FindBy(id = "com.disha.quickride:id/reg_pwd_editText")
	private WebElement elePwdTxtFld;
	
	public WebElement getElePwdTxtFld()
	{
		return elePwdTxtFld;
	}
	
	@FindBy(id = "com.disha.quickride:id/reg_name_editText")
	private WebElement eleNameTxtFld;
	public WebElement getEleNameTxtFld()
	{
		return eleNameTxtFld;
	}
	
	@FindBy(id = "com.disha.quickride:id/gender_female_radio_btn")
	private WebElement eleFemaleRdBtn;
	public WebElement getEleFemaleRdBtn()
	{
		return eleFemaleRdBtn;
	}
	@FindBy(id = "com.disha.quickride:id/gender_male_radio_btn")
	private static WebElement  eleMaleRdBtn;
	public WebElement getEleMaleRdBtn()
	{
		return eleMaleRdBtn;
	}
	
	@FindBy(id = "com.disha.quickride:id/sign_up_button")
	private WebElement  eleSignUpRegBtn;
	public WebElement getEleSignUpRegBtn()
	{
		return eleSignUpRegBtn;
	}
	
	
	@FindBy(id="com.disha.quickride:id/register_st")
	private WebElement eleThankYouTxt;
	public WebElement getEleThankYouTxt()
	{
		return eleThankYouTxt;
	}

	@FindBy(name="Facebook")
	private WebElement eleFaceBkLnk;
	public WebElement getEleFaceBkLnk()
	{
		return eleFaceBkLnk;
	}
	@FindBy(id="com.facebook.katana:id/login_login")
	private WebElement eleFBLoginBtn;
	public WebElement getEleFBLoginBtn()
	{
		return eleFBLoginBtn;
	}
	
	@FindBy(id="com.facebook.katana:id/login_username")
	private WebElement eleFBuserTxtFld;
	public WebElement getEleFBuserTxtFld()
	{
		return eleFBuserTxtFld;
	}
	@FindBy(id="com.facebook.katana:id/login_password")
	private WebElement eleFBpwdTxtFld;
	public WebElement getEleFBpwdTxtFld()
	{
		return eleFBpwdTxtFld;
	}
	
	@FindBy(name="Linkedin")
	private WebElement eleLinkedInLnk;
	public WebElement getEleLinkedInLnk()
	{
		return eleLinkedInLnk;
	}
	
	
	
	@FindBy(id="com.disha.quickride:id/activation_code_editText")
	public WebElement eleActivationTxtFld;
	public WebElement getEleActivationTxtFld()
	{
		return eleActivationTxtFld;
	}
	
	@FindBy(id="com.disha.quickride:id/activate_button")
	private WebElement eleActivateBtn;
	public WebElement getEleActivateBtn()
	{
		return eleActivateBtn;
	}
	
	
	
	
	@FindBy(name="Invalid account credentials")
	private WebElement eleInvalidAccTxt;
	public WebElement getEleInvalidAccTxt()
	{
		return eleInvalidAccTxt;
	}
	
	
	@FindBy(id="com.disha.quickride:id/alert_msg_body")
	private WebElement eleAcntExistsTxt;
	public WebElement getEleAcntExistsTxt()
	{
		return eleAcntExistsTxt;
	}
	
	@FindBy(id="com.disha.quickride:id/login_button")
	private WebElement eleLoginBtn;
	
	public WebElement getEleLoginBtn()
	{
		return eleLoginBtn;
	}
	
	
	
	@FindBy(id="com.disha.quickride:id/currentLocationText")
	private WebElement eleCurrentLocTxt;
	
	public WebElement getEleCurrentLocTxt()
	{
		return eleCurrentLocTxt;
	}
	
	
	
	@FindBy(id="com.disha.quickride:id/negative_button")
	private WebElement eleYesBtn;
	
	public WebElement getEleYesBtn()
	{
		return eleYesBtn;
	}
	@FindBy(id="com.disha.quickride:id/negative_button")
	private WebElement eleSkipBtn;
	public WebElement getEleSkipBtn()
	{
		return eleSkipBtn;
	}
	
	@FindBy(id="com.disha.quickride:id/positive_button")
	private WebElement elePopupLoginBtn;
	public WebElement getElePopupLoginBtn()
	{
		return elePopupLoginBtn;
	}
	
	
	@FindBy(id="com.disha.quickride:id/forgot_pwd_textView")
	private WebElement eleForgotPwdLnk;
	public WebElement getEleForgotPwdLnk()
	{
		return eleForgotPwdLnk;
	}
	
	@FindBy(id="com.disha.quickride:id/reset_pwd_button")
	private WebElement eleResetPwdBtn;
	public WebElement getEleResetPwdBtn()
	{
		return eleResetPwdBtn;
	}
	
	@FindBy(id="com.disha.quickride:id/login_phone_editText")
	private WebElement eleLoginPhTxtFld;
	
	public WebElement getEleLoginPhTxtFld()
	{
		return eleLoginPhTxtFld;
	}
	
	
	@FindBy(id="com.disha.quickride:id/login_pwd_editText")
	private WebElement eleLoginPwdTxtFld;
	
	public WebElement getEleLoginPwdTxtFld()
	{
		return eleLoginPwdTxtFld;
	}
	
	
	@FindBy(name="OK")
	private WebElement eleOKBtn;
	public WebElement getEleOKBtn()
	{
		return eleOKBtn;
		}	
	
	

	@FindBy(id="com.disha.quickride:id/alert_title")
	private WebElement eleAccActivatedTxt;
	public WebElement getEleAccActivatedTxt()
	{
		return eleAccActivatedTxt;
	}

	@FindBy(id="com.disha.quickride:id/alert_msg_body")
	private WebElement eleCongtrazTxt;
	public WebElement getEleCongtrazTxt()
	{
		return eleCongtrazTxt;
	}
	@FindBy(id="com.disha.quickride:id/alert_msg_recomendation")
	private WebElement eleWeRecommedTxt;
	public WebElement getEleWeRecommedTxt()
	{
		return eleWeRecommedTxt;
	}
	@FindBy(name="Profile")
	private WebElement eleProfileBtn;
	public WebElement getEleProfileBtn()
	{
		return eleProfileBtn;
	}
	
	
	
	
	@FindBy(id="com.disha.quickride:id/guest_textView")
	private WebElement eleGuestLnk;
	public WebElement getEleGuestLnk()
	{
		return eleGuestLnk;
	}
	
	
	
	@FindBy(name="Current Location")     
	private WebElement eleEnterStAdd;
	public WebElement getEleEnterStAdd()
	{
		return eleEnterStAdd;
	}
	
	/*@author: LAKSHMi BS
	 *Description: To Sign up with valid phone, pwd, name for registraion
	 */

	public void signUP(String sPhoneNum, String sPwd, String sName)
	{
		try
		{
			
			if(getEleSignUpLaunchBtn().isDisplayed())
			{
				qrLog.info("SignUp button is displayed");
				getEleSignUpLaunchBtn().click();
				getElePhoneNOTxtFld().sendKeys(sPhoneNum);
				getElePwdTxtFld().sendKeys(sPwd);
				getEleNameTxtFld().sendKeys(sName);
				driver.hideKeyboard();
				getEleFemaleRdBtn().click();
				getEleSignUpRegBtn().click();
				Assert.assertTrue(getEleThankYouTxt().isDisplayed(), "SignUp is incomplete");
				qrLog.info("Sign Up is completed");
				
			}
		}catch(Exception e)
		{
			qrLog.error("Exception in  case signUp()");
			e.printStackTrace();
			Assert.fail();
		}
		
	}

	/*@author: LAKSHMI BS
	 *Description: To Sign up with valid phone, pwd
	 */
	public void login(String sPhoneNum, String sPwd)
	{
		try
		{			
			if(getEleLoginBtn().isDisplayed())
			{
				qrLog.info("Login button is displayed");
				getEleLoginBtn().click();
				getEleLoginPhTxtFld().sendKeys(sPhoneNum);
				getEleLoginPwdTxtFld().sendKeys(sPwd);
				driver.hideKeyboard();
				getEleLoginBtn().click();
							
			}
		}catch(Exception e)
		{
			qrLog.error("Exception in  login()");
			e.printStackTrace();
			Assert.fail();
		}
		
	}

	
	public void signUPorLogin(String sPhoneNum, String sPwd, String sName) throws SQLException
	{
		String sVerificationCode=null;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		if(getEleLoginBtn().isDisplayed())
		{
			qrLog.info("Login button is displayed");
			getEleLoginBtn().click();
			getEleLoginPhTxtFld().sendKeys(sPhoneNum);
			getEleLoginPwdTxtFld().sendKeys(sPwd);
			driver.hideKeyboard();
			getEleLoginBtn().click();
			
			
		}
		try{
			Assert.assertTrue(getEleCurrentLocTxt().isDisplayed(), "Login is not Successful");
			qrLog.info("Login is completed");
			
		}catch(Exception e)
		{
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			Assert.assertTrue(getEleOKBtn().isDisplayed(), "User is registered need not sign up" );
			qrLog.info("User is not registered, need to Sign Up first");
			getEleOKBtn().click();
			getEleNameTxtFld().sendKeys(sName);
			driver.hideKeyboard();
			getEleFemaleRdBtn().click();
			getEleSignUpRegBtn().click();
			
			Assert.assertTrue(getEleThankYouTxt().isDisplayed(), "SignUp is incomplete");
			qrLog.info("Sign Up is completed");
			

			while(true)
			{
				sVerificationCode=GenericLib.getDBdata(GenericLib.getCongigValue(QRBaseLib.sConfigFile, "VERIFICATION"), "verifycode", "subject", sPhoneNum);
				System.out.println(sVerificationCode);
				if(!(sVerificationCode.isEmpty()))
				{
					break;
				}
			
			}
		
		
			getEleActivationTxtFld().sendKeys(sVerificationCode);			
			getEleActivateBtn().click();
			Assert.assertTrue(getEleAccActivatedTxt().isDisplayed(), "Account activated popup is not displayed");
			qrLog.info("Account Activated popup is successfully displayed");
			getEleSkipBtn().click();
			Assert.assertTrue(getEleCurrentLocTxt().isDisplayed(), "New Ride page is not displayed");
			qrLog.info("New Ride page is succesfully displayed");
			
		}
		
	}
	
		
	

	
}
