package tests;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class SeleniumTest extends BaseTest {

  public List<String> searchTitles = new ArrayList<String>();


  @Test
  public void testWork () {

    /*
    Step 9
    Enter “Math” in the search input at the top and press “SEARCH” button
    Make sure there are same 10 titles shown (as in step 8)
    */

    homePage
            //Step 1
            .getCDAPage()
            .closeChangeLocationPopup()
            .checkHighLevelNavigation()
            // Step 2
            .openResourcesTopMenu()
            .checkResourcesDropDownMenu()
            //Step 3
            .goToStudentsPage()
            .checkPage()
            .checkPageHeader()
            .checkWileyPlusLink()
            //Step 4
            .openSubjectsTopMenu()
            .openSecondSectionOfSubjectMenu()
            .goToEducationPage()
            .checkPageHeader()
            .checkSubjectPageItems()
            // Step 5
            .clickLogo()
            .checkPageUrl()
            //Step 6
            .submitEmptySearch()
            .checkPageUrl()
            // Step 7
            .fillSearchField("Math")
            .checkAutocompliteIsOpened()
            .checkLeftSideAutocomplite(4, "math")
            .checkRightSideAutocomplite(4, "math")
            // Step 8
            .submitSerch()
            .checkResultsCount(10)
            .checkResultsContent("Math")
            .saveSearchReultTitles(searchTitles)
            // Step 9
            .fillSearchField("Math")
            .submitSerch()
            .checkSearchResultSame(searchTitles)

            .finishAsserts();

  }
}