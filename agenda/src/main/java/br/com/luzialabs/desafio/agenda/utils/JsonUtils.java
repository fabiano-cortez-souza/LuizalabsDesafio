package br.com.luzialabs.desafio.agenda.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.json.XML;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.com.luzialabs.desafio.agenda.vo.OCSMember;

public final class JsonUtils {
	
    private static final Gson gson = new Gson();
	    
	private JsonUtils() {}

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

	public static HashMap<String, Object> getFieldsWithTypeValueString(HashMap<String, String[]> typeField) {
		
	    HashMap<String, Object> fieldTypeValue = new HashMap<String, Object>();
		
		if(typeField != null && !typeField.isEmpty()) {
		    for(String fieldName : typeField.keySet()) {
	            if(typeField.size() > 0) {
	                String fieldType = typeField.get(fieldName)[0];
	                String fieldValue = typeField.get(fieldName)[1];
	                Object fieldInJsonFormat = JsonUtils.addRootName(fieldType,fieldValue);
	                fieldTypeValue.put(fieldName, fieldInJsonFormat);
	            }

	        }
		}
		return fieldTypeValue;
	}

	public static List<OCSMember> createMembersFromHashMap(HashMap<String, Object> fieldsWithTypeValueString) {
		List<OCSMember> ocsMembers = new ArrayList<OCSMember>();
		
		for(String fieldName : fieldsWithTypeValueString.keySet()) {
			
		    OCSMember member = new OCSMember();
			member.setName(fieldName);
			member.setValue(fieldsWithTypeValueString.get(fieldName));
			ocsMembers.add(member);
		}
		
		return ocsMembers;
	}
}
