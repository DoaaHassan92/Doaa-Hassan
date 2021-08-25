# Automation-API
This is the solution of automation api part

# Tests cases and explanations:
### CRUD Operations for 'Products'
1. First I add a test to get all products and validate response time, response status  code, response schema and headers
2.  Test to create a new product and validate response time, response status  code, response schema then I get the Id of the created prodcut 
3. Test to get product by Id and pass the Id from create product request to the path parameter of the get request and again validate response time, response status  code, response schema 
4. Test to update a created product name and validate response time, response status  code, response schema then I get the Id of the updated prodcut
5. Test to get product by Id after update and pass the Id from create product request to the path parameter of the get request and again validate response time, response status  code, response schema 
6. Test to delete a created product and validate response time, response status  code,  then I get the Id of the updated prodcut
7. Test to get product by Id after deleting it to make sure it's deleted by passing the Id from delete product request to the path parameter of the get request and again validate this product is not found
### The main Frameworks included in the project:
* Rest-Assured
* TestNG

### The main pre installations needed in the project:
* Java 11 or 15
* Maven 3.6.3
* Inteliji


### How to run the project main test cases locally:

* Can find the test cases in the *src/test/java* folder mainly in the 'products' pacakage, you will find four packages for create, update, get and delete prpducts
* To start the execution, right click on the test testing.xml file and click Run As >> Testing.xml
* 
### Finally, you can also find a video for all the tests recorded in https://recordit.co/Vjf7BNhkUK
