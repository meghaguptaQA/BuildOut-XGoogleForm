package demo;
import demo.wrappers.Wrappers;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

import java.sql.Wrapper;
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

    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");


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
        Wrappers.openURL(driver,"https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform");
        String url=Wrappers.GetCurrentURL(driver);
        Thread.sleep(5000);
   
        
        //Enter the value "Crio Learner" in Name field  
        WebElement txtboxName=driver.findElement(By.xpath("//input[@type='text']"));
        Wrappers.enter_Value(txtboxName,"Crio Learner");
        //Get current epoch time
        long epochtime=System.currentTimeMillis()/1000;
        //Enter value "I want to be the best QA Engineer! +current epoch time "in Why are you practicing Automation? field
        //Xpath : //div[@class='edhGSc zKHdkd kRy7qc RdH0ib yqQS1']
        WebElement txtboxPracticeAutomation=driver.findElement(By.xpath("//textarea[@class='KHxj8b tL9Q4c']"));
        Wrappers.enter_Value(txtboxPracticeAutomation,"I want to be the best QA Engineer! "+epochtime);
        //Select value in Experience Radio button
  
        String txtExp="0 - 2";
        WebElement radiobtnExperience=driver.findElement(By.xpath("//span[contains(@class,'OvPDhc') and contains(text(),'"+txtExp+"')]"));
        Wrappers.btn_click(radiobtnExperience);

        String txtJava="Java";
        String txtSelenium="Selenium";
        String txtTestNG="TestNG";
        WebElement checkboxJava=driver.findElement(By.xpath("//span[contains(text(),'"+txtJava+"')]/../../..//div[2]"));
        Wrappers.btn_click(checkboxJava);
        //Select Selenium Checkbox in Which of the following have you learned in Crio.Do for Automation Testing?
        WebElement checkboxSelenium=driver.findElement(By.xpath("//span[contains(text(),'"+txtSelenium+"')]/../../..//div[2]"));
        Wrappers.btn_click(checkboxSelenium);
        //Select TestNG Checkbox in Which of the following have you learned in Crio.Do for Automation Testing?
        WebElement checkboxTestNG=driver.findElement(By.xpath("//span[contains(text(),'"+txtTestNG+"')]/../../..//div[2]"));
        Wrappers.btn_click(checkboxTestNG);

        WebElement dropdown = driver.findElement(By.xpath("//div[contains(@class,'MocG8c')]"));
        //System.out.println("Selected preferred address type");

        // Click to open the dropdown
        Wrappers.btn_click(dropdown);

        Thread.sleep(3000);
        //Choose value in Addressed dropdown
       String txtMs="Ms";
       List<WebElement> options=driver.findElements(By.xpath("//div[contains(@class,'ncFHed')]//span[not(contains(text(),'Choose'))]"));
       for (WebElement option : options)
       {
            if(option.getText().contains(txtMs))
            {
                Wrappers.btn_click(option);
            }
       }
        //Enter the date 7 days ago in field:What was the date 7 days ago?
        //Calculate the date
        LocalDate todaysdate=LocalDate.now();
        String datetoenter=Wrappers.date_decrmenter(todaysdate,7);      

        System.out.println(datetoenter);
        WebElement txtboxDate=driver.findElement(By.xpath("//input[@type='date']"));        
        Wrappers.enter_Value(txtboxDate,datetoenter);

        
        WebElement txthour=driver.findElement(By.xpath("//input[@aria-label='Hour']"));
        Wrappers.enter_Value(txthour, "07");
        WebElement txtminute=driver.findElement(By.xpath("//input[@aria-label='Minute']"));
        Wrappers.enter_Value(txtminute, "30");

        //Click on Submit button
        WebElement btnSubmit=driver.findElement(By.xpath("//span[text()='Submit']"));
        Wrappers.btn_click(btnSubmit);
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
   
}
