package br.com.luzialabs.desafio.agenda.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;

import br.com.luzialabs.desafio.agenda.enums.ComunicacaoTipoEnum;
import br.com.luzialabs.desafio.agenda.enums.StatusEnvioEnum;
import br.com.luzialabs.desafio.agenda.vo.AgendaVO;

class UtilsTest {

	@Test
	void msisdnOnlyHasNumbers() {
		assertTrue(Utils.hasOnlyNumbers("551111111111"));
	}

	@Test
	void invalidMsisdn() {
		assertFalse(Utils.hasOnlyNumbers("5511DD11111AA"));
	}
	
	@Test
	void negativeNumbersAreValid() {
		assertTrue(Utils.hasOnlyNumbers("-333"));
	}

	@Test
	void positiveNumbersAreValid() {
		assertTrue(Utils.hasOnlyNumbers("+333"));
	}
	
	@Test
	void classToJsonStringShouldReturnJson() {
	    AgendaVO agendaVO = new AgendaVO();
	    
	    agendaVO.setDataHora("20210701T12:34:00-0300");
	    agendaVO.setDestinatario("a@a.com");
	    agendaVO.setMensagem("abcdefghijklmnopqrstuvxyz1234567890");
	    agendaVO.setComunicacaoTipo(ComunicacaoTipoEnum.PUSH.getDesc());
	    agendaVO.setStatusEnvio(StatusEnvioEnum.ENVIADO.getDesc());
	    
		Gson gson = new Gson();
		
		assertEquals(gson.toJson(agendaVO),agendaVO.toString());
	}
	
	@Test
	void missingRequiredField() {
		List<String> fields = new ArrayList<>();
		assertTrue(Utils.anyRequiredFieldIsNullOrEmpty(fields));
		
		for(int i = 0; i < 9; i ++) {
			fields.add(StringUtils.repeat("A", 10));
		}
		
		fields.add("");
		assertTrue(Utils.anyRequiredFieldIsNullOrEmpty(fields));
	}
	
	@Test
	void allRequiredFieldsIsPresent() {
		List<String> fields = new ArrayList<>();
		
		for(int i = 0; i < 9; i ++) {
			fields.add(StringUtils.repeat("A", 10));
		}
		
		assertFalse(Utils.anyRequiredFieldIsNullOrEmpty(fields));
	}
	
	@Test
	void validValueIsNotOutOfRange() {
		assertFalse(Utils.fieldOutOfRange("33"));
	}
	
	@Test
	void invavalidPositiveValueIsOutOfRange() {
		assertFalse(Utils.fieldOutOfRange(String.valueOf(Long.MAX_VALUE+1)));
	}
	
	@Test
	void invavalidNegativeValueIsOutOfRange() {
		assertFalse(Utils.fieldOutOfRange(String.valueOf(Long.MIN_VALUE-1)));
	}
	
	
	@Test
	void plusZonedTimestampIsValid() {
		assertFalse(Utils.invalidTimestampFormat("20191216T12:00:00+0000"));
	}
	
	@Test
	void minusZonedTimestampIsValid() {
		assertFalse(Utils.invalidTimestampFormat("20191216T12:00:00-0300"));
	}
	
	@Test
    void minusZonedTimestampIsInvalid() {
	    assertTrue(Utils.invalidTimestampFormat("20191216T12:60:00-0300"));
    }
	
	@Test
    void dataValidationIsNotValid() {
        assertTrue(Utils.dateValidation("2019-11-40"));
    }
	
	@Test
    void dataValidationIsValid() {
        assertFalse(Utils.dateValidation("20191101"));
    }
	
	@Test
    void getLocalDateOfIsNotValid() {
        assertTrue(Utils.getLocalDateOf("2019-11-40"));
    }
    
    @Test
    void getLocalDateOfIsValid() {
        assertFalse(Utils.getLocalDateOf("20191101"));
    }
	
	@Test
	void stringIsOutOfRange() {
		assertTrue(Utils.stringLengthOutOfRange(StringUtils.repeat("A", 21).length(), 20));
	}
	
	@Test
	void stringIsNOTOutOfRange() {
		assertFalse(Utils.stringLengthOutOfRange(StringUtils.repeat("A", 20).length(), 20));
	}
	
