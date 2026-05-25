package api;

import io.restassured.response.Response;

public class AuthApi {

    public static String getAuthToken(
            String email,
            String password
    ) {

        String requestBody =
                "{\n" +
                        "  \"email\": \"" + email + "\",\n" +
                        "  \"password\": \"" + password + "\"\n" +
                        "}";

        Response response =
                ApiClient.getRequestSpec()
                        .body(requestBody)
                        .post("/users/login");

        response.then().statusCode(200);

        return response
                .jsonPath()
                .getString("data.token");
    }
}