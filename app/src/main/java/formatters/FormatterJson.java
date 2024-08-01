package formatters;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;

public class FormatterJson {
    public static String format(List<HashMap<String, Object>> dif) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(dif);
    }
}
