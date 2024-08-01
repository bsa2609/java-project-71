package hexlet.code;

import java.util.Map;

public class Differ {
    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        Map<String, Object> dataFile1 = Parser.parseString(FileUtils.readFile(filePath1),
                FileUtils.determineExtensionFromFilePath(filePath1));

        Map<String, Object> dataFile2 = Parser.parseString(FileUtils.readFile(filePath2),
                FileUtils.determineExtensionFromFilePath(filePath2));

        return Formatter.format(Comparator.compare(dataFile1, dataFile2), format);
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }
}
