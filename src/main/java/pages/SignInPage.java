package pages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;

import wrappers.AppWrappers;

public class SignInPage extends AppWrappers  {

	public SignInPage(RemoteWebDriver driver, ExtentTest test){
		this.driver = driver;
		this.test = test;
	}
	// Enter username in the signin Page
	public SignInPage enterName(String userName){
		
		enterById(prop.getProperty("Signin.userName.Id"), userName);
		return this;
	}
	// Enter password in the signin Page
	public SignInPage enterPassword(String password) throws InterruptedException{
		
		enterById(prop.getProperty("Signin.password.Id"), password);
		Thread.sleep(1000);
		
		return this;
	}
	 // Click the sign in button
	public HomePage clickSignInButton() throws InterruptedException{
		 
		//  String mainWindowHandle = driver.getWindowHandle();
	//	System.out.println("before click"+mainWindowHandle);
		
			clickByLinkText("Sign in");
			//Thread.sleep(3000);
			
			//try {
			
	      // WebElement ele=driver.findElement(By.xpath("//span [@class=\"my-account-style text-white\"]"));
		//	ele.isDisplayed();
			//}
			
			/*if(Display== true)
			{
				System.out.println("account log in success");
			}
			else
			{
				System.out.println("account log in not  success");
			} 
			
			}*/
			//catch(NoSuchElementException e)
			//{
			//	System.out.println("account log in not  success");
		//	}
			
			
			
			return new HomePage(driver, test);	
	}		
			
}			
			
			
			
			
			
			
			
			
			
			
			
			
	/*	Thread.sleep(2000);
		     try {
			  WebElement m = driver.findElement(By.id("RegErrmsg"));
			  System.out.println("success"); 
		     }
			 catch(NoSuchElementException e) 				  
			 {
				 System.out.println(" not success ");
			 }
					
		
		
	
	*/
	
	



