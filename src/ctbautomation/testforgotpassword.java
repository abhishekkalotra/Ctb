package ctbautomation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import org.testng.Assert;
import com.relevantcodes.extentreports.LogStatus;
import java.io.IOException;
import java.util.List;

public class testforgotpassword{
	
	 @Test
	 public void Test07() throws IOException{
		 //ctbtest.driver.navigate().to("https://app-cartobike.lusites.xyz/en/login");
		 ctbtest ctbobj = new ctbtest();	
		 ctbtest.test = ctbtest.report.startTest("To Test the forgot password functionality when invalid email has entered");
		 ctbtest.test.log(LogStatus.INFO, "<b> Steps to reproduce</b>");
		 ctbtest.test.log(LogStatus.INFO, "In the login page click on the forgot password link");
		 ctbtest.test.log(LogStatus.INFO, "In the forgot password page add invalid email abd clcik on send email button"); 
		 By ForgotPasswordURLLocator = By.linkText("Forgot Password?");
		 /**
		  * created findElement object to use the webelement method declared in the ctbtest.java file
		  * */
		 WebElement ForgotPasswordURL = ctbobj.findElementBylocator(ForgotPasswordURLLocator);
		 ForgotPasswordURL.click();
		 ctbobj.InitializeImpliciteWait();
		 By ForgotPasswordEmailLocator = By.id("outlined-basic");
		 WebElement forgotEmail = ctbobj.findElementBylocator(ForgotPasswordEmailLocator);
		 forgotEmail.click();
		 forgotEmail.sendKeys("test879@yopmail.com");
		 ctbobj.InitializeImpliciteWait();
		 By sendEmailLocator = By.id("myButton");
		 WebElement sendEmail = ctbobj.findElementBylocator(sendEmailLocator);
		 sendEmail.click();
		 try {
			 	String ExpectedResult = "The email doesn't exist.";
			 	WebElement forgotPasswordError = ctbtest.wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error")));
			 	String ActualResult = forgotPasswordError.getText();
			 	Assert.assertEquals(ActualResult, ExpectedResult);
			 	String testpass = "Test Passed";
			 	ctbobj.TestPassed(testpass);
		 }
		 catch(AssertionError e) {
			 String testfail = "The actual error message does not match with the expected error message";
			 ctbobj.TestFailed(testfail);
			 ctbobj.CapturePageURL();
			 ctbobj.CaptureConsoleLog();
		 }

	 }
	 @Test
	 public void Test08() throws IOException, InterruptedException{
		 ctbtest forgotEmailObj = new ctbtest();
		 String URLLink ="https://app-cartobike.lusites.xyz/en/forgot";
		 forgotEmailObj.NavigationURL(URLLink);
		 ctbtest.driver.navigate().refresh();
		 ctbtest.test = ctbtest.report.startTest("To Test the forgot password functionality when valid email has entered");
		 ctbtest.test.log(LogStatus.INFO, "<b> Steps to Reproduce </b>");
		 ctbtest.test.log(LogStatus.INFO, "In the forgot password pass add a valid email address");
		 ctbtest.test.log(LogStatus.INFO, "click on the send email button");
		 ctbtest.test.log(LogStatus.INFO, "open the email and locate the cartobike forgot password email");
		 ctbtest.test.log(LogStatus.INFO, "click on the forgot password link");
		 ctbtest.test.log(LogStatus.INFO, "in the reset password screen add valid password and then click on reset button");
		 By emailLocator = By.id("outlined-basic");
		 WebElement forgotEmail = forgotEmailObj.findElementBylocator(emailLocator);
		 forgotEmail.click();
		 String forgotPasswordEmail = "vk545@yopmail.com";
		 forgotEmail.sendKeys(forgotPasswordEmail);
		 By sendEmailLocator = By.id("myButton");
		 WebElement sendEmail = forgotEmailObj.findElementBylocator(sendEmailLocator);
		 sendEmail.click();
		 forgotEmailObj.InitializeImpliciteWait();
		 forgotEmailObj.openNewTab(); //To Open New Tab
		 forgotEmailObj.InitializeImpliciteWait();
		 ctbtest.driver.navigate().to("https://yopmail.com");
		 By yopmailEmailLocator = By.id("login");
		 WebElement yopmailEmail = forgotEmailObj.findElementBylocator(yopmailEmailLocator);
		 yopmailEmail.click();
		 yopmailEmail.sendKeys(forgotPasswordEmail);
		 By yopmailSubmitLocator = By.cssSelector("button.md");
		 WebElement yopmailSubmit = ctbtest.wait.until(ExpectedConditions.visibilityOfElementLocated(yopmailSubmitLocator));
		 yopmailSubmit.click();
		 forgotEmailObj.InitializeImpliciteWait();
		 
		
		 String expectedEmailSubject = "Reset Password Notification";
		 boolean conditionTrue = false; // use to check if the condition is true or not
		 for(int i =0; i <= 5; i++) {
			 By inBoxIFrameLocator = By.id("ifinbox"); 
			 WebElement inBoxIFrame = forgotEmailObj.findElementBylocator(inBoxIFrameLocator);
			 forgotEmailObj.switchToIFrame(inBoxIFrame); // use to find iframe
			 By inBoxEmailLocator = By.className("lm");
			 List<WebElement> listInBoxEmail = ctbtest.driver.findElements(inBoxEmailLocator); // using webelement rom the webelement list
			 WebElement inBoxEmail = listInBoxEmail.get(0);
			 inBoxEmail.click();
			 forgotEmailObj.InitializeImpliciteWait();
			 forgotEmailObj.switchToDefaultContent();
			 By eMailIFrameLocator = By.id("ifmail");
			 WebElement eMailIFrame = forgotEmailObj.findElementBylocator(eMailIFrameLocator);
			 forgotEmailObj.switchToIFrame(eMailIFrame);
			 By emailSubjectLocator = By.cssSelector("div.f18");
			 WebElement emailSubject = forgotEmailObj.findElementBylocator(emailSubjectLocator);
			 String ActualEmailSubject = emailSubject.getText();
			 if(ActualEmailSubject.equals(expectedEmailSubject)) {
				By resetPassowordButtonLocator = By.linkText("Reset Password");
				WebElement resetPassowordButton = forgotEmailObj.findElementBylocator(resetPassowordButtonLocator);
				resetPassowordButton.click();
				forgotEmailObj.InitializeImpliciteWait();
				forgotEmailObj.switchToDefaultContent(); // switch back from iframe
				forgotEmailObj.SwitchTab(1);
				ctbtest.driver.close();
				forgotEmailObj.SwitchTab(1);
				String newPass = "Ak@11111111";
				By newPasswordLocator = By.id("mui-4");
				WebElement newPassword = forgotEmailObj.findElementBylocator(newPasswordLocator);
				newPassword.click();
				newPassword.sendKeys(newPass);
				By confirmNewPasswordLocator = By.id("mui-5");
				WebElement confirmNewPassword = forgotEmailObj.findElementBylocator(confirmNewPasswordLocator);
				confirmNewPassword.click();
				confirmNewPassword.sendKeys(newPass);
				By saveResetButtonLocator = By.id("save-reset-password");
				WebElement saveResetButton = forgotEmailObj.findElementBylocator(saveResetButtonLocator);
				saveResetButton.click();		
				forgotEmailObj.InitializeImpliciteWait();
				String expectedResetSuccess = "Your password has been reset!";
				try {
				By resetSuccessText = By.cssSelector("div.emotion-cache-4an0mh");
				WebElement resetSuccess = forgotEmailObj.findElementBylocator(resetSuccessText);
				String actualResetSuccess = resetSuccess.getText();
				Assert.assertEquals(actualResetSuccess, expectedResetSuccess);
				String testpass = "Your password has been reset!";
				forgotEmailObj.TestPassed(testpass);				
				}
				catch(AssertionError e ) {
					String testfail = "Your password did not reset!";
					forgotEmailObj.TestFailed(testfail);
					forgotEmailObj.CapturePageURL();
					forgotEmailObj.CaptureConsoleLog();
					Thread.sleep(5000);
				} 
				conditionTrue = true;
				break;
			 }
			 else {
				 Thread.sleep(5000);
				 ctbtest.driver.navigate().refresh();
				 forgotEmailObj.switchToDefaultContent(); // switch back from iframe
			 }
			
		 }
		if(!conditionTrue) {
			 
			 String testfail = "The user did not receive reset password email";
			 forgotEmailObj.TestFailed(testfail);
			 forgotEmailObj.CapturePageURL();
			 ctbtest.driver.close();
			 
		}
		
		 
	 }
	 
	 
	 @Test
	 public void Test09() throws IOException, InterruptedException{
		 
		 ctbtest forgotEmailObj = new ctbtest();
		 String URLLink ="https://app-cartobike.lusites.xyz/en/forgot";
		 forgotEmailObj.NavigationURL(URLLink);
		 ctbtest.test = ctbtest.report.startTest("To Test the forgot password functionality when new password and confirm password values does not match");
		 ctbtest.test.log(LogStatus.INFO, "<b> Steps to Reproduce </b>");
		 ctbtest.test.log(LogStatus.INFO, "In the forgot password pass add a valid email address");
		 ctbtest.test.log(LogStatus.INFO, "click on the send email button");
		 ctbtest.test.log(LogStatus.INFO, "open the email and locate the cartobike forgot password email");
		 ctbtest.test.log(LogStatus.INFO, "click on the forgot password link");
		 ctbtest.test.log(LogStatus.INFO, "in the reset password screen add diffrent value for new and confirm password fields and then click on save button");
		 By emailLocator = By.id("outlined-basic");
		 WebElement forgotEmail = forgotEmailObj.findElementBylocator(emailLocator);
		 forgotEmail.click();
		 String forgotPasswordEmail = "vk454@yopmail.com";
		 forgotEmail.clear();
		 forgotEmailObj.InitializeImpliciteWait();
		 forgotEmail.sendKeys(forgotPasswordEmail);
		 By sendEmailLocator = By.id("myButton");
		 WebElement sendEmail = forgotEmailObj.findElementBylocator(sendEmailLocator);
		 sendEmail.click();
		 forgotEmailObj.InitializeImpliciteWait();
		 forgotEmailObj.openNewTab(); //To Open New Tab
		 forgotEmailObj.InitializeImpliciteWait();
		 ctbtest.driver.navigate().to("https://yopmail.com");
		 By yopmailEmailLocator = By.id("login");
		 WebElement yopmailEmail = forgotEmailObj.findElementBylocator(yopmailEmailLocator);
		 yopmailEmail.click();
		 String yopmailEmailValue = yopmailEmail.getAttribute("value");
		 forgotEmailObj.Clear(yopmailEmail, yopmailEmailValue); //clear the value if exist
		 forgotEmailObj.InitializeImpliciteWait();
		 yopmailEmail.sendKeys(forgotPasswordEmail);
		 By yopmailSubmitLocator = By.cssSelector("button.md");
		 WebElement yopmailSubmit = ctbtest.wait.until(ExpectedConditions.visibilityOfElementLocated(yopmailSubmitLocator));
		 yopmailSubmit.click();
		 forgotEmailObj.InitializeImpliciteWait();
		 
		 String expectedEmailSubject = "Reset Password Notification";
		 boolean conditionTrue = false; // use to check if the condition is true or not
		 for(int i =0; i <= 5; i++) {
			 By inBoxIFrameLocator = By.id("ifinbox"); 
			 WebElement inBoxIFrame = forgotEmailObj.findElementBylocator(inBoxIFrameLocator);
			 forgotEmailObj.switchToIFrame(inBoxIFrame); // use to find iframe
			 By inBoxEmailLocator = By.className("lm");
			 List<WebElement> listInBoxEmail = ctbtest.driver.findElements(inBoxEmailLocator); // using webelement rom the webelement list
			 WebElement inBoxEmail = listInBoxEmail.get(0);
			 inBoxEmail.click();
			 forgotEmailObj.InitializeImpliciteWait();
			 forgotEmailObj.switchToDefaultContent();
			 By eMailIFrameLocator = By.id("ifmail");
			 WebElement eMailIFrame = forgotEmailObj.findElementBylocator(eMailIFrameLocator);
			 forgotEmailObj.switchToIFrame(eMailIFrame);
			 By emailSubjectLocator = By.cssSelector("div.f18");
			 WebElement emailSubject = forgotEmailObj.findElementBylocator(emailSubjectLocator);
			 String ActualEmailSubject = emailSubject.getText();
			 if(ActualEmailSubject.equals(expectedEmailSubject)) {
				By resetPassowordButtonLocator = By.linkText("Reset Password");
				WebElement resetPassowordButton = forgotEmailObj.findElementBylocator(resetPassowordButtonLocator);
				resetPassowordButton.click();
				forgotEmailObj.InitializeImpliciteWait();
				forgotEmailObj.switchToDefaultContent(); // switch back from iframe
				forgotEmailObj.SwitchTab(1);
				ctbtest.driver.close();
				forgotEmailObj.SwitchTab(1);
				ctbtest.driver.close();
				forgotEmailObj.SwitchTab(1);
				String newPass = "Ak@11111111";
				By newPasswordLocator = By.id("mui-4");
				WebElement newPassword = forgotEmailObj.findElementBylocator(newPasswordLocator);
				newPassword.click();
				newPassword.sendKeys(newPass);
				By confirmNewPasswordLocator = By.id("mui-5");
				WebElement confirmNewPassword = forgotEmailObj.findElementBylocator(confirmNewPasswordLocator);
				confirmNewPassword.click();
				confirmNewPassword.sendKeys("Ak@1112121");
				By saveResetButtonLocator = By.id("save-reset-password");
				WebElement saveResetButton = forgotEmailObj.findElementBylocator(saveResetButtonLocator);
				saveResetButton.click();		
				forgotEmailObj.InitializeImpliciteWait();
				String expectedErrorText = "The password confirmation does not match.";
				try {
				By errorTextLocator = By.xpath("//div[@class='row']//div[@class='MuiBox-root emotion-cache-1qm1lh'][2]/span[@class='required']");
				WebElement errorText = ctbtest.wait.until(ExpectedConditions.visibilityOfElementLocated(errorTextLocator));
				String actualErrorText = errorText.getText();
				Assert.assertEquals(actualErrorText, expectedErrorText);
				String testpass = "the password did not reset as the new password and confirm password does not match";
				forgotEmailObj.TestPassed(testpass);
				Thread.sleep(5000);
				}
				catch(AssertionError e ) {
					String testfail = "the user failed to change the password";
					forgotEmailObj.TestFailed(testfail);
					forgotEmailObj.CapturePageURL();
					forgotEmailObj.CaptureConsoleLog();
				}
				conditionTrue = true;
				break;
			 }
			 else {
				 Thread.sleep(5000);
				 ctbtest.driver.navigate().refresh();
				 forgotEmailObj.switchToDefaultContent(); // switch back from iframe
			 }
			 
		 }
		if(!conditionTrue) {
			 
			 String testfail = "The user did not receive reset password email";
			 forgotEmailObj.TestFailed(testfail);
			 forgotEmailObj.CapturePageURL();
		}
		
	 }
	 
