package hexlet.code.formatters;

import java.util.HashMap;
import java.util.List;

public class FormatterStylish {
    private static final int TWO_SPACES = 2;
    private static final int FOR_SPACES = 4;

    private static String buildFormatString(int spaceCount, String operation, String key, String value) {
        StringBuilder formatStringBuilder = new StringBuilder(" ".repeat(spaceCount));

        if (!operation.isBlank()) {
            formatStringBuilder.append(operation);
            formatStringBuilder.append(" ");
        }

        formatStringBuilder.append(key);
        formatStringBuilder.append(": ");
        formatStringBuilder.append(value);

        return formatStringBuilder.toString();
    }

    public static String format(List<HashMap<String, Object>> dif) throws RuntimeException {
        StringBuilder formatBuilder = new StringBuilder("{");

        dif.forEach(keyInfo -> {
            String key = String.valueOf(keyInfo.get("key"));
            String type = String.valueOf(keyInfo.get("type"));
            String value1 = String.valueOf(keyInfo.get("value1"));
            String value2 = String.valueOf(keyInfo.get("value2"));

            formatBuilder.append("\n");

            switch (type) {
                case "add":
                    formatBuilder.append(buildFormatString(TWO_SPACES, "+", key, value2));
                    break;
                case "delete":
                    formatBuilder.append(buildFormatString(TWO_SPACES, "-", key, value1));
                    break;
                case "change":
                    formatBuilder.append(buildFormatString(TWO_SPACES, "-", key, value1));
                    formatBuilder.append("\n");
                    formatBuilder.append(buildFormatString(TWO_SPACES, "+", key, value2));
                    break;
                case "notChange":
                    formatBuilder.append(buildFormatString(FOR_SPACES, "", key, value2));
                    break;
                default:
                    try {
                        throw new Exception("The '" + type + "' type of change is not supported for stylish format");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
            }
        });

        formatBuilder.append("\n}");

        return formatBuilder.toString();
    }
}
