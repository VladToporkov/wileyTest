package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.get;
import io.restassured.response.Response;
public class RestTest {

/*
Develop automation tests to check status and response using Java and any library of your choice.
There is a simple HTTP Request & Response Service https://httpbin.org
GET/delay/{delay}
"Returns a delayed response (max of 10 seconds).

GET/image/png
Returns a simple PNG image.
*/
  public String baseUrl = "https://httpbin.org";


  @Test
  public void testDelay () {

    Response response = get(baseUrl + "/delay/9");
    Assert.assertEquals(response.statusCode(), 200);
    Assert.assertFalse(response == null);

  }


  @Test
  public void testPng () {

    Response response = get(baseUrl + "/image/png");
    Assert.assertEquals(response.statusCode(), 200);
    Assert.assertTrue(response.getHeader("Content-Type").equals("image/png"));

  }

}
