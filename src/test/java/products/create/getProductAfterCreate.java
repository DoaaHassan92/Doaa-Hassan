package products.create;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class getProductAfterCreate {
    private io.restassured.specification.ResponseSpecification ResponseSpecification;
    private createProductTest createProductTest;
    int id;
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

    public int getProductId(){
       createProductTest=new createProductTest();
    return createProductTest.createNewProduct();


    }
    @Test
    public void getTheLastCreatedProduct(){
        id=getProductId();
        RestAssured.given()
                .when().pathParam("id",id)
                .get("products/{id}")
                .then().spec(ResponseSpecification).assertThat().body("id",equalTo(id),"name",equalTo(createProductTest.name)
                ,"type",equalTo(createProductTest.type),"price",equalTo(createProductTest.price),"upc",equalTo(createProductTest.upc)
                ,"description",equalTo(createProductTest.description),
                "model",equalTo(createProductTest.model),"createdAt",notNullValue()).extract().path("id");
        }

}
