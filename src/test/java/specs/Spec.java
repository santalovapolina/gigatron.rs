package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;

public class Spec {

    public static RequestSpecification request = with()

            .baseUri("https://search.gigatron.rs/v1/search/")
            .basePath("get")
            .log().uri()
            .log().method()
            .contentType("application/json");


    public static ResponseSpecification response200 = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();

    public static ResponseSpecification response400 = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .build();

    public static ResponseSpecification response500 = new ResponseSpecBuilder()
            .expectStatusCode(500)
            .build();

}
