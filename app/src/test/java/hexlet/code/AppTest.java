package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;
 import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AppTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(output));
    }

    @Test
    @DisplayName("'main' method for JSON works correctly with default format")
    void testMainJSONWithDefault() throws Exception {
        String[] args = {"./src/test/resources/file1.json", "./src/test/resources/file2.json"};
        App.main(args);
        String correctResult = FileUtils.readResourceFile("correct_result_stylish.txt");
        assertEquals(correctResult, output.toString(StandardCharsets.UTF_8).trim());
    }

    @Test
    @DisplayName("'main' method for JSON works correctly with stylish format")
    void testMainJSONWithStylish() throws Exception {
        String[] args = {"-f=stylish", "./src/test/resources/file1.json", "./src/test/resources/file2.json"};
        App.main(args);
        String correctResult = FileUtils.readResourceFile("correct_result_stylish.txt");
        assertEquals(correctResult, output.toString(StandardCharsets.UTF_8).trim());
    }

    @Test
    @DisplayName("'main' method for JSON works correctly with plain format")
    void testMainJSONWithPlain() throws Exception {
        String[] args = {"-f=plain", "./src/test/resources/file1.json", "./src/test/resources/file2.json"};
        App.main(args);
        String correctResult = FileUtils.readResourceFile("correct_result_plain.txt");
        assertEquals(correctResult, output.toString(StandardCharsets.UTF_8).trim());
    }

    @Test
    @DisplayName("'main' method for JSON works correctly with JSON format")
    void testMainJSONWithJson() throws Exception {
        String[] args = {"-f=json", "./src/test/resources/file1.json", "./src/test/resources/file2.json"};
        App.main(args);
        String correctResult = FileUtils.readResourceFile("correct_result_json.json");
        assertEquals(correctResult, output.toString(StandardCharsets.UTF_8).trim());
    }

    @Test
    @DisplayName("'main' method for JSON works correctly when first file does not exist")
    void testMainJSONFirstFileDoesNotExist() {
        String[] args = {"./src/test/resources/file3.json", "./src/test/resources/file2.json"};
        App.main(args);
        String actual = output.toString(StandardCharsets.UTF_8).trim();
        assertTrue(actual.contains("File") && actual.contains("does not exist"));
    }

    @Test
    @DisplayName("'main' method for JSON works correctly when second file does not exist")
    void testMainJSONSecondFileDoesNotExist() {
        String[] args = {"./src/test/resources/file1.json", "./src/test/resources/file3.json"};
        App.main(args);
        String actual = output.toString(StandardCharsets.UTF_8).trim();
        assertTrue(actual.contains("File") && actual.contains("does not exist"));
    }

    @Test
    @DisplayName("'main' method for YAML works correctly with default format")
    void testMainYAMLWithDefault() throws Exception {
        String[] args = {"./src/test/resources/file1.yml", "./src/test/resources/file2.yml"};
        App.main(args);
        String correctResult = FileUtils.readResourceFile("correct_result_stylish.txt");
        assertEquals(correctResult, output.toString(StandardCharsets.UTF_8).trim());
    }

    @Test
    @DisplayName("'main' method for YAML works correctly with stylish format")
    void testMainYAMLWithStylish() throws Exception {
        String[] args = {"-f=stylish", "./src/test/resources/file1.yml", "./src/test/resources/file2.yml"};
        App.main(args);
        String correctResult = FileUtils.readResourceFile("correct_result_stylish.txt");
        assertEquals(correctResult, output.toString(StandardCharsets.UTF_8).trim());
    }

    @Test
    @DisplayName("'main' method for YAML works correctly with plain format")
    void testMainYAMLWithPlain() throws Exception {
        String[] args = {"-f=plain", "./src/test/resources/file1.yml", "./src/test/resources/file2.yml"};
        App.main(args);
        String correctResult = FileUtils.readResourceFile("correct_result_plain.txt");
        assertEquals(correctResult, output.toString(StandardCharsets.UTF_8).trim());
    }

    @Test
    @DisplayName("'main' method for YAML works correctly with JSON format")
    void testMainYAMLWithJson() throws Exception {
        String[] args = {"-f=json", "./src/test/resources/file1.yml", "./src/test/resources/file2.yml"};
        App.main(args);
        String correctResult = FileUtils.readResourceFile("correct_result_json.json");
        assertEquals(correctResult, output.toString(StandardCharsets.UTF_8).trim());
    }

    @Test
    @DisplayName("'main' method for YAML works correctly when first file does not exist")
    void testMainYAMLFirstFileDoesNotExist() {
        String[] args = {"./src/test/resources/file3.yml", "./src/test/resources/file2.yml"};
        App.main(args);
        String actual = output.toString(StandardCharsets.UTF_8).trim();
        assertTrue(actual.contains("File") && actual.contains("does not exist"));
    }

    @Test
    @DisplayName("'main' method for YAML works correctly when second file does not exist")
    void testMainYAMLSecondFileDoesNotExist() {
        String[] args = {"./src/test/resources/file1.yml", "./src/test/resources/file3.yml"};
        App.main(args);
        String actual = output.toString(StandardCharsets.UTF_8).trim();
        assertTrue(actual.contains("File") && actual.contains("does not exist"));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
