package Utils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public final class FlatMapUtil {

    private FlatMapUtil() {
        throw new AssertionError("No instances for you!");
    }

    public static Map<String, Object> flatten(JSONObject obj) {
        Map<String, Object> map;
        map = jsonToMap(obj);
        return map.entrySet().stream()
                .flatMap(FlatMapUtil::flatten)
                .collect(LinkedHashMap::new, (m, e) -> m.put(e.getKey(), e.getValue()), LinkedHashMap::putAll);
    }

    public static Map<String, Object> jsonToMap(JSONObject json) throws JSONException {
        Map<String, Object> retMap = new HashMap<String, Object>();

        if(json != JSONObject.NULL) {
            retMap = toMap(json);
        }
        return retMap;
    }

    public static Map<String, Object> toMap(JSONObject object) throws JSONException {
        Map<String, Object> map = new HashMap<String, Object>();

        Iterator<String> keysItr = object.keys();
        while(keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);

            if(value instanceof JSONArray) {
                value = toList((JSONArray) value);
            }

            else if(value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            map.put(key, value);
        }
        return map;
    }

    public static List<Object> toList(JSONArray array) throws JSONException {
        List<Object> list = new ArrayList<>();
        for(int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            if(value instanceof JSONArray) {
                value = toList((JSONArray) value);
            }

            else if(value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            list.add(value);
        }
        return list;
    }

    private static Stream<Map.Entry<String, Object>> flatten(Map.Entry<String, Object> entry) {

        if (entry == null) {
            return Stream.empty();
        }

        if (entry.getValue() instanceof Map<?, ?>) {
            return ((Map<?, ?>) entry.getValue()).entrySet().stream()
                    .flatMap(e -> flatten(new AbstractMap.SimpleEntry<>(entry.getKey()  + " " + e.getKey(), e.getValue())));
        }

        if (entry.getValue() instanceof List<?>) {
            List<?> list = (List<?>) entry.getValue();
            return IntStream.range(0, list.size())
                    .mapToObj(i -> new AbstractMap.SimpleEntry<String, Object>(entry.getKey() + "[" + i + "]", list.get(i)))
                    .flatMap(FlatMapUtil::flatten);
        }

        return Stream.of(entry);
    }

    public static Map<String, Object> flattenKeys(JSONObject obj) {
        Map<String, Object> map;
        map = jsonToMapKeys(obj);
        return map.entrySet().stream()
                .flatMap(FlatMapUtil::flatten)
                .collect(LinkedHashMap::new, (m, e) -> m.put(e.getKey(), e.getValue()), LinkedHashMap::putAll);
    }

    public static Map<String, Object> jsonToMapKeys(JSONObject json) throws JSONException {
        Map<String, Object> retMap = new HashMap<String, Object>();

        if(json != JSONObject.NULL) {
            retMap = toMapKeys(json);
        }
        return retMap;
    }

    public static Map<String, Object> toMapKeys(JSONObject object) throws JSONException {
        Map<String, Object> map = new HashMap<String, Object>();

        Iterator<String> keysItr = object.keys();
        while(keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);

            if(value instanceof JSONArray) {
                value = toListKeys((JSONArray) value);
            }

            else if(value instanceof JSONObject) {
                value = toMapKeys((JSONObject) value);
            } else {
                value = null;
            }

            map.put(key, value);
        }
        return map;
    }

    public static List<Object> toListKeys(JSONArray array) throws JSONException {
        List<Object> list = new ArrayList<>();
        if(array.length() > 0) {
            for (int i = 0; i < 1; i++) {
                Object value = array.get(i);
                if (value instanceof JSONArray) {
                    value = toListKeys((JSONArray) value);
                } else if (value instanceof JSONObject) {
                    value = toMapKeys((JSONObject) value);
                } else {
                    value = null;
                }
                list.add(value);
            }
        }
        return list;
    }
}
