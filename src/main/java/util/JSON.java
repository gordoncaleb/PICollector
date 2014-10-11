package util;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class JSON {

	public static String toJSON(Object obj) throws JsonGenerationException, JsonMappingException, IOException {
		return new ObjectMapper().writeValueAsString(obj);
	}

	public static <T> T fromJSON(String json, Class<T> c) throws JsonParseException, JsonMappingException, IOException {
		return new ObjectMapper().readValue(json, c);
	}

	public static Map<String, Object> getStringObjectMap(String json) throws JsonParseException, JsonMappingException, IOException {
		return new ObjectMapper().readValue(json, new TypeReference<Map<String, Object>>() {
		});
	}

	public static Object getElement(Map<String, Object> map, String... path) throws Exception {
		return getElement(map, 0, path);
	}

	private static Object getElement(Map<String, Object> map, int key, String... path) throws Exception {

		Object obj = map.get(path[key]);

		if (obj == null) {
			throw new Exception("Could not find path element: " + path[key] + " in " + Arrays.toString(path) + " in map:" + map.toString());
		}

		if (key < (path.length - 1)) {

			if (obj instanceof Map) {
				return getElement((Map) obj, ++key, path);
			} else {
				throw new Exception("Invalid path");
			}

		} else {
			return obj;
		}
	}
}
