package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AppTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private static final String PATH_TO_FIXTURES = "./src/test/resources/";

    public static String readFixture(String fileName) throws Exception {
        Path path = Paths.get(PATH_TO_FIXTURES + fileName).toAbsolutePath().normalize();

        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }

        return Files.readString(path).trim();
    }

    /**
     * Execute before all test.
     */
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(output));
    }

    @Test
    @DisplayName("'main' method for JSON works correctly with default format")
    void testMainJSONWithDefault() throws Exception {
        String[] args = {PATH_TO_FIXTURES + "file1.json", PATH_TO_FIXTURES + "file2.json"};
        App.main(args);
        String correctResult = readFixture("correct_result_stylish.txt");
        assertEquals(correctResult, output.toString(StandardCharsets.UTF_8).trim());
    }

    @Test
    @DisplayName("'main' method for JSON works correctly with stylish format")
    void testMainJSONWithStylish() throws Exception {
        String[] args = {"-f=stylish", PATH_TO_FIXTURES + "file1.json", PATH_TO_FIXTURES + "file2.json"};
        App.main(args);
        String correctResult = readFixture("correct_result_stylish.txt");
        assertEquals(correctResult, output.toString(StandardCharsets.UTF_8).trim());
    }

    @Test
    @DisplayName("'main' method for JSON works correctly with plain format")
    void testMainJSONWithPlain() throws Exception {
        String[] args = {"-f=plain", PATH_TO_FIXTURES + "file1.json", PATH_TO_FIXTURES + "file2.json"};
        App.main(args);
        String correctResult = readFixture("correct_result_plain.txt");
        assertEquals(correctResult, output.toString(StandardCharsets.UTF_8).trim());
    }

    @Test
    @DisplayName("'main' method for JSON works correctly with JSON format")
    void testMainJSONWithJson() throws Exception {
        String[] args = {"-f=json", PATH_TO_FIXTURES + "file1.json", PATH_TO_FIXTURES + "file2.json"};
        App.main(args);
        String correctResult = readFixture("correct_result_json.json");
        assertEquals(correctResult, output.toString(StandardCharsets.UTF_8).trim());
    }

    @Test
    @DisplayName("'main' method for YAML works correctly with default format")
    void testMainYAMLWithDefault() throws Exception {
        String[] args = {PATH_TO_FIXTURES + "file1.yml", PATH_TO_FIXTURES + "file2.yml"};
        App.main(args);
        String correctResult = readFixture("correct_result_stylish.txt");
        assertEquals(correctResult, output.toString(StandardCharsets.UTF_8).trim());
    }

    @Test
    @DisplayName("'main' method for YAML works correctly with stylish format")
    void testMainYAMLWithStylish() throws Exception {
        String[] args = {"-f=stylish", PATH_TO_FIXTURES + "file1.yml", PATH_TO_FIXTURES + "file2.yml"};
        App.main(args);
        String correctResult = readFixture("correct_result_stylish.txt");
        assertEquals(correctResult, output.toString(StandardCharsets.UTF_8).trim());
    }

    @Test
    @DisplayName("'main' method for YAML works correctly with plain format")
    void testMainYAMLWithPlain() throws Exception {
        String[] args = {"-f=plain", PATH_TO_FIXTURES + "file1.yml", PATH_TO_FIXTURES + "file2.yml"};
        App.main(args);
        String correctResult = readFixture("correct_result_plain.txt");
        assertEquals(correctResult, output.toString(StandardCharsets.UTF_8).trim());
    }

    @Test
    @DisplayName("'main' method for YAML works correctly with JSON format")
    void testMainYAMLWithJson() throws Exception {
        String[] args = {"-f=json", PATH_TO_FIXTURES + "file1.yml", PATH_TO_FIXTURES + "file2.yml"};
        App.main(args);
        String correctResult = readFixture("correct_result_json.json");
        assertEquals(correctResult, output.toString(StandardCharsets.UTF_8).trim());
    }

    /**
     * Execute after all test.
     */
    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
