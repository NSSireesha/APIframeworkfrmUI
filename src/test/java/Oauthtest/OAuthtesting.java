
package Oauthtest;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static io.restassured.RestAssured.given;
public class OAuthtesting {

    public static void main (String args[])
    {
       // RestAssured.baseURI ="https://rahulshettyacademy.com";

      /*  System.setProperty("webdriver.chrome.driver", "C:\\Users\\sireesha_narakuchi\\OneDrive - EPAM\\Pictures\\QE\\Automation\\chromedriver_win32");

        WebDriver driver= new ChromeDriver();

        driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");

        driver.findElements(By.cssSelector())
*/

        String Url="https://rahulshettyacademy.com/getCourse.php?code=4%2F0AdLIrYe93yuEHWlzhcijIg6qpJeQ6yMTRlRNH-A-rY103JG_9DR7-fthwP8RSTHiTyhp4A&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=2&prompt=none";
        String partial=Url.split("code=")[1];
        String code=partial.split("&scope")[0];

        System.out.println(code);

        String resp=given().urlEncodingEnabled(false)

                .queryParam("code",code)
                .queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                .queryParam("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
                .queryParam("grant_type", "authorization_code")
              //  .header("Content-Type", "application/json")

                .when().log().all().post("https://www.googleapis.com/oauth2/v4/token").asString();

        System.out.println(resp);
        JsonPath js= new JsonPath(resp);
       String access_token= js.getString("access_token");
        System.out.println("accesstoken is"+access_token);
        String response=given().log().all().queryParam("access_token", access_token).when().get("https://rahulshettyacademy.com/getCourse.php")
                .asString();


        System.out.print(response);
    }




}


