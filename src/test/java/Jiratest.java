import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class Jiratest {
    @Test
    public void session() {
        RestAssured.baseURI = "http://localhost:8081";

        SessionFilter session = new SessionFilter();

    String response= given().header("Content-type", "application/json").
           body("{\n" +
             "    \"username\": \"sireesha.n\",\n" +
             "    \"password\": \"Vihas@1308\"\n" +
             "}").when().filter(session) .post("/rest/auth/1/session").then().assertThat().statusCode(200).extract()
             .response().asString();

//System.out.println(response);

      // add comment

   String commentresponse=     given().
           header("Content-type", "application/json").log().all().pathParams("id", "10002").filter(session)
                .body("{\n" +
                        "    \"body\": \"Test comment2 for bug.\",\n" +
                        "    \"visibility\": {\n" +
                        "        \"type\": \"role\",\n" +
                        "        \"value\": \"Administrators\"\n" +
                        "    }\n" +
                        "}")
                .when().post("/rest/api/2/issue/{id}/comment").then().assertThat().statusCode(201).extract().response().asString();

   JsonPath js= new JsonPath(commentresponse);
   String commentid=js.getString("id");
        System.out.println(commentid);

        //add attachment

        given().header("Content-type", "multipart/form-data").filter(session).pathParams("id", "10002")
                .header("X-Atlassian-Token", "no-check")
                .multiPart("File", new File("C:/Users/sireesha_narakuchi/IdeaProjects/APItest/src/test/Testattach.txt"))
                .when().post("/rest/api/2/issue/{id}/attachments")
                .then().assertThat().statusCode(200);

        // Get issue

        String getissueresponse=given().filter(session).pathParams("id", "10002").
                queryParam("fields","comment").
                when().get("/rest/api/2/issue/{id}")
                .then().statusCode(200).extract().response().asString();

        JsonPath js1 = new JsonPath(getissueresponse);

       Integer size=js1.getInt("fields.comment.comments.size()");

       System.out.println(size);

for (int i=0; i<size;i++)
{
    String getcommentid=js1.getString("fields.comment.comments["+i+"].id");
    System.out.println(getcommentid);
    if (commentid.equals(getcommentid))
    {
        System.out.println("match");
    }
}
       // System.out.println(getissueresponse);
    }
}
