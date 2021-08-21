package products.delete;

import io.restassured.RestAssured;
import org.testng.annotations.Test;
import products.create.createProductTest;

public class deleteProductTest {
    private io.restassured.specification.ResponseSpecification ResponseSpecification;
    private products.create.createProductTest createProductTest;
    int id;

    public int getProductId(){
        createProductTest=new createProductTest();
        return createProductTest.createNewProduct();
    }
    @Test
    public void deleteTheCreatedProduct(){
        deleteTheCreatedProductAndGetId();
    }
    public int deleteTheCreatedProductAndGetId(){
        id=getProductId();
        int deletedId=
                RestAssured.given().baseUri("http://localhost:3030/").header("Content-type", "application/json")
                        .when().pathParam("id",id)
                        .delete("products/{id}")
                        .then().statusCode(200).log().everything().extract().path("id");
        return deletedId;
    }

}
