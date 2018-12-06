package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import pages.HomePage;

public class BaseTest {

    public WebDriver driver;
    public WebDriverWait wait;
    public HomePage homePage;


    @BeforeClass
    public void setup () {
      System.setProperty("webdriver.chrome.driver", "src/main/java/driver/chromedriver.exe");
      driver = new ChromeDriver();
      driver.manage().window().maximize();
      System.out.println(driver);
      wait = new WebDriverWait(driver, 10);
      homePage = new HomePage(driver, wait);
    }


    @AfterClass
    public void close () {
      driver.close();
    }



}
