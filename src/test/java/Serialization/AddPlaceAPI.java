package Serialization;

import io.restassured.RestAssured;

//import static.io.restassured.RestAssured.*;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class AddPlaceAPI {

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

        String response= given().queryParam("qa", "qaclick123").header("Content-Type", "application/json")
                .body(p).when().post("/maps/api/place/add/json").then().statusCode(200).extract().response().asString();

        System.out.println(response);
    }

}

