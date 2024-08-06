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

    public static String format(List<HashMap<String, Object>> dif) throws Exception {
        StringBuilder formatBuilder = new StringBuilder();

        for (HashMap<String, Object> keyInfo:dif) {
            String type = String.valueOf(keyInfo.get("type"));

            if (type.equals("notChange")) {
                continue;
            }

            String key = String.valueOf(keyInfo.get("key"));
            Object value1 = keyInfo.get("value1");
            Object value2 = keyInfo.get("value2");

            if (!formatBuilder.isEmpty()) {
                formatBuilder.append("\n");
            }

            switch (type) {
                case "add":
                    formatBuilder.append("Property '");
                    formatBuilder.append(key);
                    formatBuilder.append("' was added with value: ");
                    formatBuilder.append(getRepresentation(value2));
                    break;

                case "delete":
                    formatBuilder.append("Property '");
                    formatBuilder.append(key);
                    formatBuilder.append("' was removed");
                    break;

                case "change":
                    formatBuilder.append("Property '");
                    formatBuilder.append(key);
                    formatBuilder.append("' was updated. From ");
                    formatBuilder.append(getRepresentation(value1));
                    formatBuilder.append(" to ");
                    formatBuilder.append(getRepresentation(value2));
                    break;

                default:
                    throw new Exception("The '" + type + "' type of change is not supported for plain format");
            }
        }

        return formatBuilder.toString();
    }
}
