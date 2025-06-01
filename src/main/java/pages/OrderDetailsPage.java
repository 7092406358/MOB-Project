package pages;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;

import wrappers.AppWrappers;

public class OrderDetailsPage extends AppWrappers {
	public OrderDetailsPage(RemoteWebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
	}

	public OrderDetailsPage enterCustomerDetails(String name, String mobileNumber, String email, String GSTIN)
			throws InterruptedException {
		driver.findElement(By.xpath("//button[text()=\"Got it\"]")).click();
		Thread.sleep(1000);

		if (name == null || name.isEmpty()) {
			System.out.println("Please enter the CustomerName");
			reportStep("Please Enter the CustomerName", "WARN");
			driver.close();
		} else {

			WebElement Name = driver.findElement(By.id("CustomerName"));
			Name.clear();
			Name.sendKeys(name);
		}

		if (mobileNumber == null || mobileNumber.isEmpty()) {
			System.out.println("Please Enter the Mobile Number");
			driver.close();
		} else if (mobileNumber.length() != 10) {
			System.out.println("Please Enter the Vaild Mobile Number");
			driver.close();
		} else {
			enterById(prop.getProperty("CustomerDetails.MobileNo.Id"), mobileNumber);
			verifyValueByAttribute(prop.getProperty("CustomerDetails.MobileNo.Id"), "value", mobileNumber);
		}

		// MailID
		enterById(prop.getProperty("CustomerDetails.Mail.Id"), email);
		verifyValueByAttribute(prop.getProperty("CustomerDetails.Mail.Id"), "value", email);

		// GSTIN
		enterById(prop.getProperty("CustomerDetails.GST.Id"), GSTIN);
		verifyValueByAttribute(prop.getProperty("CustomerDetails.GST.Id"), "value", GSTIN);

		// enterById(prop.getProperty("CustomerDetails.couponCode.Id"),couponCode);
		return this;
	}

	// DeliveryAddress
	public OrderDetailsPage enterDeliveryAddress(String name, String addressLine1, String addressLine2, String pincode,
			String city, String state) {
		// DeliveryAddress - Name
		if (name == null || name.isEmpty()) {
			System.out.println("Please enter the Name");
			reportStep("Please Enter the Name", "WARN");
			driver.close();
		} else {
			enterById(prop.getProperty("DeliveryAddress.Name.Id"), name);
			verifyValueByAttribute(prop.getProperty("DeliveryAddress.Name.Id"), "value", name);
		}
		// AddressLine1
		if (addressLine1 == null || addressLine1.isEmpty()) {
			System.out.println("Please enter the Name");
			reportStep("Please Enter the Name", "WARN");
			driver.close();
		} else {
			enterById(prop.getProperty("DeliveryAddress.AddressLine1.Id"), addressLine1);
			verifyValueByAttribute(prop.getProperty("DeliveryAddress.AddressLine1.Id"), "value", addressLine1);
		}

		// AddressLine2
		if (addressLine2 == null || addressLine2.isEmpty()) {
			System.out.println("Please enter the Name");
			reportStep("Please Enter the Name", "WARN");
			driver.close();
		} else {
			enterById(prop.getProperty("DeliveryAddress.AddressLine2.Id"), addressLine2);
			verifyValueByAttribute(prop.getProperty("DeliveryAddress.AddressLine2.Id"), "value", addressLine2);
		}

		// Pincode
		if (pincode == null || pincode.isEmpty()) {
			System.out.println("Please enter the pincode");
			reportStep("Please Enter the pincode", "WARN");
			driver.close();
		} else if (pincode.length() != 6) {
			System.out.println("Please Enter the Valid Pincode");
		} else {
			enterById(prop.getProperty("DeliveryAddress.Pincode.Id"), pincode);
			verifyValueByAttribute(prop.getProperty("DeliveryAddress.Pincode.Id"), "value", pincode);

		}
		// Town/City
		if (city == null || city.isEmpty()) {
			System.out.println("Please enter the city");
			reportStep("Please Enter the pincode", "WARN");
			driver.close();
		} else {
			enterById(prop.getProperty("DeliveryAddress.Town/City.Id"), city);
			verifyValueByAttribute(prop.getProperty("DeliveryAddress.Town/City.Id"), "value", city);
		}

		// state
		if (state == null || state.isEmpty()) {
			System.out.println("Please enter the state");
			reportStep("Please Enter the state", "WARN");
			driver.close();
		} else {
			enterById(prop.getProperty("DeliveryAddress.State.Id"), state);
			verifyValueByAttribute(prop.getProperty("DeliveryAddress.State.Id"), "value", state);
		}

		// clickById("chkBillingAddress");
		return this;
	}

