package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.List;

public class EducationPage extends BasePage<EducationPage> {

  private WebDriverWait wait;
  private WebDriver driver;
  private String pageHeader = "Education";
  private String[] subjcetSideMenuTexts = {"Information & Library Science",
          "Education & Public Policy",
          "K-12 General",
          "Higher Education General",
          "Vocational Technology",
          "Conflict Resolution & Mediation (School settings)",
          "Curriculum Tools- General",
          "Special Educational Needs",
          "Theory of Education",
          "Education Special Topics",
          "Educational Research & Statistics",
          "Literacy & Reading",
          "Classroom Management"};

  public EducationPage(WebDriver driver, WebDriverWait wait) {

    super(driver, wait);
    this.driver = driver;
    this.wait = wait;

  }

  @Step("Check “Education” header is displayed")
  public EducationPage checkPageHeader () {
    checkTextElement(By.cssSelector(".section-description .wiley-slogan > h1:last-child span"), this.pageHeader);
    return this;
  }

  @Step("Check Subject menu on the left side of the screen")
  public EducationPage checkSubjectPageItems () {
    List<WebElement> elementsList = findElements(By.cssSelector(".container-with-panel .side-panel ul a"));
    for (WebElement element : elementsList) {
      checkTextElement(element, subjcetSideMenuTexts[elementsList.indexOf(element)]);
    }

    return this;
  }



}
