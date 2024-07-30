package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Differ {
    public static Map<String, Object> parseJsonString(String content) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(content, new TypeReference<Map<String, Object>>() { });
    }

    public static String readFileToString(String filePath) throws Exception {
        Path path = Paths.get(filePath).toAbsolutePath().normalize();

        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }

        return Files.readString(path).trim();
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        Map<String, Object> dataFile1 = parseJsonString(readFileToString(filePath1));
        Map<String, Object> dataFile2 = parseJsonString(readFileToString(filePath2));

        return Stream.concat(dataFile1.keySet().stream(), dataFile2.keySet().stream()
                        .filter(Predicate.not(dataFile1::containsKey)))
            .sorted()
            .flatMap(key -> {
                ArrayList<String> differResult = new ArrayList<>();

                if (!dataFile1.containsKey(key)) {
                    differResult.add("+ " + key + ": " + dataFile2.get(key));
                } else if (!dataFile2.containsKey(key)) {
                    differResult.add("- " + key + ": " + dataFile1.get(key));
                } else {
                    var value1 = dataFile1.get(key);
                    var value2 = dataFile2.get(key);

                    if (!value1.equals(value2)) {
                        differResult.add("- " + key + ": " + value1);
                        differResult.add("+ " + key + ": " + value2);
                    } else {
                        differResult.add("  " + key + ": " + value2);
                    }
                }

                return differResult.stream();
            })
            .collect(Collectors.joining("\n  ", "{\n  ", "\n}"));
    }
}
