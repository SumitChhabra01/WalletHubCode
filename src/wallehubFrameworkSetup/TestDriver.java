package wallehubFrameworkSetup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class TestDriver {

	public static WebDriver driver;
	public static Properties prop = new Properties();
	static String timeStamp = null;
	public final String currentDir = System.getProperty("user.dir");
	public HashMap<String,String[]> userData = new HashMap<String, String[]>();
	
	public void init() throws IOException
	{
		
		System.out.println("************INITIALIZING CHROME DRIVER**************");
		System.out.println(currentDir);
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		System.setProperty("webdriver.chrome.driver", currentDir+"\\drivers\\chromedriver_87.exe");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		
		System.out.println("Chrome Browser Lauched");
		readPropertyFile();
		timeStamp=fetchCurrentTime();
		timeStamp = currentDir+"\\ScreenShots\\Output_"+timeStamp;
		File file = new File(timeStamp);
		System.out.println("Test Output Folder Created - "+timeStamp);
		
		//Creating the directory
	      boolean bool = file.mkdir();
	      if(bool){
	         System.out.println("Directory created successfully");
	      }else{
	         System.out.println("Sorry couldn’t create specified directory");
	      } 
	}
	public void readPropertyFile()
	{
		File file = new File(currentDir+"\\config.properties");
		  
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//load properties file
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void takeScreenShot() throws IOException {
		 //Convert web driver object to TakeScreenshot

       TakesScreenshot scrShot =((TakesScreenshot)driver);

       //Call getScreenshotAs method to create image file

               File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

           //Move image file to new destination

               File DestFile=new File(timeStamp+"\\"+fetchCurrentTime()+".png");

               //Copy file at destination

               FileUtils.copyFile(SrcFile, DestFile);
		
	}
	
	public String fetchCurrentTime()
	{
		Date date= new Date();
        //getTime() returns current time in milliseconds
		long time = date.getTime();
        //Passed the milliseconds to constructor of Timestamp class 
		Timestamp ts = new Timestamp(time);
		String exactTime=""+ts;
		exactTime=exactTime.replaceAll("[^a-zA-Z0-9]", "_");
		return exactTime;
	}
	
	public void closeDriver()
	{
		driver.quit();
	}
}