	public OrderDetailsPage enterBillingAddress(String name, String addressLine1, String addressLine2, String pincode,
			String city, String state) {
		enterById(prop.getProperty("BillingAddress.Name.Id"), name);
		verifyValueByAttribute(prop.getProperty("BillingAddress.Name.Id"), "value", name);

		enterById(prop.getProperty("BillingAddress.AddressLine1.Id"), addressLine1);
		verifyValueByAttribute(prop.getProperty("BillingAddress.AddressLine1.Id"), "value", addressLine1);

		enterById(prop.getProperty("BillingAddress.AddressLine2.Id"), addressLine2);
		verifyValueByAttribute(prop.getProperty("BillingAddress.AddressLine2.Id"), "value", addressLine2);

		enterById(prop.getProperty("BillingAddress.Pincode.Id"), pincode);
		verifyValueByAttribute(prop.getProperty("BillingAddress.Pincode.Id"), "value", pincode);

		enterById(prop.getProperty("BillingAddress.Town/City.Id"), city);
		verifyValueByAttribute(prop.getProperty("BillingAddress.Town/City.Id"), "value", city);

		enterById(prop.getProperty("BillingAddress.State.Id"), state);
		verifyValueByAttribute(prop.getProperty("BillingAddress.State.Id"), "value", state);

		return this;

	}

	public OrderDetailsPage clickConfirmOrderNow() throws InterruptedException {
		WebElement button = driver.findElement(By.id(prop.getProperty("OrderDetails.conformOrderBt.Id")));
		jSExecutorClickByXpath(button);
		return this;
	}

	public OrderDetailsPage selectPaymentMethod(String paymentMode,String bank) throws InterruptedException {

		// switch to control to frame
		WebDriverWait wait1 = new WebDriverWait(driver, 30);
		wait1.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));

        
		WebElement fr = driver.findElement(By.xpath("//div[@id=\"merchant-desc\"]"));
		String dd = fr.getText();
		System.out.println(dd);
		
		String parentWindow = driver.getWindowHandle();
		System.out.println(parentWindow);
		
		
		
		if (paymentMode == null || paymentMode.isEmpty()) {
			System.out.println("Please enter the paymentMode");
			reportStep("Please Enter the paymentMode", "WARN");
			driver.close();
		}
		
		else if (paymentMode != null) 
		{
			String mode = driver.findElement(By.xpath("//div[text()='" + paymentMode + "']")).getText();
			if (paymentMode.equals(mode)) {
				
                //selecting payment mode
				driver.findElement(By.xpath("//div[text()='" + paymentMode + "']")).click();
				reportStep("The paymentMode is selected Successfully", "PASS");
				
				//selecting bank
				if (bank == null || bank.isEmpty()) {
					System.out.println("Please select the bank");
					reportStep("Please select the bank", "WARN");
					driver.close();
				} else if (bank != null) {
					String bankName = driver.findElement(By.xpath("//div[text()='" + bank + "']")).getText();
					if (bankName.equals(bank)) {

						driver.findElement(By.xpath("//div[text()='" + bank + "']")).click();
						reportStep("The" + bank + " bank is selected Successfully", "PASS");
					} else {
						System.out.println("The given bank is Not avaliable or mention as per the Application ");
						reportStep("The " + bank + "bank is not selected ", "FAIL");
					}

				}

			} else {
				System.out.println(
						"The " + paymentMode + " PaymentMethod is Not avaliable or mention as per the Application ");
				reportStep("The " + paymentMode + "paymentMode is not selected ", "FAIL");
			}
		    }
		
		
		
		
		
		
	/*	clickByXpath(prop.getProperty("OrderDetails.selectBank.Xpath"));
		
		clickByXpath(prop.getProperty("OrderDetails.payButton.Xpath"));

		String winHandleBefore = driver.getWindowHandle();
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		
		// clickByXpath("OrderDetails.successButton.Xpath");
		FluentWait wait = new FluentWait(driver);
		// Specify the timout of the wait
		wait.withTimeout(5000, TimeUnit.MILLISECONDS);
		// Specify polling time
		wait.pollingEvery(50, TimeUnit.MILLISECONDS);
		reportStep("The page is navigated to the payment page", "PASS");
		String parentWindow1 = driver.getWindowHandle();
		System.out.println(parentWindow1);

		WebElement findElement = driver.findElement(By.xpath("//button[text()=\"Success\"]"));

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", findElement);

		Thread.sleep(6000);
		Set<String> allWindowHandles = driver.getWindowHandles();

	/*	for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
       // Getting the Order Id 
		String text = driver.findElement(By.partialLinkText("OD")).getText();
		if (text != null) {
			System.out.println(text);
			reportStep("The Order Placed Successfully and Order ID" + text + " ", "PASS");
		} else {
			System.out.println("order not placed" + text);
			reportStep("The Order not Placed Successfully", "FAIL");
		}*/
		return this;
	}

}