	@Test
	void integerIsNOTOutOfRange() {
		for(int value = 0; value < 10; value++ ) {
			assertFalse(Utils.decimalFieldOutOfRange(String.valueOf(value)));
		}		
	}
	
	@Test
	void decimalIsNOTOutOfRange() {
		for(double value = 0.55; value < 10; value++ ) {
			assertFalse(Utils.decimalFieldOutOfRange(String.valueOf(value)));
		}		
	}
	
	@Test
    void shouldPassCalculateTotalOfPages() {
	    assertThat(Utils.calculateTotalOfPages(13, 12)).isEqualTo(2);
	    assertThat(Utils.calculateTotalOfPages(1, 1)).isEqualByComparingTo(1);
    }
	
	@Test
    void shouldPassInvalidDoubleField() {
        assertTrue(Utils.invalidDoubleField("1Z"));
    }
	
	@Test
    void shouldNotPassInvalidDoubleField() {
        assertTrue(Utils.invalidDoubleField("teste"));
    }
	
	@Test
    void shouldPassAnyTimeStampFieldIsInvalid() {
        List<String> list = new ArrayList<>();
        list.add("20191216T12:00:00.123");
        
        assertTrue(Utils.anyTimeStampFieldIsInvalid(list));
    }
	
	@Test
    void shouldNotPassAnyTimeStampFieldIsInvalided() {
        List<String> list = new ArrayList<>();
        list.add("20191216T12:00:59-0300"); // OK
        list.add("20191216T12:59:00-0300"); // OK
        
        assertFalse(Utils.anyTimeStampFieldIsInvalid(list));
        
        list.add("20191216T12:00:60-2300"); //NOK
        
        assertTrue(Utils.anyTimeStampFieldIsInvalid(list));
    }
	
	@Test
    void shouldNotPassAnyTimeStampFieldIsInvalid() {
	    List<String> list = new ArrayList<>();
	    
	    assertTrue(Utils.anyTimeStampFieldIsInvalid(list));
    }
	
	@Test
    void shouldPassAnyNumericFieldIsInvalid() {
        List<String> list = new ArrayList<>();
        list.add("1");
        
        assertFalse(Utils.anyNumericFieldIsInvalid(list));
        
        list.add("b");
        
        assertTrue(Utils.anyNumericFieldIsInvalid(list));
    }
	
	@Test
    void shouldNotPassAnyNumericFieldIsInvalided() {
        List<String> list = new ArrayList<>();

        assertTrue(Utils.anyNumericFieldIsInvalid(list));
        
        list.add("1");
        
        assertFalse(Utils.anyNumericFieldIsInvalid(list));
        
        list.add("teste");
        
        assertTrue(Utils.anyNumericFieldIsInvalid(list));

    }
	
	@Test
    void shouldNotPassAnyNumericFieldIsInvalid() {
	    List<String> list = new ArrayList<>();
	    list.add("1"); // OK
	    list.add("2"); // OK
	    
        assertFalse(Utils.anyNumericFieldIsInvalid(list));
	    
	    list.add("a"); //NOK
 
	    assertTrue(Utils.anyNumericFieldIsInvalid(list));
    }
	
	@Test
    void shouldPassAnyDoubleFieldIsInvalid() {
        List<String> list = new ArrayList<>();
        list.add("1D");
        list.add("2D");
        
        assertFalse(Utils.anyDoubleFieldIsInvalid(list));
        
        list.add("2Z");
        
        assertTrue(Utils.anyDoubleFieldIsInvalid(list));
    }
    
    @Test
    void shouldNotPassAnyDoubleFieldIsInvalided() {
        List<String> list = new ArrayList<>();
        list.add("1D");
        list.add("2D");
        
        assertFalse(Utils.anyDoubleFieldIsInvalid(list));
        
        list.add("L");
        
        assertTrue(Utils.anyDoubleFieldIsInvalid(list));
    }
    
    @Test
    void shouldNotPassAnyDoubleFieldIsInvalid() {
        List<String> list = new ArrayList<>();
 
        assertTrue(Utils.anyDoubleFieldIsInvalid(list));
    }
}