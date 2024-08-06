package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    private static String correctResultJSON;
    private static String correctResultPlain;
    private static String correctResultStylish;
    private static final String PATH_TO_FIXTURES = "./src/test/resources/";

    public static String readFixture(String fileName) throws Exception {
        Path path = Paths.get(PATH_TO_FIXTURES + fileName).toAbsolutePath().normalize();

        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }

        return Files.readString(path).trim();
    }

    @BeforeAll
    public static void beforeAll() throws Exception {
        correctResultJSON = readFixture("correct_result_json.json");
        correctResultPlain = readFixture("correct_result_plain.txt");
        correctResultStylish = readFixture("correct_result_stylish.txt");
    }

    @Test
    @DisplayName("'generate' method for JSON works correctly without format")
    void testGenerateCorrectlyJSON() throws Exception {
        String differResult = Differ.generate(PATH_TO_FIXTURES + "file1.json", PATH_TO_FIXTURES + "file2.json");
        assertEquals(correctResultStylish, differResult);
    }

    @Test
    @DisplayName("'generate' method for JSON works correctly with stylish format")
    void testGenerateCorrectlyJSONWithStylish() throws Exception {
        String differResult = Differ.generate(PATH_TO_FIXTURES + "file1.json", PATH_TO_FIXTURES + "file2.json",
                "stylish");
        assertEquals(correctResultStylish, differResult);
    }

    @Test
    @DisplayName("'generate' method for JSON works correctly with plain format")
    void testGenerateCorrectlyJSONWithPlain() throws Exception {
        String differResult = Differ.generate(PATH_TO_FIXTURES + "file1.json", PATH_TO_FIXTURES + "file2.json",
                "plain");
        assertEquals(correctResultPlain, differResult);
    }

    @Test
    @DisplayName("'generate' method for JSON works correctly with JSON format")
    void testGenerateCorrectlyJSONWithJSON() throws Exception {
        String differResult = Differ.generate(PATH_TO_FIXTURES + "file1.json", PATH_TO_FIXTURES + "file2.json",
                "json");
        assertEquals(correctResultJSON, differResult);
    }

    @Test
    @DisplayName("'generate' method for YAML works correctly without format")
    void testGenerateCorrectlyYAML() throws Exception {
        String differResult = Differ.generate(PATH_TO_FIXTURES + "file1.yml", PATH_TO_FIXTURES + "/file2.yml");
        assertEquals(correctResultStylish, differResult);
    }

    @Test
    @DisplayName("'generate' method for YAML works correctly with stylish format")
    void testGenerateCorrectlyYAMLWithStylish() throws Exception {
        String differResult = Differ.generate(PATH_TO_FIXTURES + "file1.yml", PATH_TO_FIXTURES + "file2.yml",
                "stylish");
        assertEquals(correctResultStylish, differResult);
    }

    @Test
    @DisplayName("'generate' method for YAML works correctly with plain format")
    void testGenerateCorrectlyYAMLWithPlain() throws Exception {
        String differResult = Differ.generate(PATH_TO_FIXTURES + "file1.yml", PATH_TO_FIXTURES + "file2.yml",
                "plain");
        assertEquals(correctResultPlain, differResult);
    }

    @Test
    @DisplayName("'generate' method for YAML works correctly with JSON format")
    void testGenerateCorrectlyYAMLWithJSON() throws Exception {
        String differResult = Differ.generate(PATH_TO_FIXTURES + "file1.yml", PATH_TO_FIXTURES + "file2.yml",
                "json");
        assertEquals(correctResultJSON, differResult);
    }
}
