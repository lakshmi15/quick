package com.quickride.PO;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.quickride.baselib.GenericLib;
import com.quickride.baselib.QRBaseLib;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class MyRewardsPO 
{
	AndroidDriver driver = null;
	public Logger qrLog = Logger.getLogger(this.getClass());
	
	public MyRewardsPO(AndroidDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	

	@FindBy(id="com.disha.quickride:id/recharge_button")
	private WebElement eleRechargeBtn;
	public WebElement getEleRechargeBtn()
	{
		return eleRechargeBtn;
	}
	
	
	@FindBy(id="com.disha.quickride:id/recharge_points_editText")
	private WebElement eleAmountTxtFld;
		public WebElement getEleAmountTxtFld()
	{
		return eleAmountTxtFld;
	}
		
	@FindBy(id="com.disha.quickride:id/login")
	private WebElement elePayULogin;
	
	public WebElement getElePayULogin()
	{
		return elePayULogin;
	}
	
	

	@FindBy(name="Account")
	private WebElement eleAccountTab;
	
	public WebElement getEleAccountTab()
	{
		return eleAccountTab;
		
	}
	
	@FindBy(name="Encash")		
	private WebElement eleEncashTab;
	
	public WebElement getEleEncashTab()
	{
		return eleEncashTab;
		
	}
	

	
	@FindBy(name="My Rewards")		
	private WebElement eleMyRewardsTxt;
	
	public WebElement getEleMyRewardsTxt()
	{
		return eleMyRewardsTxt;
		
	}
	

	
	
	
	@FindBy(id="com.disha.quickride:id/encash")		
	private WebElement eleEncashBtn;
	
	public WebElement getEleEncashBtn()
	{
		return eleEncashBtn;
		
	}
	@FindBy(id="com.disha.quickride:id/encash_points")		
	private WebElement eleEncashTxtFld;
	
	public WebElement getEleEncashTxtFld()
	{
		return eleEncashTxtFld;
		
	}
	
	
	
	@FindBy(id="com.disha.quickride:id/balancepoints_encash")		
	private WebElement eleBalEncashTxt;
	
	public WebElement getEleBalEncashTxt()
	{
		return eleBalEncashTxt;
		
	}
	
	
	@FindBy(id="com.disha.quickride:id/balancepoints_recharge")		
	private WebElement eleBalReChargeTxt;
	
	public WebElement getEleBalReChargeTxt()
	{
		return eleBalReChargeTxt;
		
	}

	
	@FindBy(id="com.disha.quickride:id/common_error_alert_body")		
	private WebElement eleEncashPopupTxt;
	
	public WebElement getEleEncashPopupTxt()
	{
		return eleEncashPopupTxt;
		
	}
	
	@FindBy(name="OK")
	private WebElement eleOKBtn;
	public WebElement getEleOKBtn()
	{
		return eleOKBtn;
		
	}
	
	@FindBy(name="com.disha.quickride:id/alert_title")
	private WebElement eleEncashTxt;
	public WebElement getEleEncashTxt()
	{
		return eleEncashTxt;
		
	}

	@FindBy(name="Share & Earn")
	private WebElement eleShareEarnTxt;
	public WebElement getEleShareEarnTxt()
	{
		return eleShareEarnTxt;
		
	}
@FindBy(name="Share and get free ride points.  ")
	private WebElement eleShareTxt;
	public WebElement getEleShareTxt()
	{
		return eleShareTxt;
		
	}
	
	@FindBy(name="Later")
	private WebElement eleLaterBtn;
	public WebElement getEleLaterBtn()
	{
		return eleLaterBtn;
		
	}	
	
	
	@FindBy(name="Share")
	private WebElement eleShareBtn;
	public WebElement getEleShareBtn()
	{
		return eleShareBtn;
		
	}	


	public void getEncashPts(QRProfilePO qrProfilePo)
	{
		
		String sMessage;
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		try{
		if(getEleEncashPopupTxt().isDisplayed())
		{
			qrLog.info("Bonus points cant be encashed popup is displayed as there are no encash points");
			sMessage=getEleEncashPopupTxt().getText();
			qrLog.info(sMessage);
			getEleOKBtn().click();
			qrProfilePo.logout();
		}
		}
		catch(Exception e)
		{
	
			qrLog.info("Points encashed successfully");
			Assert.assertTrue(getEleShareTxt().isDisplayed(), "Share and get free ride text is not displayed");
			qrLog.info("Share and get free ride text is  displayed");
			
			getEleLaterBtn().click();
			Assert.assertTrue(getEleMyRewardsTxt().isDisplayed(), "Share later is not successfull");
			
			qrLog.info("Encash is successfull");
			
		
			
		}
		
		
	}
	public void getEncashPtsShare(QRProfilePO qrProfilePo)
	{
		
		String sMessage;
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		try{
		if(getEleEncashPopupTxt().isDisplayed())
		{
			qrLog.info("Bonus points cant be encashed popup is displayed as there are no encash points");
			sMessage=getEleEncashPopupTxt().getText();
			qrLog.info(sMessage);
			getEleOKBtn().click();
			qrProfilePo.logout();
		}
		}
		catch(Exception e)
		{
	
			qrLog.info("Points encashed successfully");
			Assert.assertTrue(getEleShareTxt().isDisplayed(), "Share and get free ride text is not displayed");
			qrLog.info("Share and get free ride text is  displayed");
			
			getEleShareBtn().click();
			Assert.assertTrue(getEleShareEarnTxt().isDisplayed(), "Share later is not successfull");
			
			qrLog.info("Encash is successfull");
			
		
			
		}
		
		
	}
	
	
	
	public void rechargeAmt(String sAmt, String sPhone, String sPwd,MyRewardsPO myRewardsPo, NewUserRegPO newUserRegPo, QRProfilePO qrProfilePo, FeedbackPO feedbackPo) throws SQLException
	{
		
	
	myRewardsPo.getEleAmountTxtFld().sendKeys(sAmt);
	driver.hideKeyboard();
	qrLog.info("Recharging Balance to  more than 100");
	
	
	myRewardsPo.getEleRechargeBtn().click(); 
	Assert.assertTrue(myRewardsPo.getElePayULogin().isDisplayed(), "PayUMoney page is not displayed");
	qrLog.info("PayUMoney page is displayed");
	
	
	GenericLib.setDBdata(GenericLib.getCongigValue(QRBaseLib.sConfigFile, "ACCOUNT"), "balance", sAmt, "id",sPhone);
	driver.resetApp();
	
	do
	{
	
	if(newUserRegPo.getEleLoginBtn().isDisplayed())
	{
		qrLog.info("Login button is displayed");
		newUserRegPo.getEleLoginBtn().click();
		newUserRegPo.getEleLoginPhTxtFld().sendKeys(sPhone);
		newUserRegPo.getEleLoginPwdTxtFld().sendKeys(sPwd);
		driver.hideKeyboard();
		newUserRegPo.getEleLoginBtn().click();
		Assert.assertTrue(feedbackPo.getEleArrowIcn().isDisplayed(), "Login is not Successful");
		qrLog.info("Login is completed");
	
	}
	feedbackPo.getEleArrowIcn().click();
	driver.navigate().back();
	
	qrProfilePo.getEleMenuLst().click();
	qrProfilePo.getEleMyRewardsBtn().click();

	if(myRewardsPo.getEleBalReChargeTxt().getText().equals(sAmt))
	{
		Assert.assertTrue(myRewardsPo.getEleBalReChargeTxt().getText().equals(sAmt), "Failed to set Points to less than 100");
					
		break;
	}else
	{
		qrLog.info("Restarting app as db update is taking long");
		driver.resetApp();
		
	}
	}while(true);
	}

}
