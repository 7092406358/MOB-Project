package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.relevantcodes.extentreports.ExtentTest;
import wrappers.AppWrappers;

public class ProductPage  extends AppWrappers {
	public ProductPage (RemoteWebDriver driver, ExtentTest test){
		this.driver = driver;
		this.test = test;
	}
	
   public ProductDetailsPage selectProduct(String ProductName ) {
	   if(ProductName == null || ProductName.isEmpty()) {
	        System.out.println("Please enter the category");
	        reportStep("Please Enter the ProductName", "WARN");
	       driver.close();
	    }
	   
	   try
	   {
		   System.out.println("//h6/a[contains(text(),'"+ProductName+"')]");
	      WebElement findElement = driver.findElement(By.xpath("//h6/a[contains(text(),'"+ProductName+"')]"));
	      
          jSExecutorClickByXpath(findElement);
          reportStep("The "+ProductName+" Product is avaliable", "PASS");
	   }
	   catch(Exception e)
	   {
		  reportStep("The "+ProductName+" Product not is avaliable", "FAIL");  
	   }
	   return new ProductDetailsPage(driver,test);
	}	
	
}
