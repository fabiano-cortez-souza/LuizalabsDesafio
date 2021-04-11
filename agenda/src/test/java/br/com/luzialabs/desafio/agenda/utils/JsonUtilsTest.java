package br.com.luzialabs.desafio.agenda.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import br.com.luzialabs.desafio.agenda.enums.ComunicacaoTipoEnum;
import br.com.luzialabs.desafio.agenda.enums.StatusEnvioEnum;
import br.com.luzialabs.desafio.agenda.vo.AgendaVO;

class JsonUtilsTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtilsTest.class);
    private static final Gson gson = new Gson();
    
	@Test
	void validMapShouldReturnValidJsonString() {
		Map<String, String> validMap = new HashMap<String, String>();
		validMap.put("field1", "value");
		validMap.put("field2", "value");
		
		String json = JsonUtils.mapToJson(validMap);

		assertEquals("{\"field1\":\"value\",\"field2\":\"value\"}", json);
	}
	
	@Test
	void validJsonStringShouldReturnValidJavaObject() {
	    String validJsonString = new AgendaVO(10,
		                                 "20210701T12:34:00-0300",
										 "a@a.com",
										 "teste",  
										 ComunicacaoTipoEnum.WHATSAPP.getDesc(),
	                                     StatusEnvioEnum.PENDENTE.getDesc()).toString();   
		
        String jsonString = String.valueOf(JsonUtils.parseJsonStringToJavaObject(validJsonString, AgendaVO.class));

		assertEquals(validJsonString, jsonString);
	}
	
	@Test
	void shouldReturnJsonWithRootname() {
		Map<String, String> children = new HashMap<String, String>();
		children.put("field1", "value");
		children.put("field2", "value");
		children.put("field3", "20201001T17:20:50-0300");
		
		Map<String, Object> fatherWithChildren = new HashMap<String, Object>();
		fatherWithChildren.put("father", children);
		String json = JsonUtils.mapToJson(fatherWithChildren);
		LOGGER.info("teste = {}", json);
		//{1={"teste":"teste1"}, 2={"teste":"teste1"}}
		assertEquals("{\"father\":{\"field1\":\"value\",\"field3\":\"20201001T17:20:50-0300\",\"field2\":\"value\"}}", json);

	}
	
	@Test
	void shouldReturnXMLString() {
		Map<String, String> children = new HashMap<String, String>();
		children.put("field1", "value");
		children.put("field2", "value");
		
		Map<String, Object> fatherWithChildren = new HashMap<String, Object>();
		fatherWithChildren.put("father", children);
		String json = JsonUtils.mapToJson(fatherWithChildren);
		String xml = JsonUtils.jsonToXML(json);
		LOGGER.info("XML: {}", xml);
	}
	
	@Test
	void shouldPassParseToJsonString() {
	    Object object = new Object();
	    
	    String retorno = JsonUtils.parseToJsonString(object);
	    assertEquals("{}", retorno);
	}
	
	@Test
    void shouldPassGetGsonNotNull() {
        Gson retorno = JsonUtils.getGson();
        assertNotNull(retorno);
    }
    
    @Test
    void shouldPassAddRootName() {
        Object retorno = JsonUtils.addRootName("teste", "teste");
        assertEquals("{\"teste\":\"teste\"}", retorno.toString());
    }
    
    @Test
    void shouldPassGetFieldsWithTypeValueString() {
        String[] teste = {"teste", "teste1"};
        //String[] teste1 = {"datetime.iso", "20201001T17:20500300"};
        HashMap<String, String[]> hashMap = new HashMap<>();
        hashMap.put("1", teste);
        hashMap.put("2", teste);
        //hashMap.put("3", teste1);
        
        HashMap<String, Object> retorno = JsonUtils.getFieldsWithTypeValueString(hashMap);
        assertEquals("{1={\"teste\":\"teste1\"}, 2={\"teste\":\"teste1\"}}",  retorno.toString());
    }
    
    
    @Test
    void shouldPassGetFieldsWithTypeValueStringWithOneField() {
        String[] teste = {"teste", "teste1"};
        HashMap<String, String[]> hashMap = new HashMap<>();
        hashMap.put("1", teste);
        
        HashMap<String, Object> retorno = JsonUtils.getFieldsWithTypeValueString(hashMap);
        assertEquals("{1={\"teste\":\"teste1\"}}", retorno.toString());
    }
    
}