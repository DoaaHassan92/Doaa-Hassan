package products.delete;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import products.update.updateProductTest;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class getProductIdAfterDelete {
    private io.restassured.specification.ResponseSpecification ResponseSpecification;
    private deleteProductTest deleteProductTest;
    int id;
    @BeforeTest
    public void setup()
    {
        RestAssured.baseURI = "http://localhost:3030/";
    }
    @BeforeTest
    public void setResponseSpecification(){
        ResponseSpecification = new ResponseSpecBuilder().expectStatusCode(404).
                expectResponseTime(lessThan(4000L)).expectContentType("application/json").build();
    }
    public int getDeletedProductId(){
        deleteProductTest=new deleteProductTest();
        return deleteProductTest.deleteTheCreatedProductAndGetId();


    }
    @Test
    public void checkTheUpdatedProductName(){
        id=getDeletedProductId();
        RestAssured.given()
                .when().pathParam("id",id)
                .get("products/{id}")
                .then().spec(ResponseSpecification).log().everything().assertThat().body("name",equalTo("NotFound"));
    }
}
