package ctbautomation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.logging.LogEntries; // To capture console logs of a website
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
import org.testng.Assert;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.time.Duration;

public class ctbtest {
	
  public static WebDriver driver;
  public static WebDriverWait wait;
  public static ExtentReports report;
  public static ExtentTest test;
  /**
   * declare the Tabs variable at the class level so that it can be accessed by all methods
   * */
  ArrayList<String> Tabs;
 
  @BeforeSuite
  public void StartBrowser() {
    InitializeExtentReport();
    InitializeChromeDriver();
    String URLLink ="https://app-cartobike.lusites.xyz/";
    NavigationURL(URLLink);
    InitializeImpliciteWait();
    HandleCookiesPopUp();
    
    //explicit wait
    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  }/*
  @AfterMethod
  public void afterMethod() {
	  if (result.getStatus() == ITestResult.SKIP) {
          System.out.println("Test Skipped: " + result.getStatus());
          // You can perform any necessary actions for skipped tests here
      }
  }*/
  // capture Screenshot
  /**
   * destination is the location of the screenshot captured
   * 
   * 
   * */
  public static String capture(WebDriver driver) throws IOException {
    File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    //String destination = "C:\\Users\\Lz-6\\eclipse-workspace\\cartobike_test\\test-output\\extendReport\\screenshots\\" + System.currentTimeMillis() + ".png";
    String destination = "C:\\Users\\Asus\\Ctb\\test-output\\extendReport\\screenshots" + System.currentTimeMillis() + ".png";
    File Dest = new File(destination);
    String errflpath = Dest.getAbsolutePath();
    FileHandler.copy(scrFile, Dest);
    return errflpath;
  }
  //Capture Console Method
  /**
   * Capture browser console logs using LogEntries 
   * 
   * @param LogEntry entry Add console logs to the report  
   *  
   * */
  public void CaptureConsoleLog() {
    LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);	
    for (LogEntry entry: logEntries) {
      test.log(LogStatus.INFO, "Browser Console Log: " + entry.getMessage());
    }
  }
  
  // Initial Extent Report Method
  /**
   * report object use for Extent Report.
   * 
   * @param Environment is used let us now about environment config.
   * 
   * @param OS is used for operating System.
   * 
   * @param Reporter is used to let you know who is testing.
   * 
   * @param Designation is use to let you know the Designation of the tester.
   * 
   * Use addSysteminfo to add necessary info in the extent report.
   * 
   * create static method to avoid redundancy when using same method in different class
   * */
  public void InitializeExtentReport() {   
    //report = new ExtentReports("C:\\\\Users\\\\Lz-6\\\\eclipse-workspace\\\\cartobike_test\\\\test-output\\\\extendReport\\\\CartoBikeBugReport.html", true);
    report = new ExtentReports("C:\\\\Users\\\\Asus\\\\Ctb\\\\test-output\\\\extendReportreports\\\\CartoBikeBugReport.html", true);
    report.addSystemInfo("Environment", "Google Chrome Version 119.0.6045.160 (Official Build) (64-bit)");
    report.addSystemInfo("OS", "Windows 10");
    report.addSystemInfo("Reporter", "Abhishek Kalotra");
    report.addSystemInfo("Designation", "Senior QA Engineer");
  }
  
  
  // Initialize Chrome Driver Method
  /**
   * @param webdriver.chrome.silentOutput setting up property to suppress the warning
   * 
   * @param webdriver.chrome.driver use for location of Chrome driver
   * 
   * @param options use to add Chrome options
   * 
   * driver.manage().window().maximize() use to maximize browser windows
   * 
   * */
  public void InitializeChromeDriver() {
    System.setProperty("webdriver.chrome.silentOutput", "true");
    //System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\Required Software\\chromedriver-win64\\chromedriver.exe");   
    System.setProperty("webdriver.chrome.driver", "E:\\Selenium\\Chromedriver\\chromedriver-win64\\chromedriver.exe");   
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--remote-allow-origins=*");
    driver = new ChromeDriver(options);
    driver.manage().window().maximize();
  }
  //add navigate URL
  public void NavigationURL(String URLLink) {   
    driver.navigate().to(URLLink);
  }
  //implicit wait
  public void InitializeImpliciteWait() {    
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
  }
  //Handle Cookies PopUp
  public void HandleCookiesPopUp() {    
    WebElement CookiesPopUp = driver.findElement(By.xpath("//div[@id='root']/div/div/div[2]/button[1]"));
    CookiesPopUp.click();
  }
  // Web Element Method
  public WebElement findElementBylocator(By locator) {
    return driver.findElement(locator);
  }
  // Capture Page URL Method
  public void CapturePageURL() {    
    test.log(LogStatus.INFO, "Page URL: <a href='" + driver.getCurrentUrl() + "' target='_blank'>" + driver.getCurrentUrl() + "</a>");
  }
  //Test Passed Method
  public void TestPassed(String message) {	  
    test.log(LogStatus.PASS, message);
  }
  //capture screenshot
  public void TestFailed(String failmessage) throws IOException {    
    test.log(LogStatus.INFO, "<b> Screenshot </b>");
    test.log(LogStatus.FAIL, test.addScreenCapture(capture(driver)) + failmessage);
  }
  // Refresh Page Method
  public void RefreshPage() {
    driver.navigate().refresh();
  }
  // Switch Brower Tab Method
  public void SwitchTab(int tabNumber){  
	  Tabs = new ArrayList<String> (driver.getWindowHandles());
	  if(Tabs.size() > 1)
	  {
	  driver.switchTo().window(Tabs.get(tabNumber));
	  System.out.println("the current URL is " + driver.getCurrentUrl());
	  }
	  else {
		  System.out.println("There is only one tab could not switch");
	  }	  
  }
  public void SwitchTab_0() {
	  driver.switchTo().window(Tabs.get(0));
  }
  public void openNewTab() {
	  driver.switchTo().newWindow(WindowType.TAB);// open new tab
  }
  public void switchToIFrame(WebElement iFrameElement) { // to find element in iframe
      driver.switchTo().frame(iFrameElement);
  }
  public void switchToDefaultContent() {
	  driver.switchTo().defaultContent();
  }
  public void Clear(WebElement element, String textValue) // clear fields
  {
	      if (textValue != null)
          element.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
  }
  //To test the login functionality when invalid email and invalid password is entered.
  @Test

  public void Test01() throws IOException {

    test = report.startTest("To test the login functionality when invalid email and invalid password is entered.");
    test.log(LogStatus.INFO, "<b> Steps to reproduce</b>");
    test.log(LogStatus.INFO, "visit the cartobike website");
    test.log(LogStatus.INFO, "click on login button");

    //find the login button
    By LoginButtonLocator = By.tagName("Button");
    WebElement Login = findElementBylocator(LoginButtonLocator);
    Login.click();

    // implicit wait
    InitializeImpliciteWait();
    test.log(LogStatus.INFO, "login page will open now add the login credentials");
    
    // find element by xpath
    By EmailLocator = By.xpath("//*/div[@class='MuiOutlinedInput-root MuiInputBase-root MuiInputBase-colorPrimary MuiInputBase-formControl textBgWhite emotion-cache-ehy43w'][1]/input[@id='outlined-basic']");
    WebElement LoginEmail = findElementBylocator(EmailLocator);
    LoginEmail.click();
    LoginEmail.sendKeys("vk00we@yopmail.com");
   
    By PasswordLocator = By.xpath("//*/div[@class='MuiOutlinedInput-root MuiInputBase-root MuiInputBase-colorPrimary MuiInputBase-formControl MuiInputBase-adornedEnd emotion-cache-fqp8j8'][1]/input[@id='outlined-basic']");
    WebElement LoginPassword = findElementBylocator(PasswordLocator);
    LoginPassword.click();
    LoginPassword.sendKeys("ak@125478");

    test.log(LogStatus.INFO, "click on login");
    By LoginAccountLocator = By.id("myButton");
    WebElement LoginButton = findElementBylocator(LoginAccountLocator);
    LoginButton.click();

    // implicit wait
    InitializeImpliciteWait();

    try {
      //using explicit wait
      WebElement EmailError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*/span[@class='error']")));
      EmailError.getText();

      //Compare the results
      if (EmailError.getText().equals("The email doesn't exist.")) {

        String message = "Test Passed as the user is not able to login";
        TestPassed(message);

      } else {
        String failmessage = "the auctual and the expected results does not match";
        TestFailed(failmessage);
        CapturePageURL();
        CaptureConsoleLog();

      }
    } catch (NoSuchElementException e) {

      // capture screenshot
      //test.log(LogStatus.FAIL, test.addScreenCapture(capture(driver)) + "Test Failed" + e.getMessage());
      String failmessage = "Test Failed" + e.getMessage();
      TestFailed(failmessage);

    }

  }
  @Test(dependsOnMethods = "Test01")

  public void Test02() throws IOException {

    RefreshPage(); // Page Refresh
    test = report.startTest("To test the login functionality when valid email and invalid password is entered.");

    test.log(LogStatus.INFO, "<b> Steps to reproduce</b>");
    test.log(LogStatus.INFO, "In the login page add valid email and invalid password");

    // find element by xpath
    WebElement LoginEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*/div[@class='MuiOutlinedInput-root MuiInputBase-root MuiInputBase-colorPrimary MuiInputBase-formControl textBgWhite emotion-cache-ehy43w'][1]/input[@id='outlined-basic']")));
    LoginEmail.click();
    LoginEmail.sendKeys("vk3114@yopmail.com");

    By PasswordLocator = By.xpath("//*/div[@class='MuiOutlinedInput-root MuiInputBase-root MuiInputBase-colorPrimary MuiInputBase-formControl MuiInputBase-adornedEnd emotion-cache-fqp8j8'][1]/input[@id='outlined-basic']");
    WebElement LoginPassword = findElementBylocator(PasswordLocator);
    LoginPassword.click();
    LoginPassword.sendKeys("ak@125478");

    test.log(LogStatus.INFO, "click on login button");
    By LoginAccountLocator = By.id("myButton");
    WebElement LoginButton = findElementBylocator(LoginAccountLocator);
    LoginButton.click();

    // implicit wait
    InitializeImpliciteWait();

    try {

      //using explicit wait
      WebElement InvalidEmailPasswordError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*/div[1]/p[@class='MuiFormHelperText-root Mui-error emotion-cache-j7o63n']")));
      InvalidEmailPasswordError.getText();

      //Compare the results		        
      if (InvalidEmailPasswordError.getText().equals("Invalid email or password.")) {
        String message = "Test Passed";
        TestPassed(message);
      } else {
        String failmessage = "the auctual and the expected results does not match ";
        TestFailed(failmessage);
        CapturePageURL();
        CaptureConsoleLog();
      }

    } catch (NoSuchElementException e) {

      String failmessage = "Test Failed" + e.getMessage();
      TestFailed(failmessage);
    }

  }
  @Test(dependsOnMethods = "Test02")

  public void Test03() throws IOException {

    RefreshPage(); // Page Refresh
    test = report.startTest("To test the login functionality when invalid email and valid password is entered.");

    test.log(LogStatus.INFO, "<b> Steps to reproduce</b>");
    test.log(LogStatus.INFO, "In the login page add invalid email and valid password");
    // find element by xpath
    WebElement LoginEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*/div[@class='MuiOutlinedInput-root MuiInputBase-root MuiInputBase-colorPrimary MuiInputBase-formControl textBgWhite emotion-cache-ehy43w'][1]/input[@id='outlined-basic']")));
    LoginEmail.click();
    LoginEmail.sendKeys("v13114@yopmail.com");

    By PasswordLocator = By.xpath("//*/div[@class='MuiOutlinedInput-root MuiInputBase-root MuiInputBase-colorPrimary MuiInputBase-formControl MuiInputBase-adornedEnd emotion-cache-fqp8j8'][1]/input[@id='outlined-basic']");
    WebElement LoginPassword = findElementBylocator(PasswordLocator);
    LoginPassword.click();
    LoginPassword.sendKeys("Jaisurya@123");

    test.log(LogStatus.INFO, "click on login button");
    By LoginAccountLocator = By.id("myButton");
    WebElement LoginButton = findElementBylocator(LoginAccountLocator);
    LoginButton.click();

    // implicit wait
    InitializeImpliciteWait();

    try {

      //using explicit wait
      WebElement InvalidEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error")));
      InvalidEmail.getText();

      //Compare the results
      if (InvalidEmail.getText().equals("The email doesn't exist.")) {
        String message = "Test Passed";
        TestPassed(message);

      } else {

        String failmessage = "the auctual and the expected results does not match ";
        TestFailed(failmessage);
        CapturePageURL();
        CaptureConsoleLog();

      }

    } catch (NoSuchElementException e) {

      String failmessage = "Test Failed" + e.getMessage();
      TestFailed(failmessage);

    }

  }

  @Test(dependsOnMethods = "Test03")

  public void Test04() throws IOException, InterruptedException {
    RefreshPage(); // Page Refresh
    test = report.startTest("To test the login functionality when valid email and valid password is entered.");

    test.log(LogStatus.INFO, "<b> Steps to reproduce</b>");
    test.log(LogStatus.INFO, "In the login page add valid email and valid password");
    // find element by xpath
    WebElement LoginEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*/div[@class='MuiOutlinedInput-root MuiInputBase-root MuiInputBase-colorPrimary MuiInputBase-formControl textBgWhite emotion-cache-ehy43w'][1]/input[@id='outlined-basic']")));
    // LoginEmail.click();
    LoginEmail.sendKeys("fehiloqic1@yopmail.com");

    By PasswordLocator = By.xpath("//*/div[@class='MuiOutlinedInput-root MuiInputBase-root MuiInputBase-colorPrimary MuiInputBase-formControl MuiInputBase-adornedEnd emotion-cache-fqp8j8'][1]/input[@id='outlined-basic']");
    WebElement LoginPassword = findElementBylocator(PasswordLocator);
    LoginPassword.click();
    LoginPassword.sendKeys("Ak@11111111");

    test.log(LogStatus.INFO, "click on login button");

    By LoginAccountLocator = By.id("myButton");
    WebElement LoginButton = findElementBylocator(LoginAccountLocator);
    LoginButton.click();

    // implicit wait
    InitializeImpliciteWait();

    try {
      String ExpectedTitle = "Cartobike is the website for classified ads for bikes, cars - CarToBike";
      wait.until(ExpectedConditions.titleIs(ExpectedTitle));
      String ActualTitle = driver.getTitle();
      Assert.assertEquals(ActualTitle, ExpectedTitle);
      String message = "Test Passed";
      TestPassed(message);
      Thread.sleep(2000);
      // implicit wait
      InitializeImpliciteWait();
      WebElement SwitchProfile = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("text-link")));
      SwitchProfile.click();

      WebElement Signout = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("sign_out")));
      Signout.click();

    } catch (AssertionError e) {
      String failmessage = "The user is not able to login";
      TestFailed(failmessage);
      CapturePageURL();
      CaptureConsoleLog();

    }

  }
  
  @Test(dependsOnMethods = "Test04")
  	public void Test05() throws IOException{
  	
  	test = report.startTest("To Test that all the URLs of the login page are working or not ");
  	test.log(LogStatus.INFO,"<b>Steps to reproduce</b> ");
  	test.log(LogStatus.INFO, "locate all the url on the footer of the login page");
  	test.log(LogStatus.INFO, "click on the URLs are verify if they are correct or not");
  	
  	//Implicity wait
  	InitializeImpliciteWait();
  	
  	//Facebook URL
  	String ExceptedFacebookURL = "https://www.facebook.com/people/Cartobike/100083365571144/";
  	
  	By FacebookURLLocator = By.xpath("//button[@class='MuiButton-root MuiButton-text MuiButton-textPrimary MuiButton-sizeMedium MuiButton-textSizeMedium MuiButtonBase-root links emotion-cache-1cild3t'][1]");
  	WebElement FacebookURL = findElementBylocator(FacebookURLLocator);
  	FacebookURL.click();
  	// implicit wait
  	InitializeImpliciteWait();
    // Switch browser tab  
  	int tab = 1; //using for tab count
  	SwitchTab(tab);
      
    String AuctualFacebookURL = driver.getCurrentUrl();
    try
      {
      	Assert.assertEquals(AuctualFacebookURL, ExceptedFacebookURL);
      	String testpass = "The Actual Facebook URL does match with Excepted URL";
      	TestPassed(testpass);
      }
      catch(AssertionError e)
      {
    	String testfail = "The Actual Facebook URL does not match with Excepted URL";
    	TestFailed(testfail);  
    	CapturePageURL();
         
      }
      // implicit wait
      InitializeImpliciteWait();
      driver.close();
      
      // Switch browser tab  
      SwitchTab_0();
      
      
      //Instagram URL
      String ExpectedInstagramURL = "https://www.instagram.com/cartobike_/?igshid=YmMyMTA2M2Y%3D";
      
      By InstagramURLLocator = By.xpath("//button[@class='MuiButton-root MuiButton-text MuiButton-textPrimary MuiButton-sizeMedium MuiButton-textSizeMedium MuiButtonBase-root links emotion-cache-1cild3t'][2]");
      WebElement InstagramURL = findElementBylocator(InstagramURLLocator);
      InstagramURL.click();
  	// implicit wait
      InitializeImpliciteWait();
      SwitchTab(tab);
      
      String ActualInstagramURL = driver.getCurrentUrl();
      
      try
      {
      	Assert.assertEquals(ActualInstagramURL, ExpectedInstagramURL);
      	
      	String testpass = "The Actual Instagram URL does match with Excepted URL";
      	TestPassed(testpass);
      }
      catch(AssertionError e)
      {
    	String testfail = "The Actual Instagram URL does not match with Excepted URL";
      	TestFailed(testfail);
      	CapturePageURL();
      }
   // implicit wait
      InitializeImpliciteWait();
      driver.close();
      SwitchTab_0();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
      
      
      //TikTok URL
      String ExpectedTikTokURL = "https://www.tiktok.com/@cartobike?_t=8ZhOvsioPaw&_r=1";
      
      By TikTokURLLocator = By.xpath("//button[@class='MuiButton-root MuiButton-text MuiButton-textPrimary MuiButton-sizeMedium MuiButton-textSizeMedium MuiButtonBase-root links emotion-cache-1cild3t'][3]");
      WebElement TikTokURL = findElementBylocator(TikTokURLLocator);
      TikTokURL.click();
  	// implicit wait
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
      SwitchTab(tab);
      
      String ActualTikTokURL = driver.getCurrentUrl();
      
      try
      {
      	Assert.assertEquals(ActualTikTokURL, ExpectedTikTokURL);
          
      	String testpass = "The Actual TikTok URL does match with Excepted URL";
        TestPassed(testpass);
      }
      catch(AssertionError e)
      {
    	  String testfail = "The Actual TikTok URL does not match with Excepted URL";
    	  TestFailed(testfail);
    	  CapturePageURL();
      }
      // implicit wait
      InitializeImpliciteWait();
      driver.close();
      SwitchTab_0();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
      
      //Twitter URL
      String ExpectedTwitterURL = "https://twitter.com/cartobike";
      
      By TwitterURLLocator= By.xpath("//button[@class='MuiButton-root MuiButton-text MuiButton-textPrimary MuiButton-sizeMedium MuiButton-textSizeMedium MuiButtonBase-root links emotion-cache-1cild3t'][4]");
      WebElement TwitterURL = findElementBylocator(TwitterURLLocator);
      TwitterURL.click();
  	// implicit wait
      InitializeImpliciteWait();
      SwitchTab(tab);
      
      String ActualTwitterURL = driver.getCurrentUrl();
      
      try
      {
      	Assert.assertEquals(ActualTwitterURL, ExpectedTwitterURL);
          
      	String testpass= "The Actual Twitter URL does match with Excepted URL";
        TestPassed(testpass);
      }
      catch(AssertionError e)
      {
    	  String testfail ="The Actual Twitter URL does not match with Excepted URL";
    	  TestFailed(testfail);
    	  CapturePageURL();
      }
   // implicit wait
      InitializeImpliciteWait();
      driver.close();
      SwitchTab_0();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
     
      
      String ExpectedSnapchatURL = "https://www.snapchat.com/add/cartobike";
      
      By SnapchatURLLocator = By.xpath("//button[@class='MuiButton-root MuiButton-text MuiButton-textPrimary MuiButton-sizeMedium MuiButton-textSizeMedium MuiButtonBase-root links emotion-cache-1cild3t'][5]");
      WebElement SnapchatURL = findElementBylocator(SnapchatURLLocator);
      SnapchatURL.click();
  	// implicit wait
      InitializeImpliciteWait();
      SwitchTab(tab);
      
      String ActualSnapchatURL = driver.getCurrentUrl();
      
      try
      {
      	Assert.assertEquals(ActualSnapchatURL, ExpectedSnapchatURL);
          
          String testpass ="The Actual Snapchat URL does match with Excepted URL";
          TestPassed(testpass);
      }
      catch(AssertionError e)
      {
      	String testfail = "The Actual Snapchat URL does not match with Excepted URL";
      	TestFailed(testfail);
      	CapturePageURL();
      }
   // implicit wait
      InitializeImpliciteWait();
      driver.close();
      SwitchTab_0();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
   }
  
  @Test(dependsOnMethods = "Test05")
  public void Test06() throws IOException
  {
	  test = report.startTest("To Test that all the Legal Document URLs of the login page are working or not ");
	  test.log(LogStatus.INFO,"<b>Steps to reproduce</b> ");
	  test.log(LogStatus.INFO, "locate all the Legal Document URL on the footer of the login page");
	  test.log(LogStatus.INFO, "click on the URLs are verify if they are correct or not");
	  String ExpectedTermsURL = "https://app-cartobike.lusites.xyz/en/terms";
	  By TermsURLLocator = By.linkText("Terms");
	  WebElement Terms = findElementBylocator(TermsURLLocator);
	  Terms.click();
	  InitializeImpliciteWait();
	  String ActualTermURL = driver.getCurrentUrl();	  
	  try {
		  Assert.assertEquals(ActualTermURL, ExpectedTermsURL);
		  String testpass = "The Actual Term URL and the Expected Terms URL are the same";
		  TestPassed(testpass);
	  }
	  catch(AssertionError e) {
		  String testfail = "The Actual Term URL and the Expected Terms URL does not match";
		  TestFailed(testfail);
		  CapturePageURL();
		  
	  }
	  InitializeImpliciteWait();
	  driver.navigate().back();
	  System.out.println(driver.getCurrentUrl());
	  InitializeImpliciteWait();
	  String ExpectPrivacyPolicyURL = "https://app-cartobike.lusites.xyz/en/privacy_policy";
	  By PrivacyPolicyURLLocator = By.linkText("Privacy Policy");
	  WebElement PrivacyPolicy = findElementBylocator(PrivacyPolicyURLLocator);
	  PrivacyPolicy.click();
	  InitializeImpliciteWait();
	  String ActualPrivacyPolicyURL = driver.getCurrentUrl();	
	  try {
		  Assert.assertEquals(ActualPrivacyPolicyURL, ExpectPrivacyPolicyURL);
		  String testpass = "The Actual Privacy Policy URL and the Expected Privacy Policy URL are the same";
		  TestPassed(testpass);
	  }
	  catch(AssertionError e) {
		  String testfail = "The Actual Privacy Policy URL and the Expected Privacy Policy URL does not match";
		  TestFailed(testfail);
		  CapturePageURL();
	  }
	  InitializeImpliciteWait();
	  driver.navigate().back();
	  System.out.println(driver.getCurrentUrl());
	  InitializeImpliciteWait();
	  String ExpectDataProtectionURL = "https://app-cartobike.lusites.xyz/en/data_protection";
	  By DataProtectionURLLocator = By.linkText("Data Protection");
	  WebElement DataProtection = findElementBylocator(DataProtectionURLLocator);
	  DataProtection.click();
	  InitializeImpliciteWait();
	  String ActualDataProtectionURL = driver.getCurrentUrl();	
	  try {
		  Assert.assertEquals(ActualDataProtectionURL, ExpectDataProtectionURL);
		  String testpass = "The Actual Data Protection URL and the Expected Data Protection URL are the same";
		  TestPassed(testpass);
	  }
	  catch(AssertionError e) {
		  String testfail = "The Actual Data Protection URL and the Expected Data Protection URL does not match";
		  TestFailed(testfail);
		  CapturePageURL();
	  }
	  InitializeImpliciteWait();
	  driver.navigate().back();
	  System.out.println(driver.getCurrentUrl());
	  InitializeImpliciteWait();
	  String ExpectLegalNoticeURL = "https://app-cartobike.lusites.xyz/en/legal_notice";
	  By LegalNoticeURLLocator = By.linkText("Legal notice");
	  WebElement LegalNotice = findElementBylocator(LegalNoticeURLLocator);
	  LegalNotice.click();
	  InitializeImpliciteWait();
	  String ActualLegalNoticeURL = driver.getCurrentUrl();	
	  try {
		  Assert.assertEquals(ActualLegalNoticeURL, ExpectLegalNoticeURL);
		  String testpass = "The Actual Legal notice URL and the Expected Legal notice URL are the same";
		  TestPassed(testpass);
	  }
	  catch(AssertionError e) {
		  String testfail = "The Actual Legal notice URL and the Expected Legal notice URL does not match";
		  TestFailed(testfail);
		  CapturePageURL();
	  }
	  InitializeImpliciteWait();
	  driver.navigate().back();
	  System.out.println(driver.getCurrentUrl());
	  InitializeImpliciteWait();
	  String ExpectLegalDocumentsURL = "https://app-cartobike.lusites.xyz/en/legal_documents";
	  By LegalDocumentsURLLocator = By.linkText("Legal documents");
	  WebElement LegalDocuments = findElementBylocator(LegalDocumentsURLLocator);
	  LegalDocuments.click();
	  InitializeImpliciteWait();
	  String ActualLegalDocumentsURL = driver.getCurrentUrl();	
	  try {
		  Assert.assertEquals(ActualLegalDocumentsURL, ExpectLegalDocumentsURL);
		  String testpass = "The Actual Legal documents URL and the Expected Legal documents URL are the same";
		  TestPassed(testpass);
	  }
	  catch(AssertionError e) {
		  String testfail = "The Actual Legal documents URL and the Expected Legal documents URL does not match";
		  TestFailed(testfail);
		  CapturePageURL();
	  }
	  InitializeImpliciteWait();
	  driver.navigate().back();
	  System.out.println(driver.getCurrentUrl());
	  InitializeImpliciteWait();
  }
  


  @AfterSuite
  public void CloseBrowser() {
    if (driver != null) {
      driver.quit();
    }
    report.endTest(test);
    report.flush();

  }

}