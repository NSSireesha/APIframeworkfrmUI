package Ecommerce;

import Ecommerce.Pojo.LoginResponse;
import Ecommerce.Pojo.Loginclass;
import Ecommerce.Pojo.Orderdetails;
import Ecommerce.Pojo.Orders;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class Login {

public static void main(String args[])
{

    Loginclass login = new Loginclass();

    login.setUserEmail("sireesha.n@cdf.com");
    login.setUserPassword("Vihas@1308");
    RequestSpecification req= new RequestSpecBuilder().setBaseUri("https://www.rahulshettyacademy.com").setContentType(ContentType.JSON).build();


    ResponseSpecification resp= new ResponseSpecBuilder().expectStatusCode(200).build();

    LoginResponse loginResponse=given().spec(req).body(login).when().post("/api/ecom/auth/login")
            .then().spec(resp).extract().response().as(LoginResponse.class);
 String userid=   loginResponse.getUserId();

    String token= loginResponse.getToken();
    System.out.println(token);

    //Add a product

   RequestSpecification addreq= new RequestSpecBuilder()
           .setBaseUri("https://rahulshettyacademy.com")
                   .addHeader("Authorization",token) .build();

  String addproductresponse= given().log().all().spec(addreq).param("productAddedBy",userid)
                   .param("productCategory", "fashion").

           param("productSubCategory", "shirst")
                   .param("productPrice","600")
                           .param("productDescription","Addias Originals")
                                   .param("productFor","women")
                                           .param("productName","jutebag")
           .multiPart("productImage", new File("C:\\Users\\sireesha_narakuchi\\OneDrive - EPAM\\Pictures\\bag.jpg"))

           .when().post("/api/ecom/product/add-product").then().extract().response().asString();

    JsonPath js= new JsonPath(addproductresponse);
  String productid=  js.getString("productId");
  System.out.println(productid);

  //create order

  RequestSpecification orderreq=  new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
          .setContentType(ContentType.JSON).addHeader("Authorization",token)
          .build();

    Orderdetails od= new Orderdetails();
    od.setCountry("India");
    od.setProductOrderedId(productid);

    List<Orderdetails> order1= new ArrayList<Orderdetails>();
    order1.add(od);

Orders orders= new Orders();
orders.setOrders(order1);
 String createoderresp= given().spec(orderreq).body(orders).when().post("/api/ecom/order/create-order").then().extract().asString();

 System.out.println(createoderresp);

//delete product

   RequestSpecification deletereq= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
           .addHeader("Authorization", token).build();

   String deleteresp=given().spec(deletereq).pathParam("productID",productid ).when().delete("/api/ecom/product/delete-product/{productID}")
           .then().extract().response().asString();

   System.out.println(deleteresp);

}
}


