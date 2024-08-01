package formatters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class FormatterPlain {
    private static String getRepresentation(Object value) {
        String value1Representation;

        if (value == null
                || value.getClass().isPrimitive()
                || value instanceof Boolean
                || value instanceof Byte
                || value instanceof Short
                || value instanceof Integer
                || value instanceof Long
                || value instanceof Float
                || value instanceof Double
                || value instanceof Character) {
            value1Representation = String.valueOf(value);
        } else if (value instanceof String) {
            value1Representation = "'" + value + "'";
        } else {
            value1Representation = "[complex value]";
        }

        return value1Representation;
    }

    public static String format(List<HashMap<String, Object>> dif) {
        return dif.stream()
                .filter(keyInfo -> !keyInfo.get("status").toString().equals("notChange"))
                .flatMap(keyInfo -> {
                    String key = String.valueOf(keyInfo.get("key"));
                    String status = String.valueOf(keyInfo.get("status"));
                    Object value1 = keyInfo.get("value1");
                    Object value2 = keyInfo.get("value2");

                    String value1Representation = getRepresentation(value1);
                    String value2Representation = getRepresentation(value2);

                    ArrayList<String> differResult = new ArrayList<>();

                    switch (status) {
                        case "add":
                            differResult.add("Property '" + key + "' was added with value: " + value2Representation);
                            break;
                        case "delete":
                            differResult.add("Property '" + key + "' was removed");
                            break;
                        default:
                            differResult.add("Property '" + key + "' was updated. From "
                                    + value1Representation + " to " + value2Representation);
                    }

                    return differResult.stream();
                })
                .collect(Collectors.joining("\n"));
    }
}
