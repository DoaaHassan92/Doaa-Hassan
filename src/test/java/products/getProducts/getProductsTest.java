package products.getProducts;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;

public class getProductsTest {
    private int limit=5;
    private ResponseSpecification ResponseSpecification;
    @BeforeTest
    public void setup()
    {
        RestAssured.baseURI = "http://localhost:3030/";
    }
    @BeforeTest
    public void setResponseSpecification(){
        ResponseSpecification = new ResponseSpecBuilder().expectStatusCode(200).
                expectResponseTime(lessThan(4000L)).expectContentType("application/json").build();
    }
    public Response getFiveProducts(){
       return RestAssured.given().when().queryParam("$limit",limit).
                get("products/").then().extract().response();
    }
    @Test
    public void validateProductSchema(){
        Response response=getFiveProducts();
        JsonPath js = new JsonPath(response.asString());
        for(int i=0;i<limit;i++){
            System.out.println(js.getString("data["+i+"].id"));
            response.then().spec(ResponseSpecification).assertThat().body("data["+i+"].id",notNullValue(),"data["+i+"].name",notNullValue(),"data["+i+"].upc",
                    notNullValue(),"data["+i+"].description",notNullValue(),"data["+i+"].model",notNullValue());
        }
    }
}
