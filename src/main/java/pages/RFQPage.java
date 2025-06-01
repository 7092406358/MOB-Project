package pages;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Sleeper;

import com.relevantcodes.extentreports.ExtentTest;

import wrappers.AppWrappers;

public class RFQPage extends AppWrappers {

	public RFQPage (RemoteWebDriver driver, ExtentTest test){
		this.driver = driver;
		this.test = test;	
	}
	
	public RFQPage enterName(String name) throws InterruptedException
    {
    	enterById(prop.getProperty("RFQ.name.Id"), name);
    	Thread.sleep(3000);
		return this;	
    }
	public RFQPage enterMobile(String Mobile) throws InterruptedException
    {
		enterById(prop.getProperty("RFQ.mobile.Id"), Mobile);
		Thread.sleep(3000);
		return this;	
    }
	public RFQPage enterEmail(String Email) throws InterruptedException
    {
		enterById(prop.getProperty("RFQ.email.Id"), Email);
		Thread.sleep(3000);
		return this;	
    }
	public RFQPage enterDesc(String Desc) throws InterruptedException
    {Thread.sleep(3000);
		enterById(prop.getProperty("RFQ.comment.Id"), Desc);
		return this;	
    }
	public RFQPage uploadFile() 
    {
		enterById(prop.getProperty("RFQ.chooseFile.Id"), "C:\\Users\\GI Tech Games\\Desktop\\download.jpg");
		
		return this;	
    }
	public RFQPage clickSubmit() throws InterruptedException
    {
		clickById("btnRFQ");
		Thread.sleep(50000);
		reportStep("The RFQ submitted", "PASS");
		return this;	
    }
	
}
