package products.update;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import products.create.createProductTest;

import static org.hamcrest.Matchers.*;

public class updateProductTest {
    private io.restassured.specification.ResponseSpecification ResponseSpecification;
    private products.create.createProductTest createProductTest;
    int id;

    public int getProductId(){
        createProductTest=new createProductTest();
        return createProductTest.createNewProduct();
    }
    @Test
    public void updateProductName(){
        updateProductNameAndGetId();
    }
    public int updateProductNameAndGetId(){
        id=getProductId();
         int updatedId=
         RestAssured.given().baseUri("http://localhost:3030/").header("Content-type", "application/json")
                .when().pathParam("id",id).and().body("{\n" +
                " \"name\": \"Samsung note 9\"\n" +
                "}")
                .patch("products/{id}")
                .then().statusCode(200).log().everything().extract().path("id");
return updatedId;
    }

}
