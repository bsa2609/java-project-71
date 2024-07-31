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
    @DisplayName("'main' method works correctly")
    void testMain() {
        String[] args = {"./src/test/resources/file1.json", "./src/test/resources/file2.json"};
        App.main(args);
        String correctResult = "{\n"
            + "  - follow: false\n"
            + "    host: hexlet.io\n"
            + "  - proxy: 123.234.53.22\n"
            + "  - timeout: 50\n"
            + "  + timeout: 20\n"
            + "  + verbose: true\n"
            + "}";
        assertEquals(correctResult, output.toString(StandardCharsets.UTF_8).trim());
    }

    @Test
    @DisplayName("'main' method works correctly when some file does not exist")
    void testMainFileDoesNotExist() {
        String[] args = {"./src/test/resources/file1.json", "./src/test/resources/file3.json"};
        App.main(args);
        String actual = output.toString(StandardCharsets.UTF_8).trim();
        assertTrue(actual.contains("File") && actual.contains("does not exist"));
    }
    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
