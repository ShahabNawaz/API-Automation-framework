package in.mai.yourproject.common.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Utility methods for loading and manipulating workflow verification payloads.
 */
public final class WorkflowPayloadHelper {

    private static final Gson GSON = new Gson();
    private static final Type MAP_TYPE = new TypeToken<Map<String, Object>>() { }.getType();

    private WorkflowPayloadHelper() {
    }

    public static Map<String, Object> loadPayload(String location) {
        try (Reader reader = openReader(location)) {
            Map<String, Object> payload = GSON.fromJson(reader, MAP_TYPE);
            return normalize(payload);
        } catch (IOException e) {
            throw new IllegalStateException("Unable to load workflow payload from " + location, e);
        }
    }

    public static Map<String, Object> deepCopy(Map<String, Object> original) {
        Map<String, Object> copy = GSON.fromJson(GSON.toJson(original), MAP_TYPE);
        return normalize(copy);
    }

    public static String removeWorkflowName(String yaml) {
        if (yaml == null || yaml.isEmpty()) {
            return yaml;
        }
        return yaml.replaceFirst("^Name:.*?(\\r?\\n)", "");
    }

    private static Reader openReader(String location) throws IOException {
        InputStream resourceStream = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(location);
        if (resourceStream != null) {
            return new InputStreamReader(resourceStream, StandardCharsets.UTF_8);
        }

        Path filePath = Paths.get(location);
        if (!Files.exists(filePath)) {
            throw new IllegalArgumentException("Payload resource not found at " + location);
        }
        return Files.newBufferedReader(filePath, StandardCharsets.UTF_8);
    }

    private static Map<String, Object> normalize(Map<String, Object> input) {
        if (input == null) {
            return new LinkedHashMap<>();
        }

        Map<String, Object> result = new LinkedHashMap<>();
        for (Map.Entry<String, Object> entry : input.entrySet()) {
            result.put(entry.getKey(), normalizeValue(entry.getValue()));
        }
        return result;
    }

    private static Object normalizeValue(Object value) {
        if (value instanceof Number) {
            return normalizeNumber((Number) value);
        }
        if (value instanceof Map<?, ?>) {
            Map<?, ?> map = (Map<?, ?>) value;
            Map<String, Object> child = new LinkedHashMap<>();
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                child.put(Objects.toString(entry.getKey()), normalizeValue(entry.getValue()));
            }
            return child;
        }
        if (value instanceof List<?>) {
            List<?> list = (List<?>) value;
            List<Object> normalized = new ArrayList<>(list.size());
            for (Object item : list) {
                normalized.add(normalizeValue(item));
            }
            return normalized;
        }
        return value;
    }

    private static Number normalizeNumber(Number number) {
        double doubleValue = number.doubleValue();
        if (Double.isFinite(doubleValue) && Math.rint(doubleValue) == doubleValue) {
            long longValue = number.longValue();
            if (longValue >= Integer.MIN_VALUE && longValue <= Integer.MAX_VALUE) {
                return (int) longValue;
            }
            return longValue;
        }
        return number;
    }
}

