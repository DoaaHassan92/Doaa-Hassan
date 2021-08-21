package products.update;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import products.create.createProductTest;

import static org.hamcrest.Matchers.*;

public class getProductAfterUpdate {
    private io.restassured.specification.ResponseSpecification ResponseSpecification;
    private updateProductTest updateProductTest;
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
    public int getUpdatedProductId(){
        updateProductTest=new updateProductTest();
        return updateProductTest.updateProductNameAndGetId();


    }
    @Test
    public void checkTheUpdatedProductName(){
        id=getUpdatedProductId();
        RestAssured.given()
                .when().pathParam("id",id)
                .get("products/{id}")
                .then().spec(ResponseSpecification).log().everything().assertThat().body("id",equalTo(id),"name",equalTo("Samsung note 9"));
    }
}
