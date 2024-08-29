import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DynamicJson {

@Test(dataProvider = "bookscreation")
public void addbook(String isbn, String aisle)
    {

   RestAssured.baseURI ="http://216.10.245.166";
        String response1=  given().header("Content-Type", "application/json")
        .body(Payload.addbook(isbn,aisle)).when().post("/Library/Addbook.php")
              .then().assertThat().statusCode(200).extract().response().asString();

      JsonPath js=  Reusableclasses.resuablemethod(response1);

String id=js.getString("ID");
System.out.println(id);
    }

    @DataProvider(name="bookscreation")

    public Object[][] getdata()
    {

        return  new Object[][] {{"ncf", "456"},{"nuy", "506"},{"huy", "776"}};
    }
}
