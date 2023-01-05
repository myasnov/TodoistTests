package todoist.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyUtils {

    private static Properties properties = new Properties();
    private static Map<String, String> map = new HashMap<>();

    static {
        try {
            FileInputStream inputStream = new FileInputStream("src/test/resources/config.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        properties.forEach((key, value) -> map.put(String.valueOf(key), String.valueOf(value)));
    }

    public static String getValue(String key) {
        return map.get(key);
    }
}
