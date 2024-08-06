package hexlet.code.formatters;

import java.util.HashMap;
import java.util.List;

public class FormatterStylish {
    private static final int TWO_SPACES = 2;
    private static final int FOR_SPACES = 4;

    private static String spaceIndentation(int count) {
        return " ".repeat(count);
    }

    public static String format(List<HashMap<String, Object>> dif) throws Exception {
        StringBuilder formatBuilder = new StringBuilder("{");

        for (HashMap<String, Object> keyInfo:dif) {
            String key = String.valueOf(keyInfo.get("key"));
            String type = String.valueOf(keyInfo.get("type"));
            String value1 = String.valueOf(keyInfo.get("value1"));
            String value2 = String.valueOf(keyInfo.get("value2"));

            formatBuilder.append("\n");

            switch (type) {
                case "add":
                    formatBuilder.append(spaceIndentation(TWO_SPACES));
                    formatBuilder.append("+ ");
                    formatBuilder.append(key);
                    formatBuilder.append(": ");
                    formatBuilder.append(value2);
                    break;

                case "delete":
                    formatBuilder.append(spaceIndentation(TWO_SPACES));
                    formatBuilder.append("- ");
                    formatBuilder.append(key);
                    formatBuilder.append(": ");
                    formatBuilder.append(value1);
                    break;

                case "change":
                    formatBuilder.append(spaceIndentation(TWO_SPACES));
                    formatBuilder.append("- ");
                    formatBuilder.append(key);
                    formatBuilder.append(": ");
                    formatBuilder.append(value1);
                    formatBuilder.append("\n");

                    formatBuilder.append(spaceIndentation(TWO_SPACES));
                    formatBuilder.append("+ ");
                    formatBuilder.append(key);
                    formatBuilder.append(": ");
                    formatBuilder.append(value2);
                    break;

                case "notChange":
                    formatBuilder.append(spaceIndentation(FOR_SPACES));
                    formatBuilder.append(key);
                    formatBuilder.append(": ");
                    formatBuilder.append(value2);
                    break;

                default:
                    throw new Exception("The '" + type + "' type of change is not supported for stylish format");
            }
        }

        formatBuilder.append("\n}");

        return formatBuilder.toString();
    }
}