	 @Test(dependsOnMethods = "Test09")
	 public void Test10() throws IOException{
		ctbtest forgotEmailObj = new ctbtest();
		ctbtest.test = ctbtest.report.startTest("To Test the forgot password functionality when the user try to use old reset password email");
		ctbtest.test.log(LogStatus.INFO, "<b> Steps to Reproduce </b>");
		ctbtest.test.log(LogStatus.INFO, "In Yopmail open the reset password link of the old email");
		ctbtest.test.log(LogStatus.INFO, "click on the Reset Password button");
		ctbtest.test.log(LogStatus.INFO, "add new password and confirm password in the respected fields");
		ctbtest.test.log(LogStatus.INFO, "Click on save button");
		String forgotPasswordEmail = "vk454@yopmail.com";
		ctbtest.driver.navigate().to("https://yopmail.com");
		By yopmailEmailLocator = By.id("login");
		WebElement yopmailEmail = forgotEmailObj.findElementBylocator(yopmailEmailLocator);
		yopmailEmail.click();
		String yopmailEmailValue = yopmailEmail.getAttribute("value");
		forgotEmailObj.Clear(yopmailEmail, yopmailEmailValue); //clear the value if exist
		forgotEmailObj.InitializeImpliciteWait();
		yopmailEmail.sendKeys(forgotPasswordEmail);
		By yopmailSubmitLocator = By.cssSelector("button.md");
		WebElement yopmailSubmit = ctbtest.wait.until(ExpectedConditions.visibilityOfElementLocated(yopmailSubmitLocator));
		yopmailSubmit.click();
		forgotEmailObj.InitializeImpliciteWait();
		// to be countinue....
	 }
	 
