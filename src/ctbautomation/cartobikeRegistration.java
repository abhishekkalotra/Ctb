package ctbautomation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.Assert;
import com.relevantcodes.extentreports.LogStatus;
import java.io.IOException;
import java.util.List;

public class cartobikeRegistration {
	
	@Test
	 public void Test12() throws IOException{
		 ctbtest ctbsignup = new ctbtest();
		 String URLLink ="https://app-cartobike.lusites.xyz/en/registration";
		 ctbsignup.NavigationURL(URLLink);
		 ctbtest.test = ctbtest.report.startTest("To Test the signup functionality when the user try to register new account using old email");
		 ctbtest.test.log(LogStatus.INFO, "<b> Steps to reproduce</b>");
		 ctbtest.test.log(LogStatus.INFO, "Visit the cartobike signup page.");
		 ctbtest.test.log(LogStatus.INFO, "Now fill the signup form and use and old email"); 
		 ctbtest.test.log(LogStatus.INFO, "click on the signup button");
		 ctbtest.test.log(LogStatus.INFO, "<b>Test Data</b>");
		 
		 By signupEmailLocator = By.xpath("//div[1]/div[1]/div/input[@id='outlined-basic']");
		 WebElement signupEmail = ctbsignup.findElementBylocator(signupEmailLocator);
		 signupEmail.click();
		 signupEmail.sendKeys("vk3114@yopmail.com");
		 ctbtest.test.log(LogStatus.INFO, "Email: vk3114@yopmail.com");
		 
		 By passwordFieldLocator = By.xpath("//div[2]/div/div/input[@id='outlined-basic']");
		 WebElement passwordField = ctbsignup.findElementBylocator(passwordFieldLocator);
		 passwordField.click();
		 passwordField.sendKeys("Ak@11111111");
		 ctbtest.test.log(LogStatus.INFO, "Password: Ak@11111111");
		 By confirmPasswordFieldLocator = By.xpath("//div[3]/div/div/input[@id='outlined-basic']");
		 WebElement confirmPasswordField = ctbsignup.findElementBylocator(confirmPasswordFieldLocator);
		 confirmPasswordField.click();
		 confirmPasswordField.sendKeys("Ak@11111111");
		 ctbtest.test.log(LogStatus.INFO, "Confirm Password: Ak@11111111");
		 
		 By countryFieldLocator = By.xpath("//div[@class='country-drop MuiBox-root emotion-cache-1xhj18k']");
		 WebElement countryField =ctbsignup.findElementBylocator(countryFieldLocator);
		 countryField.click();
		 By countryDropDownLocator = By.xpath("//ul/li[6]");
		 WebElement countryDropDown = ctbtest.wait.until(ExpectedConditions.visibilityOfElementLocated(countryDropDownLocator));
		 countryDropDown.click();
		 ctbtest.test.log(LogStatus.INFO, "Select country: Belgium");
		 
		 By mobileNumberFieldLocator = By.xpath("//div/div[2]/div/input[@id='outlined-basic']");
		 WebElement mobileNumberField = ctbsignup.findElementBylocator(mobileNumberFieldLocator);
		 mobileNumberField.click();
		 mobileNumberField.sendKeys("9874563201");
		 ctbtest.test.log(LogStatus.INFO, "Mobile Number: 9874563201");
		 
		 By termsCheckboxFieldLocator = By.className("PrivateSwitchBase-input");
		 WebElement termsCheckboxField = ctbsignup.findElementBylocator(termsCheckboxFieldLocator);
		 termsCheckboxField.click();
		 ctbtest.test.log(LogStatus.INFO, "Term and Condition Checkbox: checked");
		 
		 By signupButtonLocator = By.id("myButton");
		 WebElement signupButton = ctbsignup.findElementBylocator(signupButtonLocator);
		 signupButton.click();
		 
		 ctbsignup.InitializeImpliciteWait();
		 try {
			 	String ExpectedResult = "The e-mail address has already been taken.";
			 	WebElement emailError = ctbtest.wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error")));
			 	String ActualResult = emailError.getText();
			 	Assert.assertEquals(ActualResult, ExpectedResult);
			 	String testpass = "Test Passed";
			 	ctbsignup.TestPassed(testpass);
		 }
		 catch(AssertionError e) {
			 String testfail = "The actual error message does not match with the expected error message";
			 ctbsignup.TestFailed(testfail);
			 ctbsignup.CapturePageURL();
			 ctbsignup.CaptureConsoleLog();
		 }
		 
	}
	@Test
	public void Test13() throws IOException {
		 ctbtest ctbsignup = new ctbtest();
		 ctbtest.test = ctbtest.report.startTest("To Test the signup functionality when password and confirm password fields does not match");
		 ctbtest.test.log(LogStatus.INFO, "<b> Steps to reproduce</b>");
		 ctbtest.test.log(LogStatus.INFO, "Visit the cartobike signup page.");
		 ctbtest.test.log(LogStatus.INFO, "Now fill the signup form and make sure to add different values in password and confirm password fields"); 
		 ctbtest.test.log(LogStatus.INFO, "click on the signup button");
		 ctbtest.test.log(LogStatus.INFO, "<b>Test Data</b>");
		 
		 By signupEmailLocator = By.xpath("//div[1]/div[1]/div/input[@id='outlined-basic']");
		 WebElement signupEmail = ctbsignup.findElementBylocator(signupEmailLocator);
		 signupEmail.click();
		 signupEmail.sendKeys("vk3114@yopmail.com");
		 ctbtest.test.log(LogStatus.INFO, "Email: vk3114@yopmail.com");
		 
		 By passwordFieldLocator = By.xpath("//div[2]/div/div/input[@id='outlined-basic']");
		 WebElement passwordField = ctbsignup.findElementBylocator(passwordFieldLocator);
		 passwordField.click();
		 passwordField.sendKeys("Ak@11111111");
		 
		 By confirmPasswordFieldLocator = By.xpath("//div[3]/div/div/input[@id='outlined-basic']");
		 WebElement confirmPasswordField = ctbsignup.findElementBylocator(confirmPasswordFieldLocator);
		 confirmPasswordField.click();
		 confirmPasswordField.sendKeys("Ak@11111112");
		 
		 By countryFieldLocator = By.xpath("//div[@class='country-drop MuiBox-root emotion-cache-1xhj18k']");
		 WebElement countryField =ctbsignup.findElementBylocator(countryFieldLocator);
		 countryField.click();
		 By countryDropDownLocator = By.xpath("//ul/li[6]");
		 WebElement countryDropDown = ctbtest.wait.until(ExpectedConditions.visibilityOfElementLocated(countryDropDownLocator));
		 countryDropDown.click();
		 ctbtest.test.log(LogStatus.INFO, "Select country: Belgium");
		 
		 By mobileNumberFieldLocator = By.xpath("//div/div[2]/div/input[@id='outlined-basic']");
		 WebElement mobileNumberField = ctbsignup.findElementBylocator(mobileNumberFieldLocator);
		 mobileNumberField.click();
		 mobileNumberField.sendKeys("1236547890");
		 
		 By termsCheckboxFieldLocator = By.className("PrivateSwitchBase-input");
		 WebElement termsCheckboxField = ctbsignup.findElementBylocator(termsCheckboxFieldLocator);
		 termsCheckboxField.click();
		 
		 
		 By signupButtonLocator = By.id("myButton");
		 WebElement signupButton = ctbsignup.findElementBylocator(signupButtonLocator);
		 signupButton.click();
		 
		 
		 try {
			 	String expectedResult = "Retype password must be same as password.";
			 	By errorMessageLocator = By.className("error");
			 	WebElement errorMessage = ctbtest.wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageLocator));
			 	String actualResult = errorMessage.getText();
			 	Assert.assertEquals(actualResult, expectedResult);
			 	ctbsignup.TestPassed("confirm password and password are matching");
		 }
		 catch(AssertionError e) {
			 String testfail = "confirm password and password are not matching";
			 ctbsignup.TestFailed(testfail);
			 ctbsignup.CapturePageURL();
			 ctbsignup.CaptureConsoleLog();
		 }
		 
		 
	}
}
