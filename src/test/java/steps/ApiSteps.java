package steps;

import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static specs.Spec.request;
import static specs.Spec.response200;
import static tests.TestBase.item;

public class ApiSteps {

    @Step("Get total search amount via API")
    public String getTotalSearchAmountViaAPI() {
        Response response = given()
                .queryParam("pojam", item)
                .queryParam("page", "true")
                .spec(request)
                .when()
                .get()
                .then()
                .spec(response200)
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        return Integer.toString(jsonPath.get("hits.total"));
    }
}
