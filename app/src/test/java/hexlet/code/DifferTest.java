package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DifferTest {
    private static String correctResultJson;

    private static Path getResourcesPath(String fileName) {
        return Paths.get("./src/test/resources" + fileName).toAbsolutePath().normalize();
    }

    private static String readResourceFile(String fileName) throws Exception {
        return Files.readString(getResourcesPath(fileName)).trim();
    }

    @BeforeAll
    public static void beforeAll() throws Exception {
        correctResultJson = readResourceFile("correct_result_json.txt");
    }

    @Test
    @DisplayName("'generate' method works correctly")
    void testgenerateCorrectly() throws Exception {
        String diffrResult = Differ.generate("./src/test/resources/file1.json", "./src/test/resources/file2.json");
        assertEquals(correctResultJson, diffrResult);
    }

    @Test
    @DisplayName("'generate' method throws an exception when reading the first file")
    void testMainFirstFileDoesNotExist() throws Exception {
        assertThrows(Exception.class, () -> {
            String diffrResult = Differ.generate("./src/test/resources/file3.json", "./src/test/resources/file2.json");
        });
    }

    @Test
    @DisplayName("'generate' method throws an exception when reading the second file")
    void testMainSecondFileDoesNotExist() throws Exception {
        assertThrows(Exception.class, () -> {
            String diffrResult = Differ.generate("./src/test/resources/file1.json", "./src/test/resources/file3.json");
        });
    }
}
