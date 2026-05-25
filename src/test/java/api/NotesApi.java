package api;

import io.restassured.response.Response;

public class NotesApi {

    public static Response getAllNotes(
            String token
    ) {

        return ApiClient.getRequestSpec()
                .header(
                        "x-auth-token",
                        token
                )
                .get("/notes");
    }
}