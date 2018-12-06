package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.ArrayList;
import java.util.List;

public class SearchResultPage extends BasePage<SearchResultPage> {
  private WebDriver driver;
  private WebDriverWait wait;


  public SearchResultPage(WebDriver driver, WebDriverWait wait) {
    super(driver, wait);
    this.driver = driver;
    this.wait = wait;
  }


  @Step("Check elements count on result page")
  public SearchResultPage checkResultsCount (int count) {
    List<WebElement> elementList = findElements(By.cssSelector(".products-list .product-item "));
    softAssertTrue(elementList.size()== count, "Count elements doesn't match. Expected: " + count
            + " Actually: " + elementList.size());
    return this;
  }

  @Step("Check results content")
  public SearchResultPage checkResultsContent (String text) {
    List<WebElement> elementList = findElements(By.cssSelector(".products-list .product-item "));
    for (WebElement element : elementList) {
      String title = element.findElement(By.cssSelector(".product-title a")).getText();
      softAssertTrue(title.contains(text), "Product title doesn't contains " + text);
      List<WebElement> addToCardButtonList = element.findElements(By.cssSelector(".add_to_cart_form .js-add-to-cart"));
      softAssertTrue(addToCardButtonList.size() >=1, "Product doesn't contain 'Add to card' button");
    }
    return this;
  }

  @Step("Save search result titles")
  public SearchResultPage saveSearchReultTitles (List<String> searchTitles) {
    List<WebElement> elementList = findElements(By.cssSelector(".products-list .product-item .product-title a "));
    for (WebElement element : elementList) {
      String title = element.getText();
      searchTitles.add(title);
    }
    return this;
  }

  @Step("Search result are same")
  public SearchResultPage checkSearchResultSame (List<String> searchTitles) {
    List<WebElement> elementList = findElements(By.cssSelector(".products-list .product-item"));
    for (WebElement element : elementList) {
      String title = element.findElement(By.cssSelector(".product-title a")).getText();
      softAssertTrue(title.equals(searchTitles.get(elementList.indexOf(element))), "Title doesn't match. Expected: " +
              searchTitles.get(elementList.indexOf(element)) + " Actually: " + title);
    }
    return this;
  }

}
