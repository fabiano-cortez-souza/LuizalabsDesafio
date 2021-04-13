package br.com.luzialabs.desafio.agendaTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.luzialabs.desafio.agenda.AgendaSettings;

class AgendaTest {
    
    private AgendaSettings agendaSettings = new AgendaSettings() {};;
    
    @BeforeEach
    void init() {
    }

	@Test
	void initDate() {
	    
	    agendaSettings.init();
	}
}
