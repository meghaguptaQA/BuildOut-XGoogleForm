package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */

     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

    //@AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
    @Test
    public void testCase01() throws InterruptedException {
        System.out.println("Start Test case: testCase01");
        //Open the URL to the forms page
        openURL("https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform");
        String url=GetCurrentURL();
        Thread.sleep(5000);
        //System.out.println("Current URL of the form page :"+url);
        
        //Enter the value "Crio Learner" in Name field
        //Xpath : //div[@class='rFrNMe k3kHxc RdH0ib yqQS1 zKHdkd']
        WebElement txtboxName=driver.findElement(By.xpath("//input[@aria-labelledby='i1 i4']"));
        enter_Value(txtboxName,"Crio Learner");
        //Get current epoch time
        long epochtime=System.currentTimeMillis()/1000;
        //Enter value "I want to be the best QA Engineer! +current epoch time "in Why are you practicing Automation? field
        //Xpath : //div[@class='edhGSc zKHdkd kRy7qc RdH0ib yqQS1']
        WebElement txtboxPracticeAutomation=driver.findElement(By.xpath("//textarea[@class='KHxj8b tL9Q4c']"));
        enter_Value(txtboxPracticeAutomation,"I want to be the best QA Engineer! "+epochtime);
        //Select value in Experience Radio button
        WebElement radiobtnExperience=driver.findElement(By.id("i25"));
        btn_click(radiobtnExperience);
        //Select Java Checkbox in Which of the following have you learned in Crio.Do for Automation Testing?
        WebElement checkboxJava=driver.findElement(By.id("i34"));
        btn_click(checkboxJava);
        //Select Selenium Checkbox in Which of the following have you learned in Crio.Do for Automation Testing?
        WebElement checkboxSelenium=driver.findElement(By.id("i37"));
        btn_click(checkboxSelenium);
        //Select TestNG Checkbox in Which of the following have you learned in Crio.Do for Automation Testing?
        WebElement checkboxTestNG=driver.findElement(By.id("i43"));
        btn_click(checkboxTestNG);

        WebElement dropdown = driver.findElement(By.xpath("//div[contains(@class,'MocG8c')]"));
        //System.out.println("Selected preferred address type");

        // Click to open the dropdown
        dropdown.click();

        Thread.sleep(3000);
       
        WebElement option = driver.findElement(By.xpath("//*[@id='mG61Hd']/div[2]/div/div[2]/div[5]/div/div/div[2]/div/div[2]/div[3]/span"));
        option.click();
       
        //Select value in How should you be addressed? dropdown //div//span[text()='Mr']/./preceding-sibling::div[@class='kRoyt MbhUzd']
        //WebElement dropdwnAddressedAs=driver.findElement(By.xpath("//span[text()='Choose']"));
        //dropdwnAddressedAs.click();
        //WebElement dropdownValues=driver.findElement(By.xpath("//span[text()='Choose']/parent::div[1]"));
       // dropdownValueMs.click();

        // List<WebElement> dropdownValues=driver.findElements(By.xpath("//div[@class='MocG8c HZ3kWc mhLiyf OIC90c LMgvRb']"));
		// System.out.println("size is"+dropdownValues.size());
		// // TODO : Create a Javascript executor to click on particular value
		// JavascriptExecutor js=(JavascriptExecutor) driver;		
		// // TODO : Iterate on each value using for each loop and if matching desired value is
		// // found, perform click() operation
		// for(WebElement chooseval:dropdownValues)
		// {   System.out.println("data-value is : "+chooseval.getAttribute("data-value"));
		// 	if(chooseval.getAttribute("data-value").equalsIgnoreCase("Dr"))
		// 	{   WebElement valuetoclick=chooseval.findElement(By.xpath("//span[text()='Dr']"));
		// 		js.executeScript("arguments[0].scrollIntoView();",valuetoclick);
		// 		chooseval.click();
		// 		break;
		// 	}
		// }
        //Enter the date 7 days ago in field:What was the date 7 days ago?
        //Calculate the date
        LocalDate todaysdate=LocalDate.now();
        LocalDate date7daysbefore=todaysdate.minusDays(7);
        //System.out.println(date7daysbefore);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/YYYY");
        String datetoenter = date7daysbefore.format(format);
        //System.out.println(datetoenter);
        WebElement txtboxDate=driver.findElement(By.xpath("//input[@type='date']"));        
        enter_Value(txtboxDate,datetoenter);

        //Enter the time now field in What is the time right now?
        LocalTime timenow=LocalTime.now();
        int hour=timenow.getHour();
        int minute=timenow.getMinute();
        String hours=String.valueOf(hour);
        String minutes=String.valueOf(minute);
        
        WebElement txthour=driver.findElement(By.xpath("//input[@aria-label='Hour']"));
        enter_Value(txthour, "07");
        WebElement txtminute=driver.findElement(By.xpath("//input[@aria-label='Minute']"));
        enter_Value(txtminute, "30");

        //Click on Submit button
        WebElement btnSubmit=driver.findElement(By.xpath("//span[text()='Submit']"));
        btn_click(btnSubmit);
        Duration timeout=Duration.ofMillis(10000);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Thanks for your response, Automation Wizard!']")));
        WebElement lblMessage=driver.findElement(By.className("vHW8K"));
        String lblMsg=lblMessage.getText();
        System.out.println(lblMsg);
        if(lblMsg.contains("Thanks for your response, Automation Wizard!"))
        {
            System.out.println("Response submitted"); 
        }
        WebElement linkMessage=driver.findElement(By.tagName("a"));
        String linkMsg=linkMessage.getText();
        System.out.println(linkMsg);
        if(linkMsg.contains("Submit another response"))
        {
            System.out.println("Response submitted"); 
        }
        System.out.println("end Test case: testCase01");
        
    }
    public void openURL(String browserURL) {

		System.out.println("Maximize the window");
		driver.manage().window().maximize();

		System.out.println("Opening Website -->" + browserURL);
		driver.get(browserURL);
	}
    public String GetCurrentURL()
    {
        String url=driver.getCurrentUrl();
        return url;
    }
    public void enter_Value(WebElement element,String attributeValue)
    {
        element.sendKeys(attributeValue);
    }
    
    public void btn_click(WebElement element)
    {
        element.click();
    }
}