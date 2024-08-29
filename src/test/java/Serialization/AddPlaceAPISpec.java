package Serialization;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class AddPlaceAPISpec {

    public static void main(String args[]) {



        RestAssured.baseURI = "https://rahulshettyacademy.com";

        AddPlace p = new AddPlace();
        p.setAccuracy(50);
        p.setAddress("29, side layout, cohen 09");
        p.setName("Frontline house");
        p.setPhone_number("(+91) 983 893 3937");
        p.setWebsite("http://google.com");
        p.setLanguage("French-IN");

        Location loc= new Location();
        loc.setLat(-38.383494);
        loc.setLng(33.427362);
        p.setLocation(loc);
List<String> typ = new ArrayList<String>() ;

typ.add("shoe park");
typ.add("shop");



        p.setTypes(typ);

       RequestSpecification req= new  RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .setContentType(ContentType.JSON).addQueryParam("qa","qaclick123").build();

       ResponseSpecification res =new ResponseSpecBuilder().expectStatusCode(200)
               .expectContentType(ContentType.JSON).build();

        String response= given().spec(req)

                .body(p).when().post("/maps/api/place/add/json").then().spec(res).extract().response().asString();

        System.out.println(response);
    }

}

