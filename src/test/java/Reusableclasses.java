import io.restassured.path.json.JsonPath;

public class Reusableclasses {

    public static JsonPath resuablemethod(String response)
    {
        JsonPath js= new JsonPath(response);
        return js;

    }
}
