package api;

import io.restassured.response.Response;
import java.util.List;
import java.util.Map;

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

    public static boolean doesNoteExist(
            String token,
            String noteTitle
    ) {

        Response response =
                getAllNotes(token);

        return response
                .asString()
                .contains(noteTitle);
    }

    public static Response createNote(
            String token,
            String title,
            String description,
            String category
    ) {

        String requestBody =
                "{\n" +
                        "  \"title\": \"" + title + "\",\n" +
                        "  \"description\": \"" + description + "\",\n" +
                        "  \"category\": \"" + category + "\",\n" +
                        "  \"completed\": false\n" +
                        "}";

        return ApiClient.getRequestSpec()
                .header(
                        "x-auth-token",
                        token
                )
                .body(requestBody)
                .post("/notes");
    }

    public static String getNoteIdByTitle(
            String token,
            String noteTitle
    ) {

        Response response =
                getAllNotes(token);

        List<Map<String, Object>> notes =
                response.jsonPath()
                        .getList("data");

        for(Map<String, Object> note : notes) {

            if(note.get("title")
                    .toString()
                    .equals(noteTitle)) {

                return note.get("id")
                        .toString();
            }
        }

        return null;
    }

    public static Response deleteNote(
            String token,
            String noteId
    ) {

        return ApiClient.getRequestSpec()
                .header(
                        "x-auth-token",
                        token
                )
                .delete("/notes/" + noteId);
    }
}