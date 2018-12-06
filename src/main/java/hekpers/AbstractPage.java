package hekpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class AbstractPage {

  private WebDriver driver;
  private WebDriverWait wait;
  private SoftAssert softAssertion = new SoftAssert();

  public AbstractPage(WebDriver driver, WebDriverWait wait){
    this.driver = driver;
    this.wait = wait;
  }


  public List<WebElement> findElements (By locator) {
    List<WebElement> elementsList = driver.findElements(locator);
    return elementsList;
  }

  public WebElement findElement (By locator) {
    WebElement element = driver.findElement(locator);
    wait.until(ExpectedConditions.visibilityOf(element));
    return element;
  }


  public void checkTextElement (WebElement element, String text) {

    softAssertTrue(element.isDisplayed(), "Element \"" + text + " isn't displayed.");
    softAssertTrue(element.getText().equals(text), "Text doesn't match. Expected: " + text + " Actually: "
    + element.getText());

  }

  public void checkTextElement (By locator, String text) {

    WebElement element = findElement(locator);
    softAssertTrue(element.isDisplayed(), "Element \"" + text + " isn't displayed.");
    softAssertTrue(element.getText().equals(text), "Text doesn't match. Expected: " + text + " Actually: "
            + element.getText());

  }

  public void checkElementTitle (WebElement element, String title) {
    softAssertTrue(element.getAttribute("title").equals(title), "Title doesn't match. Expected: " +
            title + " Actually: " + element.getAttribute("title"));
  }

  public boolean isElementDisplayed (By locator) {

    boolean result = findElement(locator).isDisplayed();
    return result;

  }

  public void softAssertTrue (Boolean condition, String errorMessage) {
    softAssertion.assertTrue(condition, errorMessage);
  }

  public void finishAsserts () {
    softAssertion.assertAll();
  }

  public void hover (By locator) {
    WebElement element = findElement(locator);
    Actions action = new Actions(driver);
    action.moveToElement(element).build().perform();
  }
}
