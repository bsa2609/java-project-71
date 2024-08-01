package hexlet.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Formatter {
    private static String spaceIndentation(int count) {
        return " ".repeat(count);
    }

    private static String formatStylish(List<HashMap<String, Object>> dif) {
        return dif.stream()
                .flatMap(keyInfo -> {
                    String key = String.valueOf(keyInfo.get("key"));
                    String status = String.valueOf(keyInfo.get("status"));
                    String value1 = String.valueOf(keyInfo.get("value1"));
                    String value2 = String.valueOf(keyInfo.get("value2"));

                    ArrayList<String> differResult = new ArrayList<>();

                    switch (status) {
                        case "add":
                            differResult.add(spaceIndentation(2) + "+ " + key + ": " + value2);
                            break;
                        case "delete":
                            differResult.add(spaceIndentation(2) + "- " + key + ": " + value1);
                            break;
                        case "change":
                            differResult.add(spaceIndentation(2) + "- " + key + ": " + value1);
                            differResult.add(spaceIndentation(2) + "+ " + key + ": " + value2);
                            break;
                        default:
                            differResult.add(spaceIndentation(4) + key + ": " + value2);
                    }

                    return differResult.stream();
                })
                .collect(Collectors.joining("\n", "{\n", "\n}"));
    }

    private static String formatPlain(List<HashMap<String, Object>> dif) {
        return "";
    }

    public static String format(List<HashMap<String, Object>> dif, String format) throws Exception {
        switch (format) {
            case "stylish":
                return formatStylish(dif);
            case "plain":
                return formatPlain(dif);
            default:
                throw new Exception("The '" + format + "' format is not supported");
        }
    }
}
