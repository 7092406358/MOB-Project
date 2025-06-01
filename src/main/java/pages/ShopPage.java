package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Sleeper;

import com.relevantcodes.extentreports.ExtentTest;

import wrappers.AppWrappers;

public class ShopPage extends AppWrappers {
	public ShopPage(RemoteWebDriver driver, ExtentTest test){
		this.driver = driver;
		this.test = test;
	}
	public ProductPage selectShop(String ShopName ) throws InterruptedException {
		
		if(ShopName == null || ShopName.isEmpty()) {
	        System.out.println("Please enter the category");
	        reportStep("Please Enter the ShopName", "WARN");
	       driver.close();
	    }
		   try {
			   WebElement findElement = driver.findElement(By.xpath("//h4[contains(text(),'"+ShopName+"')]/../..//following-sibling::a"));
		       jSExecutorClickByXpath(findElement);
		       reportStep("The "+ShopName+" Shop is avaliable", "PASS");
		       }
		   catch(Exception e)
		      {
			   reportStep("The "+ShopName+" Shop is not avaliable", "FAIL"); 
		      }
		  return new ProductPage(driver,test) ; 		
	
}
}