	@Test
	public void Test11() throws IOException {
		 ctbtest forgotEmailObj = new ctbtest();
		 String URLLink ="https://app-cartobike.lusites.xyz/en/forgot";
		 forgotEmailObj.NavigationURL(URLLink);
		 ctbtest.test = ctbtest.report.startTest("To Test the forgot password functionality when invalid email is entered");
		 ctbtest.test.log(LogStatus.INFO, "<b> Steps to Reproduce </b>");
		 ctbtest.test.log(LogStatus.INFO, "In the forgot password add an invalid email address");
		 ctbtest.test.log(LogStatus.INFO, "click on the send email button");
		 forgotEmailObj.InitializeImpliciteWait();
		 By emailFieldLocator = By.id("outlined-basic");
		 WebElement emailField = forgotEmailObj.findElementBylocator(emailFieldLocator);
		 emailField.click();
		 String forgotEmailValue = emailField.getAttribute("value");
		 forgotEmailObj.Clear(emailField, forgotEmailValue);
		 emailField.sendKeys("vk3dsds114@yopmail.com");
		 forgotEmailObj.InitializeImpliciteWait();
		 By sendEmailLocator = By.id("myButton");
		 WebElement sendEmail = forgotEmailObj.findElementBylocator(sendEmailLocator);
		 sendEmail.click();
		 String expectedErrorText = "The email doesn't exist.";
		 By errorTextLocator = By.className("error");
		 WebElement ErrorText = ctbtest.wait.until(ExpectedConditions.visibilityOfElementLocated(errorTextLocator));
		 String actualErrorText = ErrorText.getText();
		 try {
			 Assert.assertEquals(actualErrorText, expectedErrorText);
			 String testpass = "the user got a message 'The email doesn't exist.'";
			 forgotEmailObj.TestPassed(testpass);
		 }
		 catch(AssertionError e) {
			 String testfail = "the user did not get any alert message";
			 forgotEmailObj.TestFailed(testfail);
			 forgotEmailObj.CapturePageURL();
			 forgotEmailObj.CaptureConsoleLog();		
		 }
	}
}

