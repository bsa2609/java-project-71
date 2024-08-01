package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DifferTest {
    private static String correctResultJSON;
    private static String correctResultPlain;
    private static String correctResultStylish;

    @BeforeAll
    public static void beforeAll() throws Exception {
        correctResultJSON = FileUtils.readResourceFile("correct_result_json.json");
        correctResultPlain = FileUtils.readResourceFile("correct_result_plain.txt");
        correctResultStylish = FileUtils.readResourceFile("correct_result_stylish.txt");
    }

    @Test
    @DisplayName("'generate' method for JSON works correctly with stylish format")
    void testGenerateCorrectlyJSONWithStylish() throws Exception {
        String differResult = Differ.generate("./src/test/resources/file1.json", "./src/test/resources/file2.json",
                "stylish");
        assertEquals(correctResultStylish, differResult);
    }

    @Test
    @DisplayName("'generate' method for JSON works correctly with plain format")
    void testGenerateCorrectlyJSONWithPlain() throws Exception {
        String differResult = Differ.generate("./src/test/resources/file1.json", "./src/test/resources/file2.json",
                "plain");
        assertEquals(correctResultPlain, differResult);
    }

    @Test
    @DisplayName("'generate' method for JSON works correctly with JSON format")
    void testGenerateCorrectlyJSONWithJSON() throws Exception {
        String differResult = Differ.generate("./src/test/resources/file1.json", "./src/test/resources/file2.json",
                "json");
        assertEquals(correctResultJSON, differResult);
    }

    @Test
    @DisplayName("'generate' method for JSON works correctly without format")
    void testGenerateCorrectlyJSON() throws Exception {
        String differResult = Differ.generate("./src/test/resources/file1.json", "./src/test/resources/file2.json");
        assertEquals(correctResultStylish, differResult);
    }
    @Test
    @DisplayName("'generate' method for JSON throws an exception when reading the first file")
    void testGenerateFirstFileDoesNotExistJSON() throws Exception {
        assertThrows(Exception.class, () -> {
            String differResult = Differ.generate("./src/test/resources/file3.json", "./src/test/resources/file2.json",
                    "stylish");
        });
    }

    @Test
    @DisplayName("'generate' method for JSON throws an exception when reading the second file")
    void testGenerateSecondFileDoesNotExistJSON() throws Exception {
        assertThrows(Exception.class, () -> {
            String differResult = Differ.generate("./src/test/resources/file1.json", "./src/test/resources/file3.json",
                    "stylish");
        });
    }

    @Test
    @DisplayName("'generate' method for YAML works correctly with stylish format")
    void testGenerateCorrectlyYAMLWithStylish() throws Exception {
        String differResult = Differ.generate("./src/test/resources/file1.yml", "./src/test/resources/file2.yml",
                "stylish");
        assertEquals(correctResultStylish, differResult);
    }

    @Test
    @DisplayName("'generate' method for YAML works correctly with plain format")
    void testGenerateCorrectlyYAMLWithPlain() throws Exception {
        String differResult = Differ.generate("./src/test/resources/file1.yml", "./src/test/resources/file2.yml",
                "plain");
        assertEquals(correctResultPlain, differResult);
    }

    @Test
    @DisplayName("'generate' method for YAML works correctly with JSON format")
    void testGenerateCorrectlyYAMLWithJSON() throws Exception {
        String differResult = Differ.generate("./src/test/resources/file1.yml", "./src/test/resources/file2.yml",
                "json");
        assertEquals(correctResultJSON, differResult);
    }

    @Test
    @DisplayName("'generate' method for YAML works correctly without format")
    void testGenerateCorrectlyYAML() throws Exception {
        String differResult = Differ.generate("./src/test/resources/file1.yml", "./src/test/resources/file2.yml");
        assertEquals(correctResultStylish, differResult);
    }

    @Test
    @DisplayName("'generate' method for YAML throws an exception when reading the first file")
    void testGenerateFirstFileDoesNotExistYAML() throws Exception {
        assertThrows(Exception.class, () -> {
            String differResult = Differ.generate("./src/test/resources/file3.yml", "./src/test/resources/file2.yml",
                    "stylish");
        });
    }

    @Test
    @DisplayName("'generate' method for YAML throws an exception when reading the second file")
    void testGenerateSecondFileDoesNotExistYAML() throws Exception {
        assertThrows(Exception.class, () -> {
            String differResult = Differ.generate("./src/test/resources/file1.yml", "./src/test/resources/file3.yml",
                    "stylish");
        });
    }
}
