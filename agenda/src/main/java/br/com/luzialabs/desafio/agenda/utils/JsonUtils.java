package br.com.luzialabs.desafio.agenda.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONObject;
import org.json.XML;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public final class JsonUtils {
	
    private static final Gson gson = new Gson();
	    
    private JsonUtils() {
        throw new IllegalStateException("Utility class");
    }	

	public static String parseToJsonString(Object object) {
	    return gson.toJson(object);
	}

	public static Gson getGson() {
	    return gson;
	}
	  
	public static Object parseJsonStringToJavaObject(String json, Class<?> clazz) {
	    return gson.fromJson(json, clazz);
	}
	  
	public static String mapToJson(Map<String, ?> map) {
	    return gson.toJson(map);
	}
	  
	@SuppressWarnings("deprecation")
    public static Object addRootName(String rootName, String jsonString) {
	    JsonElement je = new JsonParser().parse(jsonString);
	    JsonObject jo = new JsonObject();
	    jo.add(rootName, je);
	    return new JsonParser().parse(jo.toString());
	}
	  
	public static String jsonToXML(String jsonString) {
	    JSONObject json = new JSONObject(jsonString);
		return XML.toString(json);
	}

	public static Map<Integer, Object> getFieldsWithTypeValueString(Map<Integer, String[]> typeField) {
		
	    Map<Integer, Object> fieldTypeValue = new HashMap<>();

        if (typeField != null && !typeField.isEmpty()) {
            for (Entry<Integer, String[]> entry : typeField.entrySet()) {

                Integer key = entry.getKey();
                String[] value = entry.getValue();
                String fieldType = value[0];
                String fieldValue = value[1];
                Object fieldInJsonFormat = JsonUtils.addRootName(fieldType, fieldValue);
                fieldTypeValue.put(key, fieldInJsonFormat);

            }
        }
		return fieldTypeValue;
	}

}
