package br.com.luzialabs.desafio.agendaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.TimeZone;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.luzialabs.desafio.agenda.AgendaSettings;
import br.com.luzialabs.desafio.agenda.constants.Constants;

class AgendaTest {
    
    private AgendaSettings agendaSettings = new AgendaSettings() {};;
    
    @BeforeEach
    void init() {
    }

	@Test
	void initDate() {
	    
	    agendaSettings.init();
	    assertEquals(Constants.TIME_ZONE_ID, TimeZone.getDefault().getID());
	}
}
