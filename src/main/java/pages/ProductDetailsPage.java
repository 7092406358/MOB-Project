package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import wrappers.AppWrappers;

public class ProductDetailsPage extends AppWrappers {
	
	public  String addedPRD;
	public ProductDetailsPage (RemoteWebDriver driver, ExtentTest test){
		this.driver = driver;
		this.test = test;	
	}
	
	public ProductDetailsPage clickAddToCart( ) throws InterruptedException {
       
		try {
			Thread.sleep(1000);
			// driver.findElement(By.xpath("//button[text()=\"Got it\"]")).click();

			clickByXpath(prop.getProperty("ProductDetails.AddToCart.Xpath"));

			reportStep("The Product is added to Cart Successfully", "PASS");
			
		} catch (Exception e) {
			
			reportStep("The Product is not able to add on Cart", "FAIL");
		}
		return this;
	}
	
	public  static String addedProductList()
	{
		WebElement addprd =driver.findElement(By.xpath("//div[@class=\"product-details-second-box\"]//self::h5"));
		  String prd = addprd.getText();
	
		return prd;
		
	}
	
	
}
