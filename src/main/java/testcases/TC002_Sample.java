package testcases;

import org.openqa.selenium.support.ui.Sleeper;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.CartPage;
import pages.HomePage;
import pages.OrderDetailsPage;
import pages.ProductDetailsPage;
import pages.ShopPage;
import pages.SignInPage;
import pages.SignUpPage;
import wrappers.AppWrappers;

public class TC002_Sample extends AppWrappers{

	@BeforeClass
	public void setData() {
		testCaseName="Registered User - END TO END ";
		testDescription="Registered User - END TO END ";
		browserName="chrome";
		dataSheetName="TC002";
		category="Smoke";
		authors=" ";
	}

	@Test(dataProvider ="fetchData")
	public void login(String userName,String password,String accUserName,String categoryName ,String ShopName ,String ProductName ,String customerName ,String mobileNumber ,String email ,String GSTIN ,String name ,String addressLine1 ,String addressLine2 ,String pincode ,String city ,String state ) throws InterruptedException
	{		
		
		
		new HomePage(driver, test)
		.clickSignin()
		.enterName(userName)
		.enterPassword(password)
		.clickSignInButton()
		.accountVerification(accUserName);
		
		
				   
		
		
		
		
		
}
}