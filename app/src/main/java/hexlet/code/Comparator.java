package hexlet.code;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Comparator {
    public static List<HashMap<String, Object>> compare(Map<String, Object> dataFile1, Map<String, Object> dataFile2) {
        return Stream.concat(dataFile1.keySet().stream(), dataFile2.keySet().stream()
                        .filter(Predicate.not(dataFile1::containsKey)))
                .sorted()
                .map(key -> {
                    HashMap<String, Object> keyInfo = new HashMap<>();

                    var value1 = dataFile1.getOrDefault(key, null);
                    var value2 = dataFile2.getOrDefault(key, null);

                    keyInfo.put("key", key);
                    keyInfo.put("value1", value1);
                    keyInfo.put("value2", value2);

                    if (!dataFile1.containsKey(key)) {
                        keyInfo.put("status", "add");
                    } else if (!dataFile2.containsKey(key)) {
                        keyInfo.put("status", "delete");
                    } else {
                        if ((value1 == null && value2 != null)
                                || (value1 != null && value2 == null)
                                || !value1.equals(value2)) {
                            keyInfo.put("status", "change");
                        } else {
                            keyInfo.put("status", "notChange");
                        }
                    }

                    return keyInfo;
                })
                .toList();
    }
}
