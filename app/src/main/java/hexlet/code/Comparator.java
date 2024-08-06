package hexlet.code;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Comparator {
    private static String determineTypeOfChangeAddOrDelete(boolean containsKey1) {
        String typeOfChange;

        if (containsKey1) {
            typeOfChange = "delete";
        } else {
            typeOfChange = "add";
        }

        return typeOfChange;
    }

    private static String determineTypeOfChangeChangeOrNotChange(Object value1, Object value2) {
        String typeOfChange;

        if (value1 == null && value2 == null
                || value1 != null && value1.equals(value2)) {
            typeOfChange = "notChange";
        } else {
            typeOfChange = "change";
        }

        return typeOfChange;
    }

    private static String determineTypeOfChange(boolean containsKey1, Object value1,
                                                boolean containsKey2, Object value2) {
        String typeOfChange;

        if (containsKey1 && containsKey2) {
            typeOfChange = determineTypeOfChangeChangeOrNotChange(value1, value2);
        } else {
            typeOfChange = determineTypeOfChangeAddOrDelete(containsKey1);
        }

        return typeOfChange;
    }

    public static List<HashMap<String, Object>> compare(Map<String, Object> dataFile1, Map<String, Object> dataFile2) {
        Stream<String> keysDataFile1 = dataFile1.keySet().stream();

        Stream<String> keysDataFile2NotInKeysDataFile1 = dataFile2.keySet().stream()
                .filter(Predicate.not(dataFile1::containsKey));

        return Stream.concat(keysDataFile1, keysDataFile2NotInKeysDataFile1)
                .sorted()
                .map(key -> {
                    var value1 = dataFile1.getOrDefault(key, null);
                    var value2 = dataFile2.getOrDefault(key, null);

                    String typeOfChange = determineTypeOfChange(
                            dataFile1.containsKey(key), value1,
                            dataFile2.containsKey(key), value2);

                    HashMap<String, Object> keyInfo = new HashMap<>();

                    keyInfo.put("key", key);
                    keyInfo.put("value1", value1);
                    keyInfo.put("value2", value2);
                    keyInfo.put("type", typeOfChange);

                    return keyInfo;
                })
                .toList();
    }
}
