package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.Step;

public class HomePage extends BasePage<HomePage> {

  private String CDAurl = "https://www.wiley.com/WileyCDA/";
  private String url = "https://www.wiley.com/WileyCDA/";
  public WebDriver driver;

  public HomePage(WebDriver driver, WebDriverWait wait) {
    super(driver, wait);
    this.driver = driver;
  }

  @Step("Open Main page")
  public HomePage getCDAPage() {
    try {
      driver.get("https://www.wiley.com/WileyCDA/");

    } catch (Exception e) {
      System.out.print(driver);
    }
    return this;
  }

  @Step("Check Home page is opened")
  public HomePage checkPageUrl () {
    softAssertTrue(driver.getCurrentUrl().equals(url), "Page url doesn't match. Expected: " + url +
            " Actually: " + driver.getCurrentUrl());
    return this;
  }

  @Step("Close change location popup")
  public HomePage closeChangeLocationPopup () {
    if (isElementDisplayed(By.cssSelector("#selectCountryModalWnd"))) {
      findElement(By.cssSelector("#selectCountryModalWnd .changeLocationCancelBtn ")).click();
    }
    return this;
  }






}
