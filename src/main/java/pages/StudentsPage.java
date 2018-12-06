package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.Step;

public class StudentsPage extends BasePage<StudentsPage> {

  private String pageUrl = "https://www.wiley.com/en-ru/students";
  private String pageHeader = "Students";
  private WebDriver driver;
  public StudentsPage(WebDriver driver, WebDriverWait wait) {
    super(driver, wait);
    this.driver = driver;
  }

  @Step("Check that \"https://www.wiley.com/en-ru/students\" url is opened")
  public StudentsPage checkPage() {
    softAssertTrue(driver.getCurrentUrl().equals(pageUrl), "Page url doesn't match. Expected: " + pageUrl +
            " Actually: " + driver.getCurrentUrl());
    return this;
  }

  @Step("Check that Students header is displayed")
  public StudentsPage checkPageHeader () {
    checkTextElement(By.cssSelector(".sg-title-h1"), this.pageHeader);
    return this;
  }

  @Step("Check WileyPlus is present on page and it leads to \"http://wileyplus.wiley.com/\" url")
  public StudentsPage checkWileyPlusLink () {

    checkTextElement(By.cssSelector("a[href=\"http://wileyplus.wiley.com/\"]"), "WileyPlus");

    return this;
  }


}
