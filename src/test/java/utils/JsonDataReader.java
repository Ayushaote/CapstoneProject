package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonDataReader {

    private static JsonNode testData;

    static {

        ObjectMapper mapper =
                new ObjectMapper();

        try {

            testData = mapper.readTree(
                    new File(
                            "src/test/resources/testdata.json"
                    )
            );

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public static String getData(
            String section,
            String key
    ) {

        return testData
                .get(section)
                .get(key)
                .asText();
    }
}