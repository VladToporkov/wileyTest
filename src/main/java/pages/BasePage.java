package pages;

import hekpers.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.Step;


import java.util.List;

public class BasePage<P extends BasePage<P>> extends AbstractPage {

  private String url;
  private WebDriver driver;
  private WebDriverWait wait;
  private String pageUrl;
  private String[] highLevelNavigationButtonTitles = {"Resources", "Subjects", "About"};
  private String[] resourcesDropDownTitles = {"Authors", "Corporations", "Institutions", "Instructors", "Journalists",
          "Librarians", "Professionals", "Researchers", "Resellers", "Societies", "Students"};

  public BasePage(WebDriver driver, WebDriverWait wait) {
    super(driver, wait);
    this.driver = driver;
    this.wait = wait;
  }

  @Step("Click on the Wiley logo at the top menu")
  public HomePage clickLogo () {
    findElement(By.cssSelector(".main-navigation-menu .logo a"));
    return new HomePage(driver, wait);
  }

  //--------------------------------------------Navigetion-menu------------------------------------------

  @Step("Check links from navigation are displayed in the top menu")
  public P checkHighLevelNavigation () {
    List<WebElement> highLevelNavigation = findElements(By.cssSelector(".navigation-menu-items .collapsed"));
    for (WebElement element : highLevelNavigation) {
      checkTextElement(element, highLevelNavigationButtonTitles[highLevelNavigation.indexOf(element)].toUpperCase());
    }

    return (P) this;
  }

  @Step("Open Resources Drop Down Menu")
  public P openResourcesTopMenu() {

    hover(By.cssSelector(".navigation-menu-items > li > a[href=\"#\"]"));

    return (P) this;
  }

  @Step("Check items under Resources sub-header")
  public P checkResourcesDropDownMenu () {

    List<WebElement> elementList = findElements(By.cssSelector("#navigationNode_00000RS2 a"));
    for (WebElement element : elementList) {
      checkElementTitle(element, resourcesDropDownTitles[elementList.indexOf(element)]);
    }
    return (P) this;
  }

  @Step("Open Subjects top menu")
  public P openSubjectsTopMenu () {
    hover(By.cssSelector("a[href=\"/en-ru/subjects\"]"));

    return (P) this;
  }

  @Step("Select “E-L”")
  public P openSecondSectionOfSubjectMenu () {
    hover(By.cssSelector("#navigationNode_00000RS5 a[title=\"E-L\"]"));
    return (P) this;
  }

  @Step("Go to Education Page")
  public EducationPage goToEducationPage () {
    findElement(By.cssSelector("#navigationNode_00000RS5 a[title=\"Education\"]")).click();

    return new EducationPage(driver, wait);
  }


  @Step("Open students page")
  public StudentsPage goToStudentsPage () {
    WebElement studentsLink = findElement(By.cssSelector("#navigationNode_00000RS2 [title=\"Students\"]"));
    studentsLink.click();

    return new StudentsPage(driver, wait);
  }

  //----------------------------------Search------------------------------------------------------------/

  @Step("Submit empty search")
  public P submitEmptySearch () {
    findElement(By.cssSelector("[name=\"search_form_SearchBox\"] button[type=\"submit\"]")).click();
    return (P) this;
  }

  @Step("Enter text in search field")
  public P fillSearchField (String text) {
    WebElement input = findElement(By.cssSelector("#js-site-search-input"));
    input.sendKeys(text);
    return (P) this;
  }

  @Step("Area with related content is displayed right under the search header")
  public P checkAutocompliteIsOpened () {
    softAssertTrue(isElementDisplayed(By.cssSelector("#ui-id-2")), "Autocomplite doesn't displayed");
    return (P) this;
  }

  @Step("On the left side, it has 4 words starting with text")
  public P checkLeftSideAutocomplite (int count, String text) {
    List<WebElement> elementList =  findElements(By.cssSelector("#ui-id-2 .search-list a"));
    softAssertTrue(elementList.size() == count, "Unexpected elements count. Expected: " + count
            + " Actully:" + elementList.size());
    for(WebElement element: elementList) {
      String elementText = element.getText();
      softAssertTrue(elementText.substring(0,text.length()).equals(text), "Text doesn't match. Expected: "
              + text + " Actually:" + elementText.substring(0,text.length()));
    }

    return (P) this;
  }

  @Step("On the right side, there are 4 titles under “Related content” and each title contain word")
  public P checkRightSideAutocomplite(int count, String text) {

    List<WebElement> elementList = findElements(By.cssSelector("#ui-id-2 .search-related-content .related-content-products .product-item"));
    softAssertTrue(elementList.size() == count, "Unexpected elements count. Expected: " + count
            + " Actully:" + elementList.size());
    for ( WebElement element : elementList) {
      String elementTitle = element.findElement(By.cssSelector(".product-title a")).getText();
      softAssertTrue(elementTitle.contains(text), "Text doesn't contains " + text);
    }

    return (P) this;
  }

  @Step("Submit search")
  public SearchResultPage submitSerch () {
    findElement(By.cssSelector("[name=\"search_form_SearchBox\"] button")).click();
    return new SearchResultPage(driver, wait);
  }




}
