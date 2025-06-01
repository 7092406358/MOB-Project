package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.CartPage;
import pages.HomePage;
import wrappers.AppWrappers;
public class TC001_Sample  extends AppWrappers{
	
	@BeforeClass
	public void setData() {
		testCaseName="Registered User - END TO END ";
		testDescription="Registered User - END TO END ";
		browserName="chrome";
		dataSheetName="TC001";
		category="Smoke";
		authors=" ";
	}

	@Test(dataProvider ="fetchData")
	public void login(String userName, String password, String accUserName, String categoryName, String ShopName,
			String ProductName, String customerName, String mobileNumber, String email, String GSTIN, String name,
			String addressLine1, String addressLine2, String pincode, String city, String state, String nameBA,
			String addressLine1BA, String addressLine2BA, String pincodeBA, String cityBA, String stateBA,
			String paymentMode, String bank) throws InterruptedException

	   {		
		
		new HomePage(driver, test)
		.clickSignin()
		.enterName(userName)
		.enterPassword(password)
		.clickSignInButton()
		.accountVerification(accUserName)
		.selectCategories(categoryName) 
		.selectShop(ShopName)
		.selectProduct(ProductName)
		.clickAddToCart();
		
		new CartPage (driver,test)
		.clickCartIcon()
		.cartVerification()
		.clickCheckOutButton()
		
		.enterCustomerDetails(name, mobileNumber, email, GSTIN)
		.enterDeliveryAddress(name, addressLine1, addressLine2, pincode, city, state)
		.enterBillingAddress(nameBA, addressLine1BA, addressLine2BA, pincodeBA, cityBA, stateBA)
		.clickConfirmOrderNow()
		.selectPaymentMethod(paymentMode, bank);
				
	
}
}
