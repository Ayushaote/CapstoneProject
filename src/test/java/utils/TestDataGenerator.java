package utils;

public class TestDataGenerator {

    public static String generateNoteTitle(
            String prefix
    ) {

        return prefix
                + "_"
                + System.currentTimeMillis();
    }
}