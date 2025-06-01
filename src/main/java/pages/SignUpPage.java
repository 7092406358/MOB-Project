package pages;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import wrappers.AppWrappers;

public class SignUpPage extends AppWrappers  {

	public SignUpPage(RemoteWebDriver driver, ExtentTest test){
		this.driver = driver;
		this.test = test;

		
	}

	// Enter name in the signup Page
	public SignUpPage enterName(String name){
		enterById(prop.getProperty("Signup.Name.Id"), name);
		return this;
	}

	// Enter mobile no in the signup Page
	public SignUpPage enterMobile(String mobile){
		enterById(prop.getProperty("Signup.Mobile.Id"), mobile);
		return this;
	}
	
	// Enter password in the signup Page
	public SignUpPage enterPassword(String password){
		enterById((prop.getProperty("Signup.Password.Id")), password);
		return this;
	}

	// Enter confirm password in the signup Page 
	public SignUpPage enterConfirmPassword(String confirmPassword )
	{  
		enterById(prop.getProperty("Signup.ComfirmPassword.Id"),confirmPassword);
		return this;
	}
	
	// click the signinwithOTP in the signup Page
	public SignUpPage clickSignInWithOTPButton() {
		clickById(prop.getProperty("Signup.SignUpWithOTP.Id"));
		return this;
	}

}
