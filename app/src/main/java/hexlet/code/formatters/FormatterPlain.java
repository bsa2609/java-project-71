package hexlet.code.formatters;

import java.util.HashMap;
import java.util.List;

public class FormatterPlain {
    private static String getRepresentation(Object value) {
        String value1Representation;

        if (value instanceof String) {
            value1Representation = "'" + value + "'";
        } else if (value == null
                || value.getClass().isPrimitive()
                || value.getClass().getPackageName().equals("java.lang")) {
            value1Representation = String.valueOf(value);
        } else {
            value1Representation = "[complex value]";
        }

        return value1Representation;
    }

    private static String buildFormatString(String key, String action, String value1, String value2) {
        StringBuilder formatStringBuilder = new StringBuilder("Property '");
        formatStringBuilder.append(key);
        formatStringBuilder.append("' ");
        formatStringBuilder.append(action);

        if (value1 != null) {
            formatStringBuilder.append(value1);
        }

        if (value2 != null) {
            formatStringBuilder.append(" to ");
            formatStringBuilder.append(value2);
        }

        return formatStringBuilder.toString();
    }

    private static String buildFormatString(String key, String action) {
        return buildFormatString(key, action, null, null);
    }

    private static String buildFormatString(String key, String action, String value1) {
        return buildFormatString(key, action, value1, null);
    }

    public static String format(List<HashMap<String, Object>> dif) throws RuntimeException {
        StringBuilder formatBuilder = new StringBuilder();

        dif.stream()
                .filter(keyInfo -> !keyInfo.get("type").toString().equals("notChange"))
                .forEach(keyInfo -> {
                    String type = String.valueOf(keyInfo.get("type"));

                    String key = String.valueOf(keyInfo.get("key"));
                    Object value1 = keyInfo.get("value1");
                    Object value2 = keyInfo.get("value2");

                    formatBuilder.append("\n");

                    switch (type) {
                        case "add":
                            formatBuilder.append(buildFormatString(key, "was added with value: ",
                                    getRepresentation(value2)));
                            break;
                        case "delete":
                            formatBuilder.append(buildFormatString(key, "was removed"));
                            break;
                        case "change":
                            formatBuilder.append(buildFormatString(key, "was updated. From ",
                                    getRepresentation(value1), getRepresentation(value2)));
                            break;
                        default:
                            try {
                                throw new Exception("The '" + type + "' type of change is not supported "
                                        + "for plain format");
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                    }
                });

        if (!formatBuilder.isEmpty()) {
            formatBuilder.deleteCharAt(0);
        }

        return formatBuilder.toString();
    }
}
