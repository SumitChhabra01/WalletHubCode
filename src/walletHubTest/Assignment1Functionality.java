package walletHubTest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import wallehubFrameworkSetup.TestDriver;

public class Assignment1Functionality extends TestDriver{

	public WebDriverWait wait=new WebDriverWait(driver, 10);
	
	public static By login_userName = By.id("email");
	public static By login_password = By.id("pass");
	public static By login_loginButton = By.name("login");
	public static By home_status = By.xpath("//span[contains(text(),'on your mind,')]//parent::div");
	public static By postCreationPopup_createPost = By.xpath("//span[contains(text(),'Create Post')]");
	public static By postCreationPopup_textArea = By.xpath("//form[@method='POST']//div/span/br//parent::span//parent::div");
	public static By postCreationPopup_postButton = By.xpath("//div[@aria-label='Post']");
	
	
	public void loginToApplication() throws IOException {
		// TODO Auto-generated method stub
		driver.get(prop.getProperty("URL"));
		takeScreenShot();
		driver.findElement(login_userName).sendKeys(prop.getProperty("username"));
		driver.findElement(login_password).sendKeys(prop.getProperty("password"));
		takeScreenShot();
		driver.findElement(login_loginButton).click();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(home_status)));
		takeScreenShot();
	}
	
	public void postStatus(String text) throws IOException
	{
		driver.findElement(home_status).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(postCreationPopup_createPost)));
		driver.findElement(postCreationPopup_textArea).sendKeys(text);
		takeScreenShot();
		driver.findElement(postCreationPopup_postButton).click();
		By home_postDisplay = By.xpath("//div[text()='"+text+"']");
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(home_postDisplay)));
		takeScreenShot();
	}

	
}
