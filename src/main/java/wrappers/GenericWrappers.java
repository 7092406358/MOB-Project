package wrappers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;

import utils.Reporter;

public class GenericWrappers extends Reporter implements Wrappers {

	public static RemoteWebDriver driver;
	protected static Properties prop;
	public String sUrl,primaryWindowHandle,sHubUrl,sHubPort;
	protected static WebDriverWait wait;

	public GenericWrappers() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(new File("./src/main/resources/config.properties")));
			sHubUrl = prop.getProperty("HUB");
			sHubPort = prop.getProperty("PORT");
			sUrl = prop.getProperty("URL");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public GenericWrappers(RemoteWebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test=test;
	}


	public void loadObjects() {
		prop = new Properties();
		try {
			prop.load(new FileInputStream(new File("./src/main/resources/object.properties")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void unloadObjects() {
		prop = null;
	}

	/**
	 * This method will launch the browser in local machine and maximize the browser and set the
	 * wait for 30 seconds and load the url
	 * @author Saravanan T
	 * @param url - The url with http or https
	 * 
	 */
	public void invokeApp(String browser) {
		invokeApp(browser,false);
	}

	/**
	 * This method will launch the browser in grid node (if remote) and maximize the browser and set the
	 * wait for 30 seconds and load the url 
	 * @author Saravanan T
	 * @param url - The url with http or https
	 * 
	 */
	public void invokeApp(String browser, boolean bRemote) {
		try {

			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setBrowserName(browser);
			dc.setPlatform(Platform.WINDOWS);

			// this is for grid run
			if(bRemote)
				driver = new RemoteWebDriver(new URL("http://"+sHubUrl+":"+sHubPort+"/wd/hub"), dc);
			else{ // this is for local run
				if(browser.equalsIgnoreCase("chrome")){
					System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
					driver = new ChromeDriver();
				}else{
					System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
					driver = new FirefoxDriver();
				}
			}

			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get(sUrl);

			primaryWindowHandle = driver.getWindowHandle();		
			reportStep("The browser:" + browser + " launched successfully", "PASS");

		} catch (Exception e) {
			e.printStackTrace();
			reportStep("The browser:" + browser + " could not be launched", "FAIL");
		}
	}

	/**
	 * This method will enter the value to the text field using id attribute to locate
	 * 
	 * @param idValue - id of the webelement
	 * @param data - The data to be sent to the webelement
	 * @author Saravanan T
	 * @throws IOException 
	 */
	public void enterById(String idValue, String data) {
		try {
			driver.findElement(By.id(idValue)).clear();
			driver.findElement(By.id(idValue)).sendKeys(data);	
			reportStep("The data: "+data+" entered successfully in field :"+idValue, "PASS");
		} catch (NoSuchElementException e) {
			reportStep("The data: "+data+" could not be entered in the field :"+idValue, "FAIL");
		} catch (Exception e) {
			reportStep("Unknown exception occured while entering "+data+" in the field :"+idValue, "FAIL");
		}
	}


	/**
	 * This method will enter the value to the text field using name attribute to locate
	 * 
	 * @param xpathValue - xpathValue of the webelement
	 * @param data - The data to be sent to the webelement
	 * @author Saravanan T
	 * @throws IOException 

	 */
	public void enterByXpath(String xpathValue, String data) {
		try {
			driver.findElement(By.xpath(xpathValue)).clear();
			driver.findElement(By.xpath(xpathValue)).sendKeys(data);
			System.out.println(xpathValue +  "  "+ data );
			reportStep("The data: "+data+" entered successfully in field :"+xpathValue, "PASS");

		} catch (NoSuchElementException e) {
			reportStep("The data: "+data+" could not be entered in the field :"+xpathValue, "FAIL");
		} catch (Exception e) {
			reportStep("Unknown exception occured while entering "+data+" in the field :"+xpathValue, "FAIL");
		}

	}
	
	public void enterByName(String nameValue, String data) {
		try {
			driver.findElement(By.name(nameValue)).clear();
			driver.findElement(By.name(nameValue)).sendKeys(data);	
			reportStep("The data: "+data+" entered successfully in field :"+nameValue, "PASS");

		} catch (NoSuchElementException e) {
			reportStep("The data: "+data+" could not be entered in the field :"+nameValue, "FAIL");
		} catch (Exception e) {
			reportStep("Unknown exception occured while entering "+data+" in the field :"+nameValue, "FAIL");
		}

	}
	
	
	

	/**
	 * This method will verify the title of the browser 
	 * @param title - The expected title of the browser
	 * @author Saravanan T
	 */
	public boolean verifyTitle(String title){
		boolean bReturn = false;
		try{
			if (driver.getTitle().equalsIgnoreCase(title)){
				reportStep("The title of the page matches with the value :"+title, "PASS");
				bReturn = true;
			}else
				System.out.println();
			reportStep("The title of the page:"+driver.getTitle()+" did not match with the value :"+title, "SUCCESS");

		}catch (Exception e) {
			reportStep("Unknown exception occured while verifying the title", "FAIL");
		}
		return bReturn;
	}

	/**
	 * This method will verify the given text matches in the element text
	 * @param xpath - The locator of the object in xpath
	 * @param text  - The text to be verified
	 * @author Saravanan T
	 */
	public void verifyTextByXpath(String xpath, String text){
		try {
			String sText = driver.findElementByXPath(xpath).getText();
			if (sText.equalsIgnoreCase(text)){
				reportStep("The text: "+sText+" matches with the value :"+text, "PASS");
			}else{
				reportStep("The text: "+sText+" did not match with the value :"+text, "FAIL");
			}
		}catch (Exception e) {
			reportStep("Unknown exception occured while verifying the title", "FAIL");
		}
	}
	
	public void verifyValueByAttribute(String xpath, String attribute ,String text ){
		try {
			String sText = driver.findElementById(xpath).getAttribute(attribute);
			if (sText.equalsIgnoreCase(text)){
				reportStep("The text: "+sText+" matches with the value :"+text, "PASS");
			}else{
				reportStep("The text: "+sText+" did not match with the value :"+text, "FAIL");
			}
		}catch (Exception e) {
			reportStep("Unknown exception occured while verifying the title", "FAIL");
		}
	}


	/**
	 * This method will close all the browsers
	 * @author Saravanan T
	 */
	public void closeAllBrowsers() {
		try {
			driver.quit();
		} catch (Exception e) {
			reportStep("The browser could not be closed.", "WARN");
		}

	}

	public void closeBrowser() {
		try {
			driver.close();
		} catch (Exception e) {
			reportStep("The browser could not be closed.", "WARN");
		}
	}

	/**
	 * This method will click the element using  as locator
	 * @param id  The id (locator) of the element to be clicked
	 * @author Saravanan T
	 */
	public void clickById(String id) {
		try{
			driver.findElement(By.id(id)).click();
			reportStep("The element with id: "+id+" is clicked.", "PASS");

		} catch (Exception e) {
			reportStep("The element with id: "+id+" could not be clicked.", "FAIL");
		}
	}

	/**
	 * This method will click the element using  link text as locator
	 * @param linkText  The linkText (locator) of the element to be clicked
	 * @author Saravanan T
	 */
	public void clickByLinkText(String linkText) {
		try{
			driver.findElement(By.linkText(linkText)).click();
			reportStep("The element with link text: "+linkText+" is clicked.", "PASS");

		} catch (Exception e) {
			reportStep("The element with link text: "+linkText+" could not be clicked.", "FAIL");
		}
	}

	/**
	 * This method will click the element using xpath as locator
	 * @param xpathVal  The xpath (locator) of the element to be clicked
	 * @author Saravanan T
	 */
	public void clickByXpath(String xpathVal) {
		try{
			driver.findElement(By.xpath(xpathVal)).click();
			reportStep("The element : "+xpathVal+" is clicked.", "PASS");
		} catch (Exception e) {
			reportStep("The element with xpath: "+xpathVal+" could not be clicked.", "FAIL");
		}
	}
	public void entervalueByXpath(String xpathValue) {
		try{
			driver.findElement(By.xpath("//p[text()="+xpathValue+"")).click();
			//reportStep("The element : "+xpathVal+" is clicked.", "PASS");
		} catch (Exception e) {
			reportStep("The element with xpath: "+xpathValue+" could not be clicked.", "FAIL");
		}
	}
	/**
	 * This method will verify whether the element is present using id as locator
	 * @param id  The id (locator) of the element to be verified
	 * @author Saravanan T
	 */
	public void isExistById(String id) {
		try{
			driver.findElement(By.id(id)).isDisplayed();
			reportStep("The element : "+id+" is exist in the page", "PASS");
		} catch (Exception e) {
			reportStep("The element with xpath: "+id+" could not be located.", "FAIL");
		}
	}

	/**
	 * This method will return the text of the element using xpath as locator
	 * @param xpathVal  The xpath (locator) of the element
	 * @author Saravanan T
	 */
	public String getTextByXpath(String xpathVal){
		String bReturn = "";
		try{
			return driver.findElement(By.xpath(xpathVal)).getText();
		} catch (Exception e) {
			reportStep("The element with xpath: "+xpathVal+" could not be found.", "FAIL");
		}
		return bReturn; 
	}

	/**
	 * This method will return the text of the element using id as locator
	 * @param xpathVal  The id (locator) of the element
	 * @author Saravanan T
	 */
	public String getTextById(String idVal) {
		String bReturn = "";
		try{
			return driver.findElementById(idVal).getText();
		} catch (Exception e) {
			reportStep("The element with id: "+idVal+" could not be found.", "FAIL");
		}
		return bReturn; 
	}
   
	
	
	
	
	/**
	 * This method will select the drop down value using id as locator
	 * @param id The id (locator) of the drop down element
	 * @param value The value to be selected (visibletext) from the dropdown 
	 * @author Saravanan T
	 */
	public void selectVisibileTextById(String id, String value) {
		try{
			new Select(driver.findElement(By.id(id))).selectByVisibleText(value);;
			reportStep("The element with id: "+id+" is selected with value :"+value, "PASS");
		} catch (Exception e) {
			reportStep("The value: "+value+" could not be selected.", "FAIL");
		}
	}

	/**
	 * This method will move the control to the last window
	 * @author Saravanan T
	 */
	public void switchToLastWindow() {
		try {
			Set<String> winHandles = driver.getWindowHandles();
			for (String wHandle : winHandles) {
				driver.switchTo().window(wHandle);
			}
		} catch (Exception e) {
			reportStep("The window could not be switched to the last window.", "FAIL");
		}
	}

	/**
	 * This method will take snapshot of the browser
	 * @author Saravanan T
	 */
	public long takeSnap(){
		long number = (long) Math.floor(Math.random() * 900000000L) + 10000000L; 
		try {
			FileUtils.copyFile(driver.getScreenshotAs(OutputType.FILE) , new File("./reports/images/"+number+".jpg"));
		} catch (WebDriverException e) {
			reportStep("The browser has been closed.", "FAIL");
		} catch (IOException e) {
			reportStep("The snapshot could not be taken", "WARN");
		}
		return number;
	}

	public void passingonXpath(String xpathValue) {
		// TODO Auto-generated method stub
		
	}
	public void verifyTextlistByXpath(String xpath, String text){
		String b="";
		try {
			List<WebElement> listOfText = driver.findElementsByXPath(xpath);
			if (listOfText.size()==0) {
				reportStep("The text: "+text+" is not available in the results", "WARN");
				System.out.println("no results");
			}
			for (WebElement a:listOfText) 
			{
	            b=a.getText();
	            b=b.toLowerCase();
	            System.out.println(b);
	            //System.out.println(b.contains(text));
	           // System.out.println(text.contains(b));
	            text= text.toLowerCase();
	            System.out.println(text);
			if (b.contains(text)){
				
				reportStep("The text: "+b+" matches with the value :"+text, "PASS");
				System.out.println("product contain");
			}
			else
			{
				reportStep("The text: "+b+" did not match with the value :"+text, "FAIL");
				System.out.println("product not contain");
			}
		    }
		    }
			catch (Exception e) {
			reportStep("Unknown exception occured while verifying the title", "FAIL");
		    }
		    }
	  
	
	public void jSExecutorClickByXpath(WebElement element) {
		try {
		     ((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
		     reportStep("The Element is clicked Successfully", "PASS");
	        }
		catch(Exception e)
		{
			reportStep("The Element is not clickable", "FAIL");	
		}
	
	}	
	
}
	
	/*public void selectTextlistByXpath(String xpath ,String text) throws InterruptedException
	{ boolean stop = false;
		try
		{
	List<WebElement> listOfText = driver.findElements(By.xpath(xpath));
	
	System.out.println(listOfText);
	String b="";
	if (listOfText.size()==0)
	    {
		System.out.println("no results");
		}
	for (WebElement a:listOfText) 
		{
	        b=a.getText();
	        
	        b=b.toLowerCase();
	        text= text.toLowerCase();
		}
		if (b.contains(text)){
			driver.findElement(By.xpath(xpath)).click();
			reportStep("The text: "+b+" matches with the value :"+text, "PASS");
			System.out.println("product contain");
			stop = true;
			
		}
		else
		{
			reportStep("The text: "+b+" did not match with the value :"+text, "FAIL");
			System.out.println("product not contain");
		}
		
		
}
	  catch(StaleElementReferenceException e){
		
		   System.out.println(e);
				}
		
	}
	
	}*/
/*	public void selectTextlinkByXpath(String textXpath ,String linkXpath,String text) throws InterruptedException
    {  

		int count =0;
  	  String b="";
  	  try {
  	  List<WebElement> listOfText = driver.findElementsByXPath(textXpath);
			if (listOfText.size()==0) {
				System.out.println("no results");
			}
			else
			
			for (WebElement a:listOfText) 
			{  
				//((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", a);
	            b=a.getText();
	            count++;
	            b=b.toLowerCase();
	            System.out.println(b);
	            System.out.println(text.contains(b));
	            text= text.toLowerCase();
	            System.out.println(text);
	            System.out.println(count);
			
			if (b.contains(text)){
				
				WebElement findElement = driver.findElement(By.xpath(("("+linkXpath+")"+"["+count+"]")));
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.scrollBy(0,1000)", "");
				// ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", findElement);
				
				//js.executeScript("arguments[0].click()", findElement);
				 Thread.sleep(5000);
				findElement.click();
				 //Thread.sleep(5000);
				System.out.println("product contain");
				
			}
			else
			{
				
				System.out.println("product not contain");
			}
			}
  	       }
  	        
			catch(StaleElementReferenceException e)
			{
				System.out.println("");
			}
			
  	  
  	 
}
}*/

	      
		
		
	


	
	

	

