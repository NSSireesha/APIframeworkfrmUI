package org.example;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.example.Pojoclass.API;
import org.example.Pojoclass.GetCourses;
import org.example.Pojoclass.WebAutomation;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;


public class OAuth {

    @Test

    public void sessionid() {
        RestAssured.baseURI = "https://rahulshettyacademy.com/";
        String response = given().formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                .formParam("grant_type", "client_credentials")
                .formParam("scope", "trust").when().post("/oauthapi/oauth2/resourceOwner/token")
                .then().assertThat().statusCode(200).extract().response().asString();
        JsonPath js = new JsonPath(response);
        String token = js.getString("access_token");
        System.out.println(token);

        //getrequest

        GetCourses gc = (GetCourses) given().log().all().queryParam("access_token", token).when().get("oauthapi/getCourseDetails")
             //   .then().extract().response().as(GetCourses.class);

                .then().time(1080, TimeUnit.SECONDS);
        List<API> apis = gc.getCourses().getApi();

        for (int i = 0; i < apis.size(); i++) {

            if (apis.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) ;
            {
                System.out.println(apis.get(i).getCourseTitle());
                System.out.println(apis.get(i).getPrice());
            }
        }

        String[] expectedcoursetitles= {"Selenium Webdriver Java", "Cypress","Protractor"};
        //Get the course names of WebAutomation

        List<WebAutomation> wc=gc.getCourses().getWebAutomation();
        ArrayList<String> a= new ArrayList<String>();

                for(int j=0; j<wc.size();j++)
                {
                  System.out.println(wc.get(j).getCourseTitle());
                  a.add(wc.get(j).getCourseTitle());
                }

      List<String> expcted= Arrays.asList(expectedcoursetitles);
               Assert.assertTrue(a.equals(expcted)); ;

    }
}

