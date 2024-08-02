package formatters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class FormatterPlain {
    private static String getRepresentation(Object value) {
        String value1Representation;

        if (value == null) {
            value1Representation = String.valueOf(value);
        } else if (value instanceof String) {
            value1Representation = "'" + value + "'";
        } else if (value.getClass().isPrimitive()
                || value.getClass().getPackageName().equals("java.lang")) {
            value1Representation = String.valueOf(value);
        } else {
            value1Representation = "[complex value]";
        }

        return value1Representation;
    }

    public static String format(List<HashMap<String, Object>> dif) throws RuntimeException {
        return dif.stream()
                .filter(keyInfo -> !keyInfo.get("type").toString().equals("notChange"))
                .flatMap(keyInfo -> {
                    String key = String.valueOf(keyInfo.get("key"));
                    String type = String.valueOf(keyInfo.get("type"));
                    Object value1 = keyInfo.get("value1");
                    Object value2 = keyInfo.get("value2");

                    String value1Representation = getRepresentation(value1);
                    String value2Representation = getRepresentation(value2);

                    ArrayList<String> differResult = new ArrayList<>();

                    switch (type) {
                        case "add":
                            differResult.add("Property '" + key + "' was added with value: " + value2Representation);
                            break;
                        case "delete":
                            differResult.add("Property '" + key + "' was removed");
                            break;
                        case "change":
                            differResult.add("Property '" + key + "' was updated. From "
                                   + value1Representation + " to " + value2Representation);
                            break;
                        default:
                            try {
                                throw new Exception("The '" + type + "' type of change is not supported "
                                        + "for plain format");
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                    }

                    return differResult.stream();
                })
                .collect(Collectors.joining("\n"));
    }
}
