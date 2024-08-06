package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Differ {
    private static String readFile(String filePath) throws Exception {
        Path path = Paths.get(filePath).toAbsolutePath().normalize();

        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }

        return Files.readString(path).trim();
    }

    public static String determineExtensionFromFilePath(String filePath) {
        int lastIndexOfPoint = filePath.lastIndexOf('.');
        return filePath.substring(lastIndexOfPoint).trim().toLowerCase();
    }

    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {
        String textFile1 = readFile(filePath1);
        String extensionFile1 = determineExtensionFromFilePath(filePath1);
        Map<String, Object> dataFile1 = Parser.parseString(textFile1, extensionFile1);

        String textFile2 = readFile(filePath2);
        String extensionFile2 = determineExtensionFromFilePath(filePath2);
        Map<String, Object> dataFile2 = Parser.parseString(textFile2, extensionFile2);

        List<HashMap<String, Object>> dif = Comparator.compare(dataFile1, dataFile2);

        return Formatter.format(dif, formatName);
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }
}
