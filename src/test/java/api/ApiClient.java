package api;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import utils.ConfigReader;

public class ApiClient {

    public static RequestSpecification getRequestSpec() {

        return RestAssured
                .given()
                .baseUri(
                        ConfigReader.getProperty(
                                "api.base.url"
                        )
                )
                .header(
                        "Content-Type",
                        "application/json"
                );
    }
}
