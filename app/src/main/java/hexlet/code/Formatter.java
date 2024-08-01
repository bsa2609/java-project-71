package hexlet.code;

import formatters.FormatterJson;
import formatters.FormatterPlain;
import formatters.FormatterStylish;

import java.util.HashMap;
import java.util.List;

public class Formatter {
    public static String format(List<HashMap<String, Object>> dif, String formatName) throws Exception {
        switch (formatName) {
            case "stylish":
                return FormatterStylish.format(dif);
            case "plain":
                return FormatterPlain.format(dif);
            case "json":
                return FormatterJson.format(dif);
            default:
                throw new Exception("The '" + formatName + "' format is not supported");
        }
    }
}
