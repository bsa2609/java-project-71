package hexlet.code.formatters;

import java.util.HashMap;
import java.util.List;

public class FormatterStylish {
    private static final int TWO_SPACES = 2;
    private static final int FOR_SPACES = 4;

    private static String buildFormatString(String action, String key, String value) {
        StringBuilder formatStringBuilder = new StringBuilder("\n");

        if (action.isBlank()) {
            formatStringBuilder.append(" ".repeat(FOR_SPACES));
        } else {
            formatStringBuilder.append(" ".repeat(TWO_SPACES));
            formatStringBuilder.append(action);
            formatStringBuilder.append(" ");
        }

        formatStringBuilder.append(key);
        formatStringBuilder.append(": ");
        formatStringBuilder.append(value);

        return formatStringBuilder.toString();
    }

    private static void generateExceptionTypeNotSupported(String type) throws RuntimeException {
        try {
            throw new Exception("The '" + type + "' type of change is not supported for stylish format");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String format(List<HashMap<String, Object>> dif) throws RuntimeException {
        StringBuilder formatBuilder = new StringBuilder("{");

        dif.forEach(keyInfo -> {
            String key = String.valueOf(keyInfo.get("key"));
            String type = String.valueOf(keyInfo.get("type"));
            String value1 = String.valueOf(keyInfo.get("value1"));
            String value2 = String.valueOf(keyInfo.get("value2"));

            switch (type) {
                case "add":
                    formatBuilder.append(buildFormatString("+", key, value2));
                    break;
                case "delete":
                    formatBuilder.append(buildFormatString("-", key, value1));
                    break;
                case "change":
                    formatBuilder.append(buildFormatString("-", key, value1));
                    formatBuilder.append(buildFormatString("+", key, value2));
                    break;
                case "notChange":
                    formatBuilder.append(buildFormatString("", key, value2));
                    break;
                default:
                    generateExceptionTypeNotSupported(type);
            }
        });

        formatBuilder.append("\n}");

        return formatBuilder.toString();
    }
}
