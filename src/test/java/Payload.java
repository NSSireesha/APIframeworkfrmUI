public class Payload {

    public static String addbook(String isbn, String aisle)
    {
String add_body="{\n" +
        "\"name\":\"Learn Appium Automation with Java\",\n" +
        "\"isbn\":\""+isbn+"\",\n" +
        "\"aisle\":\""+aisle+"\",\n" +
        "\"author\":\"John foer\"\n" +
        "}\n";

return add_body;
    }

}
