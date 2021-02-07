package walletHubTest;

import org.testng.annotations.Test;

import wallehubFrameworkSetup.TestDriver;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import java.io.IOException;

import org.testng.annotations.AfterSuite;

public class AssignmentTest extends TestDriver {
 
	
	@BeforeSuite
	public void beforeSuite() throws IOException {
		init();
	}
	
	
	
	@Test(enabled = true, dataProvider = "FaceBookData" )
	public void Assignment1(String text) throws IOException {
		Assignment1Functionality obj = new Assignment1Functionality();
		obj.loginToApplication();
		obj.postStatus(text);
	}
	
	@DataProvider(name = "FaceBookData")
	public Object[][] GetData()
	{
		String data[][] = {{"Hello World"}}; 
		return data;
	}
  
  

	@AfterSuite
	public void afterSuite() {
		  closeDriver();
	}

}
