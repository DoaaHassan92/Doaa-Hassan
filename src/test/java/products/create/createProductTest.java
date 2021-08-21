package products.create;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import products.getProducts.getProductsTest;

import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

public class createProductTest  {
    private int id;
    private io.restassured.specification.ResponseSpecification ResponseSpecification;
    protected String name="Galaxy inspiron";
    protected String type="HardGood";
    protected int price=233;
    protected String upc="991333424019";
    protected String description="Compatible with select electronic devices";
    protected String model="DN2400B4Z";

    ProductInfo productInfo=new ProductInfo(name,type,price,upc,
            description,model);
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String product = gson.toJson(productInfo);//serialization
    @Test
    public void test(){
        createNewProduct();
    }
    public  int createNewProduct()
    {
               id= RestAssured.given().baseUri("http://localhost:3030/").header("Content-Type", "application/json")
                        .body(product)
                        .when()
                        .post("products")
                        .then().statusCode(201).log().everything().assertThat().body("id",notNullValue(),"name",notNullValue()
                ,"type",notNullValue(),"price",notNullValue(),"upc",notNullValue(),"description",notNullValue(),
                        "model",notNullValue(),"createdAt",notNullValue()).extract().path("id");
               return id;
    }
}
