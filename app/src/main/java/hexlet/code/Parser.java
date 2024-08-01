package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

public class Parser {
    public static Map<String, Object> parseStringUsingObjectMapper(String content, ObjectMapper mapper)
            throws Exception {
        return mapper.readValue(content, new TypeReference<Map<String, Object>>() { });
    }

    public static Map<String, Object> parseString(String content, String extension) throws Exception {
        switch (extension) {
            case ".json":
                return parseStringUsingObjectMapper(content, new ObjectMapper());
            case ".yml":
                return parseStringUsingObjectMapper(content, new YAMLMapper());
            default:
                throw new Exception("The '" + extension + "' file extension is not supported");
        }
    }
}
