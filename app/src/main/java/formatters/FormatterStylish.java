package formatters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class FormatterStylish {
    private static String spaceIndentation(int count) {
        return " ".repeat(count);
    }

    public static String format(List<HashMap<String, Object>> dif) throws RuntimeException {
        return dif.stream()
                .flatMap(keyInfo -> {
                    String key = String.valueOf(keyInfo.get("key"));
                    String type = String.valueOf(keyInfo.get("type"));
                    String value1 = String.valueOf(keyInfo.get("value1"));
                    String value2 = String.valueOf(keyInfo.get("value2"));

                    ArrayList<String> differResult = new ArrayList<>();

                    switch (type) {
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
                        case "notChange":
                            differResult.add(spaceIndentation(4) + key + ": " + value2);
                            break;
                        default:
                            try {
                                throw new Exception("The '" + type + "' type of change is not supported "
                                        + "for stylish format");
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                    }

                    return differResult.stream();
                })
                .collect(Collectors.joining("\n", "{\n", "\n}"));
    }
}
