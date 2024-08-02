package hexlet.code;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Differ {
    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {
        String textFile1 = FileUtils.readFile(filePath1);
        String extensionFile1 = FileUtils.determineExtensionFromFilePath(filePath1);
        Map<String, Object> dataFile1 = Parser.parseString(textFile1, extensionFile1);

        String textFile2 = FileUtils.readFile(filePath2);
        String extensionFile2 = FileUtils.determineExtensionFromFilePath(filePath2);
        Map<String, Object> dataFile2 = Parser.parseString(textFile2, extensionFile2);

        List<HashMap<String, Object>> dif = Comparator.compare(dataFile1, dataFile2);

        return Formatter.format(dif, formatName);
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }
}
