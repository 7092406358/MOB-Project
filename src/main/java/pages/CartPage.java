package pages;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;

import wrappers.AppWrappers;

public class CartPage  extends AppWrappers{
	//public String addedPRD;
	 
	
	public CartPage(RemoteWebDriver driver, ExtentTest test){
		this.driver = driver1;
		this.test = test;
	}
	
	public CartPage clickCartIcon() throws InterruptedException
	{
		clickById(prop.getProperty("Cart.CartIcon.Id"));
		Thread.sleep(1000);
		return this;
	}			

	public OrderDetailsPage clickCheckOutButton() throws InterruptedException
	{
		
		WebDriverWait w = new WebDriverWait(driver,3);
	      
	      w.until(ExpectedConditions.presenceOfElementLocated (By.xpath("//h5[text()=\"My Cart\"]")));
	
		clickByXpath(prop.getProperty("Cart.CheckOut.Xpath"));
		return new OrderDetailsPage (driver,test);
	}
	
	
	
	public CartPage cartVerification() throws InterruptedException
	{ 
		String name =ProductDetailsPage.addedProductList();
		System.out.println("added product1"+name);
		
	    WebElement rt1 = driver.findElement(By.xpath("//div[@class=\"description-list-cart slider-box-desc\"]//self::h4"));
	    String cartprd =rt1.getText();
	    System.out.println("added product2"+cartprd);
	   
	    if(name.equals(cartprd))
	   {
		   System.out.println("added product is matching");
	   }
	   else
	   {
		   System.out.println("added product is not matching");
	   }
		return this;
	}
}




	/*public static  boolean cmpStr(String str1, String str2) {
        if (str1.length() != str2.length()) {
            System.out.println("Length mismatch.");
            return false;
        }
        if (str1.contains(" ") || str2.contains(" ")) {
            String[] a1 = str1.split(" ");
            String[] a2 = str2.split(" ");
            if (a1.length != a2.length) {
                System.out.println("Split Length mismatch.");
                return false;
            }
            for (int i = 0; i < a1.length; i++) {
                if (!a1[i].equals(a2[i])) {
                    System.out.println("One of split mismatch." + a1[i]  + " " + a2[i] );
                    return false;
                }
            }
        } else {
            if (!str1.equals(str2)) {
                System.out.println("Regular equals returns false.");
                return false;
            }
        }
        return true;
    }*/
	
	
	

