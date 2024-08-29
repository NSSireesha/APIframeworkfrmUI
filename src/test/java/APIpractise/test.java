package APIpractise;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;

public class test {

    public static void main(String[] args) {
        JsonPath js= new JsonPath(jsonclass.response());
      int count=  js.get("courses.size()");
        System.out.println(count);

        int amount= js.getInt("dashboard.purchaseAmount");
        //System.out.println(amount);

       // System.out.println(js.getString("courses[1].title"));

        int totalamount=0;
        for (int i=0; i<count; i++)

        {


         //   System.out.println(js.getString("courses["+i+"].title"));

           Integer price= js.getInt("courses["+i+"].price");

           Integer copies= js.getInt("courses["+i+"].copies");

           int amt= price * copies;
            totalamount= totalamount+amt;





        }

        System.out.println(totalamount);
    }




}
