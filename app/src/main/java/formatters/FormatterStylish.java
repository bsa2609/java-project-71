package formatters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class FormatterStylish {
    private static final int TWO_SPACES = 2;
    private static final int FOR_SPACES = 4;

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
                            differResult.add(spaceIndentation(TWO_SPACES) + "+ " + key + ": " + value2);
                            break;
                        case "delete":
                            differResult.add(spaceIndentation(TWO_SPACES) + "- " + key + ": " + value1);
                            break;
                        case "change":
                            differResult.add(spaceIndentation(TWO_SPACES) + "- " + key + ": " + value1);
                            differResult.add(spaceIndentation(TWO_SPACES) + "+ " + key + ": " + value2);
                            break;
                        case "notChange":
                            differResult.add(spaceIndentation(FOR_SPACES) + key + ": " + value2);
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
