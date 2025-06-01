package pages;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import com.relevantcodes.extentreports.ExtentTest;

import net.bytebuddy.asm.Advice.Exit;
import wrappers.AppWrappers;

public class HomePage extends AppWrappers {

	public HomePage(RemoteWebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		if (!verifyTitle("MOB-Grow your business online with us")) {
			reportStep("This is not MOB Home Page", "FAIL");
		}
	}

	public SignUpPage clickSignup() {
		clickByXpath(prop.getProperty("Home.SignUp.Xpath"));
		clickById(prop.getProperty("Home.SignUpTab.Id"));
		return null;
	}

	public SignInPage clickSignin() {
		String mainWindowHandle = driver.getWindowHandle();
		System.out.println("before click"+mainWindowHandle);
		
		clickByXpath(prop.getProperty("Home.SignIn.Xpath"));
		return new SignInPage(driver, test);
	}

	public ShopPage selectCategories(String categoryName) throws InterruptedException {

		if (categoryName == null || categoryName.isEmpty()) {
			System.out.println("Please enter the category");
			reportStep("Please Enter the categoryName", "WARN");
			driver.close();
		}

		try {

			WebElement findElement = driver.findElement(
					By.xpath("//figcaption[contains(text(),'" + categoryName + "')]/../following-sibling:: a"));
			jSExecutorClickByXpath(findElement);
			reportStep("The " + categoryName + " category is avaliable", "PASS");
		} catch (Exception e) {
			reportStep("The " + categoryName
					+ " category is not avaliable ","FAIL");
		}
		return new ShopPage(driver, test);
	}

	public RFQPage clickRFQButton() throws InterruptedException {
		WebElement findElement = driver.findElement(By.xpath("//button[text()=\"Request For Quotation (RFQ) \"]"));
		jSExecutorClickByXpath(findElement);
		return new RFQPage(driver, test);
	}

	public HomePage clickLogoutButton() throws InterruptedException {
		clickById("dropdownMenuButton1");
		clickByXpath("//span[text()=\"Logout\"]");
		Thread.sleep(5000);
		clickByXpath("//button[text()=\"Yes\"]");
		Thread.sleep(50000);
		return null;
	}

	public HomePage accountVerification(String accUserName) {
		String text = driver.findElement(By.id("dropdownMenuButton1")).getText();
		System.out.println(text);
		if (accUserName.equalsIgnoreCase(text)) {
			reportStep("The Log In Profile name is matching", "PASS");
			// System.out.println("Log In account is Correct");
		} else {
			reportStep("The Log In Profile name is not matching", "FAIL");
			// System.out.println("Log In account is InCorrect");
		}

		return this;
	}
}
