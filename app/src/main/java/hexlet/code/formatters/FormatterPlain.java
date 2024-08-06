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

    private static StringBuilder newFormatStringBuilder(String key) {
        StringBuilder formatStringBuilder = new StringBuilder("\n");
        formatStringBuilder.append("Property '");
        formatStringBuilder.append(key);
        formatStringBuilder.append("' ");

        return formatStringBuilder;
    }

    private static String buildFormatStringAdd(String key, Object value2) {
        StringBuilder formatStringBuilder = newFormatStringBuilder(key);
        formatStringBuilder.append("was added with value: ");
        formatStringBuilder.append(getRepresentation(value2));

        return formatStringBuilder.toString();
    }

    private static String buildFormatStringDelete(String key) {
        StringBuilder formatStringBuilder = newFormatStringBuilder(key);
        formatStringBuilder.append("was removed");

        return formatStringBuilder.toString();
    }

    private static String buildFormatStringChange(String key, Object value1, Object value2) {
        StringBuilder formatStringBuilder = newFormatStringBuilder(key);
        formatStringBuilder.append("was updated. From ");
        formatStringBuilder.append(getRepresentation(value1));
        formatStringBuilder.append(" to ");
        formatStringBuilder.append(getRepresentation(value2));

        return formatStringBuilder.toString();
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

                    switch (type) {
                        case "add":
                            formatBuilder.append(buildFormatStringAdd(key, value2));
                            break;
                        case "delete":
                            formatBuilder.append(buildFormatStringDelete(key));
                            break;
                        case "change":
                            formatBuilder.append(buildFormatStringChange(key, value1, value2));
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

        return formatBuilder.toString().trim();
    }
}
